package com.sekara.designpatterns.view.panel;

import javax.swing.JPanel;

import com.sekara.designpatterns.model.ModelDrawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ViewDrawing extends JPanel {

	private ModelDrawing model;

	public ViewDrawing() {
		setBackground(Color.WHITE);
	}

	public void setModel(ModelDrawing modelDrawing) {
		this.model = modelDrawing;
	}
 
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		super.paint(g);
		if (model != null) {
			model.getAllShapes().forEach(shape -> shape.draw(g));
		}
	}
}
