class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // If the target is found, return its index immediately
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if the left half is normally sorted
            if (nums[left] <= nums[mid]) {
                // Check if the target lies within the sorted left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Shrink search space to the left
                } else {
                    left = mid + 1;  // Search in the right half
                }
            } 
            // Otherwise, the right half must be normally sorted
            else {
                // Check if the target lies within the sorted right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // Shrink search space to the right
                } else {
                    right = mid - 1; // Search in the left half
                }
            }
        }
        
        // Target was not found in the array
        return -1;
    }
}