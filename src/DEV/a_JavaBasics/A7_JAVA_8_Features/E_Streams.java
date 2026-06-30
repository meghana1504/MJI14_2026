package DEV.a_JavaBasics.A7_JAVA_8_Features;


import java.util.*;
import java.util.stream.*;

/*
================================================================================
                            STREAM API (JAVA 8)
================================================================================

WHAT IS A STREAM?
-----------------
    • Stream is NOT a data structure.
    • It is a sequence of elements that supports functional-style operations.
    • Think of it as a pipeline through which collection elements flow.
    • Used for processing data, NOT storing data.
    • Original Collection is NEVER modified.
    • Mainly useful for bulk processing of data.
    • Supports both Sequential and Parallel Processing.

        Collection
            ↓
          Stream
            ↓
        Intermediate Operations (0 or More)
            ↓
        Terminal Operation (Exactly One)
            ↓
          Result


WHY STREAM API?
---------------
    Before Java 8, processing collections required lots of loops and temporary collections.

Streams provide
    ✔ Less Boilerplate
    ✔ Functional Programming
    ✔ Better Readability
    ✔ Easy Bulk Processing
    ✔ Parallel Processing Support

COLLECTION vs STREAM
--------------------

    Collection
    ----------
        • Stores data
        • Can add/remove elements
        • External Iteration
        • Can be traversed multiple times
        • Eager

    Stream
    ------
        • Doesn't store data
        • Read-only view of data - because, Every intermediate operation returns a NEW Stream object. It does NOT modify the previous Stream.
        • Internal Iteration
        • One-time use
        • Lazy
        • Doesn't modify source collection


STREAM PIPELINE
---------------

    Source
       ↓
    Open Stream
       ↓
    Intermediate Operations (0 or More)
       ↓
    Terminal Operation (Exactly One)

Example

    list.stream()
        .filter(...)
        .map(...)
        .sorted(...)
        .collect(...);


LAZY EVALUATION
---------------
    Intermediate Operations are Lazy.

    They DO NOT execute immediately.
    They only create a processing pipeline.

    Execution starts ONLY when a Terminal Operation is called.

Example

    list.stream()
        .filter(...)
        .map(...);
    Nothing executes.

    Only after
        .collect(...)
        .forEach(...)
        .count()
    the pipeline starts processing.


STREAM CHARACTERISTICS
----------------------
    ✔ Doesn't store data
    ✔ Doesn't modify original collection
    ✔ Lazy
    ✔ Internal Iteration
    ✔ One-time use
    ✔ Supports Parallel Processing
    ✔ Can process Infinite Streams

INTERNAL ITERATION
------------------
    Collection
    for(...)
    while(...)
    Programmer controls iteration.

    Stream
    stream.filter(...)
          .map(...)
          .forEach(...)
    JVM controls iteration.

HOW MANY TIMES CAN A STREAM BE USED?
------------------------------------

    Only ONCE.
    Once a Terminal Operation is executed,
    the stream is CLOSED.

    Trying to reuse it throws IllegalStateException

STREAM HIERARCHY
------------------------------------

                            -----------> Stream
                            -----------> IntStream
Autoclosable-> BaseStream<T> -----------> LongStream
                            -----------> DoubleStream

This hierarchy makes sure the autoclosing of Streams
because all kind of streams override the method close()

===============================================================================
                     DIFFERENT WAYS TO CREATE STREAMS
===============================================================================

    1. Collection.stream()
    2. Collection.parallelStream()
    3. Arrays.stream()
    4. Stream.of()
    5. Stream.builder()
    6. Stream.generate()
    7. Stream.iterate()
    8. Stream.empty()

===============================================================================
                    INTERMEDIATE OPERATIONS (LAZY)
===============================================================================

Intermediate Operations
    • Return another Stream.
    • Lazy in nature.
    • Executed only after Terminal Operation.
    • Can be chained.

    filter()
    map()
    flatMap()
    distinct()
    sorted()
    peek()
    limit()
    skip()
    mapToInt()
    mapToLong()
    mapToDouble()


STATELESS vs STATEFUL OPERATIONS
--------------------------------

Stateless
----------
    • filter()
    • map()
    • peek()
    Each element is processed independently.

Stateful
---------
    • sorted()
    • distinct()
    Need information about multiple/all elements before producing result.

*/

