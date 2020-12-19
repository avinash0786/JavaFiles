package DS_A;

public class BinarySearch
{
    public static void binarySearch(int [] arr,int key)
    {
        int min=0,mid=0,max=arr.length;
        long a=System.currentTimeMillis();
        //System.out.println(System.currentTimeMillis());
        while(min<max)
        {
            //System.out.println("time: "+System.currentTimeMillis());
            mid=(min+max)/2;
            if(arr[mid]==key)
            {
                System.out.println(key+" found at: "+mid);
                System.out.println("Running time of Binary search: "+(System.currentTimeMillis()-a)+" millis");
                return;
            }
            else if(key<arr[mid])
                max=mid-1;
            else
                min=mid+1;
        }
    }
    public static void linearSearch(int [] arr,int key)
    {
        long a=System.currentTimeMillis();
        for(int i=0;i<arr.length;i++)
        {
            //System.out.println("time: "+System.currentTimeMillis());
            if(arr[i]==key)
            {
                System.out.println(key+" found at: "+i);
                System.out.println("Running time of Linear search: "+(System.currentTimeMillis()-a)+" millis");
                return;
            }
        }
    }
    public static void main(String...ss)
    {
        int list[]={10,23,45,64,68,71,75,85,94,99,100,101,120,123,125,148,197,199,200};
        linearSearch(list,199);
        System.out.println();
        binarySearch(list,200);
        System.out.println();
        linearSearch(list,200);
        System.out.println();
        binarySearch(list,120);
        System.out.println();
        linearSearch(list,120);
    }
}
