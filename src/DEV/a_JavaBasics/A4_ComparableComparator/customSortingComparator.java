package DEV.a_JavaBasics.A4_ComparableComparator;


import java.util.*;

// To sort custom objects we use Comparator and Comparable
// Both are Functional interfaces
// Comparator - compare(Object obj1, Object obj2)

// ----------------------- Comparator ----------------------
// We implement Comparator and pass our class ka object to list.sort() method, to sort it in desired way- like asc or dsc
// or based on certain parameter value - like age, id in case of custom classes
// I tell list's sort function to sort the list how I want using myComparator class where I implement compare() method in the way I want
// if i do o1-o2 - ASC
    // contract of the compare() method >
    // if returns -ve, put o1 before o2
    // if returns +ve, put o1 after  o2

/*
    Sort Algorithm:
        "Hey Comparator,
         should 10 come before 15?"

    Comparator:
        compare(10,15) = -5

    Sort Algorithm:
        "Okay, 10 before 15."
* */

public class customSortingComparator {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(10);
        nums.add(15);
        nums.add(8);
        Collections.sort(nums); // Sorts the list by ASC order, Internally it does: Collections.sort(nums, Comparator.naturalOrder());
        System.out.println(nums);
        nums.sort((o1,o2)->(o1-o2)); // nums.sort() need a customComparator object OR lambda expression for impl of compare() function
        System.out.println("Implemented compare() using lambda expression "+nums);

        List<Integer> nums2 = Arrays.asList(7,2,9,10);
        nums2.sort(new customComparator());
        System.out.println("Implemented compare() using customComparator class "+nums2);

        // Create 3 objects of class Student, and sort them based on 1.rollNo and 2. Name
        Student s1 = new Student(21, "Krisna", 10);
        Student s2 = new Student(10, "Raju", 8);
        Student s3 = new Student(54, "Jaggu", 9);
        List<Student> students = Arrays.asList(s1,s2,s3);
        students.sort((o1,o2)->(o1.getRoll()-o2.getRoll()));
        System.out.println("Sorted students based on their roll numbers, compare impl using lambda "+students);
        students.sort(new studentComparator());
        System.out.println("Sorted students based on their names, compare method written "+students);

        students.sort(Comparator.comparing(Student::getGpa).thenComparing(Student::getName));
        System.out.println("Sorted students based on their GPA "+students);


    }

}

class studentComparator implements Comparator<Student>{
    public int compare(Student o1, Student o2){
        return o1.getName().length() - o2.getName().length();
        // "krisna" "Raju" o1 and o2
        // if -ve, put o1 before o2, if +ve o1 after o2
    }
}


class customComparator implements Comparator<Integer> { // Also tell Comparator what type of object it will be sorting
// Make sure to pass the Objects of type that you want to sort
//
    @Override
    public int compare(Integer o1, Integer o2) { // o1=10 o2=15
//        return o2-o1; // this implementation is not safe for eg; Integer.MAX_VALUE - (-1)
        return Integer.compare(o1,o2);
    }
    // Contract ?
    // if returns -ve, put o1 before o2
    // if returns +ve, put o1 after  o2

}

class Student{

    private int rollNo;
    private String name;
    private int gpa;

    Student(int rollNo, String name, int gpa){
        this.rollNo = rollNo;
        this.name = name;
        this.gpa = gpa;
    }

    int getRoll(){
        return rollNo;
    }

    String getName(){
        return name;
    }

    int getGpa(){
        return gpa;
    }

    @Override
    public String toString() {
        return "Roll Number: "+this.getRoll() + " Name: "+this.getName()+" and GPA is: "+this.getGpa() ;
    }
}
