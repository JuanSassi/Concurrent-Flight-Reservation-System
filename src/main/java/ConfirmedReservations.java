/**
 * @project Concurrent Flight Reservation System
 * @file    ConfirmedReservations.java
 * @brief   Manages confirmed and checked seat reservations.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This class is part of the Concurrent Flight Reservation System project.
 * It manages the confirmed seat reservations and handles checking of those seats.
 * The class uses synchronized methods to ensure thread safety when interacting with seat reservations.
 *
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @class ConfirmedReservations
 * @brief Class that manages confirmed seat reservations.
 */
public class ConfirmedReservations {

    /**
     * @brief List of confirmed seat reservations.
     */
    private List<Seat> confirmedReservations = new ArrayList<>();

    /**
     * @brief List of checked seat reservations.
     */
    private List<Seat> checkedReservations = new ArrayList<>();

    /**
     * @brief Constructor for the ConfirmedReservations class.
     */
    public ConfirmedReservations() {}

    /**
     * @brief Checks a seat from the confirmed reservations list.
     * @details This method checks a seat randomly from the confirmed reservations list.
     * It will notify other threads if the checked reservations list was previously empty.
     */
    public synchronized void checkSeat() {

        while(confirmedReservations.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                notifyAll();
                break;
            }
        }

        try {
            // Take an unchecked seat randomly
            Seat seat = uncheckedSeat();

            // Notify if checkedReservations is empty
            boolean notify = false;
            if (checkedReservations.isEmpty()) {
                notify = true;
            }

            // Mark the seat as checked
            seat.checked();
            checkedReservations.add(seat);
            confirmedReservations.remove(seat);
            System.out.print("\n" + seat.getId() + " Checked");

            // Notify that checkedReservations is no longer empty
            if (notify) {
                notifyAll();
            }
        } catch (IllegalArgumentException e) {
            // Handle exceptions if any
        }
    }

    /**
     * @brief Selects an unchecked seat from the confirmed reservations list.
     * @return A randomly selected unchecked seat.
     *
     * @details This method randomly selects a seat from the confirmed reservations list
     * that has not been checked yet.
     */
    public Seat uncheckedSeat() {
        Random random = new Random();
        int randomIndex;
        Seat seat;
        randomIndex = random.nextInt(confirmedReservations.size());
        seat = confirmedReservations.get(randomIndex);

        while(seat.getChecked()) {
            randomIndex = random.nextInt(confirmedReservations.size());
            seat = confirmedReservations.get(randomIndex);
            if (confirmedReservations.isEmpty()) {
                break;
            }
        }
        return seat;
    }

    /**
     * @brief Adds a seat to the confirmed reservations list.
     * @param seat The seat that is being confirmed.
     *
     * @details This method adds a seat to the confirmed reservations list and notifies
     * other threads if the list was previously empty.
     */
    public synchronized void putConfirmed(Seat seat) {
        boolean notify = false;
        if (confirmedReservations.isEmpty()) {
            notify = true;
        }
        confirmedReservations.add(seat);
        System.out.print("\n" + seat.getId() + " Paid");

        // Notify that the confirmed reservations list is not empty
        if (notify) {
            notifyAll();
        }
    }

    /**
     * @brief Removes a confirmed seat from the reservations list.
     * @return The randomly selected seat that was removed.
     *
     * @details This method removes a seat from the confirmed reservations list and returns it.
     * It waits if the list is empty until a seat becomes available.
     */
    public synchronized Seat removeConfirmed() {

        while (confirmedReservations.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                notifyAll();
                break;
            }
        }

        try {
            Random random = new Random();
            int randomIndex = random.nextInt(confirmedReservations.size());
            Seat seat = confirmedReservations.get(randomIndex);
            confirmedReservations.remove(seat);
            return seat;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * @brief Removes a checked seat from the checked reservations list.
     * @return The randomly selected seat that was removed.
     *
     * @details This method removes a seat from the checked reservations list and returns it.
     * It waits if the list is empty until a seat becomes available.
     */
    public synchronized Seat removeChecked() {
        boolean flag = false;
        while (checkedReservations.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                flag = true;
                notifyAll();
                break;
            }
        }
        if (flag) {
            return null;
        }

        try {
            Random random = new Random();
            int randomIndex = random.nextInt(checkedReservations.size());
            Seat seat = checkedReservations.get(randomIndex);
            checkedReservations.remove(seat);
            return seat;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * @brief Returns the number of confirmed reservations.
     * @return The size of the confirmed reservations list.
     */
    public synchronized int size() {
        return confirmedReservations.size();
    }

    /**
     * @brief Checks if there are any unchecked seats in the confirmed reservations list.
     * @return true if there are unchecked seats, false otherwise.
     */
    public boolean flagChecked() {
        for (Seat seat : confirmedReservations) {
            if (!seat.getChecked()) {
                return true;
            }
        }
        return false;
    }
}