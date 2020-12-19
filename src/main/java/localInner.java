public class localInner
{
    private int data=30;
    void display()
    {
        int value=50;  //Local variable must be final till jdk 1.7
        class Local
        {
            void msg(){System.out.println("Value: "+value);}
        }
        Local n=new Local();
        n.msg();
    }
    public static void main(String ...dd)
    {
        localInner ob=new localInner();
        ob.display();
    }
}
