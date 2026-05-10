package DSA.ProblemSolving;
import java.util.*;
// PROBLEM LINK >>
// https://www.geeksforgeeks.org/dsa/print-all-the-duplicates-in-the-input-string/#approach-1-using-sorting-onlogn-time-and-o1-space
public class findDuplicatesInAString {
    public static void main(String[] args) {
        findDups("Meghana");
    }
    static void findDups(String word){
        String[] letters = word.split("");
        HashMap<String, Integer> letterToCount = new HashMap<>();
        for(String s:letters){
            if(!letterToCount.containsKey(s)){
                letterToCount.put(s,1);
            }
            else{
                letterToCount.put(s,letterToCount.get(s)+1);
            }
        }

        for(Map.Entry<String, Integer> entry: letterToCount.entrySet()){
            if(entry.getValue() > 1){
                System.out.print("[ "+entry.getKey()+ ","+ entry.getValue()+" ],");
            }
        }
    }
}
