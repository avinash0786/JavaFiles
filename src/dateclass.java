import java.time.LocalDate;
import java.time.Month;

public class dateclass
{
    public static void main(String ...f)
    {
        LocalDate ldt=LocalDate.now();
        System.out.println(ldt);
        System.out.println("Get Year: "+ldt.getYear());
        System.out.println("Get month: "+ldt.getMonth());
        System.out.println("Get Day of year: "+ldt.getDayOfYear());
        System.out.println("Get Day of month: "+ldt.getDayOfMonth());
        System.out.println("Get Day of week: "+ldt.getDayOfWeek());
        System.out.println("Is Leap Year: "+ldt.isLeapYear());

        ldt=LocalDate.of(2020, Month.FEBRUARY,10);
        System.out.println(ldt);
        ldt=LocalDate.of(2020,2,10);
        System.out.println(ldt);
        System.out.println("Get Day of week: "+ldt.getDayOfWeek());
        ldt=LocalDate.parse("2018-02-09");
        System.out.println(ldt);
    }
}
