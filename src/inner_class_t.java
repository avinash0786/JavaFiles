class OuterClass
{
    int x=10;
    class InnerClass
    {
        int y=12;
        public int innerRerurnX() //inner class can access attributes and methods of outer class
        {
            return x;
        }
    }
    private class inner22  //cannot be accesed
    {
        int a=23;
    }
    static class inner333
    {
        int ss=90;
    }
}

public class inner_class_t
{
    public static void main(String ...a)
    {
        OuterClass out=new OuterClass();
        OuterClass.InnerClass in=out.new InnerClass();
        System.out.println("Inner class val: "+in.y+" Outer class val: "+out.x);
        System.out.println("Inner function , Value of X outer variable: "+in.innerRerurnX());

        OuterClass.inner333 ob333=new OuterClass.inner333();
        System.out.println("Inner Static class val: "+ob333.ss);
    }
}
