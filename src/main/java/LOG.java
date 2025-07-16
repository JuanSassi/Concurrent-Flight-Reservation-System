/**
 * @project Concurrent Flight Reservation System
 * @file    LOG.java
 * @brief   LOG class responsible for logging flight reservation system information
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The LOG class is responsible for writing log information to a file during
 * the simulation of the reservation system. It logs the current system time,
 * the number of verified and canceled reservations, and the seat map.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LOG extends Thread {
    public String filePath; // Path to the log file
    private long startTime;  // Start time of the logging process
    private Constants constants; // Constants related to system behavior
    private VerifiedReservations verifiedReservations; // List of verified reservations
    private CancelledReservations cancelledReservations; // List of canceled reservations
    private SeatMap seatMap; // Seat map of the flight reservation system

    /**
     * Constructor to initialize the LOG class with necessary parameters.
     *
     * @param startTime The start time for logging.
     * @param constants System constants that affect the logging behavior.
     * @param seatMap The seat map to print at the end of the log.
     * @param verifiedReservations The verified reservations to include in the log.
     * @param cancelledReservations The canceled reservations to include in the log.
     */
    public LOG(long startTime, Constants constants, SeatMap seatMap ,VerifiedReservations verifiedReservations, CancelledReservations cancelledReservations) {
        // Set the file path automatically
        this.filePath = System.getProperty("user.dir") + "/LOG.txt";
        //this.filePath = System.getProperty("user.dir") + File.separator + "LOG.txt";
        this.startTime = startTime;
        this.constants = constants;
        this.verifiedReservations = verifiedReservations;
        this.cancelledReservations = cancelledReservations;
        this.seatMap = seatMap;
    }

    /**
     * Run method executed by the log thread.
     * It writes log information periodically to the log file.
     */
    public void run() {
        long time = 0;
        try {
            // Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(filePath);

            // Create a BufferedWriter to write text efficiently
            BufferedWriter buffer = new BufferedWriter(writer);

            // Continuously write log information while the global flag is true
            while (constants.getGlobalFlag()) {
                // Write the log information
                buffer.write(infoLog());
                try {
                    sleep(constants.getLogTime());
                } catch (InterruptedException e) {
                    break;
                }
            }

            // Write the final seat map and duration of the log thread
            buffer.write(seatMap.printMatrix());
            time = System.currentTimeMillis() - startTime;
            buffer.write("\n" + "LOG thread ended, duration: " + time);
            // Close the BufferedWriter
            buffer.close();

            // Print final information to the console
            System.out.println(seatMap.printMatrix());
            System.out.println(asientosDeRegistros());
            System.out.println("\nLOG file automatically saved in: " + filePath);

        } catch (IOException e) {
            System.out.println("\nError writing to file: " + e.getMessage());
        }
        System.out.println("LOG thread ended, duration: " + time);
    }

    /**
     * Generates a string with the current log information.
     *
     * @return A string containing current time and reservation data.
     */
    public String infoLog() {
        String infoLog = "\n Current time: " + (System.currentTimeMillis() - startTime);
        infoLog += "\n Number of canceled reservations: " + cancelledReservations.size();
        infoLog += "\n Number of verified reservations: " + verifiedReservations.size();
        return infoLog;
    }

    /**
     * Generates a string containing the details of verified and canceled reservations.
     *
     * @return A string representing the details of the reservation entries.
     */
    public String asientosDeRegistros() {
        StringBuilder recordEntries = new StringBuilder();

        recordEntries.append("\nVerified reservations: ");
        recordEntries.append("Size = ").append(verifiedReservations.size()).append("\n");
        for (Seat seat : verifiedReservations.getVerifiedReservations()) {
            recordEntries.append(seat.getId()).append(" - ");
        }

        recordEntries.append("\nCanceled Reservations: ");
        recordEntries.append("Size = ").append(cancelledReservations.size()).append("\n");
        for (Seat seat : cancelledReservations.getCancelledReservations()) {
            recordEntries.append(seat.getId()).append(" - ");
        }

        return recordEntries.toString();
    }
}
