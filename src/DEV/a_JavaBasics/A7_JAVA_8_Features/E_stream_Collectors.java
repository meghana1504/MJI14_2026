package DEV.a_JavaBasics.A7_JAVA_8_Features;

/*
===============================================================================
                            COLLECTORS (JAVA 8)
===============================================================================

WHAT IS A COLLECTOR?
--------------------
A Collector is a utility that performs mutable reduction on Stream elements.

It is used with the collect() Terminal Operation.

Syntax

stream.collect(Collectors.xxx());

collect()
---------
• Terminal Operation.
• Triggers Stream execution.

Collectors
----------
Utility class containing predefined Collector implementations.

===============================================================================
MOST IMPORTANT COLLECTORS
===============================================================================

1. toList()

Returns List<T>

2. toSet()

Returns Set<T>

Duplicates removed.

3. toMap()

Returns Map<K,V>

Key should be unique otherwise Duplicate Key Exception occurs.

4. joining()

Joins String elements.

5. counting()

Counts number of elements.

6. summingInt()

Returns sum.

7. averagingInt()

Returns average.

8. summarizingInt()

Returns count, sum, min, max, average together.

9. groupingBy()

Equivalent to SQL GROUP BY.

10. partitioningBy()

Splits elements into ONLY TWO groups.

Key type is Boolean.

11. mapping()

Used as downstream collector.

12. collectingAndThen()

Performs one more operation after collection.

13. maxBy()

Returns maximum element.

14. minBy()

Returns minimum element.

===============================================================================
*/

import java.util.*;
import java.util.stream.Collectors;

public class E_stream_Collectors {

