package CP_training;

public class practice08March {
    public static void main(String[] args) {
       replaceSpace("Mr Jhon Smith    ".toCharArray(),13);
        System.out.println(compress("aabbbccccddfgggh"));
    }
    public static String compress(String str){
        int finalLenght=countCompression(str);
        int n=str.length();
        if (finalLenght>=n)
            return str;

        StringBuilder compressed=new StringBuilder(finalLenght);
        int countCons=0;
        for (int i = 0; i < n; i++) {
            countCons++;
            if (i+1>=n || str.charAt(i)!=str.charAt(i+1)){
                compressed.append(str.charAt(i));
                compressed.append(countCons);
                countCons=0;
            }
        }
        return compressed.toString();
    }
    public static int countCompression(String str){
        int compressedlength=0;
        int countCons=0;
        for (int i = 0; i < str.length(); i++) {
            countCons++;
            if (i+1>=str.length() || str.charAt(i)!=str.charAt(i+1)){
                compressedlength+=1+String.valueOf(countCons).length();
                countCons=0;
            }
        }
        return compressedlength;
    }
    public static boolean oneEditAway(String s1, String s2){
        int n1=s1.length();
        int n2=s2.length();

        if (Math.abs(n1-n2)>1)
            return false;
        char[] first= (n1<n2)?s1.toCharArray():s2.toCharArray();
        char[] second=(n1<n2)?s2.toCharArray():s1.toCharArray();

        int x=0;
        int y=0;
        boolean foundDif=false;
        while (y<n2 && x<n1){
            if (first[x]!=second[y]){
                if (foundDif)
                    return true;
                foundDif=true;
                if (n1==n2)     //on replace move shorter pointer
                    x++;
            }
            else
                x++;    // if matching move shorter pointer
            y++;        //always move shorter pointer
        }
        return false;
    }
    public static void replaceSpace(char[] str,int trueLenght){
        int spcCount=0, index=0,i=0;
        for (int j = 0; j < trueLenght; j++) {
            if (str[j]==' ')
                spcCount++;
        }
        index=trueLenght+spcCount*2;    //one space will be replaced by %20 i.e 3 char
        // so index=truelength having onew space+ space*2 therefore 3 times space
        for (int j = trueLenght-1; j >=0; j--) {
            if (str[j]==' '){
                str[index-1]='0';
                str[index-2]='2';
                str[index-3]='%';
                index=index-3;
            }
            else {
                str[index-1]=str[j];
                index--;
            }
        }
        System.out.println(new String(str));
    }
    public static void reorderList(ListNode head) {
        ListNode slow=head;
        ListNode fast=head.next;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode left=head;
        ListNode right=slow.next;
        slow.next=null;//break the link

        //now reverse the right part
        ListNode temp2=right;
        ListNode prev=null;
        while (temp2!=null){
            ListNode next=temp2.next;
            temp2.next=prev;
            prev=temp2;
            temp2=next;
        }
        right=prev;

        //Merging step
        while (left!=null && right!=null){
            ListNode nxt1=left.next;
            ListNode nxt2=right.next;

            left.next=right;
            right.next=nxt1;

            left=nxt1;
            right=nxt2;
        }
        traverse(left);
        System.out.println("Done rev");
    }
    static void traverse(ListNode HEAD){
        ListNode runner=HEAD;
        while (runner!=null){
            System.out.print(runner.val+"-> ");
            runner=runner.next;
        }
        System.out.println();
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p != null && q != null)
            return (p.val == q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right));
        return false;
    }
    public int maxDepth(TreeNode root) {
        if (root==null)
            return 0;
        else
            return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

    }
    public int diameterOfBinaryTree(TreeNode root) {
        diameterEfic(root);
        return res-1;
    }
    int res=0;
    public int diameterEfic(TreeNode root){
        if (root==null)
            return 0;
        int lh=diameterEfic(root.left);
        int rh=diameterEfic(root.right);
        res=Math.max(res,1+lh+rh);
        return 1+Math.max(lh,rh);
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null)
            return false;
        else if (root.left==null && root.right==null && targetSum-root.val==0)
            return true;
        else {
            return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
        }
    }
    Integer result=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathCalc(root);
        return result;
    }
    public int maxPathCalc(TreeNode root){  //***IMPORTANT****
        if (root==null)
            return 0;
        int left=maxPathCalc(root.left);
        int right=maxPathCalc(root.right);

        int maxStraight=Math.max(Math.max(left,right)+root.val,root.val);
        int maxCaseMid=Math.max(maxStraight,left+right+root.val);
        result=Math.max(maxCaseMid,result);
        return maxStraight;
    }
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }
    public int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfsBSTLL(head,root);
    }
    public  boolean dfsBSTLL(ListNode head, TreeNode root){
        if (root==null)
            return false;
        if (matchBSTLL(head,root))
            return true;
        return dfsBSTLL(head,root.left) || dfsBSTLL(head, root.right);
    }
    public  boolean matchBSTLL(ListNode head,TreeNode root){
        if (head==null)
            return true;
        if (root==null || root.val!= head.val)
            return false;
        return matchBSTLL(head.next, root.left) || matchBSTLL(head.next,root.right);
    }
    public boolean isCompleteTree(TreeNode root) {
        if (root.left==null && root.right==null)
            return true;
        if (root.left==null && root.right!=null)
            return false;
        return isCompleteTree(root.left) || isCompleteTree(root.right);
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur=head, next=null, prev=null;
        int count=0;
        while (cur!=null && count<k){
            next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
            count++;
        }
        if (next!=null)
            head.next=reverseKGroup(next,k);
        return prev;
    }
    static final int no_of_chars = 256;

    // Function to find smallest
    // window containing
    // all characters of 'pat'
    static String findSubString(String str, String pat)
    {
        int len1 = str.length();
        int len2 = pat.length();

        // Check if string's length is
        // less than pattern's
        // length. If yes then no such
        // window can exist
        if (len1 < len2) {
            System.out.println("No such window exists");
            return "";
        }

        int[] hash_pat = new int[no_of_chars];
        int[] hash_str = new int[no_of_chars];

        // Store occurrence ofs
        // characters of pattern
        for (int i = 0; i < len2; i++)
            hash_pat[pat.charAt(i)]++;

        int start = 0, start_index = -1,
                min_len = Integer.MAX_VALUE;

        // Start traversing the string
        // Count of characters
        int count = 0;
        for (int j = 0; j < len1; j++) {

            // Count occurrence of characters
            // of string
            hash_str[str.charAt(j)]++;

            // If string's char matches
            // with pattern's char
            // then increment count
            if (hash_str[str.charAt(j)]
                    <= hash_pat[str.charAt(j)])
                count++;

            // If all the characters are matched
            if (count == len2) {

                // Try to minimize the window
                while (hash_str[str.charAt(start)]
                        > hash_pat[str.charAt(start)]
                        || hash_pat[str.charAt(start)]
                        == 0) {

                    if (hash_str[str.charAt(start)]
                            > hash_pat[str.charAt(start)])
                        hash_str[str.charAt(start)]--;
                    start++;
                }

                // update window size
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }

        // If no window found
        if (start_index == -1) {
            System.out.println("No such window exists");
            return "";
        }

        // Return substring starting
        // from start_index
        // and length min_len
        return str.substring(start_index,
                start_index + min_len);
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }