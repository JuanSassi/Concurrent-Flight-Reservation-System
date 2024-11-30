/**
 * @project Concurrent Flight Reservation System
 * @file    Constants.java
 * @brief   This file contains the Constants class, which holds configuration
 *          values for the flight reservation system simulation.
 * @author  Juan Ignacio Sassi
 * @date    30/11/2024
 *
 * @details This project simulates a concurrent flight reservation system
 *          developed in Java. Concurrency is achieved through thread management,
 *          simulating a realistic flight reservation environment. This file
 *          is part of a project developed for the Concurrent Programming course
 *          of the FCEFYN at the National University of Córdoba (UNC). All rights reserved.
 */

/**
 * @class Constants
 * @brief Class that holds configuration constants for the reservation system.
 *
 * @details This class stores various configuration parameters for the flight
 *          reservation system, such as times for different processes, probabilities
 *          for seat confirmations, and the number of threads to simulate various tasks.
 *          It provides getter and setter methods to access and modify these values.
 */
public class Constants {

    /** Time in milliseconds for the reservation process. */
    private int reservationProcessTime = 200;

    /** Time in milliseconds for the payment process. */
    private int paymentProcessTime = 200;

    /** Time in milliseconds for the cancellation validation process. */
    private int cancellationValidationProcessTime = 200;

    /** Time in milliseconds for the verification process. */
    private int verificationProcessTime = 200;

    /** Time in milliseconds for logging. */
    private int logTime = 200;

    /** Number of reservation processes. */
    private int numReservationProcesses = 3;

    /** Number of payment processes. */
    private int numPaymentProcess = 2;

    /** Number of cancellation validation processes. */
    private int numCancellationValidationProcess = 3;

    /** Number of verification processes. */
    private int numVerificationProcess = 2;

    /** Number of rows in the reservation system. */
    private int rows = 31;

    /** Number of columns in the reservation system. */
    private int columns = 6;

    /** Probability of a seat being confirmed. */
    private int confirmedSeatProbability = 90;

    /** Probability of checking a seat. */
    private int checkSeatProbability = 90;

    /** Global flag to control the flow of the system. */
    private boolean globalFlag = true;

    /**
     * @brief Default constructor.
     */
    public Constants(){
    }

    /**
     * @brief Sets the time for the reservation process.
     * @param reservationProcessTime Time in milliseconds for reservation process.
     */
    public void setReservationProcessTime(int reservationProcessTime){
        this.reservationProcessTime=reservationProcessTime;
    }

    /**
     * @brief Sets the time for the payment process.
     * @param paymentProcessTime Time in milliseconds for payment process.
     */
    public void setPaymentProcessTime(int paymentProcessTime){
        this.paymentProcessTime=paymentProcessTime;
    }

    /**
     * @brief Sets the time for the cancellation validation process.
     * @param cancellationValidationProcessTime Time in milliseconds for cancellation validation process.
     */
    public void setCancellationValidationProcessTime(int cancellationValidationProcessTime){
        this.cancellationValidationProcessTime=cancellationValidationProcessTime;
    }

    /**
     * @brief Sets the time for the verification process.
     * @param verificationProcessTime Time in milliseconds for verification process.
     */
    public void setVerificationProcessTime(int verificationProcessTime){
        this.verificationProcessTime=verificationProcessTime;
    }

    /**
     * @brief Sets the time for logging operations.
     * @param logTime Time in milliseconds for logging.
     */
    public void setLogTime(int logTime){
        this.logTime = logTime;
    }

    /**
     * @brief Sets the number of reservation processes.
     * @param numReservationProcesses Number of reservation processes.
     */
    public void setNumReservationProcesses(int numReservationProcesses){
        this.numReservationProcesses=numReservationProcesses;
    }

    /**
     * @brief Sets the number of payment processes.
     * @param numPaymentProcess Number of payment processes.
     */
    public void setNumPaymentProcess(int numPaymentProcess){
        this.numPaymentProcess=numPaymentProcess;
    }

