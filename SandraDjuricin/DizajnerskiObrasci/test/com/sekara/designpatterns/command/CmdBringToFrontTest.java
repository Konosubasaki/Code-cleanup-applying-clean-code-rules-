package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdBringToFrontTest {
	private Point firstPoint;
	private Point secondPoint;
	private ModelDrawing model;
	private int indexOfShape;
	private CmdBringToFront cmdBringToFront;
	
	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstPoint = new Point(15,20, Color.BLACK);
		secondPoint = new Point(5,10, Color.BLACK);
   		model.addShape(firstPoint);
		model.addShape(secondPoint);
		cmdBringToFront = new CmdBringToFront(firstPoint, model);
		indexOfShape = model.getIndexOfShape(firstPoint);
	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdBringToFront.unExecute();
		assertEquals(indexOfShape,model.getIndexOfShape(firstPoint));
	}
	
	@Test
	public void testExecute() {
		cmdBringToFront.execute();
		assertEquals(model.getSizeOfShapeList()-1,model.getIndexOfShape(firstPoint));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdBringToFront.unExecute();
		assertEquals(indexOfShape,model.getIndexOfShape(firstPoint));
	}
}
