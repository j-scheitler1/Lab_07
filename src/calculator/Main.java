package calculator;

import java.awt.event.ActionListener;

public class Main {
	
	static CalculatorModel model;
	static CalculatorController controller;
	static CalculatorView view;
	ActionListener listener;
	
	public static void main(String[] args) {
		model = new CalculatorModel();
		view = new CalculatorView();
		view.setActionListeners(controller);
		controller = new CalculatorController(model, view);
	}
}
