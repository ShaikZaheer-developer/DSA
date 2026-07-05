import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // dp[r][c][0] stores the max score to reach (r, c) from 'S'
        // dp[r][c][1] stores the number of paths to achieve that max score
        int[][][] dp = new int[n][n][2];

        // Initialize the starting position 'S' at the bottom-right
        dp[n - 1][n - 1][1] = 1; 

        // Directions we can come from to reach (r, c): Right, Down, Down-Right
        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};

        // Iterate backwards from the bottom-right to the top-left
        for (int r = n - 1; r >= 0; r--) {
            String rowStr = board.get(r);
            for (int c = n - 1; c >= 0; c--) {
                char cell = rowStr.charAt(c);

                // Skip obstacles and the starting cell itself
                if (cell == 'X' || cell == 'S') {
                    continue;
                }

                int maxScore = -1;
                int pathCount = 0;

                // Look at all valid incoming paths from the 3 allowed directions
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Check boundaries and ensure the neighbor is reachable
                    if (nr < n && nc < n && dp[nr][nc][1] > 0) {
                        if (dp[nr][nc][0] > maxScore) {
                            maxScore = dp[nr][nc][0];
                            pathCount = dp[nr][nc][1];
                        } else if (dp[nr][nc][0] == maxScore) {
                            pathCount = (pathCount + dp[nr][nc][1]) % MOD;
                        }
                    }
                }

                // If this cell is reachable from at least one valid path
                if (maxScore != -1) {
                    int currentVal = (cell == 'E') ? 0 : (cell - '0');
                    dp[r][c][0] = maxScore + currentVal;
                    dp[r][c][1] = pathCount;
                }
            }
        }

        // Return the results accumulated at the top-left cell 'E'
        return new int[]{dp[0][0][0], dp[0][0][1]};
            
    }
}