package shapes;

public class Rectangle extends Shape implements IConvexIPolygon {
    private final double a;
    private final double b;

    Rectangle(double a, double b) {
        super("Rectangle");

        this.a = a;
        this.b = b;
    }

    @Override
    public double getArea() {
        return IConvexIPolygon.super.getArea();
    }

    @Override
    public double[] getSides() {
        return new double[]{b, a};
    }

    @Override
    public double[] getDiagonals() {
        return new double[]{a, Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)), b};
    }

    @Override
    public double getPerimeter() {
        return IConvexIPolygon.super.getPerimeter();
    }
}
