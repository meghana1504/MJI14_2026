package DEV.a_JavaBasics.A5_JavaCollections;
// JUST NOTES

import java.lang.reflect.Array;
import java.util.*;

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
/*
* ListIterator interface extends Iterator interface
* It obv has hasNext() and next() methods
* Additionally it also has : hasPrevious() and previous() method
* • List provides two methods
    • ListIterator<E> listIterator()
    • ListIterator<E> listIterator(int index)•
* ListIterator is a bidirectional iterator for lists - eg; LinkedList uses doubly linked list
*   next -> return items [index ++];
    prev -> return items [-- index];
* */

public static void main(String[] args) {
// ==============================================================================================================================
//  ============= [ PART 5 ] - About ListIterator class  ================
// ==============================================================================================================================
    List<Integer> lk = new LinkedList<>();
    lk.add(1);
    lk.add(2);
    lk.add(3);
    // [1,2,3]
//    * ListIterator is a bidirectional iterator for lists - eg; LinkedList uses doubly linked list
//      next -> return items [index ++];
//      prev -> return items [-- index];
    System.out.println("\nDemo of ListIterator's previous() and hasPrevious() ");

    ListIterator<Integer> iterator = lk.listIterator();
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.hasPrevious());
    System.out.println(iterator.previous());

    // IF you want to convert your ArrayList to Array of Integers
    List<Integer> alist = new ArrayList<>();
    alist.add(1);
    alist.add(2);
    alist.add(3);
    alist.add(2);

    Integer[] arr = alist.toArray(new Integer[0]);
    System.out.println("\nUsage of toArray method to convert ArrayList to Integer[] directly");
    for (int x : arr)
        System.out.print(x + ",");


// ==============================================================================================================================
//  ============= [ PART 6 ] - Queue interface, Deque interface and Stack class  ================
// ==============================================================================================================================
    /*
    *
    | Method Purpose  | throws exception     | Doesnt throw Exc (Better)     |
    | --------------- | -------------------- | ----------------------------- |
    | To add elements | boolean add(E e)     | boolean offer (E e)           |
    | Remove elements | E remove()           | E poll()                      |
    | Get top element | E element()          | E peek()                      |
    *
    *
    * [I] Collection --E--> [I] [QUEUE] --Implements--> [C] PriorityQueue
                                 |
                                 +--Extends--> [I] Deque --Implements--> [C] ArrayDeque | [C] LinkedList
    *
    *
    * Queue - FIFO DS
    *
    * Deque has same methods as Queue
    * To Implement Stack = Use Stack class - LIFO
    * To implement Queue = Use LinkedList  - FIFO
    * To implement Double ended Queue = Use ArrayDeque, (even for Stack)
    * Both classes implement Deque
     *
    * */
    System.out.println("\n\nFIFO Queue implementation using LinkedList class which implements Queue interface");
    Queue<Integer> q = new LinkedList<>();
    q.offer(10);
    q.offer(3);
    q.offer(54);
    System.out.println(q);
    System.out.println("q.peek() "+q.peek());
    q.poll();
    System.out.println("q.peek() "+q.peek());
    System.out.println("q.isEmpty() "+q.isEmpty());

    System.out.println("Stack example using Stack class");
    Stack<Integer> s = new Stack<>();
    s.push(9);
    s.push(11);
    while(!s.isEmpty()){
        System.out.println("s.peek() "+s.peek()); // Effectively calls Vector ka  elementAt(index); method with index = len - 1
        s.pop();
//        System.out.println("s.pop() "+s.pop()); // Effectively calls Vector ka removeElementAt(index) method with index = len-1
    }
    System.out.println(s);

    System.out.println("Deque interface using ArraysDeque class");
    // Using "First-First" for FILO approach
    System.out.println("A queue behaving like a Stack - FILO / LIFO ");
    Deque<Integer> dq = new ArrayDeque<>();
    dq.offerFirst(76);
    dq.offerFirst(56);
    dq.offerFirst(86);
    dq.offerFirst(96);
    System.out.println(dq);
    dq.pollFirst();
    System.out.println(dq);

    // Using "First-Last / Last-First" for FIFO approach
    System.out.println("A queue behaving like a Queue - FIFO / LILO ");
    Deque<Integer> dq2 = new ArrayDeque<>();
    dq2.offerFirst(76);
    dq2.offerFirst(56);
    dq2.offerFirst(86);
    dq2.offerFirst(96);
    System.out.println(dq2);
    dq2.pollLast();
    System.out.println(dq2);

