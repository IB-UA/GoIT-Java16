package shapes;

abstract public class Shape {
    private final String name;

    Shape (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return String.format("%s, with perimeter: %f, and area: %f", getName(), getPerimeter(), getArea());
    }

    abstract double getPerimeter();
    abstract double getArea();

}
