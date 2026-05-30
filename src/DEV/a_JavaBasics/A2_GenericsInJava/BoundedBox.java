package DEV.a_JavaBasics.A2_GenericsInJava;
import java.awt.print.Printable;

public class BoundedBox <N extends Number & Printable> { // This means BoundedBox will hold a type of class which extends Number class and Implements Printable interface
    N value;

    BoundedBox(N val){
        this.value=val;
    }

    N getValue(){
        return value;
    }
}

// Simply it can be like below :
/*
public class BoundedBox <N extends Number>{

}
*/
