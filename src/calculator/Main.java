package calculator;

import java.awt.event.ActionListener;

//Authors: Ethan Mayer, Josh Scheitler

public class Main {
	
	static CalculatorModel model;
	static CalculatorController controller;
	static CalculatorView view;
	ActionListener listener;
	
	public static void main(String[] args) {
		model = new CalculatorModel();
		view = new CalculatorView();
		controller = new CalculatorController(model, view);
		view.setActionListeners(controller);
	}
}
