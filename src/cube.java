//instance in mycls
public class cube
{

    int length;
    int breadth;
    int height;
    public int volume ()
    {
        return (length*breadth*height);
    }
    //constructor has same name as class, does no return anything
    cube()
    {
        System.out.println("We are in default constructor of cube");
        length=12;
        breadth=10;
        height=5;
    }

    cube(int l, int b, int h)
    {
        System.out.println("We are in constructor of cube");
        length=l;
        breadth=b;
        height=h;
    }
    /*public static void main(String [] args)
    {
        cube cube1=new cube();
        System.out.println("The volume of cube from class : "+cube1.volume());

        cube cube2=new cube(12,3,10);
        System.out.println("volume of cube by provided val:"+cube2.volume());
    }*/
}

