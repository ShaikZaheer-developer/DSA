import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // 1. Clone the original array so we can sort it safely
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // 2. Map each unique element to its correct rank
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : sortedArr) {
            // Only assign a rank if the element hasn't been seen yet (handles duplicates)
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }
        
        // 3. Transform the original array into its corresponding ranks
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        
        return arr;
    }
}