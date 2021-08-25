package com.sekara.designpatterns.model.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;

import hexagon.Hexagon;

class HexagonShapeTest {
	private ModelDrawing model;
	private HexagonShape hexagon;
	private Point centerOfHexagon;

	@BeforeEach
	public void initialization() {
		// graphics = mock(Graphics.class);
		model = new ModelDrawing();
		centerOfHexagon = new Point(1, 1);
		hexagon = new HexagonShape(centerOfHexagon, 20, Color.BLACK, Color.WHITE);
	}

	@Test
	void testContainsXYpoint() {
		assertTrue(hexagon.containsXYpoint(centerOfHexagon.getXCoordinate(), centerOfHexagon.getYCoordinate()));
	}

	@Test
	void testIsSelectedTrue() {
		hexagon.setSelected(true);
		assertTrue(hexagon.isSelected());
	}

	@Test
	void testIsSelectedFalse() {
		hexagon.setSelected(false);
		assertFalse(hexagon.isSelected());
	}

	@Test
	void testSetSelected() {
		hexagon.setSelected(true);
		model.addShape(hexagon);
		assertEquals(1, model.getSizeSelectedShapes());

	}

	@Test
	void testGetEdgeColor() {
		assertEquals(Color.BLACK, hexagon.getEdgeColor());
	}

	@Test
	void testSetEdgeColor() {
		hexagon.setEdgeColor(Color.WHITE);
		assertEquals(Color.WHITE, hexagon.getEdgeColor());
	}

	@Test
	void testGetInnerColor() {
		assertEquals(Color.WHITE, hexagon.getInnerColor());
	}

	@Test
	void testSetInnerColor() {
		hexagon.setInnerColor(Color.BLACK);
		assertEquals(Color.BLACK, hexagon.getInnerColor());
	}

	@Test
	void testClone() {
		HexagonShape hexagonClone = (HexagonShape) hexagon.clone();
		assertEquals(hexagon, hexagonClone);
	}

	@Test
	void testMoveBy() {
		Point newCenter = centerOfHexagon;
		newCenter.setXCoordinate(newCenter.getXCoordinate() + 5);
		newCenter.setYCoordinate(newCenter.getYCoordinate() + 5);
		HexagonShape newHexagon = new HexagonShape(newCenter, 20, Color.BLACK, Color.WHITE);

		hexagon.moveBy(5, 5);
		assertEquals(newHexagon, hexagon);

	}

	@Test
	void testCompareTo() {
		HexagonShape secondHexagonShape = new HexagonShape(centerOfHexagon, 10, Color.BLACK, Color.WHITE);
		assertEquals(10, hexagon.compareTo(secondHexagonShape));
	}

	@Test
	void testGetHexagonShape() {
		assertTrue(hexagon.getHexagon() instanceof Hexagon);
	}

	@Test
	void testToString() {
		String hexagonToString = hexagon.toString();
		assertEquals("Hexagon(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)", hexagonToString);
	}

	@Test
	void testParse() {
		String toParse = "Hexagon(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)";
		HexagonShape hexagonParse = (HexagonShape) HexagonShape.parse(toParse);
		assertEquals(hexagon, hexagonParse);
	}

	@Test
	void testEqualsObject() {
		HexagonShape equalHexagon = new HexagonShape(centerOfHexagon, 20, Color.BLACK, Color.WHITE);
		assertTrue(hexagon.equals(equalHexagon));
	}

}
