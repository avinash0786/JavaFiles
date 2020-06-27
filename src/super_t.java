class Aaa
{
    public Aaa()
    {
        System.out.println("The constructor of class Aaa");
    }
    public Aaa(int a)
    {
        System.out.println("Constructor Aaa Value: "+a);
    }
    public void myFunction(int a)
    {
        System.out.println("THis is the function in class Aaa value: "+a);
    }
}
class Bbb extends Aaa
{
    public Bbb()
    {
        System.out.println("Constructor of class Bbb");
    }
}
class Ccc extends Aaa
{
    public Ccc()
    {
        System.out.println("Constructor Ccc");
    }
    public Ccc(int a)
    {   super(a);   //calling superclass constructor with given parameter
        System.out.println("Constructor Ccc  value: "+a)  ;
        super.myFunction(231);  // calling super class method using super.method();
    }
}

public class super_t
{
    public static void main(String ...dd)
    {
        new Ccc(4);
    }
}
/*
*****  In constructor chanining the constructor of the most top class is called first
 and it kind of unfolds.***
* --the process continues until the last constructor along the inheritance hierarchy is called.
*
*   super();  or super(arguments);
* invokes the superclass constructor that matches the arguments.
* --the keyword super to call the superclass constructor must be the first statement
* in the constructor.
* */
