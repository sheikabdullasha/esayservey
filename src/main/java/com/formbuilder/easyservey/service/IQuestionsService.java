package com.formbuilder.easyservey.service;

import com.formbuilder.easyservey.payload.QuestionsPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IQuestionsService {


    public ResponseEntity<String> saveAll(List<QuestionsPayload> questionsPayloads);

    public ResponseEntity<?> getAllById(int id);

    ResponseEntity<?> getAllFormByUserId(int uId);
}
