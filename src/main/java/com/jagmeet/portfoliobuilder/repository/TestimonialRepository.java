package com.jagmeet.portfoliobuilder.repository;

import com.jagmeet.portfoliobuilder.entities.Testimonial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends MongoRepository<Testimonial,String> {

}
