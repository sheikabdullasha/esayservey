package com.formbuilder.easyservey.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Form {

    public static final String SEQUENCE_NAME="FORM_sequence";
    @Id
    private int fId;
    private List<QuestionDetails> questionDetails;

    /**********/
    private int cId; //creator



}
