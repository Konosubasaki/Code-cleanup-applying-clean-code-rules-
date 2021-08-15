package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Rectangle;

public class CmdUpdateRectangle extends Command {

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle currentRectangle;

	public CmdUpdateRectangle(Rectangle currentRectangle, Rectangle newRectangle) {
		this.currentRectangle = currentRectangle;
		this.newRectangle = newRectangle;
		oldRectangle = (Rectangle) currentRectangle.clone();
	}

	@Override
	public void execute() {
		updatingCurrentRectangle(newRectangle);
		super.setLog("CMD_UPDATE_RECTANGLE_EXECUTE#" + oldRectangle + "->" + currentRectangle);
	}

	@Override
	public void unExecute() {
		updatingCurrentRectangle(oldRectangle);
		super.setLog("CMD_UPDATE_RECTANGLE_UNEXECUTE#" + currentRectangle + "->" + oldRectangle);
	}

	public void updatingCurrentRectangle(Rectangle rectangle) {
		currentRectangle.getUpperLeftPoint().setX(rectangle.getUpperLeftPoint().getX());
		currentRectangle.getUpperLeftPoint().setY(rectangle.getUpperLeftPoint().getY());
		currentRectangle.setWidth(rectangle.getWidth());
		currentRectangle.setHeight(rectangle.getHeight());
		currentRectangle.setEdgeColor(rectangle.getEdgeColor());
		currentRectangle.setInnerColor(rectangle.getInnerColor());
		currentRectangle.setSelected(rectangle.isSelected());
	}
}
