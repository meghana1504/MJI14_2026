package DSA.ProblemSolving.Hashing_Arr_str;

import java.util.LinkedHashMap;
import java.util.Map;

public class LC_387_firstUniqueCharInString {

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode")); // expected o/p = 2 since v is the first unique character here
    }
    public static int firstUniqChar(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();  // Map of Character to its Frequency
        // NOTE the use of LinkedHashMap, Hashmap wont maintain order of characters as per the string

        // Filled the map
        for(int i=0; i<s.length(); i++){
            // If the character is already present, increase its frequency by 1
            if (freq.containsKey(s.charAt(i)))
                freq.put(s.charAt(i), freq.get(s.charAt(i))+1);
            // If the character is not present, put its frequency as 1
            else{
                freq.put(s.charAt(i),1);
            }
        }
        // Iterate over the hashmap's keys, retrieve the frequencies of current character
        for(char ch: freq.keySet()){
            int val = freq.get(ch);
            // when the frequency found is == 1, return its index from the input string
            if(val==1)
                return s.indexOf(ch);
        }
        return -1;
    }
}
