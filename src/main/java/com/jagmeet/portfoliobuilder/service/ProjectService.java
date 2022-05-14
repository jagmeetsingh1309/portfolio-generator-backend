package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.model.Project;
import com.jagmeet.portfoliobuilder.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(String projectId){
        return projectRepository.findById(projectId);
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public void deleteProject(String projectId){
        projectRepository.deleteById(projectId);
    }

}
