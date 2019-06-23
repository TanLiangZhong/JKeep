package com.ml.jkeep.common.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果消息 - Enum
 *
 * @author liangzhong
 * @date 2019/6/23 16:56
 */
@Getter
@AllArgsConstructor
public enum ResultMsg {

    SUCCESS("S0000", "操作成功"),
    FAIL("F0000", "操作失败"),
    LOGIN_FAIL_WRONG_PASSWORD("F0001", "账户名或者密码输入错误!"),
    LOGIN_FAIL_LOCKED("F0002", "账户被锁定,请联系管理员!"),
    LOGIN_FAIL_CREDENTIALS_EXPIRED("F0003", "登录过期,请重新登录!"),
    LOGIN_FAIL_ACCOUNT_EXPIRED("F0004", "账户过期,请联系管理员!"),
    LOGIN_FAIL_DISABLED("F0005", "账户被禁用,请联系管理员!"),
    LOGIN_FAIL("F0006", "登录失败,请联系管理员!"),
    ACCESS_DENIED("F0007", "无法访问当前URL");

    private String code;
    private String name;
}
