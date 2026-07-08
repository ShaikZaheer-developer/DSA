class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1_000_000_007L;

        // 1. Identify all non-zero digit positions
        int[] nzIndices = new int[m];
        int nzCount = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                nzIndices[nzCount++] = i;
            }
        }

        // If there are no non-zero digits at all, all queries evaluate to 0
        if (nzCount == 0) {
            return new int[queries.length];
        }

        // 2. Precompute Pre-calculated Powers of 10 modulo MOD
        long[] pow10 = new long[nzCount + 1];
        pow10[0] = 1;
        for (int i = 1; i <= nzCount; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // 3. Build Prefix Sums for Digit Values and Values Concatenation
        long[] prefixSum = new long[nzCount + 1];
        long[] prefixVal = new long[nzCount + 1];

        for (int i = 0; i < nzCount; i++) {
            int digit = s.charAt(nzIndices[i]) - '0';
            prefixSum[i + 1] = prefixSum[i] + digit;
            prefixVal[i + 1] = (prefixVal[i] * 10 + digit) % MOD;
        }

        // 4. Process queries in O(1) using Binary Search (or Two Pointers mapping)
        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            // Find the boundary indices in our compressed non-zero arrays
            int idxL = binarySearchLeft(nzIndices, nzCount, l);
            int idxR = binarySearchRight(nzIndices, nzCount, r);

            // If no non-zero digits exist within the boundary [l, r]
            if (idxL > idxR || idxL >= nzCount || idxR < 0) {
                answer[q] = 0;
                continue;
            }

            // Extract Digit Sum in O(1)
            long currentSum = prefixSum[idxR + 1] - prefixSum[idxL];

            // Extract Substring Value Concatenation in O(1)
            int segmentLen = idxR - idxL + 1;
            long currentX = (prefixVal[idxR + 1] - (prefixVal[idxL] * pow10[segmentLen]) % MOD + MOD) % MOD;

            // Calculate final product
            answer[q] = (int) ((currentX * (currentSum % MOD)) % MOD);
        }

        return answer;
    }

    private int binarySearchLeft(int[] arr, int len, int target) {
        int low = 0, high = len - 1;
        int ans = len;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int binarySearchRight(int[] arr, int len, int target) {
        int low = 0, high = len - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] <= target) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}