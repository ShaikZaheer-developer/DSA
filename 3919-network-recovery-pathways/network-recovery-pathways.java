import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // 1. Build Adjacency List and compute In-degrees for Topological Sort
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[] inDegree = new int[n];
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], cost = e[2];
            adj[u].add(new int[]{v, cost});
            inDegree[v]++;
        }
        
        // 2. Precompute a global Topological Order to save time in the binary search loop
        int[] topoOrder = new int[n];
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }
        
        while (!q.isEmpty()) {
            int u = q.poll();
            topoOrder[index++] = u;
            for (int[] edge : adj[u]) {
                if (--inDegree[edge[0]] == 0) q.offer(edge[0]);
            }
        }
        
        // 3. Binary Search for the maximum guaranteed minimum edge cost
        int low = 0, high = 1_000_000_000, ans = -1;
        long[] dist = new long[n];
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (isValid(mid, n, topoOrder, adj, online, k, dist)) {
                ans = mid;
                low = mid + 1; // Try to look for a larger minimum bottleneck
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int minCost, int n, int[] topoOrder, List<int[]>[] adj, boolean[] online, long k, long[] dist) {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        for (int u : topoOrder) {
            if (dist[u] == Long.MAX_VALUE || !online[u]) continue;
            if (u == n - 1) break; // Reached the destination
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int cost = edge[1];
                
                // Only traverse if the node is online and edge fulfills the bottleneck requirement
                if (online[v] && cost >= minCost) {
                    if (dist[u] + cost < dist[v]) {
                        dist[v] = dist[u] + cost;
                    }
                }
            }
        }
        
        return dist[n - 1] <= k;
    }
}