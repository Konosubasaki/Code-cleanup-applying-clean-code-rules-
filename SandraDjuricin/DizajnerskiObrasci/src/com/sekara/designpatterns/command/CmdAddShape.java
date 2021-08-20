package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdAddShape extends Command {

	private Shape shape;
	private ModelDrawing model;

	public CmdAddShape(Shape shape, ModelDrawing viewModel) {
		this.shape = shape;
		this.model = viewModel;
	}

	@Override
	public void execute() {
		model.addShape(shape);
		super.setLog("CMD_ADD_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		model.removeShape(shape);
		super.setLog("CMD_ADD_UNEXECUTE#" + shape);
	}
}
