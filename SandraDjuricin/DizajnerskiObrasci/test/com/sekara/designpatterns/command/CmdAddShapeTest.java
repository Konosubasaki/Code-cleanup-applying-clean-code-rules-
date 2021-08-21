package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdAddShapeTest {

	private ModelDrawing model;
	private Point point;
	private CmdAddShape cmdAddShape;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		point = new Point(5,10, Color.BLACK);
		cmdAddShape = new CmdAddShape(point, model);
	}

	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdAddShape.unExecute();
		assertFalse(model.containsShape(point));
	}

	@Test
	public void testExecute() {
		cmdAddShape.execute();
		assertTrue(model.containsShape(point));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdAddShape.unExecute();
		assertFalse(model.containsShape(point));
	}
}

