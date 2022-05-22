package com.jagmeet.portfoliobuilder.repository;

import com.jagmeet.portfoliobuilder.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByUserName(String username);

}
