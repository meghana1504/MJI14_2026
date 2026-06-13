package DEV.a_JavaBasics.A1_JVMInternals_Memory_GC;

public class AdvancedJvmConceptsStudyGuide {

    /*
     * ============================================================================
     * ADVANCED JVM CONCEPTS - COMPLETE STUDY GUIDE
     * ============================================================================
     *
     * Topics Covered:
     * 0. JMM Rules
     * 1. Heap vs Stack Memory
     * 2. Object Lifecycle
     * 3. Garbage Collection Basics
     * 4. G1GC (Garbage First Garbage Collector)
     * 5. Memory Leaks in Java
     * 6. Java Memory Model (JMM) Basics
     * 7. ClassLoader
     * 8. Metaspace
     * 9. JIT Compiler
     * 10. Advanced GC Internals (ZGC / Shenandoah)
     * 11. JVM Tuning Basics
     * 12. Escape Analysis
     *
     * This file is intentionally detailed and interview-oriented.
     *
     * IMPORTANT:
     * Java hides many JVM internals from developers.
     * This file explains simplified mental models that are accurate
     * enough for interviews and real-world understanding.
     * ============================================================================
     */

    public static void main(String[] args) {

        heapVsStack();
        objectLifecycle();
        gcBasics();
        g1gcBasics();
        memoryLeaks();
        jmmBasics();
        classLoaderBasics();
        metaspaceBasics();
        jitCompilerBasics();
        advancedGcInternals();
        jvmTuningBasics();
        escapeAnalysisBasics();
    }
    // ============================================================================
    // 1. JAVA MEMOERY MODEL
    // ============================================================================
    /*
    * Order Of Execution
    * a=1;
    * b=2;
    * a=a+1;
    * a=a+b;
    *
    * JVM Might change the order of execution here, i.e it can do a=a+1 right after it loads a=1 from the memory,
    * Bceause then it wont have to load variable a again to do a=a+1
    * But the code semantics wont change, we will get the output in the expected order
    *
    *
    * Field Visibility Problem (And how it is solved by keyword volatile)
    *
        *           Memory gets cheaper ------------>
        *   [CPU]   cores <- registers <- L1 Cache <- L2 Cache -< shared cache
        *           <------------- faster processing, nearer to the CPU
        *
    * Now lets say we have below piece of code
     * public class FieldVisibility {
            int x = 0;
            public void writerThread() {
                x = 1;
                }
            public void readerThread() {
                int r2 = x;
                }
            }
    *
    * WHAT Happens in the memory >>
    *  		writer-thread 	reader-thread
		        core 1 			core 2
        X = 1	"local			"local	  "X = 0, r2=x"
		        Cache "			Cache"

			       x = 0 	x = 0
			        shared 	cache
	*
    * Here write thread updates the value of x in its local cache, but it is not updated in the shared cache, from where the reader thread the value of x
    * This is solved by making the variable "x" volatile
    * Like :
    * public class VolatileVisibility {
        volatile int x = 0;
        public void writerThread() {
            x = 1;
        }
        public void readerThread() {
            int r2 = x;
        }
        }
    * Due to the volatile keyword, the local cache value when updated, is flushed to the shared cache, so even shared cache updates x=1, and reader thread has updated value of x
    *
    * JMM rules
    *
    * Happens Before relationship ?
    *   This is applicable to "volatile". synchronised, concurrent collections, thread ops etc
    *   All the values, which were updated before synchronised, have to be updated and visible after the reading of synchronised- hence, it is called happens before relationship
    *   Synchronised keyword HAS to use SAME Object instance of the class
    *
    * */

    // ============================================================================
    // 1. HEAP vs STACK MEMORY
    // ============================================================================

