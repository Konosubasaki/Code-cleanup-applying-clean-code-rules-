package com.sekara.designpatterns.command;

import java.util.*;
import com.sekara.designpatterns.model.Model;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdDeleteShapes extends Command {

	private List<Shape> shapes;
	private Model viewModel;

	public CmdDeleteShapes(List<Shape> shapes, Model viewModel) {
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
		viewModel.addAllShapes(shapes);
		super.setLog("CMD_DELETE_UNEXECUTE#" + shapes);
	}
}
