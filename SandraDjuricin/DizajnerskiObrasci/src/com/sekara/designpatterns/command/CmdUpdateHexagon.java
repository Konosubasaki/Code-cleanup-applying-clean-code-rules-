package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.HexagonShape;
import com.sekara.designpatterns.model.geometry.Point;

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
		updatingCurrentHexagon(newHexagon);
		super.setLog("CMD_UPDATE_HEXAGON_EXECUTE#" + oldHexagon + "->" + currentHexagon);
	}

	@Override
	public void unExecute() {
		updatingCurrentHexagon(oldHexagon);
		super.setLog("CMD_UPDATE_HEXAGON_UNEXECUTE#" + currentHexagon + "->" + oldHexagon);
	}
	
	public void updatingCurrentHexagon(HexagonShape hexagon) {
		currentHexagon.getHexagon().setX(hexagon.getHexagon().getX());
		currentHexagon.getHexagon().setY(hexagon.getHexagon().getY());
		currentHexagon.getHexagon().setR(hexagon.getHexagon().getR());
		currentHexagon.setEdgeColor(hexagon.getEdgeColor());
		currentHexagon.setInnerColor(hexagon.getInnerColor());
		currentHexagon.setSelected(hexagon.isSelected());
	}
}
