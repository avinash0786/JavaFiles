package dsa450;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test01May10 {
    public static void main(String[] args) {

    }
    public static List<Integer> getMovies(int flightDuration, List<Integer> movieDurations) {
        List<Integer> copy=new ArrayList<>(movieDurations);
        Collections.sort(copy);
        System.out.println(movieDurations);
        int start=0;
        int end=movieDurations.size()-1;
        int reqDuration=flightDuration-30;
        System.out.println("Start: "+start+" end: "+end);
        while (start<end){
            int curSum=copy.get(start)+copy.get(end);
            if (curSum>reqDuration){
                end--;
            }
            else if(curSum<reqDuration)
                start++;
            else {
                int index01=movieDurations.indexOf(copy.get(start));
                int index02=movieDurations.indexOf(copy.get(end));
                return new ArrayList<>(Arrays.asList(index01,index02));
            }
        }
        return new ArrayList<>(Arrays.asList(-1,-1));
    }
}
