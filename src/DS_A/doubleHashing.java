package DS_A;

public class doubleHashing
{
    static int [] hastTable=new int[100];

    public static int getIndex(int x)
    {
        return x%10;              // specify hash table size and return hash index
    }
    public static int getH2(int x)
    {
        return 7-(x%7);              // specify second hash function value
    }

    public static void addElement(int a)
    {
        int index=getIndex(a);
        int h2=getH2(a);
        if(hastTable[index]==0)    // 0 value denotes empty block
        {
            hastTable[index]=a;
            System.out.println(a+" Element Inserted index: "+index);
        }
        else
        {
            int m=1;
            while (hastTable[index]!=0)
            {index=(index+m*h2)%10;  m++;}                     //hash function  h'(x)=( h(x) + i*h2 )%10   ,, 1,2,3,4, next indexes

            hastTable[index]=a;
            System.out.println(a+" Element Inserted probing index: "+index);

        }
    }
    public static void search(int a)
    {
        int index=getIndex(a);
        int h2=getH2(a);
        if(hastTable[index]==a)    // 0 value denotes empty block
            System.out.println(a+" Element found index: "+index);
        else
        {   int m=1;
            while (hastTable[index]!=a )
            {
                if(hastTable[index]==a)
                {
                    System.out.println(a+" Element found index: "+index);
                    break;
                }
                else if(hastTable[index]==0)
                {
                    System.out.println(a+" Element NOT found !");
                    break;
                }
                else {index=(index+m*h2)%10; m++;}
            }
        }
    }


    public static void main(String ...aa)
    {
        //hash table of size 10
        long a=System.currentTimeMillis();
        addElement(46);
        addElement(6);
        addElement(16);              //IT IS CONCLUDED THAT THERE IS NO DIFFERENCE IN EXEC TIME i.e 13-17 MILLIS
        search(6);
        addElement(26);
        search(46);
        addElement(96);
        //addElement(56);
        //search(69);
        //search(123);
        search(999);
        System.out.println("Time to exexute:  "+ (System.currentTimeMillis()-a)+" Millis");
    }
}