    static void heapVsStack() {

        System.out.println("\n================ HEAP vs STACK ================");

        /*
         * JVM MEMORY ARCHITECTURE (SIMPLIFIED BUT INTERVIEW-LEVEL ACCURATE)
         *
         * +--------------------------------------------------------------------------------+
         * |                                JVM MEMORY                                     |
         * +--------------------------------------------------------------------------------+
         * |                                                                                |
         * | THREAD-LOCAL MEMORY                    SHARED JVM MEMORY                       |
         * |                                                                                |
         * | +----------------------------+       +--------------------------------------+ |
         * | | STACK MEMORY               |       | HEAP MEMORY                          | |
         * | |----------------------------|       |--------------------------------------| |
         * | | Method Frames (ret addrs)  |       | Young Generation                     | |
         * | | Local Variables            |       |  - Eden                              | |
         * | | Primitive Values           |       |  - Survivor S0                       | |
         * | | Object References  --------|------>|  - Survivor S1                       | |
         * | |                            |       |                                      | |
         * | +----------------------------+       | Old Generation                       | |
         * |                                      |  - Long-lived Objects                | |
         * |                                      |                                      | |
         * | +----------------------------+       | Large Object Region                  | |
         * | | PC REGISTER                |       | (depends on GC implementation)      | |
         * | |----------------------------|       +--------------------------------------+ |
         * | | Current JVM instruction    |                                                |
         * | +----------------------------+                                                |
         * |                                                                                |
         * | +----------------------------+       +--------------------------------------+ |
         * | | NATIVE METHOD STACK        |       | METASPACE                           | |
         * | |----------------------------|       |--------------------------------------| |
         * | | JNI Calls                  |       | Class Metadata                      | |
         * | | Native Libraries           |       | Method Metadata                     | |
         * | +----------------------------+       | Runtime Constant Pool               | |
         * |                                      | Bytecode Information                | |
         * |                                      +--------------------------------------+ |
         * |                                                                                |
         * |                                      +--------------------------------------+ |
         * |                                      | CODE CACHE                          | |
         * |                                      |--------------------------------------| |
         * |                                      | JIT compiled native machine code    | |
         * |                                      +--------------------------------------+ |
         * |                                                                                |
         * +--------------------------------------------------------------------------------+
         *
         * IMPORTANT JVM MEMORY FACTS
         *
         * STACK MEMORY
         * - One stack per thread
         * - Stores stack frames
         * - Very fast allocation/deallocation
         * - Automatically cleaned when methods finish
         * - StackOverflowError happens here
         *
         * HEAP MEMORY
         * - Shared across all threads
         * - Stores objects and arrays
         * - Managed by Garbage Collector
         * - OutOfMemoryError usually happens here
         *
         * METASPACE
         * - Stores class metadata
         * - Uses native OS memory
         * - Replaced PermGen in Java 8
         *
         * PC REGISTER
         * - Stores current executing JVM instruction
         * - Each thread has its own PC register
         *
         * CODE CACHE
         * - Stores JIT-compiled native machine code
         * - Improves execution speed dramatically
         *
         * NATIVE METHOD STACK
         * - Used for JNI/native C/C++ calls
         *
         * MEMORY FLOW
         *
         * Java Source Code
         *        |
         *        v
         * Bytecode (.class)
         *        |
         *        v
         * ClassLoader loads class
         *        |
         *        +-----> Metadata -> Metaspace
         *        |
         *        +-----> Objects -> Heap
         *        |
         *        +-----> Local Variables -> Stack
         *        |
         *        +-----> Hot methods -> JIT -> Code Cache
         */


        // ------------------------------------------------------------------------
        // STACK MEMORY
        // ------------------------------------------------------------------------

        /*
         * Stack memory stores:
         *
         * - Method calls
         * - Local variables
         * - Primitive values
         * - Object references
         *
         * Every thread has its OWN stack.
         *
         * Stack is:
         * - Fast
         * - Thread-safe naturally
         * - Automatically cleaned
         */

        int age = 25; // primitive stored directly in stack

        Person p = new Person("Rahul");

        /*
         * Here:
         *
         * Stack:
         *   age = 25
         *   p   = reference/address
         *
         * Heap:
         *   Person object
         */

        System.out.println("Person name = " + p.name);


        // ------------------------------------------------------------------------
        // VISUALIZATION
        // ------------------------------------------------------------------------

        /*
         * STACK MEMORY                      HEAP MEMORY
         * --------------------------------------------------------
         * age = 25
         *
         * p (obj ref) ---------------->  Person Object
         *                              +----------------+
         *                               | name = Rahul   |
         *                              +----------------+
         */


        // ------------------------------------------------------------------------
        // STACK FRAME
        // ------------------------------------------------------------------------

        methodA();

        /*
         * Every method call creates a STACK FRAME.
         *
         * Example call sequence:
         *
         * main()
         *   -> methodA()
         *       -> methodB()
         *
         * Stack grows like below, first in, last out:
         * main goes in stack, then method A, then method B
         * +----------------+
         * | methodB frame  |
         * +----------------+
         * | methodA frame  |
         * +----------------+
         * | main frame     |
         * +----------------+
         *
         * EVERY Method frame will have :
         * local primitive parameters stored directly
         * References to the objects (if any)
         * Operand stack : JVM Uses an internal operand stack for temporary computations
         * Return address : JVM Should remember, where to return the control after the method execution
         *
         * IMPORTANT INTERVIEW POINT:
         *
         * Stack memory is automatically cleaned when
         * a method finishes execution.
         *
         * Therefore:
         * Stack allocation is EXTREMELY fast.
         *
         */
    }

