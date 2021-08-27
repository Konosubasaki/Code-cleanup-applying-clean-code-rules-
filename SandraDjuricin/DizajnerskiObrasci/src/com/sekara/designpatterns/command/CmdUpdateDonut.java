package com.sekara.designpatterns.command;

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
		currentDonut.setCenter(new Point(donut.getCenter().getXCoordinate(), donut.getCenter().getYCoordinate()));
		currentDonut.setRadius(donut.getRadius());
		currentDonut.setInnerRadius(donut.getInnerRadius());
		currentDonut.setEdgeColor(donut.getEdgeColor());
		currentDonut.setInnerColor(donut.getInnerColor());
		currentDonut.setSelected(donut.isSelected());
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
