public class mycls
{
    public static void main(String[] args)
    {
        student avi=new student();//instance of student class; avi ->instance/object
        //avi.id=1;
        avi.setId(1);   //same as above using getter, settter
        //avi.name="Avinash Kumar";
        avi.setName("Avinash Kumar");
        //avi.age=19;
        avi.setAge(19);
        System.out.println(avi.name+" is "+avi.age+" years old");
        System.out.println(avi.getName()+" is "+avi.getAge()+" years old");//using getter

        //cube instance
        cube cube1=new cube();
        System.out.println("The volume of cube from class : "+cube1.volume());

        cube cube2=new cube(12,3,10);
        System.out.println("volume of cube by provided val:"+cube2.volume());

    }
}
