class Sd
{
    public void show(){System.out.println("Inside Class");}
    interface Cc
    {
        void display();
    }

}
public class classinterface implements Sd.Cc
{
    public void display(){System.out.println("Interface Function");}
    Sd.Cc obj=new classinterface();

}

