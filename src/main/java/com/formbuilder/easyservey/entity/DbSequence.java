package com.formbuilder.easyservey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dbsequence")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DbSequence {

    private String id;
    private int seqNo;
}
