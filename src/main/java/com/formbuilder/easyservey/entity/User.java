package com.formbuilder.easyservey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "user")
public class User {

    public static final String SEQUENCE_NAME="user_sequence";
    @Id
    private int uId;


    private String firstName;


    private String lastName;


    private String mailId;


    private String password;

    private String type;

    private List<Integer> createdForms; //Admin
}
