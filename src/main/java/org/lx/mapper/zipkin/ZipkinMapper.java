package org.lx.mapper.zipkin;

import org.apache.ibatis.annotations.Mapper;
import org.lx.pojo.zipkin.ZipError;
import org.lx.pojo.zipkin.ZipResponseTime;
import org.lx.pojo.zipkin.ZipVisit;

import java.util.List;

/**
 * @author 兵结藤诚
 * @version 1.0
 */
@Mapper
public interface ZipkinMapper {
    List<ZipVisit> findVisitInMinute();
    List<ZipResponseTime> findResponseInMinute();
    List<ZipError> findErrorInMinute();
}