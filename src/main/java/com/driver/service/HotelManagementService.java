package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;

import java.util.List;
import java.util.UUID;

public class HotelManagementService {
    HotelManagementRepository hmr = new HotelManagementRepository();
    public String addHotel(Hotel hotel) {
        return hmr.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return hmr.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return hmr.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        String bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);

        return hmr.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        return hmr.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hmr.updateFacilities(newFacilities, hotelName);
    }
}
