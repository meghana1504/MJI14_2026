package DSA.ProblemSolving;

/*abstract class Animal {

    Animal() {
        sound();
    }

    abstract void sound();
}

class Dog extends Animal {

    private String name = "Tommy";

    @Override
    void sound() {
        System.out.println(name.length());
    }
}

public class Testing {
    public static void main(String[] args) {
        new Dog();
    }
}*/

/*You actually asked the right question:

"Why would it go to Dog's sound?"

Because Java uses dynamic dispatch for normal instance methods, even during construction.

That's the non-obvious part most people miss.*/

interface A {
    static void hello() {
        System.out.println("A");
    }
}

class B implements A {
}

public class Testing {
    public static void main(String[] args) {
        B b = new B();
        // b.hello(); // Static method may be invoked on containing interface class only
    }
}