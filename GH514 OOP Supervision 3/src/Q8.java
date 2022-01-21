import java.lang.reflect.Array;
import java.util.Arrays;

public class Q8 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        System.out.println(counter.largestGroup(counter.sort(new int[]{2, 6, 3, 4, 5, 7, 9, 6, 4, 2, 4, 5, 3, 4})));
    }

    public static class Counter {
        public int largestGroup(int[] input){
            int largest = 0;
            int count = 0;
            int highestcount = 0;
            for (int i = 0; i < input.length-1; i++){
                if (input[i] == input[i+1]){
                    count++;
                } else {
                    if (count > highestcount){
                        highestcount = ++count;
                        largest = input[i];
                        count = 0;
                    }
                }
            }
            return largest;
        }


        public int[] sort(int[] input){
            Arrays.sort(input);
            return input;
        }
    }
}
