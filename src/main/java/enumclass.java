enum week
{
    Monday,tuesday,wednesday,THURSDAY ,FRIDAY,SATURDAY,SUNDAY;
}
public class enumclass
{
    public static void main(String ...xx) {
        //week[] w=week.values();
        //week m=week.tuesday;
        for (week w1 : week.values())
            System.out.println(w1 + " " + w1.ordinal());//ordinal to give index of valu
        week w = week.valueOf("Monday");
        System.out.println("Ans: " + w);
    }
}
/*
public enum Directions{
  EAST ("E"),
  WEST ("W"),
  NORTH ("N"),
  SOUTH ("S")
  ;
 Important Note: Must have semicolon at
 the end when there is a enum field or method

private final String shortCode;

    Directions(String code) {
        this.shortCode = code;
    }

    public String getDirectionCode() {
        return this.shortCode;
    }
}
public class EnumDemo
{
    public static void main(String[] args) {
        Directions dir = Directions.SOUTH;
        System.out.println(dir.getDirectionCode());
        Directions dir2 = Directions.EAST;
        System.out.println(dir2.getDirectionCode());
    }
}
 */

