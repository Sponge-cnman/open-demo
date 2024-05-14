package org.lx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lx.common.CommonException;
import org.lx.pojo.phone.PhoneTake;
import org.lx.pojo.phone.UserPhone;

public interface UserPhoneService extends IService<UserPhone> {

    void insertPhone(PhoneTake phoneTake) throws CommonException;
}
