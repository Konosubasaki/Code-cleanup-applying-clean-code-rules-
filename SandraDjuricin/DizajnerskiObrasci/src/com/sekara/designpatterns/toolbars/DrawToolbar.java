package com.sekara.designpatterns.toolbars;

import com.sekara.designpatterns.controller.*;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.observable.Observer;
import com.sekara.designpatterns.view.panel.ViewDrawing;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawToolbar implements Observer {

	private MainController mainController;
	private FileController fileController;
	private ViewDrawing view = new ViewDrawing();
	private ButtonGroup btnsOperation = new ButtonGroup();
	private ButtonGroup btnsShapes = new ButtonGroup();
	private JButton btnFileOpen;
	private JButton btnFileSave;
	private JButton btnReadNextCommand;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnPositionToFront;
	private JButton btnPositionBringToFront;
	private JButton btnPositionToBack;
	private JButton btnPositionBringToBack;
	private JButton btnActionEdit;
	private JButton btnActionDelete;
	private JButton btnActionDeleteAll;
	private JButton btnColorEdge;
	private JButton btnColorInner;
	private JToggleButton btnOperationDrawing;
	private JToggleButton btnOperationEditOrDelete;
	private JToggleButton btnShapePoint;
	private JToggleButton btnShapeLine;
	private JToggleButton btnShapeRectangle;
	private JToggleButton btnShapeCircle;
	private JToggleButton btnShapeDonut;
	private JToggleButton btnShapeHexagon;
	private JPanel pnlLeftOperations;
	private JPanel pnlRightOperations;
	private JPanel pnlChooseFile;
	private JPanel pnlChooseMode;
	private JPanel pnlChooseAction;
	private JPanel pnlChoosePosition;
	private JPanel pnlUndoRedo;
	private JPanel pnlChooseShape;
	private JPanel pnlChooseColor;
	private JPanel pnlLog;
	private JLabel lblColorEdge = new JLabel("Boja ivice");
	private JLabel lblColorInner = new JLabel("Boja unutrasnjosti");
	private JFileChooser readFromFileChooser;
	private JFileChooser saveToFileChooser;
	private JList listLog = new JList();
	private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
	private FileNameExtensionFilter drwFileFilter = new FileNameExtensionFilter("Crtez", "drw");
	private FileNameExtensionFilter logFileFilter = new FileNameExtensionFilter("Log", "log");
	private List<FileNameExtensionFilter> listOfAvailableFileFilters = new ArrayList<FileNameExtensionFilter>();

	public DrawToolbar() {

		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				mainController.mouseClicked(arg0);
			}
		});
		view.setBorder(new LineBorder(SystemColor.textHighlight, 500));

		initializeButtons();
		initializePanels();
		setPanelsLayouts();
		setPanelsBorders();
		addingButtonsToPanelsAndGroups();
		disableButtons();
		enableButtons();
		centerAlignButtons();
		buttonsActionListeners();
 
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
	}

	public void initializeButtons() {

		btnFileOpen = new JButton("Otvori");
		btnFileSave = new JButton("Sacuvaj");
		btnReadNextCommand = new JButton("Ucitaj sledecu komandu");
		btnUndo = new JButton("Ponisti");
		btnRedo = new JButton("Ponovi");
		btnPositionToFront = new JButton("Ispred");
		btnPositionBringToFront = new JButton("Ispred svih");
		btnPositionToBack = new JButton("Iza");
		btnPositionBringToBack = new JButton("Iza svih");
		btnActionEdit = new JButton("Izmeni");
		btnActionDelete = new JButton("Obrisi");
		btnActionDeleteAll = new JButton("Obrisi sve");
		btnColorEdge = new JButton(" ");
		btnColorInner = new JButton(" ");
		btnOperationDrawing = new JToggleButton("Crtanje");
		btnOperationEditOrDelete = new JToggleButton("Izmena/Brisanje");
		btnShapePoint = new JToggleButton("Tacka");
		btnShapeLine = new JToggleButton("Linija");
		btnShapeRectangle = new JToggleButton("Pravougaonik");
		btnShapeCircle = new JToggleButton("Krug");
		btnShapeDonut = new JToggleButton("Krofna");
		btnShapeHexagon = new JToggleButton("Sestougao");
	}
	
	public void initializePanels() {
		
		pnlLeftOperations = new JPanel();
		pnlRightOperations = new JPanel();
		pnlChooseFile = new JPanel();
		pnlChooseMode = new JPanel();
		pnlChooseAction = new JPanel();
		pnlChoosePosition = new JPanel();
		pnlUndoRedo = new JPanel();
		pnlChooseShape = new JPanel();
		pnlChooseColor = new JPanel();
		pnlLog = new JPanel();
	}
	
	public void setPanelsLayouts() {
		
		pnlLeftOperations.setLayout(new GridLayout(4, 0, 0, 0));
		pnlRightOperations.setLayout(new GridLayout(3, 0, 0, 0));
		pnlChooseFile.setLayout(new BoxLayout(pnlChooseFile, BoxLayout.Y_AXIS));
		pnlChooseMode.setLayout(new BoxLayout(pnlChooseMode, BoxLayout.Y_AXIS));
		pnlChooseAction.setLayout(new BoxLayout(pnlChooseAction, BoxLayout.Y_AXIS));
		pnlChoosePosition.setLayout(new BoxLayout(pnlChoosePosition, BoxLayout.Y_AXIS));
		pnlUndoRedo.setLayout(new BoxLayout(pnlUndoRedo, BoxLayout.Y_AXIS));
		pnlChooseShape.setLayout(new BoxLayout(pnlChooseShape, BoxLayout.Y_AXIS));
		pnlChooseColor.setLayout(new BoxLayout(pnlChooseColor, BoxLayout.Y_AXIS));
	}
	
	public void setPanelsBorders() {
		
		pnlChooseFile.setBorder(new TitledBorder(null, "Fajl", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChooseMode.setBorder(new TitledBorder(null, "Operacija", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChooseAction.setBorder(new TitledBorder(null, "Akcija", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChoosePosition.setBorder(new TitledBorder(null, "Pozicija", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlUndoRedo.setBorder(new TitledBorder(null, "Ponisti/ponovi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChooseShape.setBorder(new TitledBorder(null, "Oblici", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChooseColor.setBorder(new TitledBorder(null, "Boje", TitledBorder.LEADING, TitledBorder.TOP, null, null));

	}

	public void addingButtonsToPanelsAndGroups() {
		
		pnlChooseFile.add(btnFileOpen);
		pnlChooseFile.add(btnFileSave);
		pnlChooseFile.add(btnReadNextCommand);
		pnlChooseMode.add(btnOperationDrawing);
		pnlChooseMode.add(btnOperationEditOrDelete);
		pnlChooseAction.add(btnActionEdit);
		pnlChooseAction.add(btnActionDelete);
		pnlChooseAction.add(btnActionDeleteAll);
		pnlChoosePosition.add(btnPositionToFront);
		pnlChoosePosition.add(btnPositionBringToFront);
		pnlChoosePosition.add(btnPositionToBack);
		pnlChoosePosition.add(btnPositionBringToBack);
		pnlUndoRedo.add(btnUndo);
		pnlUndoRedo.add(btnRedo);
		pnlChooseShape.add(btnShapePoint);
		pnlChooseShape.add(btnShapeLine);
		pnlChooseShape.add(btnShapeRectangle);
		pnlChooseShape.add(btnShapeCircle);
		pnlChooseShape.add(btnShapeDonut);
		pnlChooseShape.add(btnShapeHexagon);
		pnlChooseColor.add(lblColorEdge);
		pnlChooseColor.add(btnColorEdge);
		pnlChooseColor.add(lblColorInner);
		pnlChooseColor.add(btnColorInner);
		pnlLeftOperations.add(pnlChooseFile);
		pnlLeftOperations.add(pnlChooseMode);
		pnlLeftOperations.add(pnlChooseAction);
		pnlLeftOperations.add(pnlChoosePosition);
		pnlRightOperations.add(pnlUndoRedo);
		pnlRightOperations.add(pnlChooseShape);
		pnlRightOperations.add(pnlChooseColor);
		btnsOperation.add(btnOperationDrawing);
		btnsOperation.add(btnOperationEditOrDelete);
		btnsShapes.add(btnShapePoint);
		btnsShapes.add(btnShapeLine);
		btnsShapes.add(btnShapeRectangle);
		btnsShapes.add(btnShapeCircle);
		btnsShapes.add(btnShapeDonut);
		btnsShapes.add(btnShapeHexagon);
	}
	
	public void disableButtons() {
		btnFileSave.setEnabled(false);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);

	}

	public void enableButtons() {
		btnOperationDrawing.setSelected(true);
		btnShapePoint.setSelected(true);
	}

	public void centerAlignButtons() {
		btnFileOpen.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFileSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReadNextCommand.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOperationDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOperationEditOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnActionEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnActionDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnActionDeleteAll.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPositionToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPositionBringToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPositionToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPositionBringToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUndo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRedo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapePoint.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShapeHexagon.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblColorEdge.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblColorInner.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnColorEdge.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnColorInner.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public void buttonsActionListeners() {

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
				fileController.readFromFile();
			}
		});

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
				fileController.saveToFile();
			}
		});

		btnPositionBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.positionBringToBack();
			}
		});

		btnPositionToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.positionToBack();
			}
		});

		btnPositionBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.positionBringToFront();
			}
		});

		btnPositionToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.positionToFront();
			}
		});

		btnActionDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.deleteAllShapes();
			}
		});

		btnActionDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.deleteSelectedShapes();
			}
		});

		btnActionEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.editShape();
			}
		});

		btnOperationEditOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setCurrentMode(ModeType.Selecting);
			}
		});

		btnOperationDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setCurrentMode(ModeType.Drawing);
				// setOperationDrawing();
			}
		});

		btnReadNextCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileController.readNextCommand();
			}
		});

		btnShapeDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setSelectedShape(ShapeType.Donut);
			}
		});

		btnShapeHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setSelectedShape(ShapeType.Hexagon);
			}
		});

		btnShapeCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setSelectedShape(ShapeType.Circle);
			}
		});

		btnShapeRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setSelectedShape(ShapeType.Rectangle);
			}
		});

		btnShapeLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setSelectedShape(ShapeType.Line);
			}
		});

		btnShapePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.setSelectedShape(ShapeType.Point);
			}
		});

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.undoCommand();
			}
		});

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.redoCommand();
			}
		});

		btnColorEdge.addActionListener(btnColorEdgeClickListener());

		btnColorInner.addActionListener(btnColorInnerClickListener());

	}

	private ActionListener btnColorEdgeClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", mainController.getEdgeColor());
				if (edgeColor != null) {
					mainController.setEdgeColor(edgeColor);
					btnColorEdge.setBackground(edgeColor);
				}
			}
		};
	}

	private ActionListener btnColorInnerClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrasnjosti",
						mainController.getInnerColor());
				if (innerColor != null) {
					mainController.setInnerColor(innerColor);
					btnColorInner.setBackground(innerColor);
				}
			}
		};
	}
	
	public void setControllers(MainController controller, FileController fileController) {
		this.mainController = controller;
		this.fileController = fileController;
		btnColorEdge.setBackground(controller.getEdgeColor());
		btnColorInner.setBackground(controller.getInnerColor());
		controller.setCurrentMode(ModeType.Drawing);
	}
	
	public JPanel getPnlLeftOperations() {
		return pnlLeftOperations;
	}

	public JPanel getPnlRightOperations() {
		return pnlRightOperations;
	}
	
	public ViewDrawing getViewDrawging() {
		return this.view;
	}

	public DefaultListModel<String> getDefaultListLogModel() {
		return this.defaultListModel;
	}
	
	public JPanel getPnlLog() {
		return pnlLog;
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

	public JButton getBtnColorEdge() {
		return btnColorEdge;
	}

	public JButton getBtnColorInner() {
		return btnColorInner;
	}

	@Override
	public void updateUndoRedoButtonsState(int numOfUndoCommands, int numOfRedoCommands) {

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
	}

	@Override
	public void updateFileButtonState(int numOfShapes, boolean logHasLines) {

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
	}

	@Override
	public void updateShapeManipulationButtonsState(int numOfSelectedShapes, ModeType currentMode, int numOfShapes) {

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
				btnPositionToFront.setEnabled(true);
				btnPositionBringToFront.setEnabled(true);
				btnPositionToBack.setEnabled(true);
				btnPositionBringToBack.setEnabled(true);
			} else {
				btnActionEdit.setEnabled(false);
				btnPositionToFront.setEnabled(false);
				btnPositionBringToFront.setEnabled(false);
				btnPositionToBack.setEnabled(false);
				btnPositionBringToBack.setEnabled(false);
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
		}
	}
}
