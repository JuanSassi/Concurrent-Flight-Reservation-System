# Concurrent-Flight-Reservation-System
This project simulates a concurrent flight reservation system developed in Java. Concurrency is achieved through thread management, simulating a realistic flight reservation environment.

A functionality was implemented to manage seat reservations concurrently. This functionality consists of several processes that run simultaneously, following the design guidelines outlined below. The system handles the reservation of seats on an airplane, represented by a seat matrix where each seat can be occupied, free, or discarded. Additionally, four distinct reservation records are maintained:
- Pending payment reservations.
- Confirmed reservations.
- Canceled reservations.
- Verified reservations.

The system operates in four stages:

**Reservation Process:** This process is responsible for receiving reservation requests from users. Three threads were used to execute this process. Each thread attempts to reserve a random seat in the matrix, verifying that it is available. If the seat is not free, the thread searches for another available seat. Once a seat is reserved, it is marked as occupied and the pending reservation is recorded in the pending reservations record.

**Payment Process:** This process was executed by two threads and is responsible for verifying the payment of pending reservations. Each thread took a random reservation from the pending reservations record and performed a payment verification. A 90% probability was set for the payment to be approved and a 10% probability for it to be rejected. If the payment was approved, the reservation was removed from the pending record and added to the confirmed reservations record. Otherwise, the seat was marked as discarded, the reservation was marked as canceled, removed from the pending record, and added to the canceled reservations record.

**Cancellation/Validation Process:** If a user decided to cancel their reservation, it went to the cancellation process. Three threads handled canceling reservations. Each thread selected a random reservation from the confirmed reservations and canceled it with a 10% probability. If the reservation was canceled, it was removed from the confirmed reservations record and added to the canceled reservations record, while the seat was marked as discarded. If the reservation was not canceled, it was marked as "checked."

**Verification Process:** After execution, the final state of the seat matrix and the reservation records was verified to ensure that operations were completed correctly. This process randomly selected a reservation from the confirmed reservations record. For each reservation marked as "checked," it was removed from the confirmed reservations record and inserted into the verified reservations record. This process was executed by two threads.

Considerations:
- Each process had a fixed delay per iteration, but different between processes.
- Each seat was accessible by only one thread at a time to avoid simultaneous reservation conflicts.
- Each reservation was reviewed by only one thread at a time (regardless of the process).
- Each reservation could only be approved, confirmed, canceled, or verified once.
- The reservation, payment, cancellation, and verification processes ran concurrently to simulate a realistic reservation environment.
- The waiting times for each operation were random and configurable.
- Upon program startup, all threads were launched to begin execution.

The system included a LOG for statistical purposes, which recorded every 200 milliseconds in a file:
- The number of canceled reservations.
- The number of approved (verified) reservations.

Additionally, upon completion, the log printed the final flight occupancy and the total time taken by the program.