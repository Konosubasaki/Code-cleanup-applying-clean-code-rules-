package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateCircle extends Command {

	private Circle oldStateOfCircle;
	private Circle newStateOfCircle;
	private Circle currentCircle;

	public CmdUpdateCircle(Circle currentCircle, Circle newCircle) {
		this.currentCircle = currentCircle;
		this.newStateOfCircle = newCircle;
		oldStateOfCircle = (Circle) currentCircle.clone();
	}

	public void updatingCurrentCircle(Circle circle) {
		currentCircle.setCenter(new Point(circle.getCenter().getXCoordinate(), circle.getCenter().getYCoordinate()));
		currentCircle.setRadius(circle.getRadius());
		currentCircle.setEdgeColor(circle.getEdgeColor());
		currentCircle.setInnerColor(circle.getInnerColor());
		currentCircle.setSelected(circle.isSelected());
	}

	@Override
	public void execute() {
		updatingCurrentCircle(newStateOfCircle);
		super.setLog("CMD_UPDATE_CIRCLE_EXECUTE#" + oldStateOfCircle + "->" + currentCircle);
	}

	@Override
	public void unExecute() {
		updatingCurrentCircle(oldStateOfCircle);
		super.setLog("CMD_UPDATE_CIRCLE_UNEXECUTE#" + currentCircle + "->" + oldStateOfCircle);
	}
}
