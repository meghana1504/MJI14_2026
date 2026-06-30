package DEV.a_JavaBasics.A7_JAVA_8_Features;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class E_StreamExercises {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>(List.of(5,6,7,8,1,2,3,5,8,10,1));
        System.out.println("Demo of map and filter---- ");
        myList.stream()
                .map(n->n*n*n)
                .filter(cube-> cube>100)
                .forEach(System.out::println);

        List<List<Integer>> nestedList = new ArrayList<>(List.of(List.of(1,2),List.of(4,5)));
        System.out.println("Demo of flatMap and filter---- ");
        nestedList.stream()
                .flatMap(Collection::stream)
                .filter(no-> no>1)
                .forEach(System.out::println);

        // Stateful v/s stateless -
        // sorted() is stateful since it needs ALL elements AT ONCE, another example is distinct()
        // map() or filter() -> they are stateless, they can process one element at a time
        System.out.println("demo of sorted()+distinct() on myList ---- "+myList);
        myList.stream().sorted().distinct().forEach(n-> System.out.print(n+" "));

        System.out.println("\nDemo of limit() and skip() methods with infinite stream - Printing powers of 5 ");
        Stream.iterate(1,n->n*5)
                .limit(10)
                .skip(2)
                .forEach(i-> System.out.print(i+" "));

        System.out.println("\nCONVERSION FROM PRIMITIVE TO OBJECT STREAM AND VICE-A-VERSA");
        Stream<Integer> s1 = Stream.of(1,2,3,4,5);
        IntStream intS1 = s1.mapToInt(n->n);
        Stream<Integer> s2 = intS1.boxed().peek(n-> System.out.print(n+" ")).map(num->num*num);
        s2.forEach(n-> System.out.print(n+" "));

        System.out.println("mapToInt, mapToDouble, mapToLong - these types are there ");

        System.out.println("....... TERMINAL OPERATIONS ....... ");
        System.out.println("TYPE 1 - Collection of elements - toList(), collect()");
        /*
        * - toList() - when we use toList(), the resultant list will be IMMUTABLE
        * but..
        * - .collect(Collectors.toList()) - when I use this, resultant list is MUTABLE,
        *
        * */
        System.out.println("TYPE 2 - Reducing of elements to one answer - reduce(), sum(), max(), min(), count(), average() etc ");
        /*
        * - reduce() - takes BinaryOperator which extends BinaryFunction
        * - reduce((a,b)->(a+b))
        * - reduce accepts identity (base value to start operation - 0 for addition, 1 for mutliplication) as first parameter, which can be used to make it return primitives
        * - count() - obvious  - returns long
        *
        *
        * */
        System.out.println("TYPE 3 - Searching/ Matching - findFirst(), findAny(), anyMatch(), allMatch(), noneMatch() ");
        /*
        * findFirst() - short circuit krta hai, returns Optional
        * */
        System.out.println("TYPE 4 - Iterations - forEach(), forEachOrdered() - for parallel streams ");
        /*
        * see the clear difference b/w forEach and forEachOrdered() when using parallelStream
        * */




    }

}
