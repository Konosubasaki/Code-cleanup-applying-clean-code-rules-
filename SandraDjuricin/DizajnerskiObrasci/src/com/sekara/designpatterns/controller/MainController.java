package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.command.*;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.*;
import com.sekara.designpatterns.observable.Subject;
import com.sekara.designpatterns.observable.Observer;
import com.sekara.designpatterns.view.dialog.*;
import com.sekara.designpatterns.view.frame.FrameDrawing;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class MainController implements Subject {

	private ModelDrawing model;
	private FrameDrawing frame;
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	private ShapeType selectedShape;
	private ModeType currentMode;
	private Color edgeColor;
	private Color innerColor;
	private boolean lineIsWaitingForSecondPoint;
	private Point lineFirstPoint;
	private List<Observer> listOfObservers;
	private LoggingController loggingController;

	public MainController(ModelDrawing model, FrameDrawing frame) {
		this.model = model;
		this.frame = frame;
		this.executedCommands = new Stack<Command>();
		this.unexecutedCommands = new Stack<Command>();
		this.edgeColor = Color.BLACK;
		this.innerColor = Color.WHITE;
		this.currentMode = ModeType.Drawing;
		this.selectedShape = ShapeType.Point;
		this.listOfObservers = new ArrayList<Observer>();
		loggingController = new LoggingController(this.frame);
		setEnabledBtnReadNextCommand(false);
	}

	public void executeCommand(Command command) {
		command.execute();
		loggingController.addLog(command.getCommandLog());
		executedCommands.push(command);
		frame.getViewDrawing().repaint();
		notifyObservers();
	}

	public void unexecuteCommand(Command command) {
		command.unExecute();
		loggingController.addLog(command.getCommandLog());
		unexecutedCommands.push(command);
		frame.getViewDrawing().repaint();
		notifyObservers();
	}

	public void mouseClicked(MouseEvent event) {
		switch (currentMode) {
		case Drawing:
			mouseClickedDraw(event);
			break;

		case Selecting:
			mouseClickedSelect(event);
			break;

		default:
			break;
		}
	}

	private void mouseClickedDraw(MouseEvent event) {
		Point mouseClick = new Point(event.getX(), event.getY());
		if (selectedShape == ShapeType.Point)
			drawPoint(mouseClick);
		else if (selectedShape == ShapeType.Line)
			drawLine(mouseClick);
		else if (selectedShape == ShapeType.Rectangle)
			drawRectangle(mouseClick);
		else if (selectedShape == ShapeType.Circle)
			drawCircle(mouseClick); 
		else if (selectedShape == ShapeType.Donut)
			drawDonut(mouseClick);
		else if (selectedShape == ShapeType.Hexagon)
			drawHexagon(mouseClick);
	} 

	public void drawPoint(Point mouseClick) {
		CmdAddShape cmdAddShape;
		Point point = new Point(mouseClick, edgeColor);
		cmdAddShape = new CmdAddShape(point, model);
		executeCommand(cmdAddShape);
	}

	public void drawLine(Point mouseClick) {
		CmdAddShape cmdAddShape;
		if (lineIsWaitingForSecondPoint) {
			Point lineSecondPoint = mouseClick;
			Line line = new Line(lineFirstPoint, lineSecondPoint, edgeColor);
			cmdAddShape = new CmdAddShape(line, model);
			executeCommand(cmdAddShape);
			lineIsWaitingForSecondPoint = false;
			return;
		}
		lineFirstPoint = mouseClick;
		lineIsWaitingForSecondPoint = true;
	}

	public void drawRectangle(Point mouseClick) {
		CmdAddShape cmdAddShape;
		DialogRectangle dlgRectangle = new DialogRectangle();
		dlgRectangle.setPoint(mouseClick);
		dlgRectangle.setColors(edgeColor, innerColor);
		dlgRectangle.setVisible(true);

		if (dlgRectangle.getRectangle() != null) {
			cmdAddShape = new CmdAddShape(dlgRectangle.getRectangle(), model);
			executeCommand(cmdAddShape);
		}
	}

	public void drawCircle(Point mouseClick) {
		CmdAddShape cmdAddShape;
		DialogCircle dlgCircle = new DialogCircle();
		dlgCircle.setPoint(mouseClick);
		dlgCircle.setColors(edgeColor, innerColor);
		dlgCircle.setVisible(true);

		if (dlgCircle.getCircle() != null) {
			cmdAddShape = new CmdAddShape(dlgCircle.getCircle(), model);
			executeCommand(cmdAddShape);
		}
	}

	public void drawDonut(Point mouseClick) {
		CmdAddShape cmdAddShape;
		DialogDonut dlgDonut = new DialogDonut();
		dlgDonut.setPoint(mouseClick);
		dlgDonut.setColors(edgeColor, innerColor);
		dlgDonut.setVisible(true);

		if (dlgDonut.getDonut() != null) {
			cmdAddShape = new CmdAddShape(dlgDonut.getDonut(), model);
			executeCommand(cmdAddShape);
		}
	}

	public void drawHexagon(Point mouseClick) {
		CmdAddShape cmdAddShape;
		DialogHexagon dlgHexagon = new DialogHexagon();
		dlgHexagon.setPoint(mouseClick);
		dlgHexagon.setColors(edgeColor, innerColor);
		dlgHexagon.setVisible(true);

		if (dlgHexagon.getHexagon() != null) {
			cmdAddShape = new CmdAddShape(dlgHexagon.getHexagon(), model);
			executeCommand(cmdAddShape);
		}
	}

	private void mouseClickedSelect(MouseEvent event) {
		model.getAllShapes().forEach(shape -> {
			if (shape.containsXYpoint(event.getX(), event.getY())) {
				if (shape.isSelected()) {
					shape.setSelected(false);
					loggingController
							.addLog("DESELECT#" + shape + "#MouseEvent(X:" + event.getX() + "|Y:" + event.getY() + ")");
				} else {
					shape.setSelected(true);
					loggingController
							.addLog("SELECT#" + shape + "#MouseEvent(X:" + event.getX() + "|Y:" + event.getY() + ")");
				}
			}
		});

		frame.getViewDrawing().repaint();
		notifyObservers();
	}

	public void editShape() {
		Shape selectedShape = model.getSelectedShapes().get(0);

		if (selectedShape instanceof Point) {
			DialogPoint dialogPoint = new DialogPoint();
			dialogPoint.setPoint((Point) selectedShape);
			dialogPoint.setVisible(true);

			if (dialogPoint.getPoint() != null) {
				CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint((Point) selectedShape, dialogPoint.getPoint());
				executeCommand(cmdUpdatePoint);
			}
		} else if (selectedShape instanceof Line) {
			DialogLine dialogLine = new DialogLine();
			dialogLine.setLine((Line) selectedShape);
			dialogLine.setVisible(true);

			if (dialogLine.getLine() != null) {
				CmdUpdateLine cmdUpdateLine = new CmdUpdateLine((Line) selectedShape, dialogLine.getLine());
				executeCommand(cmdUpdateLine);
			}
		} else if (selectedShape instanceof Rectangle) {
			DialogRectangle dialogRectangle = new DialogRectangle();
			dialogRectangle.setRectangle((Rectangle) selectedShape);
			dialogRectangle.setVisible(true);

			if (dialogRectangle.getRectangle() != null) {
				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle) selectedShape,
						dialogRectangle.getRectangle());
				executeCommand(cmdUpdateRectangle);
			}
		} else if (selectedShape instanceof Donut) {
			DialogDonut dialogDonut = new DialogDonut();
			dialogDonut.setDonut((Donut) selectedShape);
			dialogDonut.setVisible(true);

			if (dialogDonut.getDonut() != null) {
				CmdUpdateDonut cmdUpdateDonut = new CmdUpdateDonut((Donut) selectedShape, dialogDonut.getDonut());
				executeCommand(cmdUpdateDonut);
			}
		} else if (selectedShape instanceof Circle) {
			DialogCircle dialogCircle = new DialogCircle();
			dialogCircle.setCircle((Circle) selectedShape);
			dialogCircle.setVisible(true);

			if (dialogCircle.getCircle() != null) {
				CmdUpdateCircle cmdUpdateCircle = new CmdUpdateCircle((Circle) selectedShape, dialogCircle.getCircle());
				executeCommand(cmdUpdateCircle);
			}
		} else if (selectedShape instanceof HexagonAdapter) {
			DialogHexagon dialogHexagon = new DialogHexagon();
			dialogHexagon.setHexagon((HexagonAdapter) selectedShape);
			dialogHexagon.setVisible(true);

			if (dialogHexagon.getHexagon() != null) {
				CmdUpdateHexagon cmdUpdateHexagon = new CmdUpdateHexagon((HexagonAdapter) selectedShape,
						dialogHexagon.getHexagon());
				executeCommand(cmdUpdateHexagon);
			}
		}
	}

	public void deleteSelectedShapes() {
		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete selektovane oblike?", "Potvrda",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(model.getSelectedShapes(), model);
			executeCommand(cmdDeleteShapes);
		}
	}

	public void deleteAllShapes() {
		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete crtez?", "Potvrda",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(model.getAllShapes(), model);
			executeCommand(cmdDeleteShapes);
		}
	}

	public void positionToFront() {
		Shape selectedShape = model.getSelectedShapes().get(0);
		int shapeIndex = model.getIndexOfShape(selectedShape);
		int totalShapes = model.getSizeOfShapeList();

		if (shapeIndex >= totalShapes - 1) {
			showMessageDialog("Izabrani oblik se vec nalazi na najvisoj poziciji!");
			return;
		}

		CmdToFront cmdToFront = new CmdToFront(selectedShape, model);
		executeCommand(cmdToFront);
	}

	public void positionBringToFront() {
		Shape selectedShape = model.getSelectedShapes().get(0);
		int shapeIndex = model.getIndexOfShape(selectedShape);
		int totalShapes = model.getSizeOfShapeList();

		if (shapeIndex >= totalShapes - 1) {
			showMessageDialog("Izabrani oblik se vec nalazi na najvisoj poziciji!");
			return;
		}

		CmdBringToFront cmdBringToFront = new CmdBringToFront(selectedShape, model);
		executeCommand(cmdBringToFront);
	}

	public void positionToBack() {
		Shape selectedShape = model.getSelectedShapes().get(0);
		int shapeIndex = model.getIndexOfShape(selectedShape);

		if (shapeIndex <= 0) {
			showMessageDialog("Izabrani oblik se vec nalazi na najnizoj poziciji!");
			return;
		}

		CmdToBack cmdToBack = new CmdToBack(selectedShape, model);
		executeCommand(cmdToBack);
	}

	public void positionBringToBack() {
		Shape selectedShape = model.getSelectedShapes().get(0);
		int shapeIndex = model.getIndexOfShape(selectedShape);

		if (shapeIndex <= 0) {
			showMessageDialog("Izabrani oblik se vec nalazi na najnizoj poziciji!");
			return;
		}

		CmdBringToBack cmdBringToBack = new CmdBringToBack(selectedShape, model);
		executeCommand(cmdBringToBack);
	}

	public void undoCommand() {
		Command command = executedCommands.pop();
		unexecuteCommand(command);
	}

	public void redoCommand() {
		Command command = unexecutedCommands.pop();
		executeCommand(command);
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void selectDeselectShape(int x, int y) {
		MouseEvent event = new MouseEvent(frame.getViewDrawing(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, x, y, 1, false);
		mouseClickedSelect(event);
	}

	@Override
	public void addObserver(Observer observer) {
		listOfObservers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		listOfObservers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : listOfObservers) {
			observer.updateUndoRedoButtonsState(executedCommands.size(), unexecutedCommands.size());
			observer.updateFileButtonState(model.getSizeOfShapeList(), !loggingController.isLogEmpty());
			observer.updateShapeManipulationButtonsState(model.getSizeSelectedShapes(), currentMode,
					model.getSizeOfShapeList());
		}
	}

	public void setSelectedShape(ShapeType selectedShape) {
		this.selectedShape = selectedShape;
	}

	public void setCurrentMode(ModeType currentMode) {
		this.currentMode = currentMode;
		notifyObservers();
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public void setEnabledBtnReadNextCommand(boolean enabled) {
		frame.getDrawToolbar().getBtnReadNextCommand().setEnabled(enabled);
	}

	public ModelDrawing getModelDrawing() {
		return model;
	}

	public FrameDrawing getFrameDrawing() {
		return frame;
	}

	public Color getEdgeColor() {
		return this.edgeColor;
	}

	public Color getInnerColor() {
		return this.innerColor;
	}

	public Stack<Command> getUnexecutedCommands() {
		return unexecutedCommands;
	}

	public Stack<Command> getExecutedCommands() {
		return executedCommands;
	}
}
