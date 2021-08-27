package com.sekara.designpatterns.model.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LineTest {

	private Line line;
	private Point startPoint;
	private Point endPoint;

	@BeforeEach
	public void initialization() {
		startPoint = new Point(1, 1);
		endPoint = new Point(1, 10);
		line = new Line(startPoint, endPoint, Color.BLACK);
	}

	@Test
	void testContainsXYpointTrue() {
		assertTrue(line.containsXYpoint(1, 5));
	}

	@Test
	void testContainsXYpointFalse() {
		assertFalse(line.containsXYpoint(1, 11));
	}

	@Test
	void testClone() {
		Line lineClone = (Line) line.clone();
		assertEquals(line, lineClone);
	}

	@Test
	void testMoveBy() {
		line.moveBy(5, 5);
		assertEquals(1 + 5, line.getStartPoint().getXCoordinate());
		assertEquals(1 + 5, line.getStartPoint().getYCoordinate());

		assertEquals(1 + 5, line.getEndPoint().getXCoordinate());
		assertEquals(10 + 5, line.getEndPoint().getYCoordinate());

	}

	@Test
	void testMiddleOfLine() {
		Point middle = new Point(1, 5);
		assertEquals(middle, line.middleOfLine());
	}

	@Test
	void testEqualsObject() {
		Line equalLine = new Line(startPoint, endPoint, Color.BLACK);
		assertTrue(line.equals(equalLine));
	}

	@Test
	void testLength() {
		assertEquals(9, line.length());
	}

	@Test
	void testGetStartPoint() {
		assertEquals(startPoint, line.getStartPoint());
	}

	@Test
	void testSetStartPoint() {
		Point newPoint = new Point(15, 15);
		line.setStartPoint(newPoint);
		assertEquals(newPoint, line.getStartPoint());
	}

	@Test
	void testGetEndPoint() {
		assertEquals(endPoint, line.getEndPoint());
	}

	@Test
	void testSetEndPoint() {
		Point newPoint = new Point(15, 15);
		line.setEndPoint(newPoint);
		assertEquals(newPoint, line.getEndPoint());
	}

	@Test
	void testToString() {
		String lineToString = line.toString();
		assertEquals("Line(X1:1|Y1:1|X2:1|Y2:10|EdgeColor:-16777216)", lineToString);
	}

	@Test
	void testParse() {
		String toParse = "Line(X1:1|Y1:1|X2:1|Y2:10|EdgeColor:-16777216)";
		Line lineParse = (Line) Line.parse(toParse);
		assertEquals(line, lineParse);
	}

}
