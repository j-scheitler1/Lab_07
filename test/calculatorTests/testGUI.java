package calculatorTests;

import calculator.CalculatorModel;
import calculator.CalculatorView;
import calculator.CalculatorController;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.*;

import javax.swing.*;

public class testGUI {

    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        // Detect Swing threading violations
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        CalculatorModel model = new CalculatorModel();

        CalculatorView view = GuiActionRunner.execute(() -> {
            CalculatorView v = new CalculatorView();
            return v;
        });

        CalculatorController controller = new CalculatorController(model, view);

        // Run this inside EDT
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
}
