package ru.tikhoa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTestsNG {

    @Test // annotations are classes (pseudo comments)
    public void testArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point (3, 4);
        Assert.assertNotNull(p1);
        Assert.assertNotNull(p2);

        //assert p1.distance(p2) == 5;
        Assert.assertEquals(p2.distance(p1), p1.distance(p2), 0.001);
        Assert.assertEquals(p1.distance(p2), 5.0);
        Assert.assertTrue(p1.distance(p2) == p2.distance(p1));

    }

}