public class E_Streams {

    public static void main(String[] args) {

        /******************************************************************
         *                      STREAM CREATION
         ******************************************************************/

        // [1] From Collection
        List<String> names = Arrays.asList("Manisha", "Vabs", "Vivek", "Megh");
        Stream<String> namesStream = names.stream();

        // parallelStream() -> Opens Parallel Stream
        Stream<String> parallelNames = names.parallelStream();

        // [2] From Array
        Float[] numbers = {3.4f, 6.7f, 8.9f, 9.0f, 9.1f};
        Stream<Float> arrayStream = Arrays.stream(numbers);

        // [3] Stream.of()
        // Creates Stream from given elements.
        Stream<Integer> streamOf = Stream.of(1, 2, 3, 4, 5);

        // [4] Stream.builder()
        Stream<Integer> builderStream = Stream.<Integer>builder()
                                                        .add(10)
                                                        .add(20)
                                                        .add(30)
                                                        .build();

        // [5] Stream.generate()
        // Generates Infinite Stream.
        // Usually used with limit().

        Stream.generate(() -> "Java")
                .limit(3)
                .forEach(System.out::println);

        // [6] Stream.iterate()
        // Generates Infinite Stream.

        Stream.iterate(1, n -> n + 1)
                .limit(5)
                .forEach(System.out::println);

        // [7] Empty Stream
        Stream<String> empty = Stream.empty();

        /******************************************************************
         *                 INTERMEDIATE OPERATIONS
         ******************************************************************/

        List<Integer> rollNos = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        /*
        filter()
        --------

        Purpose
            Filters elements based on condition.

        Takes
            Predicate<T>

        Returns
            Stream<T>
        */

        List<Integer> filteredRolls =
                rollNos.stream()
                        .filter(i -> i > 3)
                        .collect(Collectors.toList());

        System.out.println(filteredRolls);


        /*
        map()
        -----

        Purpose
            Transforms each element.

        Takes
            Function<T,R>

        Examples

        Integer -> Square

        Employee -> Name

        String -> Length
        */

        System.out.println(

                filteredRolls.stream()
                        .map(i -> i * i)
                        .filter(i -> i > 60)
                        .collect(Collectors.toList())

        );


        /*
        flatMap()

        map()
            List<List<String>>
                    ↓
            Stream<List<String>>

        flatMap()
            List<List<String>>
                    ↓
            Stream<String>

        Used for flattening nested collections.
        */

        int[][] arr2d = {
                {1, 2, 3},
                {6, 7, 8},
                {9, 5, 4}
        };

        List<int[]> listOfArray = Arrays.asList(arr2d);

        listOfArray.stream()
                .flatMapToInt(Arrays::stream)
                .forEach(System.out::println);


        List<List<String>> listOfLists =
                Arrays.asList(
                        Arrays.asList("Geeks", "For"),
                        Arrays.asList("GeeksForGeeks", "Computer Portal"),
                        Arrays.asList("Java", "Programming")
                );

        listOfLists.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        /*
        distinct()

        Removes duplicate elements.

        Uses equals() and hashCode()
        */

        List<Integer> pointers =Arrays.asList( 9, 10, 8, 7, 8, 6, 9);

        List<Integer> distinctPointers =pointers.stream()
                                                .distinct()
                                                .map(i -> i * 100)
                                                .collect(Collectors.toList()); //only toList() can be written here
        System.out.println(distinctPointers);

        /*
        sorted()

        Sorts stream elements.

        Default
            Natural Ordering

        Custom
            Comparator
        */

        pointers.stream()
                .sorted()
                .forEach(System.out::println);


        /*
        peek()

        Mainly used for debugging.
        Helps inspect intermediate results.
        Avoid using it for business logic.
        */

        Integer max =distinctPointers.stream()
                        //.peek(System.out::println)
                        .max(Integer::compareTo)
                        .get();

        System.out.println(max);

        /*
        limit()
        Returns first N elements.
        */

        rollNos.stream()
                .limit(5)
                .forEach(System.out::println);


        /*
        skip()

        Skips first N elements.
        */

        rollNos.stream()
                .skip(5)
                .forEach(System.out::println);


        /*
        mapToInt()
        mapToLong()
        mapToDouble()

        Used to work with Primitive Streams.
        Avoids Boxing/Unboxing overhead.
        Gives methods like

        sum()
        average()
        max()
        min()
        */

        int sum = rollNos.stream()
                        .mapToInt(Integer::intValue) // (n->n) also works
                        .sum();

        System.out.println(sum);

        /*
         *****************************************************************
         *          STREAM EXECUTION ORDER (VERY IMPORTANT)
         ******************************************************************

         Streams process ONE element completely before moving to the next.

         Example

         stream
         .filter(...)
         .map(...)
         .forEach(...)

         Execution

         Element1
         ↓
         filter
         ↓
         map
         ↓
         forEach

         THEN

         Element2

         NOT

         filter all
         ↓
         map all

         Exception - ALL stateful Operations are exceptions, because they need to know about All the elements

         sorted()
         distinct()

         need multiple/all elements before producing output.
         ******************************************************************/

        /******************************************************************
         *                  TERMINAL OPERATIONS
         ******************************************************************/

        /*
        Terminal Operations

        • Trigger processing of the stream.
        • Return a result or produce a side effect.
        • After a Terminal Operation, the stream is CLOSED.

        Common Terminal Operations

        forEach()
        collect()
        reduce()
        count()
        min()
        max()
        toArray()
        findFirst()
        findAny()
        anyMatch()
        allMatch()
        noneMatch()
        */

        /*
        forEach()

        Performs an action for every element.

        Returns : void
        */

        rollNos.stream()
                .filter(i -> i > 10)
                .forEach(System.out::println);


        /*
        collect()

        Collects Stream elements into another data structure.

        Most Common

        Collectors.toList()
        Collectors.toSet()
        Collectors.toMap()

        Java 16+

        .toList()
        */

        List<Integer> collectedList = rollNos.stream()
                                                .filter(i -> i % 2 == 0)
                                                .collect(Collectors.toList());


        /*
        reduce()

        Combines all elements into ONE value.

        Used for

        • Sum
        • Product
        • Maximum
        • Minimum
        • String Concatenation

        Returns

        Optional<T>

        OR

        T (if Identity value supplied)

        Associative Operation

        (a+b)+c == a+(b+c)
        */

        Optional<Integer> reducedSum = rollNos.stream().reduce(Integer::sum);
        System.out.println(reducedSum.get());

        /*
        count()

        Returns number of elements.
        */

        long count = rollNos.stream()
                            .filter(i -> i > 5)
                            .count();

        System.out.println(count);



        /*
        min() / max()

        Returns Optional<T>

        Why Optional?

        Stream may be empty.
        */

        Integer minimum = rollNos.stream()
                                 .min(Integer::compareTo)
                                 .get();

        Integer maximum = rollNos.stream()
                        .max(Integer::compareTo)
                        .get();

        System.out.println(minimum);
        System.out.println(maximum);



        /*
        toArray()
        Converts Stream into Array.
        */

        Object[] array = rollNos.stream().toArray();

        /*
        anyMatch()
        Returns true if ANY element satisfies condition.
        */
        boolean any = rollNos.stream().anyMatch(i -> i > 10);

        /*
        allMatch()
        Returns true if ALL elements satisfy condition.
        */

        boolean all = rollNos.stream().allMatch(i -> i > 0);

        /*
        noneMatch()
        Returns true if NO element satisfies condition.
        */

        boolean none = rollNos.stream().noneMatch(i -> i < 0);

        System.out.println(any);
        System.out.println(all);
        System.out.println(none);

        /*
        findFirst()
        Returns first element. Ordered Streams
        ----------------
        Predictable result.
        */

        Optional<Integer> first = rollNos.stream().findFirst();
        System.out.println(first.get());

        /*
        findAny()

        Returns any element.

        Mainly useful with Parallel Streams.

        Sequential Stream
            Usually behaves like findFirst()

        Parallel Stream
            May return any matching element.
        */

        Optional<Integer> anyElement = rollNos.parallelStream().findAny();
        System.out.println(anyElement.get());

        /******************************************************************
         *                     OPTIONAL WITH STREAMS
         ******************************************************************/

        /*
        Methods like

        min()
        max()
        reduce()
        findFirst()
        findAny()

        return Optional because stream may be EMPTY.

        Common Methods

        get()

        isPresent()

        orElse()

        orElseGet()

        orElseThrow()
        */



        /******************************************************************
         *                  PRIMITIVE STREAMS
         ******************************************************************/

        /*
        Primitive Streams

        IntStream

        LongStream

        DoubleStream

        Advantages

        ✔ No Boxing / Unboxing

        ✔ Faster

        ✔ sum()

        ✔ average()

        ✔ min()

        ✔ max()
        */

        int primitiveSum =

                rollNos.stream()
                        .mapToInt(Integer::intValue)
                        .sum();

        System.out.println(primitiveSum);

        /******************************************************************
         *  CONVERSION FROM PRIMITIVE TO OBJECT STREAM AND VICE-A-VERSA
         ******************************************************************/
        System.out.println("CONVERSION FROM PRIMITIVE TO OBJECT STREAM AND VICE-A-VERSA");
        Stream<Integer> s1 = Stream.of(1,2,3,4,5);
        IntStream intS1 = s1.mapToInt(n->n);
        Stream<Integer> s2 = intS1.boxed().peek(n-> System.out.print(n+" ")).map(num->num*num);
        s2.forEach(n-> System.out.print(n+" "));




        /******************************************************************
         *                  PARALLEL STREAM
         ******************************************************************/

        /*
        parallelStream()

        Executes operations using multiple threads.

        Internally uses

        ForkJoinPool.commonPool()

        Working

        Collection

            ↓

        Spliterator

            ↓

        trySplit()

            ↓

        Smaller Chunks

            ↓

        Fork Join Threads

            ↓

        Combined Result

        Advantages

        ✔ Large Data

        ✔ CPU Intensive Tasks

        Avoid

        ✘ Small Collections

        ✘ IO Operations

        ✘ When order is important
        */

        rollNos.parallelStream()
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);



