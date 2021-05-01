public class solution {

    public static void main(String[] args) {

        byte b=6;
        b+=8;
        System.out.println(b);



        short xx=10;
        float f=(1/4)*10;
        int ii=Math.round(f);
        System.out.println(ii);
        String s1="buddi";
        String s2=new String("buddi");
        System.out.println(s1==s2);
        System.out.println(s1==s2.intern());

        int i=258;
        double d=325.59;
        b=(byte)i;
        System.out.println(b);
        i=(int)d;
        System.out.println(i);
        b=(byte)d;
        System.out.println(b);


        System.out.println("abc".compareTo("def"));
        System.out.println("This is my solution file");
        int x=2;
        int y=0;
        for (; y < 10; ++y) {
            if (y%x==0)
                continue;
            else if (y==0)
                break;
            else
                System.out.println(y+" ");
        }
    }
}
