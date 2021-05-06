package dsa450;

import java.math.BigInteger;
import java.util.*;

public class greedyApproach {
    public static void main(String[] args) {
//        System.out.println("Min rope cost: "+minCost(new long[]{4, 3, 2, 6},4));
//        System.out.println(candyStore(new int[]{3,2,1,4},4,2));
        minCostCut(new int[]{2,1,3,1,4},new int[]{4,1,2},6,4);
        System.out.println(mergeKArrays(new int[][]{{2,5,9},{44,51,65},{79,88,89}},3));
        System.out.println(findMaximumNum("1034",2));
    }
    public static void preorderToPostorder(int[] preOrder,int start,int end){
        if (start>end)
            return;
        int mid=start+1;
        while (mid<=end && preOrder[mid]<preOrder[start])
            mid++;
        mid--;
        preorderToPostorder(preOrder,start+1,mid);
        preorderToPostorder(preOrder,mid+1,end);
        System.out.print(preOrder[start]+" ");
    }
    public static NodeBST constructTree(int pre[], int size) {
        NodeBST root = new NodeBST(pre[0]);
        Stack<NodeBST> s = new Stack<>();
        s.push(root);
        for (int i = 1; i < size; ++i) {
            NodeBST temp = null;
            //we need to remove smaller elements till we get a element which will be root and set the cur element
            // to right element
            while (!s.isEmpty() && pre[i] > s.peek().data) {
                temp = s.pop();
            }
            //if we found a element greater than cur element that will be its root so set at right
            if (temp != null) {
                //for element on the right we will not be able to find any greater element on top of stack
                //so the element on the
                temp.right = new NodeBST(pre[i]);
                s.push(temp.right);
            } else {
                //if current element not is greater than any element than this element is left to the left
                // temp = s.peek();
                //the element on the left of root will be the element just smaller than the roo
                //se we remove all the stack elements which are greater to the current element
                //bcoz the left element will be smaller than the root element
                s.peek().left = new NodeBST(pre[i]);
                s.push(s.peek().left);
            }
        }
        return root;
    }
    public static TreeNode constructBSTpreorder(int[] preorder){
        return constBSTutil(preorder,0,preorder.length);
    }
    public static TreeNode constBSTutil(int[]pre,int rootIndex,int right){
        if (rootIndex>=right)
            return null;
        TreeNode rtNew=new TreeNode(pre[rootIndex]);
        int index=rootIndex+1;
        while (index<pre.length && pre[index]<rtNew.val)
            index++;
        rtNew.left=constBSTutil(pre,rootIndex+1,index);
        rtNew.right=constBSTutil(pre,index,right);
        return rtNew;
    }
    Node copyList(Node head) {
        Node cur=head;
        Node front=head;
        while (cur!=null){
            front=cur.next; //storing next pointer
            Node copy=new Node(cur.data);    //creating a copy node
            cur.next=copy;              //setting the link to copy node and break the orignal next link
            copy.next=front;        // setting copy next to orignal next
            cur=front;      //moving cur to next orignal
        }
        cur=head;
        while (cur!=null){
            if (cur.arb!=null)
                cur.next.arb=cur.arb.next;
                //  cur.next.arb  --- current new copy node arb set to
                //  cur.arb.next  --- where the orignal node next(copy) is pointing
            // setting the arbtirary link of copy node to the arb link of orignal node
            cur=cur.next.next;
        }
        Node psudoHead=new Node(0);
        Node copy = psudoHead;
        cur=head;
        while (cur!=null){
            front=cur.next.next;
            copy.next=cur.next;
            cur.next=front;       // adding front to cur.next
            copy=copy.next;     //moving copy pointer to next
            cur=front;       //moving cur/iter pointer to next/front
        }
        return psudoHead.next;
    }
    //smallest number with sum of digits S and no of digits d
    static String smallestNumber(int S, int D){
        if (S==0){
            return "-1";
        }
        if (S>9*D)
            return "-1";
        int[] number=new int[D];
        S=S-1;
        // we reduce sum by one so that we can add  one at last, because it should be atleast d digit num
        for (int i = D-1; i >0 ; i--) {
            if (S>9) {
                number[i] = 9;
                S=S-9;
            }
            else {
                number[i] = S;
                S=0;
            }
        }
        number[0]=S+1;  // 1 is the earlier subtracted one
        String num="";
        for (int i : number) {
            num+=i;
        }
        return num;
    }
    /*
    There are given N ropes of different lengths, we need to connect these ropes into one rope.
    The cost to connect two ropes is equal to sum of their lengths. The task is to connect the ropes
    with minimum cost.
     */
    static long minCost(long arr[], int n)
    {
        PriorityQueue<Long> heap=new PriorityQueue<>(Long::compare);
        for (long l : arr) {
            heap.add(l);
        }
        long totalCost=0;
        while (heap.size()>1){
            long one=heap.poll();
            long two=heap.poll();
            totalCost+=(one+two);
            heap.add(one+two);
        }
        return totalCost;
    }

