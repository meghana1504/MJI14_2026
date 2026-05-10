package DSA.ProblemSolving.B_TwoPointer;

public class LC_26_DupsInASortedArr {
    public static void main(String[] args) {
        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }
    static int removeDuplicates(int[] nums) {
//        int[] cpy = new int[nums.length];
//        cpy[0] = nums[0];
        int count = 1;

        for(int i=1; i<nums.length; i++){
            boolean isDup = false;
            for(int j=0; j<i; j++){
                if(nums[j] == nums[i]){
                    isDup = true;
                    break;
                }
            }
            if (!isDup)
            {
                nums[count] = nums[i];
                count++;
            }
        }
         for(int i=0; i<count; i++){
             System.out.print(nums[i]+" ");
         }
        return count;

    }
}
