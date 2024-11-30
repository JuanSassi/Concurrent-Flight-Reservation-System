/**
 * @project Concurrent Flight Reservation System
 * @file    Main.java
 * @brief   Main class for the Concurrent Flight Reservation System project.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;
// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * @brief Main class to execute the Concurrent Flight Reservation System.
 */
public class Main {
    /**
     * @brief Main entry point of the program.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        /**
         * @brief Initializes constants with system configuration values.
         */
        Constants constants = new Constants();

        /**
         * @brief SeatMap: Class where the seat map is initialized.
         */
        SeatMap seatMap = new SeatMap(constants);

        // Initialize the different reservation lists.
        PendingPaymentReservations pendingPaymentReservations = new PendingPaymentReservations();
        ConfirmedReservations confirmedReservations = new ConfirmedReservations();
        CancelledReservations cancelledReservations = new CancelledReservations();
        VerifiedReservations verifiedReservations = new VerifiedReservations();

        // Start time counting.
        long startTime = System.currentTimeMillis();

        // Initializes a list to gather the threads.
        List<Thread> allThreads = new ArrayList<>();

        // Initialize Reservation Process threads
        for (int i = 0; i < constants.getNumReservationProcesses(); i++) {
            ReservationProcess reservationProcess = new ReservationProcess(constants, seatMap, pendingPaymentReservations);
            Thread thread = new Thread(reservationProcess, "reservationProcess" + (i + 1));
            allThreads.add(thread);
            thread.start();
        }

        // Initialize Payment Process threads
        for (int i = 0; i < constants.getNumPaymentProcess(); i++) {
            PaymentProcess paymentProcess = new PaymentProcess(constants, pendingPaymentReservations, confirmedReservations, cancelledReservations);
            Thread thread = new Thread(paymentProcess, "reservationProcess" + (i + 1));
            allThreads.add(thread);
            thread.start();
        }

        // Initialize Cancellation/Validation Process threads
        for (int i = 0; i < constants.getNumCancellationValidationProcess(); i++) {
            CancellationValidationProcess cancellationValidationProcess = new CancellationValidationProcess(constants, confirmedReservations, cancelledReservations);
            Thread thread = new Thread(cancellationValidationProcess, "reservationProcess" + (i + 1));
            allThreads.add(thread);
            thread.start();
        }

        // Initialize Verification Process threads
        for (int i = 0; i < constants.getNumVerificationProcess(); i++) {
            VerificationProcess verificationProcess = new VerificationProcess(constants, confirmedReservations, verifiedReservations);
            Thread thread = new Thread(verificationProcess, "reservationProcess" + (i + 1));
            allThreads.add(thread);
            thread.start();
        }

        // Initialize LOG thread
        LOG logThread = new LOG(startTime, constants, seatMap, verifiedReservations, cancelledReservations);
        logThread.start();

        // Boolean variable to track whether threads are still alive.
        boolean alive;

        // Do/While loop to check if threads are alive or if all seats are processed.
        do{
            alive = false;
            for(Thread thread : allThreads){
                alive |= thread.isAlive();
            }
            if(!(verifiedReservations.size()+cancelledReservations.size() < constants.getRows()*constants.getColumns())){
                constants.flagDown();
            }

        }while(alive && constants.getGlobalFlag());

        // Interrupt remaining threads to ensure proper termination.
        for(Thread thread : allThreads){
            thread.interrupt();
        }
        logThread.interrupt();

        // Sleep to ensure final log messages are printed.
        try {
            Thread.sleep(constants.getLogTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final statistics.
        System.out.print("\n" + "PAYMENT PENDING: " + pendingPaymentReservations.size() + "\n");

        System.out.print("\n" + "CONFIRMED: " + confirmedReservations.size() + "\n");

        System.out.print("\n" + "VERIFIED: " + verifiedReservations.size());
        System.out.println(" - percentage: " + String.format("%.1f", (double)verifiedReservations.size() * 100 / (constants.getRows() * constants.getColumns())) + "%\n");

        System.out.print("CANCELED: " + cancelledReservations.size());
        System.out.println(" - percentage: " + String.format("%.1f", (double)cancelledReservations.size() * 100 / (constants.getRows() * constants.getColumns())) + "%\n");

        System.out.print("RESERVATIONS PROCESSED: " + (verifiedReservations.size() + cancelledReservations.size()));
        System.out.println(" - percentage: " + String.format("%.1f", (double)(verifiedReservations.size() + cancelledReservations.size()) * 100 / (constants.getRows() * constants.getColumns())) + "%\n");

        System.out.print("\nSystem finished.");

    }
}