package DS_A;

public class EmpRunner
{
    public static void main(String ...dd)
    {
        EmpLinkedList l1=new EmpLinkedList();
        l1.insert(1,"Avinash");
        l1.insert(22,"Tarun");
        l1.insert(13,"Akash");
        l1.insertAtEnd(234,"Pradeep");
        l1.insertAtIndex(2,93,"Yogesh");
        l1.showEmp();
    }
}
