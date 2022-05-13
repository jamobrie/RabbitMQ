import java.io.File;

//OS - anything at the privilege level of the process. This can include downloading tools, stealing credentials, changing credentials, stealing data, or deleting files depending on the privileges.
public class CommandInjection_asdasd {


    //A blind command injection is a command injection vulnerability where you do not receive visual clues about the vulnerability. To test for a blind command injection you need to use time based feedback. So submit a command that takes varying amounts of time to see if something is being executed.


    //ls - list files
    //touch - updates the access and modification times of a specified file to the current time. If the file doesn't exist it is created.

    //invalid input
    //a; ls;
    //test1; ping -i 1 -c 15 127.0.0.1     ---> Takes a long time so probably a command injection vulnerability
    //X; rm -r *   ---> Removes all contents

    //alice
    //monkey1
    public static void main(String[] args) throws Exception {
        createFile("Jimmy", "JimmyFile");
    }

    public static void createFile(String username, String filename) throws Exception{

        String filePath = System.getProperty("user.dir") + "/tmp/" + username + "/" + filename;
        System.out.println("Path to file is: " + filePath);
        File file = new File(filePath);

        try {
            file.createNewFile();
            System.out.println("File has been created successfully!");

            //Instead of this:
            Process p;
            File dir = new File(System.getProperty("user.dir"));
            p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "touch tmp/" + username + "/" + filename}, null, dir);

        } catch(Exception e) {
            System.out.println("failed to create file! ");
            throw e;
        }
    }

}


