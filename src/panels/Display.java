package panels;

import java.awt.*;
import javax.swing.*;

public class Display extends JPanel {
	static final long serialVersionUID = 1L;
	
	JLabel operation, result;
	
	public Display() {
		panelSettings();
		addLabels();
	}
	
	private void panelSettings() {
		// Panel Size
		setSize(342, 124);
		
		// Panel Background color
		setBackground(Color.lightGray);
		
		// Position of the panel
		setLocation(20, 21);
		
		// Panel Layout
		setLayout(new GridLayout(2, 1));
	}
	
	private void addLabels() {
		operation = new JLabel("");
		result = new JLabel("");
		
//		operands.setSize(100, 50);
//		result.setSize(100, 50);
		
		operation.setForeground(Color.gray);
		operation.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		result.setForeground(Color.black);
		result.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
		// Adding
		add(operation);
		add(result);
	}
	
	// Getters
	public JLabel getOperationLabel() {
		return operation;
	}
	
	public JLabel getResultLabel() {
		return result;
	}
}
