import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Factorial {
    public static void main(String[] args) throws IOException {
        boolean permission = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Factorial recursion = new Factorial();

        while (permission) {
            System.out.print("Enter the number: ");
            int number = Integer.parseInt(reader.readLine());

            if (number < 0) {
                System.out.println("The number must be a positive number to calculate its factorial");
                continue;
            } else if (number == 0) {
                System.out.println("Error!, can't have a factorial of Zero");
                continue;
            }
            System.out.println("The recursion of " + number + " is: " + recursion.factorial(number));

        }
    }

    /*
     * function name: factorial
     * argument: int number
     * description: This function takes an integer as an argument and returns
     * its factorial value
     * return type: int
     */
    public int factorial(int number) {
        if (number == 1) {
            return 1;
        }
        return number * factorial(number - 1);

    }
}
