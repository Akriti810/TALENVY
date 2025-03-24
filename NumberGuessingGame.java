import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        
        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1; // Generate number between 1 and 100
            int attempts = 0;
            int maxAttempts = 7; // Limit attempts
            boolean guessedCorrectly = false;
            
            System.out.println("\nWelcome to the Number Guessing Game!");
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.\n");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println(":) Congratulations! You guessed the number in " + attempts + " attempts!");
                    totalScore += (maxAttempts - attempts + 1); // More attempts left = higher score
                    guessedCorrectly = true;
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println(":( You've used all attempts! The correct number was: " + randomNumber);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame Over! Your total score: " + totalScore);
        scanner.close();
    }
}
