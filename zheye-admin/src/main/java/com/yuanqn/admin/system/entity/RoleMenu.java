package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 8:49
 */
@Data
@TableName("t_sys_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -4196457359804048354L;

    @TableId(value = "role_id")
    private Long roleId;

    @TableId(value = "menu_id")
    private Long menuId;
}