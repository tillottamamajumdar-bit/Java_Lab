import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        try 
        {
            // Input dimension
            System.out.print("Enter dimension (2 or 3): ");
            int dim = sc.nextInt();

            if (dim != 2 && dim != 3) {
                throw new VectorException("Only 2D or 3D vectors allowed.");
            }

            double[] v1Data = new double[dim];
            double[] v2Data = new double[dim];

            // Input Vector 1
            System.out.println("Enter elements of Vector 1:");
            for (int i = 0; i < dim; i++) {
                v1Data[i] = sc.nextDouble();
            }

            // Input Vector 2
            System.out.println("Enter elements of Vector 2:");
            for (int i = 0; i < dim; i++) {
                v2Data[i] = sc.nextDouble();
            }

            Vector v1 = new Vector(v1Data);
            Vector v2 = new Vector(v2Data);

            System.out.print("Vector 1: ");
            v1.display();

            System.out.print("Vector 2: ");
            v2.display();

            // Operations
            Vector sum = v1.add(v2);
            System.out.print("Addition: ");
            sum.display();

            Vector diff = v1.subtract(v2);
            System.out.print("Subtraction: ");
            diff.display();

            double dot = v1.dotProduct(v2);
            System.out.println("Dot Product: " + dot);

        } 
        catch (VectorException e) 
        {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}