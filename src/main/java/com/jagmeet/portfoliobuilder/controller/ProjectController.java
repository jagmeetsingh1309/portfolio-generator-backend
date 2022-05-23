package com.jagmeet.portfoliobuilder.controller;

import com.jagmeet.portfoliobuilder.dto.ProjectDto;
import com.jagmeet.portfoliobuilder.entities.Project;
import com.jagmeet.portfoliobuilder.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProjects(){
        log.info("### Getting all projects");
        return projectService.getAllProjects();
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProjectsByUser(){
        return projectService.getAllProjectsByUser();
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public Project getProjectById(@PathVariable String projectId){
        log.info("### Getting project with id: {}", projectId);
        return projectService.getProjectById(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectDto projectDto){
        log.info("### Creating project with values: {}",projectDto);
        projectService.createProject(projectDto);
    }

    @PutMapping("/{projectId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateProjectById(@RequestBody Project updatedProject,
                                  @PathVariable String projectId){
        projectService.updateProjectById(updatedProject,projectId);
    }


    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable String projectId){
        log.info("### Deleting project with projectId: {}",projectId);
        projectService.deleteProject(projectId);
    }

}
