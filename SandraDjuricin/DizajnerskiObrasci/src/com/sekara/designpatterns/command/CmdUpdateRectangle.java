package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Rectangle;

public class CmdUpdateRectangle extends Command {

	private Rectangle oldStateOfRectangle;
	private Rectangle newStateOfRectangle;
	private Rectangle currentRectangle;

	public CmdUpdateRectangle(Rectangle currentRectangle, Rectangle newRectangle) {
		this.currentRectangle = currentRectangle;
		this.newStateOfRectangle = newRectangle;
		oldStateOfRectangle = (Rectangle) currentRectangle.clone();
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

	@Override
	public void execute() {
		updatingCurrentRectangle(newStateOfRectangle);
		super.setLog("CMD_UPDATE_RECTANGLE_EXECUTE#" + oldStateOfRectangle + "->" + currentRectangle);
	}

	@Override
	public void unExecute() {
		updatingCurrentRectangle(oldStateOfRectangle);
		super.setLog("CMD_UPDATE_RECTANGLE_UNEXECUTE#" + currentRectangle + "->" + oldStateOfRectangle);
	}
}
