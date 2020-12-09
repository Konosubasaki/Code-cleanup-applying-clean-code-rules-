package com.sekara.designpatterns.view.panel;

import javax.swing.JPanel;

import com.sekara.designpatterns.model.ViewModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PnlDrawing extends JPanel {
	
	private ViewModel viewModel;
	
	public PnlDrawing() {
		setBackground(Color.WHITE);
	}
	
	public void setViewModel(ViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		
		super.paint(g);
		
		if(viewModel != null) {
			viewModel.getAllShapes().forEach(shape -> shape.draw(g));
		}
	}
}
