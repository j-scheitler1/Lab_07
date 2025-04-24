package calculatorTests;

import calculator.CalculatorModel;
import calculator.CalculatorView;
import calculator.CalculatorController;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.*;

public class testGUI {

    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        CalculatorModel model = new CalculatorModel();

        CalculatorView view = GuiActionRunner.execute(() -> new CalculatorView());
        CalculatorController controller = new CalculatorController(model, view);

        GuiActionRunner.execute(() -> view.setActionListeners(controller));

        window = new FrameFixture(view.getFrame());
        window.show();
    }

    @After
    public void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    public void testAddition() {
        window.button("1").click();
        window.button("+").click();
        window.button("2").click();
        window.button("=").click();
        window.label("display").requireText("3.0");
    }

    @Test
    public void testSquareRoot() {
        window.button("9").click();
        window.button("√").click();
        window.label("display").requireText("3.0");
    }

    @Test
    public void testSquare() {
        window.button("8").click();
        window.button("^2").click();
        window.label("display").requireText("64.0");
    }

    @Test
    public void testMemoryAddRecall() {
        window.button("5").click();
        window.button("+").click();
        window.button("5").click();
        window.button("=").click();
        window.button("M+").click();
        window.button("M").click();
        window.label("display").requireText("10.0");
    }

    @Test
    public void testNegativeResult() {
        window.button("1").click();
        window.button("0").click();
        window.button("-").click();
        window.button("1").click();
        window.button("2").click();
        window.button("=").click();
        window.label("display").requireText("-2.0");
    }

    @Test
    public void testOnlyOperandsAndResults1() {
        window.button("1").click();
        window.button("2").click();
        window.button("+").click();
        window.button("3").click();
        window.button("4").click();
        window.button("=").click();
        window.label("display").requireText("46.0");
    }

    @Test
    public void testOnlyOperandsAndResults2() {
        window.button("9").click();
        window.button("x").click();
        window.button("5").click();
        window.button("=").click();
        window.label("display").requireText("45.0");
    }

    @Test
    public void testDivisionByZeroError() {
        window.button("1").click();
        window.button("0").click();
        window.button("/").click();
        window.button("0").click();
        window.button("=").click();
        window.label("display").requireText("Error: Can't Divide by 0 or Negative");
    }

    @Test
    public void testDotOnlyInputError() {
        window.button(".").click();
        window.button("=").click();
        window.label("display").requireText("Error: Please Enter a Valid Number");
    }

    @Test
    public void testMemoryAddWithoutCalculation() {
        window.button("3").click();
        window.button("2").click();
        window.button("M+").click();
        window.label("display").requireText("Error: No Memory Number Set");
    }

    @Test
    public void testDeleteOnEmptyInput() {
        window.button("CA").click(); 
        window.button("D").click();
        window.label("display").requireText("Error: Nothing To Delete");
    }

    @Test
    public void testMemoryRecallWhenEmpty() {
        window.button("CA").click();
        window.button("M").click();
        window.label("display").requireText("Error: No Memory Number Set");
    }
    
    @Test
    public void testSqrtOfNegative() {
    	window.button("9").click();
    	window.button("-").click();
    	window.button("1").click();
    	window.button("0").click();
    	window.button("=").click();
    	window.button("√").click();
    	window.label("display").requireText("Error: Can't take Square Root of Negative Number");
    }
}
