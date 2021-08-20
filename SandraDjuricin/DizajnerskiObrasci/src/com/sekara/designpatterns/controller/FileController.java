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
		this.mainController = mainController;
		model = mainController.getModelDrawing();
		frame = mainController.getFrameDrawing();

		this.ioContext = new Context();
		this.logToFileStrategy = new LogFile(frame, mainController, model);
		this.drawingSerializationStrategy = new DrawingSerialization(model);
	}

	public void saveToFile() {
		JFileChooser saveToFileChooser = frame.getDrawToolbar().getSaveToFileChooser();

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
		JFileChooser readFromFileChooser = frame.getDrawToolbar().getReadFromFileChooser();

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
			frame.getViewDrawing().repaint();
		}

		readFromFileChooser.setVisible(false);
		mainController.notifyObservers();
	}

	public void readNextCommand() {
		logToFileStrategy.readNextCommand();
	}

}
