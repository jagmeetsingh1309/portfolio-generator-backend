package com.jagmeet.portfoliobuilder.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
@Data
public class Project {

    @Id
    String id;
    String title;
    String description;
    String projectLink;
    String githubLink;
    String youtubeLink;
    List<String> tags;
}
