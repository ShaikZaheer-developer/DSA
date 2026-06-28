import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Step 1: Sort the array to greedily build up the maximum value
        Arrays.sort(arr);
        
        // Step 2: The first element must always be 1
        arr[0] = 1;
        
        // Step 3: Ensure no two adjacent elements differ by more than 1
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        
        // Step 4: The last element will be the maximum achievable value
        return arr[arr.length - 1];
    }
}