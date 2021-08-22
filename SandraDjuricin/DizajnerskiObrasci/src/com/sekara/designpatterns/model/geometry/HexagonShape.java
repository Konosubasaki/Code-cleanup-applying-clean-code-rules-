package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;

public class HexagonShape extends Shape {
	
	private Hexagon hexagon;
	
	public HexagonShape(Point center, int radius) {
		this.hexagon = new Hexagon(center.getXCoordinate(), center.getYCoordinate(), radius);
	}
	
	public HexagonShape(Point center, int radius, Color edgeColor, Color innerColor) {
		this(center, radius);
		this.setEdgeColor(edgeColor);
		this.setInnerColor(innerColor); 
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(this.hexagon.getX()+byX);
		this.hexagon.setY(this.hexagon.getY()+byY);
		 
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonShape)
			return this.hexagon.getR() - ((HexagonShape)o).hexagon.getR();
		return 0;
	}

	@Override
	public boolean containsXYpoint(int x, int y) {
		return this.hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
	}
	
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}

	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}

	public Color getEdgeColor() {
		return this.hexagon.getBorderColor();
	}

	public void setEdgeColor(Color edgeColor) {
		this.hexagon.setBorderColor(edgeColor);
	}

	public Color getInnerColor() {
		return this.hexagon.getAreaColor();
	}

	public void setInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
	}
	
	public Hexagon getHexagon() {
		return this.hexagon;
	}

	@Override
	public Shape clone() {
		HexagonShape hexagon = new HexagonShape(new Point(getHexagon().getX(), getHexagon().getY()), getHexagon().getR());
		hexagon.setEdgeColor(getEdgeColor());
		hexagon.setInnerColor(getInnerColor());
		hexagon.setSelected(isSelected());
		
		return hexagon;
	}
	
	public String toString() {
		return "Hexagon(X:" + hexagon.getX() + "|Y:" + hexagon.getY() + "|R:" + hexagon.getR() + "|EdgeColor:" + getEdgeColor().getRGB() + "|InnerColor:" + getInnerColor().getRGB() + ")";
	}

	public static HexagonShape parse(String line) {
		line = line.replace("Hexagon(", "").replace(")", "");
		String[] parts = line.split("\\|");
		
		int x = Integer.parseInt(parts[0].replace("X:", ""));
		int y = Integer.parseInt(parts[1].replace("Y:", ""));
		int r = Integer.parseInt(parts[2].replace("R:", ""));
		Color edgeColor = Color.decode(parts[3].replace("EdgeColor:", ""));
		Color innerColor = Color.decode(parts[4].replace("InnerColor:", ""));
		
		return new HexagonShape(new Point(x, y), r, edgeColor, innerColor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonShape) {
			HexagonShape hex = (HexagonShape) obj;
			
			if (hexagon.getX() == hex.hexagon.getX() && hexagon.getY() == hex.hexagon.getY() && hexagon.getR() == hex.hexagon.getR()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
