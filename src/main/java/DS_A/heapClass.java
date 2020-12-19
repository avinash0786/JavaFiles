package DS_A;

import java.util.ArrayList;

public class heapClass
{
    static ArrayList<Integer> heap=new ArrayList<>();
    public static void insertMaxHeap(int key)       //complexity -- log n base 2
    {
        heap.add(key);
        int i=heap.indexOf(key);
        //System.out.println("i: "+i);
        if(i==0) return;
        while (i>=1 && key >heap.get(i/2))
        {
            heap.set(i,heap.get(i/2));
            i=i/2;
        }
        heap.set(i, key);
    }

    public static void insertMinHeap(int key)
    {
        heap.add(key);
        int i=heap.indexOf(key);
        //System.out.println("i: "+i);
        if(i==0) return;
        while (i>=1 && key <heap.get(i/2))
        {
            heap.set(i,heap.get(i/2));
            i=i/2;
        }
        heap.set(i, key);
    }

    public static void deleteMaxHeap(int key)
    {
        int i=heap.indexOf(key);
        int leaf=heap.get(heap.size()-1);
        System.out.println("leaf: "+leaf);
        System.out.println("index, i: "+i);
        heap.set(i,leaf);
        heap.remove(heap.size()-1);
        System.out.println("heap in bt: "+heap);
        System.out.println("heap" +i+"/2/:"+heap.get(i/2));
        System.out.println("heap" +i+": "+heap.get(i));

        while(heap.get(i/2)>heap.get(i))
        {   System.out.println("while i: "+i);
            if(heap.get(i*2)<heap.get(i*2+1))
            {   System.out.println("if");
                heap.set(i,heap.get(i*2));
                heap.set(i*2,leaf);
                i=i*2;
            }
            else
            {   System.out.println("else");
                heap.set(i,heap.get(i*2+1));
                heap.set(i*2+1,leaf);
                i=i*2+1;
            }
        }
    }

    public static void heapSort()
    {
        while (heap.size()!=0)
        {
            System.out.print(heap.get(0)+" ");
            deleteMaxHeap(heap.get(0));
        }
    }



    public static void main(String ...ff)
    {
        System.out.println("This is heap and heap sort class maintained in array and Binary tree using level order traversal");
        //System.out.println(heap.size());

        //,30,10,12,6,5,20,50
        int arr[]={40,35,15,30,10,12,6,5,20,50,7,8,3,2,60};
        for(int a:arr)
           {insertMaxHeap(a);
        System.out.println(heap);}

        System.out.println("Final: \n"+heap);
        //System.out.println("Min heap");
        //for(int a:arr)
        //{insertMinHeap(a);System.out.println(heap);}
        deleteMaxHeap(40);
        System.out.println("40 remove: "+heap);

        deleteMaxHeap(60);
        //System.out.println(heap.size());
        System.out.println("60 remove: "+heap);
        //heapSort();


    }
}
