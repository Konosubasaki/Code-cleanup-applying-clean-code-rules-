package com.sekara.designpatterns;

import com.sekara.designpatterns.controller.MainController;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.view.frame.FrameDrawing;

public class Main {

	public static void main(String[] args) {
		ModelDrawing viewModel = new ModelDrawing();
		FrameDrawing view = new FrameDrawing();
		view.getViewDrawing().setModel(viewModel);
		MainController controller = new MainController(viewModel, view);
		controller.addObserver(view);
		view.setController(controller);
		view.setVisible(true);
	}
}
