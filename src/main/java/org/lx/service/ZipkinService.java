package org.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lx.common.CommonException;
import org.lx.pojo.phone.PhoneTake;
import org.lx.pojo.phone.UserPhone;
import org.lx.pojo.zipkin.ZipError;
import org.lx.pojo.zipkin.ZipResponseTime;
import org.lx.pojo.zipkin.ZipVisit;

import java.util.List;

public interface ZipkinService  {

    List<ZipVisit> findVisitInMinute();
    List<ZipResponseTime> findResponseInMinute();
    List<ZipError> findErrorInMinute();

}
