package DS_A;

public class runner
{
    public static void main(String ...dd)
    {
        linkedList list=new linkedList();
        list.insert(33,"Avinash","b.tec");
        list.insert(25,"Tarun","b.tec");
        list.insert(66,"Arun","ece");

        list.insertAtStart(123,"RAhhu","mean");
        list.insertAt(2,565,"yog","datd");
        list.insertAt(0,888,"pradeep","net");
        list.show();
        System.out.println("------------------------");
        list.delete(2);
        list.show();
    }
}
