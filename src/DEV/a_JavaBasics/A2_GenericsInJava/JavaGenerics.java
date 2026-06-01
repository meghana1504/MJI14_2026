package DEV.a_JavaBasics.A2_GenericsInJava;

import java.util.ArrayList;

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
    String str = (String) list.get(0);
    Integer o = (Integer) list.get(1); // Manual casting reqd
    // String str2 = (String) list.get(2); // ClassCastException will occur at RT, Uncomment it

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
//    list1.add(10); // Add nahi krnne dega dusre types
//    list1.add(10.4);

     // But again, I will have to create different containers to hold different kind of objects
     // will lead to Code duplication

    /*
     * ===========================================================================
     * ----------- HOW DOES GENERICS SOLVE THESE PROBLEMS ? ----------
     * ===========================================================================
     * Checkout Box.java and GenericBox.java
     * */

        Box box = new Box();
        box.setValue(10); // I am storing integer in the box
        int val1 = (int) box.getValue();
        // String val1 = (String) box.getValue(); // By MISTAKE, I casted it to String
        System.out.println("Integer from Box class, needed casting " +val1); // It wont detect at compile time

    /*
    * Also this Box class can hold ONLY values with type Integer
    * Now I want a box which can hold float, String etc, will I write different classes for them? NO
    * I will now create a GenericBox
    * */
        GenericBox<Integer> genBoxInt = new GenericBox<>(90);
        // String integer = genBoxInt.getValue(); // Since we have mentioned that GenBox will hold INTEGER, This line will give error at COMPILE TIME ITSELF
        System.out.println("GenBox integer "+genBoxInt.getValue()); // Infact we dont even need the casting now since we have mentioned Integer

        GenericBox genBoxStr = new GenericBox<>("Meghana"); // EVEN if I dont mention <String> here, I wont get this error, I think Java handles it
        System.out.println("GenBox String "+genBoxStr.getValue());

    /*
     * ===========================================================================
     * ----------- ADVANTAGES OF GENERICS - SUMMARISED ? ----------
     * ===========================================================================
    * 1. Type casting blunders are caught at compile time, than at run time
    * 2. Do not need to do Manual Casting - where mistakes are possible
    * 3. One box or One list an hold multiple types of data, so no code duplication
    * 4. We dont even have to mention the type of object while creating an object of generic class
    *
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
     * - the JVM would need a completely different implementation to store raw primitive values. The original generics design avoided that complexity.
     *
    * */
    }
}
