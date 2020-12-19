public class customerRBI{

    public static void main(String[] args) {
        Rbi first=new PNB();
        System.out.println("PNB ROI: "+first.RateOfInterst());

        Rbi sec=new SBI();
        System.out.println("SBI ROI: "+sec.RateOfInterst());

        Rbi third=new HDFC();
        System.out.println("HDFC ROI: "+third.RateOfInterst());

        PNB pnbObject=new PNB();
        pnbObject.call();
        System.out.println(pnbObject.RateOfInterst());
    }
}
abstract class Rbi
{
    abstract float RateOfInterst();
    //abstract method : access specifier :DEFALUT

}
class PNB extends Rbi{

    @Override
    public float RateOfInterst() {
        return 8.26f;
    }
    void call(){
        System.out.println("PNB is here at your service");
    }
    static void secondCall(){
        System.out.println("Welcome PNB second");
    }
}
class SBI extends Rbi{

    @Override
    protected float RateOfInterst() {
        return 10.56f;
    }
}
class HDFC extends Rbi{

    @Override
    float RateOfInterst() {
        return 6.49f;
    }
    //private will restrict the acess and will restrict from default
    /*
    java: RateOfInterst() in HDFC cannot override RateOfInterst() in Rbi
  attempting to assign weaker access privileges; was package
     */
}


/*

WAP to create a class RBI which will
--have one Abstract method
float roi();
--it also have one non abstract method as show()
--now create three subclass of Rbi as KVB,PNB,SBI
--each class will return there own rate of interest
--now create a class Customer which have main method
 to call all the methods of sbi,pnb,kvb
 */