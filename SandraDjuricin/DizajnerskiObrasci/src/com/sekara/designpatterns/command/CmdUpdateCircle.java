package com.sekara.designpatterns.command;

import java.awt.Color;

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
		Point centerToSet = circle.getCenter();
		int radiusToSet = circle.getRadius();
		Color edgeColorToSet = circle.getEdgeColor();
		Color innerColorToSet = circle.getInnerColor();
		boolean isSelectedToSet = circle.isSelected();

		currentCircle.setCenter(centerToSet);
		currentCircle.setRadius(radiusToSet);
		currentCircle.setEdgeColor(edgeColorToSet);
		currentCircle.setInnerColor(innerColorToSet);
		currentCircle.setSelected(isSelectedToSet);
	}

	@Override
	public void execute() {
		updatingCurrentCircle(newStateOfCircle);
		super.setCommandLog("CMD_UPDATE_CIRCLE_EXECUTE#" + oldStateOfCircle + "->" + currentCircle);
	}

	@Override
	public void unExecute() {
		updatingCurrentCircle(oldStateOfCircle);
		super.setCommandLog("CMD_UPDATE_CIRCLE_UNEXECUTE#" + currentCircle + "->" + oldStateOfCircle);
	}
}
