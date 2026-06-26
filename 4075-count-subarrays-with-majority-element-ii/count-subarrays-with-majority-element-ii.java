class Solution {
    // Fenwick Tree (Binary Indexed Tree) implementation
    private void update(int[] bit, int index, int delta) {
        for (; index < bit.length; index += index & -index) {
            bit[index] += delta;
        }
    }

    private int query(int[] bit, int index) {
        int sum = 0;
        for (; index > 0; index -= index & -index) {
            sum += bit[index];
        }
        return sum;
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        long totalSubarrays = 0;
        
        // The prefix sum values can range from -n to n.
        // We shift the values by adding an offset to make them positive.
        int offset = n + 1;
        int[] bit = new int[2 * n + 3];
        
        // Initial state: prefix sum P[0] = 0 before processing any elements.
        // Shifted value: 0 + offset = offset
        update(bit, offset, 1);
        
        int currentPrefixSum = 0;
        
        for (int i = 0; i < n; i++) {
            // Update current prefix sum based on transformation
            if (nums[i] == target) {
                currentPrefixSum += 1;
            } else {
                currentPrefixSum -= 1;
            }
            
            int shiftedValue = currentPrefixSum + offset;
            
            // Query how many previous prefix sums are strictly less than the current one
            totalSubarrays += query(bit, shiftedValue - 1);
            
            // Add the current prefix sum into the Fenwick Tree
            update(bit, shiftedValue, 1);
        }
        
        return totalSubarrays;
    }
}