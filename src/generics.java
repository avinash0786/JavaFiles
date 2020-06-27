public class generics<typ1, typ2>   //generic datatypes defined
{                                   // implementing compile time type safety
    typ1 name;                    // generic data type used
    typ2 id;
    generics(typ1 n,typ2 i)
    {
        this.name=n;
        this.id=i;
    }
    public void getdata()
    {
        System.out.println("Name: "+name);
        System.out.println("ID: "+id);
    }

    public static void main(String ...ff)
    {
        generics<String,Integer> g1=new generics<>("Avinash",1180563);  //<> is used in constructor to
        //specify that we will use parameter secified before  in her generics<String,Integer> g1
        g1.getdata();
    }
}
