class Solution {
    private int[][][] dp;
    private final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        // dp[index][gcd1][gcd2]
        // GCD values range from 0 to 200 (0 means the subsequence is currently empty)
        dp = new int[n][201][201];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 200; j++) {
                java.util.Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0, 0, 0, nums);
    }

    private int solve(int i, int gcd1, int gcd2, int[] nums) {
        // Base case: processed all elements
        if (i == nums.length) {
            // Both subsequences must be non-empty (gcd > 0) and have equal GCDs
            return (gcd1 == gcd2 && gcd1 > 0) ? 1 : 0;
        }

        // Return cached result if already computed
        if (dp[i][gcd1][gcd2] != -1) {
            return dp[i][gcd1][gcd2];
        }

        // Choice 1: Skip the current element
        long ans = solve(i + 1, gcd1, gcd2, nums);

        // Choice 2: Include in the first subsequence
        int nextGcd1 = (gcd1 == 0) ? nums[i] : gcd(gcd1, nums[i]);
        ans = (ans + solve(i + 1, nextGcd1, gcd2, nums)) % MOD;

        // Choice 3: Include in the second subsequence
        int nextGcd2 = (gcd2 == 0) ? nums[i] : gcd(gcd2, nums[i]);
        ans = (ans + solve(i + 1, gcd1, nextGcd2, nums)) % MOD;

        return dp[i][gcd1][gcd2] = (int) ans;
    }

    // Helper method to calculate Greatest Common Divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}