package DEV.a_JavaBasics;

public class JavaBasics {

    /*
     * =============================================================
     * JAVA CORE CONCEPTS STUDY GUIDE
     * =============================================================
     * Topics covered in the correct learning sequence:
     *
     * 1. Primitive Types
     * 2. Rare Primitive Edge Cases
     * 3. Wrapper Classes
     * 4. Autoboxing & Unboxing
     * 5. Primitive vs Wrapper Classes
     * 6. Arrays Basics
     * 7. String Immutability
     * 8. StringBuilder vs StringBuffer
     * 9. equals() vs ==
     * 10. hashCode() Contract
     * 11. Enums
     *
     * This file is intentionally verbose and heavily commented
     * so you can study directly from the code.
     * =============================================================
     */

    public static void main(String[] args) {

        primitiveBasics();
        rarePrimitiveEdgeCases();
        wrapperClasses();
        autoboxingAndUnboxing();
        primitiveVsWrapper();
        arraysBasics();
        stringImmutability();
        stringBuilderVsStringBuffer();
        equalsVsDoubleEquals();
        hashCodeContract();
        enumsDemo();
    }

    // =============================================================
    // 1. PRIMITIVE TYPES
    // =============================================================

    static void primitiveBasics() {

        System.out.println("\n================ PRIMITIVE TYPES ================");

        /*
         * Java has 8 primitive data types.
         *
         * Primitive types are NOT objects.
         * They store actual raw values directly in memory.
         * Local primitive variables live in stack memory
         * If a primitive is part of an object or class, it’s stored in the heap right alongside the object
         *
         * MEMORY STUFF BELOW > https://medium.com/@AlexanderObregon/how-javas-primitive-data-types-are-stored-in-memory-b2b38a239263
         * When a method is called, a stackframe is created to store its local params, and it is automatically destoyed when method call ends
         * stack allocation is faster than heap allocation, as there is no need for garbage collection
         *
         * MORE ABOUT BOOLEAN STORAGE
         * Other primitive types have a fixed size in Java, but boolean does not.
         * While it logically requires only 1 bit to store true or false, most JVMs allocate 1 byte for each boolean.
         * This is because modern processors access memory in byte-sized chunks, making single-bit storage inefficient.
         * In every mainstream JVM, including HotSpot, each element of a boolean still occupies one full byte to avoid word-tearing and keep element offsets addressable.
         *
         * STACK V/S HEAP
         * JVM can directly retrive values from stack memory, but,
         * JVM reads the reference from stack and follows that reference to find actual object in heap memory , hence stack is faster
         *
         * Stack doesnt need GC because they get cleared automatically when stack frame is cleared, but,
         * Objects need GC to free memory when they are not in use, scan heap, identify unused objects, clean them etc
         *
         * Primitive vars stored in stack memory are automatically thread-safe, because each thread in Java has its own stack, but
         * Objects stored in heap memoru can be accessed by multiple threads at the same time (Inconsistent results, synchronization is needed)
         *
         * */

        // Below 4 are integer types = 1 byte = 8 bits
        byte byteValue = 127;              // 8-bit
        short shortValue = 32000;          // 16-bit
        int intValue = 100000;             // 32-bit
        long longValue = 9999999999L;      // 64-bit

        float floatValue = 10.5f;          // 32-bit floating point
        double doubleValue = 99.999;       // 64-bit floating point

        char charValue = 'A';              // Single Unicode character

        boolean booleanValue = true;       // true or false

        System.out.println(byteValue);
        System.out.println(shortValue);
        System.out.println(intValue);
        System.out.println(longValue);
        System.out.println(floatValue);
        System.out.println(doubleValue);
        System.out.println(charValue);
        System.out.println(booleanValue);

        /*
         * Primitive variables cannot store null.
         *
         * Example:
         * int x = null; // Compilation error
         */
    }


    // =============================================================
    // 2. RARE PRIMITIVE EDGE CASES
    // =============================================================

