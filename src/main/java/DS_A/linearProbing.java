package DS_A;

public class linearProbing
{
    static int [] hastTable=new int[100];

    public static int getIndex(int x)
    {
        return x%10;              // specify hash table size and return hash index
    }

    public static void addElement(int a)
    {
        int index=getIndex(a);
        if(hastTable[index]==0)    // 0 value denotes empty block
            {
                hastTable[index]=a;
                System.out.println(a+" Element Inserted index: "+index);
            }
        else
        {
            while (hastTable[index]!=0)
                index++;
            hastTable[index]=a;
            System.out.println(a+" Element Inserted probing index: "+index);

        }
    }
    public static void search(int a)
    {
        int index=getIndex(a);
        if(hastTable[index]==a)    // 0 value denotes empty block
            System.out.println(a+" Element found index: "+index);
        else
        {
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
                else index++;
            }
        }
    }


    public static void main(String ...aa)
    {
        //hash table of size 10
        //hash table of size 10
        long a=System.currentTimeMillis();
        addElement(46);
        addElement(6);
        addElement(16);
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
