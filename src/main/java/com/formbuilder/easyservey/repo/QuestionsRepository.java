package com.formbuilder.easyservey.repo;

import com.formbuilder.easyservey.entity.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuestionsRepository extends MongoRepository<Form,Integer> {

    @Query(value = "{fId : ?0}")
    List<Form> findAllFormByUserId(int uId);




}
