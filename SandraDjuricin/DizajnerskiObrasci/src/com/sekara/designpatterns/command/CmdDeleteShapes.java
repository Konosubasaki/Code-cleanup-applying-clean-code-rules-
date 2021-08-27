package com.sekara.designpatterns.command;

import java.util.*;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.Shape;

public class CmdDeleteShapes extends Command {

	private List<Shape> shapes;
	private ModelDrawing model;

	public CmdDeleteShapes(List<Shape> shapes, ModelDrawing model) {
		this.shapes = new ArrayList<Shape>(shapes);
		this.model = model;
	}

	@Override
	public void execute() {
		model.removeShapes(shapes);
		super.setCommandLog("CMD_DELETE_EXECUTE#" + shapes);
	}

	@Override
	public void unExecute() {
		model.addAllShapes(shapes);
		super.setCommandLog("CMD_DELETE_UNEXECUTE#" + shapes);
	}
}
