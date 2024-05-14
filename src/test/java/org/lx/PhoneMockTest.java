package org.lx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lx.common.CommonException;
import org.lx.pojo.phone.PhoneTake;
import org.lx.pojo.phone.UserPhone;
import org.lx.service.UserPhoneService;
import org.lx.service.impl.UserPhoneServiceImpl;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 兵结藤诚
 * @version 1.0
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class PhoneMockTest {
//    @Autowired
    private UserPhoneService userPhoneService = new UserPhoneServiceImpl();

    @Test
    public void testMockUserNameLength() {
        QueryWrapper<UserPhone> queryWrapper = new QueryWrapper<>();
        Mockito.when(userPhoneService.getOne(Mockito.any())).thenReturn(new UserPhone("lx","18813153532"));
        Mockito.when(userPhoneService.save(Mockito.any()));
        CommonException commonException1 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("", "188131535322"));
        });

        Assert.assertEquals("用户名的长度必须在1-10位", commonException1.getResultCode().message());
    }


    @Test
    public void testUserNameLength() {

        //验证用户名长度<1
        CommonException commonException1 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("", "188131535322"));
        });

        Assert.assertEquals("用户名的长度必须在1-10位", commonException1.getResultCode().message());

        //验证用户名长度=1
        CommonException commonException2 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("l", "18813153532"));
        });
        Assert.assertEquals("手机号已经注册过", commonException2.getResultCode().message());

        //验证用户名长度<=1 <=10
        CommonException commonException3 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("lx", "18813153532"));
        });

        Assert.assertEquals("手机号已经注册过", commonException3.getResultCode().message());

        //验证用户名长度=10
        CommonException commonException4 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("lxxxxxxxxx", "18813153532"));
        });

        Assert.assertEquals("手机号已经注册过", commonException4.getResultCode().message());

        //验证用户名长度>10
        CommonException commonException5 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("lxxxxxxxxxxxxxx", "188131535322"));
        });

        Assert.assertEquals("用户名的长度必须在1-10位", commonException5.getResultCode().message());

    }

    @Test
    public void testPhone() {


        //验证手机格式不匹配
        CommonException commonException1 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("lx", "188131535322"));
        });

        Assert.assertEquals("手机格式错误", commonException1.getResultCode().message());

        //验证手机格式匹配
        CommonException commonException2 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("l", "18813153532"));
        });
        Assert.assertEquals("手机号已经注册过", commonException2.getResultCode().message());

        //验证手机格式不匹配
        CommonException commonException3 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("lx", "1881315"));
        });
        Assert.assertEquals("手机格式错误", commonException3.getResultCode().message());

    }

    @Test
    public void testUniPhone() {


        //验证手机号唯一
        CommonException commonException1 = Assert.assertThrows(CommonException.class, () -> {
            userPhoneService.insertPhone(new PhoneTake("lx", "18813153532"));
        });

        Assert.assertEquals("手机号已经注册过", commonException1.getResultCode().message());



    }
}
