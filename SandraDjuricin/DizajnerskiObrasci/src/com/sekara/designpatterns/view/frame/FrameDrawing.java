package com.sekara.designpatterns.view.frame;

import com.sekara.designpatterns.controller.MainController;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.observable.Observer;
import com.sekara.designpatterns.toolbars.DrawToolbar;
import com.sekara.designpatterns.view.panel.ViewDrawing;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameDrawing extends JFrame implements Observer {
	
	private MainController controller;
	private ViewDrawing viewDrawing = new ViewDrawing();
	
	//
	DrawToolbar drawToolbar;
	
	private JLabel lblColorEdge = new JLabel("Boja ivice");
 	private JLabel lblColorInner = new JLabel("Boja unutrasnjosti");
	private JPanel contentPanel;

	private JFileChooser readFromFileChooser;
	private JFileChooser saveToFileChooser;
	
	private JList listLog = new JList();
	private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
	
	private FileNameExtensionFilter drwFileFilter = new FileNameExtensionFilter("Crtez", "drw");
	private FileNameExtensionFilter logFileFilter = new FileNameExtensionFilter("Log", "log");
	
	private List<FileNameExtensionFilter> listOfAvailableFileFilters = new ArrayList<FileNameExtensionFilter>();
	

 

	public FrameDrawing() {
		//
		drawToolbar= new DrawToolbar();
		
		
		setTitle("IT 48-2017 Sekara Danilo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 900);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1100, 700));
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);

		viewDrawing.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);
			}
		});
		viewDrawing.setBorder(new LineBorder(SystemColor.textHighlight, 5));
		contentPanel.add(viewDrawing, BorderLayout.CENTER);
		
		//
		
		
		
		
		
		
		
		//
		
		contentPanel.add(drawToolbar.getPnlLog(), BorderLayout.SOUTH);
		
//		listLog.setModel(defaultListModel);
//		listLog.setVisibleRowCount(10);
//		listLog.setFixedCellWidth(1080);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setViewportView(listLog);
//		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
//			public void adjustmentValueChanged(AdjustmentEvent e) {  
//				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//			}
//		});
//		
//		pnlLog.add(scrollPane);
		
		
		
		contentPanel.add(drawToolbar.getPnlLeftOperations(), BorderLayout.WEST);
		contentPanel.add(drawToolbar.getPnlRightOperations(), BorderLayout.EAST);
	}
	
	
	public ViewDrawing getViewDrawing() {
		return this.viewDrawing;
	}
	//+++++++++++
	public DefaultListModel<String> getDefaultListLogModel() {
		return this.defaultListModel;
	}
	
	public void setController(MainController controller) {
		this.controller = controller;
		
		drawToolbar.getBtnColorEdge().setBackground(controller.getEdgeColor());
		drawToolbar.getBtnColorInner().setBackground(controller.getInnerColor());
		
		controller.setCurrentMode(ModeType.Drawing);
	}

	public DrawToolbar getDrawToolbar() {
		return drawToolbar;
	}


	private ActionListener btnColorEdgeClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", controller.getEdgeColor());
				if (edgeColor != null) {
					controller.setEdgeColor(edgeColor);
					drawToolbar.getBtnColorEdge().setBackground(edgeColor);
				}
			}
		};
	}
	
	private ActionListener btnColorInnerClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrasnjosti", controller.getInnerColor());
				if (innerColor != null) {
					controller.setInnerColor(innerColor);
					drawToolbar.getBtnColorInner().setBackground(innerColor);
				}
			}
		};
	}

	public JFileChooser getReadFromFileChooser() {
		return readFromFileChooser;
	}
	
	public JFileChooser getSaveToFileChooser() {
		return saveToFileChooser;
	}
	
	public JButton getBtnReadNextCommand() {
		return drawToolbar.getBtnReadNextCommand();
	}

	@Override
	public void update(ModeType currentMode, int numOfShapes, int numOfUndoCommands, int numOfRedoCommands, int numOfSelectedShapes, boolean logHasLines) {
		drawToolbar.update(currentMode, numOfShapes, numOfUndoCommands, numOfRedoCommands, numOfSelectedShapes, logHasLines);
	}
}
