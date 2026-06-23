class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long MOD = 1_000_000_007L;

        // dp[dir][x] represents the number of valid sequences of the current length ending at value x.
        // dir = 0: the NEXT required move must be DOWN (current element is a peak)
        // dir = 1: the NEXT required move must be UP (current element is a valley)
        long[][] dp = new long[2][m];

        // Base case: For length 1, every single element from l to r is a valid starting element.
        // Since it's the first element, the next move can go either up or down.
        for (int x = 0; x < m; x++) {
            dp[0][x] = 1;
            dp[1][x] = 1;
        }

        // Process from length 2 up to n
        for (int i = 2; i <= n; i++) {
            long[][] nextDp = new long[2][m];

            // 1. Calculate nextDp[0][y]: Next move must be DOWN.
            // This means the current step from x to y was an UP move (x < y).
            // It transitions from the state where the required move *was* UP (dp[1][x]).
            long prefixSum = 0;
            for (int y = 0; y < m; y++) {
                nextDp[0][y] = prefixSum;
                prefixSum = (prefixSum + dp[1][y]) % MOD;
            }

            // 2. Calculate nextDp[1][y]: Next move must be UP.
            // This means the current step from x to y was a DOWN move (x > y).
            // It transitions from the state where the required move *was* DOWN (dp[0][x]).
            long suffixSum = 0;
            for (int y = m - 1; y >= 0; y--) {
                nextDp[1][y] = suffixSum;
                suffixSum = (suffixSum + dp[0][y]) % MOD;
            }

            dp = nextDp;
        }

        // At length n, a sequence is valid regardless of whether its next theoretical move is UP or DOWN.
        long totalValidArrays = 0;
        for (int x = 0; x < m; x++) {
            totalValidArrays = (totalValidArrays + dp[0][x] + dp[1][x]) % MOD;
        }

        return (int) totalValidArrays;
    }
}