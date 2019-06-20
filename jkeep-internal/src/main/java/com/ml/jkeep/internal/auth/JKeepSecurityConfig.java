package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSONObject;
import com.ml.jkeep.service.system.UserAuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserAuthServiceImpl userAuthService;
    @Autowired
    private JKeepSecurityMetadataSource securityMetadataSource;
    @Autowired
    private JKeepAccessDecisionManager accessDecisionManager;
    @Autowired
    private JKeepAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
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
//                .loginPage("/login")
//                .loginProcessingUrl("/auth")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler((req, resp, e) -> {
                    // TODO 登陆失败处理
                    log.info("登陆失败处理 : {}", JSONObject.toJSONString(req.getParameterMap()));
                    if (e instanceof BadCredentialsException ||
                            e instanceof UsernameNotFoundException) {
                        log.info("账户名或者密码输入错误！");
                    } else if (e instanceof LockedException) {
                        log.info("账户被锁定，请联系管理员!");
                    } else if (e instanceof CredentialsExpiredException) {
                        log.info("密码过期，请联系管理员!");
                    } else if (e instanceof AccountExpiredException) {
                        log.info("账户过期，请联系管理员!");
                    } else if (e instanceof DisabledException) {
                        log.info("账户被禁用，请联系管理员!");
                    } else {
                        log.info("登录失败，请联系管理员!");
                    }
                })
                .successHandler((req, resp, auth) -> {
                    // TODO 登陆成功处理
                    log.info("登陆成功处理 : {}", JSONObject.toJSONString(req.getParameterMap()));
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    // TODO 注销成功处理
                    log.info("注销成功处理 : {}", JSONObject.toJSONString(req.getParameterMap()));
                })
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/*.html", "/css/**", "/img/**", "/js/**");
    }

}
