package com.user.service.services.impl;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();
        for(User user : users){
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGMICROSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

            List<Rating> ratingList = ratings.stream().map(rating -> {

                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();

                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
        }

        return users;
    }

    @Override
    public User getUserByUserId(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with Id" + userId));
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGMICROSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