    /**
     * @brief Sets the number of cancellation validation processes.
     * @param numCancellationValidationProcess Number of cancellation validation processes.
     */
    public void setNumCancellationValidationProcess(int numCancellationValidationProcess){
        this.numCancellationValidationProcess = numCancellationValidationProcess;
    }

    /**
     * @brief Sets the number of verification processes.
     * @param numVerificationProcess Number of verification processes.
     */
    public void setNumVerificationProcess(int numVerificationProcess){
        this.numVerificationProcess = numVerificationProcess;
    }

    /**
     * @brief Sets the number of rows in the reservation system.
     * @param rows Number of rows.
     */
    public void setRows(int rows){
        this.rows=rows;
    }

    /**
     * @brief Sets the number of columns in the reservation system.
     * @param columns Number of columns.
     */
    public void setColumns(int columns){
        this.columns=columns;
    }

    /**
     * @brief Sets the probability of a seat being confirmed.
     * @param confirmedSeatProbability Probability of seat confirmation.
     */
    public void setConfirmedSeatProbability(int confirmedSeatProbability){
        this.confirmedSeatProbability=confirmedSeatProbability;
    }

    /**
     * @brief Sets the probability of checking a seat.
     * @param checkSeatProbability Probability of checking a seat.
     */
    public void setCheckSeatProbability(int checkSeatProbability){
        this.checkSeatProbability=checkSeatProbability;
    }

    /**
     * @brief Flags the global flag to true.
     */
    public void flagUp(){
        globalFlag = true;
    }

    /**
     * @brief Flags the global flag to false.
     */
    public void flagDown(){
        globalFlag = false;
    }

    /**
     * @brief Gets the time for the reservation process.
     * @return Time in milliseconds for the reservation process.
     */
    public int getReservationProcessTime(){
        return reservationProcessTime;
    }

    /**
     * @brief Gets the time for the payment process.
     * @return Time in milliseconds for the payment process.
     */
    public int getPaymentProcessTime(){
        return paymentProcessTime;
    }

    /**
     * @brief Gets the time for logging operations.
     * @return Time in milliseconds for logging.
     */
    public int getLogTime(){
        return logTime;
    }

    /**
     * @brief Gets the time for the cancellation validation process.
     * @return Time in milliseconds for the cancellation validation process.
     */
    public int getCancellationValidationProcessTime(){
        return cancellationValidationProcessTime;
    }

    /**
     * @brief Gets the time for the verification process.
     * @return Time in milliseconds for the verification process.
     */
    public int getVerificationProcessTime(){
        return verificationProcessTime;
    }

    /**
     * @brief Gets the number of reservation processes.
     * @return Number of reservation processes.
     */
    public int getNumReservationProcesses(){
        return numReservationProcesses;
    }

    /**
     * @brief Gets the number of payment processes.
     * @return Number of payment processes.
     */
    public int getNumPaymentProcess(){
        return numPaymentProcess;
    }

    /**
     * @brief Gets the number of rows in the reservation system.
     * @return Number of rows.
     */
    public int getRows(){
        return rows;
    }

    /**
     * @brief Gets the number of columns in the reservation system.
     * @return Number of columns.
     */
    public int getColumns(){
        return columns;
    }

    /**
     * @brief Gets the number of cancellation validation processes.
     * @return Number of cancellation validation processes.
     */
    public int getNumCancellationValidationProcess(){
        return numCancellationValidationProcess;
    }

    /**
     * @brief Gets the number of verification processes.
     * @return Number of verification processes.
     */
    public int getNumVerificationProcess(){
        return numVerificationProcess;
    }

    /**
     * @brief Gets the probability of a seat being confirmed.
     * @return Probability of seat confirmation.
     */
    public int getConfirmedSeatProbability(){
        return confirmedSeatProbability;
    }

    /**
     * @brief Gets the probability of checking a seat.
     * @return Probability of checking a seat.
     */
    public int getCheckSeatProbability(){
        return checkSeatProbability;
    }

    /**
     * @brief Gets the value of the global flag.
     * @return True if the flag is up, false if it is down.
     */
    public boolean getGlobalFlag(){
        return globalFlag;
    }
}