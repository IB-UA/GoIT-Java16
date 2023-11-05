package shapes;

public class Rhombus extends Shape implements IConvexIPolygon {
    private final double a;
    private final double d;

    Rhombus(double a, double d) {
        super("Rhombus");
        this.a = a;
        this.d = d;
    }

    @Override
    public double getArea() {
        return IConvexIPolygon.super.getArea();
    }

    @Override
    public double[] getSides() {
        return new double[]{a, a};
    }

    @Override
    public double[] getDiagonals() {
        return new double[]{a, d, a};
    }

    @Override
    public double getPerimeter() {
        return IConvexIPolygon.super.getPerimeter();
    }
}
