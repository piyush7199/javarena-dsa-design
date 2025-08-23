# Design A Car Rental Service.

## 1. Problem Statement and Requirements

Our task is to design a software system for a Car Rental Service. This system should allow users to rent cars, manage a
fleet, and handle payments.

### Functional Requirements:

- **User Management:** Users should be able to register, log in, and view their rental history.
- **Car Management:** The system must manage a fleet of cars, including details like make, model, year, and a status (
  e.g.,
  available, rented, out for maintenance).
  **Booking Process:**
    - Users can search for available cars based on location, date range, and car type.
    - They can reserve a specific car for a defined period.
    - The system should prevent double-booking of a single car.

- **Payment:** The system must handle payment processing for the rental fee.

- **Rental Actions:** Users should be able to pick up and drop off a car. The system needs to update the car's status
  accordingly.

### Non-Functional Requirements:

- **Scalability:** The system should be able to handle a large number of users, cars, and rental transactions without
  performance degradation.

- **Availability:** The service must be highly available, especially the booking functionality.

- **Maintainability:** It should be easy to add new car types, payment gateways, or rental locations.

- **Security:** User data and payment information must be protected.

---

## 2. Naive Solution: The "Starting Point"

A beginner might combine all the logic into a single class, perhaps named RentalManager.

### The Thought Process:

The simplest approach is to centralize everything. "I'll have a `RentalManager` class that holds all the cars, all the
users, and all the rental bookings. It'll have methods like `searchCar()`, `rentCar()`, and `returnCar()`." This seems
straightforward at first.

```java
// A simple, monolithic approach
class RentalManager {
    private List<Car> cars;
    private List<User> users;
    private List<Booking> bookings;

    public Car searchCar(String location, Date startDate, Date endDate) {
        // ... complex logic to iterate through all cars and bookings ...
    }

    public void rentCar(User user, Car car, Date startDate, Date endDate) {
        // ... logic to create a new booking, check for conflicts, etc. ...
    }

    public void returnCar(Booking booking) {
        // ... logic to mark the car as available and finalize the booking ...
    }
    // ... many other methods ...
}
```

### Limitations and Design Flaws:

This monolithic design quickly falls apart under scrutiny:

- Violation of SOLID Principles:
    - **Single Responsibility Principle (SRP):** The `RentalManager` does everything. It manages users, cars, and
      bookings. A change to the car details might unintentionally break the booking logic.
    - **Open/Closed Principle (OCP):** Adding a new rental rule (e.g., a special discount for a specific user type) or a
      new car attribute requires modifying this single, large class.
- **Tight Coupling:** The car, user, and booking objects are all directly managed by the `RentalManager`, creating a
  highly interdependent system. It's impossible to reuse the `Car` or `User` class in another context without dragging
  along the entire `RentalManager`.

- **Lack of Scalability:** As the number of cars and users grows, the linear search within the `searchCar()` method will
  become extremely slow. The single class becomes a performance bottleneck.

- **Complexity:** The class is a "God Object" or "God Class." It's huge, difficult to read, hard to debug, and a
  nightmare for multiple developers to work on simultaneously.

This naive solution is like trying to build a skyscraper with a single blueprint. While it might work for a small shed,
it's not feasible for a large, complex structure.

---

## 3. Improved Solution: The "Mentor's Guidance"

The solution to our problems is to separate concerns. We will break down the problem domain into logical, independent
components.

### The "Why": The Factory and Repository Patterns

- **Car Creation:** We need a way to create different types of cars (e.g., Sedan, SUV, Truck) without tying our core
  rental logic to specific car classes. This is a perfect use case for the Factory pattern. Instead of `new Car()`,
  we'll have a `CarFactory` that handles the instantiation. This makes it easy to add new car types in the future.
- **Data Access:** The `RentalManager` shouldn't know how data is stored (e.g., in-memory, a database, a file). We use
  the `Repository` pattern to abstract this. We'll have a `CarRepository`, a `UserRepository`, and a
  `BookingRepository`. These classes are responsible for all CRUD (Create, Read, Update, Delete) operations for their
  respective entities. This dramatically improves maintainability and testability.
- **Booking Logic:** The core of the system is the booking process. The business logic for checking availability and
  creating
  a booking should be encapsulated in a dedicated service class, say `RentalService`. This service will use the
  repositories to perform its tasks.

### Core Classes and Their Interactions:

We will design a system of collaborating classes, each with a single, clear responsibility.

1. **Entity Classes (Data Models):**
    - `User`: Represents a user with properties like `userId`, `name`, etc.
    - `Car`: Represents a car with properties like `carId`, `model`, `make`, and `status`.
    - `Booking`: Represents a rental booking with properties like bookingId, `carId`, `userId`, `startDate`, `endDate`,
      and `totalCost`.
    - These classes are plain old Java objects (POJOs) or data classes. They don't contain any business logic.

2. **Repository Interfaces & Implementations (Data Access Layer):**
    - `UserRepository`, `CarRepository`, `BookingRepository`: These are interfaces that define the contract for data
      access. For example, `CarRepository` will have methods like `save(Car car)`, `findById(String id)`, and `findByStatus(CarStatus
      status)`.
    - We can have concrete implementations, like `InMemoryCarRepository` for testing or a `DatabaseCarRepository` for
      production. This follows the **Dependency Inversion Principle**, as our service layer depends on the abstraction (
      the interface), not the concrete implementation.

3. **Service Classes (Business Logic Layer):**
    - `RentalService`: This is where the core logic resides. It will use composition to hold references to the
      repositories (`CarRepository`, `BookingRepository`, etc.).
    - Methods like `bookCar()` in `RentalService` will orchestrate the process:
        - It will ask the `CarRepository` to find an available car.
        - It will check the `BookingRepository` to ensure there are no conflicting bookings.
        - It will then create a `new Booking` object and save it using the `BookingRepository`.
        - Finally, it will update the car's status via the `CarRepository`.
    - This class is clean because all the data access details are hidden by the repositories.

4. **`PaymentService` (Extensibility with Strategy Pattern):**
    - If we need to add different payment methods (e.g., credit card, PayPal), we can use the **Strategy pattern**. We
      would have a `PaymentStrategy` interface with an `executePayment()` method. Concrete classes like
      `CreditCardPaymentStrategy` and `PayPalPaymentStrategy` would implement this. The `RentalService` would use the
      appropriate strategy to process the payment. This is a classic example of **Open/Closed
      Principle.**

5. **`Main` or `Controller` (The Application Entry Point):**
    - This class is the "driver" of the application. It creates instances of the repositories and services and ties them
      all together. It's responsible for the overall flow but contains minimal business logic.

---

## 4. Final Design Overview

Our final design is a modular, layered architecture. We've separated the application into distinct layers, each with a
single responsibility.

- Data Layer: The Repository classes manage data persistence.
- Service Layer: The Service classes contain the business logic, orchestrating calls to the repositories.
- Presentation Layer: (Not fully detailed here, but a UI/API would sit here) Handles user interaction and calls the
  service layer.

This design is:

- More Maintainable: A bug in the payment logic is confined to the `PaymentService` and its strategies. A change in the
  database schema only affects the repository layer.
- More Scalable: We can easily replace the `InMemoryRepository` with a `DatabaseRepository` without changing the
  RentalService. We can also distribute these services across different servers if needed.
- More Robust: Each class has a single, well-defined purpose, reducing the chance of bugs and making the system easier
  to test. We can test the `RentalService` by mocking its dependencies (the repositories), which is impossible with the
  naive solution.