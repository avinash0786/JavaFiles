package restart;

import java.io.*;

public class serializeDesearialize {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName="serialize.txt";
        PersonSerialize person=new PersonSerialize(1,"Tarun rana");


        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(fileName));
        try {
            outputStream.writeObject(person);
            System.out.println("success");
        }catch (Exception err){
            System.out.println(err);
        }finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }
        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(fileName));
        try{
            PersonSerialize nayaPerson=(PersonSerialize) inputStream.readObject();
            System.out.println(nayaPerson.id+" "+ nayaPerson.name);
        }catch (Exception err){
            System.out.println(err);
        }finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
    }

}
class PersonSerialize implements Serializable{
    int id=0;
    String name="Default Name";
    public PersonSerialize(int id,String name){
        this.id=id;
        this.name=name;
    }
}