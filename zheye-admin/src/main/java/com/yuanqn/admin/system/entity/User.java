package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 16:28
 */

@Data
@TableName("t_admin_user")
public class User {

    public static final String DEFAULT_STATUS = "NORMAL";

    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";

    /**
     * 用户 ID
     */
    @TableId(value = "user_Id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    @Size(min = 4, max = 10)
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 部门 ID
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 邮箱
     */
    @TableField("e_mall")
    @Size(max = 50)
    @Email(message = "{email}")
    private String mail;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 状态 0锁定 1有效
     */
    @TableField("status")
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 性别 0男 1女 2 保密
     */
    @TableField("sex")
    private String sex;

    /**
     * 真实姓名
     */
    @TableField("name")
    private String name;
}