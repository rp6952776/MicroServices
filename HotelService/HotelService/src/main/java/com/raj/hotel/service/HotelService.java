package com.raj.hotel.service;

import com.raj.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAll();

    Hotel getHotelById(String hotelId);

}
