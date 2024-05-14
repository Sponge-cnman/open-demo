package org.lx.pojo.phone;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 兵结藤诚
 * @description 用户电话表
 * @date: 2024/5/11 17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhone {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String phone;

    public UserPhone(String userName, String phone) {
            this.userName = userName;
            this.phone = phone;
    }
}
