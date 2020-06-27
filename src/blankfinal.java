public class blankfinal
{
    final int rollno;
    blankfinal(int num)
        {rollno=num;}
    void method()
    { System.out.println("Roll no is : "+rollno);}
    //void change(){System.out.println(rollno++);}            ERROR

    public static void main(String ...f)
    {
        blankfinal b1=new blankfinal(2345);
        blankfinal b2=new blankfinal(5632);
        blankfinal b3=new blankfinal(5642);
        b1.method();
        b2.method();
        b3.method();
        //b1.change();
        b1.method();
    }
}
