package com.formbuilder.easyservey.service;

import com.formbuilder.easyservey.Constant.RoleConstant;
import com.formbuilder.easyservey.entity.QuestionDetails;
import com.formbuilder.easyservey.entity.Form;
import com.formbuilder.easyservey.entity.User;
import com.formbuilder.easyservey.payload.QuestionsPayload;
import com.formbuilder.easyservey.repo.IUserRepository;
import com.formbuilder.easyservey.repo.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements IQuestionsService{
    private final QuestionsRepository questionsRepository;
    private final SequenceGeneratorservice generatorservice;
    private final IUserRepository userRepository;
    @Override
    public ResponseEntity<String> saveAll(@RequestBody List<QuestionsPayload> questionsPayloads){
        try {
            Form quests=new Form();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName =null;
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                 currentUserName = authentication.getName();

            }
            Optional<User> usr=userRepository.findByEmail1(currentUserName);
            if(usr.isPresent()){

                quests.setCId(usr.get().getUId());
            }

            List<QuestionDetails> questionDetails=new ArrayList<>();
            questionsPayloads.stream().forEach(questionsPayload -> {
                QuestionDetails QuestDet=QuestionDetails.builder()
                        .questionName(questionsPayload.getQuestionName())
                        .questionType(questionsPayload.getQuestionType())
                        .options(questionsPayload.getOptions()).build();

                questionDetails.add(QuestDet);
            });
            int id=generatorservice.getSequenceNumber(Form.SEQUENCE_NAME);
            quests.setFId(id);
            List<Integer> createdIds=new ArrayList<>();
            if(usr.isPresent()){
               if(usr.get().getCreatedForms()!=null){

                    createdIds.addAll(usr.get().getCreatedForms());
                    createdIds.add(id);
                    usr.get().setCreatedForms(createdIds);
                }else{

                    usr.get().setCreatedForms(createdIds);
                }

            }




            quests.setQuestionDetails(questionDetails);

            Form q=questionsRepository.save(quests);
            log.info(q.toString());
            return new ResponseEntity<String>("Form saved", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error in Question save API");
        }
        return new ResponseEntity<String>("Error In Form Save API", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<?> getAllById(int id){


        try {
            Optional<Form> form=questionsRepository.findById(id);
            if(form.isPresent()){
                return new ResponseEntity<Form>(form.get(),HttpStatus.FOUND);

            }else{
                return new ResponseEntity<String>("No data",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error in Questions GetAll Api ");
        }

        return new ResponseEntity<String>("error in Question GETALL API",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAllFormByUserId(int uId) {

        List<Form> forms = questionsRepository.findAllFormByUserId(uId);
        if (forms.size()!=0) {

            return new ResponseEntity<List<Form>>(forms, HttpStatus.FOUND);
        }

        return new ResponseEntity<String>("No data", HttpStatus.NOT_FOUND);

    }

}
