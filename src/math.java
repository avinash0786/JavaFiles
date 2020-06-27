public class math
{
    public static void main(String[]args)
    {
        int x,y,answer;
        x=20;
        y=15;
        answer=x/y;
        //x+y=35
        //x-y=5
        //x*y=300
        //x/y=1 not totally divisible , excluding decimal
        double a,b,c,d;
        a=35;
        b=15;
        c=a/b;
        //  a/b =2.3333333333333335
        d=a%b;
        //a%b= 5.0   remainder
        System.out.println("Modulous is  = "+d);
        System.out.println("Answer = "+answer);
        System.out.println("the value of c = "+ c);

        answer++; //post increment: value willl be incremented after this operaton
        System.out.println("incremented Answer = "+answer);

        //pre increment of c : first increment and then result
        System.out.println(++d);

        answer*=5;  // answer=answer*5

    }
}
