package com.jagmeet.portfoliobuilder.controller;

import com.jagmeet.portfoliobuilder.dto.TestimonialDto;
import com.jagmeet.portfoliobuilder.entities.Testimonial;
import com.jagmeet.portfoliobuilder.service.TestimonialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testimonials")
@Slf4j
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @GetMapping
    public ResponseEntity<List<Testimonial>> getAllTestimonials(){
        log.info("#### Getting all testimonials");
        List<Testimonial> testimonials = testimonialService.getAllTestimonials();
        return new ResponseEntity<>(testimonials, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Testimonial createTestimonial(@RequestBody TestimonialDto testimonialDto ){
        log.info("### creating testimonial, value received: {}",testimonialDto);
        return testimonialService.createTestimonial(testimonialDto);
    }

    @DeleteMapping("/{testimonialId}")
    public ResponseEntity<HttpStatus> deleteTestimonial(@PathVariable String testimonialId){
        testimonialService.deleteTestimonial(testimonialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
