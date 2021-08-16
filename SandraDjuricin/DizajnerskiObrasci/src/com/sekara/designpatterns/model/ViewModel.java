package com.sekara.designpatterns.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.sekara.designpatterns.model.geometry.Shape;

public class ViewModel {
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	public List<Shape> getAllShapes() {
		return shapes;
	}
	
	public Shape getShape(int index) {
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
	
	public void addAtIndex(Shape shape, int index) {
		shapes.add(index, shape);
	}
	
	public void addAll(List<Shape> shapes) {
		this.shapes.addAll(shapes);
	}
	
	public void updateShape(Shape oldShape, Shape newShape) {
		int index = shapes.indexOf(oldShape);
		shapes.set(index, newShape);
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
	}
	
	public void removeAll(List<Shape> shapes) {
		this.shapes.removeAll(shapes);
	}
}
