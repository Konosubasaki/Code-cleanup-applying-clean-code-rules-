package com.sekara.designpatterns;

import com.sekara.designpatterns.controller.MainController;
import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.view.frame.FrmDrawing;

public class Main {

	public static void main(String[] args) {

		ViewModel viewModel = new ViewModel();
		FrmDrawing view = new FrmDrawing();

		view.getDrawingPanel().setViewModel(viewModel);

		MainController controller = new MainController(viewModel, view);
		controller.addObserver(view);

		view.setController(controller);
		view.setVisible(true);

	}

}
