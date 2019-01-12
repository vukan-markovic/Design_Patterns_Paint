package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import shapes.Circle;

/**
 * Class represent {@link JDialog} for adding or updating {@link Circle}.
 */
public class DlgCircle extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel mainPanel;
    private JTextField txtXcoordinateOfCenter;
    private JTextField txtYcoordinateOfCenter;
    private JTextField txtRadiusLength;
    private JLabel lblXcoordinateOfCenter;
    private JLabel lblRadiusLength;
    private JLabel lblYcoordinateOfCenter;
    private int xCoordinateOfCenter;
    private int yCoordinateOfCenter;
    private int radiusLength;
    private Color edgeColor;
    private Color interiorColor;
    private boolean confirmed;
    private Color edgeColorOfCircle;
    private Color interiorColorOfCircle;
    private JButton btnEdgeColor;
    private JButton btnInteriorColor;
	private int drawWidth;
	private int drawHeight;

    public static void main(String[] arrayOfStrings) {
        try {
            DlgCircle dialog = new DlgCircle();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public DlgCircle() {
        setModal(true);
        setResizable(false);
        setTitle("Circle values");
        setBounds(100, 100, 449, 342);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        GridBagLayout gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};

        mainPanel.setLayout(gbl_mainPanel);
        {
            lblXcoordinateOfCenter = new JLabel("X coordinate of center");
            lblXcoordinateOfCenter.setFont(new Font("Arial", Font.BOLD, 12));
            GridBagConstraints gbc_lblXcoordinateOfCenter = new GridBagConstraints();
            gbc_lblXcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
            gbc_lblXcoordinateOfCenter.gridx = 3;
            gbc_lblXcoordinateOfCenter.gridy = 2;
            mainPanel.add(lblXcoordinateOfCenter, gbc_lblXcoordinateOfCenter);
        }
        {
            txtXcoordinateOfCenter = new JTextField();
            lblXcoordinateOfCenter.setLabelFor(txtXcoordinateOfCenter);
            GridBagConstraints gbc_txtXcoordinateOfCenter = new GridBagConstraints();
            gbc_txtXcoordinateOfCenter.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtXcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
            gbc_txtXcoordinateOfCenter.gridx = 7;
            gbc_txtXcoordinateOfCenter.gridy = 2;
            txtXcoordinateOfCenter.setColumns(10);
            mainPanel.add(txtXcoordinateOfCenter, gbc_txtXcoordinateOfCenter);
        }
        {
            lblYcoordinateOfCenter = new JLabel("Y coordinate of center");
            lblYcoordinateOfCenter.setFont(new Font("Arial", Font.BOLD, 12));
            GridBagConstraints gbc_lblYcoordinateOfCenter = new GridBagConstraints();
            gbc_lblYcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
            gbc_lblYcoordinateOfCenter.gridx = 3;
            gbc_lblYcoordinateOfCenter.gridy = 4;
            mainPanel.add(lblYcoordinateOfCenter, gbc_lblYcoordinateOfCenter);
        }
        {
            txtYcoordinateOfCenter = new JTextField();
            lblYcoordinateOfCenter.setLabelFor(txtYcoordinateOfCenter);
            GridBagConstraints gbc_txtYcoordinateOfCenter = new GridBagConstraints();
            gbc_txtYcoordinateOfCenter.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtYcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
            gbc_txtYcoordinateOfCenter.gridx = 7;
            gbc_txtYcoordinateOfCenter.gridy = 4;
            txtYcoordinateOfCenter.setColumns(10);
            mainPanel.add(txtYcoordinateOfCenter, gbc_txtYcoordinateOfCenter);
        }
        {
            lblRadiusLength = new JLabel("Radius length");
            lblRadiusLength.setFont(new Font("Arial", Font.BOLD, 12));
            GridBagConstraints gbc_lblRadiusLength = new GridBagConstraints();
            gbc_lblRadiusLength.insets = new Insets(0, 0, 5, 5);
            gbc_lblRadiusLength.gridx = 3;
            gbc_lblRadiusLength.gridy = 6;
            mainPanel.add(lblRadiusLength, gbc_lblRadiusLength);
        }
        {
            txtRadiusLength = new JTextField();
            lblRadiusLength.setLabelFor(txtRadiusLength);
            GridBagConstraints gbc_txtRadiusLength = new GridBagConstraints();
            gbc_txtRadiusLength.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtRadiusLength.insets = new Insets(0, 0, 5, 5);
            gbc_txtRadiusLength.gridx = 7;
            gbc_txtRadiusLength.gridy = 6;
            mainPanel.add(txtRadiusLength, gbc_txtRadiusLength);
            txtRadiusLength.setColumns(10);
        }

        btnInteriorColor = new JButton("Choose interior color");
        btnInteriorColor.setFont(new Font("Arial", Font.BOLD, 12));
        btnInteriorColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnInteriorColor.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent click) {
                interiorColor = JColorChooser.showDialog(null, "Color pallete", interiorColorOfCircle);
                if (interiorColor != null) {
					interiorColorOfCircle = interiorColor;
					if (interiorColorOfCircle.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
					else if (interiorColorOfCircle.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
					btnInteriorColor.setBackground(interiorColorOfCircle);
				}
            }
        });

        btnEdgeColor = new JButton("Choose edge color");
        btnEdgeColor.setForeground(Color.WHITE);
        btnEdgeColor.setFont(new Font("Arial", Font.BOLD, 12));
        btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEdgeColor.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent click) {
                edgeColor = JColorChooser.showDialog(null, "Color pallete", edgeColorOfCircle);
                if (edgeColor != null) {
					if (edgeColor.equals(Color.WHITE)) JOptionPane.showMessageDialog(null, "Background is white :D");
					else {
						edgeColorOfCircle = edgeColor;
						btnEdgeColor.setBackground(edgeColorOfCircle);
					}
				}
            }
        });

        GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
        gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
        gbc_btnEdgeColor.gridx = 3;
        gbc_btnEdgeColor.gridy = 8;
        mainPanel.add(btnEdgeColor, gbc_btnEdgeColor);
        btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GridBagConstraints gbc_btnInteriorColor = new GridBagConstraints();
        gbc_btnInteriorColor.anchor = GridBagConstraints.EAST;
        gbc_btnInteriorColor.insets = new Insets(0, 0, 5, 5);
        gbc_btnInteriorColor.gridx = 7;
        gbc_btnInteriorColor.gridy = 8;
        mainPanel.add(btnInteriorColor, gbc_btnInteriorColor);
        {
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
            {
                JButton btnConfirm = new JButton("Confirm");
                btnConfirm.setFont(new Font("Arial", Font.BOLD, 12));
                btnConfirm.setBackground(Color.GREEN);
                btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnConfirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent click) {
                        if (txtXcoordinateOfCenter.getText().isEmpty() || txtYcoordinateOfCenter.getText().isEmpty() || txtRadiusLength.getText().isEmpty())
                            JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        else {
                            try {
                            	xCoordinateOfCenter = Integer.parseInt(txtXcoordinateOfCenter.getText());
                            	yCoordinateOfCenter = Integer.parseInt(txtYcoordinateOfCenter.getText());
                            	radiusLength = Integer.parseInt(txtRadiusLength.getText());
                                if (xCoordinateOfCenter <= 0 || yCoordinateOfCenter <= 0 || radiusLength <= 0) JOptionPane.showMessageDialog(getParent(), "X and Y coordinates of center and radius length of circle must be positive numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                                else if (radiusLength + xCoordinateOfCenter > drawWidth || radiusLength + yCoordinateOfCenter > drawHeight || yCoordinateOfCenter - radiusLength <= 0 || xCoordinateOfCenter - radiusLength < 0) JOptionPane.showMessageDialog(null, "The circle goes out of drawing!");
                        		else {
                        			confirmed = true;
                        			setVisible(false);
                        			dispose();          
                                }
                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(getParent(), "X and Y coordinates of center and radius length of circle must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
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
                btnCancel.setFont(new Font("Arial", Font.BOLD, 12));
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
	 * <h3>Write appropriate values to fields.</h3>
	 * 
	 * Getting width and height of view to not draw out of borders.
	 * 
	 * @param xClick Represent x coordinate of clicked place.
	 * @param yClick Represent y coordinate of clicked place
	 * @param drawWidth Represent width of draw.
	 * @param drawHeight Represent height of draw.
	 */
    public void write(int xOfClick, int yOfClick, int drawWidth, int drawHeight) {
        txtXcoordinateOfCenter.setText(String.valueOf(xOfClick));
        txtXcoordinateOfCenter.setEnabled(false);
        txtYcoordinateOfCenter.setText(String.valueOf(yOfClick));
        txtYcoordinateOfCenter.setEnabled(false);
        this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
    }

	/**
	 * {@inheritDoc DlgSquare#deleteButtons()}
	 */
    public void deleteButtons() {
        btnEdgeColor.setVisible(false);
        btnInteriorColor.setVisible(false);
    }

	/**
	 * <h3>Fill up fields with parameters of {@link Circle} that user want to update.</h3>
	 * 
	 * @param square Represent {@link Circle} that user want to update.
	 */
    public void fillUp(Circle circle, int drawWidth, int drawHeight) {
        txtXcoordinateOfCenter.setText(String.valueOf(circle.getCenter().getXcoordinate()));
        txtYcoordinateOfCenter.setText(String.valueOf(circle.getCenter().getYcoordinate()));
        txtRadiusLength.setText(String.valueOf(circle.getRadius()));
        edgeColorOfCircle = circle.getColor();
        interiorColorOfCircle = circle.getInteriorColor();
        if (interiorColorOfCircle.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
		else if (interiorColorOfCircle.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
		btnEdgeColor.setBackground(edgeColorOfCircle);
		btnInteriorColor.setBackground(interiorColorOfCircle);
        this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    
    public int getXcoordinateOfCenter() {
        return xCoordinateOfCenter;
    }

    public int getYcoordinateOfCenter() {
        return yCoordinateOfCenter;
    }

    public int getRadiusLength() {
        return radiusLength;
    }

    public Color getEdgeColor() {
        return edgeColorOfCircle;
    }

    public Color getInteriorColor() {
    	return interiorColorOfCircle;
    }
}