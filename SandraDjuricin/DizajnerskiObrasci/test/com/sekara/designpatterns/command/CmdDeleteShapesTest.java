package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Shape;

class CmdDeleteShapesTest {

	private List<Shape> shapes;
	private ModelDrawing model;
	private CmdDeleteShapes cmdDeleteShapes;

	@BeforeEach
	public void initialization() {
		Point firstPoint = new Point(15, 20, Color.BLACK);
		Point secondPoint = new Point(5, 10, Color.BLACK);
		model = new ModelDrawing();
		model.addShape(firstPoint);
		model.addShape(secondPoint);
		shapes = new ArrayList<Shape>();
		shapes.add(firstPoint);
		shapes.add(secondPoint);
		cmdDeleteShapes = new CmdDeleteShapes(shapes, model);
	}

	@Test
	public void testExecute() {
		int totalShapes = model.getSizeOfShapeList();
		cmdDeleteShapes.execute();
		assertEquals(totalShapes - 2, model.getSizeOfShapeList());
	}

	@Test
	void testUnExecute() {
		int totalShapes = model.getSizeOfShapeList();
		cmdDeleteShapes.unExecute();
		assertEquals(totalShapes + 2, model.getSizeOfShapeList());
	}
}
