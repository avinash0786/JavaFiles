class Studentclass
{
    String name;
    int Id;
    Studentclass(){}
    Studentclass(String s,int i)
    {
        this.name=s;
        this.Id=i;
    }
    void display(){System.out.println("Name: "+name+" ID:"+Id);}
}

class father extends Studentclass
{
    String foccup;
    String fname;
    father(){}
    father(String s,int i,String o,String f)
    {
        super(s,i);
        this.foccup=f;
        this.fname=o;
    }
    void display(){
        //super(display())  calling above display fn
        System.out.println("Father Name: "+fname+" Occupation:"+foccup);
        System.out.println("Student Name: "+name+" Id:"+Id);}
}

class mother extends father
{
    String moccup;
    String mname;
    mother(String s,int i,String o,String f,String a,String b)
    {
        super(s,i,o,f);
        this.moccup=b;
        this.mname=a;
    }
    void display()
    {
        //super(display())  calling above display fn
        System.out.println("Mother Name: "+mname+" Occupation:"+moccup);
        System.out.println("Father Name: "+fname+" Occupation:"+foccup);
        System.out.println("Student Name: "+name+" Id:"+Id);
    }
}
class main
{
    public static void main(String ...ff)
    {
        mother m1=new mother("Tarun",5642,"abc","Govt.","xyz","air");
        m1.display();
    }
}
