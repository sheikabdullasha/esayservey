package com.formbuilder.easyservey.service;

import com.formbuilder.easyservey.entity.Form;
import com.formbuilder.easyservey.entity.User;
import com.formbuilder.easyservey.entity.UserResponse;
import com.formbuilder.easyservey.repo.IUserRepository;
import com.formbuilder.easyservey.repo.QuestionsRepository;
import com.formbuilder.easyservey.repo.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAnswerServiceImpl implements IUserAnswerService{
    private final UserAnswerRepository answerRepository;
    private final IUserRepository userRepository;
    private final QuestionsRepository questionsRepository;



    @Override
    public ResponseEntity<?> save(UserResponse userResponse) {
        try {
            log.error(userResponse.toString());
            answerRepository.save(userResponse);
            return new ResponseEntity<String>("Answer Submitted SuccessFully", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error in User Answer Save API");
        }
        return new ResponseEntity<String>("Error in User Answer Save API", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAll(int fId) {


        try {
            List<UserResponse> responses = answerRepository.GetAllResponsesById(fId);

            if (responses.size() == 0) {
                return new ResponseEntity<String>("No data", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<UserResponse>>(responses, HttpStatus.FOUND);
            }
        }catch (Exception e){
            log.error("Error in User Response Get ALl API "+e);
        }
        return new ResponseEntity<String>("User Response API Error", HttpStatus.BAD_REQUEST);
    }


}
