package com.sekara.designpatterns.command;

import java.awt.Color;

import com.sekara.designpatterns.model.geometry.Line;
import com.sekara.designpatterns.model.geometry.Point;

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
		Point startPointToSet = line.getStartPoint();
		Point endPointToSet = line.getEndPoint();
		Color edgeColorToSet = line.getEdgeColor();
		boolean isSelectedToSet = line.isSelected();

		currentLine.setStartPoint(startPointToSet);
		currentLine.setEndPoint(endPointToSet);
		currentLine.setEdgeColor(edgeColorToSet);
		currentLine.setSelected(isSelectedToSet);
	}

	@Override
	public void execute() {
		updatingCurrentLine(newStateOfLine);
		super.setCommandLog("CMD_UPDATE_LINE_EXECUTE#" + oldStateOfLine + "->" + currentLine);
	}

	@Override
	public void unExecute() {
		updatingCurrentLine(oldStateOfLine);
		super.setCommandLog("CMD_UPDATE_LINE_UNEXECUTE#" + currentLine + "->" + oldStateOfLine);
	}
}
