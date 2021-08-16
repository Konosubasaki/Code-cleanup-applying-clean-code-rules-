package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.Model;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdToFront extends Command {

	private Shape shape;
	private Model viewModel;
	private int indexOfShape;

	public CmdToFront(Shape shape, Model viewModel) {
		this.shape = shape;
		this.viewModel = viewModel;
		indexOfShape = viewModel.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		viewModel.removeShape(shape);
		viewModel.addShapeAtIndex(shape, indexOfShape + 1);
		super.setLog("CMD_TO_FRONT_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		viewModel.removeShape(shape);
		viewModel.addShapeAtIndex(shape, indexOfShape);
		super.setLog("CMD_TO_FRONT_UNEXECUTE#" + shape);
	}
}
