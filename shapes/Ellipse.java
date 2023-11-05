package shapes;

public class Ellipse extends Shape implements IEllipse  {
    private final double a;
    private final double b;

    Ellipse(double a, double b) {
       super("Ellipse");
       this.a = a;
       this.b = b;
    }

    @Override
    public double getSemiAxisA() {
        return a;
    }

    @Override
    public double getSemiAxisB() {
        return b;
    }

    @Override
    public double getPerimeter() {
        return IEllipse.super.getPerimeter();
    }

    @Override
    public double getArea() {
        return IEllipse.super.getArea();
    }
}
