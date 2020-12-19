public class test333
{
    static int a=0;  ///SECOND LARGEST
    static int b=0;
    static int c=0;///MAXX
    public static void main(String ...gg)
    {
        int []arr={2,4,1,5,7,9,4,6};
        for (int i:arr)
        {
            if(i>=c)
            {
                a=b;
                b=c;
                c=i;
            }
            else
                {if(i>=b) {b=i;}
                if(i>=a) {a=i;}}
        }
        System.out.println("3 largest:"+a);
        System.out.println("2 largest:"+b);
        System.out.println("Largest:"+c);
    }
}
