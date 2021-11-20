package com.formbuilder.easyservey.repo;

import com.formbuilder.easyservey.entity.UserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserAnswerRepository extends MongoRepository<UserResponse,Integer> {

    @Query(value = "{fId : ?0}")
    List<UserResponse> GetAllResponsesById(int fId);
}
