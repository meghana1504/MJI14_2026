package DSA.ProblemSolving;

public class LC_67_AddBinary {
    public static void main(String[] args) {
        System.out.println(addBinary("100","100"));
    }
    static String addBinary(String a, String b) {
        int diff = 0;
        int finLen = 0;
        int aLen = a.length();
        int bLen = b.length();
        String zeros="";
        StringBuffer ans = new StringBuffer();
        if(aLen==bLen){
            finLen=aLen;
        }
        else if(aLen>bLen){
            diff = aLen-bLen;
            finLen = aLen;
            for(int i=0; i<diff; i++){
                zeros=zeros+"0";
            }
            b=zeros+b;
        }
        else{
            diff = bLen-aLen;
            finLen = bLen;
            for(int i=0; i<diff; i++){
                zeros=zeros+"0";
            }
            a=zeros+a;
        }
        System.out.println(a+" + "+b+" todo ");

        int carry = 0;
        int sum = 0;
        for(int i=finLen-1; i>=0; i--){

            if(a.charAt(i)!=' ' && b.charAt(i)!=' '){
                if(i==0 && finLen>1){
                    

                }
               else if(a.charAt(i)=='0' && b.charAt(i)=='0'){
                    sum = carry ;
                    carry = 0;
                }
                else if(((a.charAt(i)=='0' && b.charAt(i)=='1') || (a.charAt(i)=='1' && b.charAt(i)=='0')) && carry==0){
                    sum = 1 ;
                    carry = 0;
                }
                else if(((a.charAt(i)=='0' && b.charAt(i)=='1') || (a.charAt(i)=='1' && b.charAt(i)=='0')) && carry==1){
                    sum = 0 ;
                    carry = 1 ;
                }
                else if(a.charAt(i)=='1' && b.charAt(i)=='1' && carry==0){
                    sum = 0 ;
                    carry = 1;
                }
                else if(a.charAt(i)=='1' && b.charAt(i)=='1' && carry==1){
                    sum = 1 ;
                    carry = 1;
                }
            }
            ans.append(sum);
            if(i==0 && finLen>1)
                ans.append(carry);
        }
//        System.out.println(ans.reverse().toString());
        return ans.reverse().toString();
    }
}
