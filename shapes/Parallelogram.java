package shapes;

public class Parallelogram extends Shape implements IConvexIPolygon {

    private final double a;
    private final double b;
    private final double d;

    Parallelogram(double a, double b, double d) {
        super("Parallelogram");
        this.a = a;
        this.b = b;
        this.d = d;
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
        return new double[]{a, d, b};
    }

    @Override
    public double getPerimeter() {
        return IConvexIPolygon.super.getPerimeter();
    }
}
