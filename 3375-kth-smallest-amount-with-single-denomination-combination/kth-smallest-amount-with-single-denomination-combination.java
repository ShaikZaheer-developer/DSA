class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1, high = (long) coins[0] * k;
        for (int c : coins) {
            high = Math.min(high, (long) c * k);
        }

        int n = coins.length;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countMultiples(mid, coins, n) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Counts distinct multiples <= target using Inclusion-Exclusion
    private long countMultiples(long target, int[] coins, int n) {
        long count = 0;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) break; // Optimization: LCM exceeds range
                }
            }

            if (currentLcm <= target) {
                if (bitCount % 2 == 1) {
                    count += target / currentLcm;
                } else {
                    count -= target / currentLcm;
                }
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}