package afterMay;

import dsa450.ListNode;
import dsa450.TreeNode;

import java.util.List;
import java.util.Stack;

//Linked list, practice
public class linkedListPrac {
    public static void main(String[] args) {

    }
    //adding two numbers represented by 2 linked list
    // 2->4->3
    // 5->6->4   sum(342,465)=708
    public static ListNode addTwoNoLL(ListNode first, ListNode second){
        ListNode ans=new ListNode(0);
        ListNode cur=ans;
        int carry=0;
        //looping till carry is not 0 and both are null, ensure all cases are met
        while (first!=null || second!=null || carry!=0){
            int sum=0;
            if (first!=null){   //adding if the first node is valid
                sum+=first.val;
                first=first.next;
            }
            if (second!=null){  //adding if second node is valid
                sum+=second.val;
                second=second.next;
            }
            sum+=carry;         //adding prev carry to cur sum
            carry=sum/10;       //computing the carry
            //  5+7=12  cur node is 12%10==2 and carry is 12/10==1
            cur.next=new ListNode(sum%10);      //adding the cur sum new node
            cur=cur.next;                       //moving further
        }
        return ans.next;
    }
    //merge two sorted linked list
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        ListNode head,cur;
        if (l1.val<l2.val){
            head=cur=l1;
            l1=l1.next;
        }
        else {
            head=cur=l2;
            l2=l2.next;
        }
        while (l1!=null && l2!=null){
            if (l1.val<l2.val){
                cur.next=l1;
                cur=cur.next;   //move cur to the curent node
                l1=l1.next;     //move traversal pointer to next
            }
            else {
                cur.next=l2;
                cur=cur.next;   //move cur to the curent node
                l2=l2.next;     //move traversal pointer to next
            }
        }
        if (l1==null)       //if first is exhausted, append the rest of second at last
            cur.next=l2;
        if (l2==null)       //if second is exhausted, append the rest of first at last
            cur.next=l1;
        return head;
    }
    //reverse the linked list in group of k
    public static ListNode reverseLLkGroup(ListNode head,int k){
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
            head.next=reverseLLkGroup(next,k);
        return prev;
    }
    //reverse in k group iterative
    public static ListNode reverstKgroupIter(ListNode head,int k){
        ListNode cur=head,prevFirst=null;
        boolean firstPass=true;
        while (cur!=null){
            ListNode first=cur,prev=null;
            int count=0;
            while (cur!=null && count<k){
                ListNode next=cur.next;
                cur.next=prev;
                prev=cur;
                cur=next;
                count++;
            }
            if (firstPass){
                head=prev;      //assign head as the last node of first group i.e prev
                firstPass=false;
            }
            else
                prevFirst.next=prev;

            prevFirst=first;    //updating the first of prev group to cur gp first
        }
        return head;
    }
    //rearrange linked list : spiral traversal of the linked list
    // 1,2,3,4,5,6      op:  1,6,2,5,3,4
    //first step is find the middle, then reverse the second part, than merge both of them
    public static ListNode rearrangeLinkedListFirstLastOrder(ListNode head){
        ListNode slow=head,fast=head;
        //finding the middle point
        while (fast!=null && fast.next!=null){  //if any one points to null stop
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode left=head;
        ListNode right=slow.next;
        slow.next=null;     //break and divide ll into to parts left and right
        //reverse the right part
        ListNode cur=right;
        ListNode prev=null;
        while (cur!=null){
            ListNode next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        right=prev; //making the end of right part as the head of right part
        //merging both the linked list by taking one by one elements from both
        while (left!=null && right!=null){
            ListNode leftNext=left.next;    //storing for next jump
            ListNode rightNext=right.next;  //storing for next jump

            left.next=right;                //liking left cur node to right first node
            right.next=leftNext;            //linking right first node to left cur next node

            left=leftNext;                  //jumping to next of current using saved pointer
            right=rightNext;
        }
        return head;
    }

    //82. Remove Duplicates from Sorted List II
    //delete all nodes that have duplicate numbers,
    // leaving only distinct numbers from the original list
    public static ListNode removeDuplicateNode(ListNode head){
        if(head == null || (head.next==null)) return head;
        ListNode dummy=new ListNode(0); //to keep track of the head new
        ListNode ans=dummy; //to change the connections
        ListNode cur=head;  //for traversal
        while (cur!=null){
            if (cur.next!=null){
                if (cur.val==cur.next.val){
                    int curVal=cur.val;
                    //we just need to skip all same values
                    while (cur!=null && cur.val==curVal)
                        cur=cur.next;
                    ans.next=cur;
                }
                //in case adj are not equal just do normal traversal
                else {
                    ans.next=cur;   //link prev to cur node
                    cur=cur.next;   //move cur iter one step forward
                    ans=ans.next;   //mode ans one step forward
                }
            }
            else
                break;
        }
        return dummy.next;
    }
    //detect and remove the loop in linked list
    public static ListNode detectAndRemoveCycleLL(ListNode head){
        ListNode slow=head,fast=head;
        if(head==null ||  fast.next==null ) //if there is 0 or 1 element no loop exist
            return null;
        //check if loop exists using : Floyd cycle detection algo
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast)
                break;
        }
        if (slow!=fast) //if after loop slow and fast are not equal, means no loop
            return null;
        //remove the loop, first find the point of loop
        slow=head;      //set slow to head and now move both fast and slow one step till they meet
        //the point where they meet is the point of loop
        while (slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    //linked list in a binary tree
    //Return True if all the elements in the linked list starting from the head
    // correspond to some downward path connected in the binary tree otherwise return False
    public static boolean isLLinBST(TreeNode root,ListNode  head){
        if (root==null)
            return false;
        if (foundLLinBST(root,head))
            return true;
        //moving left and right keeping head at its own place
        return isLLinBST(root.left,head) || isLLinBST(root.right,head);
    }
    public static boolean foundLLinBST(TreeNode root,ListNode head){
        if (head==null) //if we reached the end of linked list by matching all prev nodes return true
            return true;
        if (root==null || root.val!= head.val)
            return false;
        //if values match, check if next value match in both the tree left and right
        return foundLLinBST(root.left,head.next) || foundLLinBST(root.right,head.next);
    }
    /*given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
     which may or may not point to a separate doubly linked list. These child lists may have one or more children of
      their own, and so on Flatten the list so that all the nodes appear in a single-level, doubly linked list.
       You are given the head of the first level of the list.*/
    public static Node flatten(Node head) {
        Stack<Node> stk=new Stack<>();
        Node cur=head,tail=head;
        while (cur!=null){
            if (cur.child!=null){
                Node child=cur.child;
                if (cur.next!=null){    //if there is next node add to stack to access later
                    stk.push(cur.next);
                    cur.next.prev=null; //end the prev link
                }
                cur.next=child;
                child.prev=cur;
                cur.child=null;     //end the child link after flattening no child
            }
            tail=cur;
            cur=cur.next;
        }
        while (!stk.isEmpty()){
            cur=stk.pop();
            tail.next=cur;  //make next link
            cur.prev=tail;  //make prev link
            while (cur!=null){  //move next till end of this level
                tail=cur;
                cur=cur.next;
            }
        }
        return head;
    }
}
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
