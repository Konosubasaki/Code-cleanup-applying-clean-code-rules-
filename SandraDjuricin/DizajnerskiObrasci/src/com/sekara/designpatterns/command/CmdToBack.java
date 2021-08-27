package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdToBack extends Command {

	private Shape shape;
	private ModelDrawing model;
	private int indexOfShape;

	public CmdToBack(Shape shape, ModelDrawing model) {
		this.shape = shape;
		this.model = model;
		indexOfShape = model.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		model.removeShape(shape);
		model.addShapeAtIndex(shape, indexOfShape - 1);
		super.setCommandLog("CMD_TO_BACK_EXECUTE#" + shape);
	}

	@Override
	public void unExecute() {
		model.removeShape(shape);
		model.addShapeAtIndex(shape, indexOfShape);
		super.setCommandLog("CMD_TO_BACK_UNEXECUTE#" + shape);
	}
}