    static void methodA() {
        int x = 10;
        methodB();
    }

    static void methodB() {
        int y = 20;
    }


    // ============================================================================
    // 2. OBJECT LIFECYCLE
    // ============================================================================

    static void objectLifecycle() {

        System.out.println("\n================ OBJECT LIFECYCLE ================");

        /*
         * OBJECT LIFECYCLE
         *
         * 1. Class Loading
         * 2. Memory Allocation
         * 3. Constructor Execution
         * 4. Object Usage
         * 5. Object Becomes Unreachable
         * 6. Garbage Collection
         * 7. Memory Reclaimed
         */


        // ------------------------------------------------------------------------
        // OBJECT CREATION
        // ------------------------------------------------------------------------

        Employee emp = new Employee("Amit");

        /*
         * What happens internally:
         *
         * Step 1:
         * JVM checks whether Employee class is loaded.
         *
         * Step 2:
         * Heap memory allocated.
         *
         * Step 3:
         * Instance variables initialized.
         *
         * Step 4:
         * Constructor executes.
         *
         * Step 5:
         * Reference returned.
         */

        System.out.println(emp.name);


        // ------------------------------------------------------------------------
        // OBJECT ELIGIBLE FOR GC
        // ------------------------------------------------------------------------

        emp = null;

        /*
         * Object still exists in heap.
         *
         * But now it has NO STRONG REFERENCES.
         *
         * Therefore:
         * Object becomes ELIGIBLE for Garbage Collection.
         *
         * IMPORTANT:
         * Eligible != immediately destroyed.
         */


        // Request GC (not guaranteed),  (JVM) decides when the time is right to reclaim memory
        // Good article : https://medium.com/@AlexanderObregon/requesting-garbage-collection-with-system-gc-in-java-2dcb802ce0f3
        System.gc();
    }


    // ============================================================================
    // 3. GARBAGE COLLECTION BASICS
    // ============================================================================

