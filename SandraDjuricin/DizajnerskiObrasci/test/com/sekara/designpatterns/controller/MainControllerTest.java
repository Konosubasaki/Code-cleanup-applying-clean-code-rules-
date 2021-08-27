package com.sekara.designpatterns.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.command.CmdAddShape;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Donut;
import com.sekara.designpatterns.model.geometry.HexagonShape;
import com.sekara.designpatterns.model.geometry.Line;
import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Rectangle;
import com.sekara.designpatterns.model.geometry.Shape;
import com.sekara.designpatterns.view.frame.FrameDrawing;

class MainControllerTest {
	private MainController mainController;
	ModelDrawing model;
	MouseEvent mouse;

	@BeforeEach
	public void initialization() {
		model = new ModelDrawing();
		FrameDrawing frame = new FrameDrawing();
		mainController = new MainController(model, frame);
		// mouse=new MouseEvent();
	}

	@Test
	void testExecuteCommand() {
		Shape point = new Point(1, 1, Color.BLACK);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		assertEquals(null, cmdAddShape.getCommandLog());

		mainController.executeCommand(cmdAddShape);
		assertEquals("CMD_ADD_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)", cmdAddShape.getCommandLog());
	}

	@Test
	void testUnexecuteCommand() {
		Shape point = new Point(1, 1, Color.BLACK);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		assertEquals(null, cmdAddShape.getCommandLog());

		mainController.unexecuteCommand(cmdAddShape);
		assertEquals("CMD_ADD_UNEXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)", cmdAddShape.getCommandLog());
	}

	@Test
	void testMouseClicked() {
	}

