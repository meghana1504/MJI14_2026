package DEV.b_SOLID_principles;

public class JustNotes {
    /*
    * SOLID is set of 5 design principles
    * How does it help us
    *
    * 1. Clean code
    * 2. Maintainable code
    * 3. Flexible Architecture
    * 4. Scalable products
    *
    * 5. Powerful when working on real world products
    * 6. Microservices
    * 7. Large Enterprise systems
    * 8. SaaS platform
    *
    * */

    /*
    * S - Single Responsibility Principle ********************
    * --- A class should have only one reason to change
    * --- A class should have ONLY ONE responsibility
    * --- check : InvoiceService.java
    *
    * */

    /* O - Open/ Closed Principle ********************
    * ---- Open for extension and Closed for Modification
    * ---- Without changing the existing code you should be able to add new features, bcoz requirements will increase in future
    * ---- Check : O_DiscountServices.java
    * ----
    * */

    /* L - Liskov Substitution Principle ******************** [DOES NOT NECESSARILY REQUIRE INHERITANCE, BTW]
    * ---- PROBLEM LSP SOLVES : ""Can a child safely replace its parent?"
    * ---- LSP FOCUSES ON ----: Behavioral Correctness, It's about whether inheritance relationships make sense.
    * ---- "derived or child classes must be able to replace their base or parent classes without altering the correctness of the program."
    *
    * If B is a child of A, I should be able to use B wherever I am using A, and everything should still work correctly.
    * If S is a subtype of T, then objects of type T should be replaceable with objects of type S without breaking the program.
    * Eg;
    *       Bird bird = new Penguin();
            bird.fly();
    * ---- The code compiles because a Penguin is-a Bird according to the hierarchy.
    * ---- But it fails at runtime because the subclass cannot honor the behavioral contract of the parent.
    * ---- That's the textbook LSP violation.
    *
    * Penguin isn't implementing an interface, it is extending from a class Bird which has a method fly() .
    * Penguin is inheriting behavior from a parent that doesn't fit it, "Penguin is inheriting a contract (fly()) that it cannot fulfill." - this is more LSP explaination
    *
    * WHEN SHOULD LSP ALARMS TRIGGER ??
    * ---- Child overrides methods and changes their meaning
    * ---- Child throws UnsupportedOperationException
    * ---- Child changes expected behavior
    *
    * */

    /* I - Interface Segregation Principle ********************
    * ---- PROBLEM ISP SOLVES : ""Am I forcing a class to implement methods it doesn't need?""
    * ---- ISP FOCUSES ON ----: Interface Design
    *
    * ---- Clients should not be forced to depend on interfaces they do not use.
    * ---- The goal is to prevent fat interfaces by using multiple small, client-specific interfaces, each with a clear and specific responsibility.
    * ---- Eg; Worker interface has eat() and work() methods
    * ---- If classes Robot and Human, both implement Worker, both of them WILL HAVE TO implement its methods
    * ---- Which doesn't make sense because a Robot doesn't eat
    *
    *
    * SOLUTION
    *
    * ---- We make abstract class Worker, for common behaviours of Robot and Human
    * ---- Create Workable interface - void work() - implemented by both classes
    * ---- Create Eatble interface --- void eat() -- implemented only by Human class
    *
    *
    * */

    /*
    When you see a design issue, ask:

        ISP Question
        -> "Am I forcing this class to implement methods it doesn't need?"
        If yes → ISP.

        LSP Question
        "Can I replace the parent with the child without breaking existing code?"
        If no → LSP.

    *
    * In MOST CASES, when it comes to fixing the design, ISP and LSP solution might look the same, but they are solving different root causes is the main point

        When does ISP lead to LSP?

        Usually when the forced method is implemented as:
        throw new UnsupportedOperationException();
        or some broken behavior.

        Then the implementation can't satisfy the abstraction's contract.
        That's when you get:
        ISP + LSP


        Bad Interface
              ↓
        ISP violation
              ↓
        Implementation throws UnsupportedOperationException
              ↓
        LSP violation
    */



    /* D - Dependency Inversion Principle ********************
    * ---- "High-level modules should not depend on low-level modules. Both should depend on abstractions"
    * ---- Abstractions should not depend on details. Details should depend on abstractions
    *
    * ---- When something changes in a high-level class, the lower-level classes need to change to match.
    * ---- But, when something changes in a low-level class, the high-level classes should not break.
    *
    *Whenever you see:
        new SomeConcreteClass()

    inside your business/service class, ask yourself:
    Should this class really know about this concrete implementation?
    If the answer is "not necessarily", introduce an interface and depend on that instead.
    *
    * dependency injection, is a way of achieving dependency inversion
    *
    * Steps to fix :
    * - Create an abstraction (Interface)
    * - Create concrete implementations depending on that Abstraction (Interface)
    * - Depend on the Abstraction - a class which holds Abstraction (Interface) reference
    * - And then use it
    * */




    /*
    * ************ ************ QUICK INTERVIEE MENTAL MODELS ************ ************
    *
        1. Is the class doing too much?          -> SRP
        2. Is there a giant if/switch?           -> OCP
        3. Is inheritance suspicious?            -> LSP
        4. Is an interface too big?              -> ISP
        5. Is a concrete class being hardcoded?  -> DIP
    *
    *
| Principle                                 | Quick Question to Ask                                                            | If Answer is Yes/No           | Common Clues                                                                                                         |
| ----------------------------------------- | -------------------------------------------------------------------------------- | ----------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **SRP (Single Responsibility Principle)** | **"How many independent reasons can this class change?"**                        | More than one → SRP violation | Fat classes, report generation + saving + emailing, unrelated methods in same class                                  |
| **OCP (Open-Closed Principle)**           | **"If I add a new feature/type tomorrow, will I have to modify existing code?"** | Yes → OCP violation           | Long `if-else`, `switch` on types, `customerType`, `paymentType`, `vehicleType` strings                              |
| **LSP (Liskov Substitution Principle)**   | **"Can I replace the parent with the child without breaking existing code?"**    | No → LSP violation            | `UnsupportedOperationException`, overridden methods changing meaning, Rectangle-Square problem, Penguin-Bird problem |
|                                           | Can I replace an abstraction with any of its implementations without changing expected behavior?                 |
| **ISP (Interface Segregation Principle)** | **"Am I forcing this class to implement methods it doesn't need?"**              | Yes → ISP violation           | Large interfaces, empty implementations, `UnsupportedOperationException`, Printer forced to Scan/Fax                 |
| **DIP (Dependency Inversion Principle)**  | **"Is this class depending directly on a concrete implementation?"**             | Yes → DIP violation           | `new MySQLDatabase()`, `new EmailService()`, `new StripePayment()` inside business logic                             |

    *
    *
    | Principle | Typical Fix                                                                    |
    | --------- | ------------------------------------------------------------------------------ |
    | **SRP**   | Split responsibilities into separate classes/services                          |
    | **OCP**   | Use interfaces, inheritance, Strategy Pattern, polymorphism                    |
    | **LSP**   | Fix incorrect inheritance hierarchy; prefer composition/interfaces when needed |
    | **ISP**   | Break large interfaces into smaller focused interfaces                         |
    | **DIP**   | Depend on interfaces/abstractions and use Dependency Injection                 |
*
* */
}
