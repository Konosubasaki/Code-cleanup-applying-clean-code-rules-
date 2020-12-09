package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdAddShape extends Command {
	
	private Shape shape;
	private ViewModel viewModel;
	
	public CmdAddShape(Shape shape, ViewModel viewModel) {
		this.shape = shape;
		this.viewModel = viewModel;
	}

	@Override
	public void execute() {
		this.viewModel.addShape(shape);
		super.setLog("CMD_ADD_EXECUTE#" + shape);
		
	}

	@Override
	public void unExecute() {
		this.viewModel.remove(shape);
		super.setLog("CMD_ADD_UNEXECUTE#" + shape);
	}

}
