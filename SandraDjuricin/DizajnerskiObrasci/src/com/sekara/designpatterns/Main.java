package com.sekara.designpatterns;

import com.sekara.designpatterns.controller.*;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.toolbars.DrawToolbar;
import com.sekara.designpatterns.view.frame.FrameDrawing;

public class Main {

	public static void main(String[] args) {
		ModelDrawing modelDrawing = new ModelDrawing();
		
		
		
		
		FrameDrawing frameDrawing = new FrameDrawing();
		
		
				
		frameDrawing.getViewDrawing().setModel(modelDrawing);
		MainController controller = new MainController(modelDrawing, frameDrawing);
 		controller.addObserver(frameDrawing);
		frameDrawing.setController(controller);
		FileController fileController = new FileController(controller);
		frameDrawing.getDrawToolbar().setControllers(controller, fileController);
		frameDrawing.setVisible(true);
 
		
		
		
		
	}
}
