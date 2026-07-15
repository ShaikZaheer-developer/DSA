class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // The minimum possible eating speed is 1.
        // The maximum is 10^9 (based on the problem's constraints for pile sizes).
        int left = 1;
        int right = 1_000_000_000; 

        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Use long to prevent integer overflow during accumulation 
            long hoursNeeded = 0; 
            for (int pile : piles) {
                // This is a safe, integer-math way to do Math.ceil((double) pile / mid)
                hoursNeeded += (pile - 1) / mid + 1; 
            }
            
            if (hoursNeeded <= h) {
                // If she can finish at this speed, try a slower speed (look left)
                right = mid; 
            } else {
                // If she can't finish, she must eat faster (look right)
                left = mid + 1; 
            }
        }
        
        return left;
    }
}