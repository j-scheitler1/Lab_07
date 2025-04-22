package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		String command = ""; // TODO - Figure out how to get from GUI
		
		switch(command) {
			case "+":
				break;
			case "-":
				break;
			case "x":
				break;
			case "/":
				break;
			case "^2":
				break;
			case "√":
				break;
			case "M+":
				break;
			case "M-":
				break;
			case "M":
				break;
			case "CM":
				break;
			default:
				current.append(command);
				break;
		}
		
	}

}
