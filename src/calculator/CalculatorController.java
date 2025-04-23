package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	// TODO - REFACTOR THIS TO NOT BE THE WORST CODE EVER
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); // TODO - Figure out how to get from GUI
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
			operationSelected(command);
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
		if (current.length() == 0 || current.equals(".")) {
			error("Please enter a valid number");
			return;
		}
		
		model.setFirst(Double.parseDouble(current.toString()));
		model.setOperation(command);
		model.setOpFlag(true);
		model.setKeepFlag(true);
	}
	public void memoryOperationSelected(String command) {
		if (model.opFlag) { return; }
		model.setOperation(command);
		model.setOpFlag(true);
		clearAndDisplay();
	}
	public void singleOperationSelected(String command) {
		if (current.length() == 0 || current.equals(".")) {
			error("Please enter a valid number");
			return;
		}
		model.setOperation(command);
		model.setFirst(Double.parseDouble(current.toString()));
		clearScreen();
		
		double ans = model.parser();
		model.setMemory(ans);
		model.setFirst(ans);
		appendCommand(Double.toString(ans));
		
		model.setOpFlag(false);
		model.setAnsFlag(true);
	}
	
	public void equalSelected() {
		
		model.setSecond(Double.parseDouble(current.toString()));
		clearScreen();
		
		if (model.getOperation().equals("/") && model.getSecond() <= 0) {
			error("Can't Divide by 0 or Negative");
			return;
		}
		
		if (current.equals(".")) {
			error("Please Enter a Valid Number");
			return;
		}
		
		double ans = model.parser();
		
		if (model.getOperation().equals("") || ans == -1) {
			error("No Operation Selected");
			return;
		}
		
		if (model.getOperation().equals("√") && model.getFirst() < 0.0) {
			error("Can't take Square Root of Negative Number");
			return;
		}
		
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
		model.setAnsFlag(true);
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
