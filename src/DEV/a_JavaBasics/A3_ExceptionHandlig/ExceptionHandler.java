package DEV.a_JavaBasics.A3_ExceptionHandlig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

public class ExceptionHandler {
    /*
     ====================================================================================
     WHAT IS AN EXCEPTION
     -> Runtime error
     -> Something abnormal happens during runtime of program

    =========== Hierarchy of Exception class ===========
                                  Object
                                    │
                                Throwable
                           _________│_________
                          │                   │
        [JVM Issues]    Error             Exception     [ Application code issues]
                      ____│____           ____│____
                     │         │         │         │
                Virtual    Linkage   Checked   Unchecked
                Machine     Error    (e.g.,    (Runtime
                 Error              IOException) Exception)


    ====================================================================================
    */


//    Only objects that inherit from Throwable can be used with "throw someException()"
    public static void main(String[] args) throws Exception {
        int num=10;
        if(num==10){
//            throw new Exception("Nope, not 10");  // [UNCOMMENT TO SEE EXCEPTION]
//            throw new String("hello"); // Of course not valid
        }
/*
    * ===========================================================================================
    *                           ERROR V/S EXCEPTION
    * ===========================================================================================
    * -> ERROR
    * -> Represents JVM/ System problem
    * -> We do not do catch(Error e) like exceptions
    * -> Error usually indicates that the application can not recover
    * -> OOM or StackOverflowError etc
    * -> takes us to the java clas: VirtualMachineError : error which happens when JVM runs out of resources
    *
    * -> EXCEPTION
    * -> Represents conditions that applications are expected to handle.
    * -> TYPES -
    * -> [1] : CHEKED EXCEPTION > Compiler forces us to handle these
    * -> Eg; FileReader fr = new FileReader("abc.txt"); > Gives a compiler error saying "Unhandled exception FileNotFoundException"
    * -> Here, either use the "throws" around the method or "catch" the exception in try-catch
    *
    * -> [2] : UNCHECKED EXCEPTION > Compiler does not force handling, woh khud detect nahi krta
    * -> NullPtr, ArrayOutOfBound, IllegalArgument, etc
    * -> Compiles fine, because syntactially correct, fails at RunTime
    * -> Everything below RuntimeException is unchecked.
    * -> Everything else under Exception is checked.
*/
        // Below code causes StackOverflowException
            // callMeAgain(); // [UNCOMMENT TO SEE EXCEPTION]

        // Checked exception handling
        /*
            try {
                FileReader fr = new FileReader("abc.txt");
            } catch (FileNotFoundException e) {
            }
        */

        // Unchecked exceptions >
        /*
        String s = null;
        s.length();
        */

    /*
    *
    * */

    /*
     * ===========================================================================================
     *                           throws v/s throw
     * ===========================================================================================
     * -> throws is used in method signature to indicate that this method might throw that kind of exception, declares the possibility of exception happening
     * -> throw is used to throw an exception object like : throw new IllegalArgumentException("Invalid age");
     *
    * */

    /*
     * ===========================================================================================
     *                           EXCEPTION PROPOGATION
     * ===========================================================================================
     * Exception travels up the call stack until caught.
     *
     *
    */

    /*
     * ===========================================================================================
     *                           Multi catch and stuff
     * ===========================================================================================
     * Exception travels up the call stack until caught.
     *
     *  try {
        }
        catch(IOException | SQLException e) {
        }
     *
        try {
        }
        catch(Exception e) {
        }
        catch(IOException e) {
        }
        *
        * BAD Because Biggest exception should come at the last : IOException IS-A Exception
        * most specific -> most general > right order
        *
        * -> Finally block
        * try{} catch{} finally{ sout("Almost always runs"); }
        * Used for cleanup -> release locks, db conns, closing files etc
        *
        *
     */

    /*
     * ===========================================================================================
     *                           TRY-with-resources
     * ===========================================================================================
     * -> Replaces many finally blocks, try(---code---) > this itself closes the resources
     */
     try(FileReader fr1 = new FileReader("myFile.txt")){
         System.out.println("This automatically closes the resource, we dont need a separate finally block here");
     }
     // finally {fr1.close(); } // does this under the hood
     /*
     *
     *
     * -> CUSTOM EXCEPTION >
     * 1. Create a vote(int age) method throws InvalidAgeException
     * 2. Create an InvalidAgeException class, which extends Exception
     * 3. Handle it inside the try which calls the method - vote(age);
     *
     * -> Custom exceptions often extend RunTimeException, because caller is not forced to throw
     * */

        // EXCEPTION CHAINING >>>>
    /*
        try {
            db.save();
        }
        catch(SQLException e) {
            throw new ServiceException(
                    "Unable to save user",
                    e
            );
        }

        public class ServiceException
                extends RuntimeException {

            public ServiceException(
                    String msg,
                    Throwable cause
            ) {
                super(msg, cause);
            }
        }
        */

        // iMP METHODS >
        // e.getMessage(), e.printStackTrace(), e.getCause(), e.getClass()

        // To get suppressed exceptions >
        // e.getSuppressed()
    }

    // Supporting methods for Error :
    public static int callMeAgain(){
        return callMeAgain();
    }



}
