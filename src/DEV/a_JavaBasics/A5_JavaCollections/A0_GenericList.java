package DEV.a_JavaBasics.A5_JavaCollections;

import java.util.Iterator;

public class A0_GenericList<T> implements Iterable<T>{
    private T[] items;
    private int size;

    A0_GenericList(){
        size = 0;
        items = (T[])new Object[100];
    }

    public void add(T val){
        items[size++] = val;
    }

    public T getItem(int ind){
        return items[ind];
    }

    public static void main(String[] args) {
        A0_GenericList<Integer> list = new A0_GenericList<>();
        list.add(10);
        list.add(15);
        list.add(34);

        // BELOW Methods are not working : WHY ?
           /* for(int x: list){
                System.out.println(x);
            }*/
        /*
        * BECAUSE WE HAVE NOT IMPLEMENTED Iterable interface, so the list is not yet ITERABLE
        * It makes the given class "Iterable" , meaning u can use a for loop on that class
        * Once you impl Iterable, you have to impl the method iterator(), which returns the Iterator object, this object is used to iterate over the collection
        * Ab yeh iterator object kaha se laaoge?
        * For that we have to implement the "Iterator" interface ke 2 methods - hasNext() and next() - private inner class bana kr impl kro
        *
        * */

//        Below code block

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());

//        Can be replaced with this for loop
        System.out.println("For loop starting... ");
        for(int x:list)
            System.out.println(x);

//        Gives the same output literally
    }
    @Override
    public Iterator<T> iterator() {
        MyGenListIterator myIt = new MyGenListIterator(this); //(this)
        return myIt;
    }

    private class MyGenListIterator implements Iterator<T>{

        private A0_GenericList<T> list;
        private int index = 0;
        public MyGenListIterator (A0_GenericList<T> list)
        {
            this.list = list;
        }
        @Override
        public boolean hasNext() {
            System.out.println("hasNext called");
            return index < list.size;
        }
        @Override
        public T next() {
            System.out.println("next() called");
            return list.items [index++];
        }
    }
}


