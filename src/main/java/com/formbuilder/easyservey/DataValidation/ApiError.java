package com.formbuilder.easyservey.DataValidation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiError {
    int status;
    String message;
    long timestamp;
    String path;
    Map<String,String> validationErrors;

    public ApiError(int i, String message, String servletPath) {
    }
}
