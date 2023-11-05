package shapes;

public class ShapePrinter {
    public static void main(String[] args) {
        ShapePrinter printer = new ShapePrinter();
        Circle circle = new Circle(4.5);
        Ellipse ellipse = new Ellipse(7, 9);
        Triangle[] triangles = new Triangle[]{
                new Triangle(3,4,5),
                new Triangle(6, 8),
                new Triangle(5)
        };
        Square square = new Square(10);
        Parallelogram parallelogram = new Parallelogram(3, 4, 5);
        Rectangle rectangle = new Rectangle(3, 4);
        Rhombus rhombus = new Rhombus(8, 12);
        Polygon polygon = new Polygon(new double[]{4.0, 3.0}, new double[]{3.0, 5.0, 4.0});
        printer.print(circle);
        printer.print(ellipse);
        for (Triangle triangle: triangles) {
            printer.print(triangle);
        }
        printer.print(square);
        printer.print(parallelogram);
        printer.print(rectangle);
        printer.print(rhombus);
        printer.print(polygon);
    }

    public void print(Shape shape) {
        System.out.printf("Printing... %s%n", shape);
    }
}
