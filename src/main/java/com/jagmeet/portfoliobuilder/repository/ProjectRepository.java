package com.jagmeet.portfoliobuilder.repository;

import com.jagmeet.portfoliobuilder.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project,String> {
}
