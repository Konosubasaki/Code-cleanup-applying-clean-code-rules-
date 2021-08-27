package com.sekara.designpatterns.command;

import java.awt.Color;

import com.sekara.designpatterns.model.geometry.Donut;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateDonut extends Command {

	private Donut oldStateOfDonut;
	private Donut newStateOfDonut;
	private Donut currentDonut;

	public CmdUpdateDonut(Donut currentDonut, Donut newDonut) {
		this.currentDonut = currentDonut;
		this.newStateOfDonut = newDonut;
		oldStateOfDonut = (Donut) currentDonut.clone();
	} 
 
	public void updatingCurrentDonut(Donut donut) {
		Point centerToSet = donut.getCenter();
		int radiusToSet = donut.getRadius();
		int innerRadiusToSet = donut.getInnerRadius();
		Color edgeColorToSet = donut.getEdgeColor();
		Color innerColorToSet = donut.getInnerColor();
		boolean isSelectedToSet = donut.isSelected();

		currentDonut.setCenter(centerToSet);
		currentDonut.setRadius(radiusToSet);
		currentDonut.setInnerRadius(innerRadiusToSet);
		currentDonut.setEdgeColor(edgeColorToSet);
		currentDonut.setInnerColor(innerColorToSet);
		currentDonut.setSelected(isSelectedToSet);
	}

	@Override
	public void execute() {
		updatingCurrentDonut(newStateOfDonut);
		super.setCommandLog("CMD_UPDATE_DONUT_EXECUTE#" + oldStateOfDonut + "->" + currentDonut);
	}

	@Override
	public void unExecute() {
		updatingCurrentDonut(oldStateOfDonut);
		super.setCommandLog("CMD_UPDATE_DONUT_UNEXECUTE#" + currentDonut + "->" + oldStateOfDonut);
	}
}
