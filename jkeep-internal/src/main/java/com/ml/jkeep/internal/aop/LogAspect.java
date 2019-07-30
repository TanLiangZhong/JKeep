package com.ml.jkeep.internal.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ml.jkeep.internal.auth.JKeepSecurityContextHolder;
import com.ml.jkeep.jpa.system.entity.sys.SysLog;
import com.ml.jkeep.jpa.system.entity.sys.UserAuth;
import com.ml.jkeep.service.system.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * 日志切面
 *
 * @author liangzhong
 * @date 2019/6/22 0:39
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = "JKeep.logAop", name = "enable", havingValue = "true")
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("execution(* com.ml.jkeep.internal.system..*.*(..))")
    public void controllerAspect() {
        // AOP 拦截 Controller
    }

    /**
     * 环绕通知，围绕着方法执行
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(final ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        MethodSignature methodSignature = (MethodSignature) pjp.getStaticPart().getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        assert args.length == parameterAnnotations.length;
        StringBuilder pathParam = new StringBuilder();
        String bodyParam = null;
        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            for (Annotation annotation : parameterAnnotations[argIndex]) {
                if (annotation instanceof PathVariable) {
                    if (args[argIndex] != null) {
                        pathParam.append(args[argIndex]).append("&");
                    }
                } else if (annotation instanceof RequestBody) {
                    bodyParam = JSON.toJSONString(args[argIndex], SerializerFeature.DisableCircularReferenceDetect);
                }
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String methodName = pjp.getTarget().getClass().getName() + "." + method.getName();
        log.info(appendArgs(methodName, pathParam.toString(), JSON.toJSONString(request.getParameterMap()), bodyParam, null));
        long begin = System.currentTimeMillis();
        Object result = pjp.proceed();
        Long timeConsuming = System.currentTimeMillis() - begin;
        log.info("结果: {}, 耗时: {}", JSON.toJSONString(result), timeConsuming);

        // 保存系统日志
        JSONObject param = new JSONObject();
        param.put("PathParam", pathParam);
        param.put("RequestParam", request.getParameterMap());
        param.put("BodyParam", bodyParam);
        UserAuth user = Optional.ofNullable(JKeepSecurityContextHolder.getUserInfo()).orElse(new UserAuth());
        sysLogService.insertLog(new SysLog(user.getUserId(), user.getUsername(), "system", request.getRequestURI(), request.getMethod(), param.toJSONString(), methodName, request.getRemoteAddr(), timeConsuming));
        return result;
    }

    /**
     * 异常通知，在方法抛出异常之后
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.info("AfterThrowing, 方法抛出异常后执行通知，{} , Error: {}", joinPoint, e.getMessage());
    }

    /**
     * 拼接参数
     *
     * @param method
     * @param pathParam
     * @param queryParam
     * @param bodyParam
     * @param e
     * @return
     */
    private String appendArgs(String method, String pathParam, String queryParam, String bodyParam, Throwable e) {
        StringBuilder sb = new StringBuilder();
        if (e == null) {
            sb.append("\n######################### INFO #########################\n");
            sb.append("请求方法: ").append(method).append("()\n");
        } else {
            sb.append("\n######################### Error #########################\n");
            sb.append("异常方法: ").append(method).append("()\n");
            sb.append("异常信息: ").append(e.getMessage()).append("\n");
        }
        if (!StringUtils.isEmpty(pathParam)) {
            sb.append("请求参数1: ").append(pathParam).append("\n");
        }
        if (!StringUtils.isEmpty(queryParam)) {
            sb.append("请求参数2: ").append(queryParam).append("\n");
        }
        if (!StringUtils.isEmpty(bodyParam)) {
            sb.append("请求参数3: ").append(bodyParam).append("\n");
        }
        return sb.toString();
    }

//    /**
//     * 处理请求参数
//     *
//     * @param joinPoint
//     * @param e
//     * @return
//     */
//    private String handleArgs(JoinPoint joinPoint, Throwable e) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
//        Method method = methodSignature.getMethod();
//        Object[] args = joinPoint.getArgs();
//        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//        assert args.length == parameterAnnotations.length;
//        StringBuilder pathParam = new StringBuilder();
//        String bodyParam = null;
//        for (int argIndex = 0; argIndex < args.length; argIndex++) {
//            for (Annotation annotation : parameterAnnotations[argIndex]) {
//                if (annotation instanceof PathVariable) {
//                    if (args[argIndex] != null) {
//                        pathParam.append(args[argIndex] + "&");
//                    }
//                } else if (annotation instanceof RequestBody) {
//                    bodyParam = JSON.toJSONString(args[argIndex], SerializerFeature.DisableCircularReferenceDetect);
//                }
//            }
//        }
//        return appendArgs(joinPoint, pathParam.toString(), handleRequestParam(), bodyParam, e);
//    }
//
//    /**
//     * 处理Request里面参数
//     *
//     * @return
//     */
//    private String handleRequestParam() {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        Map<String, String[]> paramsMap = request.getParameterMap();
//        if (paramsMap != null && paramsMap.size() > 0) {
//            StringBuilder params = new StringBuilder();
//            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
//                String[] values = entry.getValue();
//                if (values != null) {
//                    String value;
//                    if (values.length == 1) {
//                        value = values[0];
//                    } else {
//                        value = JSON.toJSONString(values, SerializerFeature.DisableCircularReferenceDetect);
//                    }
//                    params.append(entry.getKey() + "=" + value + "&");
//                }
//            }
//            return params.toString();
//        }
//        return null;
//    }


}
