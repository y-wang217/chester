package Tracking.user.util;

import java.util.Arrays;

/**
 * Created by yale on 23/01/17.
 */
public class Bat3 {
    public int[] evenOdd(int[] nums) {
        int[] evens = new int[nums.length];
        int[] odds = new int[nums.length];
        int e=0, o=0;
        for(int i=0; i<nums.length;i++){
            if(nums[i] %2 == 0) {
                evens[e]=nums[i];
                e++;
            }
            else {
                odds[o] = nums[i];
                o++;
            }
        }
        System.out.println("    number of evens: " + e + "  number of odds: " + o);
        System.out.println("    arrays:  e: "+ Arrays.toString(evens) + "  o: " + Arrays.toString(odds));
        for(int i=0; i<e;i++){
            nums[i] = evens[i];
        }
        for(int i=0; i<o;i++){
            nums[i+e] = odds[i];
        }
        return nums;
    }

    public static void main(String [] argv){
        Bat3 b = new Bat3();
        System.out.println("Testing input: [1, 2, 2, 3, 4, 4]");
        int[] test = new int[]{1, 2, 2, 3, 4, 4};
        System.out.println("output: \""+ (Arrays.toString(b.evenOdd(test))));
    }

    int sum(int[] nums){
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        return sum;
    }
    int[] minAndMax(int[] nums){
        int[] m = new int[2];
        int min=nums[0],max=nums[0];
        for(int i=0;i<nums.length;i++){
            min = min<nums[i]?min:nums[i];
            max = max>nums[i]?max:nums[i];
        }
        m[0] = min;
        m[1] = max;
        return m;
    }

}
