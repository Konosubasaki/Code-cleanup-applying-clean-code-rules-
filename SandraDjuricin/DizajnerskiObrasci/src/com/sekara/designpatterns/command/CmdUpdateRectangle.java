package com.sekara.designpatterns.command;

import java.awt.Color;

import com.sekara.designpatterns.model.geometry.Point;
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
		Point upperLeftToSet = rectangle.getUpperLeftPoint();
		int widthToSet = rectangle.getWidth();
		int heightToSet = rectangle.getHeight();
		Color edgeColorToSet = rectangle.getEdgeColor();
		Color innerColorToSet = rectangle.getInnerColor();
		boolean isSelectedToSet = rectangle.isSelected();

		currentRectangle.setUpperLeftPoint(upperLeftToSet);
		currentRectangle.setWidth(widthToSet);
		currentRectangle.setHeight(heightToSet);
		currentRectangle.setEdgeColor(edgeColorToSet);
		currentRectangle.setInnerColor(innerColorToSet);
		currentRectangle.setSelected(isSelectedToSet);
	}

	@Override
	public void execute() {
		updatingCurrentRectangle(newStateOfRectangle);
		super.setCommandLog("CMD_UPDATE_RECTANGLE_EXECUTE#" + oldStateOfRectangle + "->" + currentRectangle);
	}

	@Override
	public void unExecute() {
		updatingCurrentRectangle(oldStateOfRectangle);
		super.setCommandLog("CMD_UPDATE_RECTANGLE_UNEXECUTE#" + currentRectangle + "->" + oldStateOfRectangle);
	}
}
