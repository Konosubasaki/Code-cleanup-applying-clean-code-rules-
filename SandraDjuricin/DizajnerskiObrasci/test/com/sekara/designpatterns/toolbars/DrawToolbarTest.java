package com.sekara.designpatterns.toolbars;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.command.CmdDeleteShapes;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Shape;

class DrawToolbarTest {
	
	DrawToolbar toolbar;
	@BeforeEach
	public void initialization() {
		toolbar= new DrawToolbar();
	}

	@Test
	void testButtonsActionListeners() {
		fail("Not yet implemented");
	}

	@Test
	void testSetControllers() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPnlLeftOperations() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPnlRightOperations() {
		fail("Not yet implemented");
	}

	@Test
	void testGetViewDrawging() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDefaultListLogModel() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPnlLog() {
		fail("Not yet implemented");
	}

	@Test
	void testGetReadFromFileChooser() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSaveToFileChooser() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBtnReadNextCommand() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBtnColorEdge() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBtnColorInner() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateUndoRedoButtonsState() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateFileButtonState() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateShapeManipulationButtonsState() {
		fail("Not yet implemented");
	}

}
