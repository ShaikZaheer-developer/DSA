class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i] stores the max score difference for subarray nums[i...j]
        int[] dp = new int[n];

        // Base cases: when subarray length is 1 (left == right)
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }

        // Process subarrays of increasing length
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }

        // If Player 1's score difference is >= 0, Player 1 wins
        return dp[n - 1] >= 0;
    }
}