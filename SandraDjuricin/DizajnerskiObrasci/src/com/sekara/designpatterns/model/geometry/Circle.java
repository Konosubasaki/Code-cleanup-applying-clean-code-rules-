package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {

	private Point center;
	private int radius = 0;

	public Circle() {
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color edgeColor, Color innerColor) {
		this.center = center;
		this.radius = radius;
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
		g.fillOval(this.getCenter().getXCoordinate() - this.getRadius(),
				this.getCenter().getYCoordinate() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		g.setColor(getEdgeColor());
		g.drawOval(this.getCenter().getXCoordinate() - getRadius(),
				this.getCenter().getYCoordinate() - this.getRadius(), getRadius() * 2, this.getRadius() * 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3, 6, 6);
			g.drawRect(getCenter().getXCoordinate() - 3 - getRadius(), getCenter().getYCoordinate() - 3, 6, 6);
			g.drawRect(getCenter().getXCoordinate() - 3 + getRadius(), getCenter().getYCoordinate() - 3, 6, 6);
			g.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3 + getRadius(), 6, 6);
			g.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3 - getRadius(), 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);

	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}

	public boolean containsXYpoint(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public double area() {
		return radius * radius * Math.PI;
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

	public String toString() {
		return "Circle(X:" + center.getXCoordinate() + "|Y:" + center.getYCoordinate() + "|R:" + radius + "|EdgeColor:"
				+ getEdgeColor().getRGB() + "|InnerColor:" + getInnerColor().getRGB() + ")";
	}

	@Override
	public Shape clone() {
		Circle circle = new Circle();
		circle.setCenter(new Point(center.getXCoordinate(), center.getYCoordinate()));
		circle.setRadius(getRadius());
		circle.setEdgeColor(getEdgeColor());
		circle.setInnerColor(getInnerColor());
		circle.setSelected(isSelected());
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
}
