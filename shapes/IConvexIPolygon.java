package shapes;

public interface IConvexIPolygon extends IPolygon {
    static double calcTriangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    default double getArea() {
        double area = 0;
        double[] sides = getSides();
        double[] diagonals = getDiagonals();
        for (int i = 0; i < sides.length; i++) {
            area += calcTriangleArea(sides[i], diagonals[i], diagonals[i + 1]);
        }

        return area;
    }
}
