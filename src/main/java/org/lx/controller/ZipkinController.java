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
import org.lx.pojo.zipkin.ZipError;
import org.lx.pojo.zipkin.ZipResponseTime;
import org.lx.pojo.zipkin.ZipVisit;
import org.lx.service.UserPhoneService;
import org.lx.service.ZipkinService;
import org.lx.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 兵结藤诚
 * @description
 * @date: 2024/5/11 17:30
 */
@RestController
public class ZipkinController {
    @Autowired
    private ZipkinService zipkinService;

    private final Logger logger = LoggerFactory.getLogger(ZipkinController.class);



    @GetMapping("/getVisit")
    public Result<List<ZipVisit>> getVisit() {
        return new Result<>(ResultCode.SUCCESS,   zipkinService.findVisitInMinute());

    }

    @GetMapping("/getResponseTime")
    public Result<List<ZipResponseTime>> getResponseTime() {
        return new Result<>(ResultCode.SUCCESS,  zipkinService.findResponseInMinute());

    }

    @GetMapping("/getError")
    public Result<List<ZipError>> getError() {
        return new Result<>(ResultCode.SUCCESS,   zipkinService.findErrorInMinute());

    }



}
