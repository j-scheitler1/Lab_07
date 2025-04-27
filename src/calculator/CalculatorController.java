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
            case "M+", "M-" -> memoryOperationSelected(command);
            case "CM" -> clearMemory();
            case "M" -> recallMemory();
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
            error("Please enter a valid number");
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
        if (!model.getAnsFlag()) {
            error("Only results of executed operations can be stored in memory");
            return;
        }

        double currentValue;
        try {
            currentValue = Double.parseDouble(current.toString());
        } catch (NumberFormatException ex) {
            error("Invalid number format");
            return;
        }

        double memory = model.getMemoryNum();
        if (memory == Double.MAX_VALUE) {
            memory = 0.0;
        }

        if (command.equals("M+")) {
            memory += currentValue;
        } else if (command.equals("M-")) {
            memory -= currentValue;
        }

        model.setMemory(memory);

        clearScreen();
        appendCommand(Double.toString(memory));

        model.setFirst(memory);
        model.setOperation("");
        // model.setAnsFlag(false);
        model.setAnsFlag(true);
        model.setOpFlag(false);

        if (lastOperatorButton != null) {
            lastOperatorButton.setBackground(new Color(255, 149, 0));
            lastOperatorButton = null;
        }
    }

    private void recallMemory() {
        if (model.getMemoryNum() == Double.MAX_VALUE) {
            error("No memory number set");
            return;
        }
        clearScreen();
        appendCommand(String.valueOf(model.getMemoryNum()));
        model.setFirst(model.getMemoryNum());
        model.setOperation("");
        model.setAnsFlag(true);
    }

    private void singleOperationSelected(String command) {
        if (current.toString().equals(".")) {
            error("Please enter a valid number");
            return;
        }

        model.setOperation(command);
        model.setFirst(Double.parseDouble(current.toString()));

        if (command.equals("√") && model.getFirst() < 0) {
            error("Can't take square root of negative number");
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
            error("Please enter a valid number");
            return;
        }
        if (model.getAnsFlag()) {
            error("Please select an operation");
            return;
        }

        model.setSecond(Double.parseDouble(current.toString()));

        if (model.getOperation().equals("/") && model.getSecond() == 0) {
            error("Can't divide by 0");
            return;
        }

        double ans = model.parser();
        // model.setMemory(ans);
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
            error("Nothing to delete");
            return;
        }
        current.deleteCharAt(current.length() - 1);
        displayCurrent();
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
