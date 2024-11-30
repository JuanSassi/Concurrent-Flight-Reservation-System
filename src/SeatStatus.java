/**
 * @project Concurrent Flight Reservation System
 * @file    SeatStatus.java
 * @brief   Enum representing the possible states of a seat in the flight reservation system.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The SeatStatus enum represents the different states a seat can have in the system:
 * - AVAILABLE: The seat is available for reservation.
 * - OCCUPIED: The seat has been reserved.
 * - UNAVAILABLE: The seat is unavailable for reservation.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
public enum SeatStatus {
    AVAILABLE,    ///< The seat is available for reservation.
    OCCUPIED,     ///< The seat has been reserved.
    UNAVAILABLE   ///< The seat is unavailable for reservation.
}