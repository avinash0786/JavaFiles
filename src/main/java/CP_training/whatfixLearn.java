package CP_training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class whatfixLearn {
    public static void main(String[] args) {
//        System.out.println(missingNumSorted(new int[]{1,2,3,4,6,7,8,9,10,11,12,13}));
//        System.out.println(missingNumberUnsorted(new int[]{3,4,1,6,0,5}));
////        allPairSum(new int[]{2,3,4,5,10,16,-9,12},7);
//        allPairSumSort(new int[]{2,3,4,5,10,16,-9,12},7);
//        removeDuplicates01(new int[]{1,2,3,4,4,5,6,6,7});
//        stringDuplicate("AAbccdwkkklmppp");
//        System.out.println("Palindrome: "+isPalindrome("abbccbba"));
//        permutMain("","abc");
//        System.out.println(Integer.toBinaryString(8));
//        System.out.println(Integer.toBinaryString(8<<2));
//        System.out.println(Integer.toBinaryString(8>>2));
//
//        System.out.println(Integer.toBinaryString(41));
//        System.out.println(Integer.toBinaryString(40));
//        System.out.println(Integer.toBinaryString(41&40));
//        System.out.println(numberPalindrome(22022));

    }
    public static void divisors(int n){
        ArrayList<Integer> op=new ArrayList<>();
        for (int i = 1; i*i <=n; i++) {
            if (n%i==0){
                op.add(i);
                if (i!=n/i)
                    op.add(n/i);
            }
        }
        System.out.println(op);
    }
    public static int counter=0;

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
    public static void primeFactor(int n){
        ArrayList<Integer> op=new ArrayList<>();
        for (int i = 2; i*i <= n; i++) {
            while (n%i==0){
                op.add(i);
                n=n/i;
            }
        }
        if (n>1)
            op.add(n);
        System.out.println(op);
    }
    public static boolean isPrime(int n){   //trial divison
        for (int i = 2; i*i <=n; i++) {
            if (n%i==0)
                return false;
        }
        return true;
    }
    public static int gcd(int a,int b){
        return (b==0)?a:gcd(b,a%b);
    }
    //Seive of Eratosthenes
    public static void seivePrime(int n){
        ArrayList<Integer> primes=new ArrayList<>();    //Initially all prime
        boolean []arr= new boolean[n+1];  // true==composite, false=prime no.
        arr[0]=true;
        arr[1]=true;
        for (int i =2; i*i <=n; i++) {
            if (!arr[i]){   //if arr[i] is prime
                for (int j = i*i; j <=n; j+=i) {
                    arr[j]=true;
                }
            }
        }
        for (int i = 2; i <=n ; i++) {
            if (!arr[i])
                primes.add(i);
        }
        System.out.println(primes);
    }
    public static boolean numberPalindrome(int n){
        int l=String.valueOf(n).length()-1;
        int rev=0;
        int orig=n;
        while (l>=0){
            int end=n%10;
            int mul= (int) Math.pow(10,l);
            rev=rev+mul*end;
            n=n/10;
            l--;
        }
        return orig==rev;
    }
    static boolean isPowerOfTwo (int x)
    {
      /* First x in the below expression is
        for the case when x is 0 */
        return x!=0 && ((x&(x-1)) == 0);
    }

    public static void permutMain(String perm, String word){
        if (word.isEmpty()){
            System.out.print(perm+" ");
            return;
        }
        for (int i = 0; i < word.length(); i++)
            permutMain(perm+word.charAt(i),word.substring(0,i)+word.substring(i+1));
    }
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while(head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }
        return true;
    }
    //  find the missing number in a given integer array of 1 to 100?
    public static int missingNumSorted(int[] arr){
        int n=arr.length;
        int low=0;
        int high=n-1;
        int mid=0;
//        System.out.println(Arrays.toString(arr));
        while (low<=high) {
            mid=(low+high)/2;
            if (arr[mid]!=mid+1 && arr[mid-1]==mid)
                return mid+1;
            else if (arr[mid]==mid+1)
                low=mid+1;
            else
                high=mid-1;
//            System.out.println("low: "+low+" mid: "+mid+" high: "+high);
        }
        return -1;
    }
    public static int missingNumberUnsorted(int[] arr){
        int n=arr.length;
        for (int i = 0; i < n; i++) {
            int corPos=arr[i]-1;
            while (arr[i]>=1 && arr[i]<=n && arr[i]!=arr[corPos]){
                int temp=arr[i];
                arr[i]=arr[corPos];
                arr[corPos]=temp;
                corPos=arr[i]-1;
            }
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < n; i++) {
            if (i+1!=arr[i])
                return i+1;
        }
        return -1;
    }
    public static void allPairSum(int[] arr, int sum){
        HashMap<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < arr.length; i++)
        {
            int rem = sum - arr[i];
            if (mp.containsKey(rem))
            {
                int count = mp.get(rem);
                for(int j = 0; j < count; j++)
                    System.out.print("(" + rem + ", " + arr[i] + ")" + "\n");
            }
            if (mp.containsKey(arr[i]))
                mp.put(arr[i], mp.get(arr[i]) + 1);
            else
                mp.put(arr[i], 1);
        }
    }
    public static void allPairSumSort(int[] arr, int sum){
        int low = 0;
        int high = arr.length - 1;
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        while (low < high) {
            if (arr[low] + arr[high] == sum)
                System.out.println("The pair is : (" + arr[low] + ", " + arr[high] + ")");
            if (arr[low] + arr[high] > sum)
                high--;
            else
                low++;
        }
    }
    public static int removeDuplicates01(int[] nums) {
        int dups=1;
        System.out.println(Arrays.toString(nums));
        int cur=-1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]!=nums[dups-1])
                nums[dups++]=nums[i];
            else {
                if (cur!=nums[i]){
                    System.out.println("Duplicate: "+nums[i]);
                    cur=nums[i];
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return dups;
    }
    public static void stringDuplicate(String str){
        char[] arr=str.toCharArray();
        int[] hash=new int[58];
        for (int i = 0; i < arr.length; i++) {
            hash[arr[i]-'A']++;
        }
        System.out.println(str);
        for (int i = 0; i <hash.length; i++) {
            if (hash[i]>1)
                System.out.print((char) ('A'+i)+" ");
        }
    }
}
