public class file_t
{
    public static void main(String ...a)
    {
        java.io.File file=new java.io.File("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/data.txt");
        System.out.println("File exists: "+file.exists());
        System.out.println("Absolute path: "+file.getAbsolutePath());
        System.out.println("Length of file: "+file.length()+" bytes");
        System.out.println("Readable: "+file.canRead());
        System.out.println("Writable: "+file.canWrite());
        System.out.println("Is directory: "+file.isDirectory());
        System.out.println("Last modified: "+file.lastModified());
    }
}
/*
--File class contains the methods for obtaining the properties of a  file/directory
and for remaining and deleting a file/directory.
--To permanently store the data created in a program , we need to save them in a file on a disk,
or the permanent storage device, because data stored in program are temporary, they are lost when the
program terminates.
--A relative file name is in relation to the current working directory.
--THe file class does not contain the method to read and write file contents,
** Constructing a File instance for any file name regardless whether it exists or not.
We can create a File instance for any file name regardless whether it exists or not.
We can invoke the exists() method on a File instance to check whether the file exixts.



*/