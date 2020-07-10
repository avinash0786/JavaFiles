import java.util.ArrayList;
import java.util.LinkedList;

public class arraylist
{
    private int oo=0;
    //no default value in array list
    public static void main (String ...ff)
    {
        ArrayList<Integer> mylist=new ArrayList<Integer>();    //default starting size is 10
        ArrayList aa=new ArrayList();
        aa.add(89);
        //System.out.println(aa.get(0));
        aa.add(12);
        //aa.remove(0);
        aa.add(45);
        aa.add(12);
        aa.add(56);
        System.out.println(aa);

        //----------------------------------------
        System.out.println();
        LinkedList<Integer> l1=new LinkedList<Integer>();        //linked list
        l1.add(12);
        l1.add(89);
        l1.add(76);
        l1.add(56);
        l1.add(12);
        l1.remove();
        for(int n:l1)
            System.out.println(n);

        ArrayList<Integer> op=(ArrayList<Integer>)( aa.clone());
        op.retainAll(l1);
        System.out.println(op);
        /*ArrayList<Integer> ss=new ArrayList<>();
        ss.add(88);
        ss.addAll(l1);
        System.out.println("addall ss: "+ss);*/

       // ss.removeAll(mylist);
        //System.out.println("removeall ss"+ss);

        //ss.clear();
       // ss.removeAll();removes all except provided collection
        //ss.isEmpty();
       // Arraylist<Integer> a1=new ArrayList<Integer>();


    }

}
