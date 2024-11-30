import com.sun.tools.jconsole.JConsoleContext;

/**
 * @project Concurrent Flight Reservation System
 * @file    ReservationProcess.java
 * @brief   ReservationProcess class for managing flight reservations.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The ReservationProcess class is responsible for handling the reservation
 * of seats in the flight reservation system. It interacts with the seat map
 * and manages the transfer of reserved seats to the pending payment list.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
public class ReservationProcess implements Runnable {
    private SeatMap seatMap;  ///< The seat map of the flight, used to manage seat reservations.
    private PendingPaymentReservations pendingPaymentReservations; ///< The list of reservations pending payment.
    private Constants constants; ///< The constants that control the reservation process behavior.

    /**
     * Constructor for the ReservationProcess class.
     * Initializes the seat map, pending payment reservations, and constants for the process.
     *
     * @param constants The constants that configure the reservation process.
     * @param seatMap The seat map used to manage seat reservations.
     * @param pendingPaymentReservations The list of reservations pending payment.
     */
    public ReservationProcess(Constants constants, SeatMap seatMap, PendingPaymentReservations pendingPaymentReservations) {
        this.seatMap = seatMap;
        this.pendingPaymentReservations = pendingPaymentReservations;
        this.constants = constants;
    }

    /**
     * The run method of the ReservationProcess class, which is executed when the thread starts.
     * This method reserves seats and adds them to the pending payment list for further processing.
     * The reservation process is repeated a specified number of times based on the seat map size.
     */
    public void run() {
        int loopBounds = constants.getColumns() * constants.getRows() / constants.getNumReservationProcesses();
        for (int i = 0; i < loopBounds; i++) {
            // Reserve a seat and add it to the pending payment list
            Seat seat = seatMap.reserve();
            pendingPaymentReservations.putPendingPayment(seat);
            try {
                // Simulate the time taken for the reservation process
                Thread.sleep(constants.getReservationProcessTime());
            } catch (InterruptedException e) {
                // Handle thread interruption
            }
        }
        System.out.print("\nReserve Process runnable thread ended");
    }
}