package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.exceptions.NotFoundException;
import com.jagmeet.portfoliobuilder.model.Project;
import com.jagmeet.portfoliobuilder.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects(){

        return projectRepository.findAll();

    }

    public Project getProjectById(String projectId){
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project:" + projectId + " not found."));
    }

    public Project updateProjectById(Project updatedProject, String projectId){
        Project dbProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project: " + projectId + " not found."));
        dbProject.setTags(updatedProject.getTags());
        dbProject.setProjectLink(updatedProject.getProjectLink());
        dbProject.setDescription(updatedProject.getDescription());
        dbProject.setGithubLink(updatedProject.getGithubLink());
        dbProject.setTitle(updatedProject.getTitle());
        dbProject.setYoutubeLink(updatedProject.getYoutubeLink());
        return projectRepository.save(dbProject);
    }

    public void createProject(Project project){
        projectRepository.save(project);
    }

    public void deleteProject(String projectId){
        projectRepository.deleteById(projectId);
    }

}
