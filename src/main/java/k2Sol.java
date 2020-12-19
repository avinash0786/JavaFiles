import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class k2Sol
{
    static int start;
    static int maxDays;
    static int mul=2;
    static int nextstart()
    {   //System.out.println("entry stat "+start);
        start=start*(mul);
        mul++;
        return start;
    }

    static boolean nextPosible(int checkdate,int routedate)
    {
        if(routedate%checkdate==0)
            return true;
        else return false;
    }


    public static void main(String[]args)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=in.nextInt();
        int result[]=new int [t];
        for(int i=0;i<t;i++)
        {   System .out.println();
            int bus=in.nextInt();
            maxDays=in.nextInt();
            int busesDay []=new int [bus];
            for(int j=0;j<bus;j++)
                busesDay[j]=in.nextInt();

            start=busesDay[0];
            boolean ans=true;
            int index=0;
            System.out.println("bus length:"+busesDay.length);
            while (ans)
            {
                if(index==busesDay.length-1 && ans)
                    {ans=false;System.out.println("END start: "+start);break; }

                if(nextPosible(start+1,busesDay[index]))
                {   System.out.println("start+1::"+(start+1)+nextPosible(start+1,busesDay[index])+" index "+index);
                    index++;
                    ans=true;
                }
                else
                {
                    nextstart();
                    System.out.println("start: "+start);
                    index=1;
                }
            }
            result[i]=start;
            System.out.println("----------------");
        }
        for(int i=0;i<result.length;i++)
            System.out.println("Case #"+(i+1)+": "+result[i]); //Case #4: 0
    }
}
