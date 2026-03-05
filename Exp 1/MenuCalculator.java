import java.util.Scanner;

public class MenuCalculator {

    static int add(int a, int b) {
        return a + b;
    }

    static int sub(int a, int b) {
        return a - b;
    }

    static int mul(int a, int b) {
        return a * b;
    }

    static void div(int a, int b) {
        if (b == 0)
            System.out.println("Invalid");
        else
            System.out.println("Result = " + (a / b));
    }

    static void mod(int a, int b) {
        if (b == 0)
            System.out.println("Invalid");
        else
            System.out.println("Result = " + (a % b));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int ch, a, b;

        do {
            System.out.println("\nOperations List \n1.Add  \n2.Sub  \n3.Mul  \n4.Div  \n5.Mod  \n6.Exit");
            System.out.print("\nEnter choice: ");
            ch = sc.nextInt();

            if (ch >= 1 && ch <= 5) {
                System.out.print("Enter First Number (Bigger): ");
                a = sc.nextInt();
                System.out.print("Enter Second Number (Smaller): ");
                b = sc.nextInt();
            } else {
                continue;
            }

            switch (ch) {
                case 1:
                    System.out.println("Result = " + add(a, b));
                    break;
                case 2:
                    System.out.println("Result = " + sub(a, b));
                    break;
                case 3:
                    System.out.println("Result = " + mul(a, b));
                    break;
                case 4:
                    div(a, b);
                    break;
                case 5:
                    mod(a, b);
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (ch != 6);

        sc.close();
    }
}
