package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateCircle extends Command {

	private Circle oldCircle;
	private Circle newCircle;
	private Circle currentCircle;

	public CmdUpdateCircle(Circle currentCircle, Circle newCircle) {
		this.currentCircle = currentCircle;
		this.newCircle = newCircle;

		oldCircle = (Circle) currentCircle.clone();
	}

	@Override
	public void execute() {
		currentCircle.setCenter(new Point(newCircle.getCenter().getX(), newCircle.getCenter().getY()));
		currentCircle.setRadius(newCircle.getRadius());
		currentCircle.setEdgeColor(newCircle.getEdgeColor());
		currentCircle.setInnerColor(newCircle.getInnerColor());
		currentCircle.setSelected(newCircle.isSelected());
		super.setLog("CMD_UPDATE_CIRCLE_EXECUTE#" + oldCircle + "->" + currentCircle);
	}

	@Override
	public void unExecute() {
		currentCircle.setCenter(new Point(oldCircle.getCenter().getX(), oldCircle.getCenter().getY()));
		currentCircle.setRadius(oldCircle.getRadius());
		currentCircle.setEdgeColor(oldCircle.getEdgeColor());
		currentCircle.setInnerColor(oldCircle.getInnerColor());
		currentCircle.setSelected(oldCircle.isSelected());
		super.setLog("CMD_UPDATE_CIRCLE_UNEXECUTE#" + currentCircle + "->" + oldCircle);
	}

}
