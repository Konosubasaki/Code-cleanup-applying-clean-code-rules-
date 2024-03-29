package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int xCoordinate;
	private int yCoordinate;

	public Point(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		setYCoordinate(yCoordinate);
	}

	public Point(Point point, Color edgeColor) {
		this(point.getXCoordinate(), point.getYCoordinate(), edgeColor);
	}
 
	public Point(int xCoordinate, int yCoordinate, Color edgeColor) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		setEdgeColor(edgeColor);
	}

	public Point(int xCoordinate, int yCoordinate, boolean selected) {
		this(xCoordinate, yCoordinate);
		setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(xCoordinate - 2, yCoordinate, xCoordinate + 2, yCoordinate);
		g.drawLine(xCoordinate, yCoordinate - 2, xCoordinate, yCoordinate + 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.xCoordinate - 3, this.getYCoordinate() - 3, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.xCoordinate = this.xCoordinate + byX;
		this.yCoordinate += byY;
	}

	public boolean containsXYpoint(int xCoordinate, int yCoordinate) {
		return this.distance(xCoordinate, yCoordinate) <= 3;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.xCoordinate == p.getXCoordinate() && this.yCoordinate == p.getYCoordinate()) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public double distance(int x2, int y2) {
		double dx = xCoordinate - x2;
		double dy = yCoordinate - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}

	public int getXCoordinate() {
		return this.xCoordinate;
	}

	public void setXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getYCoordinate() {
		return this.yCoordinate;
	}

	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public String toString() {
		String pointTxt = "";
		pointTxt += "Point(X:";
		pointTxt += xCoordinate;
		pointTxt += "|Y:";
		pointTxt += yCoordinate;
		pointTxt += "|EdgeColor:";
		pointTxt += getEdgeColor().getRGB();
		pointTxt += ")";
		return pointTxt;
	}

	@Override
	public Shape clone() {
		Point point = new Point(getXCoordinate(), getYCoordinate(), getEdgeColor());
		point.setSelected(isSelected());
		return point;
	}

	public static Point parse(String line) {
		line = line.replace("Point(", "").replace(")", "");
		String[] parts = line.split("\\|");

		int xCoordinate = Integer.parseInt(parts[0].replace("X:", ""));
		int yCoordinate = Integer.parseInt(parts[1].replace("Y:", ""));
		Color edgeColor = Color.decode(parts[2].replace("EdgeColor:", ""));

		return new Point(xCoordinate, yCoordinate, edgeColor);
	}
}
