import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner input = new Scanner(System.in);
        char op;
        int opNumber;
        boolean opPerm = true;
        double num1, num2, result = 0;

        System.out.println("OPERATIONS");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication(*)");
        System.out.println("4. Division (/)");

        /*
         * Expression entry __operator, and the 2 operands and the exceptions are
         * handled well
         */
        while (true) {
            if (opPerm) {
                System.out.print("Choose the operator  among ( +, -, *, /): ");
                op = input.next().charAt(0);

                System.out.print("Enter the first operand: ");
                num1 = input.nextDouble();

                System.out.print("Enter the second operand: ");
                num2 = input.nextDouble();
            } else {
                num1 = result;
                System.out.println("\nThe stored result is: " + num1);

                System.out.print("Choose the operator among ( +, -, *, /): ");
                op = input.next().charAt(0);

                System.out.print("Enter the second operand: ");
                num2 = input.nextDouble();
            }
            // Arithmetic logic
            switch (op) {
                case '+':
                    result = calc.addition(num1, num2);
                    System.out.println("\nThe summation of " + num1 + " and " + num2 + " is " + result);
                    break;
                case '-':
                    result = calc.subtraction(num1, num2);
                    System.out.println("\nThe subtraction of " + num1 + " and " + num2 + " is " + result);
                    break;
                case '*':
                    result = calc.multiplication(num1, num2);
                    System.out.println("\nThe product of " + num1 + " and " + num2 + " is " + result);
                    break;
                case '/':
                    result = calc.division(num1, num2);
                    System.out.println("\nThe quotient of " + num1 + " and " + num2 + " is " + result);
                    break;
                default:
                    System.out.println("Wrong operator entered");
            }
            /*
             * description: This section enables us reuse our results from the previous
             * calculation or start a new one
             * When a user enters 1 uses the previous result as the first operand of the
             * next calculation and 2 is for a new calculation
             */
            System.out.println("\n1. Continue with the previous result.");
            System.out.println("2. Compute using the new operation");
            System.out.print("Enter the choicee of the operaor (1, 2): ");
            opNumber = input.nextInt();
            if (opNumber == 1) {
                opPerm = false;
            } else if (opNumber == 2) {
                opPerm = true;
            } else {
                break;
            }
        }
        input.close();
        System.out.println("\nThank you for using Codveda calculator!");
    }

    // METHODS SECTION:
    /*
     * function name: addition
     * arguments: double num1, double num2
     * description: This function takes two double numbers as arguments and returns
     * their summation.
     * return type: double
     */
    public double addition(double num1, double num2) {
        double result = num1 + num2;
        return result;

    }

    /*
     * function name: subtraction
     * arguments: double num1, double num2
     * description: This function takes two double numbers as arguments and returns
     * their difference.
     * return type: double
     */
    public double subtraction(double num1, double num2) {
        double result = num1 - num2;
        return result;
    }

    /*
     * function name: multiplication
     * arguments: double num1, double num2
     * description: This function takes two double numbers as arguments and returns
     * their product.
     * return type: double
     */
    public double multiplication(double num1, double num2) {
        double result = num1 * num2;
        return result;
    }

    /*
     * function name: division
     * arguments: double num1, double num2
     * description: This function takes two double numbers as arguments and returns
     * their quotient.
     * return type: double
     */
    public double division(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("error Division By zeroerror");
            return 0.0;
        }
        double result = num1 / num2;
        return result;
    }

}
