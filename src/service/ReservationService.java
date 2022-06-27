package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static HashMap<String, IRoom> rooms = new HashMap<>();
    private static HashMap<Customer, ArrayList<Reservation>> reservations = new HashMap<>();

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.get(customer).add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        ArrayList<IRoom> recommendRooms = new ArrayList<>();

        //Avoid conflicting reservations
        //linear search? iterate all reservations to check booked date?
        //check reserved rooms in this duration
        ArrayList<IRoom> reservedRooms = new ArrayList<>();
        for (ArrayList<Reservation> reservationList : reservations.values()) {
            for (Reservation reservation: reservationList) {
                if (checkInDate.compareTo(reservation.getCheckOutDate()) < 0 &&
                        checkOutDate.compareTo(reservation.getCheckInDate()) > 0) {
                    reservedRooms.add(reservation.getRoom());
                }
            }
        }

        //Search for recommended rooms.
        for (IRoom room : rooms.values()) {
            if (!reservedRooms.contains(room)) {
                recommendRooms.add(room);
            }
        }
        // If there are no available rooms for the customer's date range,
        // a search will be performed that displays recommended rooms on alternative dates.
        //add seven days to the original checkin and checkout dates to see if the hotel has any availabilities

        if (recommendRooms.size() == 0) {
            Calendar calendar = Calendar.getInstance();
            //update alternative dates
            calendar.setTime(checkInDate);
            calendar.add(Calendar.DATE, 7);
            checkInDate = calendar.getTime();

            calendar.setTime(checkOutDate);
            calendar.add(Calendar.DATE,7);
            checkOutDate = calendar.getTime();

            reservedRooms.clear();

            for (ArrayList<Reservation> reservationList : reservations.values()) {
                for (Reservation reservation: reservationList) {
                    if (checkInDate.compareTo(reservation.getCheckOutDate()) < 0 &&
                            checkOutDate.compareTo(reservation.getCheckInDate()) > 0) {
                        reservedRooms.add(reservation.getRoom());
                    }
                }
            }

            //Search for recommended rooms.
            for (IRoom room : rooms.values()) {
                if (!reservedRooms.contains(room)) {
                    recommendRooms.add(room);
                }
            }
        }
        return recommendRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer);
    }

    public void printAllReservation() {
        for (ArrayList<Reservation> reservationList : reservations.values()) {
            for (Reservation reservation: reservationList) {
                System.out.println(reservation.toString());
            }
        }
    }
}