    //Function to find the maximum number of meetings that can
    //be performed in a meeting room.
    public static int maxMeetings(int start[], int end[], int n) {
        int maxMeeting=0;
        ArrayList<meeting> meets=new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            meets.add(new meeting(i+1,start[i],end[i]));
        }
        Collections.sort(meets); // sorting according to meeting end time : attending the first ending time meeting first
        ArrayList<Integer> ans=new ArrayList<>();   // to store the order of meeting
        ans.add(meets.get(0).index);        // first meeting
        int limit=meets.get(0).end;     // first meeting ending time
        for (int i = 1; i <start.length ; i++) {
            if (meets.get(i).start>limit){  // if cur meet start time is greater than prev meet end time, only then we can attend this meet
                limit=meets.get(i).end;
                ans.add(meets.get(i).index);
            }
        }
        return ans.size();
    }
    // find maximum product possible with the subset of elements present in the array
    public static int maxSubsetProduct(int[] arr){
        int  n=arr.length;
        if (n==1)
            return arr[0];
        int maxNeg=Integer.MIN_VALUE;
        int countNeg=0;
        int countZeros=0;
        int prod=1;
        for (int i = 0; i < n; i++) {
            if (arr[i]==0){
                countZeros++;
                continue;
            }
            if (arr[i]<0){
                countNeg++;
                maxNeg=Math.max(maxNeg,arr[i]);
            }
            prod=prod*arr[i];
        }
        if (countZeros==n)
            return 0;
        // If there are odd number of
        // negative numbers
        if (countNeg%2==1){
            // Exceptional case: There is only
            // negative and all other are zeros
            if (countNeg==1 && countZeros>0 && countZeros+countNeg==n)
                return 0;
            // Otherwise result is product of
            // all non-zeros divided by
            //negative number with
            // least absolute value.
            prod=prod/maxNeg;
        }
        return prod;
    }

    // Find max and min cost to buy all candies :; if for one candie bought we can get k candies free
    static ArrayList<Integer> candyStore(int candies[],int N,int K){
        Arrays.sort(candies);
        System.out.println(Arrays.toString(candies));
        int maxCost=0;
        int minCost=0;
        int start=0;
        int lim=N-1;
        while (start<=lim) {
            System.out.println("Sta: "+start+" lim: "+lim);
            minCost+=candies[start];
            start++;
            lim-=K;
        }
        System.out.println("min end----------: "+minCost);
        start=N-1;
        lim=0;
        while (lim<=start) {
            System.out.println("Sta: "+start+" lim: "+lim);
            maxCost+=candies[start];
            start--;
            lim+=K;
        }
        ArrayList<Integer> op=new ArrayList<>();
        op.add(minCost);
        op.add(maxCost);
        return op;
    }
    //DONE HUFFMAN ENCODING
    public void getCode(huffNode root,String str,ArrayList<String> res){
        if (root==null)
            return;
        if (root.character!='$')
            res.add(str);
        getCode(root.left,str+"0",res);
        getCode(root.right,str+"1",res);
    }
    public ArrayList<String> huffmanCodes(String S, int f[], int N)
    {
        ArrayList<String> op=new ArrayList<>();
//        PriorityQueue<huffNode> pq=new PriorityQueue<>(Comparator.comparing(huffNode::getFreq));
//        PriorityQueue<huffNode> pq=new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));
        PriorityQueue<huffNode> pq=new PriorityQueue<>((o1, o2) -> {
            if (o1.freq>= o2.freq)
                return 1;
            else
                return -1;
        });
        for (int i = 0; i < f.length; i++) {
            pq.add(new huffNode(f[i],S.charAt(i)));
        }
        huffNode left,right,top;
        while (pq.size()!=1){
            left=pq.poll();
            right=pq.poll();
            top=new huffNode(left.freq+right.freq,'$');
            top.left=left;
            top.right=right;
            pq.add(top);
        }
        getCode(pq.poll(),"",op);
        return op;
    }

    //WATER CONNECTION PROBLEM
    //given connection of ith pipe from A[i] -> B[i] of D[i] diameter pipe
    // find efficient n/w of pipe
    //OUTPUT::  HOUSE NO OF TANK, HOUSE NO OF TAP,  MIN DIAMETER OF PIPE B/T THEM
    int[] endHouse=new int[1001];
    int[] startHouse=new int[1001];
    int[] diamater=new int[1001];
    ArrayList<ArrayList<Integer>> solve(int n, int p, ArrayList<Integer> a ,ArrayList<Integer> b ,ArrayList<Integer> d) {
        // House with 1 out-pipe and 0 in-pipe get a TANK
        // House with 1 in-pipe and 0 out-pipe get a TAP
        int i=0;
        while (i<a.size()){
            int s=a.get(i);
            int e=b.get(i);
            int dm=d.get(i);

            endHouse[s]=e;
            startHouse[e]=s;
            diamater[s]=dm;
            i++;
        }
        ArrayList<ArrayList<Integer>> op=new ArrayList<>();

        for (int j = 1; j <=n; ++j) {
            if (startHouse[j]==0 && endHouse[j]>0){
                diMin=Integer.MAX_VALUE;
                int endHouseReturned=DFSpipes(j);
                ArrayList<Integer> tem=new ArrayList<>();
                tem.add(j);
                tem.add(endHouseReturned);
                tem.add(diMin);
                op.add(tem);
            }
        }
//        System.out.println(start.size());
//        for (int j = 0; j < start.size(); ++j)
//            System.out.println(start.get(j) + " " + end.get(j) + " " + dim.get(j));
        return op;
    }
    int diMin;
    int DFSpipes(int houseNo){          // dfs to the last connected house and create a link sort of
        if (startHouse[houseNo]==0)
            return houseNo;
        diMin=Math.min(diMin,diamater[houseNo]);
        return DFSpipes(endHouse[houseNo]);
    }
