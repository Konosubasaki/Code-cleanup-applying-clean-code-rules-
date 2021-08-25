package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdUpdatePointTest {
	
	private ModelDrawing model;
	private CmdUpdatePoint cmdUpdatePoint;
	Point firstPoint;
	Point editedPoint;
	
	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstPoint = new Point(15,20, Color.BLACK);
		model.addShape(firstPoint);
		editedPoint = new Point(5,10, Color.BLACK);
		cmdUpdatePoint = new CmdUpdatePoint((Point) model.getShapeByIndex(0), editedPoint);
  	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdatePoint.unExecute();
		assertEquals(firstPoint,model.getShapeByIndex(0));
	}
	
	@Test
	public void testExecute() {
		cmdUpdatePoint.execute();
		assertEquals(editedPoint,model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdatePoint.unExecute();
		assertEquals(firstPoint,model.getShapeByIndex(0));
	}
}
