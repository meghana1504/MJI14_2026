package DEV.a_JavaBasics.A7_JAVA_8_Features;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
===============================================================================
                            OPTIONAL (JAVA 8)
===============================================================================

WHY WAS OPTIONAL INTRODUCED?
----------------------------
Before Java 8, methods commonly returned null.

Example

String name = getName();

name.length();     // NullPointerException if name == null

Problems with null

• NullPointerException (Most Common Runtime Exception)
• Lots of null checks
• Less readable code

Java 8 introduced Optional to explicitly represent:

    "A value may or may not be present."

===============================================================================
WHAT IS OPTIONAL?
===============================================================================

Optional<T> is a final container class that may or may not contain a value.

Think of it as a wrapper around an object.

Instead of returning

    Employee

Return

    Optional<Employee>

===============================================================================
IMPORTANT POINTS
===============================================================================

✓ Prevents NullPointerException
✓ Makes APIs expressive
✓ Forces caller to think about missing values
✓ Used mainly as Return Type
✓ Immutable
✓ Final Class
✓ Supports Functional Programming
✓ Avoids explicit null checks

===============================================================================
HOW TO CREATE OPTIONAL
===============================================================================

1. Optional.of()
2. Optional.ofNullable()
3. Optional.empty()

===============================================================================
*/

public class G_Optional {

