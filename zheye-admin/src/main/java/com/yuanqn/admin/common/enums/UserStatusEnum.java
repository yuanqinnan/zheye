package com.yuanqn.admin.common.enums;

/**
 * @author:yuanqinnan
 * @date: 2019/7/15 11:19
 */
public enum UserStatusEnum {

    WAIT_SUBMIT("WAIT_SUBMIT", "待提交"),
    ;

    private String code;

    private String name;

    UserStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static UserStatusEnum getByCode(String code) {
        for (UserStatusEnum e : UserStatusEnum.values()) {
            if (code == e.getCode() || code != null && code.equals(e.getCode())) {
                return e;
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        UserStatusEnum brokerStatusEnum = getByCode(code);
        if (brokerStatusEnum != null) {
            return brokerStatusEnum.getName();
        }
        return "";
    }
}