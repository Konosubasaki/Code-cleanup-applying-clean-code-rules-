package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Rectangle;

class CmdUpdateRectangleTest {

	private ModelDrawing model;
	private CmdUpdateRectangle cmdUpdateRectangle;
	Rectangle firstRectangle;
	Rectangle editedRectangle;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstRectangle = new Rectangle(new Point(1, 1), 10, 20, Color.BLACK, Color.WHITE);
		model.addShape(firstRectangle);
		editedRectangle = new Rectangle(new Point(4, 4), 30, 50, Color.BLACK, Color.WHITE);
		cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle) model.getShapeByIndex(0), editedRectangle);
	}

	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdateRectangle.unExecute();
		assertEquals(firstRectangle, model.getShapeByIndex(0));
	}

	@Test
	public void testExecute() {
		cmdUpdateRectangle.execute();
		assertEquals(editedRectangle, model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdateRectangle.unExecute();
		assertEquals(firstRectangle, model.getShapeByIndex(0));
	}
}
