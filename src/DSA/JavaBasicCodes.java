package DSA;

import java.util.Arrays;

public class JavaBasicCodes {

    public static void main(String[] args){
        JavaBasicCodes code = new JavaBasicCodes();
        try {
            // Take user input and add 2 numbers
//            Scanner scan = new Scanner(System.in);
//            System.out.println("Enter first number");
//            int num1 = scan.nextInt();
//            System.out.println("Enter second number");
//            int num2 = scan.nextInt();
//            System.out.println(code.addTwoNumbers(num1, num2));
            code.swapTwoNumber(7,8);
            System.out.println(code.factorial(20));
            System.out.println(code.factorialIt(20));
            System.out.println(code.addTwoComplexNos(5,6,8,19));
            System.out.println(code.nthFibbonaci(7));
            System.out.println(code.sumOfFibbonacci(7));
            System.out.println(Arrays.toString(code.sumOfSubbArr(new int[]{1, 2, 3, 4, 5}, 1)));
            code.mergeUnsortedArrays(new int[]{2, 0, 1}, new int[]{4, -1, 5, 1});

            int x = 5;
            System.out.println(x++ + ++x);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {
            code=null;
        }

    }
    public int addTwoNumbers (int a, int b){
        return a+b;
    }

    public void swapTwoNumber(int a, int b){
        // 5 6
        // a = 11
        // b = a - b = 11 - 6 = 5
        // a = a - b = 11 - 5 = 6
        a = a+b;
        b = a-b;
        a = a-b;
        System.out.println(a+" "+b);
    }

    public long factorial (int n){
        long fact =0;
        if(n==0 || n==1)
            fact = 1;
        else {
            fact = n * factorial(n-1);
        }
        return fact;
    }

    public long factorialIt (int n){
        long fact = 1;
        if (n==0 || n==1){
            return fact;
        }
        for (int i=1;i<n+1;i++){
            fact = fact * i;
        }
        return fact;
    }

    public String addTwoComplexNos(int r1, int c1, int r2, int c2){
        System.out.println("First complex no is "+r1+" + "+c1+"i");
        System.out.println("First complex no is "+r2+" + "+c2+"i");
        return "Addition is : "+ (r1+r2) + " + "+ (c1+c2)+"i";
    }

    // 0 1 1 2 3 5 8 13 21
    public int nthFibbonaci(int n) {
        int fib;
        if (n <= 1)
            return n;
        fib = nthFibbonaci(n - 1) + nthFibbonaci(n - 2);
        return fib;
    }
    public int sumOfFibbonacci(int n){
        int a=0; int b=1; int curr=0;
        if (n <= 0) return 0;
        if (n == 1) return 0;
        int sum=a+b;
        for(int i=2; i<n; i++){
            curr = a+b;
            sum = sum+curr;
            a=b;
            b=curr;
        }
        return sum;
    }

    //java8-Collections-GroupWordsByFirstLetter
    //Given an integer array and an integer k, for each index, calculate the sum of the current element and up to the previous k-1 elements
    // [1,2,3,4,5,6,7]
    // if k=3
    public int[] sumOfSubbArr(int[] arr, int k){
        int n = arr.length;
        int[] sums = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++){
            if(i<k)
                sum = sum + arr[i];
            else{
                sum = sum + arr[i] - arr [i-k];
            }
            sums[i] = sum;
        }
        return sums;
    }

    // Merge two unsorted arrays into a one big sorted array
    // arr1 = [6,1,8], arr2 = [9,0,-1,3]
    // Ans = [-1,0,1,3,6,8,9]

    public int[] mergeUnsortedArrays(int[] arr1, int[] arr2){
        int size1 = arr1.length;
        int size2 = arr2.length;
        int[] ans = new int[size1+size2];
        for(int i=0;i<size1+size2; i++){
            if(i<size1)
                ans[i] = arr1[i];
            else
                ans[i] = arr2[i-size1];
        }
//        Arrays.sort(ans);
        for(int i=0;i<size1+size2; i++){
            for(int j=i+1; j<size1+size2; j++){
                if(ans[i] > ans[j]) {
                    int temp = ans[i];
                    ans[i] = ans[j];
                    ans[j] = temp;
                }
            }
        }
        System.out.println("mergeUnsortedArrays: "+Arrays.toString(ans));
        return ans;
    }


}
