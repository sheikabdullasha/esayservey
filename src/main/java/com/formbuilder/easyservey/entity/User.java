package com.formbuilder.easyservey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.formbuilder.easyservey.DataValidation.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "user")
public class User {

    public static final String SEQUENCE_NAME="user_sequence";
    @Id
    private int uId;

    @NotBlank(message = "Name is mandatory")
    private String firstName;


    private String lastName;

    @Email
    @UniqueEmail
    @NotBlank(message = "Email is mandatory")
    private String mailId;

    @NotBlank(message = "password is mandatory")
    private String password;

    private String type;

    private List<Integer> createdForms; //Admin
}
