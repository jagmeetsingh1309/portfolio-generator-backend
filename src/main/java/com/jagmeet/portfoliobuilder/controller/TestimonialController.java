package com.jagmeet.portfoliobuilder.controller;

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

    @GetMapping("/")
    public ResponseEntity<List<Testimonial>> getAllTestimonials(){
        log.info("#### Getting all testimonials");
        List<Testimonial> testimonials = testimonialService.getAllTestimonials();
        return new ResponseEntity<>(testimonials, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Testimonial> createTestimonial(@RequestBody Testimonial testimonial ){
        log.info("### creating testimonial, value received: {}",testimonial);
        Testimonial dbTestimonial = testimonialService.createTestimonial(testimonial);
        return new ResponseEntity<>(dbTestimonial,HttpStatus.CREATED);
    }

    @DeleteMapping("/{testimonialId}")
    public ResponseEntity<HttpStatus> deleteTestimonial(@PathVariable String testimonialId){
        testimonialService.deleteTestimonial(testimonialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