// ==============================================================================================================================
//  ============= [ PART 7 ] - Priority Queues, Comparable, Comparator  ================ [See Also : // PART 7 EXTRAS START ]
// ==============================================================================================================================
/*
* Priority Queue - Elements ke prio ke hisaab se elements are arranged, and not using the insertion order of the elements like in a normal queue
* Impl based on Prio Heap
* Priority defined either natural order of elements OR using the comparator provided
* It is not a good idea to use iterators to traverse a Prio queue even though Collection hierarchy allows us to do so - why because u will just traverse the tree and wont get elements in prio order
*
* */

    System.out.println("\nPriority Queue of <Integer> - System wrapper class USES COMPARATOR ");
    Queue<Integer> pq = new PriorityQueue<>(new nosComparator());
    pq.offer(1);
    pq.offer(2);
    pq.offer(0);
    pq.offer(100);
    System.out.println("I want to get top 2 elements ");
    System.out.println("Entire Prio Queue "+pq);
    List<Integer> top2 = new ArrayList<>();
    int ind=0;
    while (!pq.isEmpty()){
        if (ind==2)
            break;
        top2.add(pq.poll());
        ind++;
    }
    System.out.println("Top 2 elements "+top2);
    System.out.println("Remaining Prio Queue "+pq);

    // At this point check out the B0_StudentMarks class
    // TODO : Get me the top k students, where k=3, according to their Maths score
    System.out.println("\nGet me the top k students, where k=3, according to their Maths score - CUSTOM CLASS USE COMPARABLE");
    List<B0_StudentMarks> stMarks = new ArrayList<>();
    stMarks.add(new B0_StudentMarks(90,80));
    stMarks.add(new B0_StudentMarks(78,87));
    stMarks.add(new B0_StudentMarks(98,60));
    stMarks.add(new B0_StudentMarks(69,81));
    stMarks.add(new B0_StudentMarks(87,55));

    List<B0_StudentMarks> top3 = new ArrayList<>();

//    stMarks.sort(new marksComparator());
//    stMarks.sort((o1,o2)->o1.getMaths()-o2.getMaths());
//    System.out.println(stMarks);
//    stMarks.sort(null);
//    System.out.println(stMarks);

//    Queue<B0_StudentMarks> mkQ = new PriorityQueue<>(stMarks);
    Queue<B0_StudentMarks> mkQ = new PriorityQueue<>(new marksComparator());
    for(B0_StudentMarks sm: stMarks)
        mkQ.offer(sm);

    // [NOTE] : We are not calling "SORT" Method on the list 'stMarks" explicitly
    // BUT, since mkQ is a prio queue, first, it sorts the elements it is taking as input (which is stMarks list here) by calling compareTo() internally,
    // OTOH, If we pass marksComparator to PrioQueue and add elements later, it will prefer Total ordering (Sort using Physics score) over Natural ordering(Sort using maths score)
    int ind1=0;
    while (!mkQ.isEmpty()){
        if (ind1==3)
            break;
        top3.add(mkQ.poll());
        ind1++;
    }
    System.out.println("top 3 Maths scorers "+ top3);

