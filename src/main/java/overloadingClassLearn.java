public class overloadingClassLearn
{
    /*
    In java methodOverloading
    the method with least number of  implicit type casting will be invocked
    **if 2 method have same number of implicit type casting, then ERROR: ambiguous error will be raised

     */
    public static void main(String[] args) {
        System.out.println(max(2.3,3));
        System.out.println(1<<10);
    }
    public static double max(int n1, double n2){
        return 2d;
    }
    public static double max(double n1, int n2){
        return 3d;
    }
}
