package ca.mydemo.tcoffice.domain.profile;

import org.apache.commons.lang3.StringUtils;

public enum UserType {
    NONE("000"),
    SYSADMIIN("001"),
    BDRSUPERVISOR("010"),
    BDRAGENT("011"),
    CSFSUPERVISOR("020"),
    CSGAGENT("021"),
    EXT("200");

    String code;

    private UserType(String role) {
        code = role;
    }

    public static UserType getUserTypeByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return NONE;
        }

        for (UserType userType : UserType.values()) {
            if (userType.getCode().equals(code)) {
                return userType;
            }
        }
        return NONE;
    }

    public String getCode() {
        return code;
    }

}
