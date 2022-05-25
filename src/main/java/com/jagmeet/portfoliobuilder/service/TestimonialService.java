package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.dto.TestimonialDto;
import com.jagmeet.portfoliobuilder.entities.Testimonial;
import com.jagmeet.portfoliobuilder.entities.User;
import com.jagmeet.portfoliobuilder.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final UserService userService;
    @Autowired
    public TestimonialService(TestimonialRepository testimonialRepository, UserService userService) {
        this.testimonialRepository = testimonialRepository;
        this.userService = userService;
    }

    public List<Testimonial> getAllTestimonials(){
        return testimonialRepository.findAll();
    }

    public Testimonial createTestimonial(TestimonialDto testimonialDto){
        User user = userService.loadUserFromSecurityContext();
        User writtenFor = userService.findUserById(testimonialDto.getWrittenFor());
        Testimonial testimonial = Testimonial.builder()
                .authorCompany(testimonialDto.getAuthorCompany())
                .description(testimonialDto.getDescription())
                .authorPosition(testimonialDto.getAuthorPosition())
                .description(testimonialDto.getDescription())
                .authorMail(testimonialDto.getAuthorMail())
                .author(user)
                .writtenFor(writtenFor)
                .build();
        return testimonialRepository.save(testimonial);
    }

    public void deleteTestimonial(String testimonialId){
        testimonialRepository.deleteById(testimonialId);
    }

}
