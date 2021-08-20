package com.sekara.designpatterns.view.frame;

import com.sekara.designpatterns.controller.MainController;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.observable.Observer;
import com.sekara.designpatterns.toolbars.DrawToolbar;
import com.sekara.designpatterns.view.panel.ViewDrawing;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class FrameDrawing extends JFrame {

	private MainController mainController;
	private ViewDrawing view;
	private DrawToolbar drawToolbar;
	private JPanel contentPanel;

	public FrameDrawing() {
		setTitle("I7/8 Sandra Duricin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 900);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1100, 700));
		
		view = new ViewDrawing();
		view.setBorder(new LineBorder(SystemColor.textHighlight, 5));
		drawToolbar = new DrawToolbar();
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
 		contentPanel.add(view, BorderLayout.CENTER);
		contentPanel.add(drawToolbar.getPnlLog(), BorderLayout.SOUTH);
		contentPanel.add(drawToolbar.getPnlLeftOperations(), BorderLayout.WEST);
		contentPanel.add(drawToolbar.getPnlRightOperations(), BorderLayout.EAST);
		
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				mainController.mouseClicked(arg0);
			}
		});
	}

	public void setController(MainController mainController) {
		this.mainController = mainController;
		this.mainController.setCurrentMode(ModeType.Drawing);
		drawToolbar.getBtnColorEdge().setBackground(mainController.getEdgeColor());
		drawToolbar.getBtnColorInner().setBackground(mainController.getInnerColor());
		
	}

	public DrawToolbar getDrawToolbar() {
		return drawToolbar;
	}

	public ViewDrawing getViewDrawing() {
		return this.view;
	}
}
