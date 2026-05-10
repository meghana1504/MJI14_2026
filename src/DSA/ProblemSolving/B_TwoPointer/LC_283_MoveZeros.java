package DSA.ProblemSolving.B_TwoPointer;

import java.util.Arrays;

// TWO Poiner approach
public class LC_283_MoveZeros {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZeros(new int[]{1, 1, 0, 0, 12})));
    }
    public static int[] moveZeros(int[] arr){
        // Ek pointer j, keeps track of ALL zero elements and increments itself
        // Dusra pointer i to keep scanning the array

        int j=0;
        for(int i=0; i<arr.length; i++){
            // Bring all non zeros on the left of array by swapping them with Os (jyacha tracking j krtay)
            if(arr[i]!=0){
                // swap(a[j], a[i])
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++; // because we want to take the next zero to swap in the next iteration
            }
        }
        return arr;
    }
}
