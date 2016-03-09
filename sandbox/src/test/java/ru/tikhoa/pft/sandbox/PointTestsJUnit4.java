package ru.tikhoa.pft.sandbox;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTestsJUnit4 {

    private static Point p2;
    private static Point p1;

    @BeforeClass
    public static void setUp(){
        p1 = new Point(0, 0);
        p2 = new Point(3, 4);
    }

    @Test(timeout=100)
    public void testingDistance() {
        assertEquals(Point.distance(p1, p2), 5.0, 0.001);
        p2 = new Point(-4, -3);
        assertEquals(Point.distance(p1, p2), 5.0, 0.001);
    }

    @AfterClass
    public static void tearDown(){
        p1 = null;
        p2 = null;
    }
}