        /******************************************************************
         *              IMPORTANT INTERVIEW POINTS
         ******************************************************************/

        /*
        Q. Does Stream modify Collection?

        No.


        Q. Can Stream be reused?

        No.


        Q. Why are Intermediate Operations Lazy?

        To avoid unnecessary computation and improve performance.


        Q. Difference between map() and flatMap()?

        map()

            One object -> One object

        flatMap()

            One object -> Multiple objects
            (Flattens nested structures)


        Q. Difference between forEach() and peek()?

        forEach()

            Terminal Operation

        peek()

            Intermediate Operation
            Mainly for debugging.


        Q. Difference between reduce() and collect()?

        reduce()

            Reduces to ONE value.

        collect()

            Collects into Collection/Map/String etc.


        Q. Difference between findFirst() and findAny()?

        findFirst()

            Returns first element.

        findAny()

            Returns any element.
            Better suited for Parallel Streams.


        Q. Difference between Stream and Collection?

        Collection stores data.

        Stream processes data.


        Q. Why Primitive Streams?

        Avoid Boxing/Unboxing overhead.
        */



        /******************************************************************
         *                  BEST PRACTICES
         ******************************************************************/

        /*
        ✔ Prefer Stream when processing collections.

        ✔ Prefer Method References whenever possible.

        ✔ Use Primitive Streams for numeric operations.

        ✔ Don't use peek() for business logic.

        ✔ Don't reuse a Stream after Terminal Operation.

        ✔ Prefer .toList() (Java 16+) over Collectors.toList()
           when a mutable list is not required.

        ✔ Avoid Parallel Stream unless performance is actually needed.
        */



        /******************************************************************
         *                      SUMMARY
         ******************************************************************/

        /*
        Stream Lifecycle

        Source
            ↓
        Open Stream
            ↓
        Intermediate Operations (Lazy)
            ↓
        Terminal Operation
            ↓
        Stream Closed


        Intermediate Operations

        filter()
        map()
        flatMap()
        distinct()
        sorted()
        peek()
        limit()
        skip()
        mapToInt()
        mapToLong()
        mapToDouble()


        Terminal Operations

        forEach()
        collect()
        reduce()
        count()
        min()
        max()
        toArray()
        findFirst()
        findAny()
        anyMatch()
        allMatch()
        noneMatch()
        */

        /*
        * SHORT CIRCUITING is enabled due to lazy evalution and vertical processing
        * findFirst() hai for eg apna terminal op - findfirst() zalyavr baki elenments wont be procssed- similar to break statement in imperative code
        * hence short circuiting boltaat
        *
        * */
    }
}