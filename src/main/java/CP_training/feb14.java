package CP_training;

import java.util.*;

//  RECURSION AND BACKTRACKING
public class feb14 {
    public static void main(String[] args) {
        System.out.println("power 2^10: "+powerRec(2,10));
        System.out.println("Fast exp 2^10: "+fastExp(2,10));
//        genBalParam("",3,3);
//        genSubsets(0,new HashSet<>());
//        String a="hello";
//        ArrayList<Integer> ops=new ArrayList<>();
//        genPermutations("123",0,0,ops);
//        Collections.sort(ops);
//        System.out.println(ops);
//        letterCombinations("23");
//        subsetsWithDup(new int[]{1,2,2});
//        permute(new int[]{1,2,3});
//        System.out.println("Res: "+kSymbol02(4,5));
        HashSet<Character> vob=new HashSet<>();
        vob.add('a');vob.add('e');vob.add('i');vob.add('o');vob.add('u');
        genStringSpace("abc","",0,vob);

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

    //kSymbol
    public static int kSymbol02(int n,int k){
        if (k==1 && n==1)
            return 0;
        int mid= (int) (Math.pow(2,n-1)/2);
        if (k<=mid)
            return kSymbol02(n-1,k);
        else
            return 1-kSymbol02(n-1,k-mid);
    }


    public static void  genStringSpace(String s,String cur,int index, HashSet<Character> vowels){
        if (index==s.length()){
            System.out.println(cur);
            return;
        }
        if (vowels.contains(s.charAt(index))){
            genStringSpace(s,cur.concat(String.valueOf(s.charAt(index))),index+1,vowels);
        }
        else {
            genStringSpace(s,cur.concat(" ").concat(String.valueOf(s.charAt(index))),index+1,vowels);
            genStringSpace(s,cur.concat(String.valueOf(s.charAt(index))),index+1,vowels);
        }
    }
    public int kthGrammar(int N, int K) {
        return kth(0, N, K);
    }

    private int kth(int m, int N, int K) {
        if (N == 1) return m;
        int mid = (int)Math.pow(2, N-2);
        if (K <= mid) {
            return kth(m, N-1, K);
        } else {
            return kth(1-m, N-1, K-mid);
        }
    }
//    public static void  kSymbol(int level, int k, int cur, StringBuilder op, int limit, int i){
//        System.out.println("Lenght: "+op.length()+" cur: "+op);
//        if (i==k){
//            System.out.println("Curr: "+cur);
//            System.out.println(op);
//            return;
//        }
//        if (level==limit){
//            k++;
//            return;
//        }
//        if (cur==0){
//            kSymbol(level+1,k,0,op,limit,i);
//            kSymbol(level+1,k,1,op,limit,i);
//        }
//        else {
//            kSymbol(level+1,k,1,op,limit,i);
//            kSymbol(level+1,k,0,op,limit,i);
//        }
//    }
    //Letter combinations
    public static void letterCombinations(String digits) {
        List<String> op=new ArrayList<>();
        String[] letters=new String[]{"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        genLetterComb(letters,"23","",0,op);
        System.out.println(op);
    }
    public static void genLetterComb(String[] letters,String digits,String cur,int num,List<String> ops){
        if (num>=digits.length())
            return;
        int curNum=Integer.parseInt(String.valueOf(digits.charAt(num)));
        System.out.println("Cur num: "+curNum);
        String chars=letters[curNum];
        System.out.println("Cur letters:  "+chars);
        for (int i = 0; i < chars.length(); i++) {
            String temp=cur.concat(String.valueOf(chars.charAt(i)));
            if (temp.length()==digits.length())
                ops.add(temp);
            genLetterComb(letters,digits,temp,num+1,ops);
        }
    }
    //Generate all nos from given nos
    public static void genPermutations(String main, int cur, int level, ArrayList<Integer> op){
        if (level>=main.length())
            return;
        for (int i = 0; i < main.length(); i++) {
            int temp =cur*10+Integer.parseInt(String.valueOf(main.charAt(i)));
            System.out.println("Cur: "+temp+" level: "+level+" charAt: "+i);
            op.add(temp);
            genPermutations(main,temp,level+1,op);
        }
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new LinkedHashSet<>(), nums); // iteration order = insertion order
        System.out.println(list);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, Set<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList(tmpList));
        } else {
            for(int i = 0; i < nums.length; i++){
                if (tmpList.add(nums[i])) { // elements are unique
                    backtrack(list, tmpList, nums); // recursive call
                    tmpList.remove(nums[i]); // backtrack
                }
            }
        }
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
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> op=new ArrayList<>();
        List<Integer> intern=new ArrayList<>();
        Arrays.sort(nums);

        genSubSet02(nums,0,op,intern);
        op.add(new ArrayList<>());
        System.out.println(op);
        return op;
    }
    public static void genSubSet02(int[] nums,int index,List<List<Integer>> op,List<Integer> intern){
        System.out.println("=====================================");
        System.out.println("index: "+index);
        for (int i = index; i < nums.length; i++) {
            System.out.println("i: "+i);
            if (i>index && nums[i]==nums[i-1]) {
                System.out.println("Continue");
                continue;
            }
            else {
                System.out.println("else");
                intern.add(nums[i]);
                op.add(new ArrayList<>(intern));
                System.out.println("Temp: "+intern);
                System.out.println("Final: "+op);
                genSubSet02(nums,i+1,op,intern);
                intern.remove(intern.size()-1);
                System.out.println("Remove temp: "+intern);
            }
        }
        System.out.println("******Ended index: "+index);
        System.out.println("------------------");
    }

    //Balanced parenthesis generate
    public static void genBalParam(String str,int open,int close){
        System.out.println("Cur: "+str+" open: "+open+" close: "+close);
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