package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdBringToBack extends Command {

	private Shape shape;
	private ViewModel viewModel;
	private int index;

	public CmdBringToBack(Shape shape, ViewModel viewModel) {
		this.shape = shape;
		this.viewModel = viewModel;
	}

	@Override
	public void execute() {
		index = viewModel.getIndex(shape);
		viewModel.remove(shape);
		viewModel.addAtIndex(shape, 0);
		super.setLog("CMD_BRING_TO_BACK_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		viewModel.remove(shape);
		viewModel.addAtIndex(shape, index);
		super.setLog("CMD_BRING_TO_BACK_UNEXECUTE#" + shape);
	}

}
