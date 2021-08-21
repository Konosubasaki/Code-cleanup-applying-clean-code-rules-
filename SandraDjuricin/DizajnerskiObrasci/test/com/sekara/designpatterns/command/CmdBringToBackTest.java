package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdBringToBackTest {

	private Point firstPoint;
	private Point secondPoint;
	private ModelDrawing model;
	private int indexOfShape;
	private CmdBringToBack cmdBringToBack;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstPoint = new Point(15,20, Color.BLACK);
		secondPoint = new Point(5,10, Color.BLACK);
   		model.addShape(firstPoint);
		model.addShape(secondPoint);
		cmdBringToBack = new CmdBringToBack(secondPoint, model);
		indexOfShape = model.getIndexOfShape(secondPoint);
	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdBringToBack.unExecute();
		assertEquals(indexOfShape,model.getIndexOfShape(secondPoint));
	}
	
	@Test
	public void testExecute() {
		cmdBringToBack.execute();
		assertEquals(0,model.getIndexOfShape(secondPoint));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdBringToBack.unExecute();
		assertEquals(indexOfShape,model.getIndexOfShape(firstPoint));
	}
}
