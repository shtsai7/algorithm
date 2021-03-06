/*
    Given two strings S and T, determine if they are both one edit distance apart.
 */

// Solution 3: General solution for K edit distance
// Time: O(3^k * n) - Three choices for each edit
// Space: O(k * n)
// 09/07/2018
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        return helper(s, t, 1);
    }
    
    // s is guaranteed to be shorter than t
    private boolean helper(String s, String t, int edit) {
        if (edit < 0) {
            return false;
        } else if (s.length() > t.length()) {
            return helper(t, s, edit);
        } else if (s.length() == 0) {
            return t.length() == edit;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return helper(s.substring(i + 1), t.substring(i + 1), edit - 1) ||
                           helper(s.substring(i), t.substring(i + 1), edit - 1) ||
                           helper(s.substring(i + 1), t.substring(i), edit - 1);
                }
            }
            return (t.length() - s.length()) == edit;
        }
    }
}

// Solution 2:
// Time: O(n) - one pass
// Space: O(1)
// 09/06/2018
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {        // replace
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (s.length() > t.length()) {  // remove one from s
                    return s.substring(i + 1).equals(t.substring(i));
                } else {                               // remove one from t
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return Math.abs(s.length() - t.length()) == 1; // check if diff is 1
    }
}

// Solution 1: standard edit distance approach
// Dynamic programming, use 2D array to store intermediate results
// Time: O(mn) - time limit exceeded
// Space: O(mn)
// 09/30/2017

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (Math.abs(sLen - tLen) > 1) return false;
        
        int[][] dp = new int[sLen+1][tLen+1];
        dp[0][0] = 0;
        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= tLen; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int minMove = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1]);
                    dp[i][j] = minMove + 1;
                }
            }
        }
        return dp[sLen][tLen] == 1;
    }
}