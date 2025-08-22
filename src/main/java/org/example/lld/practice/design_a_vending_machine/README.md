# Design A Vending Machine

## 1. Problem Statement and Requirements

Our goal is to design a software system for a simple vending machine.

### Functional Requirements (What it must do):

- **Inventory Management:** The machine must be able to hold and manage a variety of products. Each product has a name,
  price, and a quantity in stock.

- **Payment:** It must accept coins or bills. For simplicity, let's assume it only accepts coins of a few specific
  denominations (e.g., $0.05, $0.10, $0.25, $1.00).

- **Product Selection:** Users should be able to select a product.

- **Purchase Process:**
    - The user inserts money.
    - The machine validates the inserted amount against the product's price.
    - If the amount is insufficient, the machine should indicate the remaining balance.
    - If the amount is sufficient and the product is in stock, the machine should dispense the product.
    - If a product is sold out, the machine should notify the user.

- **Change Dispensing:** The machine must be able to return the correct change. If it can't, it should refund the full
  amount.

- **Cancellation:** The user can cancel the transaction at any time before dispensing and get a full refund of the money
  they have inserted.

### Non-Functional Requirements (How well it must do it):

- **Maintainability:** The design should be easy to modify, for example, to add new coin denominations or a new payment
  method (like a credit card reader).
- **Scalability:** The system should be able to handle a large number of products without a complete overhaul.
- **Robustness:** The system should handle invalid inputs and unexpected states gracefully.
- **Testability:** Individual components should be easily testable in isolation.

---

## 2. Naive Solution: The "Starting Point"

A common first approach is to put everything into a single, large class. Let's call it `VendingMachine`.

### The Thought Process:

A beginner would likely think, "The vending machine is the main thing. It needs to handle everything: storing products,
taking money, giving change, and dispensing." This leads to a design where one class contains all the logic and state.

```java
// A simple, monolithic approach
class VendingMachine {
    // Inventory
    private Map<String, Product> products;
    // Money
    private Map<CoinType, Integer> coins;
    private double currentBalance;

    public void insertCoin(CoinType coin) {
        // ... logic to update balance ...
    }

    public void selectProduct(String productName) {
        if (products.containsKey(productName)) {
            Product product = products.get(productName);
            if (currentBalance >= product.getPrice()) {
                // ... logic for dispensing, calculating change ...
            } else {
                // ... tell user to insert more money ...
            }
        } else {
            // ... product not found ...
        }
    }

    // ... many more methods ...
}
```

### Limitations and Design Flaws:

This design, while it might work for our simple requirements, has several major problems:

- **Violation of SOLID Principles:**
    - **Single Responsibility Principle (SRP):** This class does too much. It's responsible for inventory, payment,
      state management, and dispensing. What if we need to change how payment works (e.g., add a new currency)? We'd
      have to modify this single, massive class, which risks breaking other parts of the system.
    - **Open/Closed Principle (OCP):** If we want to add a new coin type or a new payment method, we have to open and
      modify the `VendingMachine` class. The class is not "open for extension but closed for modification."
- **Tight Coupling:** The components are all tightly linked within this one class. The payment logic is directly tied to
  the inventory logic.
- **Lack of Scalability:** As the system grows, this single class will become a huge, unmanageable monolith. Adding new
  features or types of products will become a nightmare.
- **Difficulty in Testing:** Because everything is intertwined, you can't test the payment logic in isolation. To test
  getChange(), you need to set up the entire VendingMachine state, including the inventory and current balance.

In short, the naive solution is like a one-person band. They play all the instruments, but it's hard to make a complex,
harmonious piece of music, and if one instrument breaks, the whole show stops.

---

## 3. Improved Solution: The "Mentor's Guidance"

The key to a good design is to break down the problem into smaller, independent parts. This is where separation of
concerns comes in. Instead of one big class, let's have several classes, each with a single, well-defined
responsibility.

### The "Why": The State Pattern

The most critical part of a vending machine's operation is its state. A vending machine isn't just a collection of
methods; its behavior changes based on what's happening.

- When it's ready for a transaction, it accepts coins.

- When a user has inserted money, it allows product selection.

- If the balance is insufficient, it waits for more money.

- After dispensing, it returns to the "ready" state.

This is a classic use case for the **State Design pattern.**

The State pattern allows an object to alter its behavior when its internal state changes. The object appears to change
its class. In our case, the `VendingMachine` class will no longer have a huge `switch` statement or `if/else` block to
handle its state. Instead, it will delegate its behavior to a separate `State` object.

This solves the OCP problem from the naive solution. Adding a new state (e.g., "maintenance mode") doesn't require us to
modify the `VendingMachine` class. We simply create a new `State` class and plug it in.

### Core Classes and Their Interactions:

Let's design our core classes using principles like composition over **inheritance and encapsulation**.

### Design Overview:

Our final design is a well-structured system composed of several small, highly focused classes. The `VendingMachine`
class acts as a context. It is a lightweight orchestrator that delegates tasks to other, more specialized objects. The
`State` objects handle all the logic for a specific stage of the transaction. The behavior of the machine "appears" to
change simply by changing the `VendingMachine`'s current `State` object. The `Inventory` class manages all product data,
separating inventory management from the vending process itself. The `CoinHandler` class manages the money and change
calculation, separating payment logic from the core vending machine logic.

1. `VendingMachine` (The Context):
    - This is the central class that users interact with.
    - It doesn't contain the detailed business logic for each step.
    - It composes other objects (like `Inventory` and `CoinHandler`).
    - It holds a reference to the current state object. All user actions (like `insertCoin()`, `selectProduct()`) are
      delegated to this current state. This makes our `VendingMachine` class small and clean.
2. `State` Interface:
    - This is the contract for all possible vending machine states.
    - It defines the methods that a state must implement, such as `insertCoin()`, `selectProduct()`,
      `dispenseProduct()`, and `refund()`.
3. Concrete `State` Classes:
    - `IdleState`: The initial state. It waits for the user to insert money.
    - `HasMoneyState`: The machine has some money, but not enough for a product. It waits for more money or product
      selection.
    - `ProductSelectedState`: The user has selected a product. It validates if the money is enough and if the product is
      in stock.
    - `DispensingState`: The product is being dispensed, and change is being calculated.
    - `SoldOutState`: A specific product is sold out. It informs the user and returns to `IdleState`.
4. `Inventory` (The Repository):
    - This class is solely responsible for managing the products.
    - It has methods like `getProduct()`, `getQuantity()`, `decreaseQuantity()`, and `addProduct()`.
    - This adheres to SRP. The `VendingMachine` class doesn't know how products are stored; it only knows it can ask the
      `Inventory` object for a product. This is a great example of encapsulation and separation of concerns.

5. `CoinHandler` (The Strategist):
    - How do we handle different coin denominations and calculate change? This is a great place for the **Strategy
      design pattern.**
    - We can have a PaymentStrategy interface and concrete implementations like `CoinPaymentStrategy` or a future
      `CardPaymentStrategy`.
    - The `VendingMachine` class would hold a reference to the current strategy and delegate payment processing to it.
      This solves our OCP problem with payment. If we need to add a new payment method (like credit card), we just
      create a new `PaymentStrategy` class and plug it in without touching the existing `VendingMachine` or
      `CoinHandler` classes.

### Summary of the Design Benefits:

- **More Maintainable:** We can change the payment system (add credit cards) or add new states (out of order,
  maintenance)  without touching the core `VendingMachine` class. Each part of the system is easy to understand and
  modify in isolation.
- **More Scalable:** Adding new products or payment types is as simple as creating new data objects or classes, not
  refactoring a monolith.
- **More Robust:** By encapsulating behavior within specific states, we prevent illegal operations. For example, if we
  are in the `IdleState`, a call to `dispenseProduct()` will be a no-op or throw an error, which is much safer than a
  large `if/else` block that could miss a case.
- **Easily Testable:** You can test the `IdleState`'s behavior independently of the `DispensingState`, and you can test
  the `Inventory` logic without a full-blown vending machine.

