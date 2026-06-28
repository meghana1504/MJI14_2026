package DEV.a_JavaBasics.A7_JAVA_8_Features;
/*
* WHY DO WE NEED METHOD REFERENCES WHEN LAMBDAS ALREADY EXIST ??
* -> We hv to write lesser code
* -> Amount of code we need to write -
*    standard names class implementation > nested inner class > anonymous inner class > lambda expressions > method reference
* -> Method references provide a shorthand notation for lambda expressions that only call a single method.
*
* Another point
The compiler has to answer this question:
"What code should run when the functional interface method is invoked?"
    With a lambda, the answer is: "Run this little block of code."
    With a method reference, the answer is: "Just call this existing method."

Because the method already exists, the compiler/JVM may be able to represent it more directly,
without synthesizing an extra forwarding implementation.
*
* One more advantage
    Well, one advantage above just writing less code is,
    that the method ref impl doesn't have to be aware of the parameters
    as long as the implementation of that functional interface has the same signature (-> is correct) your code get's compiled.

    If the signature of the the functional interface changes and the signature of the implementation changes analogously,
    these changes only need to be applied there and not on that glue code.
*
*
* TYPES OF METHOD REFERENCES ---
* 1. Reference to a static method - ClassName::methodName
* 2. Reference to an instance method of an object - object::methodName
* 3. Reference to an instance method of an arbitrary object of a particular type - ClassName::methodName
* 4. Reference to a constructor - ClassName::new
* */
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class D_MethodReferences {
    public static void main(String[] args) {
        List<String> subjects = new ArrayList<>();
        subjects.add("Physics");
        subjects.add("Comp Sci");
        subjects.add("Chemistry");

        System.out.println("Implementing Consumer interface using Lambda expression ");
        Consumer<String> printer = (str)-> System.out.println(str);
        for(String x : subjects)
            printer.accept(x);

        System.out.println("\nNow replacing the same Consumer interface implementation with method reference");
        Consumer<String> printer2 = System.out::println;
        for(String x : subjects)
            printer2.accept(x);
    }
}

// REFER ALSO : "C:\Users\Lenovo\Desktop\2026\2024\MJI14Plan2024_1\src\F_Java8Features\F0_MethodConstReference.java"
