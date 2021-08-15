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
		updatingCurrentCircle(newCircle);
		super.setLog("CMD_UPDATE_CIRCLE_EXECUTE#" + oldCircle + "->" + currentCircle);
	}

	@Override
	public void unExecute() {
		updatingCurrentCircle(oldCircle);
		super.setLog("CMD_UPDATE_CIRCLE_UNEXECUTE#" + currentCircle + "->" + oldCircle);
	}
	
	public void updatingCurrentCircle(Circle circle) {
		currentCircle.setCenter(new Point(circle.getCenter().getX(), circle.getCenter().getY()));
		currentCircle.setRadius(circle.getRadius());
		currentCircle.setEdgeColor(circle.getEdgeColor());
		currentCircle.setInnerColor(circle.getInnerColor());
		currentCircle.setSelected(circle.isSelected());
	}
}
