package com.formbuilder.easyservey.repo;

import com.formbuilder.easyservey.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends MongoRepository<User,Integer> {
    @Query(value = "{mailId : ?0}")
    List<User>  findByEmail(String mailId);

    @Query(value = "{mailId : ?0}")
    Optional<User>  findByEmail1(String mailId);

}
