public class Outer_nestedclass
{
    private int marks=130;
    class A
    {
        class B
        {
            class C
            {
                class D
                {
                    void print()
                    {
                        System.out.println("Marks: "+marks);
                    }
                }
            }
        }
    }


    public static void main(String ...ff)
    {
        Outer_nestedclass obj=new Outer_nestedclass();
        Outer_nestedclass.A ob1=obj.new A();
        A.B ob2 =ob1.new B();
        A.B.C ob3=ob2.new C();
        A.B.C.D ob4=ob3.new D();

        ob4.print();

    }
}
