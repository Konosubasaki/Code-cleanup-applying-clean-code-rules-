package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdatePoint extends Command {
	
	private Point oldPoint;
	private Point newPoint;
	private Point currentPoint;
	
	public CmdUpdatePoint(Point currentPoint, Point newPoint) {
		this.currentPoint = currentPoint;
		this.newPoint = newPoint;
		
		oldPoint = (Point)currentPoint.clone();
	}

	@Override
	public void execute() {
		currentPoint.setX(newPoint.getX());
		currentPoint.setY(newPoint.getY());
		currentPoint.setEdgeColor(newPoint.getEdgeColor());
		currentPoint.setSelected(newPoint.isSelected());
		super.setLog("CMD_UPDATE_POINT_EXECUTE#" + oldPoint + "->" + currentPoint);
	}

	@Override
	public void unExecute() {
		currentPoint.setX(oldPoint.getX());
		currentPoint.setY(oldPoint.getY());
		currentPoint.setEdgeColor(oldPoint.getEdgeColor());
		currentPoint.setSelected(oldPoint.isSelected());
		super.setLog("CMD_UPDATE_POINT_UNEXECUTE#" + currentPoint + "->" + oldPoint);
	}

}