    static void rarePrimitiveEdgeCases() {

        System.out.println("\n================ RARE PRIMITIVE EDGE CASES ================");

        // ---------------------------------------------------------
        // Integer overflow
        // ---------------------------------------------------------

        int maxInt = Integer.MAX_VALUE;

        System.out.println("Integer.MAX_VALUE = " + maxInt);

        // Overflow wraps around in Java
        int overflowed = maxInt + 1;

        System.out.println("After overflow = " + overflowed);


        // ---------------------------------------------------------
        // Floating point precision issue
        // ---------------------------------------------------------

        double result = 0.1 + 0.2;

        // Floating point values are not always exact
        System.out.println("0.1 + 0.2 = " + result);


        // ---------------------------------------------------------
        // NaN (Not a Number)
        // ---------------------------------------------------------

        double nanValue = 0.0 / 0.0;

        System.out.println("NaN value = " + nanValue);

        // Strange but important:
        // NaN is NOT equal to itself
        System.out.println("NaN == NaN ? " + (nanValue == nanValue));

        // Correct way to check NaN
        System.out.println("Double.isNaN(nanValue) ? " + Double.isNaN(nanValue));


        // ---------------------------------------------------------
        // Positive and negative zero
        // ---------------------------------------------------------

        double positiveZero = 0.0;
        double negativeZero = -0.0;

        System.out.println("0.0 == -0.0 ? " + (positiveZero == negativeZero));

        // But division behaves differently
        System.out.println("1 / 0.0 = " + (1 / positiveZero));
        System.out.println("1 / -0.0 = " + (1 / negativeZero));


        // ---------------------------------------------------------
        // Char is internally numeric
        // ---------------------------------------------------------

        char c = 'A';

        // char internally stores Unicode integer value - ASCII Values
        System.out.println("Unicode value of A = " + (int) c);


        // ---------------------------------------------------------
        // byte arithmetic promotion
        // ---------------------------------------------------------

        byte b1 = 10;
        byte b2 = 20;

        // In arithmetic, byte becomes int automatically
        int sum = b1 + b2;

        System.out.println("byte addition result type becomes int: " + sum);
    }


    // =============================================================
    // 3. WRAPPER CLASSES
    // =============================================================

    static void wrapperClasses() {

        System.out.println("\n================ WRAPPER CLASSES ================");

        /*
         * Every primitive type has a corresponding wrapper class.
         *
         * Primitive      Wrapper
         * ---------      -------
         * byte           Byte
         * short          Short
         * int            Integer
         * long           Long
         * float          Float
         * double         Double
         * char           Character
         * boolean        Boolean
         *
         * Wrapper classes are OBJECTS.
         */

        Integer integerObject = Integer.valueOf(100);
        // this can be done without boxing as well line below - Covered in AutoBoxing section
        Integer integerObject1 = 100;
        Double doubleObject = Double.valueOf(99.5);
        Character characterObject = Character.valueOf('Z');

        System.out.println(integerObject);
        System.out.println(doubleObject);
        System.out.println(characterObject);


        // Wrapper classes can store null
        Integer nullableInteger = null;

        System.out.println("Wrapper can store null: " + nullableInteger);


        // Useful utility methods
        // Directly convert string into an Integer
        System.out.println("Integer.parseInt(\"123\") = " + Integer.parseInt("123"));

        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
    }


    // =============================================================
    // 4. AUTOBOXING & UNBOXING
    // =============================================================

    static void autoboxingAndUnboxing() {

        System.out.println("\n================ AUTOBOXING & UNBOXING ================");

        // ---------------------------------------------------------
        // AUTOBOXING
        // ---------------------------------------------------------

        /*
         * Autoboxing:
         * Automatic conversion from primitive -> wrapper object.
         */

        Integer boxed = 10; // compiler converts to Integer.valueOf(10)

        System.out.println("Autoboxed Integer = " + boxed);


        // ---------------------------------------------------------
        // UNBOXING
        // ---------------------------------------------------------

        /*
         * Unboxing:
         * Automatic conversion from wrapper -> primitive.
         */

        int primitive = boxed; // compiler converts to boxed.intValue()

        System.out.println("Unboxed int = " + primitive);


        // ---------------------------------------------------------
        // IMPORTANT PITFALL: NullPointerException
        // ---------------------------------------------------------

        Integer nullable = null;

        try {
            // Causes NullPointerException during unboxing
            int value = nullable;
            System.out.println(value);
        } catch (NullPointerException e) {
            System.out.println("Unboxing null causes NullPointerException");
        }

        // ---------------------------------------------------------
        // Integer caching
        // ---------------------------------------------------------

        /*
         * Java caches Integer objects from -128 to 127.
         */
        // True because JVM Created a cache
        /*          Integer Cache
                        |
                        v
                   [Integer(100)]
                     /      \
                    a        b
        */
        Integer a = 100;
        Integer b = 100;

        System.out.println("100 cached objects same? " + (a == b));

        // but 200 is outside of the cache range
        Integer x = 200;
        Integer y = 200;

        System.out.println("200 cached objects same? " + (x == y));
        // BEST Is to use .equals() for comparison
    }


    // =============================================================
    // 5. PRIMITIVE VS WRAPPER CLASSES
    // =============================================================

