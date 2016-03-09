import ru.tikhoa.pft.sandbox.Point;

public class Run {
    public static void main(String[] args){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        // static method
        System.out.println(Point.distance(p1, p2));

        // not static
        System.out.println(p1.distance(p2));
        System.out.println(p2.distance(p1));

        p2 = new Point(-3, -4);

        // static method
        System.out.println(Point.distance(p1, p2));

        // not static
        System.out.println(p1.distance(p2));
        System.out.println(p2.distance(p1));
    }
}
