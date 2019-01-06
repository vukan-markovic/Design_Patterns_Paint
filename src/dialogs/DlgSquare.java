package dialogs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import shapes.Square;

import java.awt.event.*;

/**
 * 
 * @author Vukan Markovic
 */
public class DlgSquare extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
	private JTextField txtXcoordinate;
	private JTextField txtYcoordinate;
	private JTextField txtSideLength;
	private JLabel lblXcoordinate;
	private JLabel lblYcoordinate;
	private JLabel lblSideLength;
	private int xCoordinate;
	private int yCoordinate;
	private int sideLength;
	private Color edgeColorOfSquare = Color.BLACK;
	private Color interiorColorOfSquare = Color.WHITE;
	private Color edgeColor = Color.BLACK;
	private Color interiorColor = Color.WHITE;
	private boolean confirmed;
	private JButton btnEdgeColor;
	private JButton btnInteriorColor;

	public static void main(String [] arrayOfStrings) {
		try {
			DlgSquare dialog = new DlgSquare();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgSquare() {
		setModal(true);
		setResizable(false);
		setTitle("Square values");
		setBounds(100, 100, 440, 320);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		mainPanel.setLayout(gbl_mainPanel);
		{
			lblXcoordinate = new JLabel("X coordinate");
			GridBagConstraints gbc_lblXcoordinate = new GridBagConstraints();
			gbc_lblXcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXcoordinate.gridx = 2;
			gbc_lblXcoordinate.gridy = 3;
			mainPanel.add(lblXcoordinate, gbc_lblXcoordinate);
		}
		{
			txtXcoordinate = new JTextField();
			lblXcoordinate.setLabelFor(txtXcoordinate);
			GridBagConstraints gbc_txtXcoordinate = new GridBagConstraints();
			gbc_txtXcoordinate.anchor = GridBagConstraints.NORTH;
			gbc_txtXcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtXcoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXcoordinate.gridx = 7;
			gbc_txtXcoordinate.gridy = 3;
			mainPanel.add(txtXcoordinate, gbc_txtXcoordinate);
			txtXcoordinate.setColumns(10);
		}
		{
			lblYcoordinate = new JLabel("Y coordinate");
			GridBagConstraints gbc_lblYcoordinate = new GridBagConstraints();
			gbc_lblYcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYcoordinate.gridx = 2;
			gbc_lblYcoordinate.gridy = 5;
			mainPanel.add(lblYcoordinate, gbc_lblYcoordinate);
		}
		{
			txtYcoordinate = new JTextField();
			lblYcoordinate.setLabelFor(txtYcoordinate);
			GridBagConstraints gbc_txtYcoordinate = new GridBagConstraints();
			gbc_txtYcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtYcoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYcoordinate.gridx = 7;
			gbc_txtYcoordinate.gridy = 5;
			mainPanel.add(txtYcoordinate, gbc_txtYcoordinate);
			txtYcoordinate.setColumns(10);
		}
		{
			lblSideLength = new JLabel("Side length");
			GridBagConstraints gbc_lblSideLength = new GridBagConstraints();
			gbc_lblSideLength.insets = new Insets(0, 0, 5, 5);
			gbc_lblSideLength.gridx = 2;
			gbc_lblSideLength.gridy = 7;
			mainPanel.add(lblSideLength, gbc_lblSideLength);
		}
		{
			txtSideLength = new JTextField();
			lblSideLength.setLabelFor(txtSideLength);
			GridBagConstraints gbc_txtSideLength = new GridBagConstraints();
			gbc_txtSideLength.insets = new Insets(0, 0, 5, 5);
			gbc_txtSideLength.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSideLength.gridx = 7;
			gbc_txtSideLength.gridy = 7;
			mainPanel.add(txtSideLength, gbc_txtSideLength);
			txtSideLength.setColumns(10);
		}
		
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdgeColor.gridx = 2;
		gbc_btnEdgeColor.gridy = 9;

		btnEdgeColor = new JButton("Choose edge color");
		btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				edgeColor = JColorChooser.showDialog(null, "Colors pallete", edgeColorOfSquare);
			}
		});

		mainPanel.add(btnEdgeColor, gbc_btnEdgeColor);

		GridBagConstraints gbc_btnInteriorColor = new GridBagConstraints();
		gbc_btnInteriorColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInteriorColor.gridx = 7;
		gbc_btnInteriorColor.gridy = 9;

		btnInteriorColor = new JButton("Choose interior color");
		btnInteriorColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInteriorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				interiorColor = JColorChooser.showDialog(null, "Colors pallete", interiorColorOfSquare);
			}
		});

		mainPanel.add(btnInteriorColor, gbc_btnInteriorColor);
		{
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.setBackground(Color.GREEN);
				btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnConfirm.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent click) {
						if (txtXcoordinate.getText().isEmpty() || txtYcoordinate.getText().isEmpty() || txtSideLength.getText().isEmpty())
							JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							try {	
								if(Integer.parseInt(txtSideLength.getText()) <= 0) {
									JOptionPane.showMessageDialog(getParent(), "Length of side must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
									txtSideLength.setText("");
								} else {
									confirmed = true;
									xCoordinate = Integer.parseInt(txtXcoordinate.getText());
									yCoordinate = Integer.parseInt(txtYcoordinate.getText());
									sideLength = Integer.parseInt(txtSideLength.getText());
									setVisible(false);
									dispose();
								}
							} catch (NumberFormatException exception) {
								JOptionPane.showMessageDialog(getParent(),"Coordinate of point and side length must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
							} 
						}  
					}
				});
				btnConfirm.setActionCommand("OK");
				buttonsPanel.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setBackground(Color.RED);
				btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnCancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent click) {
						setVisible(false);
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonsPanel.add(btnCancel);
			}
		}
	}

	/**
	 * 
	 * @param xClick
	 * @param yClick
	 */
	public void write(int xClick, int yClick) {
		txtXcoordinate.setText(String.valueOf(xClick));
		txtXcoordinate.setEnabled(false);
		txtYcoordinate.setText(String.valueOf(yClick));
		txtYcoordinate.setEnabled(false);
	}

	/**
	 * 
	 * @param square
	 */
	public void fillUp(Square square) {
		txtXcoordinate.setText(String.valueOf(square.getUpLeft().getXcoordinate()));
		txtYcoordinate.setText(String.valueOf(square.getUpLeft().getYcoordinate()));
		txtSideLength.setText(String.valueOf(square.getSide()));
		edgeColorOfSquare = square.getColor();
		interiorColorOfSquare = square.getInteriorColor();
	}

	/**
	 * 
	 */
	public void deleteButtons() {
		btnEdgeColor.setVisible(false);
		btnInteriorColor.setVisible(false);
	}

	/**
	 * 
	 * @return
	 */
	public int getXcoordinate() {
		return xCoordinate;
	}

	/**
	 * 
	 * @return
	 */
	public int getYcoordinate() {
		return yCoordinate;
	}

	/**
	 * 
	 * @return
	 */
	public int getSideLength() {
		return sideLength;
	}

	/**
	 * 
	 * @return
	 */
	public Color getEdgeColor() {
		if (edgeColor == null) return edgeColorOfSquare;
		else return edgeColor;
	}

	/**
	 * 
	 * @return
	 */
	public Color getInteriorColor() {
		if (interiorColor == null) return interiorColorOfSquare;
		else return interiorColor;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isConfirmed() {
		return confirmed;
	}
}