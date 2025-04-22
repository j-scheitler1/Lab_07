package calculator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Authors: Ethan Meyer & Josh Scheitler

// TODO - Use JFrame to implement calculator GUI

public class CalculatorView implements ActionListener {

	List<JButton> buttons = new ArrayList<JButton>();
	JLabel label;
	
	public CalculatorView() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		Color number = new Color(80, 80, 80);
		Color numFont = new Color(212, 212, 210);
		Color orange = new Color(255, 149, 0);
		
		
		JButton nine = new JButton("9");
		nine.setBackground(number);
		nine.setForeground(numFont);
		buttons.add(nine);
		JButton eight = new JButton("8");
		eight.setBackground(number);
		eight.setForeground(numFont);
		buttons.add(eight);
		JButton seven = new JButton("7");
		seven.setBackground(number);
		seven.setForeground(numFont);
		buttons.add(seven);
		JButton six = new JButton("6");
		six.setBackground(number);
		six.setForeground(numFont);
		buttons.add(six);
		JButton five = new JButton("5");
		five.setBackground(number);
		five.setForeground(numFont);
		buttons.add(five);
		JButton four = new JButton("4");
		four.setBackground(number);
		four.setForeground(numFont);
		buttons.add(four);
		JButton three = new JButton("3");
		three.setBackground(number);
		three.setForeground(numFont);
		buttons.add(three);
		JButton two = new JButton("2");
		two.setBackground(number);
		two.setForeground(numFont);
		buttons.add(two);
		JButton one = new JButton("1");
		one.setBackground(number);
		one.setForeground(numFont);
		buttons.add(one);
		JButton zero = new JButton("0");
		zero.setBackground(number);
		zero.setForeground(numFont);
		buttons.add(zero);
		JButton decimal = new JButton(".");
		decimal.setBackground(orange);
		decimal.setForeground(numFont);
		buttons.add(decimal);
		JButton equals = new JButton("=");
		equals.setBackground(orange);
		equals.setForeground(numFont);
		buttons.add(equals);
		JButton addition = new JButton("+");
		addition.setBackground(orange);
		addition.setForeground(numFont);
		buttons.add(addition);
		JButton subtraction = new JButton("-");
		subtraction.setBackground(orange);
		subtraction.setForeground(numFont);
		buttons.add(subtraction);
		JButton multiplication = new JButton("x");
		multiplication.setBackground(orange);
		multiplication.setForeground(numFont);
		buttons.add(multiplication);
		JButton division = new JButton("/");
		division.setBackground(orange);
		division.setForeground(numFont);
		buttons.add(division);
		JButton square = new JButton("^2");
		square.setBackground(orange);
		square.setForeground(numFont);
		buttons.add(square);
		JButton squareRoot = new JButton("√");
		squareRoot.setBackground(orange);
		squareRoot.setForeground(numFont);
		buttons.add(squareRoot);
		JButton memAddition = new JButton("M+");
		memAddition.setBackground(orange);
		memAddition.setForeground(numFont);
		buttons.add(memAddition);
		JButton memSubtraction = new JButton("M-");
		memSubtraction.setBackground(orange);
		memSubtraction.setForeground(numFont);
		buttons.add(memSubtraction);
		JButton memRecall = new JButton("M");
		memRecall.setBackground(orange);
		memRecall.setForeground(numFont);
		buttons.add(memRecall);
		JButton clearRecall = new JButton("CM");
		clearRecall.setBackground(orange);
		clearRecall.setForeground(numFont);
		buttons.add(clearRecall);
		
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setLayout(new GridLayout(6, 4, 5, 5));
		panel.add(seven);
		panel.add(eight);
		panel.add(nine);
		panel.add(division);

		panel.add(four);
		panel.add(five);
		panel.add(six);
		panel.add(addition);

		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(subtraction);
		
		panel.add(zero);
		panel.add(decimal);
		panel.add(multiplication);
		panel.add(equals);
		panel.add(square);
		
		panel.add(squareRoot);
		panel.add(memAddition);
		panel.add(memSubtraction);
		panel.add(memRecall);
		panel.add(clearRecall);
		
		label = new JLabel("0", SwingConstants.RIGHT);
		label.setFont(new Font("Arial", Font.BOLD, 24));
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		frame.add(label, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Simple Calculator");
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setActionListeners(ActionListener controller) {
		for (JButton b : buttons) {
			b.addActionListener(controller);
			b.setFont(new Font("Arial", Font.BOLD, 18));
		}
	}
}
