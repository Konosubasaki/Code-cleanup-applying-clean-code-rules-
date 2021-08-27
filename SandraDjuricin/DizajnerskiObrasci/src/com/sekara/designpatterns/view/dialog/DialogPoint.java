package com.sekara.designpatterns.view.dialog;

import com.sekara.designpatterns.model.geometry.Point;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogPoint extends JDialog {
	private JTextField txtX;
	private JTextField txtY;

	private Point point = null;
	private Color edgeColor = null;

	private boolean isSelected = false;

	private JButton btnEdgeColor = new JButton(" ");

	public DialogPoint() {
		setResizable(false);
		setTitle("I7 8 2020");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 150);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(3, 2, 0, 0));
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

							if (newX < 0 || newY < 0) {
								JOptionPane.showMessageDialog(null, "Uneli ste pogrešne podatke!", "Greška!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							point = new Point(newX, newY, edgeColor);
							point.setSelected(isSelected);
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		txtX.setText("" + point.getXCoordinate());
		txtY.setText("" + point.getYCoordinate());
		edgeColor = point.getEdgeColor();
		isSelected = point.isSelected();

		btnEdgeColor.setBackground(point.getEdgeColor());
	}
}
