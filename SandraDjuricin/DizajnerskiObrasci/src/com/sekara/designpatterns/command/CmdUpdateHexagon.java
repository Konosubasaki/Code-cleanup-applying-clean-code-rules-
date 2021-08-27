package com.sekara.designpatterns.command;

import java.awt.Color;

import com.sekara.designpatterns.model.geometry.HexagonAdapter;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateHexagon extends Command {

	private HexagonAdapter oldStateOfHexagon;
	private HexagonAdapter newStateOfHexagon;
	private HexagonAdapter currentHexagon;

	public CmdUpdateHexagon(HexagonAdapter currentHexagon, HexagonAdapter newHexagon) {
		this.currentHexagon = currentHexagon;
		this.newStateOfHexagon = newHexagon;
		oldStateOfHexagon = (HexagonAdapter) currentHexagon.clone();
	}

	public void updatingCurrentHexagon(HexagonAdapter hexagon) {
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
