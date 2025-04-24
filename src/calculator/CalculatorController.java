package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

// Authors: Ethan Mayer & Josh Scheitler

// TODO - Implement Listening for Button Clicks and telling the model what todo

public class CalculatorController implements ActionListener {
	
	CalculatorModel model;
	CalculatorView view;
	private StringBuilder current = new StringBuilder();
	
	public CalculatorController(CalculatorModel model, CalculatorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
//		((JButton) e.getSource()).setBackground(Color.blue);
		System.out.println(command);
		
		if (command.equals("=")) {
			equalSelected();
		}
		else if (command.equals("+") || command.equals("-") || command.equals("x") || command.equals("/")) {
			operationSelected(command);	
		} 
		else if (command.equals("^2") || command.equals("√")) {
			singleOperationSelected(command);
		}
		else if (command.equals("M+") || command.equals("M-")) {
			memoryOperationSelected(command);
		}
		else if (command.equals("CM")) {
			clearMemory();
		}
		else if (command.equals("M")) {
			displayMemory();
		}
		else if (command.equals(".")) {
			if (model.decFlag == true) { return; } // TODO - THROW ERROR
			model.decFlag = true;
			appendCommand(command);
		}
		else if (command.equals("CA")) {
			clearCalculator();
		}
		else if (command.equals("D")) {
			deleteCommand();
		}
		else { 
			appendCommand(command);
		}
	}
	
	public void operationSelected(String command) {
		if (model.opFlag) { return; }
		if (current.equals(".")) {
			error("Please enter a valid number");
			return;
		}
		if (!model.getOperation().equals("M")) {			
			model.setFirst(Double.parseDouble(current.toString()));
		}
		
		model.setFirst(Double.parseDouble(current.toString()));
		model.setOperation(command);
		model.setOpFlag(true);
		model.setKeepFlag(true);
	}
	public void memoryOperationSelected(String command) {
		System.out.print(model.getAnsFlag());
		if (model.opFlag) { return; }
		if (model.getMemoryNum() == Double.MAX_VALUE) {
			error("No Memory Number Set");
			return;
		}
		model.setOperation(command);
		model.setOpFlag(true);
		clearAndDisplay();
	}
	public void singleOperationSelected(String command) {
		if (current.equals(".")) {
			error("Please enter a valid number");
			return;
		}
		
		model.setOperation(command);
		model.setFirst(Double.parseDouble(current.toString()));
		
		if (model.getOperation().equals("√") && model.getFirst() < 0.0) {
			error("Can't take Square Root of Negative Number");
			return;
		}
		
		double ans = model.parser();
		clearScreen();
		model.setMemory(ans);
		model.setFirst(ans);
		System.out.print(ans);
		appendCommand(Double.toString(ans));
		
		model.setOpFlag(false);
		model.setAnsFlag(true);
	}
	
	public void equalSelected() {
		
		
		System.out.println(current.toString());
		if (current.toString().equals(".")) {
			error("Please Enter a Valid Number");
			return;
		}
		model.setSecond(Double.parseDouble(current.toString()));
		if (model.getOperation().equals("/") && model.getSecond() <= 0) {
			error("Can't Divide by 0 or Negative");
			return;
		}

		clearScreen();
		double ans = model.parser();
		
		
		model.setMemory(ans);
		model.setFirst(ans);
		appendCommand(Double.toString(ans));
		
		
		model.setOperation("");
		model.setAnsFlag(true);
		model.setOpFlag(false);
	}
	public void appendCommand(String command) {
		if (model.getAnsFlag() == true) {
			clearAndDisplay();
			model.setAnsFlag(false);
		}
		if (model.getKeepFlag()) {
			clearAndDisplay();
			model.setKeepFlag(false);
		}
		current.append(command);
		displayCurrent();
	}
	public void deleteCommand() {
		if (current.length() == 0) {
			error("Nothing To Delete");
			return;
		}
		current.deleteCharAt(current.length()-1);
		displayCurrent();
	}
	public void displayMemory() {
		if (model.getMemoryNum() == Double.MAX_VALUE) {
			error("No Memory Number Set");
			return;
		}
		clearScreen();
		appendCommand(String.valueOf(model.getMemoryNum()));
		model.setFirst(model.getMemoryNum());
		model.setOperation("M");
	}
	public void clearCalculator() {
		model.reset();
		clearScreen();
		displayCurrent();
	}
	public void clearScreen() {
		current.setLength(0);
	}
	public void displayCurrent() {
		view.label.setText(current.toString());
	}
	public void clearMemory() {
		model.clearMemory();
	}
	public void clearAndDisplay() {
		current.setLength(0);
		view.label.setText(current.toString());
	}
	public void error(String message) {
		clearScreen();
		view.label.setText("Error: " + message);
		model.reset();
	}


}
