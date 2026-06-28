class Solution {
    public int countSubstrings(String s) {
        int totalCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindromes (centered at a single character)
            totalCount += expandFromCenter(s, i, i);
            
            // Case 2: Even-length palindromes (centered between two characters)
            totalCount += expandFromCenter(s, i, i + 1);
        }
        
        return totalCount;
    }
    
    private int expandFromCenter(String s, int left, int right) {
        int count = 0;
        
        // Expand outward as long as it's a valid palindrome
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;  // Move left pointer outward
            right++; // Move right pointer outward
        }
        
        return count;
    }
}