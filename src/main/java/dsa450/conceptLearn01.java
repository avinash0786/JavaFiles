package dsa450;

import java.util.*;

public class conceptLearn01 {
    conceptLearn01(){
        System.out.println("Concept learn java constructor called :)");
    }
    void myFunction(int a, long b){
        System.out.println("01 Sum of a+b: "+(a+b));
    }
    void myFunction(int a, float b){
        System.out.println("02 Sum of a+b: "+(a+b));
    }
    void sum(int a,long b){System.out.println("a method invoked");}
    void sum(long a,long b){System.out.println("b method invoked");}

    public static void main(String[] args) {

        Map<String, Integer> numberOfEmployees = new HashMap<>();
        numberOfEmployees.put("executives", 10);
        numberOfEmployees.put("human ressources", 32);
        numberOfEmployees.put("accounting", 12);
        numberOfEmployees.put("IT", 100);
// Output the smallest departement in terms of number of employees
        numberOfEmployees.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(1)
                .forEach(System.out::println); // outputs : executives=10

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(numberOfEmployees.entrySet());
        Collections.sort(entries, Map.Entry.comparingByValue());

//        conceptLearn01 ob=new conceptLearn01();
//        ob.myFunction(1,1);
//        ob.sum(2l,3);
////        new child01(7);
    }
}
class par01{
    par01(){
        System.out.println("Par01 const called");
    }
    par01(int a){
        System.out.println("Par01 const called value: "+a);
    }
}
class child01 extends par01{
    child01(){
        System.out.println("Child const called");
    }
    child01(int a){
        super(a);
        System.out.println("Child const called value: "+a);
    }
}