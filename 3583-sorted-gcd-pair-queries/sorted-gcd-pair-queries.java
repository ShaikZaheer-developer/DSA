import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum element to bound our frequency/GCD processing
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        // Step 2: Calculate direct frequencies of each number
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        // Step 3: Count how many numbers are multiples of each 'i'
        long[] gcdPairsCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long countMultiples = 0;
            // Iterate through all multiples of i up to maxVal
            for (int j = i; j <= maxVal; j += i) {
                countMultiples += freq[j];
            }
            
            // Total possible pairs choosing 2 elements out of countMultiples
            long totalPairs = (countMultiples * (countMultiples - 1)) / 2;
            
            // Inclusion-Exclusion: Subtract counts of pairs whose actual GCD is a strictly greater multiple
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdPairsCount[j];
            }
            
            gcdPairsCount[i] = totalPairs;
        }
        
        // Step 4: Construct prefix sums array to represent the sorted ranges of gcdPairs
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdPairsCount[i];
        }
        
        // Step 5: Answer each query using Binary Search
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            long targetIndex = queries[q];
            
            // Binary search to find the smallest GCD 'i' where prefixSums[i] > targetIndex
            int low = 1, high = maxVal;
            int resultGCD = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > targetIndex) {
                    resultGCD = mid;
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[q] = resultGCD;
        }
        
        return answer;
    }
}