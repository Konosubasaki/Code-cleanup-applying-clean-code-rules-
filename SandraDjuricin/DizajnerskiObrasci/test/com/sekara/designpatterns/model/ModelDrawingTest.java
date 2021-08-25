package com.sekara.designpatterns.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.command.CmdAddShape;
import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Shape;

class ModelDrawingTest {
	private ModelDrawing model;
	private Point point;
	public List<Shape> shapes;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		point = new Point(5, 10, Color.BLACK);
		shapes = new ArrayList<Shape>();

		shapes.add(point);
		model.addShape(point);
	}

	@Test
	void testGetAllShapes() {
		assertEquals(shapes, model.getAllShapes());

	}

	@Test
	void testGetShapeByIndex() {
		assertEquals(shapes.get(0), model.getShapeByIndex(0));
	}

	@Test
	void testGetIndexOfShape() {
		assertEquals(0, model.getIndexOfShape(point));
	}

	@Test
	void testGetSizeOfShapeList() {
		assertEquals(1, model.getSizeOfShapeList());
	}

	@Test
	void testGetSelectedShapes() {
		point.setSelected(true);
		model.removeShapes(shapes);
		model.addShape(point);
		assertEquals(point, model.getSelectedShapes().get(0));
	}

	@Test
	void testGetSizeSelectedShapes() {
		point.setSelected(true);
		model.removeShapes(shapes);
		model.addShape(point);
		assertEquals(1, model.getSizeSelectedShapes());
	}

	@Test
	void testAddShape() {
		assertEquals(1, model.getSizeOfShapeList());
		Point newPoint = new Point(15, 15, Color.BLACK);
		model.addShape(newPoint);
		assertEquals(2, model.getSizeOfShapeList());

	}

	@Test
	void testAddShapeAtIndex() {
		Point newPoint = new Point(15, 15, Color.BLACK);
		model.addShapeAtIndex(newPoint, 0);
		;
		assertEquals(0, model.getIndexOfShape(newPoint));
	}

	@Test
	void testAddAllShapes() {
		Point newPoint = new Point(15, 15, Color.BLACK);
		shapes.add(newPoint);
		shapes.add(newPoint);
		shapes.add(newPoint);
		model.addAllShapes(shapes);
		assertEquals(5, model.getSizeOfShapeList());
	}

	@Test
	void testUpdateShape() {
		Point newPoint = new Point(15, 15, Color.BLACK);
		model.updateShape(point, newPoint);
		assertEquals(newPoint, model.getShapeByIndex(0));

	}

	@Test
	void testRemoveShape() {
		model.removeShape(point);
		assertEquals(0, model.getSizeOfShapeList());
	}

	@Test
	void testRemoveShapes() {
		Point newPoint = new Point(15, 15, Color.BLACK);
		model.addShape(newPoint);
		model.addShape(newPoint);

		model.removeShapes(shapes);
		assertEquals(2, model.getSizeOfShapeList());
	}

}