	@Test
	void testEditShapePoint() {
		Shape point = new Point(1, 1, Color.BLACK);
		point.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		mainController.executeCommand(cmdAddShape);

		mainController.editShape();
		assertEquals("CMD_UPDATE_POINT_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)->Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());
	}

	@Test
	void testEditShapeLine() {
		Point startPoint = new Point(1, 1);
		Point endPoint = new Point(1, 10);
		Shape line = new Line(startPoint, endPoint, Color.BLACK);
		line.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(line, model);
		mainController.executeCommand(cmdAddShape);

		mainController.editShape();
		assertEquals(
				"CMD_UPDATE_LINE_EXECUTE#Line(X1:1|Y1:1|X2:1|Y2:10|EdgeColor:-16777216)->Line(X1:1|Y1:1|X2:1|Y2:10|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

	@Test
	void testEditShapeRectangle() {
		Point upperLeftPoint = new Point(1, 1);
		Shape rectangle = new Rectangle(upperLeftPoint, 5, 10, Color.BLACK, Color.WHITE);
		rectangle.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(rectangle, model);
		mainController.executeCommand(cmdAddShape);

		mainController.editShape();
		assertEquals(
				"CMD_UPDATE_RECTANGLE_EXECUTE#Rectangle(X:1|Y:1|W:10|H:5|EdgeColor:-16777216|InnerColor:-1)->Rectangle(X:1|Y:1|W:10|H:5|EdgeColor:-16777216|InnerColor:-1)",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

	@Test
	void testEditShapeDonut() {
		Point centerOfDonut = new Point(1, 1);
		Shape donut = new Donut(centerOfDonut, 20, 10, Color.BLACK, Color.WHITE);
		donut.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(donut, model);
		mainController.executeCommand(cmdAddShape);

		mainController.editShape();
		assertEquals(
				"CMD_UPDATE_DONUT_EXECUTE#Donut(X:1|Y:1|R1:20|R2:10|EdgeColor:-16777216|InnerColor:-1)->Donut(X:1|Y:1|R1:20|R2:10|EdgeColor:-16777216|InnerColor:-1)",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

	@Test
	void testEditShapeCircle() {
		Point centerOfCircle = new Point(1, 1);
		Shape circle = new Circle(centerOfCircle, 20, Color.BLACK, Color.WHITE);
		circle.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(circle, model);
		mainController.executeCommand(cmdAddShape);

		mainController.editShape();
		assertEquals(
				"CMD_UPDATE_CIRCLE_EXECUTE#Circle(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)->Circle(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

	@Test
	void testEditShapeHexagonShape() {
		Point centerOfHexagon = new Point(1, 1);
		Shape hexagon = new HexagonShape(centerOfHexagon, 20, Color.BLACK, Color.WHITE);
		hexagon.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(hexagon, model);
		mainController.executeCommand(cmdAddShape);

		mainController.editShape();
		assertEquals(
				"CMD_UPDATE_HEXAGON_EXECUTE#Hexagon(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)->Hexagon(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

	@Test
	void testDeleteSelectedShapes() {
		Shape point = new Point(1, 1, Color.BLACK);
		point.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		mainController.executeCommand(cmdAddShape);

		mainController.deleteSelectedShapes();
		assertEquals("CMD_DELETE_EXECUTE#[Point(X:1|Y:1|EdgeColor:-16777216)]",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

	@Test
	void testDeleteAllShapes() {
		Shape point = new Point(1, 1, Color.BLACK);
		CmdAddShape cmdAddShapeP = new CmdAddShape(point, model);
		mainController.executeCommand(cmdAddShapeP);

		Point centerOfHexagon = new Point(1, 1);
		Shape hexagon = new HexagonShape(centerOfHexagon, 20, Color.BLACK, Color.WHITE);
		CmdAddShape cmdAddShapeH = new CmdAddShape(hexagon, model);
		mainController.executeCommand(cmdAddShapeH);

		mainController.deleteAllShapes();
		assertEquals(
				"CMD_DELETE_EXECUTE#[Point(X:1|Y:1|EdgeColor:-16777216), Hexagon(X:1|Y:1|R:20|EdgeColor:-16777216|InnerColor:-1)]",
				mainController.getExecutedCommands().pop().getCommandLog());
	}

	@Test
	void testPositionToFront() {
		Shape point = new Point(1, 1, Color.BLACK);
		Shape unselectedpoint = new Point(2, 1, Color.BLACK);
		point.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		CmdAddShape cmdAddShapeUnselectedpoint = new CmdAddShape(unselectedpoint, model);
		mainController.executeCommand(cmdAddShape);
		mainController.executeCommand(cmdAddShapeUnselectedpoint);

		mainController.positionToFront();
		assertEquals("CMD_TO_FRONT_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());
	}

	@Test
	void testPositionToBack() {
		Shape point = new Point(1, 1, Color.BLACK);
		Shape unselectedpoint = new Point(2, 1, Color.BLACK);
		point.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		CmdAddShape cmdAddShapeUnselectedpoint = new CmdAddShape(unselectedpoint, model);

		mainController.executeCommand(cmdAddShapeUnselectedpoint);
		mainController.executeCommand(cmdAddShape);

		mainController.positionToBack();
		assertEquals("CMD_TO_BACK_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());
	}

	@Test
	void testPositionBringToFront() {
		Shape point = new Point(1, 1, Color.BLACK);
		Shape unselectedpoint = new Point(2, 1, Color.BLACK);
		point.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		CmdAddShape cmdAddShapeUnselectedpoint = new CmdAddShape(unselectedpoint, model);
		mainController.executeCommand(cmdAddShape);
		mainController.executeCommand(cmdAddShapeUnselectedpoint);

		mainController.positionBringToFront();
		assertEquals("CMD_BRING_TO_FRONT_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());
	}

	@Test
	void testPositionBringToBack() {
		Shape point = new Point(1, 1, Color.BLACK);
		Shape unselectedpoint = new Point(2, 1, Color.BLACK);
		point.setSelected(true);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		CmdAddShape cmdAddShapeUnselectedpoint = new CmdAddShape(unselectedpoint, model);
		mainController.executeCommand(cmdAddShapeUnselectedpoint);
		mainController.executeCommand(cmdAddShape);

		mainController.positionBringToBack();
		assertEquals("CMD_BRING_TO_BACK_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());
	}

	@Test
	void testUndoCommand() {
		Shape point = new Point(1, 1, Color.BLACK);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		mainController.executeCommand(cmdAddShape);

		mainController.undoCommand();
		assertEquals("CMD_ADD_UNEXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getUnexecutedCommands().pop().getCommandLog());

	}

	@Test
	void testRedoCommand() {
		Shape point = new Point(1, 1, Color.BLACK);
		CmdAddShape cmdAddShape = new CmdAddShape(point, model);
		mainController.executeCommand(cmdAddShape);
		mainController.undoCommand();
		mainController.redoCommand();
		assertEquals("CMD_ADD_EXECUTE#Point(X:1|Y:1|EdgeColor:-16777216)",
				mainController.getExecutedCommands().pop().getCommandLog());

	}

}
