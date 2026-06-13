package DEV.a_JavaBasics.A2_GenericsInJava;

import java.util.ArrayList;
import java.util.Arrays;

public class JavaGenerics {
    public static void main(String[] args) {
    /*
    * ===========================================================================
    * ----------- WHY DO WE NEED GENERICS ? --------------
    * ===========================================================================
    * Observe below code
    * */

    ArrayList list = new ArrayList();
    list.add("Megh");
    list.add(10);
    list.add(10.4);
    System.out.println("List size: "+list.size());
    String str = (String) list.get(0);
    System.out.println("str: "+str);
//    System.out.println("Complete list : "+Arrays.toString(list.toArray()));
    Integer o = (Integer) list.get(1); // Manual casting reqd
//    String str2 = (String) list.get(2); // ClassCastException will occur at RT, Uncomment it
//    System.out.println("str2: "+str2);

    /*
    * What are the disadvantages here ?
    * -> This will be a RUNTIME ClassCastExeption, will not be caught at Compile Time
    * -> We have to do MANUAL CASTING here
    * -> NO TYPE SAFETY (Container mdhe type1 takun type2 retrive kraycha prayatna krtoy)
    *
    * -> After we converted list to only hold String, woh dusra data type add hi nahi krne dega
    * -> See below code, wont give any error
    * */

    ArrayList<String> list1 = new ArrayList<>();
    list1.add("Megh");
//    list1.add(10); // We can not add ANY other type than String here, COMPILER restricts us to do that, since we hv mentioned <String>
//    list1.add(10.4);

     // But again, I will have to create different containers to hold different kind of objects
     // will lead to Code duplication

    /*
     * ===========================================================================
     * ----------- HOW DOES GENERICS SOLVE THESE PROBLEMS ? ----------
     * ===========================================================================
     * Checkout Box.java
     * */

        Box box = new Box();
        box.setValue(10); // I am storing integer in the box
        int val1 = (int) box.getValue();
        System.out.println("Integer from Box class, needed casting " +val1);

        Box box2 = new Box();
        box2.setValue("Megh");
        String val = (String) box2.getValue(); // This still is fine
        System.out.println("String from Box class, needed casting " +val);

        // Now this looks like a Generic class, but IT IS NOT
        // Because Java only sees Object, whenever you retrieve something, you must tell Java what the real type is: Integer, String or Float etc
        // So there is STILL MANUAL CASTING INVOLVED

        // Also this is again dangerous since if we cast box2 to Integer by Mistake, It gives "ClassCastException", UCOMMENT Below code to get exception
        /*box2.setValue("Megh");
        Integer val3 = (Integer) box2.getValue(); // By MISTAKE, I casted it to Integer now
        System.out.println("String from Box class, needed casting " +val); // It won't detect at compile time*/

        /*
        * Now I want a box which can hold float, String etc, will I write different classes for them? NO
        * ABOVE Box class looks like this one, BUT, manual casting is still required hence it is NOT TYPE SAFE
        * SO NOW, I also want to get rid of Manual casting
        * I will now create a GenericBox - THIS ONE IS ACTUALLY A GENERIC BOX
        * Checkout - GenericBox.java
        * */


        GenericBox<String> genValStr = new GenericBox<>("This is a string"); // EVEN if I dont mention <String> here, I wont get this error, I think Java handles it
        String genValStrValue = genValStr.getValue();
        System.out.println("GenBox String "+genValStrValue);

        GenericBox<Integer> genBoxInt = new GenericBox<>(90);
//         String integer = genBoxInt.getValue(); // Since we have mentioned that GenBox will hold INTEGER, This line will give error at COMPILE TIME ITSELF
        Integer genValInt = genBoxInt.getValue();
        System.out.println("GenBox integer "+genValInt); // Infact we dont even need the casting now since we have mentioned <Integer>

        // IF I TRY BELOW, It does not give ANY Compile time or run time error
        // Seems like it should, because I am trying to store String value in a box of type Integer
        // But it runs fine, WHY??
        // Because, we are not providing exact type here, compiler issues warning : "Raw use of parameterized class 'GenericBox' "
        // so this is a bad practice with Generics, EVEN THOUGH IT WORKS
        genBoxInt = new GenericBox("iii");
        System.out.println("GenBox STRING ERROR ?? "+genBoxInt.getValue());

        // [PROOF]
        // If we try below code :
//        genBoxInt = new GenericBox<String>("iii"); // UCOMMENT TO SEE ERROR
        // Compiler issues an error - " you're trying to assign a GenericBox<String> to a variable of type GenericBox<Integer>."
        // But we had not mentioned specific type <string>, so it is treated as :
        new GenericBox<Object>("iii");
        // with the generic information erased.

        // NOW, Why does java even allow this?? Feels like it should not allow RAW TYPES AT ALL
        // but......
        // Java allows this for backward compatibility with code written before generics existed (Java 5).

        // But below line issues a compiler error saying : "required Type":"Integer", "Provided":"String"
//        String value = genBoxInt.getValue(); // // UCOMMENT TO SEE ERROR

        /*
        * When generics were introduced, Oracle couldn't break millions of existing Java programs.
        * A good habit is to treat unchecked warnings as errors mentally.
        * Always give TYPE while working with Generics, while creating the objects of generic classes
        * Otherwise, "GENERIC TYPE SAFETY" Is being compromised
        * So intuition : "If I say GenericBox<Integer>, it should only hold Integers." is correct as long as
        * - You stay within the generic type system.
        * - The moment you use a raw type (new GenericBox("iii")),
        * - You're essentially telling the compiler:
        * - "Ignore generic type checking for this object."
        * - That's why the assignment compiles with a warning instead of an error
        * */

        /*
     * ===========================================================================
     * ----------- ADVANTAGES OF GENERICS - SUMMARISED ? ----------
     * ===========================================================================
    * 1. Type casting blunders are caught at compile time, rather than runtime
    * 2. Do not need to do Manual Casting - where mistakes are possible, so code becomes safer and cleaner
    * 3. Enables code reusability, One Class (eg; box) / method can hold multiple types of data, so no code duplication
    * 4. Improves readability and maintainability because the intended type is explicitly known to the compiler and other developers.
    * 5. Type safety is enforced at compile time when we mention types correctly
    * */

    // Similarly GENERIC Interfaces bhi bana skte ho, and either a generic class can implement it, or even a normal class where the type of params are known
        // TODO : Create Container.java and GenericContainer.java

    // We can also have generic Methods and generic Constructor in a class without making that class Generic
        // see class GenericMethod.java
        /*
        --- With below syntax
            public <T> void printArray(T array) {
            // method body
            }
        * */

    /*
     * ===========================================================================
     * ----------- TYPE ERASURE ? ----------
     * ===========================================================================
     * - At Runtime >
            ArrayList<Integer>
            ArrayList<String>
     * - Both become
            ArrayList
     * - and store elements as Object references.
     * - If Java allowed:
     *      ArrayList<int>
     * - the JVM would need a completely different implementation to store raw primitive values.
     * - The original generics design avoided that complexity.
     *
    * */
    }
}
