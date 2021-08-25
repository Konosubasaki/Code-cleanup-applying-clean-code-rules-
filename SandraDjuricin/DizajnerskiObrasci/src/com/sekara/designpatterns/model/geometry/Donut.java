package com.sekara.designpatterns.model.geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, Color edgeColor, Color innerColor) {
		super(center, radius, edgeColor, innerColor);
		this.innerRadius = innerRadius;
		setEdgeColor(edgeColor);
		setInnerColor(innerColor);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);

		path.append(new Ellipse2D.Double(getCenter().getXCoordinate() - getRadius(),
				this.getCenter().getYCoordinate() - getRadius(), getRadius() * 2, getRadius() * 2), false);
		path.append(new Ellipse2D.Double(getCenter().getXCoordinate() - getInnerRadius(),
				this.getCenter().getYCoordinate() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2),
				false);

		g2d.setColor(getInnerColor());
		g2d.fill(path);

		g2d.setColor(getEdgeColor());
		g2d.drawOval(getCenter().getXCoordinate() - getRadius(), this.getCenter().getYCoordinate() - getRadius(),
				getRadius() * 2, getRadius() * 2);
		g2d.drawOval(getCenter().getXCoordinate() - getInnerRadius(),
				this.getCenter().getYCoordinate() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);

		if (isSelected()) {
			g2d.setColor(Color.BLUE);
			g2d.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3, 6, 6);
			g2d.drawRect(getCenter().getXCoordinate() - 3 - getRadius(), getCenter().getYCoordinate() - 3, 6, 6);
			g2d.drawRect(getCenter().getXCoordinate() - 3 + getRadius(), getCenter().getYCoordinate() - 3, 6, 6);
			g2d.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3 + getRadius(), 6, 6);
			g2d.drawRect(getCenter().getXCoordinate() - 3, getCenter().getYCoordinate() - 3 - getRadius(), 6, 6);
		}
	}

	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	public boolean containsXYpoint(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.containsXYpoint(x, y) && dFromCenter > innerRadius;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius()
					&& innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		return "Donut(X:" + getCenter().getXCoordinate() + "|Y:" + getCenter().getYCoordinate() + "|R1:" + getRadius()
				+ "|R2:" + innerRadius + "|EdgeColor:" + getEdgeColor().getRGB() + "|InnerColor:"
				+ getInnerColor().getRGB() + ")";
	}

	public Shape clone() {
		Donut donut = new Donut(new Point(getCenter().getXCoordinate(), getCenter().getYCoordinate()), getRadius(),
				getInnerRadius());
		donut.setEdgeColor(getEdgeColor());
		donut.setInnerColor(getInnerColor());
		donut.setSelected(isSelected());

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
