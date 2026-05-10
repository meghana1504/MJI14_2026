package DSA.DataStructureImpl;
// Prereq : methods, memory management
// function calling itself

// Stack memory of function calls, unless function stops executing, it won't come out of the stack
// When function is done executing, the code flow will be returned back to the point where the function was called

    // | print3(3) |
    // | print2(2) |
    // | print1(1) |
    // | main( )   |  first in last out

// ALL Above functions have the same body, why do you copy it n times, just call the same function again


public class C0_RecursionKK {
    public static void main(String[] args) {
        //        print1();
        printn(2);
    }
        /*
            static void print1(){
                System.out.println(1); // n
                print2();              // n+1
            }
            static void print2(){
                System.out.println(2); // n
                print3();              // n+1
            }
            static void print3(){
                System.out.println(3); // here is only "n" make this as the termination point
            }
        */

    // What is the above code doing?
    // prints itself, and makes a call to a function which prints itself+1
    // we can have a simple function as below and call it again and again

    static void printn(int n){
        // called as BASE CONDITION > CONDITION where recursion stops making new calls
        if (n == 5) { // we want to stop calling the function when n becomes 5
            System.out.println(5);
            return;
        }
        System.out.println(n);
        printn(n+1); // every function call takes separate memory in the stack, keeps growing
                        // If base condition is missed, stack is full and runs out of memory :stackOverflowError


        // WHY Do we need recursion ?
            // Solves bigger complex problems in a simpler way ??
            // rec to ite ho skta
            // space compl is not constant
            // Breaks down bigger into small probs

        // VISUALISING RECURSION
            // Check out green notebook recursion section : recursion tree

        // FIND nth Fibbonaaci number
            // Identify if it can be divided into smaller problems or not ?
            // Fibb(n) = fibb(n-1) + fibb(n-2);
            // Recursive tree : GREEN NOTEBOOk





    }
}
