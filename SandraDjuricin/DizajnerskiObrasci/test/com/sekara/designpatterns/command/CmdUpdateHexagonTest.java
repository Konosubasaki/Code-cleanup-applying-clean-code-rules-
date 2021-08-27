package com.sekara.designpatterns.command;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.HexagonAdapter;
import com.sekara.designpatterns.model.geometry.Point;

class CmdUpdateHexagonTest {

	private ModelDrawing model;
	private CmdUpdateHexagon cmdUpdateHexagon;
	HexagonAdapter firstHexagon;
	HexagonAdapter editedHexagon;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		firstHexagon = new HexagonAdapter(new Point(1, 1), 20, Color.BLACK, Color.WHITE);
		model.addShape(firstHexagon);
		editedHexagon = new HexagonAdapter(new Point(4, 4), 50, Color.BLACK, Color.WHITE);
		cmdUpdateHexagon = new CmdUpdateHexagon((HexagonAdapter) model.getShapeByIndex(0), editedHexagon);
	}

	@Test
	public void testUnExecuteExecuteNotCalled() {
		cmdUpdateHexagon.unExecute();
		assertEquals(firstHexagon, model.getShapeByIndex(0));
	}

	@Test
	public void testExecute() {
		cmdUpdateHexagon.execute();
		assertEquals(editedHexagon, model.getShapeByIndex(0));
	}

	@Test
	void testUnExecuteAfterExecute() {
		cmdUpdateHexagon.unExecute();
		assertEquals(firstHexagon, model.getShapeByIndex(0));
	}
}
