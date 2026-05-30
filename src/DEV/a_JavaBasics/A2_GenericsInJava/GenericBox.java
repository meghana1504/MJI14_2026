package DEV.a_JavaBasics.A2_GenericsInJava;

import java.util.ArrayList;

public class GenericBox <T> { // I am not teliing you at runtime which object type I want to hold, I will tell you while creating the object
    T value;

    GenericBox(T val){
        this.value=val;
    }

    T getValue(){
        return value;
    }
}

// SYNTAX HELP
// ArrayList<Integer> myNums = new ArrayList<Integer>();
