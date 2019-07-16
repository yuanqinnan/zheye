package com.yuanqn.base.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/16 14:07
 */
@Data
@ToString
public class QueryDTO implements Serializable {
    private static final long serialVersionUID = -3367998899127104394L;

    // 当前页面数据量
    private int pageSize = 10;
    // 当前页码
    private int pageNum = 1;
    // 排序字段
    private String field;
    // 排序规则，asc升序，desc降序
    private String order;
}