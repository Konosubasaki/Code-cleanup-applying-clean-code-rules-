package com.sekara.designpatterns;

import com.sekara.designpatterns.controller.*;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.view.frame.FrameDrawing;

public class Main {

	public static void main(String[] args) {
		ModelDrawing model = new ModelDrawing();
		FrameDrawing frame = new FrameDrawing();
		frame.getViewDrawing().setModel(model);
		MainController mainController = new MainController(model, frame);
 		mainController.addObserver(frame.getDrawToolbar());
		frame.setController(mainController);
		FileController fileController = new FileController(mainController);
		frame.getDrawToolbar().setControllers(mainController, fileController);
		frame.setVisible(true);
	
	}
}
