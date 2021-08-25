package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Point;

class CmdUpdateCircleTest {

	private ModelDrawing model;
	private CmdUpdateCircle cmdUpdateCircle;
	Circle firstCircle;
	Circle editedCircle;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstCircle = new Circle(new Point(1, 1), 20, Color.BLACK, Color.WHITE);
		model.addShape(firstCircle);
		editedCircle = new Circle(new Point(4, 4), 50, Color.BLACK, Color.WHITE);
		cmdUpdateCircle = new CmdUpdateCircle((Circle) model.getShapeByIndex(0), editedCircle);
	}

	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdateCircle.unExecute();
		assertEquals(firstCircle, model.getShapeByIndex(0));
	}

	@Test
	public void testExecute() {
		cmdUpdateCircle.execute();
		assertEquals(editedCircle, model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdateCircle.unExecute();
		assertEquals(firstCircle, model.getShapeByIndex(0));
	}
}
