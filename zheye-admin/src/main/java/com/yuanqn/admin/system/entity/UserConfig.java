package com.yuanqn.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 9:45
 */
@Data
@TableName("t_sys_user_config")
public class UserConfig {
    public static final String DEFAULT_THEME = "dark";
    public static final String DEFAULT_LAYOUT = "side";
    public static final String DEFAULT_MULTI_PAGE = "0";
    public static final String DEFAULT_FIX_SIDEBAR = "1";
    public static final String DEFAULT_FIX_HEADER = "1";
    public static final String DEFAULT_COLOR = "rgb(66, 185, 131)";

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 ID
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 系统主题 dark暗色风格，light明亮风格
     */
    @TableId(value = "theme")
    private String theme;

    /**
     * 系统布局 side侧边栏，head顶部栏
     */
    @TableId(value = "layout")
    private String layout;

    /**
     * 页面风格 1多标签页 0单页
     */
    @TableId(value = "multi_page")
    private String multiPage;

    /**
     * 页面滚动是否固定侧边栏 1固定 0不固定
     */
    @TableId(value = "fix_sidebar")
    private String fixSidebar;

    /**
     * 页面滚动是否固定顶栏 1固定 0不固定
     */
    @TableId(value = "fix_header")
    private String fixHeader;

    /**
     * 主题颜色 RGB值
     */
    @TableId(value = "color")
    private String color;
}