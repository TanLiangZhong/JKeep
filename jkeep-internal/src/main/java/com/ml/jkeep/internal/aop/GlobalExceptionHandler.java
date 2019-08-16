package com.ml.jkeep.internal.aop;

import com.ml.jkeep.common.constant.ResultMsg;
import com.ml.jkeep.common.exception.BusinessException;
import com.ml.jkeep.common.vo.RestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;

/**
 * 全局异常拦截
 *
 * @author 谭良忠
 * @date 2019/8/16 14:17
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param ex BusinessException
     * @return RestVo
     */
    @ExceptionHandler(BusinessException.class)
    public RestVo handleBusinessException(BusinessException ex) {
        ResultMsg result = ex.getResult();
        log.info("BusinessException restVo:{}", result);
        return RestVo.FAIL(result.getCode(), ex.getMessage());
    }

    /**
     * 系统异常
     *
     * @param param
     * @return
     */
    /**
     * 系统异常
     *
     * @param e       异常信息
     * @param request 请求对象
     * @return RestVo
     */
    @ExceptionHandler(Exception.class)
    public RestVo handleException(Exception e, HttpServletRequest request) throws IOException {
        StringBuilder message = new StringBuilder();
        message.append("\n######################### Error #########################\n");
        message.append("RequestURI: ").append(request.getRequestURI()).append("\n");
        message.append("Method: ").append(request.getMethod()).append("\n");
        message.append("Header: ").append("\n");
        Iterator<String> headerIterator = CollectionUtils.toIterator(request.getHeaderNames());
        while (headerIterator.hasNext()) {
            String name = headerIterator.next();
            message.append("\t").append(name).append(": ").append(request.getHeader(name)).append("\n");
        }
//        message.append("Request Param: ").append( JSON.toJSONString(request.getParameterMap())).append("\n");
//        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        message.append("Request Body: ").append(body);
        log.info(message.toString(), e);
        return RestVo.FAIL(ResultMsg.ERROR);
    }

}
