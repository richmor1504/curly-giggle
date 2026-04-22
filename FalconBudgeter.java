import java.util.Scanner;

public class FalconBudgeter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Messiah University Falcon Dining Dollar Budgeter");
        
        System.out.print("Enter current Dining Dollar balance: $");
        double balance = input.nextDouble();
        input.nextLine(); 

        boolean running = true;
        while (running) {
            System.out.println("\nCurrent Balance: $" + String.format("%.2f", balance) + " Dining Dollars");
            System.out.println("Select Location:");
            System.out.println("- Lottie Nelson");
            System.out.println("- Falcon Cafe");
            System.out.println("- Union (30% Discount)"); // Discount
            System.out.println("- Cafe Diem");
            System.out.println("- Exit");
            System.out.print("Selection: ");
            
            String choice = input.nextLine().toLowerCase().trim();
            
            double multiplier = 1.0; 
            String location = "";

            if (choice.equals("exit")) {
                running = false;
                continue;
            } else if (choice.contains("lottie")) {
                multiplier = 1.0;
                location = "Lottie Nelson";
            } else if (choice.contains("falcon")) {
                multiplier = 1.0;
                location = "The Falcon Cafe";
            } else if (choice.contains("union")) {
                multiplier = 0.70; // 30% Discount (You pay 70%)
                location = "The Union";
            } else if (choice.contains("diem")) {
                multiplier = 1.0; 
                location = "Café Diem";
            } else {
                System.out.println("Invalid location. Please type the name (e.g., 'Union').");
                continue;
            }

            System.out.print("Enter the price shown on the register: $");
            double rawPrice = input.nextDouble();
            input.nextLine(); 

            double actualCost = rawPrice * multiplier;

            if (actualCost <= balance) {
                balance -= actualCost;
                System.out.printf("Success! %s charged: $%.2f\n", location, actualCost);
            } else {
                System.out.println("Declined: Not enough funds!");
            }
        }

        System.out.printf("\nFinal Semester Balance: $%.2f\n", balance);
        System.out.println("Enjoy your meal!");
        input.close();
    }
}
