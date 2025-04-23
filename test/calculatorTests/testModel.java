// Authors: Ethan Mayer & Josh Scheitler
package calculatorTests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import calculator.CalculatorModel;

public class testModel {

	CalculatorModel model;

	private static final double DELTA = 1e-5;
	
	@Before
	public void initialize() {
		model = new CalculatorModel();
	}


	@Test
	public void testAddition () {
		
		assertEquals(5.0, model.addition(2.0, 3.0), DELTA);
        assertEquals(100.0, model.addition(40.5, 59.5), DELTA);

        assertEquals(-5.0, model.addition(-2.0, -3.0), DELTA);
        assertEquals(-100.0, model.addition(-40.5, -59.5), DELTA);
        
        assertEquals(2.0, model.addition(2.0, 0.0), DELTA);
        assertEquals(0.0, model.addition(0.0, 0.0), DELTA);
        assertEquals(-3.0, model.addition(0.0, -3.0), DELTA);

        assertEquals(1.0, model.addition(4.0, -3.0), DELTA);
        assertEquals(-1.0, model.addition(-4.0, 3.0), DELTA);
	}
	
	@Test
	public void testSubtraction() {
        
        assertEquals(-1.0,  model.subtraction(  2.0,   3.0), DELTA);
        assertEquals(-19.0, model.subtraction(40.5, 59.5), DELTA);

        assertEquals( 1.0,  model.subtraction( -2.0,  -3.0), DELTA);
        assertEquals( 19.0, model.subtraction(-40.5, -59.5), DELTA);

        assertEquals( 2.0,  model.subtraction(  2.0,   0.0), DELTA);
        assertEquals( 0.0,  model.subtraction(  0.0,   0.0), DELTA);
        assertEquals( 3.0,  model.subtraction(  0.0,  -3.0), DELTA);

        assertEquals( 7.0,  model.subtraction(  4.0,  -3.0), DELTA);
        assertEquals(-7.0,  model.subtraction( -4.0,   3.0), DELTA);
	}
	
	@Test
	public void testMultiplication() {

        assertEquals(   6.0, model.multiplication(  2.0,   3.0), DELTA);
        assertEquals(2409.75, model.multiplication(40.5, 59.5), DELTA);

        assertEquals(   6.0, model.multiplication( -2.0,  -3.0), DELTA);
        assertEquals(2409.75, model.multiplication(-40.5, -59.5), DELTA);

        assertEquals(   0.0, model.multiplication(  2.0,   0.0), DELTA);
        assertEquals(   0.0, model.multiplication(  0.0,   0.0), DELTA);
        assertEquals(   0.0, model.multiplication(  0.0,  -3.0), DELTA);

        assertEquals( -12.0, model.multiplication(  4.0,  -3.0), DELTA);
        assertEquals( -12.0, model.multiplication( -4.0,   3.0), DELTA);
	}
	
	@Test
	public void testDivision() {
        
        assertEquals(0.6666666667, model.division(  2.0,   3.0), DELTA);
        assertEquals(0.6806722689, model.division(40.5, 59.5), DELTA);

        assertEquals(0.6666666667, model.division( -2.0,  -3.0), DELTA);
        assertEquals(0.6806722689, model.division(-40.5, -59.5), DELTA);

        //TODO test for divide by 0

        assertEquals(-1.3333333333, model.division(  4.0,  -3.0), DELTA);
        assertEquals(-1.3333333333, model.division( -4.0,   3.0), DELTA);
	}
	
	@Test
	public void testSquareRoot() {
		
        assertEquals( 3.0,  model.squareRoot(  9.0), DELTA);
        assertEquals(10.0,  model.squareRoot(100.0), DELTA);

        //not perfect squares
        assertEquals(Math.sqrt(  2.0), model.squareRoot(  2.0), DELTA);
        assertEquals(Math.sqrt(  0.01), model.squareRoot(  0.01), DELTA);

        assertEquals( 0.0, model.squareRoot( 0.0), DELTA);
	}
	
	@Test
	public void testSquare() {
		
        assertEquals(  9.0, model.square(  3.0), DELTA);
        assertEquals(  6.25, model.square( 2.5), DELTA);

        assertEquals(  0.0, model.square(  0.0), DELTA);

        //negative
        assertEquals(  9.0, model.square( -3.0), DELTA);
        assertEquals(  6.25, model.square(-2.5), DELTA);		
	}
	
	@Test
	public void testMemory() {
		
		model.setMemory(9.0);
		assertEquals( 9.0, model.getMemoryNum(), DELTA);
		
		model.clearMemory();
		assertEquals( 0.0, model.getMemoryNum(), DELTA);
		
		model.setMemory(-9.0);
		assertEquals(-9.0, model.getMemoryNum(), DELTA);
		
		model.setMemory(19.0);
		assertEquals( 19.0, model.getMemoryNum(), DELTA);

		model.clearMemory();
		assertEquals( 0.0, model.getMemoryNum(), DELTA);
		
		model.setMemory(0.0);
		assertEquals(0.0, model.getMemoryNum(), DELTA);
		
		model.setMemory(19.0);
		assertEquals( 19.0, model.getMemoryNum(), DELTA);
	

	}

	@Test
	public void testGetFirst() {
		assertEquals( 0.0, model.getFirst(), DELTA);
	}
	
	@Test
	public void testSetFirst() {

		model.setFirst(1.0);
		assertEquals(1.0, model.getFirst(), DELTA);
		
		model.setFirst(-1.0);
		assertEquals(-1.0, model.getFirst(), DELTA);
	}
	
	@Test
	public void testGetSecond() {
		assertEquals( 0.0, model.getSecond(), DELTA);
	}
	
	@Test
	public void testSetSecond() {
		
		model.setSecond(1.0);
		assertEquals(1.0, model.getSecond(), DELTA);
		
		model.setSecond(-1.0);
		assertEquals(-1.0, model.getSecond(), DELTA);
	}
	
	@Test
	public void testGetOpFlag () {
		assertEquals( false, model.getOpFlag());
	}

	@Test
	public void testSetOpFlag() {
		model.setOpFlag(true);
		assertEquals(true, model.getOpFlag());
	}
	
	@Test
	public void testGetDecFlag () {
		assertEquals( false, model.getDecFlag());
	}
	
	@Test
	public void testSetDecFlag() {
		model.setDecFlag(true);
		assertEquals(true, model.getDecFlag());
	}
	
	@Test
	public void testGetAnsFlag () {
		assertEquals( false, model.getAnsFlag());;
	}
	
	@Test
	public void testSetAnsFlag() {
		model.setAnsFlag(true);
		assertEquals(true, model.getAnsFlag());
	}
	
	@Test
	public void testSetOperation() {
		model.setOperation("+");
		assertEquals("+", model.getOperation());
	}
	@Test
	public void testGetOperation() {
		assertEquals("", model.getOperation());
	}
}
