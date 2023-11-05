package shapes;

public class Circle extends Shape implements IEllipse {
    private final double radius;
    Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double getSemiAxisA() {
        return radius;
    }

    @Override
    public double getSemiAxisB() {
        return radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
