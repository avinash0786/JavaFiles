import java.util.*;
import java.util.stream.Collectors;

public class collectionsJavaLearn {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);
        map.compute("peter", (k,v)->v+50); //{john=20, paul=30, peter=90} //Increase the value

        map.computeIfPresent("kelly", (k,v)->v+10); //{john=20, paul=30, peter=40} //kelly not present
        map.computeIfPresent("peter", (k,v)->v+10); //{john=20, paul=30, peter=50} // peter present, so

        map.computeIfAbsent("kelly", k->map.get("john")+10); //{john=20, paul=30, peter=40, kelly=30}
        map.computeIfAbsent("peter", k->map.get("john")+10); //{john=20, paul=30, peter=40, kelly=30}
//peter already present
        List<String> fruits=new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Guava");
        Iterator<String> fruitIterator=fruits.iterator();
//        fruits.add("seb");
        //Adding element to the list after initializing an iterator will throw a Java.util.ConcurrentModificationException

//        while (fruitIterator.hasNext()){
//            System.out.println(fruitIterator.next());
//        }
        List<String> filteredApple=fruits.stream().filter(p->!"Apple".equals(p)).collect(Collectors.toList());
//        System.out.println(filteredApple);

        List<String> data= Arrays.asList("as","sw","re","qw");
//        System.out.println(data);
        List<String> list=new ArrayList<>(data);
        Set<String> set1=new HashSet<>(data);
        SortedSet<String> set2=new TreeSet<>(data);
        Set<String> set3=new LinkedHashSet<>(data);
//        System.out.println(list);
//        System.out.println(set1);
//        System.out.println(set2);
//        System.out.println(set3);

//        list.forEach(System.out::println);
        ListIterator<String> listIterator=list.listIterator();  //Supports both forward and backward traversal
//        while (listIterator.hasNext()){
//            System.out.println(listIterator.next());
//        }
//        while (listIterator.hasPrevious()){
//            System.out.println(listIterator.previous());
//        }
        Map<Integer,String> names=new HashMap<>();
        names.put(2,"Sam");
        names.put(1,"Jhon");
        names.put(5,"Alex");
//        names.forEach((key,val)-> System.out.println(key+" : "+val));
//
//        for (Map.Entry<Integer,String> entry:names.entrySet()){
//            System.out.println(entry.getKey()+" : "+entry.getValue());
//        }
//        Iterator entries=names.entrySet().iterator();
//        while (entries.hasNext()){
//            Map.Entry entry=(Map.Entry) entries.next();
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
        myStudent s1=new myStudent(2,"mm");
        myStudent s2=new myStudent(1,"bb");
        myStudent s3=new myStudent(4,"aa");
        myStudent s4=new myStudent(12,"cc");
        List<myStudent> students=new ArrayList<>();
        students.add(s1);
        students.add(s2);students.add(s3);students.add(s4);
        System.out.println(students);

//        Collections.sort(students);//[clark: 12, alake: 4, mike: 2, Blex: 1]
//
//        Collections.sort(students, new Comparator<myStudent>() {    //[aa: 4, bb: 1, cc: 12, mm: 2]
//            @Override
//            public int compare(myStudent o1, myStudent o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });

//        Collections.sort(students,(left,right)->left.name.compareTo(right.name));   //[aa: 4, bb: 1, cc: 12, mm: 2]
        students.sort((left,right)->left.name.compareTo(right.name));

        System.out.println(students);

        Set<String> set5=new TreeSet<>(String::compareTo);
//        Set<String> set5=new TreeSet<>((ls,rs)->ls.compareTo(rs));
        set5.add("Baba");
        set5.add("caca");
        set5.add("qwqwq");
        set5.add("zaza");
        System.out.println(set5);   //[Baba, caca, qwqwq, zaza]

        Map<String,Integer> map2=new HashMap<>();
        map2.put("Jhon",20);
        map2.put("Paul",30);
        map2.put("King",10);
        map2.put("Shane",60);
        System.out.println(map2);
        map2.computeIfAbsent("King",v->map2.get("Jhon")+5); //Computes only when the key is not present
        map2.computeIfAbsent("Queen",v->map2.get("Jhon")+5);
        System.out.println(map2);
