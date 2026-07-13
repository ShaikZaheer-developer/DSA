import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        // Loop through all possible lengths of sequential numbers (from 2 digits up to 9 digits)
        for (int length = 2; length <= 9; length++) {
            // Slide a window of 'length' across the digits string
            for (int i = 0; i <= 9 - length; i++) {
                String subStr = digits.substring(i, i + length);
                int num = Integer.parseInt(subStr);
                
                // If it falls within our range, add it
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}