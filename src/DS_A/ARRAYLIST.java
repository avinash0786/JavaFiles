package DS_A;

import java.util.ArrayList;

public class ARRAYLIST
{
    public static void main(String ...xx)
    {
        ArrayList obj=new ArrayList();
        obj.add("A");
        obj.add("B");
        obj.add(null);
        obj.add("C");
        obj.add(1,"D");
        System.out.println(obj);
        obj.remove(2);
        obj.add(2,"M");
        obj.add("N");
        System.out.println(obj);
        System.out.println(obj.get(4));

    }
}
