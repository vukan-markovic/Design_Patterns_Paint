package dialogs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import shapes.Square;
import java.awt.event.*;

/**
 * Class represent {@link JDialog} for adding or updating {@link Square}.
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
	private Color edgeColorOfSquare;
	private Color interiorColorOfSquare;
	private Color edgeColor;
	private Color interiorColor;
	private boolean confirmed;
	private JButton btnEdgeColor;
	private JButton btnInteriorColor;
	private int drawWidth;
	private int drawHeight;

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
		btnEdgeColor.setForeground(Color.WHITE);
		btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdgeColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				edgeColor = JColorChooser.showDialog(null, "Colors pallete", edgeColorOfSquare);
				if (edgeColor != null) {
					if (edgeColor.equals(Color.WHITE)) JOptionPane.showMessageDialog(null, "Background is white :D");
					else {
						edgeColorOfSquare = edgeColor;
						btnEdgeColor.setBackground(edgeColorOfSquare);
					}
				}
			}
		});

		mainPanel.add(btnEdgeColor, gbc_btnEdgeColor);

		GridBagConstraints gbc_btnInteriorColor = new GridBagConstraints();
		gbc_btnInteriorColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInteriorColor.gridx = 7;
		gbc_btnInteriorColor.gridy = 9;

		btnInteriorColor = new JButton("Choose interior color");
		btnInteriorColor.setForeground(Color.BLACK);
		btnInteriorColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInteriorColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				interiorColor = JColorChooser.showDialog(null, "Colors pallete", interiorColorOfSquare);
				if (interiorColor != null) {
					interiorColorOfSquare = interiorColor;
					if (interiorColorOfSquare.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
					else if (interiorColorOfSquare.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
					btnInteriorColor.setBackground(interiorColorOfSquare);
				}
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
				btnConfirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent click) {
						if (txtXcoordinate.getText().isEmpty() || txtYcoordinate.getText().isEmpty() || txtSideLength.getText().isEmpty()) JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							try {	
								xCoordinate = Integer.parseInt(txtXcoordinate.getText());
								yCoordinate = Integer.parseInt(txtYcoordinate.getText());
								sideLength = Integer.parseInt(txtSideLength.getText());
								if(xCoordinate <= 0 || yCoordinate <= 0 || sideLength <= 0) JOptionPane.showMessageDialog(getParent(), "X and Y coordinates of up left point and side length of square must be positive numbers!", "Error", JOptionPane.ERROR_MESSAGE);
								else if (sideLength + xCoordinate > drawWidth || sideLength + yCoordinate > drawHeight) JOptionPane.showMessageDialog(null, "The square goes out of drawing!");
								else {
									confirmed = true;
									setVisible(false);
									dispose();
								}
							} catch (NumberFormatException exception) {
								JOptionPane.showMessageDialog(getParent(),"X and Y coordinates of up left point and side length of square must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
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
     * {@inheritDoc DlgCircle#write(int, int, int, int)}
	 */
	public void write(int xClick, int yClick, int drawWidth, int drawHeight) {
		txtXcoordinate.setText(String.valueOf(xClick));
		txtXcoordinate.setEnabled(false);
		txtYcoordinate.setText(String.valueOf(yClick));
		txtYcoordinate.setEnabled(false);
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
	}

	/**
	 * <h3>Fill up fields with parameters of {@link Square} that user want to update.</h3>
	 * 
	 * @param square Represent {@link Square} that user want to update.
	 */
	public void fillUp(Square square, int drawWidth, int drawHeight) {
		txtXcoordinate.setText(String.valueOf(square.getUpLeft().getXcoordinate()));
		txtYcoordinate.setText(String.valueOf(square.getUpLeft().getYcoordinate()));
		txtSideLength.setText(String.valueOf(square.getSide()));
		edgeColorOfSquare = square.getColor();
		interiorColorOfSquare = square.getInteriorColor();
		if (interiorColorOfSquare.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
		else if (interiorColorOfSquare.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
		btnEdgeColor.setBackground(edgeColorOfSquare);
		btnInteriorColor.setBackground(interiorColorOfSquare);
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
	}

	/** 
	 * <h3>Hide buttons for colors when new operation is adding.</h3>
	 */
	public void deleteButtons() {
		btnEdgeColor.setVisible(false);
		btnInteriorColor.setVisible(false);
	}

	public boolean isConfirmed() {
		return confirmed;
	}
	
	public int getXcoordinate() {
		return xCoordinate;
	}

	public int getYcoordinate() {
		return yCoordinate;
	}

	public int getSideLength() {
		return sideLength;
	}

	public Color getEdgeColor() {
		return edgeColorOfSquare;
	}

	public Color getInteriorColor() {
		return interiorColorOfSquare;
	}
}