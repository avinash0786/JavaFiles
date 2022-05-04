package afterMay;

import dsa450.Pair;

import java.util.*;

//sorting practice
//STABLE ALGO: bubble sort, insertion sort, merge sort
//NOT-STABLE ALGO: selection sort,quick sort, heap sort
public class sortingPract {
    public static void main(String[] args) {
        int[] arr={9,4,6,1,12,5,13,2};
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("K th smallest element :"+kthSmallestElement(new int[]{10,4,5,8,11,6,26},5));
//        System.out.println(countSmaller(new int[]{5,2,6,1}));
        List<String> list=new ArrayList<>(Arrays.asList("ale","apple","monkey","plea"));
        System.out.println("longest words: "+findLongestWord("abpcplea",list));
        System.out.println(Arrays.toString(rearrangeBarcodes(new int[]{1,1,1,2,2,2})));
    }
    //BUBBLE SORT O(n^2)
    //in each iter we put the largest element to end
    //slight optimization, second loop from 0->n-i
    //as we are at i th iter i elements are already fixed at correct place
    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i; j++) {
                if (arr[i]>arr[j])
                    swap(arr,i,j);
            }
        }
    }
    //selection sort
    //we select the minimum element and put it in position starting from starting swapping
    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int minIndex=i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<arr[minIndex])
                    minIndex=j;
            }
            swap(arr,i,minIndex);
        }
    }
    //insertion sort: most preferred for smaller array
    //best case: for already sorted array O(n) worst case O(n^2)
    public static void insertionSort(int[]arr){
        for (int i = 1; i < arr.length; i++) {
            int curEle=arr[i];
            int j=i-1;
            while (j>=0 && arr[j]>curEle)
                arr[j+1]=arr[j--];
            arr[j+1]=curEle;
        }
    }
    //PARTITION ALGO:
    // 1.Naive partition:: using the extra space, put all smaller first and then greater or equal, maintaining index
    // 2.Lomuto partiton:: maintaining a index, swap all greater to the end
    // 3.Hoares partition:: start 2 pointer from both side and find greater in left and smaller in right and swap them

    public static int lomutoPartition(int[] arr,int low, int high){
        int pivot=arr[high];
        int i=low-1;
        for (int j = low; j <high; j++) {
            if (arr[j]<pivot){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }
    public static int hoaresPartition(int[] arr,int low,int high){
        int pivot=arr[low];
        int i=low-1;
        int j=high+1;
        while (true){
            do {    //find index of greater element
                i++;
            } while (arr[i]<pivot);

            do {    //find index of smaller element
                j--;
            }while (arr[j]>pivot);
            if (i>=j)
                return j;
            swap(arr,i,j);  //swap the greater in left with smaller in right
        }
    }
    //k th smallest element using 1. sorting and 2. partition function
    public static int kthSmallestElement(int[] arr,int k){
        int l=0,r=arr.length-1;
        while (l<=r){
            int p=lomutoPartition(arr,l,r);
            if (p==k-1)
                return arr[p];
            else if (p>k-1)
                r=p-1;
            else
                l=p+1;
        }
        return -1;
    }
    //wiggle sort  reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int mid=(nums.length-1)/2;
        int right=nums.length-1;
        int[] res=new int[right+1];
        int counter=0;
        while (mid>=0 || right>(nums.length-1)/2){
                res[counter] = nums[mid];
                mid--;
                if (counter+1<nums.length){
                    res[counter] = nums[right];
                    right--;
                }
            counter+=2;
        }
        //now store back these values in input/original array
        for(int i = 0; i < nums.length; i++){
            nums[i] = res[i];
        }
    }

    public static void wiggleSortQuickSelect(int[] nums) {
        int mid=(nums.length-1)/2;
        int right=nums.length-1;
        int l=0,r=nums.length-1;
        int medium=0;   //finding the medium, running partition till pivot!=mid index
        while (l<=r){
            int p=lomutoPartition(nums,l,r);
            if (p==mid) {
                medium=nums[p];
                break;
            }
            else if (p>mid-1)
                r=p-1;
            else
                l=p+1;
        }
        int[] res=new int[right+1];
        int counter=0;
        for (int i = 0; i < right; i++) {
            
        }
        //now store back these values in input/original array
        for(int i = 0; i < nums.length; i++){
            nums[i] = res[i];
        }
    }
    //Leetcode 315(Hard) Count of Smaller Numbers After Self
    //using the merge sort :
    public static List<Integer> countSmaller(int[] arr){
        int n=arr.length;
        int[] result=new int[n];
        int[] indices=new int[n];//to record index of elements
        for (int i = 0; i < n; i++)
            indices[i]=i;
        countSmallMergeUtil(0,n,result,arr,indices);    //calling the merge sort functions
        // change int[] to List to return
        List<Integer> resultToReturn = new ArrayList<Integer>(n);
        for (int i : result) {
            resultToReturn.add(i);
        }
        return resultToReturn;
    }
    public static void countSmallMergeUtil(int left,int right,int[] result,int[] arr,int[] indices){
        if (right-left<=1)
            return;
        int mid=left+(right-left)/2;
        System.out.println("left: "+left+" mid: "+mid+" right: "+right);
        countSmallMergeUtil(left,mid,result,arr,indices);
        countSmallMergeUtil(mid,right,result,arr,indices);
        //calculating the no of smaller elements and the merge function
        int i=left,j=mid;
        // use temp to temporarily store sorted array
        List<Integer> temp = new ArrayList<>(right - left);
        System.out.println(Arrays.toString(indices));
        System.out.println(Arrays.toString(arr));

        while (i<mid && j<right){
            if (arr[indices[i]]<=arr[indices[j]]){
                System.out.println("Result add at "+indices[i]+" i: "+i+" mid: "+mid+" j: "+mid+" val: "+(j-mid));
                result[indices[i]]+=j-mid;
                temp.add(indices[i]);
                i++;
            }
            else {
                temp.add(indices[j]);
                j++;
            }
        }
        while (i<mid){
            System.out.println("out Result add at "+indices[i]+" i: "+i+" mid: "+mid+" j: "+mid+" val: "+(j-mid));
            result[indices[i]]+=j-mid;
            temp.add(indices[i]);
            i++;
        }
        while (j<right){
            temp.add(indices[j]);
            j++;
        }
        System.out.println("Temp: "+temp);
        i=0;
        for (int k = left; k < right; k++) {//just put back the changed index to their place
            indices[k]=temp.get(i++);
        }
        System.out.println("Result "+ Arrays.toString(result));
        System.out.println("index "+Arrays.toString(indices));
        System.out.println();
    }

    public static void swap(int[]arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    //find longest-word-in-dictionary-through-deleting::most brute force
    //generate all permutation of words by deleting/dont include or include approach
    //check of it is a valid word and compare with the  max word
    public static String findLongestWord(String str,List<String> words){
        HashSet<String> hashWords=new HashSet<>(words);
        List<String> perm=new ArrayList<>();
        generateWords(str,"",0,perm);
        String maxStr="";
        System.out.println(words);
//        System.out.println(perm);

        for (String s : perm) {
            //check if this word permutation is valid
            if (hashWords.contains(s)){
                if (s.length()>maxStr.length() || (s.length()==maxStr.length() && s.compareTo(maxStr)<0))
                    maxStr=s;
            }
        }
        return maxStr;
    }
    public static void generateWords(String str,String cur,int index,List<String> op){
        if (index==str.length())
            op.add(cur);
        else{
            generateWords(str,cur+str.charAt(index),index+1,op);
            generateWords(str,cur,index+1,op);
        }
    }
    //sorting the words according to lenght
    public static String findLongestWordOpt(String str, List<String> dictionary) {
//        Collections.sort(dictionary,(a,b)->a.length()==b.length()?a.compareTo(b):b.length()-a.length());
        String maxStr="";
        //comparing each valid word with the other and store only the maximum length word
        for (String word : dictionary) {
            if (isDictWord(word,str)){  //if this word is a valid word after deleting chars,
                //check if this is a better answer than cur stored word, and update accordingly
                if (word.length()>maxStr.length() || (word.length()==maxStr.length() && word.compareTo(maxStr)<0))
                    maxStr=word;
            }
        }
        return maxStr;
    }
    public static boolean isDictWord(String dictWord,String str){
        int j=0;//to keep index of the dictword
        for (int i = 0; i < str.length() && j<dictWord.length(); i++) {
            if (str.charAt(i)==dictWord.charAt(j))
                j++;
        }
        return j>=dictWord.length();
    }
    //given the position and speed p
    public int carFleet(int target, int[] position, int[] speed) {
        int n=position.length;
        car[] cars=new car[n];
        for (int i = 0; i < n; i++) {
            double arivTime=(double) (target-position[i])/speed[i];
            cars[i]=new car(position[i],arivTime);
        }
        //sort by position
        System.out.println(Arrays.toString(cars));
        Arrays.sort(cars,Comparator.comparingInt(a->a.position));
        int fleet=0;
        int j=0;
        for (j = n-1; j >0; j--) {
            //among two adjacent cars, if prev car has ariv time greater than ariv time of next car
            //then next car is independent and form a single fleet,if not then it will collide
            //and the ariv time of the prev car is same as the next car
            if (cars[j-1].arivTime>cars[j].arivTime)
                fleet++;
            else
                cars[j-1]=cars[j];
        }
        //if the last car[first in indx 0]
        //in any case we have to inc fleet by 1 as if the 1st fleet merge then it will not int its count
        //as it will not found a car with cars[j-1].arivTime>cars[j].arivTime therefore we inc it by 1
        //if it dosent merge next fleet will inc its count but the 0 th will form a single fleet by own
        return fleet+1;
    }
    //948. Bag of Tokens::goal is to maximize your total score
    //If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
    //If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score
    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int low=0,high=tokens.length-1;
        int score=0,maxSc=0;
        while (low<=high) {
            if (power >= tokens[low]) {
                power -= tokens[low++];
                score++;
                maxSc=Math.max(maxSc,score);
            }
            else if (score>0){
                power += tokens[high--];
                score--;
            }
            else break;
        }
        return maxSc;
    }
    public static int[] rearrangeBarcodes(int[] barcodes) {
        HashMap<Integer,Integer> freqMap=new HashMap<>();
        for (int barcode : barcodes)
            freqMap.put(barcode,freqMap.getOrDefault(barcode,0)+1);
        int n=barcodes.length;
        //pair[num,count];
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->b.y-a.y);
        for (Map.Entry<Integer, Integer> val : freqMap.entrySet()) {
            pq.add(new Pair(val.getKey(),val.getValue()));
        }
        System.out.println(pq);
        int[] res=new int[n];
        int index=0;
        while (pq.size()>1){
            Pair a=pq.poll();
            Pair b=pq.poll();
            res[index++]=a.x;
            res[index++]=b.x;
            if (a.y>1)
                pq.offer(new Pair(a.x,a.y-1));
            if (b.y>1)
                pq.offer(new Pair(b.x,b.y-1));
        }
        if (!pq.isEmpty())
            res[index]=pq.poll().x;
        return res;
    }
}
class car{
    int position;
    double arivTime;
    car(int p,double a){
        this.position=p;
        this.arivTime=a;
    }

    @Override
    public String toString() {
        return "["+position+","+arivTime+"]";
    }
}
