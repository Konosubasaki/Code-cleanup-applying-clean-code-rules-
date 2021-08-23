package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.io.*;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.view.frame.FrameDrawing;
import javax.swing.*;

public class FileController {

	private ModelDrawing model;
	private FrameDrawing frame;
	private MainController mainController;
	private Context ioContext;
	private DrawingSerialization drawingSerializationStrategy;
	private LogFile logToFileStrategy;

	public FileController(MainController mainController) {
		model = mainController.getModelDrawing();
		frame = mainController.getFrameDrawing();
		this.mainController = mainController;
		this.ioContext = new Context();
		this.logToFileStrategy = new LogFile(frame, this.mainController, model);
		this.drawingSerializationStrategy = new DrawingSerialization(model);
	}

	public void SetFileStrategy(JFileChooser fileChooser) {
		switch (fileChooser.getFileFilter().getDescription()) {
		case "Crtez":
			ioContext.setStrategy(drawingSerializationStrategy);
			break;

		case "Log":
			ioContext.setStrategy(logToFileStrategy);
			break;

		default:
			break; 
		}
	}

	public void saveToFile() {
		JFileChooser fileChooser = frame.getDrawToolbar().getSaveToFileChooser();

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			SetFileStrategy(fileChooser);
			ioContext.saveToFile(fileChooser.getSelectedFile());
		}
		fileChooser.setVisible(false);
	}

	public void readFromFile() {
		JFileChooser fileChooser = frame.getDrawToolbar().getReadFromFileChooser();

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			SetFileStrategy(fileChooser);
			ioContext.readFromFile(fileChooser.getSelectedFile());
			frame.getViewDrawing().repaint();
		}
		fileChooser.setVisible(false);
		mainController.notifyObservers();
	}

	public void readNextCommand() {
		logToFileStrategy.readNextCommand();
	}
}
