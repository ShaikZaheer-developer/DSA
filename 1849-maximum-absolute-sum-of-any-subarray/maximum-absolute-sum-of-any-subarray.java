class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int prefixSum = 0;
        int minPrefixSum = 0;
        int maxPrefixSum = 0;
        int maxAbsoluteSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i]; // Update prefix sum
            // Update maxAbsoluteSum
            maxAbsoluteSum = Math.max(maxAbsoluteSum, Math.abs(prefixSum - minPrefixSum));
            maxAbsoluteSum = Math.max(maxAbsoluteSum, Math.abs(prefixSum - maxPrefixSum));
            // Update minPrefixSum and maxPrefixSum
            minPrefixSum = Math.min(minPrefixSum, prefixSum);
            maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
        }
        
        return maxAbsoluteSum;
    
    }
}