package Tracking.user.util;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by yale on 19/01/17.
 */
public class Bat2 {

    public int[] squareUp(int n) {
        int[] m = new int[n*n];
        for(int i=n-1;i>=0; i--){
            for(int j=n-1;j>=0;j--){
                if(j+1>=n-i){
                    m[i+j*n]=n-i;
                }
                //System.out.println("    inprocess: "+Arrays.toString(m));
            }
        }
        return m;
    }


    public static void main(String [] argv){
        Bat2 b = new Bat2();
        System.out.println("Testing input: [4, 2, 4, 5, 5]");
        int[] test = new int[]{4, 2, 4, 5, 5};
        System.out.println("output: "+Arrays.toString(b.squareUp(4)));
    }
}
