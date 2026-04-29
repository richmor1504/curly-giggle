// multitool.java

import java.io.IOException;
import java.util.*;

public class multitool {
    public static void main(String[] args) throws IOException{
       int choice = 0;
        Scanner in = new Scanner(System.in); 
        boolean needChoice = true;

        System.out.println("Welcome to the Multitool shell!\nPlease select a tool (1-6): ");
        System.out.println();
        while(needChoice){
            Boolean needChoiceVar = true;
            System.out.println("1. Word Game\n2. Falcon Dollar Budgeter\n3. Number Roller\n4. To-Do List\n5. Calculator\n6. Exit");
            System.out.println();
            
            while(needChoiceVar){
                System.out.print("Choice: ");
                String temp = in.nextLine();
                try{
                    choice = Integer.parseInt(temp);
                    if((choice < 1)||(choice>6))
                        System.out.println("Invalid choice, try again.");
                    else
                        needChoiceVar = false;
                }catch(NumberFormatException e){
                    System.out.println("Invalid choice, try again.");
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

            while(needChoiceVar==false){
                System.out.println();
                System.out.println("Would you like to select another tool? (Y/N)");
                String temp = in.nextLine().toLowerCase();
                if(temp.equals("y")){
                    needChoiceVar = true;
                }else if(temp.equals("n")){
                    System.out.println("See you next time!");
                    needChoice = false;
                    break;
                }else{
                    System.out.println("Invalid choice, try again.");
                }
            }
        }// big while
        
        System.out.println("Exiting Multitool shell...");
        in.close();
        System.exit(0);
    }// main
}// class
    


