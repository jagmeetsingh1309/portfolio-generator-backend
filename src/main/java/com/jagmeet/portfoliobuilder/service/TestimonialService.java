package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.entities.Testimonial;
import com.jagmeet.portfoliobuilder.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    public List<Testimonial> getAllTestimonials(){
        return testimonialRepository.findAll();
    }

    public Testimonial createTestimonial(Testimonial testimonial){

        return testimonialRepository.save(testimonial);
    }

    public void deleteTestimonial(String testimonialId){
        testimonialRepository.deleteById(testimonialId);
    }

}
