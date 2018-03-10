
package filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class Assignment_FajlSistem {

    public static void main(String[] args) throws InterruptedException, IOException {
        
        
        Scanner s = new Scanner(System.in);
        WelcomeScreen screen = new WelcomeScreen();
        HelpScreen help = new HelpScreen();
        
        boolean run = true;
        
        System.out.println(screen.toString());
        while(run) {
            
            String fileOperations = s.nextLine();
            
        
          switch(fileOperations) {
            case "HELP":
                    System.out.println(help.toString());
                    System.out.println("Choose an option");
                    break;
            case "LIST":
                    System.out.println("type in file path");
                    File path = new File(s.nextLine());
                    if (path.exists() && path.isDirectory()) {
                        String[] elements = path.list();
                        
                        for (int i = 0; i < elements.length; i++) {
                            System.out.println(elements[i]);
                        }
                    }
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "INFO":
                    System.out.println("type in file name");
                    File name = new File(s.nextLine());
                    if (name.exists()) {
                        System.out.println("Absolute path is: " + name.getAbsolutePath());
                        System.out.println("File name is: " + name.getName());
                        System.out.println("Free space: " + name.getFreeSpace());
                        Instant instantLastModified = Instant.ofEpochMilli(name.lastModified());
                        LocalDateTime dataTimeLastModified = LocalDateTime.ofInstant(instantLastModified, ZoneId.systemDefault());
                        System.out.println("Last modified: " + name.lastModified());
                    } else {
                        System.out.println("File does not exist");
                    }
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "CREATE_DIR":
                    System.out.println("type file path and name");
                    boolean bul = false;
                    File path2 = new File(s.nextLine());
                    
                    bul = path2.exists();
                    if (bul == false) {
                        System.out.println("Created a directory called: " + path2.getName());
                        path2.mkdir();
                    } else {
                        System.out.println("Directory already exists");
                    }
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "RENAME":
                    System.out.println("type in file path and name");
                    File path3 = new File(s.nextLine());
                    System.out.println("type in file path and new name");
                    File rename = new File(s.nextLine());
                    if (!path3.exists()) {
                        System.out.println("File doesen't exist");
                        return;
                    }
                    if (rename.exists()) {
                        System.out.println("File with desired name already exists");
                        return;
                    }
                    if (path3.renameTo(rename)) {
                        System.out.println("Rename succesfull");
                    } else {
                        System.out.println("Rename failed");
                    }
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "COPY":
                    System.out.println("type in file path and name");
                    File source = new File(s.nextLine());
                    System.out.println("type in new file path");
                    File dest = new File(s.nextLine());
                    copyFileUsingFileChannels(source, dest);
                    System.out.println("copy successfull");
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "MOVE":
                    System.out.println("type in file path and name");
                    try {
                        File move = new File(s.nextLine());
                        System.out.println("type in new file destination path");
                        if (move.renameTo(new File(s.nextLine() + move.getName()))) {
                            System.out.println("File is moved successfully");
                        } else {
                            System.out.println("File failed to move");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "DELETE":
                    System.out.println("type in file path and name");
                    File path4 = new File(s.nextLine());
                    if(path4.exists()) {
                        path4.delete();
                        System.out.println("File: " + path4.getName() + " deleted");
                    } else {
                        System.out.println("File doesen't exist");
                    }
                    System.out.println("Choose next option or EXIT to close the program");
                    break;
            case "EXIT":
                run = false;
                break;
            default:
            System.out.println("Invalid option selected");
            
        }
            
        }

    }
    
  private static void copyFileUsingFileChannels(File source, File dest) throws IOException {

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try {

            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
}
