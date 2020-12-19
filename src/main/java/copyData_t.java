import java.io.*;

public class copyData_t
{   //args[0] source file
    //args[1] target file
    public static void main(String [] args) throws IOException
    {
        if(args.length!=2)
        {
            System.out.println("Source and destination dosen't specified!");
            System.exit(1);
        }
        //Check if source file exists
        File sourceFile =new File(args[0]);
        if(!sourceFile.exists())
        {
            System.out.println("Source file: "+args[0]+" Does not exists!");
            System.exit(2);
        }
        //check target file exist
        File targetFile=new File(args[1]);
        if(targetFile.exists())
        {
            System.out.println("Target file "+args[1]+" already exist!");
            System.exit(3);
        }
        BufferedInputStream inp=new BufferedInputStream(new FileInputStream(sourceFile));
        BufferedOutputStream oup=new BufferedOutputStream(new FileOutputStream(targetFile));
        // read and write
        int r, nomOfBytesCopied=0;
        while((r=inp.read())!=-1)
        {
            oup.write((byte)r);
            nomOfBytesCopied++;
        }
        inp.close();
        oup.close();
        System.out.println("NO of Bytes copied: "+nomOfBytesCopied);




    }
}
