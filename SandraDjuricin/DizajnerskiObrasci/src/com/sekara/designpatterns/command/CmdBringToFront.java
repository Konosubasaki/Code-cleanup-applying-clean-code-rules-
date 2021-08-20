package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdBringToFront extends Command {

	private Shape shape;
	private ModelDrawing model;
	private int indexOfShape;

	public CmdBringToFront(Shape shape, ModelDrawing viewModel) {
		this.shape = shape;
		this.model = viewModel;
		indexOfShape = viewModel.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		model.removeShape(shape);
		model.addShapeAtIndex(shape, model.getSizeOfShapeList());
		super.setLog("CMD_BRING_TO_FRONT_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		model.removeShape(shape);
		model.addShapeAtIndex(shape, indexOfShape);
		super.setLog("CMD_BRING_TO_FRONT_UNEXECUTE#" + shape);
	}
}
