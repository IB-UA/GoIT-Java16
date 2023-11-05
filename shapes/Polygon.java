package shapes;

public class Polygon extends Shape implements IConvexIPolygon {

    private final double[] sides;
    private final double[] diagonals;

    Polygon(double[] sides, double[] diagonals) {
        super("Polygon");
        this.sides = sides;
        this.diagonals = diagonals;
    }

    @Override
    public double getArea() {
        return IConvexIPolygon.super.getArea();
    }

    @Override
    public double[] getSides() {
        return sides;
    }

    @Override
    public double[] getDiagonals() {
        return diagonals;
    }

    @Override
    public double getPerimeter() {
        return IConvexIPolygon.super.getPerimeter();
    }
}
