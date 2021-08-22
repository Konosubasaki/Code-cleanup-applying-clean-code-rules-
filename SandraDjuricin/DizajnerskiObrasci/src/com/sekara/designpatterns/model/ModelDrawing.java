package com.sekara.designpatterns.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.sekara.designpatterns.model.geometry.Shape;

public class ModelDrawing {

	private List<Shape> shapes = new ArrayList<Shape>();

	public List<Shape> getAllShapes() {
		return shapes; 
	}

	public Shape getShapeByIndex(int index) {
		return shapes.get(index);
	}

	public int getIndexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}

	public int getSizeOfShapeList() {
		return shapes.size();
	}

	public List<Shape> getSelectedShapes() {
		return shapes.stream().filter(shape -> shape.isSelected()).collect(Collectors.toList());
	}

	public int getSizeSelectedShapes() {
		return getSelectedShapes().size();
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void addShapeAtIndex(Shape shape, int index) {
		shapes.add(index, shape);
	}

	public void addAllShapes(List<Shape> shapes) {
		this.shapes.addAll(shapes);
	}

	public void updateShape(Shape oldShape, Shape newShape) {
		int index = shapes.indexOf(oldShape);
		shapes.set(index, newShape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public void removeShapes(List<Shape> shapes) {
		this.shapes.removeAll(shapes);
	}
	
	public boolean containsShape(Shape shape) {
		return this.shapes.contains(shape);
	}
}
