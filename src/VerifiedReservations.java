import java.util.ArrayList;
import java.util.List;

/**
 * @project Concurrent Flight Reservation System
 * @file    VerifiedReservations.java
 * @brief   VerifiedReservations class to manage verified reservations.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The VerifiedReservations class is responsible for storing reservations
 * that have been verified. It allows adding verified reservations,
 * retrieving the list of verified reservations, and getting the size of the list.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
public class VerifiedReservations {
    private List<Seat> verifiedReservations = new ArrayList<>();

    /**
     * @brief Constructor for the VerifiedReservations class.
     */
    public VerifiedReservations(){
    }

    /**
     * @brief Adds a verified reservation to the list.
     * @param seat The seat to be added to the list of verified reservations.
     */
    public synchronized void putVerified(Seat seat){
        verifiedReservations.add(seat);
        System.out.print("\n"+seat.getId()+" Verified");
        notifyAll();
    }

    /**
     * @brief Gets the number of verified reservations.
     * @return The number of verified reservations in the list.
     */
    public int size(){
        return verifiedReservations.size();
    }

    /**
     * @brief Retrieves the list of verified reservations.
     * @return The list of verified reservations.
     */
    public List<Seat> getVerifiedReservations(){
        return verifiedReservations;
    }
}
