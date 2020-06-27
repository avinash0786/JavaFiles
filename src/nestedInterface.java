interface Aa
{
    void display();

    interface Bb
    {
        void myMethod();
    }
}
public class nestedInterface implements Aa.Bb
{
    public void myMethod()
    {
        System.out.println("Nested interface method");
    }
    public static void main(String args[])
    {
        Aa.Bb obj=new nestedInterface();
        obj.myMethod();
    }
}
