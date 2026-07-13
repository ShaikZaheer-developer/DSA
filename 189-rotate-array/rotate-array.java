class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        // Handle cases where k is greater than the array length
        k = k % nums.length;
        
        int n = nums.length;
        
        // Step 1: Reverse the whole array
        reverse(nums, 0, n - 1);
        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1);
        // Step 3: Reverse the remaining elements
        reverse(nums, k, n - 1);
    }
    
    // Simple helper method to reverse elements in-place using two pointers
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}