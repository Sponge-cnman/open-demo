package org.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lx.common.CommonException;
import org.lx.pojo.PhoneTake;
import org.lx.pojo.UserPhone;

public interface UserPhoneService extends IService<UserPhone> {

    void insertPhone(PhoneTake phoneTake) throws CommonException;
}
