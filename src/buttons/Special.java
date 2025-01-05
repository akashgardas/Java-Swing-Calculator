package buttons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Special extends JButton {
	static final long serialVersionUID = 1L;
	
	public Special(String title, ActionListener actionListener) {
		super(title);
		buttonSettings();
		addActionListener(actionListener);
	}
	
	private void buttonSettings() {
		// Background Color
		setBackground(Color.red);
		
		// Button Font
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	}
}
