package com.formbuilder.easyservey.repo;

import com.formbuilder.easyservey.entity.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuestionsRepository extends MongoRepository<Form,Integer> {




}
