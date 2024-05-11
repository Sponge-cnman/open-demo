package org.lx.handler;





import org.lx.common.CommonException;
import org.lx.common.Result;
import org.lx.common.ResultCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 兵结藤诚
 * @version 1.0
 * 使用 @ControllerAdvice 实现全局异常处理
 * /@ExceptionHandler 统一处理某一类异常，从而能够减少代码重复率和复杂度
 * /@ControllerAdvice：异常集中处理，更好的使业务逻辑与异常处理剥离开；其是对Controller层进行拦截
 * /@ResponseStatus：可以将某种异常映射为HTTP状态码
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Result<Object>> error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        //对各种异常的统一调度
        if (e.getClass() == CommonException.class) {
            CommonException ce = (CommonException) e;
            ResultCode resultCode = ce.getResultCode();

            return ResponseEntity.status(500).body(new Result<>(resultCode));
        }
        else {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Result<>(ResultCode.SERVER_ERROR));
        }
    }
}
