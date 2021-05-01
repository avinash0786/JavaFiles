package dsa450;

import java.util.*;

public class apr08 {
    public static void main(String[] args) {
        sort012(new int[]{1,2,0,2,1,2,0,1,0,2,1});
        System.out.println("Kadens algo: "+kadensAlgo(new int[]{-1,-2,-3,-4}));
        System.out.println("Count inversion: "+countInversion(0,2));
        System.out.println("Sum > x: "+sb(new long[]{1,4,45,6,0,9},6,51));
        System.out.println("Max profit stock: "+stockMaxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println("longest palindrome subSquence: "+longestPalindromeSubString("aaaabbaa"));
        System.out.println("longest Repeting subsequence: "+LRS("AABB"));
        System.out.println("min no of swap req to balance: "+minimumNumberOfSwaps("[]]][[]["));
        System.out.println("Post fix eval: "+evaluatePostfix("231*+9-"));
        System.out.println("Longest vaid substring: "+findMaxLen(")))((("));
        System.out.println("Check redund brack: "+checkRedundancy("(a+(b)/c)"));
    }
    //Sort array of 0,1,2  given [0,2,1,2,0,1] sort to [0,0,1,1,2,2]
    public static void sort012(int[] arr){
        int high=arr.length-1;
        int low=0;
        int mid=0;
        while (mid<=high) {
            if (arr[mid]==0){     // if  0 swap
                swap(arr,mid,low);
                low++;
                mid++;
            }
            else if (arr[mid]==1){
                mid++;
            }
            else {
                swap(arr,mid,high);
                high--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void swap(int[] arr, int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void reverse(int[]arr,int  i,int j){
        while (i<j)swap(arr,i++,j--);
    }
    // largest sum contiguous array
    public static int kadensAlgo(int[] arr){
        int resSum=Integer.MIN_VALUE;
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            resSum=Math.max(resSum,sum);
            if (sum<0)
                sum=0;
        }
        return resSum;
    }
    //minimize the difference bt maximum and minimum height
    public static int minimizeHeight(int[] heights,int k){
        int n=heights.length;
        return 1;
    }
    public void nextPermutation(int[] nums) {
        if (nums.length<=1)
            return;
        int i=nums.length-2;
        while (i>=0 && nums[i]>=nums[i+1]) {
            i--;
        }
        if (i>=0){
            int j=nums.length-1;
            while (nums[j]<=nums[i])
                j--;
            swap(nums,i,j);
        }
        reverse(nums,i+1,nums.length-1);
    }
    public String countAndSay(int n) {
        if(n==1) return "1";
        String ans = "1";
        for(int i=2; i<=n; i++){
            ans = f(ans);
        }
        return ans;
    }

    public String f(String s){
        char ch = s.charAt(0);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) != ch){
                sb.append(count).append(ch);
                ch = s.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        sb.append(count).append(ch);
        return sb.toString();
    }
    public static boolean satisfyNeed(long[] arr,long blade,long m){
        long sum=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>blade)
                sum+=arr[i]-blade;
        }
        return sum>=m;
    }
    public static long mirikoTreeCut(long[] trees,long m){
        long low=0;
        long high=0;
        for (long tree : trees) {
            high=Math.max(high,tree);
        }

        long ans=0;
        while (low<=high){
            long bladeHeight=(low+high)/2;
            if (satisfyNeed(trees,bladeHeight,m)){
                ans=bladeHeight;
                low=bladeHeight+1;
            }
            else
                high=bladeHeight-1;
        }
        return ans;
    }
    //Maximum product subarray
    // we need to keep track of prev subarray min  bcoz if a -ve ele occur then
    //pre min and cur may become out max product bacause let -12(pre sub array min) and cur -30
    /// would give +ve 360 which could be greater than the positive subarray we got in prev subarray
    long maxProduct(int[] arr, int n) {
        long ans=arr[0];
        long maxVal=arr[0];
        long minVal=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if (arr[i]<0){
                long temp=maxVal;
                maxVal=minVal;
                minVal=temp;
            }
            maxVal=Math.max(maxVal*arr[i],arr[i]);
            minVal=Math.min(minVal*arr[i],arr[i]);
            ans=Math.max(ans,maxVal);
        }
        return ans;
    }

