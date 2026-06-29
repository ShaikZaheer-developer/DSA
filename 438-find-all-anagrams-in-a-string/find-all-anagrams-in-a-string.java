import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        // Edge case: if p is longer than s, no anagram can exist
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        // Frequency arrays for characters ('a' to 'z')
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Populate the frequency for string p and the first window of string s
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // If the first window matches, add index 0
        if (matches(pCount, sCount)) {
            result.add(0);
        }

        // Slide the window across the rest of string s
        for (int i = p.length(); i < s.length(); i++) {
            // Add the new character entering the window
            sCount[s.charAt(i) - 'a']++;
            // Remove the old character leaving the window
            sCount[s.charAt(i - p.length()) - 'a']--;

            // Check if the current window matches the target frequencies
            if (matches(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    // Helper method to compare two frequency arrays
    private boolean matches(int[] pCount, int[] sCount) {
        for (int i = 0; i < 26; i++) {
            if (pCount[i] != sCount[i]) {
                return false;
            }
        }
        return true;
    }
}