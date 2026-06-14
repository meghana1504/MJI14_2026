package DEV.a_JavaBasics.A7_JAVA_8_Features;

import java.util.function.*;

/*
=================================================
JAVA 8 - FUNCTIONAL INTERFACES (FI)
=================================================

    Definition
    ----------
    A Functional Interface (FI) is an interface having exactly ONE abstract method.

    Example:
    interface Calculator {
        int calculate(int a, int b);
    }

    A Functional Interface acts as the TARGET TYPE for a lambda expression.


=================================================
WHY DO WE NEED FUNCTIONAL INTERFACES?
=================================================

    A lambda by itself has no meaning.

    Example:

    (a, b) -> a + b

    Java asks:
    "Which method is this implementing?"

    A Functional Interface provides that answer.

    Calculator calc = (a, b) -> a + b;

    Now Java knows that the lambda is implementing Calculator.calculate().


=================================================
WHY ONLY ONE ABSTRACT METHOD?
=================================================

    Suppose:

    interface Animal {
        void eat();
        void sleep();
    }

    Then:

    Animal a = () -> System.out.println("Eating");

    Problem:
    Which method should the lambda implement?
    - eat() ?
    - sleep() ?

    Java cannot determine this.

    Therefore:
    1 Lambda -> 1 Abstract Method


=================================================
WERE FUNCTIONAL INTERFACES INTRODUCED IN JAVA 8?
=================================================

    NO.

    Interfaces like these existed long before Java 8:

        Runnable
        Comparator
        Callable
        ActionListener

    Java 8 simply:
        1. Gave them the name "Functional Interface"
        2. Added @FunctionalInterface annotation
        3. Allowed lambdas to implement them


=================================================
WHY DID SINGLE-METHOD INTERFACES EXIST BEFORE JAVA 8?
=================================================

    Because Java often needed ONE customizable behavior.

    Examples:

        Runnable
        ---------
        Java knows HOW to create a thread.
        You tell Java WHAT should run.

        Method:
        void run();


    Comparator
    ----------
        Java knows HOW to sort.
        You tell Java HOW to compare.

        Method:
        int compare(T o1, T o2);

Pattern:

    Java handles the framework.
    You provide one piece of behavior.

    This is often called a callback.


=================================================
ISP (INTERFACE SEGREGATION PRINCIPLE)?
=================================================

    Functional Interfaces were NOT created because of ISP.

    However, many FIs naturally follow ISP because
    they are small and focused.

    Goal of ISP:
    -------------
        Don't force clients to implement methods they don't need.

    Goal of Functional Interface:
    -----------------------------
        Represent one behavior that can be supplied and passed around.

=================================================
@FunctionalInterface
=================================================

    Optional annotation.
    Highly recommended because compiler will ensure that only one abstract method exists.
*/

@FunctionalInterface
interface ICalculator {

    int calculate(int a, int b);

    // One or more default methods are Allowed
    default void show() {
        System.out.println("Default Method in ICalculator");
    }

    // One or more static methods are Allowed
    static void info() {
        System.out.println("Static Method in ICalculator");
    }

    // Adding another abstract method would cause compile error because of @FunctionalInterface
}

//=================================================
//          CODE
//=================================================

public class B_FunctionalInterfaes {

    public static void main(String[] args) {
        // For the Calculator Interface implementation, check A_LambdaExpressions.java
        /*
         * Built-in Functional Interfaces
         */

        // Predicate<T>
        // Input -> boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(10));


        // Function<T, R>
        // Input -> Output
        Function<String, Integer> length = str -> str.length();
        System.out.println(length.apply("Java"));


        // Consumer<T>
        // Input -> No Output
        Consumer<String> printer =
                name -> System.out.println(name);

        printer.accept("Rahul");


        // Supplier<T>
        // No Input -> Output
        Supplier<String> supplier =
                () -> "Hello Java 8";

        System.out.println(supplier.get());
    }
}


/*
=================================================
MOST IMPORTANT BUILT-IN FIs
=================================================

Predicate<T>
------------
Input -> boolean

Function<T,R>
-------------
Input -> Output

Consumer<T>
-----------
Input -> No Output

Supplier<T>
-----------
No Input -> Output


=================================================
INTERVIEW DEFINITIONS
=================================================

Functional Interface:
An interface having exactly one abstract method.

Lambda Expression:
A concise way of implementing a Functional
Interface's single abstract method.

Relationship:

Functional Interface = Contract

Lambda = Implementation

Together:

Calculator calc = (a, b) -> a + b;

Contract + Implementation
=================================================
*/
