package DS_A;

import java.util.HashMap;
import java.util.Map;

public class hashmapclas
{
    public static void main(String ...aa)
    {
        HashMap<String,String> mymap=new HashMap<String,String>();
        mymap.put("apple","red in colour");
        mymap.put("mango","yellow in colour");
        mymap.put("grapes","green in colour");
        System.out.println(mymap.get("apple"));
        mymap.replace("apple","new val");
        for(String n:mymap.keySet())
            System.out.println(n+" : "+mymap.get(n));
        System.out.println("-----------------------------");

        for(Map.Entry<String,String> ent:mymap.entrySet())
        {
            System.out.println(ent.getKey()+" : "+ent.getValue());
        }
    }
}
