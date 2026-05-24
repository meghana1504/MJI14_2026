package DSA.DataStructureImpl;

import java.util.Arrays;

public class B1_SortingAlgos {
    public static void main(String[] args) {
        B1_SortingAlgos sort = new B1_SortingAlgos();
//        System.out.println("Bubble Sort "+ Arrays.toString(sort.bubbleSort(new int[]{-1, 10, 4, 0, -23})));
//        System.out.println("Selection Sort "+Arrays.toString(sort.selectionSort(new int[]{-1, 10, 4, 0, -23,  8, -66})));
//        System.out.println("Insertion Sort "+Arrays.toString(sort.insertionSort(new int[]{-1, -30, 109, 4, 70, -78})));
        System.out.println("Merge Sort "+Arrays.toString(sort.mergeSort(new int[]{4,8,2,1})));
    }

    // Bubble sort
    public int[] bubbleSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] > arr[j]){
                    arr[i] = arr[i] + arr[j];
                    arr[j] = arr[i] - arr[j];
                    arr[i] = arr[i] - arr[j];
                }
            }
        }
        return arr;
    }

    // Selection sort
    // [CONCEPT] :
    // 1. "SELECT" A max or min element, PUT it at its CORRECT POSITION, (End or Start)
    // 2. Do the SAME in the next iteration, but dont scan previous min or max WHICH IS aT END OR LAST, CAUSE THAT PART IS sorted already
    public int[] selectionSort(int[] arr){
        int len = arr.length;
        int ind=-1;
        for(int i=0; i<len; i++){
            int max = Integer.MIN_VALUE;
            for(int j=0; j<len-i; j++){
                if(max < arr[j]){
                    max = arr[j];
                    ind = j;
                }
            }
            int temp = arr[ind];
            arr[ind] = arr[len-i-1];
            arr[len-i-1] = temp;
        }
        return arr;
    }

    // INSERTION SORT
    // [CONCEPT] = Outer loop 0 to n-2 , inner loop j=i+1 to 0 , j is ALWAYS i+1
    // Left array is ALWAYS sorted, and we "INSERT" one by one element from the RIGHT array to the LEFT Array (swapping)
    // EK Last element check krne ke bad agr loop break kia toh chalega cause left array toh sorted hai already

    public int[] insertionSort(int[] arr){
        int n = arr.length;
        for(int i=0; i<=n-2; i++){
            for (int j=i+1; j>0; j--){
                if(arr[j-1]>arr[j])
                {
//                    arr[j-1] = arr[j-1] + arr[j];
//                    arr[j] = arr[j-1] - arr[j];
//                    arr[j-1] = arr[j-1] - arr[j];
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;

                }
            }
        }
        return arr;
    }
    // In selection sort, we get to DECIDE the element to be processed (max one, not the immediate one, LOGICAL choice), but its place is decided(end, no choice)
    // In Insertion sort, we have to DECIDE the place of the element in the left array(keep sorting, LOGICAL choice), but the incoming element HAS to be processed (no choice)

    public int[] mergeSort(int[] arr){
        if(arr.length==1)
            return arr;
        int n = arr.length;
        int mid = n/2;
        int[] left = mergeSort(Arrays.copyOfRange(arr,0,mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr,mid,n));
        return merge(left, right);
    }
    static int[] merge(int[] left, int[] right){
        int i=0;
        int j=0;
        int total = left.length+right.length;
        int k=0;
        int[] ans = new int[total];
        while(i < left.length && j<right.length ){
                if(left[i] < right[j]){
                    ans[k] = left[i];
                    i++;
                }
                else {
                    ans[k] = right[j];
                    j++;
                }
                k++;
        }
        // if one of the arrays is not complete
        while(i < left.length){
                ans[k] = left[i];
                i++;
                k++;
            }
        while(j < right.length){
                ans[k] = right[j];
                j++;
                k++;
            }
        return ans;
    }

    
}
