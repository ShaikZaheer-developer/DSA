import java.util.HashMap;

class Solution {
    public long maximumSubarraySum(int[] arr, int k) {
        long maxAns = 0;
        long windowSum = 0;
        int n = arr.length;
        
        // Map stores the frequency of elements in the current window
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            // 1. Add the current element to the window
            windowSum += arr[i];
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            
            // 2. Once the window size exceeds k, remove the leftmost element
            if (i >= k) {
                int leftElement = arr[i - k];
                windowSum -= leftElement;
                map.put(leftElement, map.get(leftElement) - 1);
                
                // Remove from map entirely if count reaches 0 to keep map.size() accurate
                if (map.get(leftElement) == 0) {
                    map.remove(leftElement);
                }
            }
            
            // 3. Condition Check:
            // Window is size k (i >= k - 1) AND all k elements are distinct (map.size() == k)
            if (i >= k - 1 && map.size() == k) {
                maxAns = Math.max(maxAns, windowSum);
            }
        }
        
        return maxAns;
    }
}