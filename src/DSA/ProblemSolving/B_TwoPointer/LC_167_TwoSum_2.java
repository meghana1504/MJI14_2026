package DSA.ProblemSolving.TwoPointer;
import java.util.Arrays;
public class LC_167_TwoSum_2 {
    public static void main(String args[]){
        System.out.println(Arrays.toString(twoSumSortedArray(new int[]{3,4,5,8,9},17)));
    }
    public static int[] twoSumSortedArray(int[] numbers, int target){
        // left pointer at the beginning and right at the end
        int left = 0;
        int right = numbers.length - 1;
        int sum = 0;
        int[] ans = new int[]{0,0};

        while(left < right){
            sum = numbers[left] + numbers[right];
            // if sum == target, just return those indices, "+1" because array is 1-indexed
            if(sum == target){
                ans[0] = left+1;
                ans[1] = right+1;
                break;
            }
            // Binary search logic
            // If the element to be searched is greater than the middle element, we continue our search in the second half of the array
            // Cause, eleemnt is nowhere to be found in the left half
            // SIMILARLY, if current sum is GREATER than target value, we need to decrease our sum to get near to the target
            // HENCE, we leave the last element and bring the right pointer to the second last element, and check the sum again
            // if it is stil not equal to last to last element, we move it by one to the left
            else if(sum > target){
                right = right -1;
            }
            // Same logic follows when sum is smaller than the target, then we want to leave the smaller elements behind to get closer to the targett
            // Hence we keep moving left pointer to the right and keep checking the target sum
            else
                left = left + 1;
            // And unless we hit the target sum, we keep moving right pointer to left and left pointer to right,
            // depending on how closer we are getting to the target
            // This is like BINARY SEARCH ALGORITHM
        }
        return ans;
    }
}
