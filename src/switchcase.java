public class switchcase
{
    public static void main(String [] args)
    {
        int score=100;
        String s="Abb";
        //byte , short, int , char, string also
        switch (s)
        {
            case "b":
                System.out.println("Excillent");
                break;
            case "A":
                System.out.println("Outstanding");
                break;
            case "Abb":
                System.out.println("Good");
                break;
            default:
                System.out.println("The grades are not defined !");
               // break;
        }
    }
}
