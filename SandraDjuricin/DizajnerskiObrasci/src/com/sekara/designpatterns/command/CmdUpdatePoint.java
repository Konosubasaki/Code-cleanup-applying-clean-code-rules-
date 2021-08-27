package com.sekara.designpatterns.command;

import java.awt.Color;

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
		int xCoordToSet = point.getXCoordinate();
		int yCoordToSet = point.getYCoordinate();
		Color edgeColorToSet = point.getEdgeColor();
		boolean isSelectedToSet = point.isSelected();

		currentPoint.setXCoordinate(xCoordToSet);
		currentPoint.setYCoordinate(yCoordToSet);
		currentPoint.setEdgeColor(edgeColorToSet);
		currentPoint.setSelected(isSelectedToSet);
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
