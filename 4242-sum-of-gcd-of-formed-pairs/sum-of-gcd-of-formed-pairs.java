import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int currentMax = 0;
        
        // Step 1: Construct prefixGcd array
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = calculateGcd(nums[i], currentMax);
        }
        
        // Step 2: Sort in non-decreasing order
        Arrays.sort(prefixGcd);
        
        // Step 3 & 4: Form pairs using two pointers and sum their GCDs
        long totalSum = 0; // Use long to prevent integer overflow
        int left = 0;
        int right = n - 1;
        
        // This loop inherently stops before the middle element if n is odd
        while (left < right) {
            totalSum += calculateGcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Standard Euclidean algorithm to find GCD
    private int calculateGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}