package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Donut;
import com.sekara.designpatterns.model.geometry.Point;

class CmdUpdateDonutTest {

	
	private ModelDrawing model;
	private CmdUpdateDonut cmdUpdateDonut;
	Donut firstDonut;
	Donut editedDonut;
	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstDonut = new Donut(new Point(1,1),20,10, Color.BLACK, Color.WHITE);
		model.addShape(firstDonut);
		editedDonut = new Donut(new Point(4,4),50,15, Color.BLACK, Color.WHITE);
		cmdUpdateDonut = new CmdUpdateDonut((Donut) model.getShapeByIndex(0), editedDonut);
  	}
	
	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdateDonut.unExecute();
		assertEquals(firstDonut,model.getShapeByIndex(0));
	}
	
	@Test
	public void testExecute() {
		cmdUpdateDonut.execute();
		assertEquals(editedDonut,model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdateDonut.unExecute();
		assertEquals(firstDonut,model.getShapeByIndex(0));
	}
}
