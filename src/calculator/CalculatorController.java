package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

// Authors: Ethan Meyer & Josh Scheitler

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
		String command = e.getActionCommand(); // TODO - Figure out how to get from GUI
		System.out.println(command);
		
		if (command.equals("=")) {
			equalSelected();
		}
		else if (command.equals("+") || command.equals("-") || command.equals("x") || command.equals("/")) {
			if (model.opFlag == true) { return; } // TODO - THROW EXCEPTION
			operationSelected(command);	
		} 
		else if (command.equals("^2") || command.equals("√")) {
			
		}
		else if (command.equals("M+") || command.equals("M-") || command.equals("M") || command.equals("CM")) { 
			
		} 
		else if (command.equals(".")) {
			if (model.decFlag == true) { return; } // TODO - THROW ERROR
			model.decFlag = true;
			appendCommand(command);
		}
		else { 
			appendCommand(command);
		}
	}
	
	// TODO - Add edge case checks for double
	
	// set the operation, first number
	public void operationSelected(String command) {
		if (model.opFlag == true) { return; } // TODO - THROW ERROR
		model.operation = command;
		model.first = Double.parseDouble(current.toString());
		model.opFlag = true;
		current.setLength(0);
		view.label.setText(current.toString());
	}
	public void equalSelected() {
		
		model.second = Double.parseDouble(current.toString());
		
		current.setLength(0);
		
		double ans = model.parser();
		model.setMemory(ans);
		
		current.append(Double.toString(ans));
		view.label.setText(current.toString());
	}
	public void appendCommand(String command) {
		current.append(command);
		view.label.setText(current.toString());
	}

}
