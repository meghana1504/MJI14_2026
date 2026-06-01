package DEV.a_JavaBasics.A5_JavaCollections;
// JUST NOTES

public class JustNotes {
// ==============================================================================================================================
//  ============= [ PART 1 ] - Collection hiearchy, Iterable, Iterator implementation in A0_GenericList class  ================
// ==============================================================================================================================

/*

[I] = Interface
[C] = Class
--E--> = extends
--I--> = implements

[I] Iterable --E--> [I] Collection
      |
      +-- iterator() --> [I] Iterator

[I] Collection --E--> [I] [LIST] --I--> [C] ArrayList | [C] LinkedList | [C] Vector --E--> [C] Stack | [C] CopyOnWriteArrayList
                           |
                           +-- listIterator() --> [I] ListIterator --E--> [I] Iterator

[I] Collection --E--> [I] [SET] --I--> [C] HashSet --E--> [C] LinkedHashSet
                           |
                           +--E--> [I] [SortedSet] --E--> [I] [NavigableSet] --I--> [C] TreeSet
                           |
                           +--I--> [C] EnumSet

[I] Collection --E--> [I] [QUEUE] --I--> [C] PriorityQueue
                             |
                             +--E--> [I] Deque --I--> [C] ArrayDeque | [C] LinkedList

===========================================================================================================================

[I] Map --I--> [C] HashMap --E--> [C] LinkedHashMap
        |
        +--E--> [I] [SortedMap] --E--> [I] [NavigableMap] --I--> [C] TreeMap
        |
        +--I--> [C] Hashtable --E--> [C] Properties
        |
        +--I--> [C] ConcurrentHashMap | [C] ConcurrentSkipListMap | [C] WeakHashMap | [C] IdentityHashMap | [C] EnumMap

* */

// Collection extends Iterable, and all these Interfaced extend Collection, hence they also have to implement Iterable interface
// What we have did in A0_GenericList

// ==============================================================================================================================
//  ============= [ PART 2 ] - List Interface, LL / AL / Vector  ================
// ==============================================================================================================================

    /*
    List Interface:
        ⚫ Lists are collections that maintain their elements in order and can contain
        duplicates.
        ⚫ The elements in a list are ordered.
        ⚫ Each element are position based, starting from index 0.
    List Methods:
        ⚫ E get(int index) -
        ⚫ E set(int index, E element) -
        ⚫ void add(int index, E element)
        ⚫ boolean addAll(int index, Collection<? extends E> c)
        ⚫ E remove(int index)

            ArrayList: Implements List Interface
                • ArrayList is a dynamic array.
                • To be used when we don't know what is going to be the size of the array.
                . Internally it uses a normal array, set to some default capacity.
                • When that capacity is reached, it will create a new array of bigger size (50% of its
                current capacity) and copies all the elements from the old array to the new array.
                • New array's reference for its internal usage.
                • As the old array is no longer in use, it will be garbage collected in the next
                garbage collection.

            Vector class: [THREAD-SAFE, So obv slower than ArrayList ]
                • The Vector class is a legacy class which implements the List interface.
                • The Vector and ArrayList classes are implemented using dynamically.
                • resizable arrays, providing fast random access (i.e., position-based access) and fast.
                • list traversal-very much like using an ordinary array.
                • Unlike the ArrayList class, the Vector class is thread-safe, meaning that
                concurrent calls to the vector will not compromise its integrity.
                • The ArrayList and Vector classes offer comparable performance, but Vectors
                 suffer a slight performance penalty due to synchronization.

            LinkedList:
                • The LinkedList implementation uses a doubly-linked list.
                . Insertions and deletions in a doubly-linked list are very efficient.

            AL v/s LL
                - Position based access in AL but Traverse whole thing in LL, for traversal AL is faster
                - Frequent insertions and deletions - LL is faster
                - LL also implements Deque
                - ArrayList hi use krte hai mostly

    * */

// ==============================================================================================================================
//  ============= [ PART 3 ] - DEMO Of ArrayList  ================
// ==============================================================================================================================

/*
*   Collections use generics → generics work only with reference types → primitives are not reference types → wrapper classes are required.
*   List<int> - Why it doesn't work ? Because it makes use of generics and generics ko compiler internally object me convert krta hai
*   Basically erases the type and convert it into Object references and then work with it, it can not do the same with primitives like int
*   Generics only allow reference types as parameters
*
*   al.add(no)
*   al.set(ind,newValue); - Returns the element that got replaced
*   List<Integer> alist2 = new ArrayList<>(alist); - Directly copy from one list to another, You can pass any collection above instead of alist
*   al1.addAll(al2) - Adds all elements from al2 to al1
*   al1.indexOf(int num) - Returns the index of num in al1
*   al1.lastIndexOf(int num) - Returns last index of num in al1
*   subAL = al1.sublist(1,4) - from inclusive, to exclusive, returns another AL -> It is a COPY OF REFERENCE- SHALLOW COPY
 *   subAL.set(0,100)
*   sout(al1) - it changes the original list -
*
*
* */

// ==============================================================================================================================
//  ============= [ PART 4 ] - List iterator Interface  ================
// ==============================================================================================================================
}
