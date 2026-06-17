package DEV.a_JavaBasics.A7_JAVA_8_Features.C_DefaultAndStaticMethodsInInterface;

public class A_StaticDefaultDemo implements IDemo{
    public static void main(String[] args) {
        IDemo idemo = new A_StaticDefaultDemo();
        idemo.display();
        IDemo.show();
        idemo.demo();
    }

    @Override
    public void demo() {
        System.out.println("A_StaticDefaultDemo:: demo() called - interface method ");
    }
}

@FunctionalInterface
interface IDemo{
    void demo();
    default void display(){
        System.out.println("IDemo:: Default method display()");
    }

    static void show(){
        System.out.println("IDemo:: Static method show()");
    }
}

/*
* NOTES
* ---- Why do we need Default methods in an interface ?
* -> provides backward compatibility and still allows customization
* -> Adding new functionality to existing interfaces without breaking existing implementer classes
* -> Backward Compatibility - ?
* -> Providing common behaviour shared by implementer classes, or a default implementation, which they can also override
* eg; u hv an existing interface of 1000 classes, but a new requirement arrives like, every processor should have a basic implementation of void validate(),
*     but all classes are not forced to implement it
*
* ---- Why do we need Static methods in an interface ?
* -> They belong to the interface itself, OBVIOUSLY
* -> They are used for utility/helper operations.
* -> Methods that are closely related to interface
*
* -> Interface static methods must be called using the interface name:
*
* */