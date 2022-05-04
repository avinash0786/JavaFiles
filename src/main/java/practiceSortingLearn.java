import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class practiceSortingLearn {
    public static void main(String[] args) {
//        int[]arr=new int[]{23,1,56,15,46,3,12,9,41};
//        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
//        mergeSort(arr,0,8);
//        System.out.println(Arrays.toString(arr));
        int[] a1=new int[]{10,12,16,30,30,35,46};
        int[] a2=new int[]{31,12,15,30,36,46,16};
//        intersection(a1,a2);
//        union(a1,a2);
//        System.out.println(lomutoPartition(a2));
//        System.out.println(hoarePartition(a2));
//        quickSortLomuto(a2,0,6);
//        System.out.println(kSamllest(a2,3));
//        cycleSortDistict(a2);
//        System.out.println(Arrays.toString(a2));
//        System.out.println(kkk(new int[]{10,10,8,3}));

    }
    public static void radixSort(int[] arr){
        int max=arr[0];
        for (int i = 1; i <arr.length; i++) {
            max= Math.max(arr[i], max);
        }
        for (int exp = 1; max/exp>0 ; exp*=10) {

        }
    }
    public static void countSortRad(int [] arr,int n,int exp){
        int[] count=new int[n];
        int[] out=new int[n];
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }
        for (int i = 1; i < n; i++) {
            count[i]+=count[i-1];
        }
        for (int i = n-1; i>=0; i--) {
            out[count[(arr[i]/exp)%10]-1]=arr[i];       //sorting only by the last digit
            count[(arr[i]/exp)%10]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i]=out[i];
        }
    }
    public static void countSortEff(int[] arr){
        int[] count=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        //finding number of element >= to current element
        for (int i = 1; i < arr.length; i++) {  //prefix sum
            count[i]+=count[i-1];
        }
        int[] out=new int[arr.length];
        for (int i = arr.length-1; i>=0 ; i--) {
            out[count[arr[i]]-1]=arr[i];
            count[arr[i]]--;
        }
        for (int i = 0; i < arr.length; i++) {
            out[i]=arr[i];
        }
    }
    public static void countSort(int[] arr,int k){
        int[] count=new int[arr.length];    //counting number of occurance
        for (int i = 0; i <arr.length; i++) {
            count[arr[i]]++;
        }
        int index=0;
        for (int i = 0; i < k; i++) {       //placing that number of element in array
            for (int j = 0; j <count[i] ; j++) {
                arr[index]=i;
                index++;
            }
        }
    }
    public static void cycleSortDistict(int[] arr){ //min no of memory writes
        for (int cs=0;cs<arr.length;cs++){
            int item=arr[cs];
            int pos=cs;
            for (int i =cs+1; i < arr.length; i++) {
                if (arr[i]<item)
                    pos++;
            }
            int temp=arr[pos];
            arr[pos]=item;
            item=temp;
            while (cs!=pos){
                pos=cs;
                for (int i =cs+1; i <arr.length; i++) {
                    if (arr[i]<item)
                        pos++;
                }
                temp=arr[pos];
                arr[pos]=item;
                item=temp;
            }
        }
    }
    public static int kSamllest(int[] arr,int k){
        int l=0,r=arr.length-1;
        while (l<=r){
            int p=lomutoPartition(arr,l,r);
            if (p==k-1)
                return p;
            else if (p>k-1)
                r=p-1;
            else
                l=p+1;
        }
        return -1;
    }
    public static void quickSortLomuto(int[] arr,int low,int high){
        if (low<high){
            int part=lomutoPartition(arr,low,high);
            quickSortLomuto(arr,low,part-1);
            quickSortLomuto(arr,part+1,high);
        }
    }
    public static int hoarePartition(int[] arr, int l, int high){    //pivot is first element
        int pivot=arr[l];
        int i=l-1;
        int j=high+1;
        while (true){   //inti 2 pointers
            do {
                i++;
            }while (arr[i]<pivot);  // move left pointer till encounter greater element
            do {
                j--;
            }while (arr[j]>pivot);  // move right element till encounter smaller element
            if (i>=j)
                return j;
            swap(arr,i,j);  //swap larger element in left with smaller element in right
        }       //pivot is not at correct position, hence lomuto partition is better
    }
    public static int lomutoPartition(int[] arr,int low, int high){   //Pivot is the last element
        int pivot=arr[high];
        int i=low-1;                    // maintain a window variable
        for (int j = low; j < high; j++) {
            if (arr[j]<pivot){      //if found a smaller variable
                i++;                //increment the window and swap
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);         //lastly swap the pivot last element
//        System.out.println(Arrays.toString(arr));
        return i+1;
    }
    public static void union(int[] a1, int[] a2){
        int n1=a1.length;
        int n2=a2.length;
        int i=0,j=0;
        while (i<n1 && j<n2){
            if (i>0 && a1[i]==a1[i-1]){
                i++;
                continue;
            }
            if (j>0 && a1[j]==a1[j-1]){
                j++;
                continue;
            }

            if (a1[i]<a2[j]){
                System.out.println(a1[i]);
                i++;
            }
            else if (a1[i]>a2[j]){
                System.out.println(a2[j]);
                j++;
            }
            else {
                System.out.println(a1[i]);
                i++;
                j++;
            }
        }
        while (i<n1){
            if (i>0 && a1[i]!=a1[i-1]){
                System.out.println(a1[i]);
                i++;
            }
        }
        while (j<n2){
            if (j>0 && a2[j]!=a2[j-1]){
                System.out.println(a2[j]);
                j++;
            }
        }
    }
    public static void intersection(int[] a1,int[]a2){
        int n1=a1.length;
        int n2=a2.length;
        int i=0,j=0;
        while (i<n1 && j<n2){
            if (i>0 && a1[i]==a1[i-1]) {
                i++;
                continue;
            }
            if (a1[i]<a2[j])
                i++;
            else if (a1[i]>a2[j])
                j++;
            else {
                System.out.println(a1[i]);
                i++;j++;
            }
        }
    }
    public static void swap(int[] arr, int j,int k){
        int t=arr[k];
        arr[k]=arr[j];
        arr[j]=t;
    }
    //O(n) best case
    public static void bubbleSort(int[] arr){   //comparing pairs and swapping
        int n=arr.length;
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n-i-1; j++) {
                //if prev is greater than next we swap it
                if (arr[j]>arr[j+1])
                    swap(arr,j,j+1);
                System.out.println(Arrays.toString(arr));
            }
            System.out.println("--------------------------------");
        }
    }
    //O(n) best case -- inplace and stable best for small input
    public static void insertionSort(int[] arr){    //Insert the element at correct place shifting larger elements
        int n=arr.length;
        for (int i = 1; i < n; i++) {
            int key=arr[i];
            int j=i-1;
            // we skip all greater element in left of i and place i to its correct place
            while (j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }
    //not-stable
    //find 1 st min element in first pass and place it first in array
    //find 2 nd min element in second pass and place it second in array
    public static void selectionSort(int[]arr){ //select the minimum element and place it at correct place
        int n=arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex=i;
            for (int j = i+1; j < n; j++) {
                if (arr[j]<arr[minIndex])
                    minIndex=j;
            }
            swap(arr,i,minIndex);
        }
    }
    public static int kkk(ArrayList<Integer> arr){
        Collections.sort(arr);
        int res=0;
        int cur=arr.get(0);
        int count=1;
        int totalSum=cur;
        for (int i = 1; i <arr.size() ; i++) {
            if (arr.get(i)==cur){
                count++;
            }
            else {
                res=Math.max(count*cur,res);
                cur=arr.get(i);
                count=1;
            }
            totalSum+=arr.get(i);
        }
        res=Math.max(count*cur,res);
        return totalSum-res;
    }
    public static void merge(int[] arr, int low, int mid, int high){
        System.out.println("merge fn call for low: "+low+" mid: "+mid+" right: "+high);
        int n1=mid-low+1;
        int n2=high-mid;
        int [] left=new int[n1];
        int[] right=new int[n2];
        for (int i = 0; i < n1; i++) {
            left[i]=arr[low+i];
        }
        for (int i = 0; i < n2; i++) {
            right[i]=arr[mid+1+i];
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        int i=0,j=0,k=low;
        while (i<n1 && j<n2){
            if (left[i]<=right[j])
                arr[k++]=left[i++];
            else
                arr[k++]=right[j++];
        }
        while (i<n1)
            arr[k++]=left[i++];
        while (j<n2)
            arr[k++]=right[j++];
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr,int l, int r){
        System.out.println("MergeSort call l: "+l+" r: "+r);
        if (r>l){       //the lowest point is when we have only 2 elements left
            int mid=l+(r-l)/2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid+1,r);
            merge(arr,l,mid,r);
        }
    }

    public static void mergeInterval(interval[] arr){
        int res=0;
        for (int i =1; i <arr.length; i++) {
            if (arr[res].end>=arr[i].end){
                arr[res].end=Math.max(arr[res].end,arr[i].end);
                arr[res].start=Math.min(arr[res].start,arr[i].start);
            }
            else {
                res++;
                arr[res]=arr[i];
            }
        }
        for (int i=0;i<=res;i++) {
            System.out.println(arr[i].start+" - "+arr[i].end);
        }
    }

}
class interval implements Comparable<interval>
{
    int start;
    int end;
    public interval(int s,int e){
        this.start=s;
        this.end=e;
    }
    public int compareTo(interval o) {
        return o.start-this.start;
    }
}