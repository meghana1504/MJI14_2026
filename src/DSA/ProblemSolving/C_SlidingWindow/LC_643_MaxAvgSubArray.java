package DSA.ProblemSolving.C_SlidingWindow;

public class LC_643_MaxAvgSubArray {
    public static void main(String[] args) {
        System.out.println(maxAvgSubArrayBruteForce(new int[]{4433, -7832, -5068, 4009, 2830, 6544, -6119, -7126, -780, -4254, -8249, -9168, 9492, 402, 5789, 6808, 8953, 5810, -7353, 7933, 4766, 5182, -3230, -1989, 5786, 6922, -4646, 4415, -9906, 807, -6373, 3370, 2604, 8751, -9173, -2668, -6876, 9500, 3465, -1900, 4134, -1758, -1453, -5201, -9825, 4469, -1999, -1108, 1836, 3923, 6796, -5252, 9863, -5997, -3251, 9596, -3404, -540, 2826, -1737, 3341, -3623, -9885, 2603, -5782, 8174, 2710, 6504, -4128}, 59));
        System.out.println(maxAvgSubArraySlidingWindow(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    private static double maxAvgSubArrayBruteForce(int[] nums, int k) {
        // BRUTE FORCE SOLUTION [THIS WAS ACCEPTED IN LC > But takes 2615ms ]
        int maxSum = Integer.MIN_VALUE; // MISTAKE : Should NOT be ZERO, negative numbers ko 0 dega hamesha
        double average;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum = sum + nums[j];
            }
            maxSum = Math.max(sum, maxSum); // MISTAKE : I wrote this inside the inner loop by mistake, it was comparing everytime
        }
        average = (double) (maxSum) / k; // QUESTION : Why ONLY numerator is cast to (double?)
        return average;
    }

    private static double maxAvgSubArraySlidingWindow(int[] nums, int k) {
        int window_sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            window_sum = window_sum + nums[i];
        }
        for (int i = k; i <= nums.length - 1; i++) {
            window_sum = window_sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, window_sum);
        }
        return (double) maxSum / k;

    }
}