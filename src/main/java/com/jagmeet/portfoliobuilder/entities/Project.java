package com.jagmeet.portfoliobuilder.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Document(collection = "projects")
@Data
@Builder
public class Project {

    @Id
    String id;
    String title;
    String description;
    String projectLink;
    String githubLink;
    String youtubeLink;
    List<String> tags;
    User createdBy;


}
