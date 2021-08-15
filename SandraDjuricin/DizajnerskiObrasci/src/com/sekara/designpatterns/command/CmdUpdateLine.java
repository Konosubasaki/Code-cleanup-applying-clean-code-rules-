package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Line;

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
		currentLine.getStartPoint().setX(newLine.getStartPoint().getX());
		currentLine.getStartPoint().setY(newLine.getStartPoint().getY());
		currentLine.getEndPoint().setX(newLine.getEndPoint().getX());
		currentLine.getEndPoint().setY(newLine.getEndPoint().getY());
		currentLine.setEdgeColor(newLine.getEdgeColor());
		currentLine.setSelected(newLine.isSelected());
		super.setLog("CMD_UPDATE_LINE_EXECUTE#" + oldLine + "->" + currentLine);
	}

	@Override
	public void unExecute() {
		currentLine.getStartPoint().setX(oldLine.getStartPoint().getX());
		currentLine.getStartPoint().setY(oldLine.getStartPoint().getY());
		currentLine.getEndPoint().setX(oldLine.getEndPoint().getX());
		currentLine.getEndPoint().setY(oldLine.getEndPoint().getY());
		currentLine.setEdgeColor(oldLine.getEdgeColor());
		currentLine.setSelected(oldLine.isSelected());
		super.setLog("CMD_UPDATE_LINE_UNEXECUTE#" + currentLine + "->" + oldLine);
	}

}
