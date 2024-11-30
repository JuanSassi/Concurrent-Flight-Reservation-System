/**
 * @project Concurrent Flight Reservation System
 * @file    CancelledReservations.java
 * @brief   Manages the cancelled seat reservations.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This class is part of the Concurrent Flight Reservation System project.
 * It handles the reservations that are canceled during the flight reservation process.
 * The class provides synchronized methods to add canceled seats to the list and retrieve the list of canceled reservations.
 * It ensures thread safety by using the `synchronized` keyword.
 *
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @class CancelledReservations
 * @brief Class that manages canceled seat reservations.
 */
public class CancelledReservations {

    /**
     * @brief List that holds the canceled reservations.
     */
    private List<Seat> cancelledReservations = new ArrayList<>();

    /**
     * @brief Constructor for the CancelledReservations class.
     */
    public CancelledReservations() {}

    /**
     * @brief Adds a canceled seat to the list of canceled reservations.
     * @param seat The seat that is being canceled.
     *
     * @details This method marks the seat as unavailable and adds it to the list of canceled reservations.
     * It is synchronized to ensure thread safety when accessed by multiple threads.
     */
    public synchronized void putCanceled(Seat seat) {
        seat.unavailableSeat();
        cancelledReservations.add(seat);
        System.out.print("\n" + seat.getId() + " Canceled");
        notifyAll();
    }

    /**
     * @brief Returns the number of canceled reservations.
     * @return The size of the canceled reservations list.
     */
    public int size() {
        return cancelledReservations.size();
    }

    /**
     * @brief Retrieves the list of canceled reservations.
     * @return The list of canceled seats.
     */
    public List<Seat> getCancelledReservations() {
        return cancelledReservations;
    }
}