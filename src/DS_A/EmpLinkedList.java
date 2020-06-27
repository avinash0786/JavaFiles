package DS_A;

public class EmpLinkedList
{
    Employee head;
    public void insert(int id,String na)   //insertng at start
    {
        Employee n=new Employee();
        n.id=id;
        n.name=na;
        if(head==null)
        { head=n; }
        else
        {
            n.next=head;
            head=n;
        }
    }
    ///insert at end
    public void insertAtEnd(int id,String na)   //insertng at start
    {
        Employee n=new Employee();
        n.id=id;
        n.name=na;
        if(head==null)
        {
            head=n;
        }
        else
        {
            Employee node=head;
            while (node.next!=null){node=node.next;}
            node.next=n;
        }
    }

    //insert at index
    public void insertAtIndex(int index,int id,String na)   //insertng at start
    {
        Employee n=new Employee();
        n.id=id;
        n.name=na;
        Employee node=head;   //running container
        if(index==0){insert(id,na);}
        else
        {
            for(int i=1;i<index;i++){node=node.next;}
            n.next=node.next;
            node.next=n;
        }
    }

    public void showEmp()
    {
        Employee n=head;
        while (n.next!=null)
        {
            System.out.println("Employee ID: "+n.id+" Name: "+n.name);
            n=n.next;
        }
        System.out.println("Employee ID: "+n.id+" Name: "+n.name);
    }
}
