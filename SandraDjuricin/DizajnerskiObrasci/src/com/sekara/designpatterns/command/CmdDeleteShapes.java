package com.sekara.designpatterns.command;

import java.util.*;
import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdDeleteShapes extends Command {

	private List<Shape> shapes;
	private ViewModel viewModel;

	public CmdDeleteShapes(List<Shape> shapes, ViewModel viewModel) {
		this.shapes = new ArrayList<Shape>(shapes);
		this.viewModel = viewModel;
	}

	@Override
	public void execute() {
		viewModel.removeAll(shapes);
		super.setLog("CMD_DELETE_EXECUTE#" + shapes);
	}

	@Override
	public void unExecute() {
		viewModel.addAll(shapes);
		super.setLog("CMD_DELETE_UNEXECUTE#" + shapes);
	}
}
