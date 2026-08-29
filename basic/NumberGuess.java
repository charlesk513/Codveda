
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class NumberGuess {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // maintaining the random number to be integer and between 1 and 100
        int random = (int) ((Math.random() * 100) + 1);
        int i = 0;
        while (true) {
            i = i + 1; // counter to count how many times a player guesses the number
            System.out.println(random);
            System.out.print("Guess: ");
            int user_value = Integer.parseInt(reader.readLine());

            // maintain user guess to fll between 1 and 100
            if (user_value <= 0 || user_value > 100) {
                System.out.println("Please enter a value from 1 to 100");
                continue;
            }
            if (user_value < random) {
                System.out.println("Value guessed is low");
            } else if (user_value > random) {
                System.out.println("Value guessed is higher than the random value");
            } else {
                System.out.println("Congratulations, number successfully guessed after " + i + " trials.");
                break;
            }
        }
    }
}
