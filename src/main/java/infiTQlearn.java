import java.util.Arrays;

public class infiTQlearn {
    public static void main(String[] args) {
        System.out.println(2<<1);
        int w = (int)888.8;
        byte x = (byte) 100L;
        long y = (byte)100;
        byte z = (byte)100L;
        System.out.println(w+" "+x+" "+y+" "+z);

        byte b = 50;
        b = (byte) (b * 50);
        System.out.println(b);
        char c1=65;
        System.out.println(c1);

        double a = 295.04;
        int  b1 = 300;
        byte c = (byte) a;
        byte d = (byte) b1;
        System.out.println(c + " "  + d);
        int[][] ar=new int[2][2];
        int[] rep=new int[]{23,5,94,3};

        ar[1]=rep;
        for (int[] ints : ar) {
            System.out.println(Arrays.toString(ints));
        }
        int q,r,e;
        q=r=e=2;
        System.out.println(q);

        int ax=2;
        System.out.println(ax+++ ax+++ax++);

    }
}

class singelton{
    private static singelton instance=null;
    public String str;
    private singelton(){
        str="This string is inti by const.";
    }

    public static singelton getInstance() {
        if (instance==null)
            instance=new singelton();
        return instance;
    }
}