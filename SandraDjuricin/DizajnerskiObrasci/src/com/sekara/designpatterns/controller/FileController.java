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

public class FileController {

	private ModelDrawing modelDrawing;
	private FrameDrawing frameDrawing;
	private MainController mainController;

	private Context ioContext;
	private DrawingSerialization drawingSerializationStrategy;
	private LogFile logToFileStrategy;

	public FileController(MainController mainController) {
		this.mainController = mainController;
		modelDrawing = mainController.getModelDrawing();
		frameDrawing = mainController.getFrameDrawing();

		this.ioContext = new Context();
		this.logToFileStrategy = new LogFile(frameDrawing, mainController, modelDrawing);
		this.drawingSerializationStrategy = new DrawingSerialization(modelDrawing);
	}

	public void saveToFile() {
		JFileChooser saveToFileChooser = frameDrawing.getDrawToolbar().getSaveToFileChooser();

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
		JFileChooser readFromFileChooser = frameDrawing.getDrawToolbar().getReadFromFileChooser();
 
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
			frameDrawing.getViewDrawing().repaint();
		}

		readFromFileChooser.setVisible(false);
		mainController.notifyObservers();
	}

	public void readNextCommand() {
		logToFileStrategy.readNextCommand();
	}

}
