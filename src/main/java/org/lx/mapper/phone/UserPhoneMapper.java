package org.lx.mapper.phone;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lx.pojo.phone.UserPhone;

@Mapper
public interface UserPhoneMapper extends BaseMapper<UserPhone> {

    UserPhone selectByPhoneOne(String phone);

    void insertPhone(@Param(value = "userName") String userName, @Param(value = "phone")String phone);
}
