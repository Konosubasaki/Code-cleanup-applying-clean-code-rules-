package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.Model;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdBringToBack extends Command {

	private Shape shape;
	private Model viewModel;
	private int indexOfShape;

	public CmdBringToBack(Shape shape, Model viewModel) {
		this.shape = shape;
		this.viewModel = viewModel;
		indexOfShape = viewModel.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		viewModel.removeShape(shape);
		viewModel.addShapeAtIndex(shape, 0);
		super.setLog("CMD_BRING_TO_BACK_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		viewModel.removeShape(shape);
		viewModel.addShapeAtIndex(shape, indexOfShape);
		super.setLog("CMD_BRING_TO_BACK_UNEXECUTE#" + shape);
	}
}
