package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.dto.ProjectDto;
import com.jagmeet.portfoliobuilder.entities.User;
import com.jagmeet.portfoliobuilder.exceptions.AccessDeniedException;
import com.jagmeet.portfoliobuilder.exceptions.NotFoundException;
import com.jagmeet.portfoliobuilder.entities.Project;
import com.jagmeet.portfoliobuilder.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsByUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return projectRepository.findByCreatedByUserName(username);
    }

    public Project getProjectById(String projectId){
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project:" + projectId + " not found."));
    }

    public Project updateProjectById(Project updatedProject, String projectId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project dbProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project: " + projectId + " not found."));
        if(dbProject.getCreatedBy() == null)
            throw new com.jagmeet.portfoliobuilder.exceptions.AccessDeniedException("Not Authorized to update project");
        if(!dbProject.getCreatedBy().getUserName().equals(username))
            throw new com.jagmeet.portfoliobuilder.exceptions.AccessDeniedException("Not Authorized to update project");
        dbProject.setTags(updatedProject.getTags());
        dbProject.setProjectLink(updatedProject.getProjectLink());
        dbProject.setDescription(updatedProject.getDescription());
        dbProject.setGithubLink(updatedProject.getGithubLink());
        dbProject.setTitle(updatedProject.getTitle());
        dbProject.setYoutubeLink(updatedProject.getYoutubeLink());
        return projectRepository.save(dbProject);
    }

    public void createProject(ProjectDto projectDto){
        String username =  (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User is: {}",username);
        User user = userService.findUserByUserName(username);
        Project project = Project.builder()
                .title(projectDto.getTitle())
                .description(projectDto.getDescription())
                .projectLink(projectDto.getProjectLink())
                .githubLink(projectDto.getGithubLink())
                .tags(projectDto.getTags())
                .youtubeLink(projectDto.getYoutubeLink())
                .createdBy(user)
                .build();

        projectRepository.save(project);
    }

    public void deleteProject(String projectId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project project = projectRepository.findById(projectId)
                        .orElseThrow(() -> new NotFoundException("Cannot delete project"));
        if(project.getCreatedBy() == null)
            throw new AccessDeniedException("Not authorized to delete project");
        if(project.getCreatedBy().getUserName().equals(username))
            throw new AccessDeniedException("Not authorized to delete project");
        projectRepository.delete(project);
    }

}
