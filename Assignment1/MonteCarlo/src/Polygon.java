import java.awt.geom.Point2D;
import java.util.Random;

public class Polygon {
    private MyArrayList<Point2D.Double> coords;
    private double xMin = 1000, yMin = 1000;
    private double xMax = -1000, yMax = -1000;

    public void printUniversumCoords(){
        System.out.format("(%.4f, %.4f)   (%.4f, %.4f)\n", xMin, yMax, xMax, yMax);
        System.out.format("(%.4f, %.4f)   (%.4f, %.4f)\n", xMin, yMin, xMax, yMin);
    }

    public double areaOfUniversum(){return Math.abs((yMax - yMin) * (xMax -xMin));}

   // Constructors..
   public Polygon(){
       coords = new MyArrayList<Point2D.Double>();
   }

    public Polygon(Point2D.Double[] points) {
        coords = new MyArrayList<Point2D.Double>();
        for (int i = 0; i < points.length; i++){
            coords.add(points[i]);
        }
    }

    //is point X inside of polygon
    public boolean isInside(Point2D X){
        Point2D.Double outSide = new Point2D.Double(23.44, 15.985);
        int k = 0;

        for(int i = 0; i < coords.size() - 1; i++){
            if (Program.intersects(X, outSide, coords.get(i), coords.get(i + 1)))
                k++;
        }
        if(Program.intersects(X, outSide, coords.get(0), coords.get(coords.size() - 1)))
            k++;

        return k % 2 != 0;
        /*if (k % 2 == 0){
            return false;
        }
        return true;*/
    }

    //Finds coordinates of a rectangle which contains our polygon
    public void universum(){

        for(int i = 0; i < coords.size(); i++){

            if(coords.get(i).getX() < xMin )
                xMin = coords.get(i).getX();

            if(coords.get(i).getY() < yMin)
                yMin = coords.get(i).getY();

            if(coords.get(i).getX() > xMax)
                xMax = coords.get(i).getX();

            if(coords.get(i).getY() > yMax)
                yMax = coords.get(i).getY();
        }
    }

    public double area() throws NullPointerException{
        universum();

        Random random = new Random(System.currentTimeMillis());
        final int MAX_POINTS = 10000000;
        int counter = 0;

        Point2D.Double randomPoint, outsidePoint;
        outsidePoint = new Point2D.Double();
        randomPoint = new Point2D.Double();

        outsidePoint.setLocation(23.44, 15.985);

        for (int i = 0; i < MAX_POINTS; i++){
            double X = random.nextDouble() * (xMax - xMin) + xMin;
            double Y = random.nextDouble() * (yMax - yMin) + yMin;

            randomPoint.setLocation(X, Y);
            if(isInside(randomPoint))
                counter++;
        }

        double result = areaOfUniversum() * counter / MAX_POINTS;

        return Math.round(result * 100.0)/100.0;
    }
}
