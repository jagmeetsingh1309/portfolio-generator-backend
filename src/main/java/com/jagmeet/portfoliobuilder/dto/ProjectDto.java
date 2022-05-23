package com.jagmeet.portfoliobuilder.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDto {

    String title;
    String description;
    String projectLink;
    String githubLink;
    String youtubeLink;
    List<String> tags;

}
