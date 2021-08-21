package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdBringToBackTest {

	private Point point;
	private Point secondPoint;
	private ModelDrawing model;
	private int indexOfShape;
	private CmdBringToBack cmdBringToBack;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		secondPoint = new Point(5,10, Color.BLACK);
		point = new Point(5,10, Color.BLACK);
		model.addShape(secondPoint);
 		model.addShape(point);
		cmdBringToBack = new CmdBringToBack(point, model);
		indexOfShape = model.getIndexOfShape(point);
	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdBringToBack.unExecute();
		assertEquals(indexOfShape,model.getIndexOfShape(point));
	}
	
	@Test
	public void testExecute() {
		cmdBringToBack.execute();
		assertEquals(0,model.getIndexOfShape(point));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdBringToBack.unExecute();
		assertEquals(indexOfShape,model.getIndexOfShape(point));
	}
}
