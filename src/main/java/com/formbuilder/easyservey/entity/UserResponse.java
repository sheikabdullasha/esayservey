package com.formbuilder.easyservey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "userresponse")
public class UserResponse {
    @Id
    private int uId;
    private int fId;
    private String questionName;
    private List<String> userAnswer;


}
