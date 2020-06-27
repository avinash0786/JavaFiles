package DS_A;

import java.util.*;

public class animal implements Comparable<animal>
{
    String name;
    int age;
    animal(String n,int a)
    {
        this.name=n;
        this.age=a;
    }

    @Override
    public String toString() {
        return "animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        animal animal = (animal) o;
        return age == animal.age &&
                name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    //---------------------------------------
    public static void main(String ...ss)
    {
        HashSet<animal> da=new HashSet<>();
        animal a1=new animal("dog",2);
        animal a2=new animal("dog",2);
        da.add(a1);
        da.add(new animal("cat",3));
        da.add(a2);
        da.add(new animal("cow",1));

        System.out.println(a1.equals(a2));    // false
        //we need to overide default equals meathod in animal class  then true

        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());

        for(animal a:da)
            System.out.println(a);         //in output uniqueness is not maintained in case of objects

        List<animal> sa=new ArrayList<>(da);
        System.out.println("before sort sa: "+sa);
        Collections.sort(sa);
        System.out.println("after sort sa: "+sa);       //sorted according to age
    }

    @Override
    public int compareTo(animal o) {        //to use sort function having object  we need to override compareTo method,
        // to get know that which object is greate
        if(this.age>o.age)         //we define the criteria as age to sort and compare
            return 1;
        else if(this.age<o.age)
            return -1;
        return 0;
    }
}
