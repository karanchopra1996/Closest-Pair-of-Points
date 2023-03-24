import java.util.Arrays;
import java.util.Comparator;

/**
 * It takes an array of points, sorts them by x and y coordinates, and then recursively finds the closest pair of points
 * in the array
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class ClosestPair {
    private static int n;
    private static ReadInputFile readInputFile;
    private static ClosestPair closestPair;
    private static Point[] xSorted;
    private static Point[] ySorted;

    /**
     * The entry point of application.
     * <p>
     * pre: none
     * <p>
     * post: objects of classes are initialized, input file is read and output is printed out for closest pair of points
     * for every recursive call
     */
    public static void main(String[] args) {
        String inputFilePath = "program2data.txt";
        closestPair = new ClosestPair();
        // read input file
        getInputFileData(inputFilePath);
        // sort points by x and y coordinates
        sortPoints(readInputFile.getPoints());
        // find closest pair
        closestPair.closestPair(xSorted, ySorted, 0, n - 1);
    }

    /**
     * This function reads the input file and stores the number of vertices in the graph in the variable n
     * <p>
     * pre: ClosestPair class object has been allocated memory
     * <p>
     * post: input file has been read and stored in variables, and number of points is stored in the n variable
     *
     * @param inputFilePath The path of the input file.
     */
    private static void getInputFileData(String inputFilePath) {
        readInputFile = new ReadInputFile(inputFilePath);
        // set the number of points
        n = readInputFile.getN();
    }

    /**
     * It takes an array of points and creates two new arrays of points, one sorted by x-coordinate and one sorted by
     * y-coordinate
     * <p>
     * pre: points have been allocated memory
     * <p>
     * post: point are sorted by x-coordinate and y-coordinate and stored in memory
     *
     * @param points the array of points to be sorted
     */
    private static void sortPoints(Point[] points) {
        xSorted = new Point[points.length];
        ySorted = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            xSorted[i] = points[i];
            ySorted[i] = points[i];
        }
        closestPair.sortPointsByX(xSorted);
        closestPair.sortPointsByY(ySorted);
    }

    /**
     * Calculate the distance between two points.
     * <p>
     * pre: points have been allocated memory
     * <p>
     * post: distance is returned for given points a and b
     *
     * @param a The first point
     * @param b The point that is being compared to the other points.
     *
     * @return The distance between two points.
     */
    private double calculateDistance(Point a, Point b) {
        return Math.sqrt((Math.pow((a.x - b.x), 2)) + (Math.pow((a.y - b.y), 2)));
    }

    /**
     * We divide the points into two halves, find the closest pair in each half, and then find the closest pair that
     * crosses the midpoint
     * <p>
     * pre: file points have been allocated memory, all points have been sorted
     * <p>
     * post: closest pairs in left half, right half and midpoint band are printed to console
     *
     * @param x    sorted array of points by x coordinate
     * @param y    the array of points sorted by y-coordinate
     * @param low  the index of the first point in the array
     * @param high the index of the last element in the array
     *
     * @return The minimum distance between any two points in the array.
     */
    private double closestPair(Point[] x, Point[] y, int low, int high) {
        int length = high - low + 1;
        //If there are less than 3 points, then use brute force
        if (length <= 3) return closestPairUsingBruteForce(x, low, high);

        int mid = low + (high - low) / 2;
        Point midPoint = x[mid];
        // find left and right min recursively
        double leftMin = closestPair(x, y, low, mid);
        double rightMin = closestPair(x, y, mid + 1, high);
        // find the min distance from left and right search space
        double minDistance = Math.min(leftMin, rightMin);

        // find the min distance from strip of points
        Point[] strip = new Point[length];
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (Math.abs(y[i].x - midPoint.x) < minDistance) {
                strip[j] = y[i];
                j++;
            }
        }
        // Return the minimum of minDistance and closest distance is strip[]
        return getMinDistanceInStripe(strip, j, low, high, minDistance);
    }

    /**
     * For each point in the strip, calculate the distance to the next 7 points and return the minimum distance
     * <p>
     * pre: sorted points have been allocated memory
     * <p>
     * post: closest pair in the strip is printed and returned
     *
     * @param strip       the array of points that are in the stripe
     * @param length      the number of points in the strip
     * @param low         the lower bound of the current stripe
     * @param high        the highest index of the array
     * @param minDistance the minimum distance between two points in the entire set
     *
     * @return The minimum distance between two points in the strip.
     */
    private double getMinDistanceInStripe(Point[] strip, int length, int low, int high, double minDistance) {
        double min = minDistance;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length && j <= i + 7; ++j) {
                double current = calculateDistance(strip[i], strip[j]);
                if (current < min) {
                    min = current;
                }
            }
        }
        System.out.println("D[" + low + "," + high + "]: " + String.format("%.4f", min));
        return min;
    }

    /**
     * It calculates the minimum distance between any two points in the given range
     * <p>
     * pre: sorted points by x-coordinate have been allocated memory
     * <p>
     * post: closest pair for three or less points is printed and returned
     *
     * @param points the array of points
     * @param low    the index of the first point in the array
     * @param high   the index of the last point in the array
     * @param length
     *
     * @return The minimum distance between any two points in the array.
     */
    private double closestPairUsingBruteForce(Point[] points, int low, int high) {
        double minDistance = Double.MAX_VALUE;

        for (int i = low; i <= high; i++) {
            for (int j = i + 1; j <= high; j++) {
                double distance = calculateDistance(points[i], points[j]);
                if (distance < minDistance) minDistance = distance;
            }
        }

        System.out.println("D[" + low + "," + high + "]: " + String.format("%.4f", minDistance));
        return minDistance;
    }

    /**
     * It sorts the points by their y-coordinate
     * <p>
     * pre: points have been allocated memory
     * <p>
     * post: points are sorted by their y-coordinate
     *
     * @param points the array of points to sort
     */
    private void sortPointsByY(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(Point::getY));
    }

    /**
     * Sort the points by their x-coordinate.
     * <p>
     * pre: points have been allocated memory
     * <p>
     * post: points are sorted by their x-coordinate
     *
     * @param points The array of points to sort.
     */
    private void sortPointsByX(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(Point::getX));
    }
}
