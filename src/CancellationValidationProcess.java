/**
 * @project Concurrent Flight Reservation System
 * @file    CancellationValidationProcess.java
 * @brief   Handles cancellation and validation processes for seat reservations.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This class is part of the Concurrent Flight Reservation System project.
 * It runs as a separate thread to either validate or cancel seat reservations
 * based on a probability threshold. The class interacts with `ConfirmedReservations`
 * and `CancelledReservations` to manage seat statuses.
 *
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */

import java.util.Random;

/**
 * @class CancellationValidationProcess
 * @brief Runnable class that handles seat cancellation and validation processes.
 */
public class CancellationValidationProcess implements Runnable {

    /**
     * @brief List of confirmed reservations.
     */
    private ConfirmedReservations confirmedReservations;

    /**
     * @brief List of cancelled reservations.
     */
    private CancelledReservations cancelledReservations;

    /**
     * @brief Configuration constants used in the cancellation/validation process.
     */
    private Constants constants;

    /**
     * @brief Constructor for the `CancellationValidationProcess` class.
     * @param constants Configuration constants for the process.
     * @param confirmedReservations List of confirmed reservations.
     * @param cancelledReservations List of cancelled reservations.
     */
    public CancellationValidationProcess(Constants constants, ConfirmedReservations confirmedReservations, CancelledReservations cancelledReservations) {
        this.confirmedReservations = confirmedReservations;
        this.cancelledReservations = cancelledReservations;
        this.constants = constants;
    }

    /**
     * @brief Executes the cancellation and validation process in a separate thread.
     *
     * @details The thread performs actions based on a random probability. It either:
     * - Checks a seat and updates its status, or
     * - Cancels a seat and moves it to the cancelled reservations list.
     * The thread sleeps between operations and stops when the global flag is down.
     */
    public void run() {
        while (constants.getGlobalFlag()) {
            Random random = new Random();
            int probability = random.nextInt(101);

            if (probability < constants.getCheckSeatProbability()) {
                // Seat is validated
                confirmedReservations.checkSeat();
            } else {
                // Seat is cancelled
                Seat seat = confirmedReservations.removeConfirmed();
                if (seat == null) {
                    break;
                }
                cancelledReservations.putCanceled(seat);
            }

            try {
                Thread.sleep(constants.getCancellationValidationProcessTime());
            } catch (InterruptedException e) {
                // Exception is ignored
            }
        }
        System.out.print("\nRunnable thread ended Cancellation Validation Process: " + cancelledReservations.size());
    }
}
