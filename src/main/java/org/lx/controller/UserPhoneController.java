package org.lx.controller;

import com.alibaba.fastjson.JSON;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.semconv.SemanticAttributes;
import org.lx.common.CommonException;
import org.lx.common.Result;
import org.lx.common.ResultCode;
import org.lx.pojo.phone.PhoneTake;
import org.lx.service.UserPhoneService;
import org.lx.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 兵结藤诚
 * @description
 * @date: 2024/5/11 17:30
 */
@RestController
public class UserPhoneController {
    @Autowired
    private UserPhoneService userPhoneService;
    private final Tracer tracer;
    @Autowired
    private OpenTelemetry openTelemetry;
    @Autowired
    private RedisUtil redisUtil;
    private String key = "registerUser_exception";
    private final Logger logger = LoggerFactory.getLogger(UserPhoneController.class);

    @Autowired
    UserPhoneController(OpenTelemetry openTelemetry) {
        tracer = openTelemetry.getTracer(UserPhoneController.class.getName(), "0.1.0");
    }

    @PostMapping("/register")
    public Result registerUser(@RequestBody PhoneTake phoneTake) throws CommonException {

        Context current = Context.current();
        Span currentSpan = Span.fromContext(current);
        System.out.println(currentSpan.getSpanContext().getSpanId());

        // 记录接口处理开始时间
        long startTime = System.currentTimeMillis();

        //创建span 设置为服务端
        Span span = tracer.spanBuilder("registerUser").setSpanKind(SpanKind.SERVER).startSpan();
        span.setAttribute(SemanticAttributes.HTTP_METHOD, "POST");
        span.setAttribute(SemanticAttributes.HTTP_URL, "/register");
        span.setAttribute("RequestBody", JSON.toJSONString(phoneTake));
        span.setStatus(StatusCode.OK, "正常运行");

        // Make the span the current span
        try (Scope scope = span.makeCurrent()) {
            userPhoneService.insertPhone(phoneTake);
            return new Result(ResultCode.SUCCESS);
        } catch(Throwable t) {
            //异常统计处理
            redisExceptionSave();

            //记录异常
            span.setStatus(StatusCode.ERROR, "出现异常");
            span.recordException(t);
            throw t;
        } finally {
            // 记录接口处理结束时间
            long endTime = System.currentTimeMillis();

            // 计算接口处理时间
            long elapsedTime = endTime - startTime;
            span.setAttribute("responseTime", elapsedTime+"ms");
             span.end();
        }


    }

    private void redisExceptionSave() {
        if (redisUtil.hasKey(key)){
            int nums = (int) redisUtil.get(key) + 1;
            if (nums >= 10){
                //发出警告
                logger.error("在一分钟内出现了10次异常");
                redisUtil.del(key);
            }else {
                redisUtil.incr(key,1);
            }
        }else {
            //初始化统计设置一分钟时间
            redisUtil.set(key,1,60);
        }

    }


}
