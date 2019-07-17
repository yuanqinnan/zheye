package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 14:48
 */

@Data
@TableName("t_sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 2176864310287493827L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long roleId;

    @TableField("name")
    private String roleName;

    @TableField("remark")
    private String remark;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date createTime;

    @TableField("modify_time")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date modifyTime;
}