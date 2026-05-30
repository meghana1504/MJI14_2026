package DEV.a_JavaBasics.A2_GenericsInJava;

// We have box class which can ONLY integer values
public class Box {
    Object value;

    public void setValue( Object val){
        this.value = val;
    }

    public Object getValue(){
        return value;
    }
}
