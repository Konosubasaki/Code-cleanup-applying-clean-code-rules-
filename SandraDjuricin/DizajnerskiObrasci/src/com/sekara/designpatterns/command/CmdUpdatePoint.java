package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdatePoint extends Command {

	private Point oldPoint;
	private Point newPoint;
	private Point currentPoint;

	public CmdUpdatePoint(Point currentPoint, Point newPoint) {
		this.currentPoint = currentPoint;
		this.newPoint = newPoint;
		oldPoint = (Point) currentPoint.clone();
	}

	@Override
	public void execute() {
		updatingCurrentPoint(newPoint);
		super.setLog("CMD_UPDATE_POINT_EXECUTE#" + oldPoint + "->" + currentPoint);
	}

	@Override
	public void unExecute() {
		updatingCurrentPoint(oldPoint);
		super.setLog("CMD_UPDATE_POINT_UNEXECUTE#" + currentPoint + "->" + oldPoint);
	}
	
	public void updatingCurrentPoint(Point point) {
		currentPoint.setX(point.getX());
		currentPoint.setY(point.getY());
		currentPoint.setEdgeColor(point.getEdgeColor());
		currentPoint.setSelected(point.isSelected());
	}
}
