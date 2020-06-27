abstract class abs
{
    abstract int add(int a, int b);
}
interface X
{
    abstract void test();
    default void raw(){System.out.println("This is default raw meathod");}
    static void show()
    { System.out.println("This is static meathod show");}
}

interface Y
{
    abstract void test();
    default void demo(){System.out.println("This is default demo meathod");}
}
class Z extends abs implements X,Y
{
    public void test(){System.out.println("This is test meathod");}
    int add(int a,int b){return (a+b);}
}


class program_abstrat
{
    public static void main(String ...ff)
    {
        Z a1=new Z();
        a1.raw();
        a1.test();
        X.show();
        System.out.println("Sum:  45+34 = "+a1.add(45,34));
    }
}
