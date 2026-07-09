class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Precompute component IDs for each node
        int[] component = new int[n];
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                component[i] = component[i - 1]; // Same component
            } else {
                component[i] = i; // Start a new component
            }
        }
        
        // Step 2: Answer each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = (component[queries[i][0]] == component[queries[i][1]]);
        }
        
        return answer;
    }
}