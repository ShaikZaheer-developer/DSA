class Solution {
    public long sumAndMultiply(int n) {
        long reversedX = 0;
        long sum = 0;
        
        // Step 1: Extract non-zero digits from right to left
        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                reversedX = reversedX * 10 + digit;
                sum += digit;
            }
            n /= 10;
        }
        
        // Step 2: Reverse it back to the original order to get x
        long x = 0;
        while (reversedX > 0) {
            x = x * 10 + (reversedX % 10);
            reversedX /= 10;
        }
        
        // Step 3: Return the final product
        return x * sum;
    }
}