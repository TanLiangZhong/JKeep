package com.ml.jkeep.common.exception;


import com.ml.jkeep.common.constant.ResultMsg;

/**
 * 自定义-业务异常
 *
 * @author 谭良忠
 * @date 2019/8/16 14:17
 */
public class BusinessException extends RuntimeException {

    private ResultMsg result;

    private BusinessException(ResultMsg result) {
        super(result.getMessage());
        this.result = result;
    }

    private BusinessException(ResultMsg result, String message) {
        super(message);
        this.result = result;
    }

    private BusinessException(String message) {
        super(message);
        this.result = ResultMsg.FAIL;
    }

    public ResultMsg getResult() {
        return result;
    }

    /**
     * 创建业务异常
     *
     * @param message 异常内容
     * @return BusinessException
     */
    public static BusinessException of(String message) {
        return new BusinessException(message);
    }

    /**
     * 创建业务异常
     *
     * @param result 异常内容
     * @return BusinessException
     */
    public static BusinessException of(ResultMsg result) {
        return new BusinessException(result);
    }

    /**
     * 创建业务异常
     *
     * @param result  状态码
     * @param message 异常内容
     * @return BusinessException
     */
    public static BusinessException of(ResultMsg result, String message) {
        return new BusinessException(result, message);
    }

}
