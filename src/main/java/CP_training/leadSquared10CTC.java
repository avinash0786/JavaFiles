package CP_training;

import java.util.Arrays;

public class leadSquared10CTC {
    public static void main(String[] args) {
        System.out.println("Output: "+revWords("India is Great"));
        merge2sorted(new int[]{1,5,10,15},new int[]{2,6,12,18});
        System.out.println("Spcl rev: "+revSpcChar("Ab,c,de!$"));
        System.out.println("Excel to number: "+excelToNum("CDA"));
        System.out.println("Count sorted occurance: "+countSortOcc(new int[]{1,2,3,4,5},5));
//        fizzBuzzM03(20);
        System.out.println("Closet element: "+closestValue(new int[]{1,2,5,6,9,12,15,19,23,26,29},27));
        System.out.println("bob chocolate: "+bobChocolate(20,3,2));
    }
    public static String revWords(String str){//  Question 02
        char[] arr=str.toCharArray();
        int start=0;
        System.out.println("Input: "+str);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==' ') {
                reverse(arr, start, i - 1);
                start=i+1;
            }
        }
        reverse(arr,start,arr.length-1);
        reverse(arr,0,arr.length-1);
        return new String(arr);
    }
    public static void reverse(char[] arr, int start, int end){
        while (start<=end){
            char temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }
    public static void merge2sorted (int[] a1, int[] b2){//  Question 04
        int i=0, j=0, n1=a1.length, n2=b2.length;
        int[] opt=new int[n2+n1];
        int k=0;
        while (i<n1 && j<n2){
            if (a1[i]<b2[j])
                opt[k++]=a1[i++];
            else
                opt[k++]=b2[j++];
        }
        while (i<n1)
            opt[k++]=a1[i++];
        while (j<n2)
        opt[k++]=b2[j++];
        System.out.println(Arrays.toString(opt));
    }
    public static String revSpcChar(String str){    //  Question 05
        char[] arr=str.toCharArray();
        int start=0,end=str.length()-1;
        System.out.println("Input to reverse: "+str);
        while (start<=end){
            while (arr[start]>=' ' && arr[start]<='/') {
//                System.out.println("Start skip: "+arr[start]);
                start++;
            }
            while (arr[end]>=' ' && arr[end]<='/') {
//                System.out.println("End skip: "+arr[end]);
                end--;
            }
//            System.out.println("Swapping: start: "+start+" end: "+end);
            char temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
        return new String(arr);
    }
    public static int excelToNum(String str){
        char[] arr=str.toCharArray();
        int n=arr.length-1;
        int res=0;
        for (int i = 0; i <= n; i++)
            res+=(int) Math.pow(26,n-i)*(arr[i]-'A'+1);
        return res;
    }
    public static int countSortOcc(int[] arr,int key){
        int low=0;
        int high=arr.length-1;
        int mid=0;
        while (low<=high){  //left occurance
            mid=(low+high)/2;
            if (key<arr[mid])
                high=mid-1;
            else if (key>arr[mid])
                low=mid+1;
            else {
                if (mid==0 || arr[mid]!=arr[mid-1])
                    break;
                else
                    high=mid-1;
            }
        }
        int left=mid;
        while (mid<arr.length && arr[mid]==key)
            mid++;
        return mid-left;
    }
    public static void fizzBuzz(int n){
        for (int i = 1; i < n; i++) {
            if (i%3==0 && i%5==0) System.out.println(i+" "+"fizzBuzz");
            else if (i%5==0) System.out.println(i+" "+"Buzz");
            else if (i%3==0) System.out.println(i+" "+"fizz");
            else System.out.println(i+" "+i);
        }
    }
    public static void fizzBuzzM02(int n){  // at memory cost
        for (int i = 1; i < n; i++) {
            String val="";
            if (i%3==0) val+="fizz";
            if (i%5==0) val+="buzz";
            if (val.equals(""))
                System.out.println(i+" "+i);
            else
                System.out.println(i+" "+val);
        }
    }
    public static void fizzBuzzM03(int n){  //without modulo we save computation
        int c3=0,c5=0;
        for (int i = 1; i < n; i++) {
            c3++;
            c5++;
            String val="";
            if (c3==3) {
                val += "fizz";
                c3=0;
            }
            if (c5==5) {
                val += "buzz";
                c5=0;
            }
            if (val.equals(""))
                System.out.println(i+" "+i);
            else
                System.out.println(i+" "+val);
        }
    }
    public static int closestValue(int[]arr, int key){
        int low=0;
        int high=arr.length-1;
        int mid=0;
        while (low<=high){
            mid=(low+high)/2;
            if (arr[mid]==key){
                return arr[mid];
            }
            if (key<arr[mid])
                high=mid-1;
            else
                low=mid+1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("Mid: "+mid+"  "+arr[mid]);
        if (mid==0)
            return arr[mid+1];
        else if (mid==arr.length-1)
            return arr[mid-1];
        else {
            int d1;
            int d2;
            if (arr[mid]<key){
                d1=Math.abs(arr[mid]-key);
                d2=Math.abs(key-arr[mid+1]);
                System.out.println("D1: "+d1+" d2: "+d2);
                if (d1<d2)
                    return arr[mid];
                else
                    return arr[mid+1];
            }
            else {
                d1=Math.abs(key-arr[mid-1]);
                d2=Math.abs(arr[mid]-key);
                System.out.println("D1: "+d1+" d2: "+d2);
                if (d1<d2)
                    return arr[mid-1];
                else
                    return arr[mid];
            }
        }
    }
    public static int bobChocolate(int n, int c, int m){
        int chocfromPrice=n/c;
        System.out.println("chocolate from rupee: "+chocfromPrice);
        int chocfrmWrapper=chocfromPrice/m;
        System.out.println("Wrapper for one: "+m+" free choc: "+chocfrmWrapper);
        return chocfrmWrapper+chocfromPrice;
    }
}
