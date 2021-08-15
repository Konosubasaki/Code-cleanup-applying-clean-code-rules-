package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdToFront extends Command {

	private Shape shape;
	private ViewModel viewModel;
	private int index;

	public CmdToFront(Shape shape, ViewModel viewModel) {
		this.shape = shape;
		this.viewModel = viewModel;
	}

	@Override
	public void execute() {
		index = viewModel.getIndexOfShape(shape);
		viewModel.remove(shape);
		viewModel.addAtIndex(shape, index + 1);
		super.setLog("CMD_TO_FRONT_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		viewModel.remove(shape);
		viewModel.addAtIndex(shape, index);
		super.setLog("CMD_TO_FRONT_UNEXECUTE#" + shape);
	}
}
