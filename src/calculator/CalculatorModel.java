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
	
	public CalculatorModel() {
		this.first = 0.0;
		this.second = 0.0;
		this.memoryNumber = 0.0;
		this.operation = "";
		this.opFlag = false;
		this.decFlag = false;
		
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
	public double getMemory() {
		return this.memoryNumber;
	}
	public void clearMemory() {
		this.memoryNumber = 0.0;
	}

}
