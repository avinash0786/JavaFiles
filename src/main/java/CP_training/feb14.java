package CP_training;

import java.util.Arrays;
import java.util.HashSet;

//  RECURSION AND BACKTRACKING
public class feb14 {
    public static void main(String[] args) {
        System.out.println("power 2^10: "+powerRec(2,10));
        System.out.println("Fast exp 2^10: "+fastExp(2,10));
        genBalParam("",3,3);
        genSubsets(0,new HashSet<>());
    }
    //  linked list 2 sum
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer=new ListNode();
        ListNode cur=answer;
        int carry=0;
        while (l1!=null || l2!=null || carry!=0){
            int sum=0;
            if (l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if (l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            sum+=carry;
            carry=sum/10;
            cur.next= new ListNode(sum%10);
            cur=cur.next;
        }
        return answer.next;
    }

    //  N-Queen problem
    public static void nQueen(char[][] board,int rowNum, int N){
        if (rowNum==N){
            for (char[] chars : board) {
                System.out.println(Arrays.toString(chars));
            }
            return;
        }
        for (int col = 0; col < N; col++) {
            if (canPlace(rowNum,col)){
                board[rowNum][col]='Q';
                nQueen(board,rowNum+1,N);
                board[rowNum][col]='\u0000';
            }
        }

    }

    private static boolean canPlace(int rowNum, int col) {
        return true;
    }

    //Generete subsets
    static int[] arr=new int[]{1,2,3};
    public static void genSubsets(int index, HashSet<Integer> set){ //  O(2^n*N) N for printing
        if (index>=arr.length) {
            System.out.println(set);
            return;
        }
        set.add(arr[index]);
        genSubsets(index+1,set);
        set.remove(arr[index]);
        genSubsets(index+1,set);
    }

    //Balanced parenthesis generate
    public static void genBalParam(String str,int open,int close){
        if (open==0 && close==0){
            System.out.println(str);
            return;
        }
        if (open>0)
            genBalParam(str.concat("("), open-1, close);
        if (close>open)
            genBalParam(str.concat(")"), open, close-1);
    }
    public static int counter=0;
    //  no of ways to reach destination in stair

    public static int countWaysRetAndComb(int cur,int dest){     //  Return and Combine way O(2^n-1)
        if (cur==dest){ // if reached destination increase path by 1
            return 1;
        }
        if (cur>=dest)  //if already reached just return
            return 0;
        int x=countWaysRetAndComb(cur+1,dest);
        int y=countWaysRetAndComb(cur+2,dest);
        return x+y;
    }

    public static void countWays(int cur,int dest){     //  Global varable way
        if (cur==dest){ // if reached destination increase path by 1
            counter++;
            return;
        }
        if (cur>=dest)  //if already reached just return
            return;
        countWays(cur+1,dest);
        countWays(cur+2,dest);
    }

    public static int fastExp(int x,int n){
//        System.out.println("Call for x: "+x+" n: "+n);
        if (n==0)
            return 1;
        if (n%2==0){
            int y=fastExp(x,n/2);
//            System.out.println("Even Returned: "+(y*y));
            return y*y;
        }
        else {
            n--;
            int y=fastExp(x,n/2);
//            System.out.println("Odd returned: "+(y*y*x));
            return y*y*x;
        }
    }

    public static int powerRec(int a, int n){
        if (n==0)
            return 1;
        int y=powerRec(a,n-1);
        return a*y;
    }
}
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }