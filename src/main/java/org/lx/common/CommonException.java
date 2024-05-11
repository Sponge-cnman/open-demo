package org.lx.common;


import lombok.Getter;

/**
 * @author 兵结藤诚
 * @version 1.0
 * 异常处理
 */
@Getter
public class CommonException extends Exception{
    private ResultCode resultCode;
    public CommonException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
}
