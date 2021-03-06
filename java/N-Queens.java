/*
 *  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 */

// Solution 1: DFS, backtracking
// Time: O(n^n) - n choices at n levels
// Space: O(n) - stack

// version 2:
// 01/31/2018
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> res = new ArrayList<>();
        solver(new ArrayList<>(), res, 0, n);
        return buildboard(res, n);
    }
    
    public void solver(List<Integer> placement, List<List<Integer>> res, int index, int n) {
        if (index == n) {
            res.add(new ArrayList<>(placement));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(placement, index, i)) {
                placement.add(i);
                solver(placement, res, index+1, n);
                placement.remove(placement.size()-1);
            }
        }
    }
    
    public boolean isValid(List<Integer> placement, int row, int col) {
        for (int i = 0; i < placement.size(); i++) {
            if (placement.get(i) == col) {  // same column
                return false;
            }
            if ((i + placement.get(i)) == (row + col)) { // diagonal
                return false;
            }
            if ((i - placement.get(i)) == (row - col)) { // anti-diagonal
                return false;
            }
        }
        return true;
    }
    
    public List<List<String>> buildboard(List<List<Integer>> placements, int n) {
        List<List<String>> res = new ArrayList<>();
        for (List<Integer> placement : placements) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < placement.size(); i++) {
                int place = placement.get(i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < place; j++) {
                    sb.append(".");
                }
                sb.append("Q");
                for (int j = place + 1; j < n; j++) {
                    sb.append(".");
                }
                list.add(sb.toString());
            }
            res.add(list);
        }
        return res;
    }
}

// version 1:
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> result = new ArrayList<>();
        int[] position = new int[n];      // the index indicates row, the value indicate col
        helper(0, n, position, result);   // use helper function to recursively solve smaller problem

        return result;
    }
    
    public void helper(int level, int n, int[] position, List<List<String>> result) {

        if (level == n) {    // we have found a valid placement
            List<String> list = buildBoard(position);  // build the board for this placement
            result.add(list);   //  and add it to the result list
            return;
        }
        
        for (int i = 0; i < n; i++) {  // try every column in this row
            if (isValid(level, i, position)) {   // if this position does not conflict with previous placement
                position[level] = i;   // mark the position
                helper(level+1, n, position, result);   // make recursive call
                position[level] = 0;   // unmark the position
            }
        }
    } 
    
    // this function checks if adding this new position will conflict with prior placement
    // make sure no two queens are on the same line 
    public boolean isValid(int row, int col, int[] position) {
        for (int i = 0; i < row; i++) {
            if (position[i] == col || (row-i) == Math.abs(col-position[i])){
                return false;
            }
        }
        return true;

    }
    
    // this function builds the board based on the placement
    public List<String> buildBoard(int[] position) {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < position.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < position.length; col++) {
                if (position[row] == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        return list;
    }
}