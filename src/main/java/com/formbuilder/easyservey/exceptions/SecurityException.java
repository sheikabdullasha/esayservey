package com.formbuilder.easyservey.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SecurityException {
    private static final long serialVersionUID = 8207762801852900651L;

    private String message;
}
