package com.jagmeet.portfoliobuilder.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testimonials")
@Data
public class Testimonial {

    @Id
    String id;

    String description;
    String author;
    String authorMail;
    String authorPosition;
    String authorCompany;
    String profileId;

}
