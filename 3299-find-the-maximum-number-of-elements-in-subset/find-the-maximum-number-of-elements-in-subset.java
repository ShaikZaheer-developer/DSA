import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put((long) num, counts.getOrDefault((long) num, 0) + 1);
        }
        
        // Handle the edge case of 1s: we can take the maximum odd number of 1s available
        int maxLen = 0;
        if (counts.containsKey(1L)) {
            int oneCount = counts.get(1L);
            maxLen = (oneCount % 2 == 0) ? oneCount - 1 : oneCount;
        }
        
        // Check for patterns starting from each unique x > 1
        for (long x : counts.keySet()) {
            if (x == 1) continue;
            
            int currentLen = 0;
            long current = x;
            
            // Build the chain as long as we have at least 2 copies of the current number
            while (counts.getOrDefault(current, 0) >= 2) {
                currentLen += 2;
                current = current * current; // Move to the next squared value
            }
            
            // The peak element only needs to appear at least once
            if (counts.getOrDefault(current, 0) >= 1) {
                currentLen += 1;
            } else {
                // If there's no peak element available, the last element we counted 2 copies of
                // must act as the peak instead, so we reduce the count by 1 to make it odd
                currentLen -= 1;
            }
            
            maxLen = Math.max(maxLen, currentLen);
        }
        
        return maxLen;
    }
}