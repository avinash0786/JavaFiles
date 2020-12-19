//static block is executed before main block
//static variable can only be initialized in a static block eg: main is also static
public class staticblock
{
    static int num;
    static String line;
    static
    {
        System.out.println("Static block: 1");
        num=78;
        line="apple";
    }

    static
    {
        System.out.println("Static block: 2");
        num=18;
        line="mango";
    }
    public static void main(String ...d)
    {
        System.out.println("num: "+num);
        System.out.println("line: "+line);
    }
}
