import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Calculator {

    public static void main(String[] args) throws IOException {

        Calculator calc = new Calculator();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int op;
        int opNumber;
        boolean opPerm = true;

        double num1;
        double num2;
        double result = 0;

        /*
         * Expression entry: operator and the two operands.
         * Exceptions are handled to prevent invalid input from crashing the program.
         */
        while (true) {

            op = 0;

            System.out.println("\nOPERATIONS");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("0. Exit");

            if (opPerm) {

                op = calc.opEntry(input);

                if (op == 0) {
                    break;
                }

                if (op == -1) {
                    continue;
                }

                try {
                    System.out.print("Enter the first operand: ");
                    num1 = Double.parseDouble(input.readLine());

                    System.out.print("Enter the second operand: ");
                    num2 = Double.parseDouble(input.readLine());

                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter valid numbers.");
                    continue;
                }

            } else {

                num1 = result;
                System.out.println("\nThe stored result is: " + num1);

                op = calc.opEntry(input);

                if (op == 0) {
                    break;
                }

                if (op == -1) {
                    continue;
                }

                try {
                    System.out.print("Enter the second operand: ");
                    num2 = Double.parseDouble(input.readLine());

                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid number.");
                    continue;
                }
            }

            // Arithmetic logic
            switch (op) {

                case 1:
                    result = calc.addition(num1, num2);
                    System.out.println("\nThe summation of " + num1
                            + " and " + num2 + " is " + result);
                    break;

                case 2:
                    result = calc.subtraction(num1, num2);
                    System.out.println("\nThe subtraction of " + num1
                            + " and " + num2 + " is " + result);
                    break;

                case 3:
                    result = calc.multiplication(num1, num2);
                    System.out.println("\nThe product of " + num1
                            + " and " + num2 + " is " + result);
                    break;

                case 4:
                    if (num2 == 0) {
                        System.out.println("Error! Division by zero is not allowed.");
                        continue;
                    }

                    result = calc.division(num1, num2);
                    System.out.println("\nThe quotient of " + num1
                            + " and " + num2 + " is " + result);
                    break;

                default:
                    System.out.println("Wrong operator entered.");
                    continue;
            }

            /*
             * This section enables us to reuse the result from the previous
             * calculation or start a new calculation.
             *
             * 1 - Use the previous result as the first operand.
             * 2 - Start a new calculation.
             */
            System.out.println("\n1. Continue with the previous result.");
            System.out.println("2. Compute using a new operation.");
            System.out.println("0. Exit.");
            System.out.print("Enter your choice (0, 1, 2): ");

            try {
                opNumber = Integer.parseInt(input.readLine());

                if (opNumber == 1) {
                    opPerm = false;

                } else if (opNumber == 2) {
                    opPerm = true;

                } else if (opNumber == 0) {
                    break;

                } else {
                    System.out.println("Invalid choice. Starting a new calculation.");
                    opPerm = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter 0, 1, or 2.");
                opPerm = true;
            }
        }

        input.close();
        System.out.println("\nThank you for using Codveda Calculator!");
    }

    // METHODS SECTION

    /*
     * Function name: addition
     * Arguments: double num1, double num2
     * Description: Takes two double numbers and returns their summation.
     * Return type: double
     */
    public double addition(double num1, double num2) {
        double result = num1 + num2;
        return result;
    }

    /*
     * Function name: subtraction
     * Arguments: double num1, double num2
     * Description: Takes two double numbers and returns their difference.
     * Return type: double
     */
    public double subtraction(double num1, double num2) {
        double result = num1 - num2;
        return result;
    }

    /*
     * Function name: multiplication
     * Arguments: double num1, double num2
     * Description: Takes two double numbers and returns their product.
     * Return type: double
     */
    public double multiplication(double num1, double num2) {
        double result = num1 * num2;
        return result;
    }

    /*
     * Function name: division
     * Arguments: double num1, double num2
     * Description: Takes two double numbers and returns their quotient.
     * Return type: double
     */
    public double division(double num1, double num2) {
        double result = num1 / num2;
        return result;
    }

    /*
     * Function name: opEntry
     * Description: Reads and validates the arithmetic operation selected by the
     * user.
     * Return type: int
     */
    public int opEntry(BufferedReader input) throws IOException {

        int operator;

        try {
            System.out.print("Choose the operator among (1, 2, 3, 4) or 0 to exit: ");
            operator = Integer.parseInt(input.readLine());

            if (operator < 0 || operator > 4) {
                System.out.println("Error: Operator must be between 1 and 4.");
                return -1;
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid integer.");
            return -1;
        }

        return operator;
    }
}