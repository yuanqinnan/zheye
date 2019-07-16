package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:yuanqinnan
 * @date: 2019/7/16 13:48
 */

@Data
@TableName("t_sys_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 日志ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作用户
     */
    @TableField("username")
    private String username;

    /**
     * 操作内容
     */
    @TableField("operation")
    private String operation;

    /**
     * 耗时
     */
    @TableField("time")
    private Long time;

    /**
     * 操作方法
     */
    @TableField("method")
    private String method;

    /**
     * 方法参数
     */
    @TableField("params")
    private String params;

    /**
     * 操作者IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 操作地点
     */
    @TableField("location")
    private String location;
}