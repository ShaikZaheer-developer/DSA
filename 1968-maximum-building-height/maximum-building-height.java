import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // Base case: if there are no restrictions, the max height will be at the last building (n - 1)
        if (restrictions == null || restrictions.length == 0) {
            return n - 1;
        }
        
        // 1. Create an extended restrictions list to include the absolute boundaries:
        // Building 1 (height 0) and Building n (max theoretical height n - 1)
        int m = restrictions.length;
        int[][] extended = new int[m + 2][2];
        
        extended[0] = new int[]{1, 0};
        extended[1] = new int[]{n, n - 1};
        for (int i = 0; i < m; i++) {
            extended[i + 2] = restrictions[i];
        }
        
        // 2. Sort the restrictions by building ID
        Arrays.sort(extended, (a, b) -> Integer.compare(a[0], b[0]));
        
        int totalLen = extended.length;
        
        // 3. Left-to-Right Pass: Clamp max heights based on left neighbors
        for (int i = 1; i < totalLen; i++) {
            int idDiff = extended[i][0] - extended[i - 1][0];
            extended[i][1] = Math.min(extended[i][1], extended[i - 1][1] + idDiff);
        }
        
        // 4. Right-to-Left Pass: Clamp max heights based on right neighbors
        for (int i = totalLen - 2; i >= 0; i--) {
            int idDiff = extended[i + 1][0] - extended[i][0];
            extended[i][1] = Math.min(extended[i][1], extended[i + 1][1] + idDiff);
        }
        
        // 5. Calculate the absolute peak possible between any two adjacent restrictions
        int maxGlobalHeight = 0;
        for (int i = 0; i < totalLen - 1; i++) {
            int id1 = extended[i][0], h1 = extended[i][1];
            int id2 = extended[i + 1][0], h2 = extended[i + 1][1];
            
            // Peak formula derived from: h1 + x = h2 + (id2 - id1 - x)
            int peak = (h1 + h2 + (id2 - id1)) / 2;
            maxGlobalHeight = Math.max(maxGlobalHeight, peak);
        }
        
        return maxGlobalHeight;
    }
}