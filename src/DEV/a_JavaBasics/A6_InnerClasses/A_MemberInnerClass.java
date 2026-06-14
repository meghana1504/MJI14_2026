package DEV.a_JavaBasics.A6_InnerClasses;

public class A_MemberInnerClass {
    // Check out A_Car.java

    // Why do we need inner class ??
    /*
    * Assume, we have a class Car
    * Car will have engine INSIDE IT
    * Usually if we create Car and Engine as separate classes, we will have to create an Engine inside Car class
    * BUT, Car will ALWAYS have an Engine, so why not create it inside the Car class directly ??
    *
    * That is why, we create class Engine INSIDE the Car class, like any other member of Car class
    * That is why it is called "MEMBER" inner class
    *
    * */
    public static void main(String[] args) {
        A_Car myCar = new A_Car("BMW X3");
        // Below syntax is used for creating objects of inner classes, they will ALWAYS depend on Outer class
        A_Car.Engine engine = myCar.new Engine();
        engine.start();
        engine.stop();
    }

}