    public static void main(String[] args) {

        List<String> names = Arrays.asList(
                "Java",
                "Python",
                "Java",
                "Spring",
                "Python",
                "Docker"
        );

        List<Integer> numbers =
                Arrays.asList(10,20,30,40,50,60,70,80,90);



        /******************************************************************
         * toList()
         ******************************************************************/

        /*
        Returns List.

        Java 16+
        --------
        Prefer stream().toList()
        when mutability is NOT required.
        */

        List<String> list =

                names.stream()
                        .collect(Collectors.toList());



        /******************************************************************
         * toSet()
         ******************************************************************/

        /*
        Removes duplicate elements.

        Returns Set.
        */

        Set<String> set =

                names.stream()
                        .collect(Collectors.toSet());



        /******************************************************************
         * toMap()
         ******************************************************************/

        /*
        Creates Map<Key, Value>

        Duplicate Keys
        --------------

        Throws IllegalStateException

        unless Merge Function is supplied.
        */

        Map<String,Integer> map =

                names.stream()
                        .distinct()
                        .collect(

                                Collectors.toMap(

                                        name -> name,

                                        String::length

                                )

                        );



        /******************************************************************
         * joining()
         ******************************************************************/

        /*
        Joins String elements.

        Supports

        delimiter

        prefix

        suffix
        */

        String joined =

                names.stream()

                        .distinct()

                        .collect(

                                Collectors.joining(", ")

                        );

        System.out.println(joined);



        /******************************************************************
         * counting()
         ******************************************************************/

        /*
        Counts elements.

        Equivalent to stream.count()

        but useful inside groupingBy().
        */

        Long count =

                names.stream()

                        .collect(Collectors.counting());

        System.out.println(count);



        /******************************************************************
         * summingInt()
         ******************************************************************/

        /*
        Returns sum of mapped values.
        */

        Integer sum =

                numbers.stream()

                        .collect(

                                Collectors.summingInt(

                                        Integer::intValue

                                )

                        );

        System.out.println(sum);



        /******************************************************************
         * averagingInt()
         ******************************************************************/

        /*
        Returns Average.
        */

        Double average =

                numbers.stream()

                        .collect(

                                Collectors.averagingInt(

                                        Integer::intValue

                                )

                        );

        System.out.println(average);



        /******************************************************************
         * summarizingInt()
         ******************************************************************/

        /*
        Returns IntSummaryStatistics

        Contains

        count

        sum

        min

        max

        average
        */

        IntSummaryStatistics stats =

                numbers.stream()

                        .collect(

                                Collectors.summarizingInt(

                                        Integer::intValue

                                )

                        );

        System.out.println(stats);



        /******************************************************************
         * groupingBy()
         ******************************************************************/

        /*
        MOST IMPORTANT COLLECTOR

        SQL Equivalent

            GROUP BY

        Groups elements based on a key.

        Returns

            Map<K,List<V>>

        Example

        Apple
        Ant
        Ball
        Bat

        ↓

        A=[Apple, Ant]

        B=[Ball, Bat]
        */

        Map<Character,List<String>> grouped =

                names.stream()

                        .collect(

                                Collectors.groupingBy(

                                        s -> s.charAt(0)

                                )

                        );

        System.out.println(grouped);



        /******************************************************************
         * groupingBy() + counting()
         ******************************************************************/

        /*
        Downstream Collector

        Count elements in each group.
        */

        Map<Character,Long> groupedCount =

                names.stream()

                        .collect(

                                Collectors.groupingBy(

                                        s -> s.charAt(0),

                                        Collectors.counting()

                                )

                        );

        System.out.println(groupedCount);



        /******************************************************************
         * mapping()
         ******************************************************************/

        /*
        Used WITH groupingBy().

        Transforms values inside each group.

        Very common interview question.
        */

        Map<Character,List<Integer>> groupedLengths =

                names.stream()

                        .collect(

                                Collectors.groupingBy(

                                        s -> s.charAt(0),

                                        Collectors.mapping(

                                                String::length,

                                                Collectors.toList()

                                        )

                                )

                        );

        System.out.println(groupedLengths);



        /******************************************************************
         * partitioningBy()
         ******************************************************************/

        /*
        Similar to groupingBy()

        Difference

        groupingBy()

            Multiple Groups

        partitioningBy()

            ONLY TWO groups

            true

            false

        Returns

            Map<Boolean,List<T>>
        */

        Map<Boolean,List<Integer>> partition =

                numbers.stream()

                        .collect(

                                Collectors.partitioningBy(

                                        i -> i >= 50

                                )

                        );

        System.out.println(partition);



        /******************************************************************
         * collectingAndThen()
         ******************************************************************/

        /*
        Performs one additional operation
        after collecting.

        Example

        Collect List

        ↓

        Make it Unmodifiable
        */

        List<String> immutableList =

                names.stream()

                        .collect(

                                Collectors.collectingAndThen(

                                        Collectors.toList(),

                                        Collections::unmodifiableList

                                )

                        );



        /******************************************************************
         * maxBy()
         ******************************************************************/

        /*
        Returns Optional<T>
        */

        Optional<Integer> max =

                numbers.stream()

                        .collect(

                                Collectors.maxBy(

                                        Integer::compareTo

                                )

                        );

        System.out.println(max.get());



        /******************************************************************
         * minBy()
         ******************************************************************/

        Optional<Integer> min =

                numbers.stream()

                        .collect(

                                Collectors.minBy(

                                        Integer::compareTo

                                )

                        );

        System.out.println(min.get());



        /******************************************************************
         *                  INTERVIEW NOTES
         ******************************************************************/

        /*
        collect() vs Collectors

        collect()

            Terminal Operation

        Collectors

            Utility class containing predefined collectors.



        groupingBy() vs partitioningBy()

        groupingBy()

            Multiple groups.

            Any Key Type.

        partitioningBy()

            Exactly 2 groups.

            Boolean key.



        toList() vs Collectors.toList()

        Java 16+

            stream().toList()

        Older Versions

            Collectors.toList()



        counting() vs count()

        count()

            Terminal Operation.

        counting()

            Collector.

            Mostly used inside groupingBy().



        summarizingInt()

        Better than calling

        sum()

        average()

        max()

        min()

        individually.



        mapping()

        Used inside groupingBy()

        to transform grouped values.



        collectingAndThen()

        Perform final transformation
        after collecting.



        MOST ASKED COLLECTORS

        ★★★★★ groupingBy()

        ★★★★★ partitioningBy()

        ★★★★★ toMap()

        ★★★★☆ joining()

        ★★★★☆ mapping()

        ★★★★☆ counting()

        ★★★★☆ summarizingInt()

        ★★★☆☆ collectingAndThen()

        ★★★☆☆ averagingInt()

        ★★★☆☆ summingInt()
        */

    }
}