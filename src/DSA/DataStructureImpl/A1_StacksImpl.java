package DSA.DataStructureImpl;

public class A1_StacksImpl {
    private int top=-1;
//    private int size =0; // MISTAKE FIX :  Got rid of the variable size entirely and used top and arr.length wherever possible
    private final int MAX_CAPACITY = 4; // Size can never be more than MAX_CAPACITY
    int[] myStack = new int[MAX_CAPACITY];
    A1_StacksImpl(){

    }
    public static void main(String[] args) {
        A1_StacksImpl myStack = new A1_StacksImpl();
        myStack.pushElement(10);
        myStack.pushElement(15);
        myStack.showStack();
        myStack.peek();
        myStack.pushElement(50);
        myStack.pushElement(88);
        myStack.showStack();
        myStack.peek();
        myStack.pushElement(70);
        System.out.println(myStack.popElement());
        myStack.showStack();
        myStack.peek();
        myStack.showStack();
    }
    public void pushElement(int value){
        System.out.println("Trying to push "+value+" to the stack ");
        if(!isFull() ){
            top++;
//            size++;
            myStack[top] = value;
        }
        else throw new RuntimeException("[ERROR] : Stack is full ! Try popping ");
    }
    public int popElement(){
        if(!isEmpty()) {
        int ans= myStack[top]; // MISTAKE FIXED : Get top first, then decrease top and size
            top--;
//            size--;
            return ans;
        }
        else throw new RuntimeException("Stack is empty");
    }
    public boolean isFull(){
//        System.out.println("Inside isFull : size: "+size+" MAX_CAPACITY: "+MAX_CAPACITY);
        return (top==MAX_CAPACITY-1); // MISTAKE FIXED : I was doing top==size earlier, does not make sense
    }
    public boolean isEmpty(){
//        System.out.println("Inside isFull : size: "+size+" MAX_CAPACITY: "+MAX_CAPACITY);
        return top == -1;
    }
    public void showStack(){
        System.out.println("============= Stack is now =============");
        for(int i=0; i<=top; i++){ // MISTAKE FIXED :  I was doing i<=arr.length, usme aage ke 0 bhi print ho jaate, nt good
            System.out.print(myStack[i]+" ");
        }
        System.out.println("============= End of Stack =============");
    }
    public int peek(){ // MISTAKE FIXED : I was not returning int from peek, ideally it should be the case
        System.out.print("peeking... ");
        if(isEmpty())
            throw new RuntimeException("Stack is empty, can't peek ");
        return myStack[top];
    }

}
