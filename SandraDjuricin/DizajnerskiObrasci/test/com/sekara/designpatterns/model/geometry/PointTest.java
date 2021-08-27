package com.sekara.designpatterns.model.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {

	private Point point;

	@BeforeEach
	public void initialization() {
		// graphics = mock(Graphics.class);
		point = new Point(1, 1, Color.BLACK);
	}

	@Test
	void testContainsXYpointTrue() {
		assertTrue(point.containsXYpoint(1, 1));
	}

	@Test
	void testContainsXYpointFalse() {
		assertFalse(point.containsXYpoint(1, 11));
	}

	@Test
	void testClone() {
		Point pointClone = (Point) point.clone();
		assertEquals(point, pointClone);
	}

	@Test
	void testMoveBy() {
		point.moveBy(5, 5);
		assertEquals(1 + 5, point.getXCoordinate());
		assertEquals(1 + 5, point.getYCoordinate());
	}

	@Test
	void testEqualsObject() {
		Point equalPoint = new Point(1, 1, Color.BLACK);
		assertTrue(point.equals(equalPoint));
	}

	@Test
	void testDistance() {
		assertEquals(4, point.distance(1, 5));
	}

	@Test
	void testGetXCoordinate() {
		assertEquals(1, point.getXCoordinate());
	}

	@Test
	void testSetXCoordinate() {
		point.setXCoordinate(10);
		assertEquals(10, point.getXCoordinate());
	}

	@Test
	void testGetYCoordinate() {
		assertEquals(1, point.getYCoordinate());
	}

	@Test
	void testSetYCoordinate() {
		point.setYCoordinate(20);
		assertEquals(20, point.getYCoordinate());
	}

	@Test
	void testToString() {
		String pointToString = point.toString();
		assertEquals("Point(X:1|Y:1|EdgeColor:-16777216)", pointToString);
	}

	@Test
	void testParse() {
		String toParse = "Point(X:1|Y:1|EdgeColor:-16777216)";
		Point pointParse = (Point) Point.parse(toParse);
		assertEquals(point, pointParse);
	}

}
