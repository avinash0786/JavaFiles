public class inst_var
{
    int a=4;
    public static void main(String [] args)
    {
        inst_var obj=new inst_var();
        inst_var obj1=new inst_var();
        obj1.a=40;
        //System.out.println(obj.a);

        obj.a=20;
        //System.out.println(obj.a);

        //inst_var obj1=new inst_var();
        obj1.a=80;
        System.out.println(obj.a);
        System.out.println(obj1.a);
    }
}
