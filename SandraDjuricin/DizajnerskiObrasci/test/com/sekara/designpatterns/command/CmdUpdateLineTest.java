package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Line;
import com.sekara.designpatterns.model.geometry.Point;

class CmdUpdateLineTest {
	
	private ModelDrawing model;
	private CmdUpdateLine cmdUpdateLine;
	Line firstLine;
	Line editedLine;
	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstLine = new Line(new Point(1,1), new Point(10,10), Color.BLACK);
		model.addShape(firstLine);
		editedLine = new Line(new Point(4,4), new Point(40,40), Color.BLACK);
		cmdUpdateLine = new CmdUpdateLine((Line) model.getShapeByIndex(0), editedLine);
  	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdateLine.unExecute();
		assertEquals(firstLine,model.getShapeByIndex(0));
	}
	
	@Test
	public void testExecute() {
		cmdUpdateLine.execute();
		assertEquals(editedLine,model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdateLine.unExecute();
		assertEquals(firstLine,model.getShapeByIndex(0));
	}
}
