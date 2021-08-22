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
		setEndPoint(endPoint);
	} 
	
	public Line(Point startPoint, Point endPoint, Color edgeColor) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
		setEdgeColor(edgeColor);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(this.getStartPoint().getXCoordinate(), this.getStartPoint().getYCoordinate(), this.endPoint.getXCoordinate(), this.getEndPoint().getYCoordinate());
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getStartPoint().getXCoordinate() - 3, this.getStartPoint().getYCoordinate() - 3, 6, 6);
			g.drawRect(this.getEndPoint().getXCoordinate() - 3, this.getEndPoint().getYCoordinate() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getXCoordinate() - 3, this.middleOfLine().getYCoordinate() - 3, 6, 6);
		}
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length());
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getXCoordinate() + this.getEndPoint().getXCoordinate()) / 2;
		int middleByY = (this.getStartPoint().getYCoordinate() + this.getEndPoint().getYCoordinate()) / 2;
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
			Line l = (Line) obj;
			if (this.startPoint.equals(l.getStartPoint()) && 
					this.endPoint.equals(l.getEndPoint())) {
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
	
	public String toString() {
		return "Line(X1:" + startPoint.getXCoordinate() + "|Y1:" + startPoint.getYCoordinate() + "|X2:" + endPoint.getXCoordinate() + "|Y2:" + endPoint.getYCoordinate() + "|EdgeColor:" + getEdgeColor().getRGB() + ")";
	}

	@Override
	public Shape clone() {
		Line line = new Line();
		line.setStartPoint(new Point(getStartPoint().getXCoordinate(), getStartPoint().getYCoordinate()));
		line.setEndPoint(new Point(getEndPoint().getXCoordinate(), getEndPoint().getYCoordinate()));
		line.setEdgeColor(getEdgeColor());
		line.setInnerColor(getInnerColor());
		line.setSelected(isSelected());
		
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
}
