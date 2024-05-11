package org.lx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lx.common.CommonException;
import org.lx.common.ResultCode;
import org.lx.mapper.UserPhoneMapper;
import org.lx.pojo.PhoneTake;
import org.lx.pojo.UserPhone;
import org.lx.service.UserPhoneService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author: 兵结藤诚
 * @description
 * @date: 2024/5/11 17:04
 */
@Service
public class UserPhoneServiceImpl extends ServiceImpl<UserPhoneMapper,UserPhone> implements UserPhoneService {

    @Override
    public void insertPhone(PhoneTake phoneTake) throws CommonException {
        //判断用户名长度是否在1-10个字符之间
        String userName = phoneTake.getUserName();
        if (userName == null|| userName.length()<1||userName.length()>10){
            throw new CommonException(ResultCode.USERNAME_OUTLEN);
        }
        //匹配手机规则
        if(!isValidPhoneNumber(phoneTake.getPhone())) {
            throw new CommonException(ResultCode.INVALID_PHONE);
        }

        //验证手机号唯一
        QueryWrapper<UserPhone> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phoneTake.getPhone());
        UserPhone phone = this.getOne(queryWrapper);
        if (phone !=null){
            throw new CommonException(ResultCode.RE_PHONE);
        }
        //保存手机号
        this.save(new UserPhone(phoneTake.getUserName(),phoneTake.getPhone()));

    }


    public static boolean isValidPhoneNumber(String phoneNumber) {
        if ((phoneNumber != null) && (!phoneNumber.isEmpty())) {
            return Pattern.matches("^1[3-9]\\d{9}$", phoneNumber);
        }
        return false;
    }

}
