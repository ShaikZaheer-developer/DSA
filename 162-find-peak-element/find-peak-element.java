class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                // Upward slope to the right -> Peak is in the right half
                left = mid + 1;
            } else {
                // Downward slope -> mid could be the peak or peak is to the left
                right = mid;
            }
        }
        
        // 'left' and 'right' will converge to the peak element index
        return left;
    }
}