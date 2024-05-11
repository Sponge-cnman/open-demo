package org.lx.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private boolean success;//是否成功
    private Integer code;
    private String message;//返回信息
    private T data;//返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }
    public Result(int code,String message,boolean success,T data)
    {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }


    public static Result SUCCESS() {
        return new Result<>(ResultCode.SUCCESS);
    }


    public static Result FAIL() {
        return new Result<>(ResultCode.FAIL);
    }
}