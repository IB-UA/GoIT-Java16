package shapes;

public interface IEllipse {
    double getSemiAxisA();
    double getSemiAxisB();

    default double getPerimeter() {
        double a = getSemiAxisA();
        double b = getSemiAxisB();
        return 4 * (Math.PI * a * b + Math.abs(a - b)) / (a + b);
    }

    default double getArea() {
        double a = getSemiAxisA();
        double b = getSemiAxisB();
        return Math.PI * a * b;
    }
}
