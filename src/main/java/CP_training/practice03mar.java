package CP_training;

import java.util.*;
class car{
    int position;
    double arrivalTime;
    car(int p, double t){
        position=p;
        arrivalTime=t;
    }

    @Override
    public String toString() {
        return "pos=" + position + ", arvTm=" + arrivalTime;
    }
}
public class practice03mar {
    public static void main(String[] args) {
//        System.out.println(myAtoi("  -21231  ")+100);
//        System.out.println(divide(-1,1));
//        System.out.println(simplifyPath("/a/../../b/../c//.//"));
//        System.out.println(simplifyPath("/a//b////c/d//././/.."));
//        kClosest(new int[][]{{1, 3},{-2,2}},1);
//        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
//        removeDuplicates02(new int[]{0,0,1,1,1,1,2,2,2,3,3,4});
//        carFleet(12,new int[]{10,8,0,5,3},new int[]{2,4,1,1,3});
        rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"});
    }
    public int findMaxLength(int[] nums) {
        HashMap<Integer,Integer> counts=new HashMap<>();
        int maxLen=0;
        counts.put(0,-1);
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0){
                count+=-1;
            }
            else {
                count+=1;
            }
            if (counts.containsKey(count)){
                maxLen=Math.max(maxLen,i-counts.get(count));
            }
            else {
                counts.put(count,i);
            }
        }
        return maxLen;
    }
    public static String rankTeams(String[] votes) {
        int teams=votes[0].length();
        String[] tem=votes[0].split("");
        HashMap<String,int[]> ranks=new HashMap<>();
        for (int i = 0; i < teams; i++) {
            ranks.put(tem[i],new int[teams]);
        }
        for (Map.Entry<String, int[]> stringEntry : ranks.entrySet()) {
            System.out.println(stringEntry.getKey()+"  "+ Arrays.toString(stringEntry.getValue()));
        }
        for (int i = 0; i < votes.length; i++) {
            for (int j = 0; j < teams; j++) {
                int voteFor=votes[i].charAt(j)-65;
                ranks.get(tem[j])[voteFor]++;
            }
        }
//        for (Map.Entry<String, int[]> characterEntry : ranks.entrySet()) {
//            System.out.println(characterEntry.getKey()+"  "+ Arrays.toString(characterEntry.getValue()));
//        }
        String ans=votes[0];
        String[] split=ans.split("");
        Arrays.sort(split, (o1,o2)->{
            for (int i = 0; i < ans.length(); i++) {
                if (ranks.get(o1)[i]!=ranks.get(o2)[i])
                    return ranks.get(o2)[i] - ranks.get(o1)[i];
            }
            return Integer.parseInt(o1)-Integer.parseInt(o2);
        });
        System.out.println(Arrays.toString(split));
        StringBuilder sb = new StringBuilder();
        for(String s : split){
            sb.append(s);
        }
        return sb.toString();
    }
    public static int carFleet(int target, int[] position, int[] speed) {
        int n=position.length;
        car[] cars=new car[n];
        System.out.println(Arrays.toString(position));
        System.out.println(Arrays.toString(speed));
        for (int i = 0; i < n; i++) {
            double arvTime=(double) (target-position[i])/speed[i];
            System.out.println("ariv : "+arvTime);
            cars[i]=new car(position[i],arvTime);
        }
        Arrays.sort(cars, Comparator.comparingInt(a -> a.position));
//        Arrays.sort(cars,(a,b)->Integer.compare(a.position,b.position));
        for (car car : cars) {
            System.out.println(car.toString());
        }
        int ans=0;
        int t=n;
        while (--t>0){
            if (cars[t-1].arrivalTime>cars[t].arrivalTime  )
                ans++;  ////if cars[t] arrives sooner, it can't be caught
            else
                cars[t-1]=cars[t];  ////else, cars[t-1] arrives at same time as cars[t]
        }
        return ans + (t == 0 ? 1 : 0); //lone car is fleet (if it exists)
    }
    public static int removeDuplicates02(int[] nums) {
        int dups=1;
        int cnt=1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt<=1 && nums[i]==nums[dups-1]) {
                nums[dups++] = nums[i];
                cnt++;
            }
            if (nums[i]!=nums[dups-1]) {
                nums[dups++] = nums[i];
                cnt=1;
            }
        }
        return dups;
    }
    public static int removeDuplicates(int[] nums) {
        int dups=1;
        System.out.println(Arrays.toString(nums));

        for (int i = 1; i < nums.length; i++) {
            if (nums[i]!=nums[dups-1])
                nums[dups++]=nums[i];
        }
        System.out.println(Arrays.toString(nums));
        return dups;
    }

    public static String simplifyPath(String path) {
        Stack<String> dirMain=new Stack<>();
        char[] absPath=path.toCharArray();
        int n=absPath.length;
        String op="";
        int i=0;
        System.out.println(path);
        while (i<n){
            System.out.println(dirMain+"   i: "+i);
            while (i<n && absPath[i]=='/')
                i++;
            String dir="";
            while (i<n && absPath[i]!='/'){
                dir+=absPath[i];
                i++;
            }
            if (dir==""){
                System.out.println("Dir empty return");
                continue;
            }
            System.out.println("dir: "+dir);
            if (dir.equals("..")){
                if (!dirMain.isEmpty())
                    dirMain.pop();
            }
            else if (dir.equals("."))
                continue;
            else {
                dirMain.push(dir);
            }
            i++;
        }
        Stack<String> rev=new Stack<>();
        while (!dirMain.isEmpty())
            rev.push(dirMain.pop());
        System.out.println(rev);
        while (!rev.isEmpty())
            op=op.concat('/' + rev.pop());

        return op==""?"/":op;
    }

    public static int myAtoi(String s) {
        char[] arr=s.toCharArray();
        boolean neg=false;
        System.out.println(Arrays.toString(arr));
        String op="";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==' ')
                continue;
            else if (arr[i]=='-')
                neg=true;
            else if (Integer.parseInt(String.valueOf(arr[i]))>=1 && Integer.parseInt(String.valueOf(arr[i]))<=9)
                op=op.concat(String.valueOf(arr[i]));
        }
        int res=neg?-1*Integer.parseInt(op):Integer.parseInt(op);
        return res;
    }
    public List<String> generateParenthesis(int n) {
        List<String> op=new LinkedList<>();
        genParn("",n,n,op);
        return op;
    }
    public static void genParn(String str, int open, int close,List<String> ans){
        if (open==0 && close==0){
            ans.add(str);
            return;
        }
        if (open>0)
            genParn(str.concat("("),open-1,close,ans);
        if (close>open)
            genParn(str.concat(")"),open,close-1,ans);
    }
    public static int divide(int dividend, int divisor) {
        if (dividend==0 || Math.abs(divisor)>Math.abs(dividend))
            return 0;
        boolean neg=(dividend<0)^(divisor<0);
        if (divisor<0) {
            divisor = -divisor;
        }
        if (dividend<0) {
            dividend = -dividend;
        }
        int res=1;
        System.out.println("DIv: "+dividend+" divr: "+divisor);
        int t=divisor;
        while (divisor<dividend){
            divisor=divisor+t;

            if (divisor<dividend)
                res++;
            System.out.println("divr: "+divisor+" res: "+res);
        }
        return neg?-res:res;
    }
    public int findPeakElement(int[] nums) {
        int n=nums.length;
        if (n==1)
            return nums[0];
        if (nums[0]>=nums[1])
            return nums[0];
        if (nums[n-1]>=nums[n-2])
            return nums[n-1];
        for (int i = 1; i < n-1; i++) {
            if (nums[i]>nums[i-1] && nums[i]>=nums[i+1])
                return nums[i];
        }
        return -1;
    }
    public static int[][] kClosest(int[][] points, int K) {
        System.out.println(Arrays.deepToString(points));
        Arrays.sort(points, Comparator.comparingDouble(a ->  Math.sqrt(a[0]*a[0]+a[1]*a[1])));
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        System.out.println(Arrays.deepToString(points));

        int[][] op=new int[K][2];
        for (int i = 0; i < op.length; i++) {
            for (int j = 0; j < 2; j++) {
                op[i][j]=points[i][j];
            }
        }
        System.out.println(Arrays.deepToString(op));
        return op;
    }

    /*
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || (head.next==null)) return head;
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;
        ListNode tmp = head;
        while(tmp!=null){
            if(tmp.next != null){
                if(tmp.val == tmp.next.val){
                    int val = tmp.val;
                    while(tmp!=null && tmp.val==val){
                        tmp = tmp.next;
                    }
                    dummy.next = tmp;
                }else{
                    dummy.next = tmp;
                    tmp = tmp.next;
                    dummy = dummy.next;
                }
            }else break;
        }

        return ans.next;
    }
     */
}
