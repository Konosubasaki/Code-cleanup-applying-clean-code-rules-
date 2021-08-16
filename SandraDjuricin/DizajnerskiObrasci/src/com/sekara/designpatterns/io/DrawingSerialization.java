package com.sekara.designpatterns.io;

import java.io.*;
import java.util.*;

import com.sekara.designpatterns.model.Model;
import com.sekara.designpatterns.model.geometry.Shape;

public class DrawingSerialization implements Strategy {
	
	private Model viewModel;
	private FileOutputStream outputStream;
	private FileInputStream inputStream;
	
	public DrawingSerialization(Model viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void saveToFile(File file) {
		try {
			outputStream = new FileOutputStream(file + ".drw");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(viewModel.getAllShapes());
			objectOutputStream.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readFromFile(File file) {
		try {
			inputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			List<Shape> listOfShapes = (ArrayList<Shape>) objectInputStream.readObject();
			viewModel.addAllShapes(listOfShapes);
			objectInputStream.close();
	        inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
