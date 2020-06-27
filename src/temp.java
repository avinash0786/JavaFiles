import java.util.Scanner;

public class temp
{
    public static void main(String[] args)
    {   Scanner input=new Scanner(System.in);

        int pin =2512;
        for(int i=1;i<4;i++)
        {
            System.out.println("enter pin:");
            int y=input.nextInt();
            if(pin==y)
                {System.out.println("pin is correct....welcome ");
                break;
                }
            else
                System.out.println("password is not correct....no of trials left:"+(3-i));
        }


        //conditional statemnt
        int y=(1<4)?1:-1;
        System.out.println(y);
        System.out.println(Math.pow(2,8));


        //System.out.println(Math.pow(2,3));
        float aa=2.3f;
        //System.out.println(System.currentTimeMillis());
        //CASTING to know ascii value of char
        int i=(int)'A';
        int j=(int)'a';
        int k=(int)'9';
        System.out.println("A:"+i);
        System.out.println("a:"+j);
        System.out.println("9:"+k);


        int ba='A'+'a';
        System.out.println("A + a: "+ba);

        //TO CONVERT STRING TO INTEGER
        String intstr="1234";
        int intval=Integer.parseInt(intstr);
        int sum=intval+5;
        System.out.println("intstr+5: "+sum);



        /*int [] arr=new int [5];
        arr= new int[]{1, 2, 3, 4, 5};
        for (int el:arr)
        {
            System.out.println(el);
        }
        for (int i=0; i<5;i++)
        {
            System.out.println(arr[i]);
        }*/

    }
}
