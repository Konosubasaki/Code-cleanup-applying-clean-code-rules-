package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.command.*;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.io.*;
import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.*;
import com.sekara.designpatterns.observable.Subject;
import com.sekara.designpatterns.observable.Observer;
import com.sekara.designpatterns.view.dialog.*;
import com.sekara.designpatterns.view.frame.FrmDrawing;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class MainController implements Subject {
	
	private ViewModel viewModel;
	private FrmDrawing view;
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;
	private ShapeType selectedShape;
	private ModeType currentMode;
	private Color edgeColor;
	private Color innerColor;
	private boolean lineWaitingForSecondPoint;
	private Point lineFirstPoint;
	private DefaultListModel<String> logger;
	private Context ioContext;
	private DrawingSerialization drawingSerializationStrategy;
	private LogFile logToFileStrategy;
	private List<Observer> listOfObservers;
	private boolean isLogEmpty = true;
	
	public MainController(ViewModel viewModel, FrmDrawing view) {
		this.viewModel = viewModel;
		this.view = view;
		
		this.executedCommands = new Stack<Command>();
		this.unexecutedCommands = new Stack<Command>();
		
		this.edgeColor = Color.BLACK;
		this.innerColor = Color.WHITE;
		
		this.currentMode = ModeType.Drawing;
		this.selectedShape = ShapeType.Point;
		
		this.lineWaitingForSecondPoint = false;
		
		this.logger = view.getDefaultListLogModel();
		
		this.ioContext = new Context();
		
		this.drawingSerializationStrategy = new DrawingSerialization(viewModel);
		this.logToFileStrategy = new LogFile(view, this, viewModel);
		
		setEnabledBtnReadNextCommand(false);
		
		this.listOfObservers = new ArrayList<Observer>();
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
		
		if (selectedShape != ShapeType.Line) lineWaitingForSecondPoint = false;
		
		switch (selectedShape) {
			case Point:
				Point point = new Point(mouseClick, edgeColor);
				cmdAddShape = new CmdAddShape(point, viewModel);
				executeCommand(cmdAddShape);
				break;
				
			case Line:
				if (lineWaitingForSecondPoint) {
					Line line = new Line(lineFirstPoint, mouseClick, edgeColor);
					cmdAddShape = new CmdAddShape(line, viewModel);
					executeCommand(cmdAddShape);
					lineWaitingForSecondPoint = false;
					break;
				}
				
				lineFirstPoint = mouseClick;
				lineWaitingForSecondPoint = true;
				break;
				
			case Rectangle:
				DlgRectangle dlgRectangle = new DlgRectangle();
				dlgRectangle.setPoint(mouseClick);
				dlgRectangle.setColors(edgeColor, innerColor);
				dlgRectangle.setVisible(true);
				
				if(dlgRectangle.getRectangle() != null) {
					cmdAddShape = new CmdAddShape(dlgRectangle.getRectangle(), viewModel);
					executeCommand(cmdAddShape);
				}
				break;
				
			case Circle:
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.setPoint(mouseClick);
				dlgCircle.setColors(edgeColor, innerColor);
				dlgCircle.setVisible(true);
				
				if(dlgCircle.getCircle() != null) {
					cmdAddShape = new CmdAddShape(dlgCircle.getCircle(), viewModel);
					executeCommand(cmdAddShape);
				}
				break;
				
			case Donut:
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.setPoint(mouseClick);
				dlgDonut.setColors(edgeColor, innerColor);
				dlgDonut.setVisible(true);
				
				if(dlgDonut.getDonut() != null) {
					cmdAddShape = new CmdAddShape(dlgDonut.getDonut(), viewModel);
					executeCommand(cmdAddShape);
				}
				break;
				
			case Hexagon:
				DlgHexagon dlgHexagon = new DlgHexagon();
				dlgHexagon.setPoint(mouseClick);
				dlgHexagon.setColors(edgeColor, innerColor);
				dlgHexagon.setVisible(true);
				
				if(dlgHexagon.getHexagon() != null) {
					cmdAddShape = new CmdAddShape(dlgHexagon.getHexagon(), viewModel);
					executeCommand(cmdAddShape);
				}
				break;
	
			default:
				break;
		}
	}
	
	private void mouseClickedSelect(MouseEvent event) {
		viewModel.getAllShapes().forEach(shape -> {
			if (shape.contains(event.getX(), event.getY())) {
				if (shape.isSelected()) {
					shape.setSelected(false);
					addLog("DESELECT#" + shape + "#MouseEvent(X:" + event.getX() + "|Y:" + event.getY() + ")");
				} else {
					shape.setSelected(true);
					addLog("SELECT#" + shape + "#MouseEvent(X:" + event.getX() + "|Y:" + event.getY() + ")");
				}
			}
		});
		
		view.getDrawingPanel().repaint();
		notifyObservers();
	}
	
	public void selectDeselectShape(int x, int y) {
		MouseEvent event = new MouseEvent(view.getDrawingPanel(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, x, y, 1, false);
		mouseClickedSelect(event);
	}
	
	public void editShape() {
		Shape selectedShape = viewModel.getSelectedShapes().get(0);
		
		if (selectedShape instanceof Point) {
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.setPoint((Point)selectedShape);
			dlgPoint.setVisible(true);
			
			if(dlgPoint.getPoint() != null) {
				CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint((Point)selectedShape, dlgPoint.getPoint());
				executeCommand(cmdUpdatePoint);
			}
		} else if (selectedShape instanceof Line) {
			DlgLine dlgLine = new DlgLine();
			dlgLine.setLine((Line)selectedShape);
			dlgLine.setVisible(true);
			
			if(dlgLine.getLine() != null) {
				CmdUpdateLine cmdUpdateLine = new CmdUpdateLine((Line)selectedShape, dlgLine.getLine());
				executeCommand(cmdUpdateLine);
			}
		} else if (selectedShape instanceof Rectangle) {
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.setRectangle((Rectangle)selectedShape);
			dlgRectangle.setVisible(true);
			
			if(dlgRectangle.getRectangle() != null) {
				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle((Rectangle)selectedShape, 
						dlgRectangle.getRectangle());
				executeCommand(cmdUpdateRectangle);
			}
		} else if (selectedShape instanceof Donut) {
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.setDonut((Donut)selectedShape);
			dlgDonut.setVisible(true);
			
			if(dlgDonut.getDonut() != null) {
				CmdUpdateDonut cmdUpdateDonut = new CmdUpdateDonut((Donut)selectedShape, dlgDonut.getDonut());
				executeCommand(cmdUpdateDonut);
			}
		} else if (selectedShape instanceof Circle) {
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.setCircle((Circle)selectedShape);
			dlgCircle.setVisible(true);
			
			if(dlgCircle.getCircle() != null) {
				CmdUpdateCircle cmdUpdateCircle = new CmdUpdateCircle((Circle)selectedShape, dlgCircle.getCircle());
				executeCommand(cmdUpdateCircle);
			}
		} else if (selectedShape instanceof HexagonShape) {
			DlgHexagon dlgHexagon = new DlgHexagon();
			dlgHexagon.setHexagon((HexagonShape)selectedShape);
			dlgHexagon.setVisible(true);
			
			if(dlgHexagon.getHexagon() != null) {
				CmdUpdateHexagon cmdUpdateHexagon = new CmdUpdateHexagon((HexagonShape)selectedShape, dlgHexagon.getHexagon());
				executeCommand(cmdUpdateHexagon);
			}
		}
	}
	
	public void deleteSelectedShapes() {
		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete selektovane oblike?", "Potvrda", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(viewModel.getSelectedShapes(), viewModel);
			executeCommand(cmdDeleteShapes);
		}
	}
	
	public void deleteAllShapes() {
		if (JOptionPane.showConfirmDialog(null, "Da li zaista zelite da obrisete crtez?", "Potvrda", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			CmdDeleteShapes cmdDeleteShapes = new CmdDeleteShapes(viewModel.getAllShapes(), viewModel);
			executeCommand(cmdDeleteShapes);
		}
	}
	
	public void executeCommand(Command command) {
		command.execute();
		addLog(command.getLog());
		executedCommands.push(command);
		view.getDrawingPanel().repaint();
		notifyObservers();
	}
	
	public void unexecuteCommand(Command command) {
		command.unExecute();
		addLog(command.getLog());
		unexecutedCommands.push(command);
		view.getDrawingPanel().repaint();
		notifyObservers();
	}
	
	public void undoCommand() {
		Command command = executedCommands.pop();
		unexecuteCommand(command);
	}
	
	public void redoCommand() {
		Command command = unexecutedCommands.pop();
		executeCommand(command);
	}
	
	public void positionToFront() {
		Shape selectedShape = viewModel.getSelectedShapes().get(0);
		int shapeIndex = viewModel.getIndexOfShape(selectedShape);
		int shapeCount = viewModel.getSizeOfShapeList();
		
		if (shapeIndex >= shapeCount - 1) {
			showMessageDialog("Izabrani oblik se već nalazi na najvisoj poziciji!");
			return;
		}
		
		CmdToFront cmdToFront = new CmdToFront(selectedShape, viewModel);
		executeCommand(cmdToFront);
	}
	
	public void positionBringToFront() {
		Shape selectedShape = viewModel.getSelectedShapes().get(0);
		int shapeIndex = viewModel.getIndexOfShape(selectedShape);
		int shapeCount = viewModel.getSizeOfShapeList();
		
		if (shapeIndex >= shapeCount - 1) {
			showMessageDialog("Izabrani oblik se već nalazi na najvisoj poziciji!");
			return;
		}
		
		CmdBringToFront cmdBringToFront = new CmdBringToFront(selectedShape, viewModel);
		executeCommand(cmdBringToFront);
	}
	
	public void positionToBack() {
		Shape selectedShape = viewModel.getSelectedShapes().get(0);
		int shapeIndex = viewModel.getIndexOfShape(selectedShape);
		
		if (shapeIndex <= 0) {
			showMessageDialog("Izabrani oblik se već nalazi na najnizoj poziciji!");
			return;
		}
		
		CmdToBack cmdToBack = new CmdToBack(selectedShape, viewModel);
		executeCommand(cmdToBack);
	}
	
	public void positionBringToBack() {
		Shape selectedShape = viewModel.getSelectedShapes().get(0);
		int shapeIndex = viewModel.getIndexOfShape(selectedShape);
		
		if (shapeIndex <= 0) {
			showMessageDialog("Izabrani oblik se već nalazi na najnizoj poziciji!");
			return;
		}
		
		CmdBringToBack cmdBringToBack = new CmdBringToBack(selectedShape, viewModel);
		executeCommand(cmdBringToBack);
	}
	
	public void saveToFile() {
		JFileChooser saveToFileChooser = view.getSaveToFileChooser();
		
		if (saveToFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			switch (saveToFileChooser.getFileFilter().getDescription()) {
				case "Crtez":
					ioContext.setStrategy(drawingSerializationStrategy);
					break;
					
				case "Log":
					ioContext.setStrategy(logToFileStrategy);
					break;
					
				default:
					break;
			}
			
			ioContext.saveToFile(saveToFileChooser.getSelectedFile());
		}
		
		saveToFileChooser.setVisible(false);
	}
	
	public void readFromFile() {
		JFileChooser readFromFileChooser = view.getReadFromFileChooser();
		
		if (readFromFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			switch (readFromFileChooser.getFileFilter().getDescription()) {
				case "Crtez":
					ioContext.setStrategy(drawingSerializationStrategy);
					break;
					
				case "Log":
					ioContext.setStrategy(logToFileStrategy);
					break;
					
				default:
					break;
			}
			
			ioContext.readFromFile(readFromFileChooser.getSelectedFile());
			view.getDrawingPanel().repaint();
		}
		
		readFromFileChooser.setVisible(false);
		notifyObservers();
	}
	
	public void readNextCommand() {
		logToFileStrategy.readNextCommand();
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
		view.getBtnReadNextCommand().setEnabled(enabled);
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
			observer.update(currentMode, viewModel.getSizeOfShapeList(), executedCommands.size(), unexecutedCommands.size(), viewModel.getSizeSelectedShapes(), !isLogEmpty);
		}
	}
}
