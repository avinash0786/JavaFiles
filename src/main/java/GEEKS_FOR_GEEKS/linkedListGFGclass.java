package GEEKS_FOR_GEEKS;

import java.util.HashSet;

public class linkedListGFGclass
{
    public static void main(String[] args) {
//        linkedList first=new linkedList();
//        first.insertStart(23);
//        first.insertStart(12);
//        first.insertStart(56);
//        first.insertStart(89);
//        first.traverse();
//        first.middleLinkedList();
//        System.out.println("the length of linked  list is : "+first.getLength());
//        first.nthFromEnd(4);
//        first.traverse();
//        first.reverselinkedList();
//        first.traverse();
//        LRUcacheInplm(new int[]{10,20,10,30,40,50,30,40,60,30},4);
    }
    // LRU(least recent use)  cache design: Concept :temproral locality
    //the recently used program is most likely to be required again

    static void LRUcacheInplm(int[]sequence,int cache){
        linkedList lru=new linkedList();
        node HEAD=lru.getHEAD();
        int curLen=0;
        for (int i = 0; i < sequence.length; i++) {
            node forw=HEAD;
            node back=HEAD;
            forw=forw.next;
            while (forw !=null && forw.next!=null){
                if (forw.data==sequence[i]){
                    back.next=forw.next;
                    forw.next=HEAD;
                    HEAD=forw;
                    System.out.println("Hit: "+sequence[i]);
                    break;
                }
                forw=forw.next;
                back=back.next;
            }
            System.out.println("Miss: "+sequence[i]);
            node temp=new node(sequence[i]);
            temp.next=HEAD;
            HEAD=temp;
            System.out.println("Added start: "+sequence[i]);
            if (lru.getLength()>cache){
                forw=HEAD;
                back=null;
                while (forw.next!=null){
                    forw=forw.next;
                    back=back.next;
                }
                back.next=null;
                System.out.println("Removed last");
            }
        }
    }
    static node merge2sortedLL(node a,node b){
        node head=null,tail=null;
        if (a==null) return b;
        if (b==null) return a;
        if (a.data<=b.data){
            head=tail=a;
            a=a.next;
        }
        else {
            head=tail=b;
            b=b.next;
        }
        while (a!=null && b!=null){
            if (a.data<=b.data){
                tail.next=a;    //pointing the next to a;
                tail=a;         //and moving the tail to a'
                a=a.next;       // and moving the pointer to next element in own linklist
            }
            else {
                tail.next=b;
                tail=b;
                b=b.next;
            }
        }
        if (a==null) tail.next=b;
        else tail.next=a;
        return head;
    }
}
class linkedList{
    private node HEAD;
    private static int length;
    node getHEAD(){return HEAD;}
    void insertStart(int d){
        node box=new node(d);
        if (HEAD==null)
            HEAD=box;
        else {
            box.next=HEAD;
            HEAD=box;
        }
        length++;
    }
    int getLength(){
        return length;
    }
    void traverse(){
        node runner=HEAD;
        while (runner!=null){
            System.out.println(runner.data);
            runner=runner.next;
        }
    }
    void middleLinkedList(){    //fast and slow runner
        node slow=HEAD;
        node fast=HEAD;
        while (fast!=null && fast.next!=null){  //checking fast!=null ensure that fast.next is never accessed
            slow=slow.next;
            fast=fast.next.next;
        }
        /*
        in case of even number elements fast.next becomes null
        in case of odd nodes fast becomes null
        SO BOTH CASE MUST BE TAKEN CASE
         */
        System.out.println("Middle element in linked list is: "+slow.data);
    }
    //  N-th node form end (length-n+1)
    //second method is to fist move pointer n steps ahead
    // and start a new pointer from start and now move both pointer one ahead
    // till second reach null first reach n-th form end
    void nthFromEnd(int n){
        if (HEAD==null)
            return;
        node first=HEAD;
        node second=HEAD;
        while (n-->0){
            second=second.next;
        }
        while (second!=null){
            first=first.next;
            second=second.next;
        }
        System.out.println("N-the node from end is: "+first.data);
    }
    void reverselinkedList(){
        if (HEAD==null)
            return;
        node curr=HEAD;
        node prev=null;
        while (curr != null) {
            node next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        HEAD=prev;
        System.out.println("Linked list reversed");
    }
    node reverseRec(node curr,node prev){   //recursive method to reverse
        if (curr==null)
            return prev;
        node next=curr.next;                //similar to iterative method
        curr.next=prev;
        return reverseRec(next,curr);
    }
    //Reverse a linked list in group
    node reverseK(node head,int k){
        node curr=head,next=null,prev=null;
        int count=0;
        while (curr!=null && count<k){
            next=curr.next;         //reversing node group
            curr.next=prev;
            prev=curr;
            curr=next;
            count++;
        }
        if (next!=null){
            head.next= reverseK(next,k);    //assigning end of current group to prev of second group
        }
        return prev;    // prev=curr;   //last node in group
    }
    node reverseKiterative(node head,int k){
        node curr=head,prevfirst=null;
        boolean isFirstPass=true;
        while (curr!=null){
            node first=curr,prev=null;
            int count=0;
            while (curr!=null && count<k){
                node next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
                count++;
            }
            if (isFirstPass){
                head=prev;
                isFirstPass=false;
            }
            else {
                prevfirst.next=prev;
            }
            prevfirst=first;
        }
        return head;
    }
    //  LOOP DETECTION
    //  the next of last loop, rather than pointing to null points to one of the previous nodes
    /*
    Method 1. O(n^2)
    method 2. visited/ notVisited bool value
    method 3. modify using temp node
    method 4. hashing
     */

    boolean detectLoopHashing(){
        HashSet<node> hash=new HashSet<>();     //node data type hashset
        for(node curr=HEAD;curr!=null;curr=curr.next){
            if (hash.contains(curr))
                return true;
            hash.add(curr);
        }
        return false;
    }

    boolean detectLoopMethod3(){
        node temp=new node(99);
        node curr=HEAD;
        while (curr!=null){
            if (curr.next==null)
                return false;
            if (curr.next==temp)
                return true;
            node currNext=curr.next;
            curr.next=temp;
            curr=curr.next;
        }
        return false;
    }

    /*  ****    FLOYD'S CYCLE DETECTION     ****
    1. does not modify node
    2. does not modift the linkedlist
    3. dose not take O(n) auxillary space
     */
    boolean floydCycleDet(){
        node fast=HEAD, slow=HEAD;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast)
                return true;
        }
        return false;
    }
    void segregateOddEven(){
        node evenStart=null,evenEnd=null,oddStart=null,oddEnd=null;

        for(node curr=HEAD;curr!=null;curr=curr.next){
            int x=curr.data;
            if (x%2==0){
                if (evenStart==null){
                    evenStart=curr;
                    evenEnd=evenStart;
                }
                else {
                    evenEnd.next=curr;
                    evenEnd=evenEnd.next;
                }
            }
            else {
                if (oddStart==null){
                    oddStart=curr;
                    oddEnd=curr;
                }
                else {
                    oddEnd.next=curr;
                    oddEnd=oddEnd.next;
                }
            }
        }
        if (oddStart==null || evenStart ==null)
            return;
        evenEnd.next=oddStart;
        oddEnd.next=null;
        System.out.println("Segreated linked list Even-Odd");
    }
    void intersectionPoint(node H1, node H2){
        int c1=0;
        int c2=0;
        node temp1=H1;
        node temp2=H2;
        while (temp1!=null){
            c1++;
            temp1=temp1.next;
        }
        while (temp2!=null){
            c2++;
            temp2=temp2.next;
        }
        temp1=H1;
        temp2=H2;
        int traverse=0;
        if (c1>c2){
            traverse=c1-c2;
            while (traverse-->0)
                temp1=temp1.next;
        }
        else {
            traverse=c2-c1;
            while (traverse-->0)
                temp2=temp2.next;
        }
        while (temp1!=null && temp2!=null){
            if (temp1==temp2){
                System.out.println("Point of intersection is at : "+temp1.data);
                return;
            }
            temp1=temp1.next;
            temp2=temp2.next;
        }
    }
    void pairWiseSwap(){
        if (HEAD==null || HEAD.next==null) return;
        node curr=HEAD.next.next;
        node prev=HEAD;
        HEAD=HEAD.next;
        HEAD.next=prev;
        while (curr!=null && curr.next!=null){
            prev.next=curr.next;
            prev=curr;
            node next=curr.next.next;
            curr.next.next=curr;
            curr=next;
        }
        prev.next=curr;
        System.out.println("Pair wise swapped");
    }


}

class node{
    node next;
    int data;
    node(int x){
        data=x;
        next=null;  //  OPTIONAL::null by default
    }
}
/*
Like arrays, linked lists are also linear data structures but in linked lists elements are not stored at contiguous memory locations.
They can be stored anywhere in the memory but for sequential access, the nodes are linked to each other using pointers.

 */