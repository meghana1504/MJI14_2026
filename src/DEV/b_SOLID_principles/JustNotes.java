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
    * S - Single Responsibility Principle
    * --- A class should have only one reason to change
    * --- A class should have ONLY ONE respponsibility
    * --- check : InvoiceService.java
    *
    * */

    /* O - Open/ Closed Principle
    * ---- Open for extension and Closed for Modification
    * ---- Without changing the existing code you should be able to add new features, bcoz requirements will increase in future
    * ---- Check : O_DiscountServices.java
    * ----
    * */

    /* L - Liskov Substitution Principle
    * ---- "derived or child classes must be able to replace their base or parent classes without altering the correctness of the program."
    * ---- Subclass must be replacable for their parent class without breaking the behaviour
    *Ask:
        "Kya main child object ko parent object ki jagah rakh sakta hoon bina kisi surprise ke?"
        Agar answer "No" hai:
        👉 inheritance galat hai.
    *
    * */

    /* I - Interface Segregation Principle
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

    /* D - Dependency Inversion Principle
    * ---- "High-level modules should not depend on low-level modules. Both should depend on abstractions"
    * ---- Abstractions should not depend on details. Details should depend on abstractions
    *
    * */
}
