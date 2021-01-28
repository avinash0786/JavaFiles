import java.util.*;

public class mapEntryLearn {
    public static void main(String[] args) {
        Map<String,Integer> map=new HashMap<>();
        map.put("Apple",1);
        map.put("Mango",2);
        map.put("Grapes",3);
        map.put("Pinapple",4);
        map.put("Guava",5);
        System.out.println(map);

        Map<String,Integer> map1=new LinkedHashMap<>();
        map1.put("Apple",1);
        map1.put("Mango",2);
        map1.put("Grapes",3);
        map1.put("Pinapple",4);
        map1.put("Guava",5);
        System.out.println(map1);

        Map<String,Integer> map2=new TreeMap<>();
        map2.put("Apple",1);
        map2.put("Mango",2);
        map2.put("Grapes",3);
        map2.put("Pinapple",4);
        map2.put("Guava",5);
        System.out.println(map2);

        Set<Map.Entry<String,Integer>> ent=map1.entrySet();
        for (Map.Entry<String, Integer> et : ent) {
            System.out.println(et.getKey()+" : "+et.getValue());
        }
        //sorting according to value
//        Collections.sort(map2,(o1,o2)->(o1.getValue().compareTo(o2.getValue())));
    }
}
