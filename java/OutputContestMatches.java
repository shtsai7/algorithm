/*
	During the NBA playoffs, we always arrange the rather strong team to play with the rather weak team, like make the rank 1 team play with the rank nth team, which is a good strategy to make the contest more interesting. Now, you're given n teams, you need to output their final contest matches in the form of a string.

	The n teams are given in the form of positive integers from 1 to n, which represents their initial rank. (Rank 1 is the strongest team and Rank n is the weakest team.) We'll use parentheses('(', ')') and commas(',') to represent the contest team pairing - parentheses('(' , ')') for pairing and commas(',') for partition. During the pairing process in each round, you always need to follow the strategy of making the rather strong one pair with the rather weak one.

	Example 1:
	Input: 2
	Output: (1,2)
	Explanation: 
	Initially, we have the team 1 and the team 2, placed like: 1,2.
	Then we pair the team (1,2) together with '(', ')' and ',', which is the final answer.

	Example 2:
	Input: 4
	Output: ((1,4),(2,3))
	Explanation: 
	In the first round, we pair the team 1 and 4, the team 2 and 3 together, as we need to make the strong team and weak team together.
	And we got (1,4),(2,3).
	In the second round, the winners of (1,4) and (2,3) need to play again to generate the final winner, so you need to add the paratheses outside them.
	And we got the final answer ((1,4),(2,3)).

	Example 3:
	Input: 8
	Output: (((1,8),(4,5)),((2,7),(3,6)))
	Explanation: 
	First round: (1,8),(2,7),(3,6),(4,5)
	Second round: ((1,8),(4,5)),((2,7),(3,6))
	Third round: (((1,8),(4,5)),((2,7),(3,6)))
	Since the third round will generate the final winner, you need to output the answer (((1,8),(4,5)),((2,7),(3,6))).
	
	Note:
	The n is in range [2, 212].
	We ensure that the input n can be converted into the form 2k, where k is a positive integer.
 */

// Solution 2: Iterative solution
// Use an array to store intermediate results.
// Time: O(nlogn)
// Space: O(n)
// 12/26/2017
	
class Solution {
    public String findContestMatch(int n) {
        String[] teams = new String[n];
        for (int i = 1; i <= n; i++) {
            teams[i-1] = "" + i;
        }
        for (int i = n; i > 1; i /= 2) {       // log(n) levels
            for (int j = 0; j < i / 2; j++) {  // merge left and right
                teams[j] = "(" + teams[j] + "," + teams[i-1-j] + ")";
            }
        }
        return teams[0];
    }
}

// Solution 1: Recursive solution
// First generate all n numbers, separated by space.
// Then recursively match first to last, until
// there is only one string left (no space left).
// Time: O(nlogn) - log n level, each level n
// Space: O(logn) - call stack
// 12/26/2017

class Solution {
    public String findContestMatch(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(i + " ");
        }
        return find(sb.toString().trim());
    }
    
    private String find(String str) {
        String[] strs = str.split(" ");
        if (strs.length == 1) return strs[0];   // base case
        
        StringBuilder res = new StringBuilder();
        int i = 0, j = strs.length - 1;
        while (i < j) {
            res.append("(" + strs[i] + "," + strs[j] + ") ");
            i++;
            j--;
        }
        return find(res.toString().trim());
    }
}