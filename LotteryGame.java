import java.util.Scanner; // Import Scanner for user input
import java.util.Random;  // Import Random for generating random numbers

public class LotteryGame {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in); // Create Scanner object for input
        Random random = new Random();        // Create Random object for random number generation

        int rounds = 4; // Number of main rounds

        // Print game welcome and rules
        System.out.println("Welcome to Crayon Muncher's Lottery Game!");
        System.out.println("Rules:\n1. 3 main rounds\n2. Each round increases the numbers you input\n3. Have fun!");

        // Loop through main rounds only
        for (int round = 1; round <= rounds; round++) {
            int numCount = round + 1;                     // Number of inputs increases each round
            int[] userNumbers = new int[numCount];        // Array to store user's numbers
            int[] numWinners = new int[numCount];         // Array to store randomly generated winning numbers

            // Prompt the user to enter numbers
            System.out.println("\nRound " + round + " - Enter " + numCount + " numbers (1-10):");

            // Loop to get user input numbers
            for (int i = 0; i < numCount; i++) {
                System.out.print("Enter number " + (i + 1) + " (or press Enter twice to exit): ");
                String input = kb.nextLine();

                // Exit the game if input is empty
                if (input.equals("")) {
                    System.out.println("Game has ended.\nThank you for playing!");
                    return;
                }

                int userInput = 0;
                boolean isNumber = true;

                // Convert string to number manually and check if it's valid
                for (int j = 0; j < input.length(); j++) {
                    char c = input.charAt(j);
                    if (c >= '0' && c <= '9') {
                        userInput = userInput * 10 + (c - '0');
                    } else {
                        isNumber = false;
                        break;
                    }
                }

                // Validate the number and store it
                if (isNumber && userInput >= 1 && userInput <= 10) {
                    userNumbers[i] = userInput;
                } else {
                    System.out.println("Invalid number. Try again.");
                    i--; // Retry current index
                }
            }

            // Generate random winning numbers
            for (int i = 0; i < numCount; i++) {
                numWinners[i] = random.nextInt(10) + 1; // Random number between 1 and 10
            }

            // Display user's numbers
            System.out.print("Your numbers: ");
            for (int i = 0; i < numCount; i++) {
                System.out.print(userNumbers[i] + " ");
            }
            System.out.println();

            // Display winning numbers
            System.out.print("Winning numbers: ");
            for (int i = 0; i < numCount; i++) {
                System.out.print(numWinners[i] + " ");
            }
            System.out.println();

            // Count how many numbers matched
            int matchCount = 0;
            for (int i = 0; i < numCount; i++) {
                if (userNumbers[i] == numWinners[i]) {
                    matchCount++;
                }
            }

            // Display match result
            System.out.println("You matched " + matchCount + " number(s).");

            // Calculate and display the chance of exact match (not used for prize)
            double totalCombinations = 1;
            for (int i = 0; i < numCount; i++) {
                totalCombinations *= 10; // 10 options for each number
            }

            double winChance = (1 / totalCombinations) * 100;
            System.out.printf("Chance of winning (exact match): %.10f%%\n", winChance);
        }

        // Final message after all rounds
        System.out.println("\nThanks for playing! Hope you enjoyed the game.");
        kb.close(); // Close the scanner
    }
}
