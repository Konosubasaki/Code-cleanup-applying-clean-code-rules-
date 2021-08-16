package com.sekara.designpatterns.view.frame;

import com.sekara.designpatterns.controller.MainController;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.observable.Observer;
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
	
	private ButtonGroup btnsOperation = new ButtonGroup();
	private ButtonGroup btnsShapes = new ButtonGroup();
	
	private JButton btnFileOpen = new JButton("Otvori");
	private JButton btnFileSave = new JButton("Sacuvaj");
	private JButton btnReadNextCommand = new JButton("Ucitaj sledecu komandu");
	private JButton btnUndo = new JButton("Ponisti");
	private JButton btnRedo = new JButton("Ponovi");
	private JButton btnPositionToFront = new JButton("Ispred");
	private JButton btnPositionBringToFront = new JButton("Ispred svih");
	private JButton btnPositionToBack = new JButton("Iza");
	private JButton btnPositionBringToBack = new JButton("Iza svih");
	private JButton btnActionEdit = new JButton("Izmeni");
	private JButton btnActionDelete = new JButton("Obrisi");
	private JButton btnActionDeleteAll = new JButton("Obrisi sve");
	private JButton btnColorEdge = new JButton(" ");
	private JButton btnColorInner = new JButton(" ");

	private JToggleButton btnOperationDrawing = new JToggleButton("Crtanje");
	private JToggleButton btnOperationEditOrDelete = new JToggleButton("Izmena/Brisanje");
	private JToggleButton btnShapePoint = new JToggleButton("Tacka");
	private JToggleButton btnShapeLine = new JToggleButton("Linija");
	private JToggleButton btnShapeRectangle = new JToggleButton("Pravougaonik");
	private JToggleButton btnShapeCircle = new JToggleButton("Krug");
	private JToggleButton btnShapeDonut = new JToggleButton("Krofna");
	private JToggleButton btnShapeHexagon = new JToggleButton("Sestougao");
	
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
		
		JPanel pnlLeftOperations = new JPanel();
		JPanel pnlRightOperations = new JPanel();
		pnlRightOperations.setLayout(new GridLayout(3, 0, 0, 0));
		JPanel pnlLog = new JPanel();
		contentPanel.add(pnlLeftOperations, BorderLayout.WEST);
		pnlLeftOperations.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel pnlChooseFile = new JPanel();
		pnlChooseFile.setBorder(new TitledBorder(null, "Fajl", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLeftOperations.add(pnlChooseFile);
		pnlChooseFile.setLayout(new BoxLayout(pnlChooseFile, BoxLayout.Y_AXIS));
		
		btnFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFromFileChooser = new JFileChooser();
				readFromFileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
				readFromFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				readFromFileChooser.enableInputMethods(true);
				readFromFileChooser.setMultiSelectionEnabled(false);
				readFromFileChooser.setFileHidingEnabled(false);
				readFromFileChooser.setEnabled(true);
				readFromFileChooser.setDialogTitle("Otvori");
				readFromFileChooser.setAcceptAllFileFilterUsed(false);
				readFromFileChooser.setFileFilter(drwFileFilter);
				readFromFileChooser.setFileFilter(logFileFilter);
				
				controller.readFromFile();
			}
		});
		btnFileOpen.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseFile.add(btnFileOpen);
				
		btnFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFileChooser = new JFileChooser();
				saveToFileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
				saveToFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				saveToFileChooser.enableInputMethods(false);
				saveToFileChooser.setMultiSelectionEnabled(false);
				saveToFileChooser.setFileHidingEnabled(false);
				saveToFileChooser.setEnabled(true);
				saveToFileChooser.setDialogTitle("Sacuvaj");
				saveToFileChooser.setAcceptAllFileFilterUsed(false);
 
				for (FileNameExtensionFilter fileFilter : listOfAvailableFileFilters) {
					saveToFileChooser.setFileFilter(fileFilter);
				}
				
				controller.saveToFile();
			}
		});
		btnFileSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFileSave.setEnabled(false);
		pnlChooseFile.add(btnFileSave);
		
		btnReadNextCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.readNextCommand();
			}
		});
		btnReadNextCommand.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseFile.add(btnReadNextCommand);
		
		JPanel pnlChooseMode = new JPanel();
		pnlChooseMode.setBorder(new TitledBorder(null, "Operacija", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLeftOperations.add(pnlChooseMode);
		pnlChooseMode.setLayout(new BoxLayout(pnlChooseMode, BoxLayout.Y_AXIS));
		
		btnOperationDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentMode(ModeType.Drawing);
				//setOperationDrawing();
			}
		});
		btnOperationDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(btnOperationDrawing);
		pnlChooseMode.add(btnOperationDrawing);
		
		btnOperationEditOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setCurrentMode(ModeType.Selecting);
 			}
		});
		btnOperationEditOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(btnOperationEditOrDelete);
		pnlChooseMode.add(btnOperationEditOrDelete);
		
		JPanel pnlChooseAction = new JPanel();
		pnlChooseAction.setBorder(new TitledBorder(null, "Akcija", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLeftOperations.add(pnlChooseAction);
		pnlChooseAction.setLayout(new BoxLayout(pnlChooseAction, BoxLayout.Y_AXIS));
		
		btnActionEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.editShape();
			}
		});
		btnActionEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseAction.add(btnActionEdit);
		
		btnActionDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteSelectedShapes();
			}
		});
		btnActionDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseAction.add(btnActionDelete);
		
		btnActionDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteAllShapes();
			}
		});
		btnActionDeleteAll.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseAction.add(btnActionDeleteAll);
		
		JPanel pnlChoosePosition = new JPanel();
		pnlChoosePosition.setBorder(new TitledBorder(null, "Pozicija", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLeftOperations.add(pnlChoosePosition);
		pnlChoosePosition.setLayout(new BoxLayout(pnlChoosePosition, BoxLayout.Y_AXIS));
		
		btnPositionToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionToFront();
			}
		});
		btnPositionToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChoosePosition.add(btnPositionToFront);
		
		btnPositionBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionBringToFront();
			}
		});
		btnPositionBringToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChoosePosition.add(btnPositionBringToFront);
		
		btnPositionToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionToBack();
			}
		});
		btnPositionToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChoosePosition.add(btnPositionToBack);
		
		btnPositionBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionBringToBack();
			}
		});
		btnPositionBringToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChoosePosition.add(btnPositionBringToBack);
		
		JPanel pnlUndoRedo = new JPanel();
		pnlUndoRedo.setBorder(new TitledBorder(null, "Ponisti/ponovi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRightOperations.add(pnlUndoRedo);
		pnlUndoRedo.setLayout(new BoxLayout(pnlUndoRedo, BoxLayout.Y_AXIS));
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undoCommand();
			}
		});
		btnUndo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUndo.setEnabled(false);
		pnlUndoRedo.add(btnUndo);
				
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redoCommand();
			}
		});
		btnRedo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRedo.setEnabled(false);
		pnlUndoRedo.add(btnRedo);
		
		JPanel pnlChooseShape = new JPanel();
		pnlChooseShape.setBorder(new TitledBorder(null, "Oblici", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRightOperations.add(pnlChooseShape);
		pnlChooseShape.setLayout(new BoxLayout(pnlChooseShape, BoxLayout.Y_AXIS));
		
		btnShapePoint.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedShape(ShapeType.Point);
			}
		});
		btnsShapes.add(btnShapePoint);
		pnlChooseShape.add(btnShapePoint);
		
		btnShapeLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedShape(ShapeType.Line);
			}
		});
		btnsShapes.add(btnShapeLine);
		pnlChooseShape.add(btnShapeLine);
		
		btnShapeRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedShape(ShapeType.Rectangle);
			}
		});
		btnsShapes.add(btnShapeRectangle);
		pnlChooseShape.add(btnShapeRectangle);
		
		btnShapeCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedShape(ShapeType.Circle);
			}
		});
		btnsShapes.add(btnShapeCircle);
		pnlChooseShape.add(btnShapeCircle);
		
		btnShapeDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedShape(ShapeType.Donut);
			}
		});
		btnsShapes.add(btnShapeDonut);
		pnlChooseShape.add(btnShapeDonut);
		
		btnShapeHexagon.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedShape(ShapeType.Hexagon);
			}
		});
		btnsShapes.add(btnShapeHexagon);
		pnlChooseShape.add(btnShapeHexagon);
		
		btnOperationDrawing.setSelected(true);
		btnShapePoint.setSelected(true);
		
		JPanel pnlChooseColor = new JPanel();
		pnlChooseColor.setBorder(new TitledBorder(null, "Boje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRightOperations.add(pnlChooseColor);
		pnlChooseColor.setLayout(new BoxLayout(pnlChooseColor, BoxLayout.Y_AXIS));
		
		lblColorEdge.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseColor.add(lblColorEdge);
		
		btnColorEdge.addActionListener(btnColorEdgeClickListener());
		btnColorEdge.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseColor.add(btnColorEdge);
		
		lblColorInner.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseColor.add(lblColorInner);
		
		btnColorInner.addActionListener(btnColorInnerClickListener());
		btnColorInner.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChooseColor.add(btnColorInner);
		
		contentPanel.add(pnlLog, BorderLayout.SOUTH);
		
		listLog.setModel(defaultListModel);
		listLog.setVisibleRowCount(10);
		listLog.setFixedCellWidth(1080);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(listLog);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent e) {  
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
		
		pnlLog.add(scrollPane);
		
		contentPanel.add(pnlRightOperations, BorderLayout.EAST);
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
		
		btnColorEdge.setBackground(controller.getEdgeColor());
		btnColorInner.setBackground(controller.getInnerColor());
		
		controller.setCurrentMode(ModeType.Drawing);
	}

	private ActionListener btnColorEdgeClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", controller.getEdgeColor());
				if (edgeColor != null) {
					controller.setEdgeColor(edgeColor);
					btnColorEdge.setBackground(edgeColor);
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
					btnColorInner.setBackground(innerColor);
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
		return btnReadNextCommand;
	}

	@Override
	public void update(ModeType currentMode, int numOfShapes, int numOfUndoCommands, int numOfRedoCommands, int numOfSelectedShapes, boolean logHasLines) {
		if (numOfShapes != 0 && logHasLines) {
			btnFileSave.setEnabled(true);
			listOfAvailableFileFilters.clear();
			listOfAvailableFileFilters.add(drwFileFilter);
			listOfAvailableFileFilters.add(logFileFilter);
		} else if (numOfShapes == 0 && logHasLines) {
			btnFileSave.setEnabled(true);
			listOfAvailableFileFilters.clear();
			listOfAvailableFileFilters.add(logFileFilter);
		} else if (numOfShapes != 0 && !logHasLines) {
			btnFileSave.setEnabled(true);
			listOfAvailableFileFilters.clear();
			listOfAvailableFileFilters.add(drwFileFilter);
		} else {
			btnFileSave.setEnabled(false);
			listOfAvailableFileFilters.clear();
		}
		
		if (numOfUndoCommands > 0) {
			btnUndo.setEnabled(true);
		} else {
			btnUndo.setEnabled(false);
		}
		
		if (numOfRedoCommands > 0) {
			btnRedo.setEnabled(true);
		} else {
			btnRedo.setEnabled(false);
		}
		
		if (currentMode == ModeType.Drawing) {
			btnActionEdit.setEnabled(false);
			btnActionDelete.setEnabled(false);
			btnActionDeleteAll.setEnabled(false);
			
			btnShapePoint.setEnabled(true);
			btnShapeLine.setEnabled(true);
			btnShapeRectangle.setEnabled(true);
			btnShapeCircle.setEnabled(true);
			btnShapeDonut.setEnabled(true);
			btnShapeHexagon.setEnabled(true);
			
			btnColorEdge.setEnabled(true);
			btnColorInner.setEnabled(true);
			
			btnPositionToFront.setEnabled(false);
			btnPositionBringToFront.setEnabled(false);
			btnPositionToBack.setEnabled(false);
			btnPositionBringToBack.setEnabled(false);
		} else {
			if (numOfSelectedShapes == 1) {
				btnActionEdit.setEnabled(true);
			} else {
				btnActionEdit.setEnabled(false);
			}
			
			if (numOfSelectedShapes != 0) {
				btnActionDelete.setEnabled(true);
			} else {
				btnActionDelete.setEnabled(false);
			}
			
			if (numOfShapes != 0) {
				btnActionDeleteAll.setEnabled(true);
			} else {
				btnActionDeleteAll.setEnabled(false);
			}
			
			btnShapePoint.setEnabled(false);
			btnShapeLine.setEnabled(false);
			btnShapeRectangle.setEnabled(false);
			btnShapeCircle.setEnabled(false);
			btnShapeDonut.setEnabled(false);
			btnShapeHexagon.setEnabled(false);
			btnColorEdge.setEnabled(false);
			btnColorInner.setEnabled(false);
			
			if (numOfSelectedShapes == 1) {
				btnPositionToFront.setEnabled(true);
				btnPositionBringToFront.setEnabled(true);
				btnPositionToBack.setEnabled(true);
				btnPositionBringToBack.setEnabled(true);
			} else {
				btnPositionToFront.setEnabled(false);
				btnPositionBringToFront.setEnabled(false);
				btnPositionToBack.setEnabled(false);
				btnPositionBringToBack.setEnabled(false);
			}
		}
	}
}
