class nestedClass
{
    static int dta=30;
    static class inner
    {
        void msg(){System.out.println("Data : "+dta);}
    }

    public static void main(String []aa)
    {
        nestedClass.inner obj=new nestedClass.inner();
        obj.msg();
    }
}
