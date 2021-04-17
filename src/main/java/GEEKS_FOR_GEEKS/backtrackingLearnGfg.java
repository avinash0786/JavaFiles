package GEEKS_FOR_GEEKS;

import java.util.ArrayList;

public class backtrackingLearnGfg {
    public static void main(String[] args) {
        getPermutation("ABC",0,2);  //[ACB, BAC, BCA, CBA]
        System.out.println(perms);
        //  normal permutation: [ABC, ACB, BAC, BCA, CBA, CAB]
//        System.out.println("Rat maze :"+ratMazeProblem(new int[][]{{1,0,0,0},{1,1,0,1},{0,1,0,0},{1,1,1,1}}));
        System.out.println("nqueen Problen: "+nQueenMain(10));
    }
    //Print all permutation of a string which do not contain AB as substring
    static ArrayList<String> perms=new ArrayList<>();
    public static void getPermutation(String str,int l,int h){
        if (l==h){
            if (!str.contains("AB")){
                perms.add(str);
                return;
            }
        }
        for (int i =l; i <=h; i++) {
            if (isSafe(str,l,i,h)){
                str=interchange(str,l,i);
                getPermutation(str,l+1,h);
                str=interchange(str, l, i);
            }
        }
    }
    public static boolean isSafe(String str,int l, int i, int r){
        if(l!=0 && str.charAt(l-1)=='A' && str.charAt(i)=='B')
            return false;
        if(r==(l+1) && str.charAt(i)=='A' && str.charAt(l)=='B')
            return false;
        return true;
    }
    //at each level we fix a car and permut for rest of characters
    public static String interchange(String s, int a, int b){
        char[] ar=s.toCharArray();
        char temp=ar[a];
        ar[a]=ar[b];
        ar[b]=temp;
        return new String(ar);
    }
    //Rat and maze problem
    /*
    Given a bin martix rat is at top left bottom and destination is at bottom right
    //find if it is possible to reach the path
     */
    private static int[][] solnMaze;
    private static int[][] ratMaze;
    public static boolean ratMazeProblem(int[][] maze){
        ratMaze=maze;
        solnMaze=new int[ratMaze.length][ratMaze.length];
        if (solveRatMaze(0,0)){
            System.out.println("Rat path to destination");
            for (int[] ints : solnMaze) {
                for (int anInt : ints) {
                    System.out.print(anInt+" ");
                }
                System.out.println();
            }
            return true;
        }
        return false;
    }
    private static boolean solveRatMaze(int i,int j){
        if (i==ratMaze.length-1 && j==ratMaze.length-1){
            solnMaze[i][j]=1;
            return true;
        }
        if (isSafeMaze(i,j)){
            solnMaze[i][j]=1;
            if (solveRatMaze(i+1,j))
                return true;
            if (solveRatMaze(i,j+1))
                return true;
            solnMaze[i][j]=0;
        }
        return false;
    }
    private static boolean isSafeMaze(int i, int j){
        return i<ratMaze.length && j<ratMaze.length && ratMaze[i][j]==1;
    }
    // N QUEEEN problem
    //for each row we chack for its element if we can place a queen
    // we can only plce a queen in a row , once we cannot place we check for next col
    // if we can place there we go to check for next row (not next col, next col only when we can place a queen in that row and check for next row)
    static int[][] boardQueen;
    public static boolean nQueenMain(int n){
        boardQueen=new int[n][n];
        if (solveNQueen(0)){
            for (int[] ints : boardQueen) {
                for (int anInt : ints) {
                    System.out.print(anInt+" ");
                }
                System.out.println();
            }
            return true;
        }
        return false;
    }
    private static boolean solveNQueen(int row){
        if (row==boardQueen.length)
            return true;
        //for loop will try to place queen in diff cols of a row
        for (int i = 0; i < boardQueen.length; i++) {
            if (isQueenSafe(row,i)){
                boardQueen[row][i]=1;
                if (solveNQueen(row+1))
                    return true;
                boardQueen[row][i]=0;
            }
        }
        return false;
    }
    private static boolean isQueenSafe(int row, int col){
        // we only need to check cur row upper left diagonal and lower left diagonal
        for (int i = 0; i < row; i++) {
            if (boardQueen[i][col]==1)
                return false;
        }
        for (int i = row,j=col; i>=0 && j>=0 ; i--,j--) {
            if (boardQueen[i][j]==1)
                return false;
        }
        for (int i = row,j=col; i>=0 && j<boardQueen.length ; i--,j++) {
            if (boardQueen[i][j]==1)
                return false;
        }
        return true;
    }
}
/*
[0, 0, 0, 1, 0, 0]
[1, 0, 0, 0, 0, 0]
[0, 0, 0, 0, 1, 0]
[0, 1, 0, 0, 0, 0]
[0, 0, 0, 0, 0, 1]
[0, 0, 1, 0, 0, 0]
 */