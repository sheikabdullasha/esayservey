package com.formbuilder.easyservey.service;

import com.formbuilder.easyservey.entity.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserAnswerService {

    public ResponseEntity<?> save(UserResponse userResponse);

    public ResponseEntity<?> getAll(int fId);
}
