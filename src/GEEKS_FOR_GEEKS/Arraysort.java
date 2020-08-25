package GEEKS_FOR_GEEKS;

import java.util.Arrays;
import java.util.Comparator;

class Point implements Comparable<Point>
{
    int x,y;
    Point(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public int  compareTo(Point p)
    {
        return this.x-p.x;
    }
}
class MyCmp implements Comparator<Point>
{
    public int compare(Point a,Point b)
    {
        return a.x-b.x;
    }
}
class EvenOddCmp implements Comparator<Integer>
{
    public int compare(Integer a,Integer b)
    {
        return a%2-b%2;
    }
}
public class Arraysort {
    public static void main(String...a)
    {
        Point arr[]={new Point(10,20), new Point(3,12),new Point(5,7)};
        Arrays.sort(arr,new MyCmp());
        //Arrays.sort(arr);   Without using mycomp class
        System.out.println("Sorting...");
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i].x+" "+arr[i].y);

        Integer [] axx=new Integer[]{2,5,6,20,12,3,9,17};
        Arrays.sort(axx,new EvenOddCmp()); // first even then odd
        System.out.println();
        for(int i=0;i<axx.length;i++)
            System.out.println(axx[i]);
    }
}
