package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.HashMap;
import java.util.List;

public class HotelManagementRepository {
    HashMap<String , Hotel> hotelMap = new HashMap<>();
    HashMap<Integer, User> userMap = new HashMap<>();
    HashMap<String, Booking> bookingMap = new HashMap<>();

    HashMap<Integer, Integer> booking_byUser = new HashMap<>();

    public String addHotel(Hotel hotel) {
        if(hotel==null || hotel.getHotelName()==null){
            return "FAILURE";
        }

        if(hotelMap.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }

        hotelMap.put(hotel.getHotelName(), hotel);

        return "SUCCESS";
    }

    public Integer addUser(User user) {
        if(user!=null){
            userMap.put(user.getaadharCardNo(), user);
        }
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        int min = 0;
        String ans = "";
        for (String hotelName : hotelMap.keySet()) {
            Hotel hotel = hotelMap.get(hotelName);
            if(hotel.getFacilities().size() > min){
                min = hotel.getFacilities().size();
                ans = hotelName;
            }
        }
        return ans;
    }

    public int bookARoom(Booking booking) {

        int user_id = booking.getBookingAadharCard();

        if(!userMap.containsKey(user_id)){
            String hotelName = booking.getHotelName();
            Hotel hotel = hotelMap.get(hotelName);
            int pricePer = hotel.getPricePerNight();
            int noOfRooms = booking.getNoOfRooms();

            if(noOfRooms > hotel.getAvailableRooms()){
                return -1;
            }
            return pricePer*noOfRooms;
        }

        bookingMap.put(booking.getBookingId(), booking);

        if(booking.getHotelName()==null){
            return 0;
        }
        String hotelName = booking.getHotelName();
        int no_of_rooms_to_book = booking.getNoOfRooms();
        if(!hotelMap.containsKey(hotelName)){
            return -1;
        }
        int no_of_rooms_available = hotelMap.get(hotelName).getAvailableRooms();

        if(no_of_rooms_available < no_of_rooms_to_book){
            return -1;
        }

        int price_per_room = hotelMap.get(hotelName).getPricePerNight();
        int amount = no_of_rooms_to_book*price_per_room;

        User user = userMap.get(booking.getBookingAadharCard());
        booking_byUser.put(user.getaadharCardNo(), booking_byUser.getOrDefault(user.getaadharCardNo(), 0)+1);

        return amount;
    }

    public int getBookings(Integer aadharCard) {
        int check = aadharCard;
        if(!booking_byUser.containsKey(check)){
            return 2;
        }
        return booking_byUser.get(check);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelMap.get(hotelName);
        hotel.setFacilities(newFacilities);
        return hotel;
    }
}
