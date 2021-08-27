package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdatePoint extends Command {

	private Point oldStateOfPoint;
	private Point newStateOfPoint;
	private Point currentPoint;

	public CmdUpdatePoint(Point currentPoint, Point newPoint) {
		this.currentPoint = currentPoint;
		this.newStateOfPoint = newPoint;
		oldStateOfPoint = (Point) currentPoint.clone();
	}

	public void updatingCurrentPoint(Point point) {
		currentPoint.setXCoordinate(point.getXCoordinate());
		currentPoint.setYCoordinate(point.getYCoordinate());
		currentPoint.setEdgeColor(point.getEdgeColor());
		currentPoint.setSelected(point.isSelected());
	}

	@Override
	public void execute() {
		updatingCurrentPoint(newStateOfPoint);
		super.setCommandLog("CMD_UPDATE_POINT_EXECUTE#" + oldStateOfPoint + "->" + currentPoint);
	}

	@Override
	public void unExecute() {
		updatingCurrentPoint(oldStateOfPoint);
		super.setCommandLog("CMD_UPDATE_POINT_UNEXECUTE#" + currentPoint + "->" + oldStateOfPoint);
	}
}
