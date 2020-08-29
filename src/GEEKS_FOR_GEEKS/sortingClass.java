package GEEKS_FOR_GEEKS;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class sortingClass {
    static void insert(int arr[], int n) {
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        for (int k : arr)
            System.out.print(k + " ");
    }

    static void merge(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int i = 0;
        int j = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while (i < n && j < m) {
            if (A[i] < B[j])
                res.add(A[i++]);
            else
                res.add(B[j++]);
        }
        while (i < n) {
            res.add(A[i++]);
        }
        while (j < m) {
            res.add(B[j++]);
        }
        System.out.println(res);
    }

    public static void sortedMerge(int[] a, int[] b) {
        int i = 0, j = 0;
        ArrayList<Integer> op = new ArrayList<>();
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                op.add(a[i]);
                i++;
            } else {
                op.add(b[j]);
                j++;
            }
        }
        while (i < a.length)
            op.add(a[i++]);
        while (j < b.length)
            op.add(b[j++]);
        System.out.println(op);
    }

    public static void merging(int[] arr, int l, int mid, int h) {
        System.out.println("Merging  Low: " + l + " High: " + h);
        int m = mid - l + 1;
        int n = h - mid;
        int[] left = new int[m];
        int[] right = new int[n];

        for (int i = 0; i < m; i++)
            left[i] = arr[l + i];
        for (int j = 0; j < n; j++)
            right[j] = arr[mid + j + 1];

        int i = 0;
        int j = 0;
        int k = l;
        while (i < m && j < n) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else
                arr[k++] = right[j++];
        }
        while (i < m) {
            arr[k++] = left[i++];
        }
        while (j < n) {
            arr[k++] = right[j++];
        }
        if (l == 0 && h == arr.length - 1) {
            for (int a : arr)
                System.out.print(a + " ");
        }
    }

    public static void intersection(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int i = 0;
        int j = 0;

        while (i < n && j < m) {
            if (i > 0 && a[i - 1] == a[i]) {
                i++;
                continue;
            }
            if (a[i] < b[j])
                i++;
            else if (a[i] > b[j])
                j++;
            else {
                System.out.print(a[i] + " ");
                i++;
                j++;
            }
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            System.out.println("Sort call low: " + l + " high: " + r);
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merging(arr, l, m, r);
        }
    }

    public static void union(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (i > 0 && a[i] == a[i - 1]) {
                i++;
                continue;
            }
            if (j > 0 && b[j] == b[j - 1]) {
                j++;
                continue;
            }
            if (a[i] < b[j]) {
                System.out.print(a[i] + " ");
                i++;
            } else if (a[i] > b[j]) {
                System.out.print(b[j] + " ");
                j++;
            } else {
                System.out.print(a[i] + " ");
                i++;
                j++;
            }
        }
        while (i < n) {
            if (i == 0 || a[i] != a[i - 1]) {
                System.out.print(a[i] + " ");
                i++;
            }
        }
        while (j < m) {
            if (j == 0 || b[i] != b[i - 1]) {
                System.out.print(a[j] + " ");
                j++;
            }
        }

    }

    public static int countInv(int[] arr, int l, int r) {
        int res = 0;
        if (l < r) {
            int m = l + (r - l) / 2;
            System.out.println("Calling count inv for low: " + l + " mid: " + m + " high: " + r);
            res += countInv(arr, l, m);
            res += countInv(arr, m + 1, r);
            res += countAndMerge(arr, l, m, r);
        }
        return res;
    }

    public static int countAndMerge(int[] arr, int l, int m, int r) {
        System.out.println("COunt and merge low: " + l + " mid: " + m + " high: " + r);
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[m + 1 + j];
        }
        System.out.print("Left arr: ");
        for (int i : left)
            System.out.print(i + " ");
        System.out.println();
        System.out.print("Right arr: ");
        for (int i : right)
            System.out.print(i + " ");
        System.out.println();

        int res = 0, i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                System.out.println("Raisd for left: " + left[i] + " right: " + right[j] + " Res: " + res);
                arr[k] = right[j];
                j++;
                res = res + (n1 - i);
                System.out.println("Res val: " + res);
            }
            k++;
        }
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
        System.out.print("Arr: ");
        for (int s : arr)
            System.out.print(s + " ");
        System.out.println();
        return res;
    }

    public static void partitionNaive(int[] arr, int l, int h, int p) {
        int temp[] = new int[h - l + 1];
        int index = 0;
        for (int i = 0; i <= h; i++) {
            if (arr[i] <= arr[p]) {
                temp[index] = arr[i];
                index++;
            }
        }
        for (int i = 0; i <= h; i++) {
            if (arr[i] > arr[p]) {
                temp[index] = arr[i];
                index++;
            }
        }
        for (int i = l; i <= h; i++) {
            arr[i] = temp[i];
        }

    }
    public static int partitionLomuto(int[]arr, int l,int h,int p)
    {
        if(!(arr[p]==arr[h]))
        {
            System.out.println("Adj..");
            int t=arr[p];
            arr[p]=arr[h];
            arr[h]=t;
        }
        System.out.println("Pivot: "+arr[h]);
        int i=-1;
        for (int j = 0; j <h ; j++) {
            if(arr[j]<arr[h]){
                i++;
                int t=arr[j];
                arr[j]=arr[i];
                arr[i]=t;
            }
        }
        int t=arr[h];
        arr[h]=arr[i+1];
        arr[i+1]=t;
        for (int i1 : arr) {
            System.out.print(i1+" ");
        }
        return i+1;
    }
    public static int partitinHores(int []arr,int l,int h,int p)
    {
//        System.out.println("Partition called l: "+l+" h:"+h);
        if(!(arr[p]==arr[l]))
        {
            int t=arr[p];
            arr[p]=arr[l];
            arr[l]=t;
        }
        int pivot=arr[l];
        int i=l-1;
        int j=h+1;
//        System.out.println("i: "+i+" j: "+j+" pivot: "+pivot);
        while (true){
//            for (int i1 : arr) {
//                System.out.print(i1+" ");
//            }
//            System.out.println();
            do {
                i++;
            }while (arr[i]<pivot);

            do {
                j--;
            }while (arr[j]>pivot);
            if(i>=j) {
//                System.out.println("Returining j: "+j);
                return j;
            }
            int s=arr[j];
            arr[j]=arr[i];
            arr[i]=s;
        }
    }
    public static void qSortLom(int []arr,int l,int h)
    {
        if(l<h)
        {
            int p=partitionLomuto(arr,l,h,l);
            qSortLom(arr,l,p-1);
            qSortLom(arr,p+1,h);
        }
    }
    public static void qSortLomTail(int []arr,int l,int h)// Tail call elemination in QuickSort
    {
        begin:
        if(l<h)
        {
            int p=partitinHores(arr,l,h,l);
            qSortLom(arr,l,p);
            l=p+1;
        }
    }
    public static int kSmallest(int []arr,int k)
    {
        int l=0;
        int h=arr.length-1;
        while (l<=h)
        {
            int p=partitionLomuto(arr,l,h,h);
            if(p==k-1)
                return p;
            else if(p>k-1)
                h=p-1;
            else
                l=p+1;
        }
        return -1;
    }
    public static int chocDist(int []arr,int m)
    {
        int n=arr.length;
        Arrays.sort(arr);
        int res=arr[m-1]-arr[0];
        if(m<n)
        {
            for (int i = 1; (i+m-1)<n ; i++) {
                res=Math.min(res,(arr[i+m-1]-arr[i]));
                System.out.println("i: "+i+ " d: "+(i+m-1)+" A: "+arr[i+m-1]+" B: "+arr[i]);
            }
        }
        return res;
    }
    public static void segPosNeg(int []arr)
    {
        int i=-1;
        int j=arr.length;
        while (true)
        {
            do{
                i++;
            }while (arr[i]<0);
            do{
                j--;
            }while (arr[j]>=0);
            if(i>=j) {
                for (int i1 : arr) {
                    System.out.print(i1+" ");
                }
                return;
            }
            int t=arr[j];
            arr[j]=arr[i];
            arr[i]=t;
        }
    }
    public static void segEvenOdd(int[]arr)
    {
        int i=-1;
        int j=arr.length;
        while (true){
            do{
                i++;
            }while (arr[i]%2==0);
            do{
                j--;
            }while (arr[j]%2!=0);
            if(i>=j){
                for (int i1 : arr) {
                    System.out.print(i1+" ");
                }
                return;
            }
            int t=arr[j];
            arr[j]=arr[i];
            arr[i]=t;
        }
    }
    public static void segOneZero(int[]arr)
    {
        int i=-1;
        int j=arr.length;
        while (true){
            do {
                i++;
            }while (arr[i]==1);
            do {
                j--;
            }while (arr[j]==0);
            if(i>=j){
                for (int i1 : arr) {
                    System.out.print(i1+" ");
                }
                return;
            }
            int t=arr[j];
            arr[j]=arr[i];
            arr[i]=t;
        }
    }
    public static void sort012(int[]arr)
    {
        int low=0;
        int mid=0;
        int high=arr.length-1;
        while (mid<=high)
        {
//            System.out.println("Low: "+low+" Mid: "+mid+" high: "+high);
            switch (arr[mid])
            {
                case 0:{
                    int t=arr[mid];
                    arr[mid]=arr[low];
                    arr[low]=t;
                    low++;
                    mid++;
                    break;
                }
                case 1:{
                    mid++;
                    break;
                }
                case 2:{
                    int t=arr[high];
                    arr[high]=arr[mid];
                    arr[mid]=t;
                    high--;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void threeWayPartition(int[]arr,int pivot)
    {
        int low=0;
        int mid=0;
        int high=arr.length-1;
        while (mid<=high)
        {
            if(arr[mid]<pivot){
                int t=arr[mid];
                arr[mid]=arr[low];
                arr[low]=t;
                low++;
                mid++;
            }
            else if(arr[mid]==pivot){
                mid++;
            }
            else {
                int t=arr[high];
                arr[high]=arr[mid];
                arr[mid]=t;
                high--;
            }
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void rangePartition(int[]arr,int start,int end)
    {
        int low=0;
        int mid=0;
        int high=arr.length-1;
        while (mid<=high)
        {
            if(arr[mid]<start){
                int t=arr[mid];
                arr[mid]=arr[low];
                arr[low]=t;
                low++;
                mid++;
            }
            else if(arr[mid]>=start && arr[mid]<=end){
                mid++;
            }
            else {
                int t=arr[high];
                arr[high]=arr[mid];
                arr[mid]=t;
                high--;
            }
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static boolean checkCons(int[]arr)
    {
        int low=99999;
        int high=-99999;
        int sum=0;
        for (int i : arr) {
            low=Math.min(i,low);
            high=Math.max(high,i);
            sum+=i;
        }
//        System.out.println("low: "+low+" high: "+high);
//        System.out.println("High: "+(high*(high+1)/2));
//        System.out.println("low: "+(low*(low+1)/2));
        if(sum==((high*(high+1)/2)-((low-1)*(low)/2))){
            return true;
        }
        return false;
    }
    public static void mergeInterval(interval[]arr)
    {
        int res=0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[res].end>=arr[i].start){
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
    public static int maxGuest(int[]ariv,int[]dept)
    {
        Arrays.sort(ariv);
        Arrays.sort(dept);
        int i=1;
        int j=0;
        int res=1;
        int cur=1;
        while (i<ariv.length && j<ariv.length)
        {
            if(ariv[i]<=dept[j]){
                cur++;
                i++;
            }
            else {
                cur--;
                j++;
            }
            res=Math.max(res,cur);
        }
        return res;
    }

    public static void cycleSortDist(int[]arr)
    {
        for (int cs = 0; cs < arr.length - 1; cs++) {
            int item=arr[cs];
            int pos=cs;
            for (int i = cs; i < arr.length; i++) {
                if(arr[i]<item)
                    pos++;
            }
            int t=arr[pos];
            arr[pos]=item;
            item=t;
            while (pos!=cs){
                pos=cs;
                for (int i = cs+1; i < arr.length; i++) {
                    if(arr[i]<item)
                        pos++;
                }
                t=arr[pos];
                arr[pos]=item;
                item=t;
            }
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void countSort(int[]arr,int k)//When range of element is known i.e K
    {
        int []count=new int[k];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        for (int i = 1; i < k; i++) {
            count[i]=count[i-1]+count[i];
        }
        int []output=new int[arr.length];
        for (int i = arr.length-1; i >=0; i--) {
            output[count[arr[i]]-1]=arr[i];
            count[arr[i]]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i]=output[i];
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void radixSort(int []arr)
    {
        int max=arr[0];
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(arr[i],max);
        }
        for (int exp = 1; max/exp>0; exp*=10) {  //exp: 1,10,100,1000...
            countModSort(arr,arr.length,exp);//if max is 3 digit for loops 3 times
            System.out.println();
            for (int i : arr) {
                System.out.print(i+" ");
            }
            System.out.println();
            System.out.println("-------------------------");
        }
    }
    public static void countModSort(int[]arr,int n,int exp)
    {
        int []count=new int[10];
        for (int i = 0; i < n; i++) {
            count[(arr[i]/exp)%10]++;
        }
        System.out.print("Count: ");
        for (int i : count) {
            System.out.print(i+" ");
        }
        for (int i = 1; i < 10; i++) {
            count[i]=count[i]+count[i-1];
        }
        System.out.println();
        System.out.print("Count Up: ");
        for (int i : count) {
            System.out.print(i+" ");
        }
        int []output=new int[n];
        for (int i = n-1; i >=0 ; i--) {
            output[count[(arr[i]/exp)%10]-1]=arr[i];
            count[(arr[i]/exp)%10]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i]=output[i];
        }
    }

    public static void main(String... a) {
//        radixSort(new int[]{319,212,6,8,100,50});

//        countSort(new int[]{1,4,4,1,0,1},5);
//        cycleSortDist(new int[]{2,5,6,4,8,9,3});
//        insert(new int[]{7 ,72, 90, 21, 60},5);
        //sortedMerge(new int[]{1,3,5,8,9,12,56,76,98},new int[]{2,4,5,6,7,10,11,99,101,111,222,333});
//        merge(new int[]{1,3,5,8,9,12,56,76,98},new int[]{2,4,5,6,7,10,11,99,101,111,222,333});
        //mergeSort(new int[]{10,5,20,15,30,45,6},0,6);
//        intersection(new int[]{2,5,8,13,15},new int[]{1,2,5,13,15});
//        union(new int[]{2,5,8,13,15},new int[]{1,2,5,13,15});
//        System.out.println(countInv(new int[]{2,5,8,11,3,6,9,13},0,7));
//        partitionLomuto(new int[]{10,80,30,42,22,65,50},0,6,6);
//        System.out.println(partitinHores(new int[]{7,5,1,3,9,4,8},0,6,0));
//        int []arr=new int[]{8,4,7,9,3,10,5};
//        qSortLom(arr,0,6);
//        System.out.println("Sorted: ");
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }
        System.out.println(chocDist(new int[]{3,4,1,9,56,7,9,12},5));
//        segPosNeg(new int[]{2,-3,5,-9,6,-7,-4,-5});
//        segEvenOdd(new int[]{1,5,6,8,3,4,9,6,7,2,5,13,15});
//        segOneZero(new int[]{1,0,1,0,0,1,1,0,1,1,1,0,0,0,0,1});
//        sort012(new int[]{0,1,2,1,0,1,2});
//        threeWayPartition(new int[]{2,5,6,3,1,4,8,9,5,6,12},5);
//        rangePartition(new int[]{2,5,6,3,12,5,6,8,2,4,26},3,6);
//        System.out.println();
//        System.out.println(checkCons(new int[]{5,6,7,8,9,10,11,12,13,3}));
//        Scanner inp=new Scanner(System.in);
//        interval[] arrInter=new interval[5];
//        int nn=inp.nextInt();
//        for (int i = 0; i < nn; i++) {
//            int aa=inp.nextInt();
//            int b=inp.nextInt();
//            arrInter[i]=new interval(aa,b);
//        }
//        Arrays.sort(arrInter,interval::compareTo);
////        for (interval interval : arrInter) {
////            System.out.println("X: "+interval.start+" Y: "+interval.end);
////        }
//        mergeInterval(arrInter);
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

    @Override
    public int compareTo(@NotNull interval o) {
        return o.start-this.start;
    }
}
