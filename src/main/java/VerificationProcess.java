/**
 * @project Concurrent Flight Reservation System
 * @file    VerificationProcess.java
 * @brief   VerificationProcess class responsible for verifying confirmed reservations.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The VerificationProcess class processes reservations that have been confirmed
 * and verifies them before moving them to the VerifiedReservations list.
 * The process continues running until the global flag is false or the thread is interrupted.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
public class VerificationProcess implements Runnable{

    private ConfirmedReservations confirmedReservations; ///< List of confirmed reservations to be processed.
    private VerifiedReservations verifiedReservations; ///< List where verified reservations are stored.
    private Constants constants; ///< Constants object holding system configurations.

    /**
     * @brief Constructor for the VerificationProcess class.
     * @param constants The configuration constants.
     * @param confirmedReservations The list of confirmed reservations.
     * @param verifiedReservations The list to store verified reservations.
     */
    public VerificationProcess(Constants constants, ConfirmedReservations confirmedReservations, VerifiedReservations verifiedReservations){
        this.confirmedReservations = confirmedReservations;
        this.verifiedReservations = verifiedReservations;
        this.constants = constants;
    }

    /**
     * @brief Run method for the verification process.
     *
     * The method processes the confirmed reservations and moves them to the verified list
     * after verification. The process continues until the global flag is false or the thread is interrupted.
     */
    public void run(){
        while(constants.getGlobalFlag() && !Thread.currentThread().isInterrupted()){
            Seat seat = confirmedReservations.removeChecked();
            if(seat == null){
                break; // Exit if there are no more seats to process.
            }
            verifiedReservations.putVerified(seat); // Move the seat to the verified list.
            try {
                Thread.sleep(constants.getVerificationProcessTime()); // Simulate the verification delay.
            } catch (InterruptedException e){
                // Handle interruption
            }
        }
        System.out.print("\nVerification Process runnable thread ends: "+(verifiedReservations.size())); // Print the size of verified reservations
    }
}