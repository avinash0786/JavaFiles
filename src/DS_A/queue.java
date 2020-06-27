package DS_A;

public class queue
{
    queue_data head;
    public void enqueue(int d)
    {
        queue_data node=new queue_data();
        node.data=d;
        if(head==null)
            head=node;
        else
        {
            queue_data n=head;
            while(n.next!=null)n=n.next;
            n.next=node;
        }
    }
    public void dequeue()
    {
        head=head.next;
    }

    public void queueShow()
    {
        queue_data m=head;
        while (m.next!=null)
        {
            System.out.println(m.data);
            m=m.next;
        }
        System.out.println(m.data);
    }
}
