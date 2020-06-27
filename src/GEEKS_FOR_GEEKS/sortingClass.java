package GEEKS_FOR_GEEKS;

import java.util.ArrayList;

public class sortingClass
{
    static void insert(int arr[],int n)
    {
        for (int i=1;i<n;i++)
        {   //System.out.println();
            int key=arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>key)
            {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
        for(int k:arr)
            System.out.print(k+" ");
    }
    static void merge(int arr[], int l, int m, int r)
    {

    }

    public static void sortedMerge(int []a,int []b)
    {
        int i=0,j=0;
        ArrayList<Integer> op=new ArrayList<>();
        while (i<a.length && j<b.length)
        {
            if(a[i]<b[j])
            {
                op.add(a[i]);
                i++;
            }
            else {
                op.add(b[j]);
                j++;
            }
        }
        while (i<a.length)
            op.add(a[i++]);
        while (j<b.length)
            op.add(b[j++]);
        System.out.println(op);
    }
    public static void main(String ...ee)
    {
        //insert(new int[]{7 ,72, 90, 21, 60},5);
        sortedMerge(new int[]{1,3,5,8,9,12,56,76,98},new int[]{2,4,5,6,7,10,11,99,101,111,222,333});
    }
}