    static void gcBasics() {

        System.out.println("\n================ GC BASICS ================");

        /*
         * Garbage Collector automatically removes unused objects.
         *
         * Main goal:
         * Prevent memory exhaustion.
         */


        // ------------------------------------------------------------------------
        // WHY GC EXISTS
        // ------------------------------------------------------------------------

        /*
         * Languages like C/C++ require manual memory management.
         *
         * Example problems:
         * - Memory leaks
         * - Dangling pointers
         * - Double free()
         *
         * Java automates memory cleanup.
         */


        // ------------------------------------------------------------------------
        // GENERATIONAL HEAP MODEL
        // ------------------------------------------------------------------------

        /*
         * JVM HEAP
         *
         * +------------------------------------------------+
         * |                    HEAP                        |
         * +------------------------------------------------+
         * |                                                |
         * |  YOUNG GEN        OLD GEN                      |
         * |                                                |
         * |  Eden             Long-lived objects           |
         * |  Survivor S0                                   |
         * |  Survivor S1                                   |
         * |                                                |
         * +------------------------------------------------+
         */


        // ------------------------------------------------------------------------
        // GC THEORY
        // ------------------------------------------------------------------------

        /*
         * MOST objects die young.
         * Therefore:
         * New objects go to Young Generation.
         * Surviving objects eventually move to Old Generation.
         */


        // ------------------------------------------------------------------------
        // MINOR GC
        // ------------------------------------------------------------------------

        /*
         * Minor GC:
         *
         * - Cleans Young Generation
         * - Fast
         * - Frequent
         */


        // ------------------------------------------------------------------------
        // MAJOR / FULL GC
        // ------------------------------------------------------------------------

        /*
         * Major GC / Full GC:
         *
         * - Cleans Old Generation
         * - Slower
         * - More expensive
         * - Causes longer pauses
         */


        // ------------------------------------------------------------------------
        // MARK AND SWEEP
        // ------------------------------------------------------------------------

        /*
         * Simplified GC algorithm:
         *
         * 1. MARK reachable objects
         * 2. SWEEP unreachable objects
         * 3. COMPACT memory if needed
         */


        byte[] memoryPressure = new byte[1024 * 1024];

        System.out.println("Allocated memory to create GC pressure");
    }


    // ============================================================================
    // 4. G1GC (Garbage First GC)
    // ============================================================================

    static void g1gcBasics() {

        System.out.println("\n================ G1GC ================");

        /*
         * G1GC = Garbage First Garbage Collector
         *
         * Default GC in modern Java versions.
         *
         * Designed for:
         * - Large heaps
         * - Predictable pause times
         * - Server applications
         */


        // ------------------------------------------------------------------------
        // HOW G1GC WORKS
        // ------------------------------------------------------------------------

        /*
         * Traditional collectors divide heap into:
         * - Young Gen
         * - Old Gen
         *
         * G1GC divides heap into REGIONS.
         */


        /*
         * +--------------------------------------------------+
         * |                G1GC REGIONS                      |
         * +--------------------------------------------------+
         * | Y | Y | O | Y | O | O | S | Y | O | Y | ...     |
         * +--------------------------------------------------+
         *
         * Y = Young
         * O = Old
         * S = Survivor
         */


        // ------------------------------------------------------------------------
        // WHY "GARBAGE FIRST"?
        // ------------------------------------------------------------------------

        /*
         * G1 prioritizes regions with MOST garbage first.
         *
         * Therefore:
         * Better efficiency.
         */


        // ------------------------------------------------------------------------
        // IMPORTANT FEATURES
        // ------------------------------------------------------------------------

        /*
         * 1. Incremental collection
         * 2. Region-based heap
         * 3. Predictable pause goals
         * 4. Concurrent marking
         * 5. Heap compaction
         */


        // ------------------------------------------------------------------------
        // JVM FLAG
        // ------------------------------------------------------------------------

        /*
         * Enable G1GC:
         *
         * -XX:+UseG1GC
         */


        // ------------------------------------------------------------------------
        // IMPORTANT INTERVIEW POINT
        // ------------------------------------------------------------------------

        /*
         * G1GC tries to avoid long stop-the-world pauses.
         *
         * But SOME pauses still happen.
         */
    }


    // ============================================================================
    // 5. MEMORY LEAKS
    // ============================================================================

