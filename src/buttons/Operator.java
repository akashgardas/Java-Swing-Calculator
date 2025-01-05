package buttons;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Operator extends JButton{
	static final long serialVersionUID = 1L;
	
	public Operator(String title, ActionListener actionListener) {
		super(title);
		
		buttonSettings();
		addActionListener(actionListener);
	}
	
	private void buttonSettings() {
		// Button Color
		setBackground(Color.blue);
		
		// Button Font
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	}
}
