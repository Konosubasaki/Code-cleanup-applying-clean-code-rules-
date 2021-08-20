package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.command.*;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.io.*;
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
	
	private ModelDrawing modelDrawing;
	private FrameDrawing frameDrawing;
	
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	
	private ShapeType selectedShape;
	private ModeType currentMode;
	private Color edgeColor;
	private Color innerColor;
	private boolean lineIsWaitingForSecondPoint;
	private Point lineFirstPoint;
	private DefaultListModel<String> logger;
	private Context ioContext;
  	private List<Observer> listOfObservers;
	private boolean isLogEmpty = true;
	
	public MainController(ModelDrawing modelDrawing, FrameDrawing frameDrawing) {
		this.modelDrawing = modelDrawing;
		this.frameDrawing = frameDrawing;
		
		this.executedCommands = new Stack<Command>();
		this.unexecutedCommands = new Stack<Command>();
		
		this.edgeColor = Color.BLACK;
		this.innerColor = Color.WHITE;
		
		this.currentMode = ModeType.Drawing; 
		this.selectedShape = ShapeType.Point;
		//+++++++++++
		 
		
		this.logger = frameDrawing.getDrawToolbar().getDefaultListLogModel();
		//+++++++++++++++
		this.ioContext = new Context();
		
  		
		setEnabledBtnReadNextCommand(false);
		
		this.listOfObservers = new ArrayList<Observer>();
	}
	
	
	public Context getIoContext() {
		return ioContext;
	}


	public ModelDrawing getModelDrawing() {
		return modelDrawing;
	}


	public FrameDrawing getFrameDrawing() {
		return frameDrawing;
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
		CmdAddShape cmdAddShape;
				
		switch (selectedShape) {
			case Point:
				Point point = new Point(mouseClick, edgeColor);
				cmdAddShape = new CmdAddShape(point, modelDrawing);
				executeCommand(cmdAddShape);
				break;
				
			case Line:
				if (lineIsWaitingForSecondPoint) {
					Point lineSecondPoint = mouseClick;
					Line line = new Line(lineFirstPoint, lineSecondPoint, edgeColor);
					cmdAddShape = new CmdAddShape(line, modelDrawing);
					executeCommand(cmdAddShape);
					lineIsWaitingForSecondPoint = false;
					break;
				}
				
				lineFirstPoint = mouseClick;
				lineIsWaitingForSecondPoint = true;
				break;
				
			case Rectangle:
				DialogRectangle dlgRectangle = new DialogRectangle();
				dlgRectangle.setPoint(mouseClick);
				dlgRectangle.setColors(edgeColor, innerColor);
				dlgRectangle.setVisible(true);
				
				if(dlgRectangle.getRectangle() != null) {
					cmdAddShape = new CmdAddShape(dlgRectangle.getRectangle(), modelDrawing);
					executeCommand(cmdAddShape);
				}
				break;
				
			case Circle:
				DialogCircle dlgCircle = new DialogCircle();
				dlgCircle.setPoint(mouseClick);
				dlgCircle.setColors(edgeColor, innerColor);
				dlgCircle.setVisible(true);
				
				if(dlgCircle.getCircle() != null) {
					cmdAddShape = new CmdAddShape(dlgCircle.getCircle(), modelDrawing);
					executeCommand(cmdAddShape);
				}
				break;
				
			case Donut:
				DialogDonut dlgDonut = new DialogDonut();
				dlgDonut.setPoint(mouseClick);
				dlgDonut.setColors(edgeColor, innerColor);
				dlgDonut.setVisible(true);
				
				if(dlgDonut.getDonut() != null) {
					cmdAddShape = new CmdAddShape(dlgDonut.getDonut(), modelDrawing);
					executeCommand(cmdAddShape);
				}
				break;
				
			case Hexagon:
				DialogHexagon dlgHexagon = new DialogHexagon();
				dlgHexagon.setPoint(mouseClick);
				dlgHexagon.setColors(edgeColor, innerColor);
				dlgHexagon.setVisible(true);
				
				if(dlgHexagon.getHexagon() != null) {
					cmdAddShape = new CmdAddShape(dlgHexagon.getHexagon(), modelDrawing);
					executeCommand(cmdAddShape);
				}
				break;
	
			default:
				break;
		}
	}
	
	private void mouseClickedSelect(MouseEvent event) {
		modelDrawing.getAllShapes().forEach(shape -> {
			if (shape.containsXYpoint(event.getX(), event.getY())) {
				if (shape.isSelected()) {
					shape.setSelected(false);
					addLog("DESELECT#" + shape + "#MouseEvent(X:" + event.getX() + "|Y:" + event.getY() + ")");
				} else {
					shape.setSelected(true);
					addLog("SELECT#" + shape + "#MouseEvent(X:" + event.getX() + "|Y:" + event.getY() + ")");
				}
			}
		});
		
		frameDrawing.getViewDrawing().repaint();
		notifyObservers();
	}
	
	public void executeCommand(Command command) {
		command.execute();
		addLog(command.getLog());
		executedCommands.push(command);
		frameDrawing.getViewDrawing().repaint();
		notifyObservers();
	}
	
	public void unexecuteCommand(Command command) {
		command.unExecute();
		addLog(command.getLog());
		unexecutedCommands.push(command);
		frameDrawing.getViewDrawing().repaint();
		notifyObservers();
	}
	
	public void editShape() {
		Shape selectedShape = modelDrawing.getSelectedShapes().get(0);
		
		if (selectedShape instanceof Point) {
			DialogPoint dialogPoint = new DialogPoint();
			dialogPoint.setPoint((Point)selectedShape);
			dialogPoint.setVisible(true);
			
			if(dialogPoint.getPoint() != null) {
				CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint((Point)selectedShape, dialogPoint.getPoint());
				executeCommand(cmdUpdatePoint);
			}
		} else if (selectedShape instanceof Line) {
			DialogLine dialogLine = new DialogLine();
			dialogLine.setLine((Line)selectedShape);
			dialogLine.setVisible(true);
			
			if(dialogLine.getLine() != null) {
				CmdUpdateLine cmdUpdateLine = new CmdUpdateLine((Line)selectedShape, dialogLine.getLine());
				executeCommand(cmdUpdateLine);
			}
		} else if (selectedShape instanceof Rectangle) {
			DialogRectangle dialogRectangle = new DialogRectangle();
			dialogRectangle.setRectangle((Rectangle)selectedShape);
			dialogRectangle.setVisible(true);
			
			if(dialogRectangle.getRectangle() != null) {
				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle)selectedShape, 
						dialogRectangle.getRectangle());
				executeCommand(cmdUpdateRectangle);
			}
		} else if (selectedShape instanceof Donut) {
			DialogDonut dialogDonut = new DialogDonut();
			dialogDonut.setDonut((Donut)selectedShape);
			dialogDonut.setVisible(true);
			
			if(dialogDonut.getDonut() != null) {
				CmdUpdateDonut cmdUpdateDonut = new CmdUpdateDonut((Donut)selectedShape, dialogDonut.getDonut());
				executeCommand(cmdUpdateDonut);
			}
		} else if (selectedShape instanceof Circle) {
			DialogCircle dialogCircle = new DialogCircle();
			dialogCircle.setCircle((Circle)selectedShape);
			dialogCircle.setVisible(true);
			
			if(dialogCircle.getCircle() != null) {
				CmdUpdateCircle cmdUpdateCircle = new CmdUpdateCircle((Circle)selectedShape, dialogCircle.getCircle());
				executeCommand(cmdUpdateCircle);
			}
		} else if (selectedShape instanceof HexagonShape) {
			DialogHexagon dialogHexagon = new DialogHexagon();
			dialogHexagon.setHexagon((HexagonShape)selectedShape);
			dialogHexagon.setVisible(true);
			
			if(dialogHexagon.getHexagon() != null) {
				CmdUpdateHexagon cmdUpdateHexagon = new CmdUpdateHexagon((HexagonShape)selectedShape, dialogHexagon.getHexagon());
				executeCommand(cmdUpdateHexagon);
			}
		}
	}
	
	public void deleteSelectedShapes() {
		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete selektovane oblike?", "Potvrda", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(modelDrawing.getSelectedShapes(), modelDrawing);
			executeCommand(cmdDeleteShapes);
		}
	}
	
	public void deleteAllShapes() {
		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete crtez?", "Potvrda", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(modelDrawing.getAllShapes(), modelDrawing);
			executeCommand(cmdDeleteShapes);
		}
	}
	
	public void positionToFront() {
		Shape selectedShape = modelDrawing.getSelectedShapes().get(0);
		int shapeIndex = modelDrawing.getIndexOfShape(selectedShape);
		int shapeCount = modelDrawing.getSizeOfShapeList();
		
		if (shapeIndex >= shapeCount - 1) {
			showMessageDialog("Izabrani oblik se vec nalazi na najvisoj poziciji!");
			return;
		}
		
		CmdToFront cmdToFront = new CmdToFront(selectedShape, modelDrawing);
		executeCommand(cmdToFront);
	}
	
	public void positionBringToFront() {
		Shape selectedShape = modelDrawing.getSelectedShapes().get(0);
		int shapeIndex = modelDrawing.getIndexOfShape(selectedShape);
		int shapeCount = modelDrawing.getSizeOfShapeList();
		
		if (shapeIndex >= shapeCount - 1) {
			showMessageDialog("Izabrani oblik se vec nalazi na najvisoj poziciji!");
			return;
		}
		
		CmdBringToFront cmdBringToFront = new CmdBringToFront(selectedShape, modelDrawing);
		executeCommand(cmdBringToFront);
	}
	
	public void positionToBack() {
		Shape selectedShape = modelDrawing.getSelectedShapes().get(0);
		int shapeIndex = modelDrawing.getIndexOfShape(selectedShape);
		
		if (shapeIndex <= 0) {
			showMessageDialog("Izabrani oblik se vec nalazi na najnizoj poziciji!");
			return;
		}
		
		CmdToBack cmdToBack = new CmdToBack(selectedShape, modelDrawing);
		executeCommand(cmdToBack);
	}
	
	public void positionBringToBack() {
		Shape selectedShape = modelDrawing.getSelectedShapes().get(0);
		int shapeIndex = modelDrawing.getIndexOfShape(selectedShape);
		
		if (shapeIndex <= 0) {
			showMessageDialog("Izabrani oblik se vec nalazi na najnizoj poziciji!");
			return;
		}
		
		CmdBringToBack cmdBringToBack = new CmdBringToBack(selectedShape, modelDrawing);
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
	
	public void setSelectedShape(ShapeType selectedShape) {
		this.selectedShape = selectedShape;
	}

	public void setCurrentMode(ModeType currentMode) {
		this.currentMode = currentMode;
		notifyObservers();
	}
	
	public Color getEdgeColor() {
		return this.edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	
	public Color getInnerColor() {
		return this.innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	public void addLog(String log) {
		logger.addElement(log);
		isLogEmpty = false;
	}
	
	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void setEnabledBtnReadNextCommand(boolean enabled) {
		frameDrawing.getDrawToolbar().getBtnReadNextCommand().setEnabled(enabled);
	}
	
	public void selectDeselectShape(int x, int y) {
		MouseEvent event = new MouseEvent(frameDrawing.getViewDrawing(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, x, y, 1, false);
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
		for(Observer observer : listOfObservers) {
			observer.update(currentMode, modelDrawing.getSizeOfShapeList(), executedCommands.size(), unexecutedCommands.size(), modelDrawing.getSizeSelectedShapes(), !isLogEmpty);
		}
	}
}
