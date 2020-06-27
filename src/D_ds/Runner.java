package D_ds;

public class Runner
{
    public static void main(String ...ff)
    {
        LinkedList l1=new LinkedList();
        l1.insert(7,"chocolate");
        l1.insert(2,"samosa");
        l1.insert(3,"momos");
        l1.insertAtStart(7,"chocolate");
        l1.insertAtStart(2,"samosa");
        l1.insertAtStart(3,"momos");
        l1.insertAtIndex(2,3333,"momos");
        l1.show();
        System.out.println("-----------------------------------");
        Stack s=new Stack();
        s.push(3,"momos");
        s.push(34,"mo");
        s.push(67,"maggie");
        s.pop();
        s.push(38,"momos");
        s.show();
    }
}
