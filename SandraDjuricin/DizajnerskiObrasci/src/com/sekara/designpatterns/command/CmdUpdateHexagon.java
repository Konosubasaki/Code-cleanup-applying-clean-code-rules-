package com.sekara.designpatterns.command;

import java.awt.Color;

import com.sekara.designpatterns.model.geometry.HexagonShape;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateHexagon extends Command {

	private HexagonShape oldStateOfHexagon;
	private HexagonShape newStateOfHexagon;
	private HexagonShape currentHexagon;

	public CmdUpdateHexagon(HexagonShape currentHexagon, HexagonShape newHexagon) {
		this.currentHexagon = currentHexagon;
		this.newStateOfHexagon = newHexagon;
		oldStateOfHexagon = (HexagonShape) currentHexagon.clone();
	}

	public void updatingCurrentHexagon(HexagonShape hexagon) {
		Point centerToSet = hexagon.getCenter();
		int radiusToSet = hexagon.getRadius();
		Color edgeColorToSet = hexagon.getEdgeColor();
		Color innerColorToSet = hexagon.getInnerColor();
		boolean isSelectedToSet = hexagon.isSelected();

		currentHexagon.setCenter(centerToSet);
		currentHexagon.setRadius(radiusToSet);
		currentHexagon.setEdgeColor(edgeColorToSet);
		currentHexagon.setInnerColor(innerColorToSet);
		currentHexagon.setSelected(isSelectedToSet);
	}

	@Override
	public void execute() {
		updatingCurrentHexagon(newStateOfHexagon);
		super.setCommandLog("CMD_UPDATE_HEXAGON_EXECUTE#" + oldStateOfHexagon + "->" + currentHexagon);
	}

	@Override
	public void unExecute() {
		updatingCurrentHexagon(oldStateOfHexagon);
		super.setCommandLog("CMD_UPDATE_HEXAGON_UNEXECUTE#" + currentHexagon + "->" + oldStateOfHexagon);
	}
}
