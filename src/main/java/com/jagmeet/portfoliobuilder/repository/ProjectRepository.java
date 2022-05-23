package com.jagmeet.portfoliobuilder.repository;

import com.jagmeet.portfoliobuilder.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project,String> {

    List<Project> findByCreatedByUserName(String username);

}
