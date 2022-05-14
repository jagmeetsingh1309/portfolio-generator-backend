package com.jagmeet.portfoliobuilder.controller;

import com.jagmeet.portfoliobuilder.model.Project;
import com.jagmeet.portfoliobuilder.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/projects")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects(){
        log.info("### Getting all projects");
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId){
        log.info("### Getting project with id: {}", projectId);
        Optional<Project> projectOptional = projectService.getProjectById(projectId);
        return projectOptional
                .map(project -> new ResponseEntity<>(project, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        log.info("### Creating project with values: {}",project);
        Project responseProject = projectService.createProject(project);
        return new ResponseEntity<>(responseProject,HttpStatus.CREATED);
    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable String projectId){
        log.info("### Deleting project with projectId: {}",projectId);
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
