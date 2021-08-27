package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {

	protected Point center;
	protected int radius;

	public Circle() {
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color edgeColor, Color innerColor) {
		this(center, radius);
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(center.getXCoordinate() - radius, center.getYCoordinate() - radius, radius * 2, radius * 2);
		g.setColor(getEdgeColor());
		g.drawOval(center.getXCoordinate() - radius, center.getYCoordinate() - radius, radius * 2, radius * 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getXCoordinate() - 3, center.getYCoordinate() - 3, 6, 6);
			g.drawRect(center.getXCoordinate() - 3 - radius, center.getYCoordinate() - 3, 6, 6);
			g.drawRect(center.getXCoordinate() - 3 + radius, center.getYCoordinate() - 3, 6, 6);
			g.drawRect(center.getXCoordinate() - 3, center.getYCoordinate() - 3 + radius, 6, 6);
			g.drawRect(center.getXCoordinate() - 3, center.getYCoordinate() - 3 - radius, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}

	public boolean containsXYpoint(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle circleToCompare = (Circle) obj;
			if (center.equals(circleToCompare.getCenter()) && radius == circleToCompare.getRadius())
				return true;
			else
				return false;
		} else
			return false;
	}

	public double area() {
		return radius * radius * Math.PI;
	}

	public String toString() {
		String circleTxt = "";
		circleTxt += "Circle(X:";
		circleTxt += center.getXCoordinate();
		circleTxt += "|Y:";
		circleTxt += center.getYCoordinate();
		circleTxt += "|R:";
		circleTxt += radius;
		circleTxt += "|EdgeColor:";
		circleTxt += getEdgeColor().getRGB();
		circleTxt += "|InnerColor:";
		circleTxt += getInnerColor().getRGB();
		circleTxt += ")";
		return circleTxt;
	}

	@Override
	public Shape clone() {
		Point centerOfClone = new Point(center.getXCoordinate(), center.getYCoordinate());
		int radiusOfClone = radius;
		Color edgeColorOfClone = getEdgeColor();
		Color innerColorOfClone = getInnerColor();
		boolean isSelectedOfclone = isSelected();

		Circle circle = new Circle();
		circle.setCenter(centerOfClone);
		circle.setRadius(radiusOfClone);
		circle.setEdgeColor(edgeColorOfClone);
		circle.setInnerColor(innerColorOfClone);
		circle.setSelected(isSelectedOfclone);
		return circle;
	}

	public static Circle parse(String line) {
		line = line.replace("Circle(", "").replace(")", "");
		String[] parts = line.split("\\|");
		int x = Integer.parseInt(parts[0].replace("X:", ""));
		int y = Integer.parseInt(parts[1].replace("Y:", ""));
		int r = Integer.parseInt(parts[2].replace("R:", ""));
		Color edgeColor = Color.decode(parts[3].replace("EdgeColor:", ""));
		Color innerColor = Color.decode(parts[4].replace("InnerColor:", ""));
		return new Circle(new Point(x, y), r, edgeColor, innerColor);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
	}
}
