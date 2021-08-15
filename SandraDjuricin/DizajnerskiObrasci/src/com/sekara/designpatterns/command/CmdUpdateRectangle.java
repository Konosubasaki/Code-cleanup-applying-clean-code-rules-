package com.sekara.designpatterns.command;

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
		currentRectangle.getUpperLeftPoint().setX(newRectangle.getUpperLeftPoint().getX());
		currentRectangle.getUpperLeftPoint().setY(newRectangle.getUpperLeftPoint().getY());
		currentRectangle.setWidth(newRectangle.getWidth());
		currentRectangle.setHeight(newRectangle.getHeight());
		currentRectangle.setEdgeColor(newRectangle.getEdgeColor());
		currentRectangle.setInnerColor(newRectangle.getInnerColor());
		currentRectangle.setSelected(newRectangle.isSelected());
		super.setLog("CMD_UPDATE_RECTANGLE_EXECUTE#" + oldRectangle + "->" + currentRectangle);
	}

	@Override
	public void unExecute() {
		currentRectangle.getUpperLeftPoint().setX(oldRectangle.getUpperLeftPoint().getX());
		currentRectangle.getUpperLeftPoint().setY(oldRectangle.getUpperLeftPoint().getY());
		currentRectangle.setWidth(oldRectangle.getWidth());
		currentRectangle.setHeight(oldRectangle.getHeight());
		currentRectangle.setEdgeColor(oldRectangle.getEdgeColor());
		currentRectangle.setInnerColor(oldRectangle.getInnerColor());
		currentRectangle.setSelected(oldRectangle.isSelected());
		super.setLog("CMD_UPDATE_RECTANGLE_UNEXECUTE#" + currentRectangle + "->" + oldRectangle);
	}

}
