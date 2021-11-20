package com.formbuilder.easyservey.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsPayload {

    private String questionType;
    private String questionName;
    private List<String> options;

}
