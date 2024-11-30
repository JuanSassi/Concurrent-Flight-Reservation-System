/**
 * @project Concurrent Flight Reservation System
 * @file    Seat.java
 * @brief   Seat class for managing seat statuses and details in the reservation system.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The Seat class represents an individual seat in the reservation system,
 * managing its status (available, occupied, unavailable) and other details
 * like the seat ID and whether it has been checked.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
public class Seat {
    private SeatStatus status; ///< The current status of the seat (available, occupied, or unavailable).
    private int id; ///< The unique identifier for the seat.
    private boolean checked = false; ///< The checked status of the seat, indicating whether it has been processed.

    /**
     * Default constructor for the Seat class.
     * Initializes the seat with the available status and a default ID of 0.
     */
    public Seat() {
        this.status = SeatStatus.AVAILABLE;
        this.id = 0;
    }

    /**
     * Gets the current status of the seat.
     *
     * @return The status of the seat as an enum value of type SeatStatus.
     */
    public SeatStatus getStatus() {
        return status;
    }

    /**
     * Marks the seat as occupied.
     */
    public void occupiedSeat() {
        status = SeatStatus.OCCUPIED;
    }

    /**
     * Marks the seat as unavailable.
     */
    public void unavailableSeat() {
        status = SeatStatus.UNAVAILABLE;
    }

    /**
     * Sets the ID of the seat.
     *
     * @param id The new ID to be assigned to the seat.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the seat.
     *
     * @return The ID of the seat.
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the seat has been marked as checked.
     *
     * @return True if the seat is checked, otherwise false.
     */
    public boolean getChecked() {
        return checked;
    }

    /**
     * Marks the seat as checked.
     */
    public void checked() {
        checked = true;
    }
}