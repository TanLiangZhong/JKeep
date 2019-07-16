package com.ml.jkeep.internal.auth;

import com.alibaba.fastjson.JSONObject;
import com.ml.jkeep.common.constant.Common;
import com.ml.jkeep.common.constant.ResultMsg;
import com.ml.jkeep.common.vo.RestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 访问被拒处理
 *
 * @author 谭良忠
 * @date 2019/6/20 11:41
 */
@Slf4j
@Component
public class JKeepAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // TODO 带优化有Bug , 若 Ajax 请求不传 X-CSRF-TOKEN 当如何？
        log.info("处理访问被拒 , Parameter: {}, RequestURI: {}, RemoteUser: {}, RemoteAddr: {}",
                JSONObject.toJSONString(request.getParameterMap()), request.getRequestURI(),
                request.getRemoteUser(), request.getRemoteAddr());
        if (request.getHeader(Common.CSRF_TOKEN_KEY) != null) {
            // Ajax 提交
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(JSONObject.toJSONString(RestVo.FAIL(ResultMsg.ACCESS_DENIED)));
            out.flush();
            out.close();
        } else {
            // 表单提交 . 重定向401页
            response.sendRedirect("/401.html");
        }
    }
}
