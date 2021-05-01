package GEEKS_FOR_GEEKS;

import java.util.*;

public class hashingClass
{
    //  Open Addressing: Double hashing
    public int TABLESIZE;
    public int[] hashTable;

    hashingClass(int n){
        TABLESIZE=n;
        hashTable=new int[TABLESIZE];
    }
    public void showHash(){
        for (int i : hashTable) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public void addHash(int key){
        if(hashTable[hash1(key)]<=0) {
            hashTable[hash1(key)] = key;
            System.out.println("Inserted : "+key+" as index: "+hash1(key));
        }        else {
            int i=1;
            while (i!=hash1(key)){
                int tem=(hash1(key)+i*hash2(key))%TABLESIZE;
                if(hashTable[tem]<=0) {
                    hashTable[tem] = key;
                    System.out.println("Inserted : "+key+" as index: "+tem);
                    return;
                }
                i++;
            }
        }
    }
    public boolean searchHash(int key){
        if(hashTable[hash1(key)]==key)
            return true;
        else {
            int i=1;
            while (i!=hash1(key)){
                int tem=(hash1(key)+i*hash2(key))%TABLESIZE;
                if(hashTable[tem]==key) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
    public void deleteHash(int key){
        if(hashTable[hash1(key)]==key) {
            hashTable[hash1(key)] = -1;
        }        else {
            int i=1;
            while (i!=hash1(key)){
                int tem=(hash1(key)+i*hash2(key))%TABLESIZE;
                if(hashTable[tem]==key) {
                    hashTable[tem] = -1;
                    return;
                }
                i++;
            }
        }
    }
    private int hash1(int key){
        return key%TABLESIZE;
    }
    private int hash2(int key){
        return 11-(key %11 ) ;
    }


    /////////////////////////////////////////////////////////////////////

    public static int distinctCount(int[]arr){
        Arrays.sort(arr);
        int count=1;
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]==arr[i+1]) {
            }
            else
                count++;

        }
        return count;
    }
    public static void countFreq(int[]arr){
        HashMap<Integer,Integer>  op=new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(op.containsKey(arr[i])){
                op.put(arr[i],op.get(arr[i])+1);
            }
            else
                op.put(arr[i],1);
        }
        for (Map.Entry<Integer,Integer> entry : op.entrySet())
            System.out.println("Ele: "+entry.getKey()+" count: "+entry.getValue());
    }
    public static <Int> boolean isPair(int []arr, int sum){
        HashSet<Integer> op=new HashSet<>();
        for (int i : arr) {
            op.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            if(op.contains(sum-arr[i]))
                return true;
        }
        return false;
    }
    public static boolean zeroSumSubArr(int[]arr){
        HashSet<Integer> perf=new HashSet<>();
        int preSum=0;
        for (int i = 0; i < arr.length; i++) {
            preSum=preSum+arr[i];
            if(perf.contains(preSum))
                return true;
            if(preSum==0)
                return true;
            perf.add(preSum);
        }
        return false;
    }
    public static boolean subArrSum(int[]arr, int sum){
        HashSet<Integer> perf=new HashSet<>();
        int preSum=0;
        for (int i = 0; i < arr.length; i++) {
            if(preSum==sum)
                return true;
            preSum=preSum+arr[i];
            if(perf.contains(preSum-sum))
                return true;
            perf.add(preSum);
        }
        return false;
    }
    public static int longSubSum(int []arr,int sum){
        HashMap<Integer,Integer> index=new HashMap<>();
        int preSum=0;
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            preSum+=arr[i];
            if(preSum==sum)
                res=i+1;
            if(!index.containsKey(preSum))
                index.put(preSum,i);
            if(index.containsKey(preSum-sum))
                res=Math.max(res,i-index.get(preSum-sum));
        }
        return res;
    }
    public static int eqZeroOneSubArr(int []arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(arr[i]==0)?-1:arr[i];
        }
        HashMap<Integer,Integer> index=new HashMap<>();
        int res=0;
        int prefSum=0;
        for (int i = 0; i < arr.length; i++) {
            prefSum+=arr[i];
            if(!index.containsKey(prefSum))
                index.put(prefSum,i);
            if(index.containsKey(prefSum))
                res=Math.max(res,i-index.get(prefSum));
        }
        return res;
    }
    public static int commonSameSumLar(int[]arr1, int[]arr2){
        int[] mod=new int[arr1.length];
        for (int i = 0; i <arr1.length ; i++) {
            mod[i]=arr1[i]-arr2[i];
        }
        HashMap<Integer,Integer> index=new HashMap<>();
        int res=0,presum=0;
        for (int i = 0; i < mod.length; i++) {
            presum+=mod[i];
            if(!index.containsKey(presum))
                index.put(presum,i);
            if(index.containsKey(presum))
                res=Math.max(res,i-index.get(presum));
        }
        return res;
    }
    public static int logestConsSubSeq(int[]arr){
        HashSet<Integer> temp=new HashSet<>();
        for (int i : arr) {
            temp.add(i);
        }
        int res=0,cur=0;
        for (int i = 0; i < arr.length; i++) {
            if(!temp.contains(arr[i]-1)){
                cur=1;
                while (temp.contains(arr[i]+cur))
                    cur++;
                res=Math.max(res,cur);
            }
        }
        return res;
    }
    public static void countDistinctEle(int[]arr,int k){
        HashMap<Integer,Integer> freq=new HashMap<>();
        for (int i = 0; i < k; i++) {
            if(!freq.containsKey(arr[i]))
                freq.put(arr[i],1);
            else
                freq.replace(arr[i],freq.get(arr[i])+1);
        }
        System.out.println(freq.size());
        for (int i = k; i < arr.length; i++) {
            freq.replace(arr[i-k],freq.get(arr[i-k])-1);
            if (freq.get(arr[i-k])==0)
                freq.remove(arr[i-k]);
            if (!freq.containsKey(arr[i]))
                freq.put(arr[i],1);
            else
                freq.replace(arr[i],freq.get(arr[i])+1);
//            System.out.println(freq.size());
            for (Map.Entry<Integer,Integer> entry : freq.entrySet())
                System.out.println("Ele: "+entry.getKey()+" count: "+entry.getValue());
            System.out.println("-------------");
        }
    }
    public static void printNbyK(int[]arr,int k){
        int n=arr.length;
        HashMap<Integer,Integer> index=new HashMap<>();
        for (int i : arr) {
            index.put(i,index.getOrDefault(i,0)+1);
        }
        for(Map.Entry<Integer,Integer> ent:index.entrySet()){
            if (ent.getValue()>n/k)
                System.out.print(ent.getKey()+" ");
        }
    }
    public static void moreNbyKOcc(int[]arr,int k){
        int n=arr.length;
        HashMap<Integer,Integer> index=new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (index.containsKey(arr[i]))
                index.replace(arr[i],index.get(arr[i])+1);
            else if (index.size()<(k-1))
                index.put(arr[i],1);
            else {
                index.replaceAll((key, val) -> val--);
                for(Map.Entry<Integer,Integer> ent:index.entrySet()){
                    if (ent.getValue()==0)
                        index.remove(ent.getKey());
                }
            }
        }
        for(Map.Entry<Integer,Integer> ent:index.entrySet()){
            if (ent.getValue()>n/k)
                System.out.println(ent.getKey()+"-"+ent.getValue()+" ");
        }
    }
    static int findLongestConseqSubseq(int arr[], int N)
    {
        HashSet<Integer> index=new HashSet<>();
        for (int i : arr) {
            index.add(i);
        }
        int res=0,cur=0;
        for (int i = 0; i < N; i++) {
            if (!index.contains(arr[i]-1)){
                cur=1;
                while (index.contains(arr[i]+cur))
                    cur++;
                res=Math.max(res,cur);
            }
        }
        return res;
    }
    static ArrayList<Integer> sortByFreq(int arr[], int n)
    {
        HashMap<Integer,Integer> index=new HashMap<>();
        for (int i : arr) {
            if (index.containsKey(i)){
                index.replace(i,index.get(i)+1);
            }
            else
                index.put(i,0);
        }
        List<Map.Entry<Integer,Integer>> set = new ArrayList<Map.Entry<Integer,Integer>> (index.entrySet());
        Collections.sort(set, new comparator() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if(o1.getValue() > o2.getValue())
                    return -1;
                else if(o1.getValue() == o2.getValue()){
                    if(o2.getKey() < o1.getKey())
                        return 1;
                    else
                        return -1;

                }
                else if(o1.getValue() < o2.getValue())
                    return 1;

                return Integer.MIN_VALUE;
            }
        });
        ArrayList <Integer> ans = new ArrayList<Integer>();
        for(int i = 0;i<index.size();i++)
        {
            int count = set.get(i).getValue();
            while(count >= 0)
            {
                ans.add(set.get(i).getKey());
                count -- ;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
//        moreNbyKOcc(new int[]{30,10,20,20,20,10,40,30,30},4);
//        printNbyK(new int[]{10,20,30,10,10},3);
//        countDistinctEle(new int[]{10,20,10,10,30,40},4);
//        System.out.println(logestConsSubSeq(new int[]{3,8,4,5,7,12,1,6,23}));
//        System.out.println(commonSameSumLar(new int[]{0,1,0,0,0,0},new int[]{1,0,1,0,0,1}));
//        System.out.println(eqZeroOneSubArr(new int[]{1,4,13,-3,-10,5}));
//        System.out.println(longSubSum(new int[]{8,3,1,5,-6,6,2,2},4));
//        System.out.println(subArrSum(new int[]{1,5,13,-3,-10,5},6));
//        System.out.println(zeroSumSubArr(new int[]{1,5,13,3,-10,5}));// preSum: 1 6 19 16 6 11
//        System.out.println(isPair(new int[]{1,4,12,6,9,11,23},6));
//        countFreq(new int[]{1,4,6,1,4,62,9,12,4,12,62,59,59,13});
//        System.out.println(distinctCount(new int[]{1,4,6,1,4,62,9,12,4,12,62,59,59,13}));
//        HashSet<String> hashSet=new HashSet<>();
//        hashSet.add("Apple");
//        hashSet.add("Is");
//        hashSet.add("Red");
//        Iterator<String> itr=hashSet.iterator();
//        while (itr.hasNext())
//            System.out.print(itr.next()+" ");
//        System.out.println(hashSet.hashCode());
//        System.out.println(hashSet.contains("Read"));
//        System.out.println(hashSet.isEmpty());
//        hashingClass hsh=new hashingClass(12);
//        hsh.addHash(12);
//        hsh.addHash(9);
//        hsh.addHash(7);
//        hsh.addHash(11);
//        hsh.showHash();
//        hsh.deleteHash(7);
//        hsh.showHash();
//        System.out.println(hsh.searchHash(12));
//        hsh.deleteHash(11);
//        hsh.showHash();
//        hsh.addHash(11);
//        hsh.addHash(98);
//        hsh.addHash(190);
//        hsh.addHash(232);
//        hsh.addHash(19);
//        hsh.showHash();
//        hashingClass h2=new hashingClass(10);
//        h2.addHash(232);
//        h2.addHash(12);
//        h2.showHash();
//        myHash hash1=new myHash(10);
//        hash1.insert(12);
//        hash1.insert(3);
//        hash1.insert(7);
//        hash1.insert(11);
//        hash1.insert(4);
//        hash1.remove(12);
//        hash1.remove(11);
//        System.out.println(hash1.search(12));
//        System.out.println(hash1.search(11));
    }
}
abstract class comparator implements Comparator<Map.Entry<Integer,Integer>>
{
    public int compareTo(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)
    {
        if(o1.getValue() > o2.getValue())
            return -1;
        else if(o1.getValue() == o2.getValue()){
            if(o2.getKey() < o1.getKey())
                return 1;
            else
                return -1;

        }
        else if(o1.getValue() < o2.getValue())
            return 1;

        return Integer.MIN_VALUE;
    }
}

class myHash{
    int BUCKET;
    ArrayList<LinkedList<Integer>> hashTable;
    myHash(int b){
        BUCKET=b;
        hashTable=new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < b; i++) {
            hashTable.add(new LinkedList<Integer>());
        }
    }
    private int genHashKey(int key){
        return key%BUCKET;
    }
    void insert(int key){
        hashTable.get(genHashKey(key)).add(key);
    }
    boolean search(int key){
        return hashTable.get(genHashKey(key)).contains(key);
    }
    void remove(int key){
        hashTable.get(genHashKey(key)).remove((Integer)key);
    }
}

