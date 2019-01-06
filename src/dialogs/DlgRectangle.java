package dialogs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import shapes.Rectangle;

/**
 * 
 * @author Vukan Markovic
 */
public class DlgRectangle extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
	private JTextField txtXcoordinate;
	private JTextField txtYcoordinate;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JLabel lblXcoordinate;
	private JLabel lblYcoordinate;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private int xCoordinate;
	private int yCoordinate;
	private int width;
	private int height;
	private Color edgeColor = Color.BLACK;
	private Color interiorColor = Color.WHITE;
	private Color edgeColorOfRectangle = Color.BLACK;
	private Color interiorColorOfRectangle = Color.WHITE;
	private boolean confirmed;
	private JButton btnEdgeColor;
	private JButton btnInteriorColor;

	/**
	 * 
	 * @param arrayOfStrings
	 */
	public static void main(String [] arrayOfStrings) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public DlgRectangle() {
		setModal(true);
		setResizable(false);
		setTitle("Rectangle values");
		setBounds(100, 100, 418, 386);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		{
			lblXcoordinate = new JLabel("X coordinate");
			GridBagConstraints gbc_lblXcoordinate = new GridBagConstraints();
			gbc_lblXcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXcoordinate.gridx = 3;
			gbc_lblXcoordinate.gridy = 2;
			mainPanel.add(lblXcoordinate, gbc_lblXcoordinate);
		}
		{
			txtXcoordinate = new JTextField();
			lblXcoordinate.setLabelFor(txtXcoordinate);
			GridBagConstraints gbc_txtXcoordinate = new GridBagConstraints();
			gbc_txtXcoordinate.anchor = GridBagConstraints.NORTH;
			gbc_txtXcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtXcoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXcoordinate.gridx = 6;
			gbc_txtXcoordinate.gridy = 2;
			mainPanel.add(txtXcoordinate, gbc_txtXcoordinate);
			txtXcoordinate.setColumns(10);
		}
		{
			lblYcoordinate = new JLabel("Y coordinate");
			GridBagConstraints gbc_lblYcoordinate = new GridBagConstraints();
			gbc_lblYcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYcoordinate.gridx = 3;
			gbc_lblYcoordinate.gridy = 4;
			mainPanel.add(lblYcoordinate, gbc_lblYcoordinate);
		}
		{
			txtYcoordinate = new JTextField();
			lblYcoordinate.setLabelFor(txtYcoordinate);
			GridBagConstraints gbc_txtYcoordinate = new GridBagConstraints();
			gbc_txtYcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtYcoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYcoordinate.gridx = 6;
			gbc_txtYcoordinate.gridy = 4;
			mainPanel.add(txtYcoordinate, gbc_txtYcoordinate);
			txtYcoordinate.setColumns(10);
		}
		{
			lblWidth = new JLabel("width");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 3;
			gbc_lblWidth.gridy = 6;
			mainPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			lblWidth.setLabelFor(txtWidth);
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 6;
			gbc_txtWidth.gridy = 6;
			mainPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			lblHeight = new JLabel("height");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 3;
			gbc_lblHeight.gridy = 8;
			mainPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			lblHeight.setLabelFor(txtHeight);
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 6;
			gbc_txtHeight.gridy = 8;
			mainPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			btnInteriorColor = new JButton("Choose interior color");
			btnInteriorColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			btnInteriorColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent click) {
					interiorColor = JColorChooser.showDialog(null, "Colors pallete", interiorColorOfRectangle);
				}
			});
			
			btnEdgeColor = new JButton("Choose edge color");
			btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent click) {
					edgeColor = JColorChooser.showDialog(null, "Colors pallete", edgeColorOfRectangle);
				}
			});
			
			GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
			gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnEdgeColor.gridx = 3;
			gbc_btnEdgeColor.gridy = 10;
			mainPanel.add(btnEdgeColor, gbc_btnEdgeColor);
			GridBagConstraints gbc_btnInteriorColor = new GridBagConstraints();
			gbc_btnInteriorColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnInteriorColor.gridx = 6;
			gbc_btnInteriorColor.gridy = 10;
			mainPanel.add(btnInteriorColor, gbc_btnInteriorColor);
		}
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
						if (txtXcoordinate.getText().isEmpty() || txtYcoordinate.getText().isEmpty() || txtWidth.getText().isEmpty() || txtHeight.getText().isEmpty())
							JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							try {	
								if(Integer.parseInt(txtWidth.getText()) <= 0) {
									JOptionPane.showMessageDialog(getParent(), "Length of width must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
									txtWidth.setText("");
								}
								else if(Integer.parseInt(txtHeight.getText()) <= 0) {
									JOptionPane.showMessageDialog(getParent(), "Lenght of height must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
									txtHeight.setText("");
								} else {
									confirmed = true;
									xCoordinate = Integer.parseInt(txtXcoordinate.getText());
									yCoordinate = Integer.parseInt(txtYcoordinate.getText());
									width = Integer.parseInt(txtWidth.getText());
									height = Integer.parseInt(txtHeight.getText());
									setVisible(false);
									dispose();
								}
							} catch (NumberFormatException nfe) {
								JOptionPane.showMessageDialog(getParent(),"Coordinates of point, width and height must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
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
	 * @param rectangle
	 */
	public void fillUp(Rectangle rectangle) {
		txtXcoordinate.setText(String.valueOf((rectangle.getUpLeft().getXcoordinate())));
		txtYcoordinate.setText(String.valueOf((rectangle.getUpLeft().getYcoordinate())));
		txtWidth.setText(String.valueOf(rectangle.getWidth()));
		txtHeight.setText(String.valueOf(rectangle.getSide()));
		edgeColorOfRectangle = rectangle.getColor();
		interiorColorOfRectangle = rectangle.getInteriorColor();
	}

	/**
	 * 
	 */
	public void deleteButtons() {
		btnEdgeColor.setVisible(false);
		btnInteriorColor.setVisible(false);
	}

	public int getXcoordinate() {
		return xCoordinate;
	}

	public int getYcoordinate() {
		return yCoordinate;
	}

	public int getRectangleWidth() {
		return width;
	}

	public int getRectangleHeight() {
		return height;
	}

	public Color getEdgeColor() {
		if (edgeColor == null) return edgeColorOfRectangle;
		else return edgeColor;
	}

	public Color getInteriorColor() {
		if(interiorColor == null) return interiorColorOfRectangle;
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