import java.util.*;

public class collection_t
{
    public static void main(String ...a)
    {
        Collection<String > coll=new ArrayList<String>();
        coll.add("Delhi");
        coll.add("Mumbai");
        coll.add("Chennai");
        coll.add("Kolkata");
        coll.add("Patna");
        Iterator<String> iter=coll.iterator();
        while (iter.hasNext())
        {
            System.out.println(iter.next().toUpperCase()+" ");
        }
        System.out.println("---------------");
        /*for(String ele:coll)
            System.out.println(ele.toUpperCase()+" ");*/

        //-------------------------------------------
        List<Integer> arl=new ArrayList<Integer>();
        arl.add(1);
        arl.add(2);
        arl.add(3);
        arl.add(4);
        arl.add(5);
        arl.add(1,23);
        arl.add(4,12);
        System.out.println(arl);

        LinkedList<Object> ll=new LinkedList<Object>(arl);
        ll.add(2,"red");
        ll.removeLast();
        ll.addFirst("green");
        System.out.println(ll);
        ListIterator<Object> it=ll.listIterator();
        while(it.hasNext())
            System.out.println(it.next()+" ");
        System.out.println("backward display");
        it=ll.listIterator(ll.size());
        while (it.hasPrevious())
            System.out.println(it.previous()+" ");
        System.out.println("Sorting: ");
        Collections.sort(arl);
        System.out.println(arl);





    }
}
/*
--Collection interface defines the common operation for the list, vectors, stacks,,
queues, priority queues, and sets.
JAva collection framework supports 2 types of container:
1. One for storing a collection of elements is simply called a collection.
2. THe other , for storing key/value pairs, called maps.

=====
THere are different kind of collections:
1. Sets store a group of nonDuplicate elements
2. Lists store an ordered collection of elements
3. Queues store objects that are processed in FIFO fashion
--Common features of these collections are defined in the interface, and implementation
are provided in concrete classes.
--the collection interface provide the basic operation for adding and removing elements
in a collection.
----ITERATOR
Each collection has an iterator object that can be used to traverse all the elements
it the collection.
Iterator has a classic design pattern for walking through a dataStructure without having to
expose the details of how data is stored in the data structure.
--LIST
The list interface extends the collection inteface and defined a collection for storing elements
it a sequential order.
To create a list, use one of its two concrete classes. ArrayList and LinkedList
COMMON METHODS
add
addall
get
indexOf
lastIndexOf
listIterator
remove
set
sublist

---LINKEDLIST
addFirst
addLast
getFirst
getLast
removefirst
removeLast
 */
