package Tracking.user.action;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yale on 23/09/16.
 */
public class DefaultAction {
    private static int _SIZE = 10;
    public static ArrayList<Integer> quickSort(ArrayList<Integer>a){
        if(a.size() <=1){
            return a;
        }
        int pivot = a.size()/2;
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        int equalToPivot = 0;
        for(int number : a){
            if(number > a.get(pivot)){
                left.add(number);

            }else if (number < a.get(pivot)){
                right.add(number);

            }else{
                equalToPivot++;
            }
        }
        left = quickSort(left);
        for(int i=0; i< equalToPivot; i++){
            left.add(a.get(pivot));
        }
        right = quickSort(right);

        ArrayList<Integer> output = new ArrayList<Integer>();
        for(int number:left){
            output.add(number);
        }
        for(int number:right){
            output.add(number);
        }

        return output;
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("[ ");
        Random seed = new Random();
        for(int i=0; i<_SIZE; i++){
            list.add(seed.nextInt(_SIZE));
        }
        for(int number: list){
            System.out.print(number + " ");
        }
        System.out.println("] \n");

        System.out.print("sorted: ");
        for(int number: quickSort(list)){
            System.out.println(number + ", ");
        }
    }
}
