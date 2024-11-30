/**
 * @project Concurrent Flight Reservation System
 * @file    PendingPaymentReservations.java
 * @brief   PendingPaymentReservations class for managing reservations pending payment.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The PendingPaymentReservations class handles the reservations that are pending payment.
 * It provides methods for adding reservations to the pending list and removing them
 * once payment processing is initiated.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PendingPaymentReservations {
    private List<Seat> pendingPaymentReservations = new ArrayList<>(); // List of seats pending payment

    /**
     * Constructor for the PendingPaymentReservations class.
     * Initializes the list of pending payment reservations.
     */
    public PendingPaymentReservations(){
    }

    /**
     * Removes a seat from the list of pending payment reservations.
     * This method waits if the list is empty and returns a randomly chosen seat from the list.
     *
     * @return The seat to be processed, or null if no seat is found.
     */
    public synchronized Seat removePendingPayment(){
        while(pendingPaymentReservations.isEmpty()){
            try{
                // Wait until there are pending reservations to process
                wait();
            }catch (InterruptedException e){
            }
        }
        try{
            Random random = new Random();
            int randomIndex = random.nextInt(pendingPaymentReservations.size());
            Seat seat = pendingPaymentReservations.get(randomIndex);
            pendingPaymentReservations.remove(seat);
            return seat;
        }catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Adds a seat to the list of pending payment reservations.
     * If the list was empty before adding the seat, it notifies other threads waiting
     * to process a reservation.
     *
     * @param seat The seat to be added to the pending list.
     */
    public synchronized void putPendingPayment(Seat seat){
        boolean notify = false;
        if(pendingPaymentReservations.isEmpty()){
            // If the list was empty, notify waiting threads
            notify = true;
        }
        pendingPaymentReservations.add(seat);
        System.out.print("\n"+seat.getId()+" Reserved");
        if(notify){
            // Notify other threads that a reservation has been added
            notifyAll();
        }
    }

    /**
     * Returns the number of reservations pending payment.
     *
     * @return The size of the pending payment list.
     */
    public int size(){
        return pendingPaymentReservations.size();
    }
}