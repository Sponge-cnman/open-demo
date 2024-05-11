package org.lx.common;

/**
 * 公共返回码
 * @author 兵结藤诚
 */
public enum ResultCode {

    SUCCESS(true, 10000, "操作成功！"),
    FAIL(false, 10001, "操作失败"),
    USERNAME_OUTLEN(false,10002,"用户名的长度必须在1-10位"),
    INVALID_PHONE(false,10003,"手机格式错误"),
    RE_PHONE(false,10004,"手机号已经注册过"),

    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

 ;




    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public  int code() {
        return code;
    }

    public String message() {
        return message;
    }

}