package com.jagmeet.portfoliobuilder.repository;

import com.jagmeet.portfoliobuilder.model.Testimonial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends MongoRepository<Testimonial,String> {

}
