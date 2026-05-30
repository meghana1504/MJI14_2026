package DEV.a_JavaBasics.A2_GenericsInJava;

public class GenericMethod {
    // Lets say I want to create a method which tells student's marks, but it can be float, int or if he has failed, it will say "failed"

    public static <T> T gpaCalc(T perc){
       return perc;
    }

    // Method overloading example with generics >
    public static void display(String message){
        System.out.println(message);
    }

    public static <N extends Number> void display(N num){ // bounded only to Number and its Subclasses
        display("Entering number display --->");
        System.out.println("Number from generic display : "+num);
    }


    public static void main(String[] args) {
        System.out.println(gpaCalc(89));
        System.out.println(gpaCalc(45.5));
        System.out.println(gpaCalc("FAIL"));

        display("How are you doing ! "); // Calls the String message display
        display(10.5); // Calls the generic display
        display(100);
    }
/*
    COMPARE
    int square(int x)
    <T> T identity(T x)
    Think of <T> as introducing a placeholder type name, and the second T as using that placeholder as the method's return type.
*/

}