    public static void main(String[] args) {

        /******************************************************************
         * Optional.of()
         ******************************************************************/

        /*
        Creates Optional containing NON-NULL value.
        Throws NullPointerException if value is null.
        Because, this should be used only when we know that "This value must not be null", it should fail immediately, cause we dont want to hide the bug
        Use this when we are 100% certain that the value is not null
        */

        Optional<String> name = Optional.of("Vabs");
        System.out.println(name);

        /******************************************************************
         * Optional.ofNullable()
         ******************************************************************/

        /*
        Creates Optional.

        If value is null
            Empty Optional is returned.

        Preferred method when value may be null, and when that is acceptable.
        */

        String city = null;
        Optional<String> optionalCity = Optional.ofNullable(city);
        System.out.println(optionalCity);

        /******************************************************************
         * Optional.empty()
         ******************************************************************/

        /*
        Creates Empty Optional.
        */
        Optional<String> empty = Optional.empty();
        System.out.println(empty);

        /*
        * Example usage
        Optional<Employee> findEmployee(int id) {
            if(employeeFound)
                return Optional.of(employee);
            return Optional.empty();
        }
        *
        * Other examples are- Search operations, DB Query, Cache lookup etc
        *
        * WHY NOT SIMPLY RETURN null ??
        * ->
        *
        * We do not use Optional.empty() directly, optional.ofNullable() does it for us [check its definition]
        *
        * */
        /******************************************************************
         * isPresent()
         ******************************************************************/

        /*
        Returns true if value exists.
        Avoid excessive use.

        Better alternatives
            orElse()
            ifPresent()
            orElseGet()
        */

        if(name.isPresent()){
            System.out.println(name.get());
        }

        /*
        * WHY NOT null checks? and why Optional ?
        *
        * ADVANTAGE # 1 ----- EXAMPLE 1 --------
        * We have this method -
        *           Employee getEmployee(int id)
        * By looking at the signature, we dk if it will ALWAYS return an Employee, it CAN return null
        * if it returns null, and someone does -
        *           Employee emp = getEmployee(id); emp.getName() // NPE !!
        *
        * But.. with Optional..
        *           Optional<Employee> getEmployee(int id)
        * We immediately know that This method may or may not return an Employee
        *
        *
        * ADVANTAGE # 2 ----- EXAMPLE 2 --------
        * "CHAINING"
        * - We dont need to have nested null checks without Optional
        * - The moment any map() returns null in the chain, the Optional becomes empty, and the rest of the chain is skipped.
        *
        *
        * ADVANTAGE # 3 ----- EXAMPLE 3 ---------
        * " DEFAULT VALUES "
        * No need to do this :
                if(city != null)
                    return city;
                else
                    return "Pune";
        *
        * We can simply write :
        *       return optionalCity.orElse("Pune");
        *
        *
        * */
        /******************************************************************
         * get()
         ******************************************************************/
        /*
        Returns wrapped value.
        Throws NoSuchElementException if Optional is empty.
        Avoid calling get() without checking.
        */

        System.out.println(name.get());


        /******************************************************************
         * orElse()
         ******************************************************************/
        /*
        Returns wrapped value.
        Else returns default value.

        */
        System.out.println(optionalCity.orElse("Pune") );


        /******************************************************************
         * orElseGet()
         ******************************************************************/
        /*
        Similar to orElse()
        Difference
        Default value is generated lazily.
        Takes Supplier - It supplies the value whenever somebody asks
        */

        System.out.println( optionalCity.orElseGet(() -> "Mumbai"));

        /*
        * DIFFERENCE BETWEEN orElse() [Takes default argument directly] and orElseGet() [Takes a supplier]
        *
        * In case of orElse() --
        *       optional.orElse(createObject());
        * sequence of execution is - createObject() -> orElse() ALWAYS
        * Because Java evaluates method arguments before calling the method.
        * So even before caling orElse() it has evaluated its parameter createObject() and created that object- eg default user in DB
        *
        * But in case of orElseGet() --
        *       optional.orElseGet(() -> createObject());
        * Optional empty -> YES -> supplier.get() -> createObject()
        * Java passes the lambda, nothing is executed
        * Java internally does --
            if (valuePresent)
                return value;
            else
                return supplier.get();
        *
        *
        * Use orElse when default value is already available
        * But, if default value requires DB Call/ API Call/ Creating some object etc, use orElseGet()
        *
        * IN SHORT ------
        * orElse() eagerly evaluates its argument, even if the Optional contains a value.
        * orElseGet() takes a Supplier and invokes it only when the Optional is empty (lazy evaluation).
        *
        * */

        /******************************************************************
         * orElseThrow()
         ******************************************************************/

        /*
        Throws custom exception if value is absent, than returning any random city value.
        */
         optionalCity.orElseThrow( () -> new RuntimeException("City Missing") );

        /******************************************************************
         * ifPresent()
         ******************************************************************/

        /*
        Executes Consumer only if value exists.
        Eliminates explicit null check.
        defer execution until the Optional decides whether to invoke it.
        */
        name.ifPresent(System.out::println);

        /******************************************************************
         * ifPresentOrElse() (Java 9)
         ******************************************************************/

        /*
        if value present -
            Consumer
        else -
            Runnable

        Internally, it does ----
            if(optional.isPresent()) {
                consumer.accept(value);
            }
            else {
                runnable.run();
            }

        When the Optional is empty, there is no value to pass to a Consumer.
        The else action doesn't need any input, so a Runnable (whose run() method takes no arguments) is the appropriate functional interface.
        */

        /******************************************************************
         * map()
         ******************************************************************/

        /*
        Transforms wrapped value.

        Returns another Optional.
        */

        Optional<Integer> length = name.map(String::length);
        System.out.println(length);



        /******************************************************************
         * flatMap()
         ******************************************************************/

        /*
        Similar to Stream flatMap()
        Avoids Optional<Optional<T>>

        Used when mapping function
        itself returns Optional.
        */

        /******************************************************************
         * filter()
         ******************************************************************/

        /*
        Keeps value only if
        Predicate returns true.

        Else returns Empty Optional.
        */

        Optional<String> result =

                name.filter(

                        s -> s.startsWith("V")

                );

        System.out.println(result);



        /******************************************************************
         * STREAM + OPTIONAL
         ******************************************************************/

        /*
        Many Stream methods return Optional

        min()

        max()

        reduce()

        findFirst()

        findAny()

        because stream may be empty.
        */

        List<Integer> numbers =
                Arrays.asList(10,20,30,40);

        Optional<Integer> maximum =

                numbers.stream()
                        .max(Integer::compareTo);

        System.out.println(maximum.orElse(0));



        /******************************************************************
         * COMMON METHODS
         ******************************************************************/

        /*
        of()

        ofNullable()

        empty()

        isPresent()

        get()

        ifPresent()

        orElse()

        orElseGet()

        orElseThrow()

        map()

        flatMap()

        filter()
        */



        /******************************************************************
         * orElse() vs orElseGet()
         ******************************************************************/

        /*
        orElse()

        Default value is ALWAYS created.

        Even if Optional has value.


        orElseGet()

        Supplier executes ONLY if Optional is empty.

        Hence,

        Prefer orElseGet()
        when default object creation
        is expensive.
        */

        Optional<String> language =
                Optional.of("Java");

        System.out.println(

                language.orElse(createLanguage())

        );

        System.out.println(

                language.orElseGet(

                        G_Optional::createLanguage

                )

        );



        /******************************************************************
         * OPTIONAL vs NULL
         ******************************************************************/

        /*
        NULL
            • Unsafe
            • Can cause NPE
            • Caller may forget null check


        OPTIONAL
            • Safe
            • Forces handling of absence
            • Cleaner API
            • Self-documenting
        */



        /******************************************************************
         * BEST PRACTICES
         ******************************************************************/

        /*
        ✔ Use Optional mainly as Return Type.

        ✔ Prefer ofNullable() when value may be null.

        ✔ Avoid get() unless sure value exists.

        ✔ Prefer orElseGet() over orElse()
          for expensive default creation.

        ✔ Prefer ifPresent() instead of
          if(optional.isPresent()).

        ✔ Don't use Optional for fields.

        ✔ Don't use Optional as method parameter.

        ✔ Don't store Optional inside Collections.

        ✔ Don't serialize Optional.

        ✔ Avoid Optional<List<T>>

           Prefer

                List<T>

           returning empty list instead.
        */



        /******************************************************************
         * INTERVIEW QUESTIONS
         ******************************************************************/

        /*
        Q. Why Optional introduced?

        To reduce NullPointerException
        and make APIs expressive.


        Q. Difference between

        Optional.of()

        Optional.ofNullable()

        of()

            Doesn't accept null.

        ofNullable()

            Accepts null.


        Q. Difference between

        orElse()

        orElseGet()

        orElse()

            Default object always created.

        orElseGet()

            Created only if needed.


        Q. Difference between

        map()

        flatMap()

        map()

            Returns Optional<Optional<T>>
            if mapper returns Optional.

        flatMap()

            Returns Optional<T>


        Q. Is Optional Serializable?

        No.


        Q. Can Optional contain null?

        No.

        Empty Optional represents absence of value.


        Q. Is Optional immutable?

        Yes.


        Q. Where should Optional be used?

        Mainly as Return Type.
        */



        /******************************************************************
         * SUMMARY
         ******************************************************************/

        /*
        Creation

        of()

        ofNullable()

        empty()


        Retrieval

        get()

        orElse()

        orElseGet()

        orElseThrow()


        Conditional

        isPresent()

        ifPresent()

        filter()


        Transformation

        map()

        flatMap()
        */

    }

    static String createLanguage(){

        System.out.println("Creating Default Object...");

        return "Python";
    }

}