package com.formbuilder.easyservey.service;

import com.formbuilder.easyservey.entity.UserResponse;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

public interface IUserAnswerService {

    public ResponseEntity<?> save(UserResponse userResponse);

    public ResponseEntity<?> getAll(int fId);

    public ByteArrayInputStream ExportExcelById(int fId);


}
