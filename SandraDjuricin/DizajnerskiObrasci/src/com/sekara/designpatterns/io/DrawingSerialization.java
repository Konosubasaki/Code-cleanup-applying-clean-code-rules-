package com.sekara.designpatterns.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sekara.designpatterns.model.ViewModel;
import com.sekara.designpatterns.model.geometry.Shape;

public class DrawingSerialization implements Strategy {
	
	private ViewModel viewModel;
	private FileOutputStream outputStream;
	private FileInputStream inputStream;
	
	public DrawingSerialization(ViewModel viewModel) {
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
			viewModel.addAll(listOfShapes);
			objectInputStream.close();
	        inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
