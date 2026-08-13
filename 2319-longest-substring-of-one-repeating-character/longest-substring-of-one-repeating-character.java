class Solution {
    static class Node {
        char leftChar, rightChar;
        int maxLen, prefixLen, suffixLen;

        Node(char c) {
            leftChar = rightChar = c;
            maxLen = prefixLen = suffixLen = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Prefix length calculation
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == leftLen && left.rightChar == right.leftChar) {
            res.prefixLen += right.prefixLen;
        }

        // Suffix length calculation
        res.suffixLen = right.suffixLen;
        if (right.suffixLen == rightLen && left.rightChar == right.leftChar) {
            res.suffixLen += left.suffixLen;
        }

        // Max length calculation
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            chars[idx] = val;
            tree[node] = new Node(val);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) update(2 * node, start, mid, idx, val);
        else update(2 * node + 1, mid + 1, end, idx, val);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }
}