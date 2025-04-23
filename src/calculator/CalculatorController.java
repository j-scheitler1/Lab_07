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
		else { 
			appendCommand(command);
		}
	}
	
	public void operationSelected(String command) {
		if (model.opFlag) { return; }
		
		model.setFirst(Double.parseDouble(current.toString()));
		model.setOperation(command);
		model.setOpFlag(true);
		clearAndDisplay();
	}
	public void memoryOperationSelected(String command) {
		if (model.opFlag) { return; }
		
		// model.setFirst(model.getMemoryNum());
		model.setOperation(command);
		model.setOpFlag(true);
		clearAndDisplay();
	}
	public void singleOperationSelected(String command) {
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
		current.append(command);
		displayCurrent();
	}
	public void displayMemory() {
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
	

}
