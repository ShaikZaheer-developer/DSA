import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) return m;

        // Base cases for transition matrices
        long[][] U = new long[m][m];
        long[][] L = new long[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i < j) U[i][j] = 1;
                if (i > j) L[i][j] = 1;
            }
        }

        // Target total transitions is n - 1
        int steps = n - 1;
        int halfSteps = steps >> 1;

        // Compute the combined matrix (U * L)
        long[][] UL = multiply(U, L, m);

        // Compute (UL)^(halfSteps) using binary exponentiation
        long[][] P = matrixPower(UL, halfSteps, m);

        // If the remaining step count is odd, multiply by L at the end
        if ((steps & 1) == 1) {
            P = multiply(L, P, m);
        }

        // Sum up all elements in the final transition matrix
        // Multiplying by 2 accounts for the exact symmetric paths starting downwards
        long totalSum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                totalSum = (totalSum + P[i][j]) % MOD;
            }
        }

        return (int) ((totalSum * 2) % MOD);
    }

    // High-performance Matrix Multiplication with Cache Locality Optimization
    private long[][] multiply(long[][] A, long[][] B, int m) {
        long[][] C = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < m; k++) {
                if (A[i][k] == 0) continue; // Speculative execution optimization
                for (int j = 0; j < m; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Binary Exponentiation for Matrices
    private long[][] matrixPower(long[][] base, int exp, int m) {
        long[][] result = new long[m][m];
        for (int i = 0; i < m; i++) {
            result[i][i] = 1; // Identity matrix
        }

        long[][] currentBase = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, currentBase, m);
            }
            currentBase = multiply(currentBase, currentBase, m);
            exp >>= 1;
        }
        return result;
    }
}