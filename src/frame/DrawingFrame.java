package frame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.DrawingController;
import observer.DrawingObserver;
import view.DrawingView;
import java.awt.event.*;

/**
 * @author Vukan Markovic
 * @version 1.0
 * @since 11.01.2019.
 */
public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private final ButtonGroup buttonsGroup;
	private DrawingView view;
	private DrawingController controller;
	private Color color = Color.BLACK;
	private JToggleButton tglBtnSelect;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnNewDraw;
	private JButton btnSaveDraw;
	private JButton btnLog;
	private MouseAdapter mouseAdapterUpdate;
	private MouseAdapter mouseAdapterDelete;
	private MouseAdapter mouseAdapterUndo;
	private MouseAdapter mouseAdapterRedo;
	private MouseAdapter mouseAdapterNewDraw;
	private MouseAdapter mouseAdapterSaveDrawing;
	private MouseAdapter mouseAdapterLog;
	private MouseAdapter mouseAdapterToFront;
	private MouseAdapter mouseAdapterToBack;
	private MouseAdapter mouseAdapterBringToFront;
	private MouseAdapter mouseAdapterBringToBack;
	private JList<String> activityLog;
	private DefaultListModel <String> dlmList;
	private JScrollPane scrollPane;

	public DrawingFrame() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(DrawingFrame.class.getResource("/icons/cursor.png"));
		Image image1 = toolkit.getImage(DrawingFrame.class.getResource("/icons/hand.png"));
		setCursor(toolkit.createCustomCursor(image , new java.awt.Point(this.getX(), this.getY()), "img"));
		setForeground(Color.BLUE);
		setBackground(Color.CYAN);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DrawingFrame.class.getResource("/icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLocationRelativeTo(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		dlmList = new DefaultListModel<String>();
		scrollPane = new JScrollPane();

		JPanel buttonsPanelForDrawing = new JPanel();
		buttonsPanelForDrawing.setBackground(Color.GRAY);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.GRAY);

		view = new DrawingView();
		view.setBackground(Color.WHITE);
		mainPanel.add(buttonsPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanelForDrawing, BorderLayout.SOUTH);
		mainPanel.add(view, BorderLayout.CENTER);
		buttonsGroup = new ButtonGroup();

		JToggleButton tglBtnDrawPoint = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/point.png")));
		tglBtnDrawPoint.setFont(new Font("Dotum", Font.BOLD, 12));
		tglBtnDrawPoint.setText("Point");
		tglBtnDrawPoint.setSelected(true);
		buttonsGroup.add(tglBtnDrawPoint);
		tglBtnDrawPoint.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawPoint.getX(), tglBtnDrawPoint.getY()), "img"));
		buttonsPanelForDrawing.add(tglBtnDrawPoint);

		JToggleButton tglBtnDrawSquare = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/square.png")));
		tglBtnDrawSquare.setFont(new Font("Dotum", Font.BOLD, 12));
		tglBtnDrawSquare.setText("Square");
		buttonsGroup.add(tglBtnDrawSquare);
		tglBtnDrawSquare.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(tglBtnDrawSquare);

		JToggleButton tglBtnDrawRectangle = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/rectangle.png")));
		tglBtnDrawRectangle.setFont(new Font("Dotum", Font.BOLD, 12));
		tglBtnDrawRectangle.setText("Rectangle");
		buttonsGroup.add(tglBtnDrawRectangle);
		tglBtnDrawRectangle.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(tglBtnDrawRectangle);

		JToggleButton tglBtnDrawLine = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/line.png")));
		tglBtnDrawLine.setFont(new Font("Dotum", Font.BOLD, 12));
		tglBtnDrawLine.setText("Line");
		buttonsGroup.add(tglBtnDrawLine);
		tglBtnDrawLine.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(tglBtnDrawLine);

		JToggleButton tglBtnDrawCircle = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/circle.png")));
		tglBtnDrawCircle.setFont(new Font("Dotum", Font.BOLD, 12));
		tglBtnDrawCircle.setText("Circle");
		buttonsGroup.add(tglBtnDrawCircle);
		tglBtnDrawCircle.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(tglBtnDrawCircle);
		
		JToggleButton tglBtnDrawHexagon = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/hexagon.png")));
		tglBtnDrawHexagon.setFont(new Font("Dotum", Font.BOLD, 12));
		tglBtnDrawHexagon.setText("Hexagon");
		buttonsGroup.add(tglBtnDrawHexagon);
		tglBtnDrawHexagon.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(tglBtnDrawHexagon);
		
		btnSaveDraw = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/save.png")));
		btnSaveDraw.setEnabled(false);
		btnSaveDraw.setText("Save");
		btnSaveDraw.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(btnSaveDraw);
		
		btnNewDraw = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/new.png")));
		btnNewDraw.setText("New draw");
		btnNewDraw.setEnabled(false);
		btnNewDraw.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		btnNewDraw.setEnabled(false);
		buttonsPanelForDrawing.add(btnNewDraw);
		
		JButton btnOpenDraw = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/open.png")));
		btnOpenDraw.setText("Open");
		btnOpenDraw.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(btnOpenDraw);
		
		btnLog = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/log.png")));
		btnLog.setEnabled(false);
		btnLog.setText("Log");
		btnLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
		btnLog.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanelForDrawing.add(btnLog);

		tglBtnSelect = new JToggleButton(new ImageIcon(DrawingFrame.class.getResource("/icons/select.png")));
		tglBtnSelect.setText("Select");
		tglBtnSelect.setEnabled(false);
		buttonsGroup.add(tglBtnSelect);
		tglBtnSelect.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(tglBtnSelect);

		btnUpdate = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/update.png")));
		btnUpdate.setText("Update");
		btnUpdate.setEnabled(false);
		buttonsGroup.add(btnUpdate);
		btnUpdate.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnUpdate);

		btnDelete = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/delete.png")));
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		buttonsGroup.add(btnDelete);
		btnDelete.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnDelete);

		JButton btnEdgeColor = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/picker.png")));
		btnEdgeColor.setForeground(Color.WHITE);
		btnEdgeColor.setText("Edge color");
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnEdgeColor);

		JButton btnInteriorColor = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/color-picker.png")));
		btnInteriorColor.setText("Area color");
		btnInteriorColor.setForeground(Color.BLACK);
		btnInteriorColor.setBackground(Color.WHITE);
		btnInteriorColor.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnInteriorColor);
		
		btnUndo = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/undo.png")));
		btnUndo.setEnabled(false);
		btnUndo.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnUndo);
		
		btnRedo = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/redo.png")));
		btnRedo.setEnabled(false);
		btnRedo.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnRedo);
		
		btnToFront = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/to-front.png")));
		btnToFront.setText("To front");
		btnToFront.setEnabled(false);
		btnToFront.setFont(new Font("Lucida Console", Font.BOLD, 11));
		btnToFront.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnToFront);
		
		btnToBack = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/to-back.png")));
		btnToBack.setText("To back");
		btnToBack.setFont(new Font("Lucida Console", Font.BOLD, 11));
		btnToBack.setEnabled(false);
		btnToBack.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnToBack);
		
		btnBringToFront = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/bring-to-front.png")));
		btnBringToFront.setText("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.setFont(new Font("Lucida Console", Font.BOLD, 11));
		btnBringToFront.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnBringToFront);
		
		btnBringToBack = new JButton(new ImageIcon(DrawingFrame.class.getResource("/icons/bring-to-back.png")));
		btnBringToBack.setText("Bring to back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.setFont(new Font("Lucida Console", Font.BOLD, 11));
		btnBringToBack.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
		buttonsPanel.add(btnBringToBack);
		
		activityLog = new JList<String>();
		activityLog.setEnabled(false);
		activityLog.setModel(dlmList);
		activityLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
		scrollPane.setViewportView(activityLog);

		mouseAdapterUpdate = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.updateShapeClicked();
			}
		};
		
		mouseAdapterDelete = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.btnDeleteShapeClicked();
			}
		};
		
		mouseAdapterUndo = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.undo();
			}
		};
		
		mouseAdapterRedo = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.redo();
			}
		};
		
		mouseAdapterNewDraw = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.newDraw();
			}
		};
		
		mouseAdapterBringToBack = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.bringToBack();
			}
		};
		
		mouseAdapterBringToFront = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.bringToFront();
			}
		};
		
		mouseAdapterToBack = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.toBack();
			}
		};
		
		mouseAdapterToFront = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.toFront();
			}
		};
		
		mouseAdapterSaveDrawing = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.save();
			}
		};
		
		mouseAdapterLog = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (btnLog.getText().equals("Log")) {
					mainPanel.remove(view);
					mainPanel.add(scrollPane, BorderLayout.CENTER);
					btnLog.setText("Draw");
					btnLog.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/draw.png")));
					
				} else if (btnLog.getText().equals("Draw")) {
					mainPanel.remove(scrollPane);
					mainPanel.add(view, BorderLayout.CENTER);
					btnLog.setText("Log");
					btnLog.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/log.png")));
				}
				
				repaint();
			}
		};
		
		btnEdgeColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = controller.btnEdgeColorClicked();
				if (color != null) btnEdgeColor.setBackground(color);
			}
		});

		btnInteriorColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = controller.btnInteriorColorClicked();
				if (color != null) {
					if (color.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
					else if (color.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
					btnInteriorColor.setBackground(color);
				}
			}
		});
		
		btnOpenDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.open();
			}
		});
	
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (tglBtnDrawPoint.isSelected()) controller.btnAddPointClicked(click);
				else if (tglBtnDrawLine.isSelected()) controller.btnAddLineClicked(click);
				else if (tglBtnDrawSquare.isSelected()) controller.btnAddSquareClicked(click);
				else if (tglBtnDrawRectangle.isSelected()) controller.btnAddRectangleClicked(click);
				else if (tglBtnDrawCircle.isSelected()) controller.btnAddCircleClicked(click);
				else if (tglBtnDrawHexagon.isSelected()) controller.btnAddHexagonClicked(click);
				else if (tglBtnSelect.isSelected()) controller.btnSelectShapeClicked(click);
			}
		});
	}
	
	public DefaultListModel<String> getList() {
		return dlmList;
	}
	
	public DrawingView getView() {
		return view;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;
		controller.addPropertyChangedListener(new DrawingObserver(this));
	}

	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnNewDraw() {
		return btnNewDraw;
	}

	public JButton getBtnSaveDraw() {
		return btnSaveDraw;
	}

	public JButton getBtnLog() {
		return btnLog;
	}

	public MouseAdapter getMouseAdapterUpdate() {
		return mouseAdapterUpdate;
	}

	public MouseAdapter getMouseAdapterDelete() {
		return mouseAdapterDelete;
	}

	public MouseAdapter getMouseAdapterUndo() {
		return mouseAdapterUndo;
	}

	public MouseAdapter getMouseAdapterRedo() {
		return mouseAdapterRedo;
	}

	public MouseAdapter getMouseAdapterNewDraw() {
		return mouseAdapterNewDraw;
	}

	public MouseAdapter getMouseAdapterSaveDrawing() {
		return mouseAdapterSaveDrawing;
	}

	public MouseAdapter getMouseAdapterLog() {
		return mouseAdapterLog;
	}

	public MouseAdapter getMouseAdapterToFront() {
		return mouseAdapterToFront;
	}

	public MouseAdapter getMouseAdapterToBack() {
		return mouseAdapterToBack;
	}

	public MouseAdapter getMouseAdapterBringToFront() {
		return mouseAdapterBringToFront;
	}

	public MouseAdapter getMouseAdapterBringToBack() {
		return mouseAdapterBringToBack;
	}
}