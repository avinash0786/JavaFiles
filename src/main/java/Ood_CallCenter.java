import java.util.List;

public class Ood_CallCenter {
    public static void main(String[] args) {

    }
}

class CallHandler{
    private final int LEVEL=3;

    private final int Respondent=10;
    private final int Manager=5;
    private final int Director=2;

    //List of employee by LEVEL 0-Respondent 1-Manager 2- Director
    List<List<Employee>> employeeLevel;

    //Queue if emoloyee is engaged
    List<List<Call>> callQueue;

    public CallHandler(){
        System.out.println("Call Handling System Started ...");
    }


    boolean respondentAvail(){
        if(employeeLevel.get(0).size()>Respondent)
            return false;
        return true;
    }
    boolean managerAvail(){
        if(employeeLevel.get(1).size()>Manager)
            return false;
        return true;
    }
    boolean directorAvail(){
        if(employeeLevel.get(2).size()>Director)
            return false;
        return true;
    }
}
class Call{

}
enum  Rank{
RESPONDER, MANAGER, DIRECTOR
}
abstract class Employee{
    private Call currentCall=null;
    protected Rank rank;
    public void recieveCall(Call call){
        currentCall=call;
        System.out.println("Call Assigned");
    }
}
class Respondent extends Employee{
    public Respondent(){
        rank=Rank.RESPONDER;
    }
}
class Manager extends Employee{
    public Manager(){
        rank=Rank.MANAGER;
    }
}
class Director extends Employee{
    public Director(){
        rank=Rank.DIRECTOR;
    }
}



//  classes to be included
/*
Problem statement :
Design a call center system having 3 types of representative :
1. Respondent
2. Manager
3. Director
an incoming call must first allocated to respondent , cant handle the call then move upward s
design the required data structure to implement the same
------------------------
CallHandler
call
Emoployee
-director
-manager
-respondent

 */
