package main;

import javax.swing.*;
import java.awt.*;

import panels.*;

public class CalculatorFrame extends JFrame {
	static final long serialVersionUID = 1L;
	
	private static CalculatorFrame calculator;
	
	private Display display;
	private Content content;
	
	public static CalculatorFrame createFrame() {
		calculator = new CalculatorFrame();
		return calculator;
	}
	
	private CalculatorFrame() {
		super("Calculator");
		
		frameSettings();
		addPanels();
	}
	
	private void frameSettings() {
		// Frame Size
		setSize(394, 562);
		setResizable(false);
		
		// Frame Background
//		setBackground(Color.green);
		// Know why?
		getContentPane().setBackground(Color.white);
		
		// Closing option
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Frame Visibility
		setVisible(true);
		
		// Frame Layout
		setLayout(null);
		
		// Frame Location
		setLocationRelativeTo(null);
	}
	
	private void addPanels() {
		// Add Display
		display = new Display();
		add(display);
		
		// Add Content
		content = new Content();
		add(content);
	}
	
	// Getters
	public static Display getDisplay() {
		return calculator.display;
	}
}
