package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.HexagonShape;

public class CmdUpdateHexagon extends Command {

	private HexagonShape oldHexagon;
	private HexagonShape newHexagon;
	private HexagonShape currentHexagon;

	public CmdUpdateHexagon(HexagonShape currentHexagon, HexagonShape newHexagon) {
		this.currentHexagon = currentHexagon;
		this.newHexagon = newHexagon;
		oldHexagon = (HexagonShape) currentHexagon.clone();
	}

	@Override
	public void execute() {
		currentHexagon.getHexagon().setX(newHexagon.getHexagon().getX());
		currentHexagon.getHexagon().setY(newHexagon.getHexagon().getY());
		currentHexagon.getHexagon().setR(newHexagon.getHexagon().getR());
		currentHexagon.setEdgeColor(newHexagon.getEdgeColor());
		currentHexagon.setInnerColor(newHexagon.getInnerColor());
		currentHexagon.setSelected(newHexagon.isSelected());
		super.setLog("CMD_UPDATE_HEXAGON_EXECUTE#" + oldHexagon + "->" + currentHexagon);
	}

	@Override
	public void unExecute() {
		currentHexagon.getHexagon().setX(oldHexagon.getHexagon().getX());
		currentHexagon.getHexagon().setY(oldHexagon.getHexagon().getY());
		currentHexagon.getHexagon().setR(oldHexagon.getHexagon().getR());
		currentHexagon.setEdgeColor(oldHexagon.getEdgeColor());
		currentHexagon.setInnerColor(oldHexagon.getInnerColor());
		currentHexagon.setSelected(oldHexagon.isSelected());
		super.setLog("CMD_UPDATE_HEXAGON_UNEXECUTE#" + currentHexagon + "->" + oldHexagon);
	}
}
