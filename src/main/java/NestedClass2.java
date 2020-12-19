public class NestedClass2
{
    private int marks=130;
    class Innerclass
    {
        void print()
        {
            System.out.println("Marks: "+marks);
        }
    }
    public static void main(String ...ff)
    {
        NestedClass2 obj=new NestedClass2();
        NestedClass2.Innerclass in =obj.new Innerclass();
        in.print();

    }
}
