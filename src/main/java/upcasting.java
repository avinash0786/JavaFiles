class Super
{
    void Sample(){System.out.println("method of super class");}
}

class Sub extends Super
{
    void Sample(){System.out.println("Method of sub class");}

    public static void main(String ...ff)
    {
        Super obj=new Sub();
        Sub sub=(Sub) obj;
        //        Sub sub=new Sub();  Same op
        sub.Sample();
        obj.Sample();
    }
}
