package panels;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;
import javax.swing.*;

import buttons.*;
import main.CalculatorFrame;


public class Content extends Panel implements ActionListener {
	static final long serialVersionUID = 1L;
	
	Special clear, equal, backspace;
	Operator plus, minus, mul, div, dot, leftparen, rightparen;
	Numeric zero, one, two, three, four, five, six, seven, eight, nine;
	
	private JLabel resultLabel;
	private JLabel operationLabel;
	
	public Content() {
		panelSettings();
		addButtons();
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		resultLabel = CalculatorFrame.getDisplay().getResultLabel();
		operationLabel = CalculatorFrame.getDisplay().getOperationLabel();
		
		String text;
		Object source = e.getSource();
		String value = e.getActionCommand();
		
		if(source instanceof Numeric) {
			resultLabel.setText(resultLabel.getText() + value);
		}
		
		if(source instanceof Operator) {
			text = resultLabel.getText();
			if(text.isEmpty() == false)
				resultLabel.setText(text + value);
			else if(value.equals("(") == true)
				resultLabel.setText(value);
		}
				
		if(source instanceof Special) {
			switch(value) {
				case "C":
					resultLabel.setText("");
					operationLabel.setText("");
					break;
				case "backspace":
					text = resultLabel.getText();
					if(text.isEmpty() == false)
						resultLabel.setText(text.substring(0, text.length() - 1));
					break;
				case "=":
					operationLabel.setText(resultLabel.getText());
					try {
						String result = "" + evaluateExpression(resultLabel.getText());
						resultLabel.setText(result);
					}
					catch(Exception exception) {}
					break;
			}
		}

	}
	
	private void panelSettings() {
		// Panel Size
		setSize(342, 350);
				
		// Panel Background color
		setBackground(Color.white);
				
		// Position of the panel
		setLocation(20, 160);
		
		// Layout
		setLayout(new GridLayout(5, 4, 0, 8));
	}
	
	private void addButtons() {
		clear = new Special("C", this);
		clear.setBackground(Color.orange);

		equal = new Special("=", this);
		backspace = new Special("backspace", this);

		plus = new Operator("+", this);
		minus = new Operator("-", this);
		mul = new Operator("*", this);
		div = new Operator("/", this);
		dot = new Operator(".", this);
		leftparen = new Operator("(", this);
		rightparen = new Operator(")", this);
		
		zero = new Numeric("0", this);
		one = new Numeric("1", this);
		two = new Numeric("2", this);
		three = new Numeric("3", this);
		four = new Numeric("4", this);
		five = new Numeric("5", this);
		six = new Numeric("6", this);
		seven = new Numeric("7", this);
		eight = new Numeric("8", this);
		nine = new Numeric("9", this);
		
		// Adding
		add(leftparen);
		add(rightparen);
		add(backspace);
		add(plus);
		add(one);
		add(two);
		add(three);
		add(minus);
		add(four);
		add(five);
		add(six);
		add(mul);
		add(seven);
		add(eight);
		add(nine);
		add(div);
		add(dot);
		add(zero);
		add(clear);
		add(equal);
	}
	
	/*Shunting Yard Algorithm to solve*/
	
	// Method to evaluate the string expression with parentheses
    public static double evaluateExpression(String expression) {
        List<String> tokens = toRPN(expression);
        return evaluateRPN(tokens);
    }

    // Method to convert infix expression to RPN, now with parentheses handling
    private static List<String> toRPN(String expression) {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Handle numbers and decimal points
            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i++));
                }
                output.add(number.toString());
                i--; // Step back after finding the full number
            }
            // Handle opening parenthesis
            else if (c == '(') {
                operators.push(String.valueOf(c));
            }
            // Handle closing parenthesis
            else if (c == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                }
            }
            // Handle operators
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(String.valueOf(c))) {
                    output.add(operators.pop());
                }
                operators.push(String.valueOf(c));
            }
        }

        // Pop any remaining operators in the stack
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    // Method to evaluate the RPN expression
    private static double evaluateRPN(List<String> tokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            }
        }

        return stack.pop();
    }

    // Helper method to check if a string is a number
    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to set operator precedence
    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "(" -> 0;  // Lowest precedence for '(' to handle it correctly
            default -> -1;
        };
    }
}
