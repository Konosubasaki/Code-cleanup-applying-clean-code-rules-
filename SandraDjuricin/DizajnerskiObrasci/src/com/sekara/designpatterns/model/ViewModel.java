package com.sekara.designpatterns.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.sekara.designpatterns.model.geometry.Shape;

public class ViewModel {
	
	private List<Shape> listOfShapes = new ArrayList<Shape>();
	
	public List<Shape> getAllShapes() {
		return listOfShapes;
	}
	
	public Shape getShape(int index) {
		return listOfShapes.get(index);
	}
	
	public int getIndex(Shape shape) {
		return listOfShapes.indexOf(shape);
	}
	
	public int getSize() {
		return listOfShapes.size();
	}
	
	public List<Shape> getSelectedShapes() {
		return listOfShapes.stream().filter(shape -> shape.isSelected()).collect(Collectors.toList());
	}
	
	public int getSizeSelectedShapes() {
		return getSelectedShapes().size();
	}
	
	public void addShape(Shape shape) {
		listOfShapes.add(shape);
	}
	
	public void addAtIndex(Shape shape, int index) {
		listOfShapes.add(index, shape);
	}
	
	public void addAll(List<Shape> shapes) {
		listOfShapes.addAll(shapes);
	}
	
	public void updateShape(Shape oldShape, Shape newShape) {
		int index = listOfShapes.indexOf(oldShape);
		listOfShapes.set(index, newShape);
	}
	
	public void remove(Shape shape) {
		listOfShapes.remove(shape);
	}
	
	public void removeAll(List<Shape> shapes) {
		listOfShapes.removeAll(shapes);
	}

}
