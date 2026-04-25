package DSA.DataStructureImpl;

// TODO : To refactor while revisiting (edge cases)
public class A2_LinkedList {
    public static void main(String[] args) {
        A2_LinkedList myList = new A2_LinkedList();
        myList.addNodeAtEnd(10);
        myList.addNodeAtEnd(45);
        myList.addNodeAtEnd(89);
        myList.addNodeAtEnd(78);
        myList.addNodeAtEnd(79);
        myList.displayList();
        myList.deleteNodeAtEnd();
        myList.displayList();
        myList.replaceNodeAtPos(3,5);
        myList.displayList();
        myList.addNodeAtPos(2,75);
        myList.displayList();
        myList.deleteNodeFromPos(5);
        myList.displayList();
        System.out.println("size of list is : "+myList.sizeOfList());
    }
    static class Node{
        int value;
        Node next;
        Node(int value){
            this.value=value;
//            this.next=null;
        }
    }

    Node head;

    public Node addNodeAtEnd(int value){
        Node temp = new Node(value);
        if (head==null){
            head=temp;
            head.next=null; // JAVA Does this by default, btw
            return head;
        }
        else{
            Node curr = head;
            while(curr.next!=null){
//                System.out.print("curr "+curr.value+" -> ");
                curr = curr.next;
            }
            curr.next=temp;
            return curr;
        }
    }
    public void deleteNodeAtEnd(){ // MISTAKE : Use of next.next is crucial
        Node temp = head;
        if(temp!=null){
            while (temp.next.next!=null)
                temp=temp.next;
        }
        temp.next=null;
    }

    public void deleteNodeFromPos(int pos){
        Node curr = head;
        int count = 1;
        if(pos<=sizeOfList()){
            while(count<pos-1){
                curr = curr.next;
                count++;
            }
        curr.next = curr.next.next;
        }
    }

    public void replaceNodeAtPos(int pos, int value){
        int count = 1;
        Node curr = head;
        Node temp = new Node(value);
        if(pos<=sizeOfList()) {
            while (count != pos - 1) {
                curr = curr.next;
                count++;
            }
        }
        temp.next= curr.next.next;
        curr.next=temp;
    }
    public void addNodeAtPos(int pos, int value){
        int count = 1;
        Node curr = head;
        Node temp = new Node(value);
        if(pos<=sizeOfList()) {
            while (count < pos ) {
                curr = curr.next;
                count++;
            }
        }
        temp.next= curr.next;
        curr.next=temp;
    }

    private int sizeOfList(){
        Node temp = head;
        int size=0;
        while(temp!=null){
            size=size+1;
            temp=temp.next;
        }
        return size;
    }
    public void displayList(){
        if (head==null)
            System.out.println("\n Empty Linked List ! ");
        else {
            Node temp = head;
            System.out.println("\n Linked List is : ");
            while(temp!=null){ // MISTAKE, temp->next krat hote
                System.out.print(temp.value+" -> ");
                temp=temp.next;
            }
            System.out.print("null");
        }
    }



}
