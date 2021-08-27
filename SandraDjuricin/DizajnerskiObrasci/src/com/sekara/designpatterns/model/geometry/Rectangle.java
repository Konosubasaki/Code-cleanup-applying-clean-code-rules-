package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private Point upperLeftPoint;
	private int width;
	private int height;

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	public Rectangle(Point upperLeftPoint, int height, int width, Color edgeColor, Color innerColor) {
		this(upperLeftPoint, height, width);
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawRect(upperLeftPoint.getXCoordinate(), upperLeftPoint.getYCoordinate(), width,
				height);
		g.setColor(getInnerColor());
		g.fillRect(upperLeftPoint.getXCoordinate() + 1, upperLeftPoint.getYCoordinate() + 1,
				width - 1, height - 1);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getXCoordinate() - 3, upperLeftPoint.getYCoordinate() - 3, 6,
					6);
			g.drawRect(upperLeftPoint.getXCoordinate() - 3 + width,
					upperLeftPoint.getYCoordinate() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getXCoordinate() - 3,
					upperLeftPoint.getYCoordinate() - 3 + getHeight(), 6, 6);
			g.drawRect(upperLeftPoint.getXCoordinate() + width - 3,
					upperLeftPoint.getYCoordinate() + getHeight() - 3, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);

	}

	public boolean containsXYpoint(int x, int y) {
		if (upperLeftPoint.getXCoordinate() <= x && x <= upperLeftPoint.getXCoordinate() + width
				&& upperLeftPoint.getYCoordinate() <= y
				&& y <= upperLeftPoint.getYCoordinate() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangleToCompare = (Rectangle) obj;
			if (upperLeftPoint.equals(rectangleToCompare.getUpperLeftPoint()) && height == rectangleToCompare.getHeight()
					&& width == rectangleToCompare.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int area() {
		return width * height;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return "Rectangle(X:" + upperLeftPoint.getXCoordinate() + "|Y:" + upperLeftPoint.getYCoordinate() + "|W:"
				+ width + "|H:" + height + "|EdgeColor:" + getEdgeColor().getRGB() + "|InnerColor:"
				+ getInnerColor().getRGB() + ")";
	}

	@Override
	public Shape clone() {
		Rectangle rectangle = new Rectangle(
				new Point(getUpperLeftPoint().getXCoordinate(), getUpperLeftPoint().getYCoordinate()), getHeight(),
				width);
		rectangle.setEdgeColor(getEdgeColor());
		rectangle.setInnerColor(getInnerColor());
		rectangle.setSelected(isSelected());

		return rectangle;
	}

	public static Rectangle parse(String line) {
		line = line.replace("Rectangle(", "").replace(")", "");
		String[] parts = line.split("\\|");

		int x = Integer.parseInt(parts[0].replace("X:", ""));
		int y = Integer.parseInt(parts[1].replace("Y:", ""));
		int w = Integer.parseInt(parts[2].replace("W:", ""));
		int h = Integer.parseInt(parts[3].replace("H:", ""));
		Color edgeColor = Color.decode(parts[4].replace("EdgeColor:", ""));
		Color innerColor = Color.decode(parts[5].replace("InnerColor:", ""));

		return new Rectangle(new Point(x, y), h, w, edgeColor, innerColor);
	}
}