// ====================================================================================================================================
//  ============= [ PART 8  ] - Sets and its implementations - HashSet, LinkedHashSet, hashcode and equals importance  ================
// ====================================================================================================================================
/*
* [I] Collection --E--> [I] [SET] --I--> [C] HashSet --E--> [C] LinkedHashSet
                           |
                           +--E--> [I] [SortedSet] --E--> [I] [NavigableSet] --I--> [C] TreeSet
                           |
                           +--I--> [C] EnumSet
*
* .CONTAINS(E) - true or false
* Methods that SET interface provides --> Mostly Mathematical set operations
    • a.containsAll(b) (subset) ------- boolean - Returns true if this set contains all the elements of the specified collection.
                                                  If the specified collection is also a set, this method returns true if it is a subset of this set.
    • a.addAll(b) (union) ------------- boolean - Adds all of the elements in the specified collection to this set if they're not already present
    • a.removeAll(b) (difference) ----- boolean - Removes from this set all of its elements that are contained in the specified collection
    • a.retainAll(b) (intersection) --- boolean - Retains only the elements in this set that are contained in the specified collection
    • a.clear() (empty set) ----------- void ---- Removes all of the elements from this set, set becomes empty
*
* */

    System.out.println("\nPerforming set operations... ");
    Set<Integer> set1 = new HashSet<>();
    set1.add(1);
    set1.add(3);
    set1.add(2);
    set1.add(5);

    Set<Integer> set2 = new HashSet<>();
    set2.add(11);
    set2.add(3);
    set2.add(22);
    set2.add(50);
    set2.add(5);

    System.out.println(set1 +" " + set2);
    System.out.println("set1.containsAll(set2)> "+set1.containsAll(set2)+ "" + set1 + set2 );
    System.out.println("set1.addAll(set2)> "+set1.addAll(set2) + set1 + set2);
    System.out.println("set1.removeAll(set2)> "+set1.removeAll(set2)+ set1+set2);
    System.out.println("set1.retainAll(set2)> "+set1.retainAll(set2)+ set1+set2);
    set1.clear();
    System.out.println("Set1: "+set1+ "set2: "+set2);

    System.out.println("\nNormal set does not maintain sorting order .. ");
    Set<Integer> set = new HashSet<>();
    set.add(10);
    set.add(30);
    set.add(20);
    set.add(10);
    set.add(90);
    System.out.println(set);
//    set.remove(10);
//    System.out.println(set);

    for(int x: set)
        System.out.print(x+" ");

    System.out.println("\nUse hashset when insertion order does not matter for you ");

    // LinkedHashSet is used when we want to preserve the order of insertion
    // HashSet which maintains the linkedList BTS
    // SLIGHTLY slower than HashSet

    System.out.println("\nLinkedHashSet maintains sorting order .. ");
    Set<Integer> lhset = new LinkedHashSet<>();
    lhset.add(10);
    lhset.add(30);
    lhset.add(20);
    lhset.add(10);
    lhset.add(90);
    System.out.println(lhset);

    for(int x: lhset)
        System.out.print(x+" ");

    System.out.println("\n\nNow we will make set of B0_StudentMarks and see what happens");
    Set<B0_StudentMarks> smset = new HashSet<>(stMarks);
    smset.add(new B0_StudentMarks(90,80)); // This entry is already present in the set, but it will get added AGAIN if we do not override hashcode and equals in class StudentMarks
    System.out.println(smset);
    System.out.println("Check if B0_StudentMarks(90,80) is present in smset "+smset.contains(new B0_StudentMarks(90,80)));
    // Although the object with SAME values is present in our set, it returns FALSE, why? hashcode() thing
    /*
    * When we insert element into a HashSet, hashcode of that element is generated, it gets mapped to a bucket
    * Checks elements from the buckets, (LL or BBT),
    * We have to override hashcode() method for the class whose objects which we are comparing, otherwise it generates new hashcode for every object every time
    * Because when we want to add new B0_StudentMarks(90,80) to our hashset, it treats it as a new object and it does not match with any current object, so it thinks it is unique
    * Set ko kaise pta chalega do objects same hai,? uske liye equals and hashcode override krna hi pdega
    * First checks hashcode, then equals
    *
    * See again class : B0_StudentMarks : equals() and override() methods
    *
    * */

