package com.yuanqn.admin.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 19:29
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {
    private static final long serialVersionUID = 1713846331610752796L;

    private Boolean closeable;

    private Boolean isShow;
}