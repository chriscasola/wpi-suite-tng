package edu.wpi.cs.wpisuitetng.janeway.gui.login;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel to contain buttons for the login window
 *
 */
@SuppressWarnings("serial")
public class LoginButtonPanel extends JPanel {
	
	/** The connect button */
	protected JButton btnConnect;

	/**
	 * Constructs the button panel for the login window
	 */
	public LoginButtonPanel() {

		// Use the BoxLayout layout manager
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(layout);
		
		// Add the login button
		btnConnect = new JButton("Connect");
		btnConnect.setPreferredSize(new Dimension(btnConnect.getPreferredSize().width + 50, btnConnect.getPreferredSize().height));
		add(Box.createHorizontalGlue());
		add(btnConnect);
		add(Box.createHorizontalGlue());
		
		validate();
	}
}
