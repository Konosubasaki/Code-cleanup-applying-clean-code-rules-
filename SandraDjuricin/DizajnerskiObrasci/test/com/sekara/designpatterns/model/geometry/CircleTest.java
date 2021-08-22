package com.sekara.designpatterns.model.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {
	
	private Graphics graphics;
	private Circle circle;
	private Point centerOfCircle;
	@BeforeEach
	public void initialization() {
	//	graphics = mock(Graphics.class);
		centerOfCircle=new Point(1,1);
		circle = new Circle(centerOfCircle,20, Color.BLACK, Color.WHITE);
  	}
	@Test
	void testContainsXYpointTrue() {
		assertTrue(circle.containsXYpoint(centerOfCircle.getXCoordinate(), centerOfCircle.getYCoordinate()));
	}

	@Test
	void testContainsXYpointFalse() {
		assertFalse(circle.containsXYpoint(55,55));
	}

	@Test
	void testDraw() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDrawShapeIsSelected() {
		fail("Not yet implemented");
	}
	
	@Test
	void testClone() {
		Circle circleClone=(Circle)circle.clone();
		assertEquals(circle,circleClone);
	}

	@Test
	void testMoveBy() {
		Point centerbefore=circle.getCenter();
 		centerbefore.setXCoordinate(centerbefore.getXCoordinate()+5);
		centerbefore.setYCoordinate(centerbefore.getYCoordinate()+5);
		
		circle.moveBy(5, 5);
		assertEquals(centerbefore,circle.getCenter());
	}

	@Test
	void testCompareTo() {
		Circle secondCircle = new Circle(centerOfCircle,10, Color.BLACK, Color.WHITE);
		assertEquals(10,circle.compareTo(secondCircle));
	}

	@Test
	void testEqualsObject() {
		Circle equalCircle = new Circle(centerOfCircle,20, Color.BLACK, Color.WHITE);
		assertTrue(circle.equals(equalCircle));
	}

	@Test
	void testArea() {
		double Area= 20*20* Math.PI;
		assertEquals(Area,circle.area());

	}
	
	@Test
	void testSetCenter() {
		Point newCenter=new Point (2,2);
		circle.setCenter(newCenter);
		assertEquals(newCenter,circle.getCenter());
	}

	@Test
	void testGetRadius() {
		int radius=circle.getRadius();
		assertEquals(20,radius);
	}

	@Test
	void testSetRadius() {
		int newRadius=50;
		circle.setRadius(newRadius);
		assertEquals(newRadius,circle.getRadius());
	}
	
	@Test
	void testSetRadiusLessThan0() {
		int newRadius=-1;
 	    assertThrows(NumberFormatException.class,
	            ()->{circle.setRadius(newRadius);} );
	}

	@Test
	void testToString() {
		String circleToString=circle.toString();
		assertEquals("Circle(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)",circleToString);
	}

	@Test
	void testParse() {
		String toParse="Circle(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)";
		Circle circleParse=(Circle)circle.parse(toParse);
		assertEquals(circle,circleParse);
	}
}
