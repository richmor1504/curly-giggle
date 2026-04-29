import java.util.*;

public class RdmGen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int choice = 0;

        while (choice != 6) {

            System.out.println("\nRandom Generator Menu");
            System.out.println("1. Flip a Coin");
            System.out.println("2. 4 Sided Die");
            System.out.println("3. 6 Sided Die");
            System.out.println("4. 10 Sided Die");
            System.out.println("5. 20 Sided Die");
            System.out.println("6. Quit");
            System.out.print("Choice: ");

            choice = input.nextInt();

            if (choice == 1) {
                String result = (rand.nextInt(2) == 0) ? "Heads" : "Tails";
                System.out.println("You flipped: " + result);

            } else if (choice == 2) {
                int roll = rand.nextInt(4) + 1;
                System.out.println("You rolled: " + roll);

            } else if (choice == 3) {
                int roll = rand.nextInt(6) + 1;
                System.out.println("You rolled: " + roll);

            } else if (choice == 4) {
                int roll = rand.nextInt(10) + 1;
                System.out.println("You rolled: " + roll);

            } else if (choice == 5) {
                int roll = rand.nextInt(20) + 1;
                System.out.println("You rolled: " + roll);

            } else if (choice == 6) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice.");
            }
        }

    }
}
