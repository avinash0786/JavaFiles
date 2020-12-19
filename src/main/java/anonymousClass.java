interface Age
{
    int x=29;
    void getAge();
}
class Ad
{
    public void show()
    {
        System.out.println("THis is the show method od class Ad");
    }
}
public class anonymousClass
{
    public static void main(String ...ff)
    {
        Age o1=new Age() {          //instantiation and definition at same time without terminating
            @Override
            public void getAge() {
                System.out.println("Age:"+x);
            }
        };
        o1.getAge();
        Ad ob=new Ad()
        {                   // overriding by creating a class(anynomous class)
            public void show()
            {
                System.out.println("THis is the overrided method");
            }
        };
        ob.show();
        String a="aple";
        System.out.println(a.contains("a"));
    }
}
