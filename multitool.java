// multitool.java
// Richard Morsching

import java.util.*;

public class multitool {
    public static void main(String[] args){
        int choice = 0;
        Scanner in = new Scanner(System.in); 
        boolean needChoice = true;
        // initializes choice value and the bool determines if user input is needed or not. Default is needed

        System.out.println("Welcome to the Multitool shell!\nPlease select a tool (1-6): ");
        System.out.println();
        while(needChoice){
            // start of a really big, fun while loop which allows rerunning the program after usage of a tool. 
            // not sure if this is the best way. But it works well enough
            Boolean needChoiceVar = true;
            // for a nested while loop. asks the same question as needChoice.
            System.out.println("1. Word Game\n2. Falcon Dollar Budgeter\n3. Number Roller\n4. To-Do List\n5. Calculator\n6. Exit");
            System.out.println();
            
            while(needChoiceVar){
                System.out.print("Choice: ");
                String temp = in.nextLine();
                try{
                    choice = Integer.parseInt(temp);
                    // attempt to get int value for choice
                    if((choice < 1)||(choice>6))
                        System.out.println("Invalid choice, try again.");
                        // if response is outside used int range, deny it
                    else
                        needChoiceVar = false;
                        // if the response is valid, keep going out of the loop
                }catch(NumberFormatException e){
                    System.out.println("Invalid choice, try again.");
                    // if the response is not an integer, just try again
                }
            }// lil while
            
            if(choice==1)
                wordle.main(args);
            else if(choice==2)
            FalconBudgeter.main(args);
            else if(choice==3)
                RdmGen.main(args);
            else if(choice==4)
                ToDoList.main(args);
            else if(choice==5)
                Calculator.main(args);
            else if(choice==6){
                System.out.println("See you next time!");
                System.exit(0);
            }
            // runs the associated program when prompted by input. Only learned about .main for this project (very helpful)

            while(needChoiceVar==false){
                // since needChoiceVar must be false here, I just reused it
                System.out.println();
                System.out.println("Would you like to select another tool? (Y/N)");
                String temp = in.nextLine().toLowerCase();
                if(temp.equals("y")){
                    needChoiceVar = true;
                    // if the response is a yalid yes, just exit this loop and repeat the biggun
                }else if(temp.equals("n")){
                    System.out.println("See you next time!");
                    needChoice = false;
                    break;
                    // if a valid no, exit this loop after setting the requisite for the biggun to be false
                }else{
                    System.out.println("Invalid choice, try again.");
                    // duh
                }
            }
        }// big while
        
        System.out.println("Exiting Multitool shell...");
        in.close();
        System.exit(0);
        // just wrapping things up cleanly after the big while loop finishes
        // this MUST be the only place System.in closes
    }// main
}// class
    


