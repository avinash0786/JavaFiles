import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class testing
{
    public static void main(String ...ss)
    {
        //System.out.println("Hello World");
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n=in.nextInt(); //no of points
        //System.out.println("n: "+n);
        double perimeter=0;
        int preX=in.nextInt();
        int preY=in.nextInt();
        int fx=preX;
        int fy=preY;
        int X=0;
        int Y=0;
        for(int i=0;i<n;i++)
        {
            if(i==n-1)
            {  System.out.println("laste ");
                System.out.println("preX: "+preX+" preY:"+preY);
                System.out.println("fx: "+fx+" fy:"+fy);
                double peri=Math.sqrt(Math.pow(preX-fx,2)+Math.pow(preY-fy,2));
                System.out.println("cur per: "+peri);
                perimeter+=peri;
                System.out.println("run: "+i+" pri: "+perimeter);
                break;
            }
            else
            {
                X=in.nextInt();
                Y=in.nextInt();
                System.out.println("preX: "+preX+" preY:"+preY);
                System.out.println("X: "+X+" Y:"+Y);
                double peri=Math.sqrt(Math.pow(X-preX,2)+Math.pow(Y-preY,2));
                System.out.println("cur per: "+peri);
                perimeter+=peri;
                preX=X;
                preY=Y;
                System.out.println("run: "+i+" pri: "+perimeter);
            }
            System.out.println("-------------------------");
        }
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("final peri: "+perimeter);
        System.out.println("avg: "+(perimeter/n));


        //-----------------------------------------------------------------------
        /*int id=0;
        String var="p";
        System.out.println(var);
        var+="apple";
        System.out.println(var);
        char []arr={'a','b','c'};
        String aa=new String(arr);
        System.out.println(aa);*/
        //Scanner in=new Scanner(System.in);
        /*int a=2;
        if(a==2)
        {

        }
        else
            System.out.println("else");
        //String s=in.nextLine();
        System.out.println("eee");
        //

        //int c=in.nextInt();
        /*switch (c)
        {
            case 1:
                System.out.println("case 1");
            case 2:
                System.out.println("cae 2");
        }*//*
        System.out.println(950%100==60);
        double q=50;
        double w=100;
        System.out.println((q/w));*/
        int x=0,b;
        //b=in.nextInt();
        //x=b/500*100;
        //System.out.println("x: "+x);

    }
}
/*
* // Prices must be given for at least two days
    if (n == 1)
        return;

    int count = 0; // count of solution pairs

    // solution vector
    Interval sol[n/2 + 1];

    // Traverse through given price array
    int i = 0;
    while (i < n-1)
    {
        // Find Local Minima. Note that the limit is (n-2) as we are
        // comparing present element to the next element.
        while ((i < n-1) && (price[i+1] <= price[i]))
            i++;

        // If we reached the end, break as no further solution possible
        if (i == n-1)
            break;

        // Store the index of minima
        sol[count].buy = i++;

        // Find Local Maxima.  Note that the limit is (n-1) as we are
        // comparing to previous element
        while ((i < n) && (price[i] >= price[i-1]))
            i++;

        // Store the index of maxima
        sol[count].sell = i-1;

        // Increment count of buy/sell pairs
        count++;
    }

    // print solution
    if (count == 0)
        printf("No Profit");
    else
    {
       for (int i = 0; i < count; i++)
          printf("(%d %d) ", sol[i].buy, sol[i].sell);
    }
    //printf("\n");
    return;
* */