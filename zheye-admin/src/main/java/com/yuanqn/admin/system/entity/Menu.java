package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 19:00
 */
@Data
@TableName("t_sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -6421110778894696181L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long menuId;

    @TableId(value = "parent_id")
    private Long parentId;

    @TableId(value = "name")
    private String menuName;

    @TableId(value = "path")
    private String path;

    @TableId(value = "component")
    private String component;

    @TableId(value = "perms")
    private String perms;

    @TableId(value = "icon")
    private String icon;

    @TableId(value = "type")
    private String type;

    @TableId(value = "order_num")
    private Double orderNum;

    @TableId(value = "create_time")
    private Date createTime;

    @TableId(value = "modify_time")
    private Date modifyTime;
}