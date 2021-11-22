package com.formbuilder.easyservey.controller;

import com.formbuilder.easyservey.entity.UserResponse;
import com.formbuilder.easyservey.service.UserAnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

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


    @GetMapping("/getExcelFile/{fId}")
    public void downloadExcelFile(HttpServletResponse response,@PathVariable int fId) throws IOException {
        ByteArrayInputStream byteArrayInputStream = userAnswerService.ExportExcelById(fId);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Responses.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }





}
