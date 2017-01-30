package Tracking.user.util;

/**
 * Created by yale on 23/01/17.
 */
public class BatTemp {
    public int[] fix45(int[] nums) {
        int lastFive = findFive(nums, 0);
        //very good restrictions to make things easy
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 4){
                int newFive = findFive(nums, lastFive);
                nums = swap(nums, i+1, findFive(nums, lastFive));
                lastFive = findFive(nums, i+1>newFive?i+2:newFive+1);
            }
        }
        return nums;
    }
    public int[] swap(int[] n, int a, int b){
        //swap values of n[a] and n[b]
        int temp = n[a];
        n[a]=n[b];
        n[b]=temp;
        return n;
    }
    public int findFive(int[] n, int start){
        int j = start;
        while(j<n.length && n[j]!=5){
            j++;
        }
        return j;
    }
}
