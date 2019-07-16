package com.yuanqn.admin.common.enums;

/**
 * @author:yuanqinnan
 * @date: 2019/7/15 11:19
 */
public enum UserStatusEnum {

    LOCK("LOCK", "锁定"),
    EFFECTIVITY("EFFECTIVITY", "有效"),
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
        UserStatusEnum e = getByCode(code);
        if (e != null) {
            return e.getName();
        }
        return "";
    }
}