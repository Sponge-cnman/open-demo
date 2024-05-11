package org.lx.controller;

import lombok.AllArgsConstructor;
import org.lx.common.CommonException;
import org.lx.common.Result;
import org.lx.common.ResultCode;
import org.lx.pojo.PhoneTake;
import org.lx.service.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @PostMapping("/register")
    public Result registerUser(@RequestBody PhoneTake phoneTake) throws CommonException {
        userPhoneService.insertPhone(phoneTake);
        return new Result(ResultCode.SUCCESS);
    }

}
