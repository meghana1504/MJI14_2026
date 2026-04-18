package DSA;

public class A0_ArrayImpl {
    private int size;
    private int[] mainArray;
    int capacity = 10;
    public A0_ArrayImpl(int capacity, int size) {
        this.size=size;
        this.capacity = capacity;
        mainArray = new int [this.capacity];
    }

    public static void main(String[] args){
        A0_ArrayImpl myArr = new A0_ArrayImpl(10,5);
        myArr.createArray();

        int index= 2;
        myArr.displayArray();
        System.out.println("\n"+"Removed element from index: "+index+" is: " +myArr.removeElement(index));
        myArr.displayArray();

        int elementToBeFound = 15;
        int answer = myArr.searchElement(elementToBeFound);
        if(answer==-1)
            System.out.println("\n"+ elementToBeFound + " is not present in the array !");
        else
            System.out.println("\n"+elementToBeFound + " is present at index "+answer);

        myArr.updateArray(2,35);
        myArr.displayArray();

        myArr.addAtEnd(24);
        myArr.displayArray();

        myArr.addAtIndex(0,57);
        myArr.displayArray();
    }

    public void createArray(){
        // User will create the array
        while (size > capacity) {
            resizeArray();
        }
        for (int i = 0; i < size; i++) {
            mainArray[i] = (i + 1) * (i + 1);
        }
    }
    public void displayArray(){
        System.out.println(" << Array >> ");
        for(int i=0; i<size; i++){
            System.out.print(mainArray[i]+" " );
        }
    }
    public int removeElement(int index){
        int deletedElement = mainArray[index];
        for(int i=index; i<size-1; i++)
            mainArray[i] = mainArray[i+1];
        size = size-1;
        mainArray[size] = 0;
        return deletedElement;
    }

    public int searchElement(int element){
        int answerIndex = -1;
        for(int i=0; i<size; i++){
            if(element == mainArray[i]) {
                answerIndex = i;
                break;
            }
        }
        return answerIndex;
    }

    public void updateArray(int index, int newElement){
        mainArray[index] = newElement;
    }

    public void addAtEnd(int newElement){
        if(size >= capacity){
            resizeArray();
        }
        mainArray[size] = newElement;
        size=size+1;

        //        size = size+1;
        //        int[] newArray = new int[size];
        //        for(int i=0; i<size; i++){
        //            newArray[i] = mainArray[i];
        //        }
        //        newArray[size-1] = newElement;
        //        mainArray = newArray;

    }
    public void addAtIndex(int index, int value){
        if(size>=capacity)
            resizeArray();
        size=size+1;
        for(int i= size-1; i>index ; i--){
            mainArray[i]= mainArray[i-1];
        }
        mainArray[index] = value;
    }
    private void resizeArray() {
        // create a new array, copy the elements and reference
        capacity = capacity*2;
        int newArray[] = new int[capacity];
        for(int i=0; i<size; i++){
            newArray[i] = mainArray[i];
        }
        mainArray = newArray;
    }
}
