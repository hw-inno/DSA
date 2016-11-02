import java.awt.geom.Point2D;
import java.io.*;

public class Program {

    public static void main(String[] args) {
        String input = readFromFile();
        Polygon polygon = parser(input);
        writeToFile(polygon.area());
    }

    public static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {
// We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
// then we solve A + (B-A)*u = C + (D-C)*v
// let's use Kramer's rule to solve the task (Ax = B) were x = (u, v)^T
// build a matrix for the equation
        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();
// calculate determinant
        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];
// substitute columns and calculate determinants
        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());
// calculate the solution
// even if det0 == 0 (they are parallel) this will return NaN and comparison will fail -> false
        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    public static String readFromFile(){

        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));
            String s = br.readLine();
            return s;
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static void writeToFile(Double result){
        try{
            PrintWriter writer = new PrintWriter("output.txt");
            System.out.format("Area == %.2f", result);
            writer.format("%.2f", result);
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static Polygon parser(String s) throws NumberFormatException,
                                                    NullPointerException{
        String[] parts = s.split("\\s");

        if(parts.length < 6){
            System.out.println("Number of vertices < 3");
            System.exit(1);
        }

        int j = 0;
        double[] mas = new double[parts.length];

        Point2D.Double[] points = new Point2D.Double[parts.length / 2];

        try {
            for(int i = 0; i < parts.length; i++){
                mas[i] =  Double.parseDouble(parts[i]);
            }
        }
        catch (NumberFormatException ex){
           System.out.println("Invalid input");
           System.exit(1);
        }

       for(int i = 0; i < points.length; i++, j += 2){
           points[i] = new Point2D.Double();
           points[i].setLocation(mas[j], mas[j+1]);
       }

        return new Polygon(points);
    }
}
