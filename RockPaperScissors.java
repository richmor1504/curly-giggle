import java.util.*;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        boolean playing = true;
        
        // Lowercase and Uppercase options for user input
        String[] options = {"rock", "paper", "scissors"};

        System.out.println("Rock, Paper, Scissors ---");

        while (playing) {
            System.out.print("\nEnter Rock, Paper, or Scissors (or type 'exit'): ");
            String userMove = input.nextLine().trim().toLowerCase();

            if (userMove.equals("exit")) {
                playing = false;
                continue;
            }

            int computerIndex = rand.nextInt(3);
            String computerMove = options[computerIndex];

            if (userMove.equals("rock") || userMove.equals("paper") || userMove.equals("scissors")) {
                System.out.println("Computer chose: " + computerMove);

                // 1. Check for user win first
                if ((userMove.equals("rock") && computerMove.equals("scissors")) ||
                    (userMove.equals("paper") && computerMove.equals("rock")) ||
                    (userMove.equals("scissors") && computerMove.equals("paper"))) {
                    System.out.println("You win!");
                } 
                // 2. Check for tie second
                else if (userMove.equals(computerMove)) {
                    System.out.println("It's a tie!");
                } 
                // 3. Otherwise computer wins
                else {
                    System.out.println("Computer wins!");
                }
            } else {
                System.out.println("Invalid input. Type Rock, Paper, or Scissors.");
            }
        }
        System.out.println("Returning to Multitool Menu...");
    }
}
