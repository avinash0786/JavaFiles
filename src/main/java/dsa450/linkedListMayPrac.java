package dsa450;

import java.util.ArrayList;

//linked list practice may 13
public class linkedListMayPrac {
    public static void main(String[] args) {
        ListNode l1=makeLinkedList(new int[]{1,2,3,4,5,6,7});
        traverseLinkedList(l1);
        ListNode l2=makeLinkedList(new int[]{1,3,6,9});
        ListNode l3=makeLinkedList(new int[]{4,7,10,12});
        ListNode merged=mergelinkedList(l2,l3);
        traverseLinkedList(merged);
        ListNode seg=segregateEvenodd(makeLinkedList(new int[]{17,15,8,9,2,4,6}));
        traverseLinkedList(seg);
        traverseLinkedList(rotateRight(makeLinkedList(new int[]{1,2,3,4,5}),2));
    }
    public static ListNode mergelinkedList(ListNode l1, ListNode l2){
        if (l1==null) return l2;
        if (l2==null) return l1;
        ListNode head,prev;
        if (l1.val<l2.val){
            head=prev=l1;
            l1=l1.next;
        }
        else {
            head=prev=l2;
            l2=l2.next;
        }
        while (l1!=null && l2!=null){
            if (l1.val<l2.val){
                prev.next=l1;
                prev=l1;
                l1=l1.next;
            }
            else {
                prev.next=l2;
                prev=l2;
                l2=l2.next;
            }
        }
        if (l1==null)
            prev.next=l2;
        else
            prev.next=l1;
        return head;
    }

    public static ListNode segregateEvenodd(ListNode list){
        ListNode even=new ListNode(2);
        ListNode start=even;
        ListNode odd=new ListNode(1);
        ListNode odstart=odd;
        while (list!=null){
            if (list.val%2==0){
                even.next=list;
                even=even.next;
            }
            else {
                odd.next=list;
                odd=odd.next;
            }
            ListNode next=list.next;
            list.next=null;
            list=next;
        }
        even.next=odstart.next;
        return start.next;
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1=0;
        int len2=0;
        ListNode cp1=headA,cp2=headB;
        while (cp1!=null){
            len1++;
            cp1=cp1.next;
        }
        while (cp2!=null){
            len2++;
            cp2=cp2.next;
        }
        int moveForward=Math.abs(len1-len2);
        cp1=headA;cp2=headB;
        if (len1>len2){
            while (len1-->0)
                cp1=cp1.next;
        }
        else {
            while (len2-->0)
                cp2=cp2.next;
        }
        while (cp1!=null && cp2!=null){
            if (cp1.next==cp2.next)
                return cp1.next;
            cp1=cp1.next;
            cp2=cp2.next;
        }
        return null;
    }

    //rearrange linked list :inp:: 1,2,3,4  op:: 1,4,2,3
    //find mid , reverse the other part and merge them
    public static ListNode rearrangeLinkedList(ListNode head){
        ListNode slow=head,fast=head;
        //finding mid ceil in the linked list
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode left=head;
        ListNode right=slow.next;
        slow.next=null;//break the mid link
        //reverse the right part
        ListNode temp2=right;
        ListNode prev=null;
        while (temp2!=null){
            ListNode nxt=temp2.next;
            temp2.next=prev;
            prev=temp2;
            temp2=nxt;
        }
        right=prev;
        //merging both list
        while (left!=null && right!=null){
            ListNode leftNext=left.next;
            ListNode rightNext=right.next;
            left.next=right;
            right.next=leftNext;
            left=leftNext;
            right=rightNext;
        }
        return head;
    }

    //reverse in group of k
    public static ListNode reverseKgroup(ListNode head,int k){
        ListNode cur=head,next=null,prev=null;
        int count=0;
        while (cur!=null && count<k){
            next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
            count++;
        }
        if (next!=null)
            head.next=reverseKgroup(next,k);
        return prev;
    }

    //rotate by k step
    public static ListNode rotateRight(ListNode head, int k) {
        if (head==null)
            return null;
        traverseLinkedList(head);
        int len=1;
        ListNode cur=head;
        while (cur.next!=null){
            cur=cur.next;
            len++;
        }
        //make list circular
        cur.next=head;
        int move=len-k%len;     //MOST IMPORTANT LOGIC
        // as after each k==len the list remains same , so we find the moves by taking the %

        while (move-->0)
            cur = cur.next;
        head=cur.next;
        cur.next=null;
        return head;
    }
    //---------------------------------UTILS-------------------------------------------------
    //make linked list from array
    public static ListNode makeLinkedList(int[] arr){
        int n=arr.length;
        if (n==0)
            return null;
        ListNode root=new ListNode(arr[0]);
        ListNode prev=root;
        for (int i = 1; i < n; i++) {
            prev.next=new ListNode(arr[i]);
            prev=prev.next;
        }
        return root;
    }
    public static void traverseLinkedList(ListNode root){
        ArrayList<Integer> op=new ArrayList<>();
        while (root!=null){
            op.add(root.val);
            root=root.next;
        }
        System.out.println(op);
    }
}
