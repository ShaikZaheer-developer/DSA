
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // 1. Sort the costs in ascending order (Greedy approach)
        Arrays.sort(costs); 
        
        int count = 0;
        
        // 2. Buy the cheapest ice cream bars first
        for (int i = 0; i < costs.length; i++) {
            if (coins >= costs[i]) {
                coins -= costs[i];
                count++;
            } else {
                // If we can't afford the current cheapest one, we are done
                break; 
            }
        }
        
        return count;
    }
}