package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Line;
import com.sekara.designpatterns.model.geometry.Point;

public class CmdUpdateLine extends Command {

	private Line oldLine;
	private Line newLine;
	private Line currentLine;

	public CmdUpdateLine(Line currentLine, Line newLine) {
		this.currentLine = currentLine;
		this.newLine = newLine;
		oldLine = (Line) currentLine.clone();
	}

	@Override
	public void execute() {
		updatingCurrentLine(newLine);
		super.setLog("CMD_UPDATE_LINE_EXECUTE#" + oldLine + "->" + currentLine);
	}

	@Override
	public void unExecute() {
		updatingCurrentLine(oldLine);
		super.setLog("CMD_UPDATE_LINE_UNEXECUTE#" + currentLine + "->" + oldLine);
	}
	
	public void updatingCurrentLine(Line line) {
		currentLine.getStartPoint().setX(line.getStartPoint().getX());
		currentLine.getStartPoint().setY(line.getStartPoint().getY());
		currentLine.getEndPoint().setX(line.getEndPoint().getX());
		currentLine.getEndPoint().setY(line.getEndPoint().getY());
		currentLine.setEdgeColor(line.getEdgeColor());
		currentLine.setSelected(line.isSelected());
	}
}
