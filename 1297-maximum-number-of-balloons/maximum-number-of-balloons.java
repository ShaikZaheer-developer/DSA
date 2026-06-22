class Solution {
    public int maxNumberOfBalloons(String text) {
        // Step 1: Count frequencies of all lowercase characters using a fixed-size array
        int[] counts = new int[26];
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i) - 'a']++;
        }
        
        // Step 2: Determine the bottleneck by checking the minimum available instances
        int maxBalloons = counts['b' - 'a'];             // requires 1 'b'
        maxBalloons = Math.min(maxBalloons, counts['a' - 'a']);     // requires 1 'a'
        maxBalloons = Math.min(maxBalloons, counts['l' - 'a'] / 2); // requires 2 'l's
        maxBalloons = Math.min(maxBalloons, counts['o' - 'a'] / 2); // requires 2 'o's
        maxBalloons = Math.min(maxBalloons, counts['n' - 'a']);     // requires 1 'n'
        
        return maxBalloons;
    }
}