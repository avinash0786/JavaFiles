package dsa450;

public class linkedLstApr {
    public static void main(String[] args) {

    }
    //MERGE SORT FOR LINKED LIST
    public ListNode sortList(ListNode head) {
        if (head==null || head.next==null)
            return head;
        ListNode temp=head;     // end of first half ,  HEAD IS head of first half
        ListNode slow=head;      //head of second half
        ListNode fast=head;       // fast is end on next half
        while (fast!=null && fast.next!=null){
            temp=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        temp.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(slow);
        return mergeList(left,right);
    }

    private ListNode mergeList(ListNode left, ListNode right) {
        ListNode sorted=new ListNode(0);
        ListNode curNode=sorted;
        while (left!=null && right!=null){
            if (left.val<right.val){
                curNode.next=left;
                left=left.next;
            }
            else {
                curNode.next=right;
                right=right.next;
            }
            curNode=curNode.next;
        }
        if (left!=null){
            curNode.next=left;
        }
        if (right!=null){
            curNode.next=right;
        }
        return sorted.next;
    }
}