    static void memoryLeaks() {

        System.out.println("\n================ MEMORY LEAKS ================");

        /*
         * MEMORY LEAK in Java:
         *
         * Objects are NO LONGER NEEDED
         * BUT are STILL REACHABLE.
         *
         * Therefore GC cannot remove them.
         */


        // ------------------------------------------------------------------------
        // COMMON LEAK: STATIC COLLECTIONS
        // ------------------------------------------------------------------------

        LeakyContainer.addObject(new byte[1024 * 1024]);

        /*
         * Problem:
         * Static collection holds references forever.
         *
         * Object remains reachable.
         *
         * GC cannot clean it.
         */


        // ------------------------------------------------------------------------
        // OTHER COMMON LEAK SOURCES
        // ------------------------------------------------------------------------

        /*
         * 1. Static collections
         * 2. Unclosed resources
         * 3. ThreadLocal misuse
         * 4. Listeners not removed
         * 5. Cache without eviction
         * 6. Infinite growing collections
         */


        // ------------------------------------------------------------------------
        // MEMORY LEAK VISUALIZATION
        // ------------------------------------------------------------------------

        /*
         * GC ROOT
         *    |
         *    v
         * Static List ----------------------+
         *    |                              |
         *    v                              |
         * Object A                          |
         *                                   |
         * Object B                          |
         *                                   |
         * Object C <------------------------+
         *
         * Since references exist,
         * objects cannot be collected.
         */


        // ------------------------------------------------------------------------
        // HOW TO DETECT LEAKS
        // ------------------------------------------------------------------------

        /*
         * Tools:
         *
         * - VisualVM
         * - JProfiler
         * - Eclipse MAT
         * - Java Flight Recorder
         * - Heap dumps
         */
    }


    // ============================================================================
    // 6. JAVA MEMORY MODEL (JMM)
    // ============================================================================

    static void jmmBasics() {

        System.out.println("\n================ JMM BASICS ================");

        /*
         * JMM = Java Memory Model
         *
         * Defines:
         *
         * - How threads interact with memory
         * - Visibility rules
         * - Ordering rules
         * - Happens-before relationships
         */


        // ------------------------------------------------------------------------
        // THREAD MEMORY MODEL
        // ------------------------------------------------------------------------

        /*
         *                MAIN MEMORY
         *                      |
         *        -----------------------------
         *        |                           |
         *        v                           v
         *   Thread 1 Cache             Thread 2 Cache
         *
         * Threads may cache variables locally.
         */


        // ------------------------------------------------------------------------
        // VISIBILITY PROBLEM
        // ------------------------------------------------------------------------

        SharedData data = new SharedData();

        data.flag = true;

        /*
         * Without proper synchronization:
         *
         * Another thread may NOT immediately see updated value.
         */


        // ------------------------------------------------------------------------
        // VOLATILE
        // ------------------------------------------------------------------------

        /*
         * volatile guarantees:
         *
         * 1. Visibility
         * 2. Prevents certain reordering
         *
         * But volatile DOES NOT provide atomicity.
         */


        // ------------------------------------------------------------------------
        // HAPPENS-BEFORE
        // ------------------------------------------------------------------------

        /*
         * Happens-before guarantees visibility.
         *
         * Example:
         *
         * Unlock happens-before subsequent lock.
         */


        // ------------------------------------------------------------------------
        // IMPORTANT KEYWORDS
        // ------------------------------------------------------------------------

        /*
         * synchronized
         * volatile
         * atomic classes
         * locks
         */
    }


    // ============================================================================
    // 7. CLASSLOADER
    // ============================================================================