//Maximize sum(arr[i]*i) of an Array
    int Maximize(int arr[], int n)
    {
        Arrays.sort(arr);
        long ans=0;
        for (int i = 0; i < n; i++) {
            ans=ans+ (long) arr[i] *i;
        }
        return (int) (ans%1000000007);
    }

    //Check if it is possible to survive on Island
    // if yes find minimum number of days required to buy foo
    public static void canSurvive(int toSurvive, int eachDayBuy,int needEachDay){
        if ((eachDayBuy*6)<(needEachDay*7) && toSurvive>6 || needEachDay>eachDayBuy){
            System.out.println("cannot survive");
            return;
        }
        // If we can not buy at least a week
        // supply of food during the first
        // week OR We can not buy a day supply
        // of food on the first day then we
        // can't survive.
        int days=(needEachDay*toSurvive)/eachDayBuy;
        // If we can survive then we can
        // buy ceil(A/N) times where A is
        // total units of food required.
        if ((needEachDay*toSurvive)%eachDayBuy!=0)
            days++;
        System.out.println("yes we can survive days: "+days);
    }
    public static void minCostCut(int[] horizontal,int[] vertical,int m,int n){
        int h=1;
        int v=1;
        Arrays.sort(horizontal);
        int i=0,j=m-2;
        while (i<=j){
            int t=horizontal[i];
            horizontal[i]=horizontal[j];
            horizontal[j]=t;
            i++;
            j--;
        }
        Arrays.sort(vertical);
        System.out.println(Arrays.toString(horizontal));
        i=0;j=n-2;
        while (i<=j){
            int t=vertical[i];
            vertical[i]=vertical[j];
            vertical[j]=t;
            i++;
            j--;
        }
        System.out.println(Arrays.toString(vertical));
        i=0;j=0;
        int ans=0;
        while (i<m-1 && j<n-1){
            if (horizontal[i]>vertical[j]){ //when we make a horizontal cut no of horizontal part inc
//                System.out.println("H-cut: "+(horizontal[i]*v));
                ans+=horizontal[i]*v;
                ++h;
                ++i;
            }
            else {      //when we make a vertical cut no of vertical part inc
//                System.out.println("VV-cut: "+(vertical[j]*h));
                ans+=vertical[j]*h;
                v++;
                j++;
            }
        }
        if(i<m){
            int sum = 0;
            while(i<m-1){
                sum += horizontal[i];
                ++i;
            }
//            System.out.println("hor add: "+sum*v);
            ans += sum*v;
        } else {
            int sum = 0;
            while(j<n-1){
                sum += vertical[j];
                ++j;
            }
            System.out.println("ver add: "+sum*h);
            ans += sum*h;
        }
        System.out.println("min cost of cutting: "+ans);
    }

    // all the array to priority queue with index of all to first element
    //we choose the first smallest element from the array, inc its index by one and now
    //push it back to the que. and continue this step, till queue is not empty
    public static ArrayList<Integer> mergeKArrays(int[][] arrays,int k)
    {
        //priority queue sorted by the array first index
        PriorityQueue<heapItem> pq=new PriorityQueue<>();
        ArrayList<Integer> op=new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            pq.add(new heapItem(arrays[i],0));
        }
        while (!pq.isEmpty()){
            heapItem cur=pq.poll();
            op.add(cur.arr[cur.index]);
            if (cur.index<cur.arr.length-1){
                cur.index++;
                pq.add(cur);
            }
        }
        return op;
    }

    public String reorganizeString(String S) {
        return ";";
    }
    String solve(int[] arr, int n) {
        Arrays.sort(arr);
        BigInteger one=new BigInteger("0");
        BigInteger two=new BigInteger("0");
        for (int i = 0; i < n; i++) {
            if (i%2==0) {
                one=one.multiply(new BigInteger("10")).add(new BigInteger(String.valueOf(arr[i])));
            }
            else {
                two=two.multiply(new BigInteger("10")).add(new BigInteger(String.valueOf(arr[i])));
            }
        }
        return String.valueOf(one.add(two));
    }
    // function to find maximum integer possible by
    // doing at-most K swap operations on its digits
    public static void findMaximumNumUtil(char ar[], int k, Res r)
    {
        // r.max = "";
        if (k == 0) return;
        // char ar[] = str.toCharArray();
        int n = ar.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                // if digit at position i is less than digit
                // at position j, swap it and check for maximum
                // number so far and recurse for remaining swaps
                if (ar[j] > ar[i])
                {
                    char temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;

                    String st = new String(ar);
                    // If current num is more than maximum so far
                    if (Res.max.compareTo(st) < 0)
                    {
                        Res.max = st;
                    }
                    // recurse of the other k - 1 swaps
                    findMaximumNumUtil(ar, k - 1, r);

                    // backtrack
                    temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;
                }
            }
        }
    }

    //largest number in atmost k swap
    public static String findMaximumNum(String str, int k){
        Res r = new Res();
        Res.max = str;
        findMaximumNumUtil(str.toCharArray(), k, r);
        return Res.max;
    }



    ///ENDING CLASS
}
class numDigit implements Comparable<numDigit>{
    int val;
    int index;
    numDigit(int v,int i){
        this.val=v;
        this.index=i;
    }

