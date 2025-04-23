package calculator;

//Authors: Ethan Mayer & Josh Scheitler

//TODO - Implement Calculator Logic and state management

public class CalculatorModel {
	
	double memoryNumber;
	double first;
	double second;
	String operation;
	Boolean opFlag;
	Boolean decFlag;
	Boolean ansFlag;
	
	public CalculatorModel() {
		this.first = 0.0;
		this.second = 0.0;
		this.memoryNumber = 0.0;
		this.operation = "";
		this.opFlag = false;
		this.decFlag = false;
		this.ansFlag = false;
	}
	
	public double parser() {
		
		System.out.println("First " + first);
		System.out.println("Second " + second);
		System.out.println("Operation " + operation);
		
		switch(this.operation) {
			case "+":
				return addition(first, second);
			case "-":
				return subtraction(first, second);
			case "x":
				return multiplication(first, second);
			case "/":
				return division(first, second);
			case "^2":
				System.out.print("TEST: " + square(first));
				return square(first);
			case "√":
				return squareRoot(first);
			case "M+":
				return addition(memoryNumber, second);
			case "M-":
				return subtraction(memoryNumber, second);
		}
		
		return -1;
	}
	
	public double addition (double addends_a, double addends_b) {
		double sum = addends_a + addends_b; 
		return sum;
	}
	
	public double subtraction(double minuend, double subtrahend) {
		double difference = minuend - subtrahend;
		return difference;
	}
	
	public double multiplication(double multiplicand , double multiplier) {
		double product = multiplicand * multiplier;
		return product;
	}
	
	public double division(double dividend, double divisor) {
		double quotient = dividend / divisor;
		return quotient;
	}
	
	public double squareRoot(double radicand) {
		double root = Math.sqrt(radicand);
		return root;
	}
	
	public double square(double num) {
		double square = num * num;
		return square;
	}
	
	public void setMemory(double memNumber) {
		this.memoryNumber = memNumber;
	}
	public double getMemoryNum() {
		return this.memoryNumber;
	}
	public void clearMemory() {
		this.memoryNumber = 0.0;
	}
	
	public double getFirst() {
		return this.first;
	}
	public void setFirst(double num) {
		this.first = num;
	}
	
	public double getSecond() {
		return this.second;
	}
	public void setSecond(double num) {
		this.second = num;
	}
	
	public boolean getOpFlag () {
		return this.opFlag;
	}
	public void setOpFlag(boolean value) {
		this.opFlag = value;
	}
	
	public boolean getDecFlag () {
		return this.decFlag;
	}
	public void setDecFlag(boolean value) {
		this.decFlag = value;
	}
	
	public boolean getAnsFlag () {
		return this.ansFlag;
	}
	public void setAnsFlag(boolean value) {
		this.ansFlag = value;
	}
	
	public void setOperation(String op) {
		this.operation = op;
	}
	public String getOperation() {
		return this.operation;
	}

	public void reset() {
		this.memoryNumber = 0.0;
		this.first = 0.0;
		this.second = 0.0;
		this.operation = "";
		this.opFlag = false;
		this.decFlag = false;
		this.ansFlag = false;
	}
}
