/**
 * @project Concurrent Flight Reservation System
 * @file    PaymentProcess.java
 * @brief   PaymentProcess class for handling the flight reservation payment process
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The PaymentProcess class is responsible for processing reservations that
 * are pending payment. It determines whether a reservation is confirmed or canceled
 * based on a probability, and updates the respective reservation lists.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
import java.util.Random;

public class PaymentProcess implements Runnable {

    private PendingPaymentReservations pendingPaymentReservations; // List of pending payment reservations
    private ConfirmedReservations confirmedReservations; // List of confirmed reservations
    private CancelledReservations cancelledReservations; // List of canceled reservations
    private Constants constants; // Constants related to system behavior

    /**
     * Constructor for the PaymentProcess class.
     *
     * @param constants System constants that affect the payment process.
     * @param pendingPaymentReservations List of reservations pending payment.
     * @param confirmedReservations List of confirmed reservations.
     * @param cancelledReservations List of canceled reservations.
     */
    public PaymentProcess(Constants constants, PendingPaymentReservations pendingPaymentReservations, ConfirmedReservations confirmedReservations, CancelledReservations cancelledReservations) {
        this.pendingPaymentReservations = pendingPaymentReservations;
        this.confirmedReservations = confirmedReservations;
        this.cancelledReservations = cancelledReservations;
        this.constants = constants;
    }

    /**
     * The run method that is executed when the PaymentProcess thread starts.
     * It processes pending payments, assigning reservations as either confirmed or canceled
     * based on a probability.
     */
    public void run() {
        // The number of iterations for the payment process based on the number of seats
        int loopBounds = constants.getColumns() * constants.getRows() / constants.getNumPaymentProcess();
        for (int i = 0; i < loopBounds; i++) {
            try {
                // Simulate payment processing time
                Thread.sleep(constants.getPaymentProcessTime());
            } catch (InterruptedException e) {
                // Handle interruption
            }
            // Remove a seat from the pending payment list
            Seat seat = pendingPaymentReservations.removePendingPayment();
            Random random = new Random();
            // Generate a random number to determine if the seat is confirmed or canceled
            int probability = random.nextInt(101);
            if (probability < constants.getConfirmedSeatProbability()) {
                // Seat is confirmed
                confirmedReservations.putConfirmed(seat);
            } else {
                // Seat is canceled
                cancelledReservations.putCanceled(seat);
            }
        }
        // Output message indicating the thread has ended
        System.out.print("\nPayment Process runnable thread ended");
    }
}
