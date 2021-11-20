package com.formbuilder.easyservey.controller;

import com.formbuilder.easyservey.entity.UserResponse;
import com.formbuilder.easyservey.service.UserAnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/users")
public class UserAnswerController {

    private final UserAnswerServiceImpl userAnswerService;

    @PostMapping("/saveUserAnswer")
    public ResponseEntity<?> save(@RequestBody UserResponse userResponse){
        return userAnswerService.save(userResponse);
    }

    @GetMapping("/getAllUserResponse/{fId}")
    public ResponseEntity<?> getAll(@PathVariable int fId){
        return userAnswerService.getAll(fId);
    }



}
