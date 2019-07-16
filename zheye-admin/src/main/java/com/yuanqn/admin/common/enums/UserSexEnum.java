package com.yuanqn.admin.common.enums;

/**
 * @author:yuanqinnan
 * @date: 2019/7/16 9:11
 */
public enum UserSexEnum {
    MAN("MAN", "男"),
    WOMAN("WOMAN", "女"),
    SECRECY("SECRECY", "保密"),
    ;

    private String code;

    private String name;

    UserSexEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static UserSexEnum getByCode(String code) {
        for (UserSexEnum e : UserSexEnum.values()) {
            if (code == e.getCode() || code != null && code.equals(e.getCode())) {
                return e;
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        UserSexEnum e = getByCode(code);
        if (e != null) {
            return e.getName();
        }
        return "";
    }
}