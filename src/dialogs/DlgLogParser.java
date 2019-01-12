package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import strategy.FileLog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * Class represent {@link JDialog} for parsing log command in interaction with user.
 */
public class DlgLogParser extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JScrollPane scrollPane;
	private JList<String> activityLog;
	private DefaultListModel<String> log;
	private FileLog fileLog;
	
	public static void main(String[] args) {
		try {
			DlgLogParser dialog = new DlgLogParser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLogParser() {
		setBounds(100, 100, 600, 400);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Log commands parser");
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
		}
		{
			activityLog = new JList<String>();
			log = new DefaultListModel<>();
			activityLog.setModel(log);
			activityLog.setVisibleRowCount(20);
			activityLog.setEnabled(false);
			activityLog.setBackground(Color.ORANGE);
			activityLog.setFont(new Font("Lucida Console", Font.BOLD, 12));
			scrollPane.setViewportView(activityLog);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Execute");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent click) {
						if (fileLog != null) fileLog.readLine(log.getElementAt(log.size() - 1));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent click) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	/**
	 * <h3>Add command to list that is last executed.</h3>
	 * @param command
	 */
	public void addCommand(String command) {
		log.addElement(command);
	}

	/**
	 * <h3>Method that closes this dialog.</h3> 
	 */
	public void closeDialog() {
		dispose();
	}
	
	public void setFileLog(FileLog fileLog) {
		this.fileLog = fileLog;
	}
}