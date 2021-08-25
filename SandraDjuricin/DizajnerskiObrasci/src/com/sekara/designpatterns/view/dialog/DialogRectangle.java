package com.sekara.designpatterns.view.dialog;

import com.sekara.designpatterns.model.geometry.Point;
import com.sekara.designpatterns.model.geometry.Rectangle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogRectangle extends JDialog {
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private Rectangle rectangle = null;
	private Color edgeColor = null, innerColor = null;
	private boolean isSelected = false;
	private JButton btnEdgeColor = new JButton(" ");
	private JButton btnInnerColor = new JButton(" ");

	public DialogRectangle() {
		setResizable(false);
		setTitle("IT 48-2017 Šekara Danilo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 210);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(6, 2, 0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("X koordinata", SwingConstants.CENTER);
				panel.add(lblNewLabel_1);
			}
			{
				txtX = new JTextField();
				panel.add(txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Y koordinata");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				txtY = new JTextField();
				panel.add(txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Visina");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_2);
			}
			{
				txtHeight = new JTextField();
				panel.add(txtHeight);
				txtHeight.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("širina");
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_3);
			}
			{
				txtWidth = new JTextField();
				panel.add(txtWidth);
				txtWidth.setColumns(10);
			}
			{
				JLabel lblEdgeColor = new JLabel("Boja ivice");
				lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblEdgeColor);
			}
			{
				btnEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnEdgeColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						edgeColor = JColorChooser.showDialog(null, "Izaberite boju ivice", edgeColor);
						if (edgeColor == null)
							edgeColor = Color.BLACK;
						btnEdgeColor.setBackground(edgeColor);
					}
				});
				panel.add(btnEdgeColor);
			}
			{
				JLabel lblInnerColor = new JLabel("Boja unutrašnjosti");
				lblInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblInnerColor);
			}
			{
				btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnInnerColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						innerColor = JColorChooser.showDialog(null, "Izaberite boju unutrašnjosti", innerColor);
						if (innerColor == null)
							innerColor = Color.WHITE;
						btnInnerColor.setBackground(innerColor);
					}
				});
				panel.add(btnInnerColor);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton("Potvrdi");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							int newHeight = Integer.parseInt(txtHeight.getText());
							int newWIdth = Integer.parseInt(txtWidth.getText());

							if (newX < 0 || newY < 0 || newHeight < 1 || newWIdth < 1) {
								JOptionPane.showMessageDialog(null, "Uneli ste pogrešne podatke!", "Greška!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							rectangle = new Rectangle(new Point(newX, newY), newHeight, newWIdth, edgeColor,
									innerColor);
							rectangle.setSelected(isSelected);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Uneli ste pogrešne podatke!", "Greška!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				panel.add(btnOk);
			}
			{
				JButton btnNotOk = new JButton("Odustani");
				btnNotOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				panel.add(btnNotOk);
			}
		}
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getXCoordinate());
		txtY.setText("" + point.getYCoordinate());
	}

	public void setColors(Color edgeColor, Color innerColor) {
		this.edgeColor = edgeColor;
		this.innerColor = innerColor;
		btnEdgeColor.setBackground(edgeColor);
		btnInnerColor.setBackground(innerColor);
	}

	public void setRectangle(Rectangle rect) {
		txtX.setText("" + rect.getUpperLeftPoint().getXCoordinate());
		txtY.setText("" + rect.getUpperLeftPoint().getYCoordinate());
		txtHeight.setText("" + rect.getHeight());
		txtWidth.setText("" + rect.getWidth());
		edgeColor = rect.getEdgeColor();
		innerColor = rect.getInnerColor();
		isSelected = rect.isSelected();
		setColors(rect.getEdgeColor(), rect.getInnerColor());
	}
}