    static void classLoaderBasics() {

        System.out.println("\n================ CLASSLOADER ================");

        /*
         * ClassLoader loads .class files into JVM memory.
         */


        // ------------------------------------------------------------------------
        // CLASSLOADER HIERARCHY
        // ------------------------------------------------------------------------

        /*
         * Bootstrap ClassLoader
         *        |
         *        v
         * Platform ClassLoader
         *        |
         *        v
         * Application ClassLoader
         */


        // ------------------------------------------------------------------------
        // BOOTSTRAP CLASSLOADER
        // ------------------------------------------------------------------------

        /*
         * First class loader that starts working
         * Loads most basic/ core Java classes
         * java.lang.*
         * java.util.*
         * located in JAVA_HOME/lib directory
         *
         */

        // ------------------------------------------------------------------------
        // EXTENSION CLASSLOADER
        // ------------------------------------------------------------------------

        /*
         * Loads additional classes which extend the functionality of Java
         * Inside <JAVA_HOME>/lib/ext
         * Eg; DB/ XML files vale classes
         */

        // ------------------------------------------------------------------------
        // APPLICATION CLASSLOADER
        // ------------------------------------------------------------------------

        /*
         * Loads application classes from classpath.
         * The classes created by developers are loaded here
         */

        // GOOD ARTICLE : https://medium.com/@fullstacktips/what-are-class-loaders-and-different-types-of-class-loaders-in-java-e12f05821bc2
        ClassLoader loader = AdvancedJvmConceptsStudyGuide.class.getClassLoader();

        System.out.println("Current ClassLoader = " + loader);


        // ------------------------------------------------------------------------
        // PARENT DELEGATION MODEL
        // ------------------------------------------------------------------------

        /*
         * Child classloader asks parent first.
         * This prevents fake core Java classes.
         */


        // ------------------------------------------------------------------------
        // CLASS LOADING PHASES
        // ------------------------------------------------------------------------

        /*
         * 1. Loading
         * 2. Linking
         *    - Verification
         *    - Preparation
         *    - Resolution
         * 3. Initialization
         */
    }


    // ============================================================================
    // 8. METASPACE
    // ============================================================================

    static void metaspaceBasics() {

        System.out.println("\n================ METASPACE ================");

        /*
         * Before Java 8:
         * JVM used PERMGEN.
         * Java 8 replaced it with METASPACE.
         */


        // ------------------------------------------------------------------------
        // WHAT IS STORED IN METASPACE?
        // ------------------------------------------------------------------------

        /*
         * - Class metadata
         * - Method metadata
         * - Runtime constant pool
         * - Bytecode information
         */


        // ------------------------------------------------------------------------
        // IMPORTANT DIFFERENCE
        // ------------------------------------------------------------------------

        /*
         * PermGen:
         * - Fixed size
         * - Inside JVM heap
         *
         * Metaspace:
         * - Uses native memory
         * - Can grow dynamically
         */


        // ------------------------------------------------------------------------
        // COMMON ERROR
        // ------------------------------------------------------------------------

        /*
         * java.lang.OutOfMemoryError: Metaspace
         *
         * Usually caused by:
         * - Too many dynamically generated classes
         * - ClassLoader leaks
         */


        // ------------------------------------------------------------------------
        // JVM FLAGS
        // ------------------------------------------------------------------------

        /*
         * -XX:MetaspaceSize=256m
         * -XX:MaxMetaspaceSize=512m
         */
    }


    // ============================================================================
    // 9. JIT COMPILER
    // ============================================================================

    static void jitCompilerBasics() {

        System.out.println("\n================ JIT COMPILER ================");

        /*
         * JIT = Just-In-Time Compiler
         * Converts bytecode into native machine code at runtime.
         */


        // ------------------------------------------------------------------------
        // WITHOUT JIT
        // ------------------------------------------------------------------------

        /*
         * Java bytecode would be interpreted line-by-line.
         * Slow.
         */


        // ------------------------------------------------------------------------
        // WITH JIT
        // ------------------------------------------------------------------------

        /*
         * Frequently executed code becomes compiled native code.
         * Faster execution.
         */


        // ------------------------------------------------------------------------
        // HOTSPOT JVM
        // ------------------------------------------------------------------------

        /*
         * JVM identifies HOT methods.
         * Hot methods = frequently executed methods.
         */


        for (int i = 0; i < 100000; i++) {
            hotMethod();
        }


        // ------------------------------------------------------------------------
        // IMPORTANT OPTIMIZATIONS
        // ------------------------------------------------------------------------

        /*
         * JIT optimizations include:
         *
         * 1. Method inlining
         * 2. Dead code elimination
         * 3. Loop optimization
         * 4. Escape analysis
         * 5. Lock elimination
         */


        // ------------------------------------------------------------------------
        // TIERED COMPILATION
        // ------------------------------------------------------------------------

        /*
         * JVM may:
         *
         * Interpret first
         * Then compile later
         * Then optimize aggressively later
         */
    }


