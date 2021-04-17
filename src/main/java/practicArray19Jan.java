import java.util.Arrays;

public class practicArray19Jan {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3,3}));
//        int [][] arr=new int[][]{{5,2},{5,4},{10,3},{20,1}};
//        System.out.println(averageWaitingTime(arr));
    }
    public int maxOccured(int[] la, int[] ra){
        int arrLen=la.length;
        int maxVal=0;
        for (int i : la) {
            maxVal=Math.max(i,maxVal);
        }
        for (int i : ra) {
            maxVal=Math.max(i,maxVal);
        }
        int arr[]=new int[maxVal];
        for (int i = 0; i < arrLen; i++) {
            arr[la[i]]++;
            arr[ra[i]]--;
        }
        int max=arr[0],res=0;
        for (int i = 1; i < maxVal; i++) {
            arr[i]+=arr[i-1];
            if (max<arr[i]){
                max=arr[i];
                res=i;
            }
        }
        return res;
    }

    public static double averageWaitingTime(int[][] customers) {
        int prevLag=0;
        int avgSum=0;
        avgSum+=((customers[0][0]+customers[0][1])-customers[0][0]);
        prevLag=avgSum+customers[0][0];
        for (int i = 1; i < customers.length; i++) {
            System.out.println("avg: "+avgSum+" prevLag: "+prevLag);
            if (prevLag<customers[i][0]){
                avgSum+=((customers[i][1]+customers[i][0])-customers[i][0]);
                prevLag=customers[i][1]+prevLag;
                continue;
            }
            avgSum+=(customers[i][1]+prevLag-customers[i][0]);
            prevLag=customers[i][1]+prevLag;
        }
        System.out.println("avg: "+avgSum+" prevLag: "+prevLag);
        return (double) avgSum/customers.length;
    }
    /*
    Given a sorted array nums, remove the duplicates in-place such that duplicates
     appeared at most twice and return the new length.
     */
    public static int removeDuplicates(int[] nums) {
        System.out.println(Arrays.toString(nums));
        int dups=1;
        int cnt=1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt<=1 && nums[i]==nums[dups-1]){
                nums[dups++]=nums[i];
                cnt++;
            }
            else if (nums[i]!=nums[dups-1]){
                nums[dups++]=nums[i];
                cnt=1;
            }
        }
        System.out.println(Arrays.toString(nums));
        return dups;
    }
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])]>0)
                nums[Math.abs(nums[i])]=nums[Math.abs(nums[i])]*(-1);
            else
                return Math.abs(nums[i]);
        }
        return 0;
    }
    public static int removeDuplicates01(int[] nums) {
        int dups=1;
        System.out.println(Arrays.toString(nums));

        for (int i = 1; i < nums.length; i++) {
            if (nums[i]!=nums[dups-1])
                nums[dups++]=nums[i];
        }
        System.out.println(Arrays.toString(nums));
        return dups;
    }
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]==nums[i-1])
                return true;
        }
        return false;
    }
    public int xorOperation(int n, int start) {
        int res=0;
        int[]arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=start+2*i;
        }
        for (int i : arr) {
            res=res^i;
        }
        return res;
    }
}
