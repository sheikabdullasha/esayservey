package com.formbuilder.easyservey.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleConstant {
    Admin("ADMIN"),
    User("USER");
    String role;
}
