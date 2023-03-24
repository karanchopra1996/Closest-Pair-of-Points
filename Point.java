/**
 * This class represents a point in the plane.
 * <p>
 * It stores x and y coordinates in double format.
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class Point {
    double x;
    double y;

    /**
     * Instantiates a new Point.
     * <p>
     * pre: none
     * <p>
     * post: x and y are stored in the memory
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function returns the value of the x variable.
     * <p>
     * pre: none
     * <p>
     * post: return the value of the x variable
     */
    public double getX() {
        return x;
    }

    /**
     * This function returns the y coordinate of the point.
     * <p>
     * pre: none
     * <p>
     * post: return the value of the y variable
     */
    public double getY() {
        return y;
    }
}
