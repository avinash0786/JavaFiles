public class annonymousarray
{
    public static void main(String ...a)
    {
        sum(new int []{1,2,3,4});
    }


    public static void sum(int []a)
    {
        int total=0;
        for(int i:a)
            total+=i;
        System.out.println("Sum is : "+total);
    }
}
