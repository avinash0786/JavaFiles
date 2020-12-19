public class staticclass
{
    int rollno;
    String name;
    static String college="its";
    static void change()
    { college="BBDIT";}
    staticclass(int r,String n)
    {
        rollno=r;
        name=n;
    }
    void display(){System.out.println(rollno+" name "+name+" college: " +college);}
    public static void main(String ...ff)
    {
        staticclass.change();
        staticclass s1=new staticclass(23,"lpu");
        staticclass s2=new staticclass(56,"iit");
        staticclass s3=new staticclass(12,"iti");
        s1.display();
        s2.display();
        s3.display();
    }
}
