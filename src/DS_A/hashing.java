package DS_A;

import java.util.LinkedList;

public class hashing
{
    static LinkedList [] hashTable=new LinkedList[10];
    //static
    public static int getIndex(int x)
    {
        return x%10;
    }

    public static void addElement(int a)
    {
        int index=getIndex(a);
        if(hashTable[index]==null)
        {
            LinkedList<Integer> a1=new LinkedList<>();
            a1.push(a);
            hashTable[index]=a1;
            System.out.println("Element added: "+a);
        }
        else
        {
            hashTable[index].push(a);
            System.out.println("Element added: "+a);
        }
    }
    public static void search(int a)
    {
        int index=getIndex(a);
        if(hashTable[index]==null)
        {
            System.out.println(a+" Element Not Present !");
        }
        else
        {
            if(hashTable[index].contains(a))
                System.out.println(a+" Element Present at index: "+index);
        }
    }


    public static void main(String ...ff)
    {
        //hash table of size 10
        addElement(10);
        addElement(23);
        addElement(16);
        addElement(25);
        search(10);
        addElement(6);
        addElement(122);
        search(123);
        addElement(5);
        search(5);
        addElement(68);
        addElement(75);
        search(69);
        search(123);
        search(999);
    }
}
