package com.sekara.designpatterns.model.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color edgeColor, Color innerColor) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
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
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, this.height);
		g.setColor(getInnerColor());
		g.fillRect(this.getUpperLeftPoint().getX()+1, this.getUpperLeftPoint().getY()+1, this.width-1, this.height-1);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x 
				&& x <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= y
				&& y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getX() <= p.getX() 
				&& p.getX() <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight()
					&& this.width == r.getWidth()) {
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
		return "Rectangle(X:" + upperLeftPoint.getX() + "|Y:" + upperLeftPoint.getY() + "|W:" + width + "|H:" + height + "|EdgeColor:" + getEdgeColor().getRGB() + "|InnerColor:" + getInnerColor().getRGB() + ")";
	}

	@Override
	public Shape clone() {
		Rectangle rectangle = new Rectangle(new Point(getUpperLeftPoint().getX(), getUpperLeftPoint().getY()), getHeight(), getWidth());
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
