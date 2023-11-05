package shapes;

public class Triangle extends Shape implements IConvexIPolygon {
    private final double a;
    private final double b;
    private final double c;

    private static String getTriangleType(double a, double b, double c) {
        if (a == b && b == c) {
            return "Equilateral triangle";
        } else if (a == b || b == c) {
            return "Isosceles triangle";
        }
        return "Triangle";

    }

    Triangle (double a) {
        this(a, a);
    }

    Triangle (double a, double b) {
        this(a, b, b);
    }



    Triangle (double a, double b, double c) {
        super(getTriangleType(a, b, c));
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double[] getSides() {
        return new double[]{a};
    }

    @Override
    public double[] getDiagonals() {
        return new double[]{b,c};
    }

    @Override
    public double getPerimeter() {
        return IConvexIPolygon.super.getPerimeter();
    }

    @Override
    public double getArea() {
        return IConvexIPolygon.super.getArea();
    }
}
