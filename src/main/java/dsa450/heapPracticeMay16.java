package dsa450;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

//heap, BFS, DFS
public class heapPracticeMay16 {
    public static void main(String[] args) {
        int[][] stat=new int[][]{
                {10,60},{20,30},{30,30},{60,40}
        };
//        System.out.println("Min fuel refill: "+minRefuelStops(100,10,stat));
        System.out.println("min refule ans: "+minRefuelStopsSoln(100,10,stat));
    }
    //given starting fuel and destination, and station location and fule
    //find min no of stops to refuel required to reach the destination
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        dest=target;
        stn=stations;
        System.out.println(Arrays.deepToString(stations));
        return refuelStops(0,startFuel);
    }
    static int dest;
    static int[][] stn;
    private static int refuelStops(int stnIdx,int fuel){
        if (fuel>=dest)
            return 1;
        if (stnIdx>=stn.length)
            return 0;
        if (fuel>stn[stnIdx][0]){
            int refuel=1+refuelStops(stnIdx+1,stn[stnIdx][0]-fuel+stn[stnIdx][1]);
            int dontRefuel=refuelStops(stnIdx+1,fuel);
            return Math.min(refuel,dontRefuel);
        }
        else if (fuel<stn[stnIdx][0]){
            return 0;
        }
        else
            return 1+refuelStops(stnIdx+1,stn[stnIdx][1]);
    }
    public static int minRefuelStopsSoln(int target, int startFuel, int[][] s) {
        System.out.println(Arrays.deepToString(s));
        long[] dp = new long[s.length + 1];
        dp[0] = startFuel;
        for (int i = 0; i < s.length; ++i) {
            for (int t = i; t >= 0 && dp[t] >= s[i][0]; --t) {
                System.out.println("t "+t+" val: "+(dp[t] + s[i][1]));
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + s[i][1]);
                System.out.println(Arrays.toString(dp));
            }
            System.out.println();
        }

        for (int t = 0; t <= s.length; ++t)
            if (dp[t] >= target) return t;
        return -1;
    }
    //k th smallest pair with min abs distance
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        int maxDif=nums[n-1]-nums[0];
        int l=0;    //cannot take min dif as nums[1]-nums[0] bcoz there could be repeating/same element so
        //min diff will be 0 eg [9,9] diff is 0
        int r=maxDif;
        while (l<r){
            int mid=(l+r)/2;
            if (possibleDiff(mid,nums,k))
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }
    private static boolean possibleDiff(int mid,int[]arr, int k){
        int n=arr.length;
        int j=1;
        int total=0;
        for (int i = 0; i < n; i++) {
            while (j<n && arr[j]-arr[i]<=mid) j++;
            j--;
            int x=j-i;
            total+=x;
        }
        return total>=k;
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n=buildings.length;
        buildingPoints[] bulds=new buildingPoints[n*2];
        int idx=0;
        for (int i = 0; i < n; i++) {
            bulds[idx]=new buildingPoints(buildings[i][0],buildings[i][2],true);
            bulds[idx+1]=new buildingPoints(buildings[i][1],buildings[i][2],false);
            idx+=2;
        }
        Arrays.sort(bulds);
        TreeMap<Integer,Integer> pq=new TreeMap<>();        //height,count
        pq.put(0,1);
        int prevMaxHeight=0;
        List<List<Integer>> result=new ArrayList<>();
        for (buildingPoints point : bulds) {
            //if it is start of building then add the height to map. If height already exists then increment
            //the value
            if (point.isStart){
                pq.compute(point.height,(key,val)->{
                    if (val!=null)
                        return val+1;
                    return 1;
                });
            }
            else {//if it is end of building then decrement or remove the height from map.
                pq.compute(point.height,(key,val)->{
                    if (val==1)
                        return null;
                    return val-1;
                });
            }
            //peek the current height after addition or removal of building x.
            int curMaxHeight=pq.lastKey();
            //if height changes from previous height then this building x becomes critcal x.
            // So add it to the result.
            if (prevMaxHeight!=curMaxHeight){
                result.add(new ArrayList<>(Arrays.asList(point.x,curMaxHeight)));
                prevMaxHeight=curMaxHeight;
            }
        }
        return result;
    }
    public static int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }
}

class buildingPoints implements Comparable<buildingPoints>{
    int x;
    int height;
    boolean isStart;
    public buildingPoints(int x,int h,boolean isStart){
        this.x=x;
        this.height=h;
        this.isStart=isStart;
    }

    @Override
    //first compare by x.
    //If they are same then use this logic
    //if two starts are compared then higher height building should be picked first
    //if two ends are compared then lower height building should be picked first
    //if one start and end is compared then start should appear before end
    public int compareTo(@NotNull buildingPoints o) {
        if (this.x!=o.x)
            return this.x-o.x;
        else{
            if (this.isStart && o.isStart)
                return this.height-o.height;
            else if (!isStart && !o.isStart)
                return this.height-o.height;
            else
                return 1;
        }
    }
}