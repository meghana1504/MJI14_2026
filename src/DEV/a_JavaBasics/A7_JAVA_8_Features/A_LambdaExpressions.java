package DEV.a_JavaBasics.A7_JAVA_8_Features;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
========================================
JAVA 8 - LAMBDA EXPRESSIONS
========================================

What is a Lambda Expression?
    ----------------------------
    A lambda expression is a concise way of providing the implementation of a Functional Interface's single abstract method.

    Lambda can not exist on its own, otherwise it has no meaning
    Example -> if java allows this :
        (a, b) -> a + b;
    What should Java do with it?
        Store it where?
        Call it when?
        What are the parameter types?
        What's the return type?
    Java has no answers.

    That's why a lambda always needs a target type, and that target type is usually a functional interface.

Syntax:
    (parameter1, parameter2) -> expression

or

    (parameter1, parameter2) -> {
        multiple statements;
    }

Key Points:
-----------
    1. Lambda = Anonymous function (no method name).
    2. Used to implement Functional Interfaces.
    3. Reduces boilerplate code compared to anonymous classes.
    4. Parameter types are usually inferred by the compiler.
    5. Cannot be used with interfaces having more than one abstract method.
    6. Commonly used with Streams, Collections, and CompletableFuture.

Mental Model:
-------------
    Functional Interface = Contract
    Lambda = Implementation of the SAM (Single Abstract Method) in the FI(Functional Interfaces)

    Example:
    Calculator calc = (a, b) -> a + b;

    Here:
    - Calculator defines WHAT should be done.
    - Lambda defines HOW it should be done.
    */


// Functional Interface
interface Calculator {
    int calculate(int a, int b);
}

// Super traditional implementation
class CalculatorImpl implements Calculator{

    @Override
    public int calculate(int a, int b) {
        return a-b;
    }
}

public class A_LambdaExpressions {

    public static void main(String[] args) {

        // Using traditional implementation
        Calculator calc0 = new CalculatorImpl();
        int ans0 = calc0.calculate(10,6); // This one Subtracts
        System.out.println("Subtracting 2 numbers using CalculatorImpl "+ans0);

        // Implementation using anonymous class
        Calculator calc1 = new Calculator(){
            public int calculate(int n1, int n2){
                return n1/n2;
            }
        };
        int ans1 = calc1.calculate(20,2); // this one divides
        System.out.println("Dvision of 20 and 2 is "+ans1);

        // Lambda Implementation
        Calculator calc2 = (no1, no2)-> no1*no2;
        int ans2 = calc2.calculate(40,20); // This one Multiplies
        System.out.println("40 multiplied by 20 is "+ans2);

        // Lambda with Multiple Statements - do addition()
        Calculator calc3 = (no1, no2) -> {
            System.out.println("This is a lambda with Multiple statements ");
            return no1+no2;
        };


    /*
    ========================================
    WHY FUNCTIONAL INTERFACE IS REQUIRED?
    ========================================

    Consider:

    interface Animal {
        void eat();
        void sleep();
    }

    Now suppose Java allowed:

    Animal a = () -> System.out.println("Eating");

    Question:
    Which method is being implemented?
    - eat() ?
    - sleep() ?

    Java cannot determine this.

    Therefore, lambdas work only with interfaces having exactly one abstract method.
    This is called a Functional Interface.

    To implement interfaces with more than one abstract methods, we need to use
    - anonymous classes or
    - traditional implementation

    */


    /*
    ========================================
    COMMON LAMBDA EXAMPLES
    ========================================
    */

    // No parameters
    Runnable r = () -> System.out.println("Running");
    r.run();
    // One parameter
    // x -> x * 2

    // Multiple parameters
    // (a, b) -> a + b

    // Multiple statements
    // (a, b) -> {
    //     int sum = a + b;
    //     return sum;
    // };


    /*
    ========================================
    LAMBDA WITH COLLECTIONS
    ========================================
    */

        List<String> names = Arrays.asList("John", "Alice", "Bob");

        /*
         * forEach() expects a Consumer<T>
         * Consumer is a Functional Interface
         */
        Consumer<String> consumer = (name) ->System.out.println(name);
        names.forEach(consumer);

        // OR
        names.forEach((n)-> System.out.println(n));

    }


}

/*
========================================
INTERVIEW DEFINITION
========================================
A lambda expression is a concise way of implementing a Functional Interface'ssingle abstract method.

*/