    static int hotMethod() {
        return 10 + 20;
    }


    // ============================================================================
    // 10. ADVANCED GC INTERNALS (ZGC / SHENANDOAH)
    // ============================================================================

    static void advancedGcInternals() {

        System.out.println("\n================ ZGC / SHENANDOAH ================");

        /*
         * Modern ultra-low-pause garbage collectors.
         *
         * Designed for:
         * - Huge heaps
         * - Low latency systems
         * - Cloud services
         * - Financial systems
         */


        // ------------------------------------------------------------------------
        // MAIN GOAL
        // ------------------------------------------------------------------------

        /*
         * Reduce stop-the-world pauses dramatically.
         *
         * Traditional GC pauses:
         * - milliseconds to seconds
         *
         * ZGC/Shenandoah:
         * - often sub-10ms pauses
         */


        // ------------------------------------------------------------------------
        // ZGC
        // ------------------------------------------------------------------------

        /*
         * ZGC = Z Garbage Collector
         *
         * Features:
         * - Concurrent GC
         * - Colored pointers
         * - Load barriers
         * - Extremely low pauses
         */


        // ------------------------------------------------------------------------
        // SHENANDOAH
        // ------------------------------------------------------------------------

        /*
         * Shenandoah:
         * - Concurrent compaction
         * - Concurrent evacuation
         * - Low latency focused
         */


        // ------------------------------------------------------------------------
        // STOP-THE-WORLD REDUCTION
        // ------------------------------------------------------------------------

        /*
         * Traditional collectors:
         * App threads STOP during major collection.
         *
         * ZGC/Shenandoah:
         * Much work done concurrently while app runs.
         */


        // ------------------------------------------------------------------------
        // JVM FLAGS
        // ------------------------------------------------------------------------

        /*
         * ZGC:
         * -XX:+UseZGC
         *
         * Shenandoah:
         * -XX:+UseShenandoahGC
         */


        // ------------------------------------------------------------------------
        // IMPORTANT INTERVIEW POINT
        // ------------------------------------------------------------------------

        /*
         * Tradeoff:
         *
         * Lower pause times
         * BUT higher CPU overhead.
         */
    }


    // ============================================================================
    // 11. JVM TUNING BASICS
    // ============================================================================

    static void jvmTuningBasics() {

        System.out.println("\n================ JVM TUNING ================");

        /*
         * JVM tuning = adjusting JVM settings for:
         *
         * - Performance
         * - Throughput
         * - Latency
         * - Memory usage
         */


        // ------------------------------------------------------------------------
        // IMPORTANT MEMORY FLAGS
        // ------------------------------------------------------------------------

        /*
         * Initial heap size:
         * -Xms512m
         *
         * Maximum heap size:
         * -Xmx2g
         */


        // ------------------------------------------------------------------------
        // GC FLAGS
        // ------------------------------------------------------------------------

        /*
         * Use G1GC:
         * -XX:+UseG1GC
         *
         * Enable GC logging:
         * -Xlog:gc
         */


        // ------------------------------------------------------------------------
        // COMMON JVM METRICS
        // ------------------------------------------------------------------------

        /*
         * 1. Heap usage
         * 2. GC pause time
         * 3. Allocation rate
         * 4. CPU utilization
         * 5. Thread count
         */


        // ------------------------------------------------------------------------
        // IMPORTANT TOOLS
        // ------------------------------------------------------------------------

        /*
         * jstat
         * jmap
         * jstack
         * VisualVM
         * Java Flight Recorder
         */


        // ------------------------------------------------------------------------
        // BAD PRACTICE
        // ------------------------------------------------------------------------

        /*
         * Do NOT randomly tune JVM.
         *
         * Always:
         *
         * Measure first.
         * Tune second.
         */


        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory();

        System.out.println("Max JVM memory = " + maxMemory / (1024 * 1024) + " MB");
    }


