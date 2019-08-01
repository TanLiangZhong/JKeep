package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSONObject;
import com.ml.jkeep.common.constant.Common;
import com.ml.jkeep.common.constant.ResultMsg;
import com.ml.jkeep.common.vo.RestVo;
import com.ml.jkeep.service.system.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 安全认证配置
 *
 * @author 谭良忠
 * @date 2019/6/20 10:27
 */
@Slf4j
@EnableWebSecurity
public class JKeepSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthServiceImpl userAuthService;
    @Autowired
    private JKeepSecurityMetadataSource securityMetadataSource;
    @Autowired
    private JKeepAccessDecisionManager accessDecisionManager;
    @Autowired
    private JKeepAccessDeniedHandler accessDeniedHandler;

    /**
     * 令牌有效期秒
     */
    @Value("${jkeep.auth.token-validity}")
    private int tokenValidity;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(securityMetadataSource);
                        o.setAccessDecisionManager(accessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginPage(Common.LOGIN_PAGE_URL)
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler((req, resp, e) -> {
                    log.error("登陆失败处理 : {}", JSONObject.toJSONString(req.getParameterMap()));
                    ResultMsg resultMsg;
                    if (e instanceof BadCredentialsException ||
                            e instanceof UsernameNotFoundException) {
                        // 账户名或者密码输入错误
                        resultMsg = ResultMsg.LOGIN_FAIL_WRONG_PASSWORD;
                    } else if (e instanceof LockedException) {
                        // 账户被锁定,请联系管理员!
                        resultMsg = ResultMsg.LOGIN_FAIL_LOCKED;
                    } else if (e instanceof CredentialsExpiredException) {
                        // 登录过期,请重新登录!
                        resultMsg = ResultMsg.LOGIN_FAIL_CREDENTIALS_EXPIRED;
                    } else if (e instanceof AccountExpiredException) {
                        // 账户过期,请联系管理员!
                        resultMsg = ResultMsg.LOGIN_FAIL_ACCOUNT_EXPIRED;
                    } else if (e instanceof DisabledException) {
                        // 账户被禁用,请联系管理员!
                        resultMsg = ResultMsg.LOGIN_FAIL_DISABLED;
                    } else {
                        // 登录失败,请联系管理员!
                        resultMsg = ResultMsg.LOGIN_FAIL;
                    }
                    if (req.getHeader(Common.CSRF_TOKEN_KEY) != null) {
                        // Ajax 提交
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = resp.getWriter();
                        out.write(JSONObject.toJSONString(RestVo.FAIL(resultMsg)));
                        out.flush();
                        out.close();
                    } else {
                        // 表单提交
                        resp.sendRedirect("/login.html");
                    }
                })
                .successHandler((req, resp, auth) -> {
                    if (req.getHeader(Common.CSRF_TOKEN_KEY) != null) {
                        // Ajax 提交
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = resp.getWriter();
                        out.write(JSONObject.toJSONString(RestVo.SUCCESS("登录成功")));
                        out.flush();
                        out.close();
                    } else {
                        // 表单提交
                        resp.sendRedirect("/index");
                    }
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    //  注销成功重定向登录页
                    if (req.getHeader(Common.CSRF_TOKEN_KEY) != null) {
                        // Ajax 提交
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = resp.getWriter();
                        out.write(JSONObject.toJSONString(RestVo.SUCCESS("登出成功")));
                        out.flush();
                        out.close();
                    } else {
                        // 表单提交
                        resp.sendRedirect("/login.html");
                    }
                })
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                // X-Frame-Options: SAMEORIGIN, 允许来自同一源的任何请求. 若frame需要访问其他域的页面, 需禁用此功能.
                .and().headers().frameOptions().sameOrigin()
                // 记住密码
                .and().rememberMe().tokenValiditySeconds(tokenValidity)
//                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        // 忽略请求, 无需鉴权即可访问 ,
        //                "/api/**", "/login", "/auth", "/error"
        web.ignoring().antMatchers("/plugins/**", "**.js", "/img/**", "**.css", "/favicon.ico", "/401.html", "/404.html");
    }

}
