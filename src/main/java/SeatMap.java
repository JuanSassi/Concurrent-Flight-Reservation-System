import java.util.Random;

/**
 * @project Concurrent Flight Reservation System
 * @file    SeatMap.java
 * @brief   SeatMap class for managing and reserving seats in the flight reservation system.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 * developed in Java. Concurrency is achieved through thread management,
 * simulating a realistic flight reservation environment.
 * The SeatMap class represents the grid of seats in the reservation system,
 * allowing for seat reservations, random seat selection, and displaying the seat map.
 * This file is part of a project developed for the Concurrent Programming course
 * of the FCEFYN at the National University of Córdoba (UNC).
 * All rights reserved.
 */
public class SeatMap {
    private Seat[][] seatMap; ///< A 2D array representing the seat map (rows and columns).
    private int rows; ///< Number of rows in the seat map.
    private int columns; ///< Number of columns in the seat map.

    /**
     * Constructor for the SeatMap class.
     * Initializes the seat map with the specified number of rows and columns,
     * and assigns IDs to each seat.
     *
     * @param constants The constants object providing the number of rows and columns.
     */
    public SeatMap(Constants constants){
        this.rows = constants.getRows();
        this.columns = constants.getColumns();
        seatMap = new Seat[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seatMap[i][j] = new Seat();
                seatMap[i][j].setId(i * seatMap[0].length + j + 1);
            }
        }
    }

    /**
     * Reserves a seat by selecting a random available seat and changing its status to occupied.
     *
     * @return The seat that was reserved.
     */
    public synchronized Seat reserve(){
        // Randomly select an available seat
        Seat seat = randomAvailableSeat();

        // Change the seat status to OCCUPIED
        seat.occupiedSeat();

        // Return the seat to be added to the Pending Payment Reservations list
        return seat;
    }

    /**
     * Selects a random available seat from the seat map.
     * The seat's status must be AVAILABLE.
     *
     * @return A random available seat.
     */
    public Seat randomAvailableSeat(){
        Random random = new Random();
        int randomRow;
        int randomColumn;
        Seat seat;
        do{
            randomRow = random.nextInt(seatMap.length);
            randomColumn = random.nextInt(seatMap[0].length);
            seat = seatMap[randomRow][randomColumn];
        }while(seat.getStatus() != SeatStatus.AVAILABLE);
        return seat;
    }

    /**
     * Generates a string representation of the seat map, showing the ID and status of each seat.
     *
     * @return A formatted string representing the seat map.
     */
    public String printMatrix(){
        StringBuilder printSeatChart = new StringBuilder();
        printSeatChart.append("\n");
        int idWidth = 3;
        int statusWidth = 10;

        // Iterate through each row and seat, formatting the output
        for (Seat[] row : seatMap) {
            for (Seat seat : row) {
                String formattedSeat = String.format("ID: %-" + idWidth + "d State: %-" + statusWidth + "s;\t", seat.getId(), seat.getStatus());
                printSeatChart.append(formattedSeat);
            }
            printSeatChart.append("\n");
        }

        printSeatChart.append("\n");
        return printSeatChart.toString();
    }
}