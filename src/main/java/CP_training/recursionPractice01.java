package CP_training;

import java.util.*;

public class recursionPractice01 {
    public static void main(String[] args) {
//        System.out.println("Natural sum: "+natSum(5));
//        System.out.println("Is palindrome: "+isPal("asba",0,2));
//        System.out.println("Digit  sum: "+digitSum(111));
//        System.out.println("Max cuts: "+maxCuts(5));
//        genSubset("",0);
//        System.out.println("Count subSet sum: "+countSubsetSum(25,4));
//        stringPerm("ABC",0);
        genPermutations(new int[]{1,2,3});
//        System.out.println("K th grammer: "+kSymbol02(4,5));
//        TreeSet<Integer> ord=new TreeSet<>();
//        TreeMap<Integer,Integer> pp=new TreeMap<>();
//        HashMap<Integer,Integer> qq=new HashMap<>();
//        ord.add(1);
//        ord.add(5);
//        ord.add(10);
//        ord.add(15);
//        ord.add(2);
//        ord.add(6);
//        ord.add(12);
//        ord.add(18);
//        System.out.println(ord);
    }
    //  K-th Grammer
    /*
    Input: N = 4, K = 5
    Output: 1

    Explanation:
    row 1: 0
    row 2: 01
    row 3: 0110
    row 4: 01101001
     */
    public static int kSymbol02(int n,int k){
        System.out.println("Call for: n: "+n+" k: "+k);
        if (k==1 && n==1)
            return 0;
        int mid= (int) (Math.pow(2,n-1)/2);
        System.out.println("Total : "+(int)Math.pow(2,n-1)+"  Mid: "+mid);
        if (k<=mid)
            return kSymbol02(n-1,k);
        else
            return 1-kSymbol02(n-1,k-mid);
    }
    //  generate subsets without duplicate elements
    /*
    Input: nums = [1,2,2]
    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
    ---we can also generate subset and remove duplicate later on but it will be hectic
    ==so we will skip the repeated elements
     */

    public static List<List<Integer>> genSubSetWODupl(int[]arr){
        List<List<Integer>> op=new ArrayList<>();
        List<Integer> intern=new ArrayList<>();
        Arrays.sort(arr);
        subSetWithOutDupl(arr,0,op,intern);
        op.add(new ArrayList<>());
        System.out.println(op);
        return op;
    }
    //  int[] arr,int index, List<List<Integer>> op, List<Integer> intern
    public static void subSetWithOutDupl(int[] arr,int index, List<List<Integer>> op, List<Integer> intern){
        for (int i = index; i < arr.length ; i++) {
            if (i>index && arr[i]==arr[i-1])
                continue;
            else {
                intern.add(arr[index]);
                op.add(new ArrayList<>(intern));
                subSetWithOutDupl(arr, index+1, op, intern);
                intern.remove(intern.size()-1);
            }
        }
    }




    //Permutation generate MO2
    public static List<List<Integer>> genPermutations(int[] nums){
        List<List<Integer>> op=new ArrayList<>();
        genPerBacktrack(op,new LinkedHashSet<>(),nums);
        System.out.println(op);
        return op;
    }
    private static void  genPerBacktrack(List<List<Integer>> list, Set<Integer> temp, int[]nums){
        if (temp.size()==nums.length) {
            list.add(new ArrayList(temp));
        }
        else {
            for (int num : nums) {
                if (temp.add(num)) { // elements are unique
                    genPerBacktrack(list, temp, nums); // recursive call
                    temp.remove(num); // backtrack
                }
            }
        }
    }
    public static void stringPerm(String str, int index){
        if (index==str.length()-1){
            System.out.print(str+" ");
            return;
        }
        for (int i = index; i <str.length() ; i++) {
            str=swapStr(str,index,i);
            stringPerm(str,index+1);
            str=swapStr(str,index,i);
        }
    }
    public static String swapStr(String s, int i, int j){
        char[] arr=s.toCharArray();
        char t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
        return new String(arr);
    }

    static int[] arr=new int[]{10,20,15,5};
    public static int countSubsetSum(int sum, int index){
        if (index==0)
            return sum==0?1:0;
        return countSubsetSum(sum-arr[index-1],index-1)+countSubsetSum(sum,index-1);
    }

    //SUBSET METHOD 2
    static int[] subsArr=new int[]{1,2,3};
    public static void genSubSets02(int index, HashSet<Integer> set){
        if (index>=subsArr.length){
            System.out.println(set);
            return;
        }
        set.add(subsArr[index]);
        genSubSets02(index+1,set);
        set.remove(subsArr[index]);
        genSubSets02(index+1,set);
    }
    static String str="122";
    public static void genSubset(String cur, int index){
        if (index==str.length()) {
            System.out.print(cur + " ");
            return;
        }
        genSubset(cur+str.charAt(index),index+1);
        genSubset(cur,index+1);
    }


    //----MAX ROPE CUTTING SOLUTION WITH DP INIT VARIABLES HERE -----
    static int a=2,b=5,c=1;
    static HashMap<Integer,Integer> DP_maxCut= new HashMap<>();
    public static int maxCuts(int n){
        if (n==0)
            return 0;
        if (n<0)
            return -1;
        if (DP_maxCut.containsKey(n)) {
//            System.out.println("dp hit: "+n);
            return DP_maxCut.get(n);
        }
//        System.out.println("dp mised: "+n);
        int res=Math.max(maxCuts(n-a),Math.max(maxCuts(n-b),maxCuts(n-c)));

        if (res==-1) {
            DP_maxCut.put(n,-1);
            return DP_maxCut.get(n);
        }
        DP_maxCut.put(n,res+1);
        return DP_maxCut.get(n);
    }

    public static boolean isPal(String str, int start, int end){
        if (start>=end)
            return true;
        return str.charAt(start)==str.charAt(end) && isPal(str,start+1,end-1);
    }

    public static int digitSum(int n){
        if (n<=0)
            return 0;
        return n%10+digitSum(n/10);
    }
    public static int natSum(int n){
        if (n<=0)
            return 0;
        else
            return n+natSum(n-1);
    }
}
