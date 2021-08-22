package com.sekara.designpatterns.model.geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DonutTest {

	private Graphics graphics;
	private Donut donut;
	private Point centerOfDonut;
	@BeforeEach
	public void initialization() {
	//	graphics = mock(Graphics.class);
		centerOfDonut=new Point(1,1);
		donut = new Donut(centerOfDonut,20,10, Color.BLACK, Color.WHITE);
  	}
	@Test
	void testContainsXYpoint() {
		assertFalse(donut.containsXYpoint(centerOfDonut.getXCoordinate(), centerOfDonut.getYCoordinate()));
	}

	@Test
	void testDraw() {
		fail("Not yet implemented");
	}

	@Test
	void testClone() {
		Donut donutClone=(Donut)donut.clone();
		assertEquals(donut,donutClone);
	}

	@Test
	void testCompareTo() {
		Donut secondDonut = new Donut(centerOfDonut,10,5, Color.BLACK, Color.WHITE);
		double area= secondDonut.area();
		assertEquals((int)(donut.area()-area),donut.compareTo(secondDonut));
	}

	@Test
	void testEqualsObject() {
		Donut equalDonut = new Donut(centerOfDonut,20,10, Color.BLACK, Color.WHITE);
		assertTrue(donut.equals(equalDonut));
	}

	@Test
	void testArea() {
		double Area= (20*20* Math.PI)-(10*10* Math.PI);
		assertEquals(Area,donut.area());
	}

	@Test
	void testToString() {
		String donutToString=donut.toString();
		assertEquals("Donut(X:1|Y:1|R1:20|R2:10|EdgeColor:-16777216|InnerColor:-1)",donutToString);
	}

	@Test
	void testGetInnerRadius() {
		int radius=donut.getRadius();
		assertEquals(20,radius);
	}

	@Test
	void testSetInnerRadius() {
		int newRadius=50;
		donut.setRadius(newRadius);
		assertEquals(newRadius,donut.getRadius());
	}

	@Test
	void testParseString() {
		String toParse="Donut(X:1|Y:1|R1:20|R2:10|EdgeColor:-16777216|InnerColor:-1)";
		Donut donutParse=(Donut)donut.parse(toParse);
		assertEquals(donut,donutParse);
	}

}