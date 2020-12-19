import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class list_interface
{
    public static void main(String ...ss)
    {
        List<Integer> l1=new ArrayList<Integer>();
        l1.add(23);
        l1.add(34);
        System.out.println(l1);
        ListIterator<Integer> itr=l1.listIterator();
        System.out.println("Traversing list using iterator");
        while (itr.hasNext())
            System.out.println("Index: "+itr.nextIndex()+" value: "+itr.next());

    }
}
/*
* --List is an interface whereas Arraylist is the implementation class of List
* --we can create a List of any type, ArrayList, LinedList
* */