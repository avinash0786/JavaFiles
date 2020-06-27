public class t1
{
    //int i=10;
    static String name="hello";
    static int a;
    public static void main(String a[])
    {
        int aa=10;
        //t1 ob=new t1();
        System.out.println(name);
        System.out.println(t1.a);
        System.out.println(~aa);

        String s ="KAMAL";
        char b[] = new char [10];
        b[0] ='N';
        b[1] = 'E';
        b[2] ='E';
        b[3] ='L';
        s.getChars(0, 4, b, 4);
        System.out.println(b);
        System.out.println(s.indexOf("MA"));
        System.out.println(s.endsWith("AL"));
        System.out.println(s.substring(2,4));

    }
}
