package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdToBack extends Command {

	private Shape shape;
	private ModelDrawing model;
	private int indexOfShape;

	public CmdToBack(Shape shape, ModelDrawing viewModel) {
		this.shape = shape;
		this.model = viewModel;
		indexOfShape = viewModel.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		model.removeShape(shape);
		model.addShapeAtIndex(shape, indexOfShape - 1);
		super.setLog("CMD_TO_BACK_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		model.removeShape(shape);
		model.addShapeAtIndex(shape, indexOfShape);
		super.setLog("CMD_TO_BACK_UNEXECUTE#" + shape);
	}
}
