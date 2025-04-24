package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

// Authors: Ethan Mayer & Josh Scheitler

public class CalculatorController implements ActionListener {

	private final CalculatorModel model;
	private final CalculatorView view;
	private final StringBuilder current = new StringBuilder();
	private JButton lastOperatorButton = null;

	public CalculatorController(CalculatorModel model, CalculatorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);

		switch (command) {
			case "=" -> equalSelected();
			case "+", "-", "x", "/" -> operationSelected(command, (JButton) e.getSource());
			case "^2", "√" -> singleOperationSelected(command);
			case "M+" , "M-" -> memoryOperationSelected(command);
			case "CM" -> clearMemory();
			case "M" -> displayMemory();
			case "." -> {
				if (model.decFlag) return;
				model.decFlag = true;
				appendCommand(command);
			}
			case "CA" -> clearCalculator();
			case "D" -> deleteCommand();
			default -> appendCommand(command);
		}
	}

	private void operationSelected(String command, JButton sourceButton) {
		if (model.opFlag) return;

		if (current.toString().equals(".")) {
			error("Please enter a Valid Number");
			return;
		}

		try {
			model.setFirst(Double.parseDouble(current.toString()));
		} catch (NumberFormatException ex) {
			error("Invalid number format");
			return;
		}

		model.setOperation(command);
		model.setOpFlag(true);
		model.setKeepFlag(true);

		highlightOperatorButton(sourceButton);
	}

	private void highlightOperatorButton(JButton newButton) {
		if (lastOperatorButton != null) {
			lastOperatorButton.setBackground(new Color(80, 80, 80));
		}
		newButton.setBackground(Color.ORANGE);
		lastOperatorButton = newButton;
	}

	private void memoryOperationSelected(String command) {
		if (current.toString().equals(".")) {
			error("Please enter a valid Number");
			return;
		}
		double ans = -56.7;
		double currentValue = Double.parseDouble(current.toString());
		double memory = model.getMemoryNum();

		if (memory == Double.MAX_VALUE) {
			error("No Memory Number Set");
			return;
		}

		if (command.equals("M+")) {
			model.setFirst(currentValue);
			model.setSecond(memory);
			model.setOperation("+");
			ans = model.parser();
		} else if (command.equals("M-")) {
			model.setFirst(currentValue);
			model.setSecond(memory);
			model.setOperation("-");
			ans = model.parser();
		}
		if (ans != -56.7) {			
			model.setMemory(ans);
		}
		
		model.setMemory(ans);
		model.setFirst(ans);
		clearScreen();
		appendCommand(Double.toString(ans));

		model.setOperation("");
		model.setAnsFlag(true);
		model.setOpFlag(false);

		if (lastOperatorButton != null) {
			lastOperatorButton.setBackground(new Color(255, 149, 0));
			lastOperatorButton = null;
		}
	}

	private void singleOperationSelected(String command) {
		if (current.toString().equals(".")) {
			error("Please enter a Valid Number");
			return;
		}

		model.setOperation(command);
		model.setFirst(Double.parseDouble(current.toString()));

		if (command.equals("√") && model.getFirst() < 0) {
			error("Can't take Square Root of Negative Number");
			return;
		}

		double ans = model.parser();
		clearScreen();
		model.setMemory(ans);
		model.setFirst(ans);
		appendCommand(Double.toString(ans));
		model.setOpFlag(false);
		model.setAnsFlag(true);
	}

	private void equalSelected() {
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

		if (lastOperatorButton != null) {
			lastOperatorButton.setBackground(new Color(255, 149, 0));
			lastOperatorButton = null;
		}
	}

	private void appendCommand(String command) {
		if (model.getAnsFlag()) {
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

	private void deleteCommand() {
		if (current.length() == 0) {
			error("Nothing To Delete");
			return;
		}
		current.deleteCharAt(current.length() - 1);
		displayCurrent();
	}

	private void displayMemory() {
		if (model.getMemoryNum() == Double.MAX_VALUE) {
			error("No Memory Number Set");
			return;
		}
		clearScreen();
		appendCommand(String.valueOf(model.getMemoryNum()));
		model.setFirst(model.getMemoryNum());
		model.setOperation("M");
		model.setAnsFlag(true);
	}

	private void clearCalculator() {
		model.reset();
		clearScreen();
		displayCurrent();
	}

	private void clearScreen() {
		current.setLength(0);
	}

	private void displayCurrent() {
		view.label.setText(current.toString());
	}

	private void clearMemory() {
		model.clearMemory();
	}

	private void clearAndDisplay() {
		clearScreen();
		displayCurrent();
	}

	private void error(String message) {
		clearScreen();
		view.label.setText("Error: " + message);
		model.reset();
	}
}
