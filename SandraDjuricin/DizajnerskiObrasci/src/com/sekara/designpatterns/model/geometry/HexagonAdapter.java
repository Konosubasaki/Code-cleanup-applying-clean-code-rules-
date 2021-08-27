package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	private Hexagon hexagon;

	public HexagonAdapter(Point center, int radius) {
		int centerXCoord = center.getXCoordinate();
		int centerYCoord = center.getYCoordinate();

		hexagon = new Hexagon(centerXCoord, centerYCoord, radius);
	}
 
	public HexagonAdapter(Point center, int radius, Color edgeColor, Color innerColor) {
		this(center, radius);
		this.setEdgeColor(edgeColor);
		this.setInnerColor(innerColor);
	}

	@Override
	public void moveBy(int byX, int byY) {
		int newMovedX = hexagon.getX();
		newMovedX += byX;
		int newMovedY = hexagon.getY();
		newMovedY += byY;

		hexagon.setX(newMovedX);
		hexagon.setY(newMovedY);
	}

	@Override
	public boolean containsXYpoint(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public Shape clone() {
		Point centerOfClone = new Point(getCenter().getXCoordinate(), getCenter().getYCoordinate());
		int radiusOfClone = getRadius();
		Color edgeColorOfClone = getEdgeColor();
		Color innerColorOfClone = getInnerColor();
		boolean isSelectedOfclone = isSelected();
		HexagonAdapter hexagon = new HexagonAdapter(centerOfClone, radiusOfClone);
		hexagon.setEdgeColor(edgeColorOfClone);
		hexagon.setInnerColor(innerColorOfClone);
		hexagon.setSelected(isSelectedOfclone);
		return hexagon;
	}

	public String toString() {
		String hexagonTxt = "";
		hexagonTxt += "Hexagon(X:";
		hexagonTxt += hexagon.getX();
		hexagonTxt += "|Y:";
		hexagonTxt += hexagon.getY();
		hexagonTxt += "|R:";
		hexagonTxt += hexagon.getR();
		hexagonTxt += "|EdgeColor:";
		hexagonTxt += getEdgeColor().getRGB();
		hexagonTxt += "|InnerColor:";
		hexagonTxt += getInnerColor().getRGB();
		hexagonTxt += ")";
		return hexagonTxt;
	}

	public static HexagonAdapter parse(String line) {
		line = line.replace("Hexagon(", "").replace(")", "");
		String[] parts = line.split("\\|");

		int x = Integer.parseInt(parts[0].replace("X:", ""));
		int y = Integer.parseInt(parts[1].replace("Y:", ""));
		int r = Integer.parseInt(parts[2].replace("R:", ""));
		Color edgeColor = Color.decode(parts[3].replace("EdgeColor:", ""));
		Color innerColor = Color.decode(parts[4].replace("InnerColor:", ""));

		return new HexagonAdapter(new Point(x, y), r, edgeColor, innerColor);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter hex = (HexagonAdapter) obj;

			if (hexagon.getX() == hex.hexagon.getX() && hexagon.getY() == hex.hexagon.getY()
					&& hexagon.getR() == hex.hexagon.getR()) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	public Color getEdgeColor() {
		return hexagon.getBorderColor();
	}

	public void setEdgeColor(Color edgeColor) {
		hexagon.setBorderColor(edgeColor);
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public Point getCenter() {
		int xCoordOfCenter = hexagon.getX();
		int yCoordOfCenter = hexagon.getY();
		Point centerOfHexagon = new Point(xCoordOfCenter, yCoordOfCenter);
		return centerOfHexagon;
	}

	public void setCenter(Point center) {
		int x = center.getXCoordinate();
		int y = center.getYCoordinate();
		hexagon.setX(x);
		hexagon.setY(y);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}
}
