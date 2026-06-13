package DEV.a_JavaBasics.A2_GenericsInJava;

public class GenericBox <T> { // Me to JVM : I am not telLing you at compile time which object type I want to hold, I will tell you while creating the object
    T value;

    GenericBox(T val){
        this.value=val;
    }

    T getValue(){
        return value;
    }
}

/*
SYNTAX HELP

// We have box class which can ONLY integer values
public class Box {
    int value;

    Box( int val ){
        this.value = val;
    }

    public int getValue(){
        return value;
    }
}

* */
// SYNTAX HELP
// ArrayList<Integer> myNums = new ArrayList<Integer>();
