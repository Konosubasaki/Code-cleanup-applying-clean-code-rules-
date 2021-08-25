package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;

class CmdToFrontTest {
	private Point firstPoint;
	private Point secondPoint;
	private ModelDrawing model;
	private int indexOfShape;
	private CmdToFront cmdToFront;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstPoint = new Point(15, 20, Color.BLACK);
		secondPoint = new Point(5, 10, Color.BLACK);
		model.addShape(firstPoint);
		model.addShape(secondPoint);
		indexOfShape = model.getIndexOfShape(firstPoint);

		cmdToFront = new CmdToFront(firstPoint, model);
	}

	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdToFront.unExecute();
		assertEquals(indexOfShape, model.getIndexOfShape(firstPoint));
	}

	@Test
	public void testExecute() {
		cmdToFront.execute();
		assertEquals(indexOfShape + 1, model.getIndexOfShape(firstPoint));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdToFront.unExecute();
		assertEquals(indexOfShape, model.getIndexOfShape(firstPoint));
	}
}