// ====================================================================================================================================
//  ============= [ PART 9 ] - SortedSet Interface, NavigableSet Interface , TreeSet ================
// ====================================================================================================================================

    /*
    * // SortedSet Interface //
    * Balanced binary serch tree is used BTS
    * Eelements will be sorted with natural or Total ordering (if passed a comparator)
    * E first()
    * E last()
    *
    *
    * // NavigableSet Interface //
    • Extends the SortedSet interface with navigation methods to find the closest
    matches for specific search targets.
    • By navigation, we mean operations that require searching for elements in the
    navigable set.
    • In the absence of elements, these operations return null rather than throw a
    NoSuchElementException.
    • In addition to the methods of the SortedSet interface, the Navigable Set
    interface adds the following new methods:
    // First-last elements          // Closest-matches
    • E pollFirst()                 • E ceiling(E e) • E higher(E e)
    • E pollLast()                  • E floor(E e) • E lower(E e)
    *
    * USED IN INTERVAL Problems
    *
    *
    * */

    /*
    ---------- Treeset ----------
    * - Naturally adds elements in the sorted order
    * - Just like the Priority Queue
    * - It also calls B0_StudentMarks::CompareTo(), while the elements are getting added
    * - Agar nahi hota defined to classCastException, obviously
    * - Hence it is sorted on maths score
    *
    * */
    System.out.println("\nTreeSet starts");
    Set<B0_StudentMarks> tst = new TreeSet<>(stMarks); // we can also pass comparator to sort based on physics score
    System.out.println("\nTreeset prints elements in a sorted order ");
    System.out.println(tst);

    System.out.println("\nCreating a Treeset of Integer > ");
    NavigableSet<Integer> set4 = new TreeSet<>();
    set4.add(9);
    set4.add(8);
    set4.add(3);
    set4.add(2);
    set4.add(5);
    System.out.println("Set4: "+set4);
    System.out.println("set4.floor(4) "+set4.floor(4)); // floor of 4, element just less than or equal to 4
    System.out.println("set4.ceiling(4) "+set4.ceiling(4)); // floor of 4, element just greater than or equal to 4
    System.out.println("set4.lower(4) "+set4.lower(4)); // element strictly next lower than 4
    System.out.println("set4.higher(4) "+set4.higher(4)); // element strictly next higher than 4

