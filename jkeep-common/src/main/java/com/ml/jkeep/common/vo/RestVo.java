package com.ml.jkeep.common.vo;

import com.ml.jkeep.common.constant.ResultMsg;
import lombok.Data;

import java.io.Serializable;

/**
 * 业务响应实体
 *
 * @author liangzhong
 * @date 2019/6/23 16:56
 */
@Data
public class RestVo<T> implements Serializable {

    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回结果
     */
    private T result;

    private boolean success = true;

    public RestVo() {
        ResultMsg msg = ResultMsg.SUCCESS;
        this.code = msg.getCode();
        this.message = msg.getMessage();
    }

    public static RestVo SUCCESS() {
        return new RestVo();
    }

    public static RestVo SUCCESS(Object obj) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setResult(obj);
        return restVo;
    }

    public static RestVo SUCCESS(String msg) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setMessage(msg);
        return restVo;
    }

    public static RestVo SUCCESS(Object obj, String msg) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setResult(obj);
        restVo.setMessage(msg);
        return restVo;
    }

    public static RestVo FAIL() {
        RestVo<Object> restVo = new RestVo<>();
        ResultMsg result = ResultMsg.FAIL;
        restVo.setCode(result.getCode());
        restVo.setMessage(result.getMessage());
        restVo.setSuccess(false);
        return restVo;
    }

    public static RestVo FAIL(String msg) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setCode(ResultMsg.FAIL.getCode());
        restVo.setMessage(msg);
        restVo.setSuccess(false);
        return restVo;
    }

    public static RestVo FAIL(String code,String msg) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setCode(code);
        restVo.setMessage(msg);
        restVo.setSuccess(false);
        return restVo;
    }

    public static RestVo FAIL(ResultMsg result) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setCode(result.getCode());
        restVo.setMessage(result.getMessage());
        restVo.setSuccess(false);
        return restVo;
    }

    public static RestVo ERRUR(String msg) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setCode(ResultMsg.ERROR.getCode());
        restVo.setMessage(msg);
        restVo.setSuccess(false);
        return restVo;
    }

    public static RestVo ERRUR(ResultMsg result) {
        RestVo<Object> restVo = new RestVo<>();
        restVo.setCode(result.getCode());
        restVo.setMessage(result.getMessage());
        restVo.setSuccess(false);
        return restVo;
    }
}
