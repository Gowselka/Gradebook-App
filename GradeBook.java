
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class GradeBook 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        boolean finished = false;
        Scanner console = new Scanner(System.in);
        String menu = "";
        int low = 1;
        int high = 4;
        int menuChoice = 0;
        
        final int CREATE = 1;        
        final int ADD_GRADES = 2;
        final int DISPLAY = 3;
        final int QUIT = 4;
  
            // build the menu
            menu = "+++++++++++++++++++++++++++++++++++++++++";
            menu += "\n\tCreate a Class Set";
            menu += "\n\tAdd Grades to a Set";
            menu += "\n\tDisplay a Class Report";
            menu += "\n\tQuit the Program";
            menu += "\n+++++++++++++++++++++++++++++++++++++++++";
            menu += "\nGet choice:";        
        
        
        do
        {

            // display menu
            // get the choice
            menuChoice = getRangedInt(console, menu, low, high);
            // execute the choice
            switch (menuChoice)
            {
                case CREATE:
                    createClassSet();
                    System.out.println("Create option chosen.");
                    break;
                case ADD_GRADES:
                    System.out.println("Add Grades option chosen.");
                    break;
                case DISPLAY:
                    System.out.println("Display option chosen.");
                    break;
                case QUIT:
                    System.out.println("Thanks for using the Grade Book.");
                    System.exit(0);
                default:
                    System.out.println("Error unknown menu choice!");
                    System.exit(0);
            }
            
        }while(!finished);
    }
    
    
    static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int value = low - 1;    
        String trash = "";
        // do this 
        do
        {
            // show the prompt
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            // check for a valid number
            if(pipe.hasNextInt())
            {
               //if so input it
               value = pipe.nextInt();
               trash = pipe.nextLine();
            }
            else
            {
               // if not read trash and error msg
               trash = pipe.nextLine();
               System.out.println("You must enter a valid int: " + trash);
            }
        // until the input value is in range
        }while(value < low || value > high);
        return value;
    }

    private static void createClassSet() 
    {
        String classFileName = "";
        String studentName = "";
        boolean doneNameInput = false;
        File classFile;
        PrintWriter out;
        Scanner in = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        
        System.out.print("Enter the name of the class file [1st grade math] :");
        
        classFileName = in.nextLine();
        
        try
        {
           // loop and get the student names into an ArrayList
            do
            {
                System.out.print("Enter the student name [First Last]: ");
                studentName = in.nextLine();
                names.add(studentName);
                // Prompt the user to continue
                if(!getYNConfirm(in, "Enter Another Student"))
                {
                    doneNameInput = true;
                }
            }while(!doneNameInput);
        
            classFile = new File(classFileName + ".txt");
            out = new PrintWriter(classFile);
            
            // Write the header
            out.println(classFileName);
            // Write student names to file
            for(String nm : names)
            {
                out.println(nm);
            }
            
           // write Student names ArrayList to file
            out.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error, could not create output file!");
            System.exit(0);
        }    
        catch (IOException ex)
        {
            ex.getStackTrace();
            System.exit(0);
        }
        
    }
    
    static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String response = "";
        boolean result = false;
        do
        {
           // display prompt
           System.out.print(prompt + " [Y/N]:"); 
           // input response
           response = pipe.nextLine();
           
        }while(!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N"));
        
        if(response.equalsIgnoreCase("Y"))
        {
            result = true;
        }    
        
        return result;
    }
}
