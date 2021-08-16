package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdToFront extends Command {

	private Shape shape;
	private ViewModel viewModel;
	private int indexOfShape;

	public CmdToFront(Shape shape, ViewModel viewModel) {
		this.shape = shape;
		this.viewModel = viewModel;
		indexOfShape = viewModel.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		viewModel.remove(shape);
		viewModel.addAtIndex(shape, indexOfShape + 1);
		super.setLog("CMD_TO_FRONT_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		viewModel.remove(shape);
		viewModel.addAtIndex(shape, indexOfShape);
		super.setLog("CMD_TO_FRONT_UNEXECUTE#" + shape);
	}
}
