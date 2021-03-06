package edu.wpi.cs.wpisuitetng.modules.defecttracker.search.views;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel to contain the list of filters that have been saved by the user
 */
@SuppressWarnings("serial")
public class FilterListPanel extends JPanel {
	
	/**
	 * Constructs the panel
	 */
	public FilterListPanel() {
		// Set the layout manager and give the panel a border
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Filters"));
		
		// TODO implement the rest of the controls to display saved filters
		// and store saved filters in the ConfigManager
		this.add(Box.createRigidArea(new Dimension(0, 150)));
		this.add(new JLabel("Filters will go here."));
	}
}