    // ============================================================================
    // 12. ESCAPE ANALYSIS
    // ============================================================================

    static void escapeAnalysisBasics() {

        System.out.println("\n================ ESCAPE ANALYSIS ================");

        /*
         * Escape Analysis is a JIT optimization.
         *
         * JVM determines whether an object "escapes"
         * the current method/thread.
         */


        // ------------------------------------------------------------------------
        // NON-ESCAPING OBJECT
        // ------------------------------------------------------------------------

        calculate();


        // ------------------------------------------------------------------------
        // WHY IMPORTANT?
        // ------------------------------------------------------------------------

        /*
         * If object does NOT escape:
         *
         * JVM may:
         *
         * 1. Allocate on stack instead of heap
         * 2. Eliminate allocation entirely
         * 3. Remove synchronization
         */


        // ------------------------------------------------------------------------
        // SCALAR REPLACEMENT
        // ------------------------------------------------------------------------

        /*
         * JVM may break object into individual variables.
         *
         * Example:
         *
         * Point(x, y)
         *
         * becomes:
         *
         * int x
         * int y
         */


        // ------------------------------------------------------------------------
        // IMPORTANT INTERVIEW POINT
        // ------------------------------------------------------------------------

        /*
         * Escape analysis reduces:
         *
         * - Heap allocations
         * - GC pressure
         * - Synchronization overhead
         */
    }


    static int calculate() {

        Point p = new Point(10, 20);

        // Object does not escape method
        return p.x + p.y;
    }


    // ============================================================================
    // SUPPORTING CLASSES
    // ============================================================================

    static class Person {

        String name;

        Person(String name) {
            this.name = name;
        }
    }


    static class Employee {

        String name;

        Employee(String name) {
            this.name = name;
        }
    }


    static class SharedData {

        volatile boolean flag;
    }


    static class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static class LeakyContainer {

        static java.util.List<Object> objects = new java.util.ArrayList<>();

        static void addObject(Object obj) {
            objects.add(obj);
        }
    }
}

/*
===============================================================================
                    JAVA MEMORY MODEL — GROUPED TABLE
===============================================================================

HEAP MEMORY
-------------
- Object instances
- Array objects
- String objects created using 'new'
- Instance variables
- Primitive fields inside objects
- Reference fields inside objects
- Actual referenced objects
- Array elements
- Object members inside arrays
- Lambda objects
- Thread objects
- Enum objects
- Boxed primitives (Integer, Double, etc.)

STRING CONSTANT POOL (sub-area of Heap Memory)
------------------------------------------------
- String literals
- Interned Strings
- Reused String constants

STACK MEMORY
--------------
- Method call stack frames
- Local primitive variables
- Local object references
- Method parameters
- Return addresses
- Partial/intermediate calculations
- 'this' reference
- final local variables
- Reference variables

METHOD AREA / METASPACE
------------------------
- Class metadata
- Class structure information
- Static variables
- Static methods bytecode
- Non-static methods bytecode
- Runtime Constant Pool
- Method definitions
- Constructor definitions
- Interface metadata
- final static variables
- Class-level constants

RUNTIME CONSTANT POOL (sub-area of Method Area / Metaspace)
-------------------------------------------------------------
- Compile-time constants
- Symbolic references
- Class constants
- Method references
- Field references
- Literal constants

PC REGISTER
-------------
- Address of current JVM instruction being executed

NATIVE METHOD STACK
--------------------
- Native method execution data
- Native library calls
- JNI-related execution frames

===============================================================================


IMPORTANT RULES
===============================================================================

1. Objects ALWAYS live in Heap.
2. References usually live in Stack.
3. Static members live in Method Area.
4. Method code is stored in Method Area, NOT Stack.
5. Stack stores execution state, not actual objects.
6. Each thread has:
       -> its own Stack
       -> its own PC Register
       -> its own Native Method Stack
7. Heap and Method Area are shared among threads.

===============================================================================
*/

