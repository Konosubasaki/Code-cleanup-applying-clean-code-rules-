package com.sekara.designpatterns.command;

import com.sekara.designpatterns.model.geometry.Line;

public class CmdUpdateLine extends Command {

	private Line oldStateOfLine;
	private Line newStateOfLine;
	private Line currentLine;

	public CmdUpdateLine(Line currentLine, Line newLine) {
		this.currentLine = currentLine;
		this.newStateOfLine = newLine;
		oldStateOfLine = (Line) currentLine.clone();
	}
	
	public void updatingCurrentLine(Line line) {
		currentLine.getStartPoint().setXCoordinate(line.getStartPoint().getXCoordinate());
		currentLine.getStartPoint().setYCoordinate(line.getStartPoint().getYCoordinate());
		currentLine.getEndPoint().setXCoordinate(line.getEndPoint().getXCoordinate());
		currentLine.getEndPoint().setYCoordinate(line.getEndPoint().getYCoordinate());
		currentLine.setEdgeColor(line.getEdgeColor());
		currentLine.setSelected(line.isSelected());
	}

	@Override
	public void execute() {
		updatingCurrentLine(newStateOfLine);
		super.setLog("CMD_UPDATE_LINE_EXECUTE#" + oldStateOfLine + "->" + currentLine);
	}

	@Override
	public void unExecute() {
		updatingCurrentLine(oldStateOfLine);
		super.setLog("CMD_UPDATE_LINE_UNEXECUTE#" + currentLine + "->" + oldStateOfLine);
	}
}
