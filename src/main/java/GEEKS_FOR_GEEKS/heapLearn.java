package GEEKS_FOR_GEEKS;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class heapLearn {

    public static void sortK(int[] arr, int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for (int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }
        int index=0;
        for (int i = k+1; i < arr.length; i++) {
            arr[index++]=pq.poll();
            pq.add(arr[i]);
        }
        while (!pq.isEmpty())
            arr[index++]=pq.poll();
    }

    public static int maxPurchase(int[] arr, int max){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for (int i : arr) {
            pq.add(i);
        }
        int count=0;
        int sum=0;
        while(!pq.isEmpty() && sum<=max){
            sum+=pq.poll();
            count++;
        }
        return count-1;
    }

    public static void nLargest(int[] arr, int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for (int i : arr) {
            pq.add(i);
        }
        while (k-->0){
            System.out.print(pq.poll()+" ");
        }
    }
    public static void nLarg02(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        /*
        it is a min-heap having the min element at the top
        if we encounter a element smaller than the top if min heap we ignore
        if we encounter a element greater then the top we remove the top and add that element to the
        min heap
         */
        for (int i = k; i <arr.length && !pq.isEmpty() ; i++) {
            if (pq.peek()<arr[k]){
                pq.poll();
                pq.add(arr[i]);
            }
        }
        System.out.println(pq);
    }
    public static void printKClosest(int arr[], int n, int k, int x)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (p1, p2) -> p2.getValue().compareTo(p1.getValue()));

        /*  --WITHOUT LAMBDA    --
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                new Comparator<Pair>()
                {
                    public int compare(Pair p1, Pair p2)
                    {
                        return p2.getValue().compareTo(
                                p1.getValue());
                    }
                });
         */

        for(int i = 0; i < k; i++)
        {
            pq.offer(new Pair(arr[i],
                    Math.abs(arr[i] - x)));
        }

        for(int i = k; i < n; i++)
        {
            int diff = Math.abs(arr[i] - x);

            if(pq.peek().getValue()>diff) {
                pq.poll();
                pq.offer(new Pair(arr[i], diff));
            }
        }

        while(!pq.isEmpty())
        {
            System.out.print(pq.poll().getKey() + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Max purchase count: "+maxPurchase(new int[]{1,12,5,111,200},10));
        nLargest(new int[]{5,15,10,20,8,25,18},3);
        System.out.println();
        System.out.println("Min heap n larg method");
        nLarg02(new int[]{5,15,10,20,8,25,18},3);
        PriorityQueue<Integer> pq=new PriorityQueue<>();   //   Min Heap
        pq.add(10);
        pq.add(20);
        pq.add(15);
//        System.out.println(pq);
        System.out.println("Top: "+pq.peek());

        //  Max Heap
        PriorityQueue<Integer> maxPQ=new PriorityQueue<>(Collections.reverseOrder());
        maxPQ.add(10);
        maxPQ.add(20);
        maxPQ.add(15);
        System.out.println(maxPQ);
        System.out.println("Max heap top: "+maxPQ.peek());

        int[] ar=new int[]{10,20,15,40,50,100,25,45};
        minHeap min=new minHeap(20);
        for (int i : ar) {
            min.insert(i);
        }
        min.showMinHeap();
        min.insert(12);
        min.showMinHeap();

        int[] arr = {12, 11, 13, 5, 6, 7};
        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        HeapSort.printArray(arr);
    }
}
class minHeap{
    private final int[] arr;
    private int size;
    private final int capacity;
    minHeap(int cap){
        size=0;
        capacity=cap;
        arr=new int[cap];
    }
    minHeap(int cap,int[] batch){
        arr=batch;
        capacity=cap;
    }
    int left(int i){return (2*i+1);}
    int right(int i){return (2*i+2);}
    int parent(int i){return (i-1)/2;}

    public void showMinHeap(){
        System.out.println(Arrays.toString(arr));
    }
    public void insert(int n){
        if (size==capacity){
            System.out.println("Max capacity reached !");
            return;
        }
        size++;
        arr[size-1]=n;
        for (int i =size-1; i !=0 && arr[parent(i)]>arr[i];) {
            int temp=arr[i];
            arr[i]=arr[parent(i)];
            arr[parent(i)]=temp;
            i=parent(i);
        }
    }

    public void minHeapify(int i){  //  O(log n)
        int lt=left(i);
        int rt=right(i);
        int smallest=i;
        if (lt<size && arr[lt]<arr[smallest])
            smallest=lt;
        if (rt<size && arr[rt]<arr[smallest])
            smallest=rt;
        if (i!=smallest){
            int temp=arr[i];
            arr[i]=arr[smallest];
            arr[smallest]=temp;
            minHeapify(smallest);
        }
    }
    public int extractMin(){    //  O(log n)
        if (size==0)
            return Integer.MAX_VALUE;
        if (size==1){
            size--;
            return arr[0];
        }
        int temp=arr[0];
        arr[0]=arr[size-1];
        arr[size-1]=temp;
        size--;
        minHeapify(0);
        return arr[size];
    }

    public void decreaseKey(int i, int x){
        arr[i]=x;
        while (i!=0 && arr[parent(i)]>arr[i]){
            int temp=arr[i];
            arr[i]=arr[parent(i)];
            arr[parent(i)]=temp;
            i=parent(i);
        }
    }
    public void deleteKey(int i){
        decreaseKey(i,Integer.MIN_VALUE);
        extractMin();
    }


}


class HeapSort
{
    public void buildheap(int[] arr, int n){
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
    }

    public void sort(int[] arr)
    {
        int n = arr.length;
        buildheap(arr,n);
        for (int i=n-1; i>0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int[] arr, int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    static void printArray(int[] arr)
    {
        int n = arr.length;
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }
}

class Pair
{
    Integer key;
    Integer value;

    public Pair(Integer key, Integer value)
    {
        this.key = key;
        this.value = value;
    }
    public Integer getKey()
    {
        return key;
    }
    public void setKey(Integer key)
    {
        this.key = key;
    }
    public Integer getValue()
    {
        return value;
    }
    public void setValue(Integer value)
    {
        this.value = value;
    }
}