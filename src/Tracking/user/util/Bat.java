package Tracking.user.util;

/**
 * Created by yale on 18/01/17.
 */
public class Bat {
    public String notReplace(String str) {
        if(str.length()<=1) return str;
        if(str.length()==2) return str.equals("is")?"not":str;
        String ans="";
        int cur = 0;
        for(int i=0; i<str.length(); i++){
            //iterate through 4 character blocks
            if(i==0){
                System.out.println("    "+ str.substring(0,2) + "| \\" + str.charAt(2));
                //special case, look through 3 character block
                if(str.substring(0,2).equals("is") && !Character.isLetter(str.charAt(2))){
                    ans = "is not"+Character.toString(str.charAt(2));
                    cur=3;
                    continue;
                }else continue;
            }
            if(i==str.length()-2){
                //special case, check 3 char block
                System.out.println("    "+ str.charAt(str.length()-3) + " " + str.substring(str.length()-2));
                if(str.substring(str.length()-2).equals("is") && !Character.isLetter(str.charAt(str.length()-3))){
                    ans+=str.substring(cur,i) + "is not";
                    break;
                }else {
                    ans+=str.substring(cur);
                    break;
                }
            }
            System.out.println("    "+ str.charAt(i-1) + "/ |" + str.substring(i,i+2) + "| \\" + str.charAt(i+2));
            if(str.substring(i,i+2).equals("is")){
                if(!Character.isLetter(str.charAt(i-1)) && !Character.isLetter(str.charAt(i+2))){
                    //"is" found
                    ans+=str.substring(cur,i)+"is not"+str.substring(i+2,i+3);
                    cur=i+3;
                }
            }
        }
        return ans;
    }



    public static void main (String [] args){
        Bat b = new Bat();
        System.out.println("Test 1: input: is This is isabell is not is");
        System.out.println("Output: " + b.notReplace("is This is isabell is not is"));



    }
}
