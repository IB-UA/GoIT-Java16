package shapes;

public interface IPolygon {
    double[] getSides();
    double[] getDiagonals();

    default  double getPerimeter() {
        double[] diagonals = getDiagonals();
        double perimeter = 0.0;
        for (double side: getSides()) {
            perimeter += side;
        }
        perimeter += diagonals[0];
        perimeter += diagonals[diagonals.length - 1];
        return perimeter;
    }

}