    public int compareTo(numDigit o) {
        return Integer.compare(this.val,o.val);
    }
    public String toString(){
        return val+"";
    }
}







/*
//Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        Arrays.sort(arr,(a,b)->b.profit-a.profit);
        int maxDeadline=0;
        for (int i = 0; i < n; i++) {
            maxDeadline=Math.max(maxDeadline,arr[i].deadline);
        }
        int[] result=new int[maxDeadline+1];
        Arrays.fill(result,-1);
        int countJobs=0,jobProfit=0;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i].deadline; j >0 ; j--) {
                if (result[j]==-1){
                    result[j]=i;
                    countJobs++;
                    jobProfit+=arr[i].profit;
                    break;
                }
            }
        }
        return new int[]{countJobs,jobProfit};
    }

     NodeBST root = new NodeBST(pre[0]);
        Stack<NodeBST> s = new Stack<NodeBST>();
        s.push(root);
        for (int i = 1; i < size; ++i) {
            NodeBST temp = null;
            //we need to remove smaller elements till we get a element which will be root and set the cur element
            // to right element
            while (!s.isEmpty() && pre[i] > s.peek().data) {
                temp = s.pop();
            }
            //if we found a element greater than cur element that will be its root so set at right
            if (temp != null) {
                temp.right = new NodeBST(pre[i]);
                s.push(temp.right);
            } else {
                //if current element not is greater than any element than this element is left to the left
                // temp = s.peek();
                s.peek().left = new NodeBST(pre[i]);
                s.push(s.peek().left);
            }
        }
        return root;
 */
class Res {
    static String max = "";
}
class huffNode{
    int freq;
    char character;
    huffNode left;
    huffNode right;
    huffNode(int frq,char ch){
        this.freq=frq;
        this.character=ch;
        left = right = null;
    }

    public char getCharacter() {
        return character;
    }

    public int getFreq() {
        return freq;
    }
}
class meeting implements Comparable<meeting>{
    int start;
    int end;
    int index;
    meeting(int index,int start,int end){
        this.index=index;
        this.start=start;
        this.end=end;
    }

    public int compareTo( meeting o) {
        if (this.end>o.end)
            return 1;
        else if (this.end<o.end)
            return -1;
        else {
            if (this.index>o.index)
                return 1;
            else
                return -1;
        }
    }
}

class heapItem implements Comparable<heapItem>{
    int[] arr;
    int index;
    heapItem(int[] arr,int ind){
        this.arr=arr;
        this.index=ind;
    }
    public int compareTo( heapItem o) {
        return Integer.compare(arr[index], o.arr[o.index]);
    }
}