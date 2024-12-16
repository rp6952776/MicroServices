package com.raj.rating.service.impl;

import com.raj.rating.entities.Rating;
import com.raj.rating.repository.RatingRapository;
import com.raj.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRapository ratingRapository;

    @Override
    public Rating createRating(Rating rating) {

        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);

        return ratingRapository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRapository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRapository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRapository.findByHotelId(hotelId);
    }
}
