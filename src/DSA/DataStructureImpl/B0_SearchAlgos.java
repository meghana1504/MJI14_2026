package DSA.DataStructureImpl;

import java.sql.SQLOutput;
import java.util.Arrays;

public class B0_SearchAlgos {
    // Linear search
    public static void main(String[] args) {
        B0_SearchAlgos search = new B0_SearchAlgos();
       // search.linearSearch(new int[]{5, 6, 7, 8, 9}, 8);
//        search.binarySearch(new int[]{8,-1,45,7, 20}, 8);
        System.out.println(search.binarySearchRec(new int[]{8,-1,45,7, 20}, 8,0,4));
    }
    public int linearSearch(int[] arr, int key){
        for(int i=0; i<arr.length; i++){
            if(arr[i] == key) {
                System.out.println(key + " found at "+ i);
                return i;
            }
        }
        System.out.println(key+" not found");
        return -1;
    }

    // Binary Search
    public int binarySearch(int[] arr, int key){
        Arrays.sort(arr);
        for(int i: arr)
            System.out.print(" "+i);
        System.out.println("\n");
        int len = arr.length;
        int low = 0;
        int high = len-1;
        int mid = (low+high)/2;
        while(low<high) {
            if (key == arr[mid]) {
                System.out.println(key + " found at " + mid);
                return mid;
            }
            if (key < arr[mid]) {
                high = mid;
                mid = (low + high) / 2;
            }
            if (key > arr[mid]){
                low = mid;
                mid = (low + high) / 2;
            }
            else {
                System.out.println(key+" not found");
            }
        }
        return -1;
    }

    // Binary Search using Recursion

    public int binarySearchRec(int[] arr, int target, int low, int high){
        // ALL the variables in the iterative approach, we are now using as parameters to the method
        if(low<=high) {  // Realisation : we dont need while, because recursion already works like a loop
            int mid = (low+high)/2;
            if (target == arr[mid]) {
                return mid;
            }
            if (target < arr[mid]) {
                return binarySearchRec(arr, target, low, mid-1); //Each recursive call must return its result back up the call stack. - I WAS MISSING THIS
            }
            if (target > arr[mid]) {
                return binarySearchRec(arr, target, mid+1, high);
            }
        }
        return -1;

    }
}
