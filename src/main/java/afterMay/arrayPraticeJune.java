package afterMay;

import dsa450.ListNode;

import java.util.*;

public class arrayPraticeJune {
    public static void main(String[] args) {
        urlifyString("Mr Jhon Smith    ".toCharArray(),13);
        System.out.println("Combination sum: "+combinationSum(new int[]{2,3,6,7},7));
//        System.out.println("Search in sorted matrix: "+searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},60));
        System.out.println(leastInterval("AAABBB".toCharArray(),2));
        System.out.println(pushDominoes(".L.R...LR..L.."));
    }
    //urlify string inp: mr jhon smith  op: mr%20jhon%20smith
    public static void urlifyString(char[] str,int trueLen){
        int spaceCount=0;
        for (int i = 0; i < trueLen; i++) {
            if (str[i]==' ')
                spaceCount++;
        }
        int index=trueLen+spaceCount*2;
        for (int i = trueLen-1; i >=0; i--) {
            if (str[i]==' '){
                str[index-1]='0';
                str[index-2]='2';
                str[index-3]='%';
                index=index-3;
            }
            else {
                str[index-1]=str[i];
                index--;
            }
        }
        System.out.println(str);
    }
    //check if a given string is a palindrome permutations
    public static boolean isPalidPermutation(String str){
        char[] arr=str.toCharArray();
        int[] count=new int[128];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int cnt=0;
        for (int i = 0; i < count.length; i++) {
            cnt+=count[i]%2;
        }
        return cnt<=1;
    }
    //remove duplicate form a sorted array
    public static int removeDuplicate(int[] arr){
        int dups=1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]!=arr[dups-1])
                arr[dups++]=arr[i];
        }
        return dups;
    }
    //combination sum
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList<>();
        combUtil(candidates,0,target,new ArrayList<>(),result);
        return result;
    }
    private static void combUtil(int[] cand,int index,int target,List<Integer> temp,List<List<Integer>> res){
        if (target<0)
            return;
        if (target==0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < cand.length; i++) {
            temp.add(cand[i]);
            combUtil(cand,i,target-cand[i],temp,res);
            temp.remove(temp.size()-1);
        }
    }
    //combination sum 02,candidates may only be used once in the combination
    //not contain duplicate combinations ::candidates = [10,1,2,7,6,1,5], target = 8
    //op: [1,1,6], [1,2,5], [1,7], [2,6]      TAKE AND DONT TAKE APPROACH
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {   //O(2^n)
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(candidates);
        findCombSum(candidates,0,target,new ArrayList<>(),new ArrayList<>());
        return result;
    }
    private static void findCombSum(int[] cand,int index,int target,List<Integer> temp,List<List<Integer>> res){
        if (target<0)return;
        if (target==0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < cand.length; i++) {
            if (i==index || cand[i]!=cand[i-1]){
                temp.add(cand[i]);
                findCombSum(cand,i+1,target-cand[i],temp,res);
                temp.remove(temp.size()-1);
            }
        }
    }

    //find first missing positive number
    public static int findfirstMissingPos(int[] arr){
        int n=arr.length;
        for (int i = 0; i < n; i++) {
            int curPos=arr[i]-1;
            while (arr[i]>=1 && arr[i]<=n && arr[i]!=arr[curPos]){
                int temp=arr[i];
                arr[i]=arr[curPos];
                arr[curPos]=temp;
                curPos=arr[i]-1;
            }
        }
        for (int i = 0; i < n; i++) {
            if ((i+1)!=arr[i])
                return i+1;
        }
        return -1;
    }
    public static boolean canReachEnd(int[] jumps){
        int n=jumps.length-1;
        int reach=0;
        for (int i = 0; i <= reach; i++) {
            reach=Math.max(reach,jumps[i]+i);
            if (reach>=n)
                return true;
        }
        return false;
    }
    //find min jumps required to reach the end
    public static int minJumpsToReachEnd(int[] jumps,int index){
        if (index==0)
            return 0;
        int res=Integer.MAX_VALUE;
        for (int i = 0; i <=index-2; i++) {
            if (i+jumps[i]>=index-1){
                int subRes=minJumpsToReachEnd(jumps,i+1);
                if (subRes!=Integer.MAX_VALUE)
                    res=Math.min(res,subRes+1);
            }
        }
        return res;
    }
    //merge intervals 
    public static int[][] mergeIntervals(int[][] intervals){
        //sort by starting point of intervals
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        LinkedList<int[]> merged=new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1]<interval[0])
                merged.add(interval);
            else
                merged.getLast()[1]=Math.max(merged.getLast()[1],interval[1]);
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //Given a positive integer n, generate an n x n matrix filled with elements from
    // 1 to n2 in spiral order
    public static int[][] spiralMatGen(int n){
        int[][] op=new int[n][n];
        int top=0,bottom=n-1,left=0,right=n-1;
        int val=1;
        while (val<=n*n){
            for (int i = left; i <=right; i++)
                op[top][i]=val++;
            top++;

            for (int i = top; i <=bottom; i++)
                op[i][right]=val++;
            right--;

            for (int i = right; i>=left; i--)
                op[bottom][i]=val++;
            bottom--;

            for (int i = bottom; i >=top; i--)
                op[i][left]=val++;
            left++;
        }
        return op;
    }
    //unique paths:: find the no of ways to reach bottom-right from top-left
    public static int uniquePaths(int m,int n){
        int[][] grid=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0 || j==0)
                    grid[i][j]=1;
                else
                    grid[i][j]=grid[i][j-1]+grid[i-1][j];
            }
        }
        return grid[m-1][n-1];
    }

    //given cost of each grid cell, find the min path sum dist
    // to reach bottom-right from top-left,
    public static int minPathSum(int[][] grid){
        int m=grid.length;
        int n=grid[0].length;
        int[][] table=new int[m+1][n+1];
        table[0][0]=grid[0][0];
        for (int i = 1; i < m; i++)
            table[0][i]=grid[0][i]+table[0][i-1];
        for (int i = 1; i < n; i++)
            table[i][0]=grid[i][0]+table[i-1][0];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                table[i][j]=grid[i][j]+Math.min(table[i-1][j],table[i][j-1]);
            }
        }
        return table[m-1][n-1];
    }
    public static int[] plusOne(int[] arr){
        int n=arr.length;
        for (int i = n-1; i >=0; i--) {
            if (arr[i]!=9){
                arr[i]++;
                break;
            }
            else
                arr[i]=0;
        }
        if (arr[0]==0){
            int[] res=new int[n+1];
            res[0]=1;
            return res;
        }
        return arr;
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length,n=matrix[0].length;
        //doing binary search in the rows last value
        int top=0,down=m-1,col=n-1;
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("Target:  "+target);
        while (top<=down){
            int mid=(top+down)/2;
            if (matrix[mid][0]<=target && matrix[mid][col]>=target)
                return searchRow(matrix,mid,target);
            if (matrix[mid][col]<target)
                top=mid+1;
            if (matrix[mid][0]>target)
                down=mid-1;
        }
        return false;
    }
    public static boolean searchRow(int[][] mat,int row,int targ){
        int left=0,right=mat[row].length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (mat[row][mid]<targ)
                left=mid+1;
            else if (mat[row][mid]>targ)
                right=mid-1;
            else
                return true;
        }
        return false;
    }
    public static boolean searchMatrixSorted(int[][] mat,int trg){
        int m=mat.length,n=mat[0].length;
        int row=0,col=n-1;
        while (col>=0 && row<=m){
            if (mat[row][col]==trg)
                return true;
            else if (mat[row][col]>trg)
                col--;
            else
                row++;
        }
        return false;
    }
    public static void summaryRange(int[] arr){
        int n=arr.length;
        List<String> op=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start=arr[i];
            while (i+1<n && arr[i+1]==arr[i]+1)
                i++;
            if (start!=arr[i])
                op.add(""+start+"->"+arr[i]);
            else
                op.add(""+start);
        }
        System.out.println(op);
    }
    public static boolean canPlaceFlower(int[] flowerBed,int n){
        int l=flowerBed.length;
        int[] bed=new int[l+2];
        for (int i = 0; i < l; i++) {
            bed[i+1]=flowerBed[i];
        }
        int free=0;
        for (int i = 1; i <= l; i++) {
            if (bed[i]==0 && bed[i-1]==0 && bed[i+1]==0){
                bed[i]=1;
                free++;
            }
        }
        return n<=free;
    }
    public static int leastInterval(char[] tasks, int n) {
        int[] freq=new int[26];
        for (char task : tasks)
            freq[task-'A']++;
        Arrays.sort(freq);
        System.out.println(Arrays.toString(tasks));
        int chunk=freq[25]-1; //max freq element
        int idleSpot=chunk*n;
        System.out.println("Chunk: "+chunk+" idleSpot: "+idleSpot);
        for (int i = 24; i >=0; i--) {
            idleSpot-=Math.min(chunk,freq[i]);
        }
        System.out.println(" idleSpot: "+idleSpot);

        return idleSpot<0?tasks.length:idleSpot+tasks.length;
    }
    public static int[] constructArray(int n, int k) {
        int high=n,low=1;
        int[] res=new int[n];
        int index=0;
        res[index++]=low++;
        boolean isHigh=false;
        while (k>1){
            res[index++]=high--;
            k--;
            isHigh=true;
            if (k>1){
                res[index++]=low++;
                k--;
                isHigh=false;
            }
        }
        while (index<n){
            if (isHigh){
                res[index++]=high--;
            }
            else
                res[index++]=low++;
        }
        return res;
    }
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n=timeSeries.length;
        if (n==0)return 0;
        int total=0;
        for (int i = 0; i < n - 1; i++) {
            total+=Math.min(timeSeries[i+1]-timeSeries[i],duration);
        }
        return total+duration;
    }
    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        HashSet<Integer> hs=new HashSet<>();
        int n=nums.length;
        for (int i = 0; i < n - 1; i++) {
            //finding the nums[i]+k, in rest of array
            if (Arrays.binarySearch(nums,i+1,n,nums[i]+k)>0)
                hs.add(nums[i]);
        }
        return hs.size();
    }
    public static int findPairsHashMapOpt(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int num : nums)
            map.put(num,map.getOrDefault(num,0));
        int n=nums.length;
        int result=0;
        for (Integer val : map.keySet()) {
            if (k==0 && map.get(val)>1 || k>0 && map.containsKey(val+k))
                result++;
        }
        return result;
    }
    //LeetCode 697. Degree of an Array
    //find min size subarray with degree same as the degree of the given array
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> freqMap=new HashMap<>();
        HashMap<Integer,Integer> firstSeen=new HashMap<>();//to store the first occr index of
        int degree=0,minLen=0;
        for (int i = 0; i < nums.length; i++) {
            firstSeen.putIfAbsent(nums[i],i);
            freqMap.put(nums[i],freqMap.getOrDefault(nums[i],0)+1);
            //if cur ele degree is greater than the prev degree, we update degree and calc subarray len
            if (freqMap.get(nums[i])>degree){
                degree=freqMap.get(nums[i]);
                minLen=i-firstSeen.get(nums[i])+1;
            }
            //if 2 elem has same degree take the min subarray length
            else if (freqMap.get(nums[i])==degree){
                minLen=Math.min(minLen,i-firstSeen.get(nums[i])+1);
            }
        }
        return minLen;
    }

    //3 sum closest,find three integers in nums such that the sum is closest to target
    public static int threeSumClosest(int[] arr,int target){
        int n=arr.length;
        int result=arr[0]+arr[1]+arr[n-1];
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            int start=i+1;
            int end=n-1;
            while (start<end){
                int sum=arr[i]+arr[start]+arr[end];
                if (sum>target)
                    end--;
                else
                    start++;
                if (Math.abs(sum-target)<Math.abs(result-target))
                    result=sum;
            }
        }
        return result;
    }
    //return starting index of all Substring with Concatenation of All given Words
    public static List<Integer> findSubstring(String S, String[] L) {
        List<Integer> index=new ArrayList<>();
        int wordLen=L[0].length();
        Map<String,Integer> map=new HashMap<>();
        for (String s : L)
            map.put(s,map.getOrDefault(s,0)+1);
        //consider each index as starting index of substring
        for (int i = 0; i < S.length(); i++) {
            Map<String,Integer> curWords=new HashMap<>(map);
            //check of all words are present in the substring starting from this index
            for (int j = 0; j < L.length; j++) {
                String word=S.substring(i+j*wordLen,i+j*wordLen+wordLen);
                //if this word is a valid word, we remove it from the curWord list, and find other words in cur substring
                if (curWords.containsKey(word)){
                    int count=curWords.get(word);
                    if (count==1)
                        curWords.remove(word);
                    else
                        curWords.put(word,count-1);//decrement the count, to handle case of multiple same words
                    //if all words are matched: means map is empty, put this substring index into result
                    if (curWords.isEmpty()){
                        index.add(i);
                        break;
                    }
                }
                else
                    break;
            }
        }
        return index;
    }
    //Given the head of a linked list and a value x, partition it such that all nodes
    // less than x come before nodes greater than or equal to x.
    //You should preserve the original relative order of the nodes
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead=new ListNode(0);
        ListNode small=smallHead;
        ListNode moreHead=new ListNode(0);//to keep track of the head
        ListNode more=moreHead;//to iterate
        while (head!=null){
            if (head.val<=x){
                small.next=head;
                small=small.next;
            }
            else {
                more.next=head;
                more=more.next;
            }
            head=head.next;
        }
        more.next=null;
        small.next=moreHead.next;
        return smallHead.next;
    }
    //partition lable
    public List<Integer> partitionLabels(String s) {
        int[] lastIndex=new int[26];
        for (int i = 0; i < s.length(); i++)
            lastIndex[s.charAt(i)-'a']=i;
        int j=0,start=0;
        List<Integer> res=new ArrayList<>();
        //we need to find the points where there is no element having its occurrence ahead this index/point
        for (int i = 0; i < s.length(); i++) {
            j=Math.max(j,lastIndex[s.charAt(i)-'a']);
            if (i==j){
                res.add(i-start+1);
                start=i+1;
            }
        }
        return res;
    }
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n=difficulty.length;
        work[] jobs=new work[n];
        for (int i = 0; i < n; i++)
            jobs[i]=new work(difficulty[i],profit[i]);
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.diff));
        Arrays.sort(worker);
        System.out.println(Arrays.toString(jobs));
        int ans=0,i=0,best=0;
        for (int curSkill : worker) {
            while (i<n && curSkill>=jobs[i].diff)
                best=Math.max(best,jobs[i++].profit);
            ans+=best;
        }
        return ans;
    }
    //838. Push Dominoes, L-left push R-right push .- no push standing domino
    //return the final state of domino after all domino have been pushed left/right
    public static String pushDominoes(String dominoes) {
        char[] arr=dominoes.toCharArray();
        StringBuilder result=new StringBuilder();
        int n=dominoes.length();
        int[] forces=new int[n];
        int force=0;//calc force from left to right
        for (int i = 0; i < n; i++) {
            if (arr[i]=='R')
                force=n;
            else if (arr[i]=='L')
                force=0;
            else
                force=Math.max(force-1,0);
            forces[i]+=force;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(forces));
        force=0;//calc force from right to left
        for (int i = n-1; i >=0; i--) {
            if (arr[i]=='L')
                force=n;
            else if (arr[i]=='R')
                force=0;
            else
                force=Math.max(force-1,0);
            forces[i]-=force;
        }
        System.out.println(Arrays.toString(forces));
        for (int i : forces) {
            if (i>0)
                result.append('R');
            else if (i<0)
                result.append("L");
            else
                result.append(".");
        }
        return result.toString();
    }
}
class work{
    int diff;
    int profit;
    work(int d,int p){
        this.diff=d;
        this.profit=p;
    }
    public String toString(){
        return "["+diff+","+profit+"]";
    }
}
