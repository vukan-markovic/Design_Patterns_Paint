package dialogs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import shapes.Line;
import java.awt.event.*;

/**
 * Class represent {@link JDialog} for adding or updating {@link Line}.
 */
public class DlgLine extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
	private JTextField txtxCoordinateInitial;
	private JTextField txtyCoordinateInitial;
	private JLabel lblxCoordinateInitial;
	private JLabel lblyCoordinateInitial;
	private boolean confirmed;
	private JButton btnColor;
	private Color color = Color.BLACK;
	private Color lineColor = Color.BLACK;
	private JLabel lblxCoordinateLast;
	private JLabel lblyCoordinateLast;
	private JTextField txtxCoordinateLast;
	private JTextField txtyCoordinateLast;
	private int xCoordinateInitial;
	private int yCoordinateInitial;
	private int xCoordinateLast;
	private int yCoordinateLast;

	public static void main(String[] arrayOfStrings) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgLine() {
		setModal(true);
		setResizable(false);
		setTitle("Line values");
		setBounds(100, 100, 325, 373);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		{
			lblxCoordinateInitial = new JLabel("X coordinate of initial point");
			lblxCoordinateInitial.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblxCoordinateInitial = new GridBagConstraints();
			gbc_lblxCoordinateInitial.insets = new Insets(0, 0, 5, 5);
			gbc_lblxCoordinateInitial.gridx = 1;
			gbc_lblxCoordinateInitial.gridy = 2;
			mainPanel.add(lblxCoordinateInitial, gbc_lblxCoordinateInitial);
		}
		{
			txtxCoordinateInitial = new JTextField();
			lblxCoordinateInitial.setLabelFor(txtxCoordinateInitial);
			txtxCoordinateInitial.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_txtxCoordinateInitial = new GridBagConstraints();
			gbc_txtxCoordinateInitial.gridwidth = 3;
			gbc_txtxCoordinateInitial.insets = new Insets(0, 0, 5, 5);
			gbc_txtxCoordinateInitial.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtxCoordinateInitial.gridx = 3;
			gbc_txtxCoordinateInitial.gridy = 2;
			mainPanel.add(txtxCoordinateInitial, gbc_txtxCoordinateInitial);
			txtxCoordinateInitial.setColumns(10);
		}
		{
			lblyCoordinateInitial = new JLabel("Y coordinate of initial point");
			lblyCoordinateInitial.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblyCoordinateInitial = new GridBagConstraints();
			gbc_lblyCoordinateInitial.insets = new Insets(0, 0, 5, 5);
			gbc_lblyCoordinateInitial.gridx = 1;
			gbc_lblyCoordinateInitial.gridy = 4;
			mainPanel.add(lblyCoordinateInitial, gbc_lblyCoordinateInitial);
		}
		{
			txtyCoordinateInitial = new JTextField();
			lblyCoordinateInitial.setLabelFor(txtyCoordinateInitial);
			txtyCoordinateInitial.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_txtyCoordinateInitial = new GridBagConstraints();
			gbc_txtyCoordinateInitial.gridwidth = 3;
			gbc_txtyCoordinateInitial.insets = new Insets(0, 0, 5, 5);
			gbc_txtyCoordinateInitial.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtyCoordinateInitial.gridx = 3;
			gbc_txtyCoordinateInitial.gridy = 4;
			mainPanel.add(txtyCoordinateInitial, gbc_txtyCoordinateInitial);
			txtyCoordinateInitial.setColumns(10);
		}
		{
			lblxCoordinateLast = new JLabel("X coordinate of last point");
			lblxCoordinateLast.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblxCoordinateLast = new GridBagConstraints();
			gbc_lblxCoordinateLast.insets = new Insets(0, 0, 5, 5);
			gbc_lblxCoordinateLast.gridx = 1;
			gbc_lblxCoordinateLast.gridy = 6;
			mainPanel.add(lblxCoordinateLast, gbc_lblxCoordinateLast);
		}
		{
			txtxCoordinateLast = new JTextField();
			lblxCoordinateLast.setLabelFor(txtxCoordinateLast);
			GridBagConstraints gbc_txtxCoordinateLast = new GridBagConstraints();
			gbc_txtxCoordinateLast.gridwidth = 3;
			gbc_txtxCoordinateLast.insets = new Insets(0, 0, 5, 5);
			gbc_txtxCoordinateLast.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtxCoordinateLast.gridx = 3;
			gbc_txtxCoordinateLast.gridy = 6;
			mainPanel.add(txtxCoordinateLast, gbc_txtxCoordinateLast);
			txtxCoordinateLast.setColumns(10);
		}
		{
			lblyCoordinateLast = new JLabel("Y coordinate of last point");
			lblyCoordinateLast.setFont(new Font("Arial", Font.BOLD, 12));
			GridBagConstraints gbc_lblyCoordinateLast = new GridBagConstraints();
			gbc_lblyCoordinateLast.insets = new Insets(0, 0, 5, 5);
			gbc_lblyCoordinateLast.gridx = 1;
			gbc_lblyCoordinateLast.gridy = 8;
			mainPanel.add(lblyCoordinateLast, gbc_lblyCoordinateLast);
		}
		{
			txtyCoordinateLast = new JTextField();
			lblyCoordinateLast.setLabelFor(txtyCoordinateLast);
			GridBagConstraints gbc_txtyCoordinateLast = new GridBagConstraints();
			gbc_txtyCoordinateLast.gridwidth = 3;
			gbc_txtyCoordinateLast.insets = new Insets(0, 0, 5, 5);
			gbc_txtyCoordinateLast.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtyCoordinateLast.gridx = 3;
			gbc_txtyCoordinateLast.gridy = 8;
			mainPanel.add(txtyCoordinateLast, gbc_txtyCoordinateLast);
			txtyCoordinateLast.setColumns(10);
		}
		
		btnColor = new JButton("Choose color");
		btnColor.setFont(new Font("Arial", Font.BOLD, 12));
		btnColor.setForeground(Color.WHITE);
		btnColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = JColorChooser.showDialog(null, "Colors pallete", lineColor);
				if (color != null) {
					if (color.equals(Color.WHITE)) JOptionPane.showMessageDialog(null, "Background is white :D");
					else {
						lineColor = color;
						btnColor.setBackground(lineColor);
					}
				}
			}
		});
	
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor.gridx = 1;
		gbc_btnColor.gridy = 10;
		mainPanel.add(btnColor, gbc_btnColor);
		{
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.setBackground(Color.GREEN);
				btnConfirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent click) {
						if (txtxCoordinateInitial.getText().isEmpty() || txtyCoordinateInitial.getText().isEmpty()  || txtxCoordinateLast.getText().isEmpty() || txtyCoordinateLast.getText().isEmpty()) JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							try {	
								xCoordinateInitial = Integer.parseInt(txtxCoordinateInitial.getText());
								yCoordinateInitial = Integer.parseInt(txtyCoordinateInitial.getText());
								xCoordinateLast = Integer.parseInt(txtxCoordinateLast.getText());
								yCoordinateLast = Integer.parseInt(txtyCoordinateLast.getText());
								if (xCoordinateInitial <= 0 || yCoordinateInitial <= 0 || xCoordinateLast <= 0 || yCoordinateLast <= 0) JOptionPane.showMessageDialog(null, "X and Y coordinates of initial and last point of line must be positive numbers!");
								else {
									confirmed = true;
									setVisible(false);
									dispose();
								}
							} catch (NumberFormatException nfe) {
								JOptionPane.showMessageDialog(getParent(),"X and Y coordinates of initial and last point of line must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
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
	public void write(Line line) {
		txtxCoordinateInitial.setText(String.valueOf(line.getInitial().getXcoordinate()));
		txtyCoordinateInitial.setText(String.valueOf(line.getInitial().getYcoordinate()));
		txtxCoordinateLast.setText(String.valueOf(line.getLast().getXcoordinate()));
		txtyCoordinateLast.setText(String.valueOf(line.getLast().getYcoordinate()));
		lineColor = line.getColor();
		btnColor.setBackground(lineColor);
	}

	public boolean isConfirmed() {
		return confirmed;
	}
	
	public Color getColor() {
		return lineColor;
	}

	public int getXcoordinateInitial() {
		return xCoordinateInitial;
	}
	public int getYcoordinateInitial() {
		return yCoordinateInitial;
	}

	public int getXcoordinateLast() {
		return xCoordinateLast;
	}

	public int getYcoordinateLast() {
		return yCoordinateLast;
	}
}