public class learnFebBook
{
    public static void main(String[] args) {
        students s1=new students("Apple",23);
    }
}

class school{
    String name;
    int noStudents;
    school(){}
    school(String s , int n){
        name=s;
        noStudents=n;
        System.out.println("School initalized");
    }
}

class students extends school{
    String name;
    int rollNO;
    students(String na, int rol){
        super(na,rol);
        name=na;
        rollNO=rol;
        System.out.println("Student intialized");
    }
}

class inmmutableArray{
    private final int[] array;

    inmmutableArray(int[] array) {
        this.array = array.clone();
    }
    int[] getValue(){return array.clone();}
}