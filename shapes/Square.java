package shapes;

public class Square extends Shape implements IConvexIPolygon {
    private final double a;
    Square(double a) {
        super("Square");
        this.a = a;
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
        return new double[]{a, Math.sqrt(2 * Math.pow(a, 2)), a};
    }

    @Override
    public double getPerimeter() {
        return IConvexIPolygon.super.getPerimeter();
    }
}
