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
