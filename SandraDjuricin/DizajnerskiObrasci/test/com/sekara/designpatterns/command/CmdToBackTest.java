package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdToBackTest {
	private Point firstPoint;
	private Point secondPoint;
	private ModelDrawing model;
	private int indexOfShape;
	private CmdToBack cmdToBack;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstPoint = new Point(15, 20, Color.BLACK);
		secondPoint = new Point(5, 10, Color.BLACK);
		model.addShape(firstPoint);
		model.addShape(secondPoint);
		indexOfShape = model.getIndexOfShape(secondPoint);

		cmdToBack = new CmdToBack(secondPoint, model);
	}

	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdToBack.unExecute();
		assertEquals(indexOfShape, model.getIndexOfShape(secondPoint));
	}

	@Test
	public void testExecute() {
		cmdToBack.execute();
		assertEquals(indexOfShape - 1, model.getIndexOfShape(secondPoint));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdToBack.unExecute();
		assertEquals(indexOfShape, model.getIndexOfShape(secondPoint));
	}
}
