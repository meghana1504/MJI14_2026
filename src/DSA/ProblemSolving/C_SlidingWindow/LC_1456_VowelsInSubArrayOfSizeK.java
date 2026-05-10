package DSA.ProblemSolving.C_SlidingWindow;

public class LC_1456_VowelsInSubArrayOfSizeK {
    public static void main(String[] args) {
        System.out.println(maxVowels("weallloveyou", 7));
    }
    public static int maxVowels(String s, int k) {
        int count = 0;
        for(int i=0; i<k; i++){
            if(isVowel(s.charAt(i)))
                count++;
        }
        int maxCount = count;
        // count = 0;
        for(int i=k; i<s.length(); i++){
            if(isVowel(s.charAt(i))) {
                count++;
            }
            if(isVowel(s.charAt(i-k+1))) {
                count--;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
    private static boolean isVowel(char c){
        return ( c=='a' || c=='e' || c=='i' || c=='o' || c=='u' );
    }

}


// 1. Active recall :
// right after solving a problem, replay that in mind
// Explain it out loud to yourself
// 2. Space repeatition
// Pattern recognition kelie revision strategy
// 1 day and 7 day revision before getting to new problems (weekend)
// DSA Sheet (Problem link, comment)
// Try to frame the solution in your mind, refer to the comments in your sheet, code it again , identify week areas

// 2 hrs DSA daily - Mon to Fri - Solve regular leetcode Problems, Saturday- Only revision, Sunday - Virtual Contests, Company wise problems ?

// DSA SHEET ?
//