//        Collections.sort(map2,(o1,o2)->o1.compareTo(o2));
        map2.computeIfPresent("Shane",(k,v)->map2.get("Jhon")+v);   //Computes only if the key is present
        System.out.println(map2);
        map2.compute("King",(k,v)->v+25);
        System.out.println(map2);
        //ITERATING THROUGH KEY AND VALUE TOGETHER
        for (Map.Entry<String,Integer> entry:map2.entrySet()){
            System.out.printf("%s : %dn\n",entry.getKey(),entry.getValue());
        }
    }
    TreeSet<Person> treeSet = new TreeSet<>(Comparator.comparingInt(Person::getId));

//    TreeSet<Person> treeSet = new TreeSet<>((personA, personB) -> Integer.compare(personA.getId(),
//            personB.getId()));
    TreeSet<Person> treeSet1 = new TreeSet<>(Comparator.comparingInt(Person::getId));

//    TreeSet<Person> treeSet1 = new TreeSet<>((personA, personB) -> Integer.compare(personA.getId(), personB.getId()));


//    TreeSet<Person> treeSet1 = new TreeSet<>(new Comparator<Person>(){
//        @Override
//        public int compare(Person personA, Person personB) {
//            return Integer.compare(personA.getId(), personB.getId());
//        }
//    });

    /*
    //Lambda
    Collections.sort(people, (p1, p2) -> { //Legal
        //Method code....
    });

    Comparator default methods
    Furthermore, there are interesting default methods on the Comparator interface for building comparators : the
    following builds a comparator comparing by lastName and then firstName.

    Collections.sort(people, Comparator.comparing(Person::getLastName)
                                .thenComparing(Person::getFirstName));

     Inversing the order of a comparator
    Any comparator can also easily be reversed using the reversedMethod which will change ascending order to
    descending.

    --The Comparable<T> interface requires one method:

    public interface Comparable<T> {
        public int compareTo(T other);
    }
    --And the Comparator<T> interface requires one method:

    public interface Comparator<T> {
        public int compare(T t1, T t2);
    }
These two methods do essentially the same thing, with one minor diﬀerence: compareTo compares this to other,
whereas compare compares t1 to t2, not caring at all about this.

//-- explicit sorting, define sort on another property here goes with name
            Collections.sort(pList, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            System.out.println(pList);

  Comparator.comparing(Person::getName)
This creates a comparator for the class Person that uses this person name as the comparison source. Also it is
possible to use method version to compare long, int and double. For example:
    Comparator.comparingInt(Person::getAge)

Reversed order
To create a comparator that imposes the reverse ordering use reversed() method:
    Comparator.comparing(Person::getName).reversed()

Chain of comparators
    Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)
This will create a comparator that ﬁrs compares with last name then compares with ﬁrst name. You can chain as
many comparators as you want.
     */
}
class myStudent implements Comparable<myStudent>{
    public final int id;
    String name;
    myStudent(int i,String n){
        this.id=i;
        this.name=n;
    }
    public String toString(){return String.format("%s: %d",name,id);}

    public int compareTo( myStudent o) {
        return o.id-id;
    }
}
class Person implements Comparable<Person> {
    private int id;
    private String firstName, lastName;
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    //... Constuctors, getters, setters and various methods
    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.id, o.id); //Compare by id
    }
}
/*
The collections framework in java.util provides a number of generic classes for set of data
with functionality that can't be provided by regular arrays.
--Collection framework contains interfaces for Collections<0>, with sub-interfaces
List<O>
Set<O>
Map<K,V>
--Collections are the root interface and are being implemented by many other collection framework.
--While using an Iterator a ConcurrentModificationException is thrown when the modCount of the List is changed
from when the Iterator was created. This could have happened in the same thread or in a multi-threaded
application sharing the same list.
**A modCount is an int variable which counts the number of times this list has been structurally modiﬁed. A structural
change essentially means an add() or remove() operation being invoked on Collection object (changes made by
Iterator are not counted). When the Iterator is created, it stores this modCount and on every iteration of the List
checks if the current modCount is same as and when the Iterator was created. If there is a change in the modCount
value it throws a ConcurrentModificationException.

 */