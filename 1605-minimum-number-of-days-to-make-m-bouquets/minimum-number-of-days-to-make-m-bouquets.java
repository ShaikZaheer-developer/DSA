class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        
        // Edge case: Not enough flowers in the garden to satisfy the requirement
        if ((long) m * k > n) {
            return -1;
        }

        // Define the binary search space
        int left = 1;
        int right = 0;
        for (int day : bloomDay) {
            right = Math.max(right, day);
        }

        int minDays = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canMakeBouquets(bloomDay, mid, m, k)) {
                minDays = mid;     // Record valid day
                right = mid - 1;   // Try to find an earlier valid day
            } else {
                left = mid + 1;    // We need more time to bloom
            }
        }

        return minDays;
    }

    // Helper function to greedily check if we can form 'm' bouquets on a given 'day'
    private boolean canMakeBouquets(int[] bloomDay, int currentDay, int m, int k) {
        int bouquets = 0;
        int contiguousFlowers = 0;

        for (int day : bloomDay) {
            if (day <= currentDay) {
                contiguousFlowers++;
                // If we have enough contiguous flowers for one bouquet
                if (contiguousFlowers == k) {
                    bouquets++;
                    contiguousFlowers = 0; // Reset for the next bouquet
                }
            } else {
                // The contiguous chain is broken by an unbloomed flower
                contiguousFlowers = 0;
            }
        }

        return bouquets >= m;
    }
}