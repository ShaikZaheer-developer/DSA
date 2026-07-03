class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        
        // Edge case: s1 cannot be a substring of s2 if it's longer
        if (len1 > len2) {
            return false;
        }
        
        // Frequency array for lowercase English letters (fixed size 26)
        int[] count = new int[26];
        
        // Initialize the frequencies for s1 and the first window of s2
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        
        // If the first window is a match, all elements in count will be 0
        if (isAllZero(count)) {
            return true;
        }
        
        // Slide the window across s2
        for (int i = len1; i < len2; i++) {
            // Include the new character entering the window from the right
            count[s2.charAt(i) - 'a']--;
            // Exclude the old character leaving the window from the left
            count[s2.charAt(i - len1) - 'a']++;
            
            // Check if the current window matches the required permutation
            if (isAllZero(count)) {
                return true;
            }
        }
        
        return false;
    }
    
    // Helper method to check if the window perfectly matches s1's frequency
    private boolean isAllZero(int[] count) {
        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
}