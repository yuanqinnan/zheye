package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 8:55
 */
@Data
@TableName("t_sys_user_role")
public class UserRole {

    @TableId(value = "user_id")
    private Long userId;

    @TableId(value = "role_id")
    private Long roleId;
}