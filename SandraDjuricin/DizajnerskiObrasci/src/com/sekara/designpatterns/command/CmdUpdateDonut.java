package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Donut;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateDonut extends Command {
	
	private Donut oldDonut;
	private Donut newDonut;
	private Donut currentDonut;
	
	public CmdUpdateDonut(Donut currentDonut, Donut newDonut) {
		this.currentDonut = currentDonut;
		this.newDonut = newDonut;
		
		oldDonut = (Donut)currentDonut.clone();
	}

	@Override
	public void execute() {
		currentDonut.setCenter(new Point(newDonut.getCenter().getX(), newDonut.getCenter().getY()));
		currentDonut.setRadius(newDonut.getRadius());
		currentDonut.setInnerRadius(newDonut.getInnerRadius());
		currentDonut.setEdgeColor(newDonut.getEdgeColor());
		currentDonut.setInnerColor(newDonut.getInnerColor());
		currentDonut.setSelected(newDonut.isSelected());
		super.setLog("CMD_UPDATE_DONUT_EXECUTE#" + oldDonut + "->" + currentDonut);
	}

	@Override
	public void unExecute() {
		currentDonut.setCenter(new Point(oldDonut.getCenter().getX(), oldDonut.getCenter().getY()));
		currentDonut.setRadius(oldDonut.getRadius());
		currentDonut.setInnerRadius(oldDonut.getInnerRadius());
		currentDonut.setEdgeColor(oldDonut.getEdgeColor());
		currentDonut.setInnerColor(oldDonut.getInnerColor());
		currentDonut.setSelected(oldDonut.isSelected());
		super.setLog("CMD_UPDATE_DONUT_UNEXECUTE#" + currentDonut + "->" + oldDonut);
	}

}
