package com.sekara.designpatterns.io;

import com.sekara.designpatterns.command.*;
import com.sekara.designpatterns.controller.MainController;
import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.*;
import com.sekara.designpatterns.view.frame.FrmDrawing;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class LogFile implements Strategy {
	
	private FrmDrawing view;
	private MainController controller;
	private ViewModel viewModel;
	private BufferedReader reader;
	private BufferedWriter writer;
	public LogFile(FrmDrawing view, MainController controller, ViewModel viewModel) {
		this.view = view;
		this.controller = controller;
		this.viewModel = viewModel;
	}

	@Override
	public void saveToFile(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel<String> logList = view.getDefaultListLogModel();
			for(int i = 0; i < logList.size(); i++) {
				writer.write(logList.getElementAt(i));
				writer.newLine();
			}
			writer.close();
			controller.showMessageDialog("Log uspesno sacuvan!");
		} catch (IOException e) {
			e.printStackTrace();
			controller.showMessageDialog("Greska! " + e.getMessage());
		}
	}

	@Override
	public void readFromFile(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			controller.setEnabledBtnReadNextCommand(true);
			controller.showMessageDialog("Log uspesno ucitan! Kliknite dugme za ucitavanje naredne komande iz loga.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readNextCommand() {
		try {
			Command command;
			String line;
			
			if ((line = reader.readLine()) == null) {
				controller.setEnabledBtnReadNextCommand(false);
				controller.showMessageDialog("Nema vise dostupnih komandi iz loga!");
				return;
			}
			
			String[] parts = line.split("#");
			
			switch (parts[0]) {
				case "CMD_ADD_EXECUTE":
					command = new CmdAddShape(detectShape(parts[1]), viewModel);
					controller.executeCommand(command);
					break;
					
				case "CMD_DELETE_EXECUTE":
					List<Shape> shapesToDelete = new ArrayList<Shape>();
					String shapesLine = parts[1].replace("[", "").replace("]", "");
					String[] shapes = shapesLine.split(",");
					
					for (String shape : shapes) {
						shapesToDelete.add(detectShape(shape.trim()));
					}
					
					command = new CmdDeleteShapes(shapesToDelete, viewModel);
					controller.executeCommand(command);
					break;
					
				case "CMD_UPDATE_POINT_EXECUTE":
					String[] points = parts[1].split("->");
					Point currentPoint = Point.parse(points[0]);
					Point newPoint = Point.parse(points[1]);
					
					currentPoint = (Point) viewModel.getShape(viewModel.getIndex(currentPoint));
					
					command = new CmdUpdatePoint(currentPoint, newPoint);
					controller.executeCommand(command);
					break;
					
				case "CMD_UPDATE_LINE_EXECUTE":
					String[] lines = parts[1].split("->");
					Line currentLine = Line.parse(lines[0]);
					Line newLine = Line.parse(lines[1]);
					
					currentLine = (Line) viewModel.getShape(viewModel.getIndex(currentLine));
					
					command = new CmdUpdateLine(currentLine, newLine);
					controller.executeCommand(command);
					break;
					
				case "CMD_UPDATE_RECTANGLE_EXECUTE":
					String[] rectangles = parts[1].split("->");
					Rectangle currentRectangle = Rectangle.parse(rectangles[0]);
					Rectangle newRectangle = Rectangle.parse(rectangles[1]);
					
					currentRectangle = (Rectangle) viewModel.getShape(viewModel.getIndex(currentRectangle));
					
					command = new CmdUpdateRectangle(currentRectangle, newRectangle);
					controller.executeCommand(command);
					break;
					
				case "CMD_UPDATE_CIRCLE_EXECUTE":
					String[] circles = parts[1].split("->");
					Circle currentCircle = Circle.parse(circles[0]);
					Circle newCircle = Circle.parse(circles[1]);
					
					currentCircle = (Circle) viewModel.getShape(viewModel.getIndex(currentCircle));
					
					command = new CmdUpdateCircle(currentCircle, newCircle);
					controller.executeCommand(command);
					break;
					
				case "CMD_UPDATE_DONUT_EXECUTE":
					String[] donuts = parts[1].split("->");
					Donut currentDonut = Donut.parse(donuts[0]);
					Donut newDonut = Donut.parse(donuts[1]);
					
					currentDonut = (Donut) viewModel.getShape(viewModel.getIndex(currentDonut));
					
					command = new CmdUpdateDonut(currentDonut, newDonut);
					controller.executeCommand(command);
					break;
					
				case "CMD_UPDATE_HEXAGON_EXECUTE":
					String[] hexagons = parts[1].split("->");
					HexagonShape currentHexagon = HexagonShape.parse(hexagons[0]);
					HexagonShape newHexagon = HexagonShape.parse(hexagons[1]);
					
					currentHexagon = (HexagonShape) viewModel.getShape(viewModel.getIndex(currentHexagon));
					
					command = new CmdUpdateHexagon(currentHexagon, newHexagon);
					controller.executeCommand(command);
					break;
					
				case "CMD_TO_BACK_EXECUTE":
					command = new CmdToBack(detectShape(parts[1]), viewModel);
					controller.executeCommand(command);
					break;
					
				case "CMD_TO_FRONT_EXECUTE":
					command = new CmdToFront(detectShape(parts[1]), viewModel);
					controller.executeCommand(command);
					break;
					
				case "CMD_BRING_TO_BACK_EXECUTE":
					command = new CmdBringToBack(detectShape(parts[1]), viewModel);
					controller.executeCommand(command);
					break;
					
				case "CMD_BRING_TO_FRONT_EXECUTE":
					command = new CmdBringToFront(detectShape(parts[1]), viewModel);
					controller.executeCommand(command);
					break;
					
				case "CMD_ADD_UNEXECUTE":
				case "CMD_DELETE_UNEXECUTE":
				case "CMD_UPDATE_POINT_UNEXECUTE":
				case "CMD_UPDATE_LINE_UNEXECUTE":
				case "CMD_UPDATE_RECTANGLE_UNEXECUTE":
				case "CMD_UPDATE_CIRCLE_UNEXECUTE":
				case "CMD_UPDATE_DONUT_UNEXECUTE":
				case "CMD_UPDATE_HEXAGON_UNEXECUTE":
				case "CMD_TO_BACK_UNEXECUTE":
				case "CMD_TO_FRONT_UNEXECUTE":
				case "CMD_BRING_TO_BACK_UNEXECUTE":
				case "CMD_BRING_TO_FRONT_UNEXECUTE":
					controller.undoCommand();
					break;
					
				case "SELECT":
				case "DESELECT":
					Integer[] coordinates = getMouseEventCoordinates(parts[2]);
					controller.selectDeselectShape(coordinates[0], coordinates[1]);
					break;
					
				default:
					break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Shape detectShape(String line) {
		if (line.startsWith("Point")) {
			return Point.parse(line);
		}
		
		if (line.startsWith("Line")) {
			return Line.parse(line);
		}
		
		if (line.startsWith("Rectangle")) {
			return Rectangle.parse(line);
		}
		
		if (line.startsWith("Circle")) {
			return Circle.parse(line);
		}
		
		if (line.startsWith("Donut")) {
			return Donut.parse(line);
		}
		
		if (line.startsWith("Hexagon")) {
			return HexagonShape.parse(line);
		}
		
		throw new IllegalArgumentException("Unrecognized shape: " + line);
	}
	
	private Integer[] getMouseEventCoordinates(String mouseEvent) {
		mouseEvent = mouseEvent.replace("MouseEvent(", "").replace(")", "");
		String[] coordinates = mouseEvent.split("\\|");
		String x = coordinates[0].replace("X:", "");
		String y = coordinates[1].replace("Y:", "");
		
		return new Integer[] { Integer.parseInt(x), Integer.parseInt(y) };
	}

}
