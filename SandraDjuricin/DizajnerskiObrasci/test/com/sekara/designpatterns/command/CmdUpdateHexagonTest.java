package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.HexagonShape;
import com.sekara.designpatterns.model.geometry.Point;

 
class CmdUpdateHexagonTest {

	private ModelDrawing model;
	private CmdUpdateHexagon cmdUpdateHexagon;
	HexagonShape firstHexagon;
	HexagonShape editedHexagon;
	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstHexagon = new HexagonShape(new Point(1,1),20, Color.BLACK, Color.WHITE);
		model.addShape(firstHexagon);
		editedHexagon = new HexagonShape(new Point(4,4),50, Color.BLACK, Color.WHITE);
		cmdUpdateHexagon = new CmdUpdateHexagon((HexagonShape) model.getShapeByIndex(0), editedHexagon);
  	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdateHexagon.unExecute();
		assertEquals(firstHexagon,model.getShapeByIndex(0));
	}
	
	@Test
	public void testExecute() {
		cmdUpdateHexagon.execute();
		assertEquals(editedHexagon,model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdateHexagon.unExecute();
		assertEquals(firstHexagon,model.getShapeByIndex(0));
	}
}
