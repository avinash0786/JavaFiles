public class bubble_sort
{
    public static void main(String args[])
    {
        int arr[]={3,6,2,9,1,12,5,7};
        //System.out.println(arr[7]);
        //System.out.println(arr.length);
        System.out.println("Array before sorting:");
        for(int el:arr)
        {
            System.out.print(el+" ");
        }
//::::::::::SORTING FUNCTION:::::::::::::
        bubblesort(arr);

        System.out.println("Array after sorting:");
        for(int el:arr)
        {
            System.out.print(el+" ");
        }
    }
    public static void bubblesort(int arr[])
    {
        int temp=0;
        int n = arr.length;
        for(int i=0;i<n;i++)
        {
            for(int j=1;j<(n-i); j++)
            {//Swap elements
                if(arr[j-1]>arr[j])
                {
                    temp=arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
}
