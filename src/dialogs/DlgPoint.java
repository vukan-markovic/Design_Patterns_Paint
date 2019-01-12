package dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import shapes.Point;

/**
 * Class represent {@link JDialog} for adding or updating {@link Point}.
 */
public class DlgPoint extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
	private JTextField txtXcoordinate;
	private JTextField txtYcoordinate;
	private JLabel lblXcoordinate;
	private JLabel lblYcoordinate;
	private int xCoordinate;
	private int yCoordinate;
	private boolean confirmed;
	private JButton btnColor;
	private Color color;
	private Color pointColor;
	private int drawWidth;
	private int drawHeight;
	
	public static void main(String [] arrayOfStrings) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgPoint() {
		setModal(true);
		setResizable(false);
		setTitle("Point values");
		setBounds(100, 100, 251, 245);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		{
			lblXcoordinate = new JLabel("X coordinate");
			GridBagConstraints gbc_lblXcoordinate = new GridBagConstraints();
			gbc_lblXcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXcoordinate.gridx = 2;
			gbc_lblXcoordinate.gridy = 1;
			mainPanel.add(lblXcoordinate, gbc_lblXcoordinate);
		}
		lblXcoordinate.setLabelFor(txtXcoordinate);
		{
			txtXcoordinate = new JTextField();
			GridBagConstraints gbc_txtXcoordinate = new GridBagConstraints();
			gbc_txtXcoordinate.gridwidth = 4;
			gbc_txtXcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtXcoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXcoordinate.gridx = 4;
			gbc_txtXcoordinate.gridy = 1;
			mainPanel.add(txtXcoordinate, gbc_txtXcoordinate);
			txtXcoordinate.setColumns(10);
		}
		{
			lblYcoordinate = new JLabel("Y coordinate");
			GridBagConstraints gbc_lblYcoordinate = new GridBagConstraints();
			gbc_lblYcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYcoordinate.gridx = 2;
			gbc_lblYcoordinate.gridy = 3;
			mainPanel.add(lblYcoordinate, gbc_lblYcoordinate);
		}
		lblYcoordinate.setLabelFor(txtYcoordinate);
		{
			txtYcoordinate = new JTextField();
			GridBagConstraints gbc_txtYcoordinate = new GridBagConstraints();
			gbc_txtYcoordinate.gridwidth = 4;
			gbc_txtYcoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtYcoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYcoordinate.gridx = 4;
			gbc_txtYcoordinate.gridy = 3;
			mainPanel.add(txtYcoordinate, gbc_txtYcoordinate);
			txtYcoordinate.setColumns(10);
		}
		
		btnColor = new JButton("Choose color");
		btnColor.setForeground(Color.WHITE);
		btnColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = JColorChooser.showDialog(null, "Colors pallete", pointColor);
				if (color != null) {
					if (color.equals(Color.WHITE)) JOptionPane.showMessageDialog(null, "Background is white :D");
					else {
						pointColor = color;
						btnColor.setBackground(pointColor);
					}
				}
			}
		});
		
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.anchor = GridBagConstraints.NORTH;
		gbc_btnColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor.gridx = 2;
		gbc_btnColor.gridy = 5;
		mainPanel.add(btnColor, gbc_btnColor);
		{
			JPanel butttonsPanel = new JPanel();
			butttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(butttonsPanel, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.setBackground(Color.GREEN);
				btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnConfirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent click) {
						if (txtXcoordinate.getText().isEmpty() || txtYcoordinate.getText().isEmpty()) JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							try {	
								xCoordinate = Integer.parseInt(txtXcoordinate.getText());
								yCoordinate = Integer.parseInt(txtYcoordinate.getText());
								if (xCoordinate <= 0 || yCoordinate <= 0) JOptionPane.showMessageDialog(null, "X and Y coordinates of point must be positive numbers!");
								else if (xCoordinate > drawWidth || yCoordinate > drawHeight) JOptionPane.showMessageDialog(null, "The point goes out of drawing!");
								else {
									confirmed = true;
									setVisible(false);
									dispose();
								}
							} catch (NumberFormatException nfe) {
								JOptionPane.showMessageDialog(getParent(),"X and Y coordinates of point must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
							} 
						}  
					}
				});
				
				btnConfirm.setActionCommand("OK");
				butttonsPanel.add(btnConfirm);
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
				butttonsPanel.add(btnCancel);
			}
		}
	}

	/** 
     * {@inheritDoc DlgCircle#write(int, int, int, int)}
	 */
	public void write(Point point, int drawWidth, int drawHeight) {
		txtXcoordinate.setText(String.valueOf(point.getXcoordinate()));
		txtYcoordinate.setText(String.valueOf(point.getYcoordinate()));
		pointColor = point.getColor();
		btnColor.setBackground(pointColor);
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
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

	public Color getColor() {
		return pointColor;
	}
}