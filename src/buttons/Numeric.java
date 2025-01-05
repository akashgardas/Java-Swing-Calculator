package buttons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Numeric extends JButton{
	static final long serialVersionUID = 1L;
	
	public Numeric(String title, ActionListener actionListener) {
		super(title);
		buttonSettings();
		addActionListener(actionListener);
	}
	
	private void buttonSettings() {
		// Button Color
		setBackground(Color.cyan);
		
		// Button Font
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	}
}