// ====================================================================================================================================
//  ============= [ PART 10 ] - Map interface ================
// ====================================================================================================================================

    /*
    [I] Map --I--> [C] HashMap --E--> [C] LinkedHashMap
        |
        +--E--> [I] [SortedMap]
                        --E--> [I] [NavigableMap] --I--> [C] TreeMap
        |
        +------------------------I--> [C] Hashtable --E--> [C] Properties
        |
        +--I--> [C] ConcurrentHashMap | [C] ConcurrentSkipListMap | [C] WeakHashMap | [C] IdentityHashMap | [C] EnumMap

    - Map.Entry interface hai for Map traversal
    - Map has <Key,Value> pairs,
    - Duplicate keys are not allowed, keys are unique, values can be duplicate
    - Both keys and values should be Objects/Wrapper classes, No promitive types
    * */

    /*
    * Map Interface Methods :
    * Object put (K key, V value)
    * Object get (Object key)
    * Object remove (Object key)
    * boolean containsKey(Object key)
    * boolean containsValue(Object value)
    * int size()
    * boolean isEmpty()
    *
    * BULK METHODS
    * void putAll()
    * Set<K> keySet()
    * Collection <V> values()
    * Set<Map.Entry<K,V>> entrySet()
    *
    * Methods of interface Entry
    * K getKey();
    * V getValue();
    * V setValue(V value);
    *
    * HashMap v/s HashTable
    * - HashMap is not thread safe, HashTable is
    * - HashMap permits one null key, HashTable does not (only non-null key and values)
    * - Obv HashMaps are faster
    *
    * Make sure hashcode() and equals() are implemented in the custom classes, jiska HashMap bana rhe hai
    *
    * LinkedHashMap preserves sorting order, maintains doubly linked list
    * But HashMap is a natural choice if sorting is not an issue
    *
    *
    *
    * */

    System.out.println("\nHashMap implementation");
    Map<String, Integer> map = new HashMap<>();
    map.put("Pupla", 45);
    map.put("Pupli", 80);
    map.put("Vaibhu", 55);
    map.put("Meghana", 10);

    System.out.println(map.get("Pupli"));
    System.out.println(map.get("Mama")); // This key is not present in the maps, returns null
    // But this is not good since if it is a custom class and we call a method on it, itll be null.getSomethin() - NULLPointerException
    // So we have different null ptr exc safe method . see below
    System.out.println(map.getOrDefault("Mama",0));
    System.out.println(map.containsKey("Kaka")); // should be false

    // Adjacency list demo - Map<Integer, List<Integer> > - use of adj.computeIfAbsent(1, f->new ArrayList<>()).add(2);

    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
    for( Map.Entry<String, Integer> entry: entrySet)
        System.out.println(entry.getKey()+" <key and value> "+entry.getValue());

    for(String key: map.keySet())
        System.out.println("key: "+key+" value: "+map.get(key));

    /*
    * SortedMap - Keys will be sorted ~ SortedSet jaise ops [Order defined with Natural or Total ordering - Comparator]
    * - K firstKey()
    * - k lastKey()
    *
    * NavigableMap - Find closest matches for specific entries ~ NavigableSet jaise ops
    * - ceilingEntry(K key) / K ceilingKey(K key)
    * - floorEntry(K key)   / K floorKey (K key)
    * - higherEntry(K key)  / K higherKey(K key)
    * - lowerEntry(K key )  / K lowerKey(K key)
    *
    *
    */

    /*
    *
    * TreeMap - Concrete class - Impl NavigableMap - SortedMap
    *
    * */
    System.out.println("\nTreeMap methods.. ");
    NavigableMap<Integer, String> tmap = new TreeMap<>((a,b)->b-a); // passing our own comparator to sort it DESC order
    tmap.put(9,"Megh");
    tmap.put(2,"Man");
    tmap.put(1,"Nis");
    tmap.put(4,"Ish");

    for(Integer key: tmap.keySet())
        System.out.println("key: "+key+" value: "+tmap.get(key));

    System.out.println(tmap.pollFirstEntry());
    System.out.println("Key just greater than 3 "+tmap.ceilingKey(3));
    System.out.println("Corresponding value of the key which is just greater than 3 "+tmap.ceilingEntry(3));


    // Some more imp methods MISC
    // For sorting
    // Arrays.sort(int[] arr)
    // Collections.sort(ArrayList)
    // Collections.sort(ArrayList, )
    //
    Collections.sort(lk, Collections.reverseOrder()); // aisa bhi kr skte hai
    // Arrays.asList(Integer[] arr);
    System.out.println(Collections.binarySearch(lk,10));
}
}



// PART 7 EXTRAS START ==================
/*
 * USAGE OF Comparable v/s Comparator
 * For custom class - Comparable AND Comparator BOTH, For non-custom class - myComp implements Comparator
 *
 * Use Comparable for Custom classes, and define compareTo() method in that class
 *
 * For system defined classes, like Integer, they have already implemented compareTo() method by implementing Comparable interface an we cant change Integer ka compareTo() method
 * So, for those, create customComparator and implement Comparator, define compare() method and pass object of customComparator or Lambda expression to List.sort(myList, new customComparator())
 * where, myList<Integer>
 * So we can have a choice of deciding the order or sorting even though the class is not user defined
 *
 * COMPARATOR will ALWAYS override COMPARABLE's natural ordering, if defined

 * */

class marksComparator implements Comparator<B0_StudentMarks>{

    @Override
    public int compare(B0_StudentMarks o1, B0_StudentMarks o2) {
        System.out.println("marksComparator::compare() is called");
        return o2.getPhysics()-o1.getPhysics(); // Because we want desceding order of numbers from PrioQueue : pq
    }
}

class nosComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        System.out.println("nosComparator::compare() is called");
        return o2-o1; // Because we want desceding order of numbers from PrioQueue : pq
    }
}

// PART 7 EXTRAS END ==================