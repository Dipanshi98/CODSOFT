import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int range = upperBound - lowerBound + 1;

        int secretNumber = random.nextInt(range) + lowerBound;
        int guess;
        int attempts = 0;
        boolean correctGuess = false;

        System.out.println("Welcome to Guess the Number game!");
        System.out.println("I've picked a number between " + lowerBound + " and " + upperBound + ". Try to guess it!");

        while (!correctGuess) {
            System.out.print("Enter your guess: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
            guess = scanner.nextInt();
            attempts++;

            if (guess < lowerBound || guess > upperBound) {
                System.out.println("Your guess is out of the valid range (" + lowerBound + " - " + upperBound + "). Try again.");
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > secretNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number " + secretNumber + " correctly!");
                System.out.println("Number of attempts: " + attempts);
                correctGuess = true;
            }
        }

        scanner.close();
    }
}
