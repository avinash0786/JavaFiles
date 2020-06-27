class Temp
{
    private static Temp t;
    private Temp(){}
    public static Temp getTemp()
    {
        if(t==null){t=new Temp();}
        return t;
    }
}
class A
{
    public static void main(String ...ff)
    {
        Temp t=Temp.getTemp();
        Temp t1=Temp.getTemp();
        System.out.println(t==t1);
    }
}