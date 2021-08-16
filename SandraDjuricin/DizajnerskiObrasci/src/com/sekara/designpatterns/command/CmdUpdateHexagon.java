package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.HexagonShape;

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
		currentHexagon.getHexagon().setX(hexagon.getHexagon().getX());
		currentHexagon.getHexagon().setY(hexagon.getHexagon().getY());
		currentHexagon.getHexagon().setR(hexagon.getHexagon().getR());
		currentHexagon.setEdgeColor(hexagon.getEdgeColor());
		currentHexagon.setInnerColor(hexagon.getInnerColor());
		currentHexagon.setSelected(hexagon.isSelected());
	}

	@Override
	public void execute() {
		updatingCurrentHexagon(newStateOfHexagon);
		super.setLog("CMD_UPDATE_HEXAGON_EXECUTE#" + oldStateOfHexagon + "->" + currentHexagon);
	}

	@Override
	public void unExecute() {
		updatingCurrentHexagon(oldStateOfHexagon);
		super.setLog("CMD_UPDATE_HEXAGON_UNEXECUTE#" + currentHexagon + "->" + oldStateOfHexagon);
	}
}
