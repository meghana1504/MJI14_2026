package DEV.a_JavaBasics.A4_ComparableComparator;

import java.util.ArrayList;
import java.util.List;

// I want to tell my class directly ki kaise sorting krti hai, toh use Comparable in
// Comparable has compareTo(Object obj) method, Because the class directly implements it, so we already have ONE current object to "Compare to"
// WE Define natural ordering for sorting custom class objects using compareTo() method
// So the custom class should implement Comparable<CustomClass>
public class demoComparable {
    public static void main(String[] args) {
        Student1 s1 = new Student1(21, "Krisna", 10);
        Student1 s2 = new Student1(10, "Raju", 8);
        Student1 s3 = new Student1(54, "Jaggu", 9);
        List<Student1> students1 = new ArrayList<>();
        students1.add(s1);
        students1.add(s2);
        students1.add(s3);
        students1.sort(null);
        System.out.println(students1);
    }

}


class Student1 implements Comparable<Student1>{

    private int rollNo;
    private String name;
    private int gpa;

    Student1(int rollNo, String name, int gpa){
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

    @Override
    public int compareTo(Student1 student) {
        // just like compare() in Comparator, the contract is same
        // if o1-o2 is -ve, put o1 before o2, else o1 after o2
        return this.getGpa()-student.getGpa();
    }

}

/*
* USAGE OF Comparable v/s Comparator
* For custom class - Comparable, For non-custom class - myComp implements Comparator
*
* Use Comparable for Custom classes, and define compareTo() method in that class
*
* For system defined classes, like Integer, they have already implemented compareTo() method by implementing Comparable interface an we cant change Integer ka compareTo() method
* So, for those, create customComparator and implement Comparator, define compare() method and pass object of customComparator or Lambda expression to List.sort(myList, new customComparator())
* where, myList<Integer>
* So we can have a choice of deciding the order or sorting even though the class is not user defined

* */
