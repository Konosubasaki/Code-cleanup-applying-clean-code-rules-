package com.sekara.designpatterns.model.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RectangleTest {

	private Rectangle rectangle;
	private Point upperLeftPoint;

	@BeforeEach
	public void initialization() {
		// graphics = mock(Graphics.class);
		upperLeftPoint = new Point(1, 1);
		rectangle = new Rectangle(upperLeftPoint, 5, 10, Color.BLACK, Color.WHITE);
	}

	@Test
	void testContainsXYpointTrue() {
		assertTrue(rectangle.containsXYpoint(upperLeftPoint.getXCoordinate(), upperLeftPoint.getYCoordinate()));
	}

	@Test
	void testContainsXYpointFalse() {
		Point newPoint = new Point(100, 100);
		assertFalse(rectangle.containsXYpoint(newPoint.getXCoordinate(), newPoint.getYCoordinate()));
	}

	@Test
	void testClone() {
		Rectangle rectangleClone = (Rectangle) rectangle.clone();
		assertEquals(rectangle, rectangleClone);
	}

	@Test
	void testMoveBy() {
		Point oldUpperLeftPoint = rectangle.getUpperLeftPoint();
		oldUpperLeftPoint.setXCoordinate(oldUpperLeftPoint.getXCoordinate() + 5);
		oldUpperLeftPoint.setYCoordinate(oldUpperLeftPoint.getYCoordinate() + 5);

		rectangle.moveBy(5, 5);
		assertEquals(oldUpperLeftPoint, rectangle.getUpperLeftPoint());
	}

	@Test
	void testArea() {
		double Area = 5 * 10;
		assertEquals(Area, rectangle.area());
	}

	@Test
	void testGetUpperLeftPoint() {
		assertEquals(upperLeftPoint, rectangle.getUpperLeftPoint());
	}

	@Test
	void testSetUpperLeftPoint() {
		Point newUpperLeftPoint = rectangle.getUpperLeftPoint();
		rectangle.setUpperLeftPoint(newUpperLeftPoint);
		assertEquals(newUpperLeftPoint, rectangle.getUpperLeftPoint());
	}

	@Test
	void testGetWidth() {
		assertEquals(10, rectangle.getWidth());
	}

	@Test
	void testSetWidth() {
		rectangle.setWidth(15);
		assertEquals(15, rectangle.getWidth());

	}

	@Test
	void testGetHeight() {
		assertEquals(5, rectangle.getHeight());
	}

	@Test
	void testSetHeight() {
		rectangle.setHeight(8);
		assertEquals(8, rectangle.getHeight());
	}

	@Test
	void testToString() {
		String rectangleToString = rectangle.toString();
		assertEquals("Rectangle(X:1|Y:1|W:10|H:5|EdgeColor:-16777216|InnerColor:-1)", rectangleToString);
	}

	@Test
	void testParse() {
		String toParse = "Rectangle(X:1|Y:1|W:10|H:5|EdgeColor:-16777216|InnerColor:-1)";
		Rectangle rectangleParse = (Rectangle) Rectangle.parse(toParse);
		assertEquals(rectangle, rectangleParse);
	}

}
