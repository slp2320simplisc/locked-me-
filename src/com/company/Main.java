package com.company;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SocketHandler;
import java.util.stream.Collectors;

public class Main {

    public static String lastName;
    public static  String firstName;
    public static  String fileName;



    public  static  void  addUser()
    {
        Scanner keyboard = new Scanner(System.in);


        System.out.println("Enter file name you want add user to or Enter NEW for new file");
        fileName = keyboard.nextLine();

        if(fileName.matches("NEW"))
        {
            System.out.println("Enter file name you want to create ");
            fileName = keyboard.nextLine();
            File file = new File("src/com/company/FilesNames"+fileName); //initialize File object and passing path as argument
            boolean result;
            try
            {
                result = file.createNewFile();  //creates a new file
                if(result)      // test if successfully created a new file
                {
                    System.out.println("file created "+file.getCanonicalPath()); //returns the path string
                }
                else
                {
                    System.out.println("File already exist at location: "+file.getCanonicalPath());
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();    //prints exception if any
            }

        }else
        {

            File file1 = new File("src/com/company/FilesNames"+fileName);
            if(file1.exists()){
                System.out.println(file1+ " Exists");
            }else{
                System.out.println(file1 + " Does not exists");

            }

            System.out.println("-----------------------------------------------------------------");
        }

        try
        {

            FileOutputStream fos=new FileOutputStream("src/com/company/FilesNames"+fileName, true);  // true for append mode
            System.out.print("Enter first name  :");
            firstName =keyboard.nextLine();
            System.out.println("Enter last name :");
            lastName = keyboard.nextLine();

            System.out.println(fileName);

            String line = firstName+" "+lastName+"\n";

            byte[] b= line.getBytes();
            fos.write(b);           //writes bytes into file
            fos.close();            //close the file
            System.out.println("file saved.");
            System.out.println("-------------------successful operation----------------------");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public static void deleteUser( String firstName) throws IOException {

        Scanner keyboard = new Scanner(System.in);
       String lineContent = firstName;
       String record;

        System.out.println("Enter file name you want to Delete ");
        fileName = keyboard.nextLine();

        File file1 = new File("src/com/company/FilesNames"+fileName);
        if(file1.exists())
        {


            File tempDB = new File("src/com/company/FilesNames/deleted.txt");
            File db = new File("src/com/company/FilesNames/"+fileName);
            BufferedReader br = new BufferedReader( new FileReader( db ) );
            BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );

            while( ( record = br.readLine() ) != null ) {
                if( record.contains(firstName) )
                    continue;

                bw.write(record);
                bw.flush();
                bw.newLine();
            }

            br.close();
            bw.close();
            db.delete();
            tempDB.renameTo(db);

            System.out.println("-------------------successful operation----------------------");

        }else{
            System.out.println(file1 + " Does not exists");

        }

    }
    public static void searchUser(String firstName ) throws IOException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter file name you want to Delete ");
        fileName = keyboard.nextLine();

        File file1 = new File("src/com/company/FilesNames"+fileName);
        if(file1.exists())
        {
            File f1=new File("src/com/company/FilesNames/"+fileName); //Creation of File Descriptor for input file
            String[] words=null;  //Intialize the word Array
            FileReader fr = new FileReader(f1);  //Creation of File Reader object
            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
            String s="";

            int count=0;   //Intialize the word to zero
            while((s=br.readLine())!=null)   //Reading Content from the file
            {
                words=s.split(" ");  //Split the word using space
                for (String word : words)
                {
                    if (word.equals(firstName))   //Search for the given word
                    {
                        count++;    //If Present increase the count by one
                    }
                }
            }
            if(count!=0)  //Check for count not equal to zero
            {
                System.out.println("The "+ firstName+" is found in this file "+fileName);
            }
            else
            {
                System.out.println("The "+ firstName+" not present in the file");
            }

            fr.close();

            System.out.println("-------------------successful operation----------------------");

        }else{
            System.out.println(file1 + " Does not exists");

        }
    }

    public static void displayNames()
    {
        // Creates an array in which we will store the names of files and directories
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File("src/com/company/FilesNames");

        // Populates the array with names of files and directories
        pathnames = f.list();


        if(pathnames.length >0) {
            for (String pathname : pathnames) {
                // Print the names of files and directories
                System.out.println(pathname);
            }

            System.out.println("-------------------successful operation----------------------");
        }else
        {
            System.out.println("------------------------Folder is empty------------------------------");
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);

        int option;

        System.out.println("1. To Display files name ");
        System.out.println("2. Details of the user interface");
        System.out.println("3. Close the application");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Enter your option :");
        option = keyboard.nextInt();
        System.out.println("---------------------------------------------------------------------");


    while (true) {


        switch (option) {
            case 1:
                displayNames();
                break;
            case 2:
                System.out.println("1. Add user ");
                System.out.println("2. Delete user ");
                System.out.println("3. Search User");
                String optio;
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Enter your option :");
                optio = keyboard.next();
                System.out.println("---------------------------------------------------------------------");

                switch (optio) {
                    case "1":
                        addUser();
                        break;
                    case "2":
                        System.out.println("Enter first name you want to delete: ");
                        String firstNam = keyboard.next();


                        deleteUser(firstNam);
                        break;
                    case "3":
                        System.out.println("Enter first name your are looking for :");
                        String firstNa = keyboard.next();

                        searchUser(firstNa);
                        break;
                    default:
                        System.out.println("You Option does not exist");
                        break;
                }

                break;
            case 3:
                System.exit(0);
                break;


        }


        System.out.println("1. To Display files name ");
        System.out.println("2. Details of the user interface");
        System.out.println("3. Close the application");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Enter your option :");
        option = keyboard.nextInt();
        System.out.println("---------------------------------------------------------------------");

    }
    }
}
