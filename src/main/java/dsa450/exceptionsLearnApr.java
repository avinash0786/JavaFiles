package dsa450;

public class exceptionsLearnApr {
    public static void throwErrFn(int a) throws customCheckedExc {
        System.out.println("Fn throwing exeception");
        if ((a&1)==0)
            throw new customCheckedExc();
        else
            throw new customUncheckedExc();
    }
    public static void main(String[] args) {

        try{
            throwErrFn(2);
            int count=0;
//            while (count<10000){
//                if (count==10)
////                    throw new RuntimeException("My runtime Exception");
//                count++;
//            }
        }
        catch (RuntimeException rn){
            System.out.println("Runtime exc: "+rn);
        }
        catch (Exception err){
//            err.printStackTrace();
            System.out.println("General exc: "+err);
        }
        finally {
            System.out.println("Finally run");
        }
    }
}

class appleIsRed extends myAbstract implements myInterf{

    @Override
    void orign() {
        System.out.println("abstract fn inplementation in apples is red");
    }

    @Override
    void absFn() {
        System.out.println("Abstrach seconf fn override imp");
//        super.absFn();
    }

    @Override
    public void myInfFn() {
        System.out.println("My intef fn implementation");
    }

    @Override
    public void orinalInterfFn() {
        System.out.println("Orign interface method implem");
    }

}
class customCheckedExc extends Exception{
    customCheckedExc(){
        System.out.println("Custom CHECKED exeption");
    }
}
class customUncheckedExc extends RuntimeException{
    customUncheckedExc(){
        System.out.println("This is custom UN-CHECKED exception");
    }
}
abstract class myAbstract{
    void absFn(){
        System.out.println("This is my abstract function");
    }
    abstract void orign();
}
interface myInterf{
    default void myInfFn(){
        System.out.println("my default interface function");
    }
    void orinalInterfFn();
}

/*
the most specific catch blocks (in terms of the exception types) should appear
first, and the most general ones should be last.

 */
