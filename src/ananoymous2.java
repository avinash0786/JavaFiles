interface Age2
{
    int x=29;
    String name="Rahul";
    void getAge();
    void getName();
}
public class ananoymous2
{
    public static void main(String ...aa)
    {
        Age2 ob2=new Age2() {
            @Override
            public void getAge() {
                System.out.println("Age: "+x);
            }

            @Override
            public void getName() {
                System.out.println("Name: "+name);
            }
        };
        ob2.getAge();
        ob2.getName();
    }
}
