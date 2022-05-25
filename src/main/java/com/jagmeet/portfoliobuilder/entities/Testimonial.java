package com.jagmeet.portfoliobuilder.entities;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "testimonials")
public class Testimonial {

    @Id
    String id;

    String description;
    @DBRef
    User author;
    String authorMail;
    String authorPosition;
    String authorCompany;
    @DBRef
    User writtenFor;

}
