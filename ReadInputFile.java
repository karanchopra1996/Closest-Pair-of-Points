import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * It reads the input file and stores the points in an array
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class ReadInputFile {
    int n;
    Point[] points;

    /**
     * Reading the input file and storing the points in an array.
     * <p>
     * pre: none
     * <p>
     * post: points are stored in an array
     */
    public ReadInputFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            setN(scanner.nextInt());
            readPairOfPoints(scanner, getN());
        }
        catch (IOException e) {
            System.err.println("Error !!! The provided data file or file path is incorrect.");
            e.printStackTrace();
        }
    }

    /**
     * It reads a pair of points from the input file and stores them in an array of points
     * <p>
     * pre: Scanner object has been initialized and allocated memory
     * <p>
     * post: points are stored in an array
     *
     * @param scanner the scanner object that reads the input file
     * @param n       the number of points
     */
    private void readPairOfPoints(Scanner scanner, int n) {
        points = new Point[n];
        double[] currentPoint = {-0.0, -0.0};
        int i = 0;
        while (scanner.hasNextDouble()) {
            double coordinate = scanner.nextDouble();
            if (currentPoint[0] == -0.0) {
                currentPoint[0] = coordinate;
            } else {
                currentPoint[1] = coordinate;
            }

            if (currentPoint[0] != -0.0 && currentPoint[1] != -0.0) {
                points[i] = new Point(currentPoint[0], currentPoint[1]);
                currentPoint[0] = -0.0;
                currentPoint[1] = -0.0;
                i++;
            }
        }
    }

    /**
     * This function returns the value of the variable n.
     * <p>
     * pre: points are stored in the array
     * <p>
     * post: return the number of points
     *
     * @return The value of the variable n.
     */
    public int getN() {
        return n;
    }

    /**
     * This function sets the value of the variable n to the value of the parameter n.
     * <p>
     * pre: Scanner object has been initialized and allocated memory
     * <p>
     * post: Set the value of the variable n as per the number of points
     *
     * @param n The number of elements in the array.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Returns the stored points array.
     * <p>
     * pre: points array has been initialized and allocated memory
     * <p>
     * post: return the array of points
     *
     * @return The array of points.
     */
    public Point[] getPoints() {
        return points;
    }
}
