package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Donut;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateDonut extends Command {

	private Donut oldDonut;
	private Donut newDonut;
	private Donut currentDonut;

	public CmdUpdateDonut(Donut currentDonut, Donut newDonut) {
		this.currentDonut = currentDonut;
		this.newDonut = newDonut;
		oldDonut = (Donut) currentDonut.clone();
	}

	@Override
	public void execute() {
		updatingCurrentDonut(newDonut);
		super.setLog("CMD_UPDATE_DONUT_EXECUTE#" + oldDonut + "->" + currentDonut);
	}

	@Override
	public void unExecute() {
		updatingCurrentDonut(oldDonut);
		super.setLog("CMD_UPDATE_DONUT_UNEXECUTE#" + currentDonut + "->" + oldDonut);
	}
	
	public void updatingCurrentDonut(Donut donut) {
		currentDonut.setCenter(new Point(donut.getCenter().getX(), donut.getCenter().getY()));
		currentDonut.setRadius(donut.getRadius());
		currentDonut.setInnerRadius(donut.getInnerRadius());
		currentDonut.setEdgeColor(donut.getEdgeColor());
		currentDonut.setInnerColor(donut.getInnerColor());
		currentDonut.setSelected(donut.isSelected());
	}
}
