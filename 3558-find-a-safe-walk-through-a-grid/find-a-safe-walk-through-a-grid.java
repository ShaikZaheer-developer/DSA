import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // Directions for moving Up, Down, Left, Right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // minHealthLost[i][j] stores the minimum health lost to reach cell (i, j)
        int[][] minHealthLost = new int[m][n];
        for (int[] row : minHealthLost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Deque for 0-1 BFS
        Deque<int[]> deque = new ArrayDeque<>();
        
        // Initialize starting position
        minHealthLost[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // If we reached the bottom-right corner, we can check if it's safe
            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                // Boundary check
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextCost = minHealthLost[r][c] + grid.get(nr).get(nc);
                    
                    // If we found a path to (nr, nc) with less health lost
                    if (nextCost < minHealthLost[nr][nc]) {
                        minHealthLost[nr][nc] = nextCost;
                        
                        // 0-1 BFS optimization: 
                        // If cost is 0, push to front. If cost is 1, push to back.
                        if (grid.get(nr).get(nc) == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        // We need to reach the destination with at least 1 health remaining
        return health - minHealthLost[m - 1][n - 1] >= 1;
    }
}