    //count inversions
    static int[] cntInvArr=new int[]{10, 10, 10};
    public static int countInversion(int low,int high){
        int res=0;
        if (low<high){
            int mid=low+(high-low)/2;
            res+=countInversion(low,mid);
            res+=countInversion(mid+1,high);
            res+=countAndMergeInv(low,mid,high);
        }
        return res;
    }
    //if left[i] > right[j] in 2 subarray left and right
    //then a element in left will be inverson for all the element in right from that index
    //because both are sorted subarray
    public static int countAndMergeInv(int low,int mid,int high){
        int n1=mid-low+1;
        int n2=high-mid;
        int[] left=new int[n1];
        int[] right=new int[n2];
        for (int i = 0; i < n1; i++) {
            left[i]=cntInvArr[low+i];
        }
        for (int i = 0; i < n2; i++) {
            right[i]=cntInvArr[mid+i+1];
        }
        int res=0,i=0,j=0,k=low;
        while (i<n1 && j<n2){
            if (left[i]<=right[j]){
                cntInvArr[k++]=left[i++];
            }
            else {// as both array are sorted of a single element is greater ,
                // all element after that element will be greate and form invresion
                // eg [5,10,15,16,18]  [6,9,30,45]  we have 2 element 10>6 and 10>9
                // all element after 10 in left would be greater than6
                // all element after 10 will be greater than 9 so they form a inversion
                //  EG  :  [10,6], [15,6],[16,6], [18,6]
                // and  :  [10,9], [15,9],[16,9], [18,9]
                cntInvArr[k++]=right[j++];
                res=res+(n1-i);
            }
        }
        while (i < n1) {
            cntInvArr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            cntInvArr[k] = right[j];
            j++;
            k++;
        }
        return res;
    }
    // minimum subarray with subarray sum x
    public static long sb(long a[], long n, long x) {
        long start=0;
        long end=0;
        long curSum=0;
        long minLen=n+1;
        while (end<n){
            while (curSum<=x && end<n)
                curSum+=a[(int) end++];

            while (curSum>=x && start<n){
                if (end-start<minLen)
                    minLen=end-start;
                curSum-=a[(int) start++];
            }
        }
        return minLen;
    }
    //maximum profit made by doing 2 non-overlapping transactions in a day
    //given stock prices throughout a dya
    public static int maxProfit2Trans01Day(int[]prices){
        int n=prices.length;
        int[] DP_PROFIT=new int[n];
        int spRight=prices[n-1];        // we need to sell at max selling price so storing max SP
        int cpLeft=prices[0];       // we need to buy at min CP

        //  PROFIT=SP-COSTPRICE
        //EITHER WE FIND MAX SP OR WE FIND MIN CP IF BTH THEN MAX PROFIT IS POSSIBLE
        //doing  second transaction and calc profit by doing trans from i->n
        for (int i = n-2; i >=0; i--) {
            if (prices[i]>spRight)
                spRight=prices[i];
            //spRight-prices[i]  we keep track of largest SP from right and find profit we can make from current CP price

            DP_PROFIT[i]=Math.max(DP_PROFIT[i+1],spRight-prices[i]);
        }
        //doing first transaction and calc profit by doing trans from 0->i
        for (int i = 1; i < n; i++) {
            if (cpLeft<prices[i])
                cpLeft=prices[i];
            //DP_PROFIT[i]+(prices[i]-cpLeft)
            // expln: DP_PROFIT[i]  profit by doing second trans
            // (prices[i]-cpLeft)   current price minus min CP to get profit
            // we add both as we can perform 2 transactions here
            DP_PROFIT[i]=Math.max(DP_PROFIT[i-1],DP_PROFIT[i]+(prices[i]-cpLeft));
        }
        return DP_PROFIT[n-1];
    }
    public static int stockBuySell(int[] prices){
        int maxProf=0;
        int minPrice=Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]<minPrice)
                minPrice=prices[i];
            else if (prices[i]-minPrice>maxProf)
                maxProf=prices[i]-minPrice;
        }
        return maxProf;
    }
    public static int stockMaxProfit(int[] prices){
        int prof=0;
        for (int i = 1; i <prices.length; i++) {
            if (prices[i]>prices[i-1])
                prof+=prices[i]-prices[i-1];
        }
        return prof;
    }
    public String isSubset( long a1[], long a2[], long n, long m) {
        Set<Long> hash=new HashSet<>();
        for (long l : a1) {
            hash.add(l);
        }
        for (long l : a2) {
            if (!hash.contains(l))
                return "No";
        }
        return "Yes";
    }
    //Longest Palindrome in a String
    // at any index there can be 2 possibility
    // either this index is mid of even length palindrome
    //OR this index is mid if odd lenght palindrome
    //whenever we find a palindrome of greater length we update our result
    public static String longestPalindromeSubString(String str){
        int n=str.length();
        int low,high;
        int palinStart=0;
        int palinLenght=1;
        char[] arr=str.toCharArray();
        for (int i = 1; i <n ; i++) {
            // if this index is mid of even size array then
            //both index will be adjacent
            low=i-1;
            high=i;
            while (low>=0 && high<n && arr[low]==arr[high]){
                if (high-low+1>palinLenght){
                    palinStart=low;
                    palinLenght=high-low+1;
                }
                low--;
                high++;
            }

            // if this index is mid of odd size array then
            //both index will be one space seperated mid elemnt will be unique it dont match any elem
            low=i-1;
            high=i+1;
            while (low>=0 && high<n && arr[low]==arr[high]){
                if (high-low+1>palinLenght){
                    palinStart=low;
                    palinLenght=high-low+1;
                }
                low--;
                high++;
            }
        }
        System.out.println("Input str: "+str);
        return str.substring(palinStart,palinStart+palinLenght);
    }
    //Longest repeating subsequence
    //  it is same as LCS(excuding chars as same index)
    public static int LRS(String s1){
        int m=s1.length();
        int[][] DP_Tab=new int[m+1][m+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=m; j++) {
                // i!=j is one addition to the LCS code
                if (s1.charAt(i-1)==s1.charAt(j-1) && i!=j)
                    DP_Tab[i][j]=1+DP_Tab[i-1][j-1];
                else
                    DP_Tab[i][j]=Math.max(DP_Tab[i-1][j],DP_Tab[i][j- 1]);
            }
        }

        //  Printing LCS string
        System.out.println("Input str: "+s1);
        int index=DP_Tab[m][m];
        char[] lcs=new char[index+1];
        int i=s1.length();
        int j=s1.length();
        while (i>0 && j>0){
            if (s1.charAt(i-1)==s1.charAt(j-1) && i!=j){
                lcs[index-1]=s1.charAt(i-1);
                i--;j--;index--;
            }
            else if (DP_Tab[i-1][j]> DP_Tab[i][j-1])
                i--;
            else
                j--;
        }
        System.out.println("Longest rec  String: "+new String(lcs));
        return DP_Tab[m][m];
    }
    public static int minEditDistDp(String s1, String s2){
        int m=s1.length();
        int n=s2.length();
        int[][] DP_EDIT=new int[m+1][n+1];
        for (int i = 0; i <=m; i++) {
            DP_EDIT[i][0]=i;
        }
        for (int i = 1; i <=n; i++) {
            DP_EDIT[0][i]=i;
        }
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1))
                    DP_EDIT[i][j]=DP_EDIT[i-1][j-1];
                else {
                    DP_EDIT[i][j]=1+Math.min(Math.min(DP_EDIT[i][j-1],DP_EDIT[i-1][j]),DP_EDIT[i-1][j-1]);
                }
            }
        }
        return DP_EDIT[m][n];
    }
    //by solving examples we observed that the no of swap to fix one fault bracket
    //is distance bt the fault bracket and the coming open bracket which will fix it
    // balanced case is when there are more no of closed brackets than the no of open brackets
    // whenever we see a open bracket we will fix the closed bracet problem
    // to fix 2 closing imbalance we need 2 swaps
    static int minimumNumberOfSwaps(String S){
        char[] arr=S.toCharArray();
        int swap=0;
        int imBalance=0;
        int open=0;
        int close=0;
        System.out.println(S);
        for (int i = 0; i < arr.length; i++) {
//            System.out.println("i: "+i+" open: "+open+" close: "+close);
            if (arr[i]=='['){
//                System.out.println("Fix: "+imBalance);
                open++;
                if (imBalance>0){
                    swap+=imBalance;
                    imBalance--;
                }
            }
            else {
                close++;
                imBalance=close-open;
            }
        }
        return swap;
    }
    // find all 4 numbers whose sum is equal to given sum
    //sort the array we will take two number by 2 for loop and 2 number by 2 pointer
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);  // do sorting to reduce TC
        int n=nums.length;
        List<List<Integer>> sol=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            for (int j = i+1; j < n; j++) {
                int searchRem=target-nums[i]-nums[j];
                // now we will do 2 pointer to find 2 ele with sum==searchRem
                int left=j+1;
                int right=n-1;
                while (left<right){
                    int secSum=nums[left]+nums[right];
                    if (secSum<searchRem)
                        left++;
                    else if (secSum>searchRem)
                        right--;
                    else {
                        List<Integer> tem=new ArrayList<>();
                        tem.add(nums[i]);
                        tem.add(nums[j]);
                        tem.add(nums[left]);
                        tem.add(nums[right]);
                        sol.add(tem);
                        //added the 4 elms in ans arraylist
                        // now skip the duplicate
                        while (left<right && nums[left]==tem.get(2))++left;
                        while (left<right && nums[right]==tem.get(3))--right;
                    }
                }
                while (j+1<n && nums[j+1]==nums[j])++j; //skipping duplicates
            }
            while (i+1<n && nums[i+1]==nums[i])++i; // skipping duplicates
        }
        return sol;
    }
    static void sortBySetBitCount(Integer arr[], int n)
    {
        Arrays.sort(arr, (a, b) -> -Integer.compare(Integer.bitCount(a), Integer.bitCount(b)));
        System.out.println(Arrays.toString(arr));
    }
    //find if number exist in the AP
    static int inSequence(int A, int B, int C){
        // A->first term  C-> common diff  B-> no to check
        //if the diff bt the no B and first no is divisible by the common diff then the no exist else no
        int diff=B-A;
        if (diff==0)
            return 1;
        // if common diff is +ve then the no cant be negetive
        if (diff<0){
            if(C >= 0)
                return 0;
            if(diff%C == 0)
                return 1;
            return 0;
        }
        else{
            if(C <= 0)
                return 0;
            if(diff%C == 0)
                return 1;
            return 0;
        }
    }

    // add one 1 to a no represented by linked list
    public static Node addOne(Node head)
    {
        Node cur=head;
        Node prev=null;
        while (cur!=null){
            Node next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        head=prev;
        int carry=(head.data+1)/10;
        head.data=(head.data+1)%10;
        cur=head.next;
        prev=head;
        while (cur!=null && carry>0){
            prev=cur;
            int sum=cur.data+carry;
            cur.data=sum%10;
            carry=sum/10;
            cur=cur.next;
        }
        if (carry>0){
            cur.next=new Node(carry);
        }
        cur=head;
        prev=null;
        while (cur!=null){
            Node next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }
    public static int evaluatePostfix(String exp){
        Stack<Integer> stk=new Stack<>();
        int n=exp.length();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(exp.charAt(i)))
                stk.push(exp.charAt(i)-'0');        //when we do -'0' the char is converted to int
            else {
                int val=stk.pop();
                int val2=stk.pop();
                switch (exp.charAt(i)){
                    case '+':stk.push(val2+val);break;
                    case '-':stk.push(val2-val);break;
                    case '*':stk.push(val2*val);break;
                    case '/':stk.push(val2/val);break;
                }
            }
        }
        return stk.peek();
    }

    int celebrity(int matrix[][], int n){
        Stack<Integer> stk=new Stack<>();
        for (int i = 0; i < n; i++) {
            stk.push(i);
        }
        while (stk.size()>1){
            int a=stk.pop();
            int b=stk.pop();
            //if a dosent know b then a can be a celeb else if a knows b then b can be a cleb
            if (matrix[a][b]==0)
                stk.push(a);
            else
                stk.push(b);
        }
        int cleb=stk.pop(); // candidate for celeb
        boolean flag=false;
        for (int i = 0; i < n; i++) {
            if (i==cleb)continue;
            //if our candidate celeb knows any one or if any person dosent knows our celeb
            // the this cand celeb is not our celeb there is no celeb
            if (matrix[cleb][i] == 1 || matrix[i][cleb] == 0) {
                flag = true;
                break;
            }
        }
        return (!flag)?-1:cleb;
    }
    //lenght of longest valid substring
    // given a paranthesis ()(())( find its length till this is valid
    static int findMaxLen(String s) {
        int n = s.length();
        int left = 0, right = 0, maxlength = 0;

        // Iterating the string from left to right
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            if (left == right) maxlength = Math.max(maxlength, 2 * right);
            else if (right > left)
                left = right = 0;
        }
        System.out.println("maxL : "+maxlength);
        left = right = 0;
        // Iterating the string from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            // Whenever left is equal to right, it signifies
            // that the subsequence is valid and
            if (left == right) maxlength = Math.max(maxlength, 2 * left);

            else if (left > right)
                left = right = 0;
        }
        return maxlength;
    }

    static boolean checkRedundancy(String s) {
        // create a stack of characters
        Stack<Character> st = new Stack<>();
        char[] str = s.toCharArray();
        // Iterate through the given expression
        for (char ch : str) {

            // if current character is close parenthesis ')'
            if (ch == ')') {
                char top = st.peek();
                st.pop();

                // If immediate pop have open parenthesis '('
                // duplicate brackets found
                boolean flag = true;

                while (top != '(') {

                    // Check for operators in expression
                    if (top == '+' || top == '-' || top == '*' || top == '/') {
                        System.out.println("Set false");
                        flag = false;
                    }

                    // Fetch top element of stack
                    top = st.peek();
                    st.pop();
                }

                // If operators not found
                if (flag) {
                    return true;
                }
            } else {
                st.push(ch); // push open parenthesis '(',
            }                // operators and operands to stack
        }
        return false;
    }

    public static int countLess(int[][]mat,int mid){
        int count=0;
        int row=mat.length;
        int col=mat[0].length;
        int j=col-1;
        for (int i = 0; i < row; i++) {
            for (; j >=0 ; j--) {
                if (mat[i][j]<=mid) {
                    count += j + 1;
                    break;
                }
            }
        }
        return count;
    }
    public static int kthSmallest(int[][]mat,int n,int k) {
        int row = mat.length;
        int col = mat[0].length;
        int low = mat[0][0];
        int high = mat[row - 1][col - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLess(mat, mid);
            //if more no of element are less than the corrent mid we need to reduce our mid
            if (count >= k)
                high = mid;
            else
                low = mid + 1;
            //if lesss no of ele are samller than our mid we need to inc our mid
        }
        return low;
    }

    int leafCheck(bstNode root){
        if (root==null)
            return 0;
        int left=leafCheck(root.left);
        if (left==-1)
            return -1;
        int right=leafCheck(root.right);
        if (right==-1)
            return -1;
        if (left==right)
            return left;

        return -1;
    }
    //Finding if 2 n-ary tree is mirror of each other
    static HashMap<Integer,Stack<Integer> > tree1;
    static HashMap<Integer,Queue<Integer> > tree2;
    static int checkMirrorTree(int n, int e, int[] A, int[] B) {
        tree1=new HashMap<>();
        tree2=new HashMap<>();
        for(int i=0; i<2*e; i+=2)
        {
            int x = A[i];
            int y = A[i+1];
            if (tree1.containsKey(x)){
                tree1.get(x).push(y);
            }
            else {
                Stack<Integer> st = new Stack<>();
                st.push(y);
                tree1.put(x,st);
            }
        }

        for(int i=0; i<2*e; i+=2)
        {
            int x = B[i];
            int y = B[i+1];
            if (tree2.containsKey(x)){
                tree2.get(x).add(y);
            }
            else {
                Queue<Integer> st =new LinkedList<>();
                st.add(y);
                tree2.put(x,st);
            }
        }
        return 1;
    }
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
            //we will check from last day to 1st day not 0day for easy implementation
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
    public static Node mergeLinkedList(Node first,Node second){
        Node cur=new Node(1);
        Node res=cur;
        while (first!=null && second!=null){
            if (first.data<second.data){
                cur.bottom=first;
                first=first.next;
            }
            else {
                cur.bottom=second;
                second=second.next;
            }
            cur=cur.bottom;
        }
        if (first!=null)
            cur.bottom=first;
        if (second!=null)
            cur.bottom=second;
        return res.bottom;
    }
    Node flatten(Node root)
    {
        Node cur=root,merged=root;
        while (cur!=null)
        {
            merged=mergeLinkedList(merged,cur);
            cur=cur.next;
        }
        return root;
    }
}
class Job{
    int id,deadline,profit;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}

/*
Node merge(Node a, Node b)
	{
		if(a==null) return b;
		if(b==null) return a;

		Node result;

		if(a.data < b.data)
		{
			result = a;
			result.bottom = merge(a.bottom, b);
		}
		else
		{
			result = b;
			result.bottom = merge(a, b.bottom);
		}
		return result;
	}
	Node flatten(Node root)
	{
		if(root == null || root.next == null)
		return root;

		root.next  = flatten(root.next);
		root = merge(root,root.next);
		return root;
		//return merge(root,flatten(root.next));
	}
 */