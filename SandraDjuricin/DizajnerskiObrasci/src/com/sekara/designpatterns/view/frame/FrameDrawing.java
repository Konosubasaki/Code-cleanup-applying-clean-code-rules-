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

public class FrameDrawing extends JFrame implements Observer {

	private MainController mainController;
	private ViewDrawing view = new ViewDrawing();

	//
	DrawToolbar drawToolbar;

	private JPanel contentPanel;

	public FrameDrawing() {
		//
		drawToolbar = new DrawToolbar();

		setTitle("IT 48-2017 Sekara Danilo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 900);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1100, 700));

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);

		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				mainController.mouseClicked(arg0);
			}
		});
		view.setBorder(new LineBorder(SystemColor.textHighlight, 5));
		contentPanel.add(view, BorderLayout.CENTER);

		contentPanel.add(drawToolbar.getPnlLog(), BorderLayout.SOUTH);

		contentPanel.add(drawToolbar.getPnlLeftOperations(), BorderLayout.WEST);
		contentPanel.add(drawToolbar.getPnlRightOperations(), BorderLayout.EAST);
	}

	public void setController(MainController controller) {
		this.mainController = controller;

		drawToolbar.getBtnColorEdge().setBackground(controller.getEdgeColor());
		drawToolbar.getBtnColorInner().setBackground(controller.getInnerColor());

		controller.setCurrentMode(ModeType.Drawing);
	}

	public DrawToolbar getDrawToolbar() {
		return drawToolbar;
	}

	public ViewDrawing getViewDrawing() {
		return this.view;
	}

	@Override
	public void update(ModeType currentMode, int numOfShapes, int numOfUndoCommands, int numOfRedoCommands,
			int numOfSelectedShapes, boolean logHasLines) {
		drawToolbar.update(currentMode, numOfShapes, numOfUndoCommands, numOfRedoCommands, numOfSelectedShapes,
				logHasLines);
	}
}
