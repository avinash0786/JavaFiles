package DS_A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

//it dosent care about order it only cares about values in it
public class sets  //very similar to list but it does not contain duplicate
{
    public static void main(String ...sd)
    {
        HashSet<Integer> s1=new HashSet<>();
        s1.add(12);
        s1.add(23);
        s1.add(12);
        s1.add(65);
        s1.add(35);
        s1.add(64);
        for(Integer n:s1)
            System.out.println(n);

        List<Integer> l1=new ArrayList<>(s1);    //converitng l=set to list
        System.out.println(l1);
        Collections.sort(l1);       //accepts only list data not set, so converting is required
        System.out.println(l1);
        //collection.printElements(s1);   this is not a list
    }
}