    static void primitiveVsWrapper() {

        System.out.println("\n================ PRIMITIVE VS WRAPPER ================");

        /*
         * PRIMITIVES
         * ----------
         * Faster
         * Less memory
         * Cannot store null
         * Not objects
         * Used for performance-critical code
         *
         * WRAPPERS
         * --------
         * Slower (object overhead)
         * More memory
         * Can store null
         * Required for collections
         * Have utility methods
         */

        int primitiveInt = 5;
        Integer wrapperInt = 5;

        System.out.println("Primitive int = " + primitiveInt);
        System.out.println("Wrapper Integer = " + wrapperInt);


        // Collections only work with objects
        java.util.List<Integer> numbers = new java.util.ArrayList<>();

        numbers.add(10);
        numbers.add(20);

        System.out.println("List with wrapper types = " + numbers);


        /*
         * You cannot do this:
         *
         * List<int> list = new ArrayList<>();
         *
         * Because generics require objects, not primitives.
         */
    }


    // =============================================================
    // 6. ARRAYS BASICS
    // =============================================================

    static void arraysBasics() {

        System.out.println("\n================ ARRAYS BASICS ================");

        /*
         * Arrays store multiple values of same type.
         *
         * Arrays are FIXED SIZE in Java.
         */

        int[] numbers = {10, 20, 30, 40};

        System.out.println("First element = " + numbers[0]);
        System.out.println("Array length = " + numbers.length);


        // ---------------------------------------------------------
        // Iterating array using loop
        // ---------------------------------------------------------

        System.out.println("Traditional for loop:");

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }


        // ---------------------------------------------------------
        // Enhanced for loop
        // ---------------------------------------------------------

        System.out.println("Enhanced for loop:");

        for (int value : numbers) {
            System.out.println(value);
        }


        // ---------------------------------------------------------
        // Default values in arrays
        // ---------------------------------------------------------

        int[] intArray = new int[3];
        boolean[] boolArray = new boolean[3];
        String[] stringArray = new String[3];

        /*
         * Default values:
         * int      -> 0
         * boolean  -> false
         * object   -> null
         */

        System.out.println("Default int value = " + intArray[0]);
        System.out.println("Default boolean value = " + boolArray[0]);
        System.out.println("Default String value = " + stringArray[0]);


        // ---------------------------------------------------------
        // Arrays utility methods
        // ---------------------------------------------------------

        java.util.Arrays.sort(numbers);

