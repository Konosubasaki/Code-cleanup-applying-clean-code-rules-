package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Cloneable, Serializable {

	private boolean selected;
	private Color edgeColor, innerColor;

	public abstract boolean containsXYpoint(int xCoordinate, int yCoordinate);

	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public abstract Shape clone();
}
