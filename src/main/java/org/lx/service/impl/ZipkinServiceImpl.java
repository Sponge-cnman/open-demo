package org.lx.service.impl;

import org.lx.mapper.zipkin.ZipkinMapper;
import org.lx.pojo.zipkin.ZipError;
import org.lx.pojo.zipkin.ZipResponseTime;
import org.lx.pojo.zipkin.ZipVisit;
import org.lx.service.ZipkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 兵结藤诚
 * @version 1.0
 */
@Service
public class ZipkinServiceImpl implements ZipkinService {
    @Autowired
    private ZipkinMapper zipkinMapper;

    @Override
    public List<ZipVisit> findVisitInMinute() {
        return zipkinMapper.findVisitInMinute();
    }

    @Override
    public List<ZipResponseTime> findResponseInMinute() {
        return zipkinMapper.findResponseInMinute();
    }

    @Override
    public List<ZipError> findErrorInMinute() {
        return zipkinMapper.findErrorInMinute();
    }
}
