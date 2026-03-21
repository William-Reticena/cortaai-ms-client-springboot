package br.com.cortaai.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    CLIENT(1, "CLIENT"),
    OWNER(2, "OWNER"),
    BARBER(3, "BARBER");

    private final Integer code;
    private final String description;

    public static UserRoleEnum valueOf(Integer code) {
        for (UserRoleEnum value : UserRoleEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid UserRoleEnum code: " + code);
    }
}
