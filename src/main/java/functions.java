public class functions
{
    public static void main(String []args)
    {
        myfirstfn("apple");
        myfirstfn("Avinash");
        add(4,5);
        add(123,432);
        int sum=addfn(23,43);
        System.out.println("Returned value sum: "+sum);
    }
        //defining method, function
    public static void myfirstfn(String str)
    {
        System.out.println("Hello your name is: "+str);
    }
    public static void add(int a, int b)
    {
        System.out.println("The addition is :"+(a+b));
    }
    //return value: change void to datatype
    public static int addfn(int x, int y)
    {
        return (x+y);
    }
}
