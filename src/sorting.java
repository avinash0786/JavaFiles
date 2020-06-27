public class sorting
{

    //::::::function:::::::::::::
    static void selectionsort(int arr[])//O(n^2) complexity
    {
        int n=arr.length;
        for (int i=0;i<n;i++)
        {
            int index=i;
            for(int j=i+1;j<n;j++)
            {
                if(arr[j]<arr[index])
                {
                    index=j;
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }
    ///////////////////////////////////////////////////
    static void bubbleSort(int arr[])//O(n^2) complexity
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int t=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=t;
                }
            }
        }
    }
    public static int[] merging(int[]a,int[]b)
    {
        int m=a.length;
        int n=b.length;
        int []result=new int[m+n];
        int i,j,k;
        i=j=k=0;

        while(i<m && j<n)
        {
            if(a[i]<b[i])
                result[k++]=a[i++];
            //post increment used, so that first use current value and then increase with 1
            else
                result[k++]=b[j++];
        }
        for(;i<m;i++)
            result[k++]=a[i];
        for(;j<n;j++)
            result[k++]=b[i];
        System.out.println();
        for(int p:result)
            System.out.print(p+" ");
        return result;

    }
    /////////////////////////////////////////
    static void mergeSort(int arr[])// O(nlogn) complexity
    {
        int a=0;
        int b=arr.length;
        int k=(a+b)/2;


    }



    //////////////////////////////////////////
    public static void main(String args[])
    {
        int arr[]={3,6,2,9,1,12,5,7};
        System.out.println("Array before sorting:");
        for(int el:arr)
        {
            System.out.print(el+" ");
        }
        //::::::::::SORTING FUNCTION::::::::::::://
        System.out.println();
        selectionsort(arr);
        System.out.println("Selection sorting :");
        for(int el:arr)
            System.out.print(el+" ");
        //------------------------------------------//
        System.out.println();
        arr= new int[]{3, 6, 2, 9, 1, 12, 5, 7};
        System.out.println("Bubble sorting :");
        bubbleSort(arr);
        for(int el:arr)
            System.out.print(el+" ");
        //------------------------------------------//
        System.out.println();
        arr= new int[]{3, 6, 2, 9, 1, 12, 5, 7};
        System.out.println("Merge sorting :");
        bubbleSort(arr);
        for(int el:arr)
            System.out.print(el+" ");
        merging(new int[]{56},new int[]{5});




    }


}