        System.out.println("Sorted array = " + java.util.Arrays.toString(numbers));
    }


    // =============================================================
    // 7. STRING IMMUTABILITY
    // =============================================================

    static void stringImmutability() {

        System.out.println("\n================ STRING IMMUTABILITY ================");

        /*
         * Strings are IMMUTABLE in Java.
         *
         * Once created, String objects cannot be changed.
         */

        String original = "Hello";

        // This does NOT modify original string.
        // Instead, it creates a NEW String object.
        String modified = original.concat(" World");

        System.out.println("Original = " + original);
        System.out.println("Modified = " + modified);


        // ---------------------------------------------------------
        // Why String immutability matters
        // ---------------------------------------------------------

        /*
         * Benefits:
         *
         * 1. Thread-safe
         * 2. Secure
         * 3. String pool optimization
         * 4. Reliable hashCode caching
         */


        // ---------------------------------------------------------
        // String pool
        // ---------------------------------------------------------

        String s1 = "java";
        String s2 = "java";
        String s5 = "java";
        // Both point to same pooled string object
        System.out.println("s1 == s2 ? " + (s1 == s2));
        System.out.println("s2 == s5 ? " + (s2 == s5));

        // Creates object outside pool
        String s3 = new String("java");

        System.out.println("s1 == s3 ? " + (s1 == s3));

        // Content comparison
        System.out.println("s1.equals(s3) ? " + s1.equals(s3));

        // Another String with same value created on Heap, creates a nw object
        String s4 = new String("java");
        System.out.println("s3 == s4 ? " + (s3 == s4));

        // NEW keyword ALWAYS creates a new object on heap, No matter what the contents of string is
    }


    // =============================================================
    // 8. STRINGBUILDER VS STRINGBUFFER
    // =============================================================

    static void stringBuilderVsStringBuffer() {

        System.out.println("\n================ STRINGBUILDER VS STRINGBUFFER ================");

        /*
         * Since String is immutable, repeated modifications are costly.
         *
         * StringBuilder and StringBuffer solve this problem.
         */


        // ---------------------------------------------------------
        // StringBuilder
        // ---------------------------------------------------------

        /*
         * Mutable
         * Faster
         * NOT thread-safe
         * Preferred in single-threaded code
         */

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Java");
        stringBuilder.append(" ");
        stringBuilder.append("Builder");

        System.out.println("StringBuilder result = " + stringBuilder);


        // ---------------------------------------------------------
        // StringBuffer
        // ---------------------------------------------------------

        /*
         * Mutable
         * Slower than StringBuilder
         * Thread-safe (methods synchronized)
         * Used in multithreaded scenarios
         */

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Java");
        stringBuffer.append(" ");
        stringBuffer.append("Buffer");

        System.out.println("StringBuffer result = " + stringBuffer);


        // ---------------------------------------------------------
        // Interview summary
        // ---------------------------------------------------------

        /*
         * String        -> Immutable
         * StringBuilder -> Mutable + Fast + Not thread-safe
         * StringBuffer  -> Mutable + Thread-safe + Slower
         */
    }


    // =============================================================
    // 9. equals() vs ==
    // =============================================================

    static void equalsVsDoubleEquals() {

        System.out.println("\n================ equals() vs == ================");

        /*
         * ==
         * --
         * Compares references for objects.
         * Compares actual values for primitives.
         *
         * equals()
         * --------
         * Compares logical/content equality.
         */


        // ---------------------------------------------------------
        // Primitive comparison
        // ---------------------------------------------------------

        int a = 10;
        int b = 10;

        // For primitives, == compares actual values
        System.out.println("Primitive comparison = " + (a == b));


        // ---------------------------------------------------------
        // Object comparison
        // ---------------------------------------------------------

        String s1 = new String("Java");
        String s2 = new String("Java");

        // Different objects in memory
        System.out.println("s1 == s2 ? " + (s1 == s2));

        // Same content
        System.out.println("s1.equals(s2) ? " + s1.equals(s2));


        // ---------------------------------------------------------
        // Common interview trap with wrappers
        // ---------------------------------------------------------

        Integer x = 127;
        Integer y = 127;

        // Cached values
        System.out.println("127 wrappers using == ? " + (x == y));


        Integer p = 128;
        Integer q = 128;

        // Different objects, because it is out of cache limit
        System.out.println("128 wrappers using == ? " + (p == q));

        // Correct comparison
        System.out.println("128 wrappers using equals() ? " + p.equals(q));
    }


    // =============================================================
    // 10. hashCode() CONTRACT
    // =============================================================

    static void hashCodeContract() {

        System.out.println("\n================ hashCode() CONTRACT ================");

        /*
         * hashCode() is heavily used in:
         *
         * - HashMap
         * - HashSet
         * - Hashtable
         *
         * CONTRACT:
         *
         * 1. If two objects are equal according to equals(),
         *    they MUST have same hashCode().
         *
         * 2. If two objects have same hashCode(),
         *    they MAY or MAY NOT be equal.
         */

        Student s1 = new Student(1, "Rahul");
        Student s2 = new Student(1, "Rahul");

        System.out.println("s1.equals(s2) = " + s1.equals(s2));

        System.out.println("s1.hashCode() = " + s1.hashCode());
        System.out.println("s2.hashCode() = " + s2.hashCode());


        // ---------------------------------------------------------
        // HashSet behavior
        // ---------------------------------------------------------

        java.util.Set<Student> set = new java.util.HashSet<>();

        set.add(s1);
        set.add(s2);

        // Only one object stored because equals/hashCode are correct
        System.out.println("HashSet size = " + set.size());
    }


    // =============================================================
    // 11. ENUMS
    // =============================================================

    static void enumsDemo() {

        System.out.println("\n================ ENUMS ================");

        /*
         * Enum = fixed set of constants.
         *
         * Enums are type-safe.
         *
         * Better than using plain constants.
         */

        Day today = Day.SUNDAY;

        System.out.println("Today = " + today);


        // ---------------------------------------------------------
        // switch with enum
        // ---------------------------------------------------------

        switch (today) {

            case MONDAY:
                System.out.println("Start of work week");
                break;

            case SATURDAY:
            case SUNDAY:
                System.out.println("Weekend");
                break;

            default:
                System.out.println("Weekday");
        }


        // ---------------------------------------------------------
        // Enum methods
        // ---------------------------------------------------------

        System.out.println("Ordinal = " + today.ordinal());

        System.out.println("All enum constants:");

        for (Day day : Day.values()) {
            System.out.println(day);
        }
    }


    // =============================================================
    // SUPPORTING CLASS FOR hashCode() EXAMPLE
    // =============================================================

    static class Student {

        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) {
                return true;
            }

            // Null or different class
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Student other = (Student) obj;

            return id == other.id && java.util.Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {

            // Generates consistent hash based on fields
            return java.util.Objects.hash(id, name);
        }
    }


    // =============================================================
    // ENUM DECLARATION
    // =============================================================

    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}

