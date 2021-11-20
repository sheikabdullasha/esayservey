package com.formbuilder.easyservey.controller;

import com.formbuilder.easyservey.payload.QuestionsPayload;
import com.formbuilder.easyservey.service.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/form")
public class QuestionsController {


    private final QuestionServiceImpl questionService;


    @PostMapping("/saveQuestions")
    public ResponseEntity<String> saveAll(@RequestBody List<QuestionsPayload> questionsPayloads){
            return questionService.saveAll(questionsPayloads);
    }

    @GetMapping("/getQuestionsById/{fId}")
    public ResponseEntity<?> getAll(@PathVariable int fId){
        return questionService.getAllById(fId);
    }








}
