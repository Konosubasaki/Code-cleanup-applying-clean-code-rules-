package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;

	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color edgeColor) {
		this(startPoint, endPoint);
		setEdgeColor(edgeColor);
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(startPoint.getXCoordinate(), startPoint.getYCoordinate(), this.endPoint.getXCoordinate(),
				endPoint.getYCoordinate());

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getXCoordinate() - 3, startPoint.getYCoordinate() - 3, 6, 6);
			g.drawRect(endPoint.getXCoordinate() - 3, endPoint.getYCoordinate() - 3, 6, 6);
			g.drawRect(middleOfLine().getXCoordinate() - 3, middleOfLine().getYCoordinate() - 3, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}

	public Point middleOfLine() {
		int middleByX = (startPoint.getXCoordinate() + endPoint.getXCoordinate()) / 2;
		int middleByY = (startPoint.getYCoordinate() + endPoint.getYCoordinate()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}

	public boolean containsXYpoint(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line lineToCompare = (Line) obj;
			if (startPoint.equals(lineToCompare.getStartPoint()) && endPoint.equals(lineToCompare.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public double length() {
		return startPoint.distance(endPoint.getXCoordinate(), endPoint.getYCoordinate());
	}

	public String toString() {

		String lineTxt = "";
		lineTxt += "Line(X1:";
		lineTxt += startPoint.getXCoordinate();
		lineTxt += "|Y1:";
		lineTxt += startPoint.getYCoordinate();
		lineTxt += "|X2:";
		lineTxt += endPoint.getXCoordinate();
		lineTxt += "|Y2:";
		lineTxt += endPoint.getYCoordinate();
		lineTxt += "|EdgeColor:";
		lineTxt += getEdgeColor().getRGB();
		lineTxt += ")";
		return lineTxt;
	}

	@Override
	public Shape clone() {
		Point startOfClone = new Point(getStartPoint().getXCoordinate(), getStartPoint().getYCoordinate());
		Point endOfClone = new Point(getEndPoint().getXCoordinate(), getEndPoint().getYCoordinate());
		Color edgeColorOfClone = getEdgeColor();
		Color innerColorOfClone = getInnerColor();
		boolean isSelectedOfclone = isSelected();

		Line line = new Line();
		line.setStartPoint(startOfClone);
		line.setEndPoint(endOfClone);
		line.setEdgeColor(edgeColorOfClone);
		line.setInnerColor(innerColorOfClone);
		line.setSelected(isSelectedOfclone);
		return line;
	}

	public static Line parse(String line) {
		line = line.replace("Line(", "").replace(")", "");
		String[] parts = line.split("\\|");

		int x1 = Integer.parseInt(parts[0].replace("X1:", ""));
		int y1 = Integer.parseInt(parts[1].replace("Y1:", ""));
		int x2 = Integer.parseInt(parts[2].replace("X2:", ""));
		int y2 = Integer.parseInt(parts[3].replace("Y2:", ""));
		Color edgeColor = Color.decode(parts[4].replace("EdgeColor:", ""));

		return new Line(new Point(x1, y1), new Point(x2, y2), edgeColor);
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
