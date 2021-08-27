package com.sekara.designpatterns.model.geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import com.sekara.designpatterns.model.geometry.Point;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {
	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius; 
	}

	public Donut(Point center, int radius, int innerRadius, Color edgeColor, Color innerColor) {
		this(center, radius, innerRadius);
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public void draw(Graphics g) {
		Point donutCenter = getCenter();
		int donutRadius = getRadius();

		Graphics2D g2d = (Graphics2D) g;
		Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);

		path.append(new Ellipse2D.Double(donutCenter.getXCoordinate() - donutRadius,
				donutCenter.getYCoordinate() - donutRadius, donutRadius * 2, donutRadius * 2), false);
		path.append(new Ellipse2D.Double(donutCenter.getXCoordinate() - innerRadius,
				donutCenter.getYCoordinate() - innerRadius, innerRadius * 2, innerRadius * 2), false);

		g2d.setColor(getInnerColor());
		g2d.fill(path);

		g2d.setColor(getEdgeColor());
		g2d.drawOval(donutCenter.getXCoordinate() - donutRadius, donutCenter.getYCoordinate() - donutRadius,
				donutRadius * 2, donutRadius * 2);
		g2d.drawOval(donutCenter.getXCoordinate() - innerRadius, donutCenter.getYCoordinate() - innerRadius,
				innerRadius * 2, innerRadius * 2);

		if (isSelected()) {
			g2d.setColor(Color.BLUE);
			g2d.drawRect(donutCenter.getXCoordinate() - 3, donutCenter.getYCoordinate() - 3, 6, 6);
			g2d.drawRect(donutCenter.getXCoordinate() - 3 - donutRadius, donutCenter.getYCoordinate() - 3, 6, 6);
			g2d.drawRect(donutCenter.getXCoordinate() - 3 + donutRadius, donutCenter.getYCoordinate() - 3, 6, 6);
			g2d.drawRect(donutCenter.getXCoordinate() - 3, donutCenter.getYCoordinate() - 3 + donutRadius, 6, 6);
			g2d.drawRect(donutCenter.getXCoordinate() - 3, donutCenter.getYCoordinate() - 3 - donutRadius, 6, 6);
		}
	}

	public boolean containsXYpoint(int x, int y) {
		Point donutCenter = getCenter();
		double distanceFromCenter = donutCenter.distance(x, y);
		return super.containsXYpoint(x, y) && distanceFromCenter > innerRadius;
	} 

	public double area() {
		return super.area() - (innerRadius * innerRadius * Math.PI);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut donutToCompare = (Donut) obj;
			if (getCenter().equals(donutToCompare.getCenter()) && getRadius() == donutToCompare.getRadius()
					&& innerRadius == donutToCompare.getInnerRadius()) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		String donutTxt = "";
		donutTxt += "Donut(X:";
		donutTxt += getCenter().getXCoordinate();
		donutTxt += "|Y:";
		donutTxt += getCenter().getYCoordinate();
		donutTxt += "|R1:";
		donutTxt += getRadius();
		donutTxt += "|R2:";
		donutTxt += innerRadius;
		donutTxt += "|EdgeColor:";
		donutTxt += getEdgeColor().getRGB();
		donutTxt += "|InnerColor:";
		donutTxt += getInnerColor().getRGB();
		donutTxt += ")";
		return donutTxt;
	}

	public Shape clone() {
		Point centerOfClone = new Point(getCenter().getXCoordinate(), getCenter().getYCoordinate());
		int radiusOfClone = getRadius();
		int innerRadiusOfClone = innerRadius;
		Color edgeColorOfClone = getEdgeColor();
		Color innerColorOfClone = getInnerColor();
		boolean isSelectedOfclone = isSelected();

		Donut donut = new Donut(centerOfClone, radiusOfClone, innerRadiusOfClone);
		donut.setEdgeColor(edgeColorOfClone);
		donut.setInnerColor(innerColorOfClone);
		donut.setSelected(isSelectedOfclone);

		return donut;
	}

	public static Donut parse(String line) {
		line = line.replace("Donut(", "").replace(")", "");
		String[] parts = line.split("\\|");

		int x = Integer.parseInt(parts[0].replace("X:", ""));
		int y = Integer.parseInt(parts[1].replace("Y:", ""));
		int r1 = Integer.parseInt(parts[2].replace("R1:", ""));
		int r2 = Integer.parseInt(parts[3].replace("R2:", ""));
		Color edgeColor = Color.decode(parts[4].replace("EdgeColor:", ""));
		Color innerColor = Color.decode(parts[5].replace("InnerColor:", ""));

		return new Donut(new Point(x, y), r1, r2, edgeColor, innerColor);
	}
}
