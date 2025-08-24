# Design A Parking Lot Management System

## 1. Problem Statement and Requirements

Our goal is to design the software for an automated Parking Lot Management System.

### Functional Requirements:

- **Entry and Exit:** The system must control the entry and exit of vehicles. It should assign a unique ticket upon
  entry and validate it upon exit.
- **Parking Spot Management:** The system needs to keep track of available and occupied parking spots. Spots should be
  categorized by size (e.g., small, medium, large) to accommodate different vehicle types.
- **Vehicle Types:** The system must handle different vehicle types, such as motorcycles, cars, and buses. A motorcycle
  can fit in a small spot, a car in a medium spot, and a bus in a large spot.
- **Payment:** Upon exit, the system must calculate the parking fee based on the duration of stay and a predefined rate.
  It should handle payment processing.
- **Display:** The system should display the number of available spots for each size at the entrance.

### Non-Functional Requirements:

- **Scalability:** The system should be scalable to handle a parking lot with thousands of spots and high traffic.
- **Reliability:** The system must be robust and reliable. Failure of one component (e.g., a single entrance gate)
  should not
  bring down the entire system.
- **Maintainability:** It should be easy to add new vehicle types, spot sizes, or change pricing rules.
- **Concurrency:** The system must handle multiple vehicles entering and exiting at the same time without race
  conditions.

---

## 2. Naive Solution: The "Starting Point"

A beginner might, once again, opt for a single, monolithic class, perhaps called `ParkingLot`.

### The Thought Process:

"The parking lot is the core of the system. It needs to know about all the spots, all the cars, and handle all
operations." This leads to a design where a single `ParkingLot` class contains everything.

```java
class ParkingLot {
    private Map<Integer, ParkingSpot> spots;
    private List<Ticket> activeTickets;

    public void enterCar(Vehicle vehicle) {
        // ... find a spot, create a ticket, add to activeTickets ...
    }

    public void exitCar(Ticket ticket) {
        // ... find the car, calculate fee, process payment, free up spot ...
    }

    public int getAvailableSpots(SpotSize size) {
        // ... iterate through all spots, count available ones ...
    }
}
```

### Limitations and Design Flaws:

This "God Class" design has many of the same issues we've seen before:

- **Violation of SOLID Principles:**
    - **Single Responsibility Principle (SRP):** The `ParkingLot` class is responsible for spot management, ticket
      issuance, payment calculation, and vehicle tracking. What if we need to change how fees are calculated? We'd
      modify this one class, potentially breaking unrelated functionality.
    - **Open/Closed Principle (OCP):** Adding a new vehicle type (e.g., an electric vehicle with special charging spots)
      would require modifying the `enterCar()` method.
- **Tight Coupling:** The `enterCar()` method is tightly coupled to ParkingSpot and Vehicle objects. The payment logic
  is directly tied to the ticket and spot management logic.
- **Concurrency Issues:** With multiple threads trying to access and modify the same spots map, we'll run into race
  conditions without complex synchronization logic. This makes the code brittle and hard to manage.
- **Lack of Scalability:** A single `ParkingLot` object handling all operations for a large lot would become a
  bottleneck. It's difficult to scale a monolithic object.

---

## 3. Improved Solution: The "Mentor's Guidance"

The key is to model the system as a collection of independent, collaborating components. This is where separation of
concerns shines.

### The "Why": The Strategy and Singleton Patterns

- **Pricing:** The pricing logic is a prime candidate for the **Strategy pattern**. Pricing can vary based on duration,
  time of day, special events, or vehicle type. By defining a `PricingStrategy` interface and concrete implementations,
  we can easily swap pricing rules without changing the core `ParkingLot` logic.
- **Parking Lot as a Singleton:** A parking lot is a unique resource. There should only be one instance of it running at
  any given time. This is a perfect use case for the Singleton pattern. It ensures a single point of access to the
  parking lot's state, simplifying management and preventing multiple instances from corrupting data. However, for a
  real-world, highly distributed system, this might be too restrictive. For this design, a single instance is
  appropriate.
- **Factories:** Similar to our car rental system, the creation of different vehicle types and parking spots can be
  handled by a Factory. This hides the instantiation logic and makes it easy to add new types later.

### Core Classes and Their Interactions:

We'll break down the ParkingLot into a set of specialized, encapsulated classes.

1. `ParkingLot` **(The Singleton)**:
    - This class will be the global entry point. It's responsible for managing the high-level system state.
    - It won't contain all the logic; instead, it will hold references to other components via composition.
    - It will be a Singleton to ensure a single, consistent state.
2. `Gate` **(The Interface):**
    - The system has multiple entry and exit points. We can model this with an `EntryGate` and an `ExitGate` class.
    - They will be responsible for handling the physical entry/exit process, interacting with the `ParkingLot`'s service
      layer.
3. `ParkingSpotManager` **(The Repository):**
    - This class is a specialized component dedicated to managing parking spots. It knows how many spots are available
      and which ones are occupied.
    - It handles tasks like finding an available spot, assigning a vehicle to a spot, and freeing up a spot. This
      adheres to **SRP**.
4. **(The Data Model):**
    - A `Ticket` is a simple data object containing information about a parking session: vehicle details, entry time,
      and the assigned spot. It's a key piece of information passed between components.
    - `Vehicle` - An abstract base class for different vehicle types (Motorcycle, Car, Bus). This is a good use case for
      inheritance.
5. `PaymentService` **(The Orchestrator):**
    - This class is responsible for the entire payment process. It uses a `PricingStrategy` to calculate the fee and
      then interacts with an external payment gateway.
6. `PricingStrategy` **(The Strategy):**
    - An interface with a `calculateFee()` method.
    - Concrete classes like `HourlyPricingStrategy`, `DailyPricingStrategy`, or `WeekendPricingStrategy` will implement
      this. The `PaymentService` can be configured with the correct strategy.

---

## 4. Final Design Overview

Our final design is a modular, layered system with a clear separation of concerns.

* The `ParkingLot` Singleton acts as a controller, managing the overall state and delegating tasks.
* The `ParkingSpotManager` handles all resource allocation logic.
* The `Gate` classes manage the physical entry/exit points.
* The `PaymentService` handles all financial transactions and uses a configurable `PricingStrategy`.

This design is:

- **More Maintainable:** A change to the hourly rate only requires modifying the `HourlyPricingStrategy` class, leaving
  the rest of the system untouched.
- **More Scalable:** The `ParkingSpotManager` can be optimized independently of the payment system. Multiple entry/exit
  gates can run concurrently, as they interact with a single, controlled `ParkingLot` instance.
- **More Robust:** By having separate, encapsulated components, the system is less prone to bugs. For example, a failure
  in the payment gateway doesn't affect the ability to find available parking spots.
- **Concurrency-ready:** The `ParkingLot` singleton can be carefully synchronized to manage access to shared resources (
  like the `ParkingSpotManager`), preventing race conditions.
