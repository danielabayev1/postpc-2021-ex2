package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SimpleCalculatorImplTest {

    @Test
    public void when_noInputGiven_then_outputShouldBe0() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_inputIsPlus_then_outputShouldBe0Plus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertPlus();
        assertEquals("0+", calculatorUnderTest.output());
    }


    @Test
    public void when_inputIsMinus_then_outputShouldBeCorrect() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        String expected = "0-"; // TODO: decide the expected output when having a single minus
        assertEquals(expected, calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        try {
            calculatorUnderTest.insertDigit(357);
            fail("should throw an exception and not reach this line");
        } catch (RuntimeException e) {
            // good :)
        }
    }


    @Test
    public void when_callingDeleteLast_then_lastOutputShouldBeDeleted() {
        // todo: implement test
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertDigit(8);
        calculatorUnderTest.deleteLast();
        assertEquals("5-7", calculatorUnderTest.output());
        calculatorUnderTest.deleteLast();
        assertEquals("5-", calculatorUnderTest.output());
        calculatorUnderTest.deleteLast();
        assertEquals("5", calculatorUnderTest.output());
    }

    @Test
    public void when_callingClear_then_outputShouldBeCleared() {
        // todo: implement test
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());

    }

    @Test
    public void when_savingState_should_loadThatStateCorrectly() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        // give some input
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);

        // save current state
        Serializable savedState = calculatorUnderTest.saveState();
        assertNotNull(savedState);

        // call `clear` and make sure calculator cleared
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());

        // load the saved state and make sure state was loaded correctly
        calculatorUnderTest.loadState(savedState);
        assertEquals("5+7", calculatorUnderTest.output());
    }

    @Test
    public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
        // TODO: implement the test based on this method's name.
        //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`

        firstCalculator.insertDigit(3);
        firstCalculator.insertMinus();
        firstCalculator.insertDigit(5);

        Serializable firstSavedState = firstCalculator.saveState();
        secondCalculator.loadState(firstSavedState);

        assertFalse(secondCalculator.isFirst);
        assertEquals("3-5", secondCalculator.output());

        secondCalculator.insertEquals();
        assertEquals("-2", secondCalculator.output());

    }

    @Test
    public void complicatedInput_part1() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertDigit(9);
        calculatorUnderTest.insertDigit(9);
        calculatorUnderTest.insertDigit(9);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(8);
        calculatorUnderTest.insertDigit(8);
        calculatorUnderTest.insertDigit(8);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertDigit(3);
        assertEquals("-111-333", calculatorUnderTest.output());

    }

    @Test
    public void complicatedInput_part2() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(5);
        assertEquals("5+7-125", calculatorUnderTest.output());
    }

    @Test
    public void checkNewInput_after_deletingLastInput() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.insertDigit(8);

        assertEquals("8", calculatorUnderTest.output());
    }

    @Test
    public void complicatedInput_part3() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertDigit(9);
        calculatorUnderTest.clear();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.clear();
        calculatorUnderTest.insertDigit(8);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();

        assertEquals("1", calculatorUnderTest.output());
    }

    @Test
    public void complicatedInput_multipleInsertEquals() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertDigit(8);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(4);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertEquals();

        assertEquals("4", calculatorUnderTest.output());
    }

    @Test
    public void check_FirstInputMinus_should_BeChainedTo0() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(3);

        assertEquals("0-1+3", calculatorUnderTest.output());
    }

    @Test
    public void when_deletingLast_andAnOrderIsWhatLeft_shouldOutput0() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.deleteLast();

        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_noInputGiven_AndCallingDeleteLast_then_outputShouldBe0(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.deleteLast();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void check_multipleInsertMinus(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(3);
        assertEquals("0-5-3", calculatorUnderTest.output());

        calculatorUnderTest.insertEquals();
        assertEquals("-8", calculatorUnderTest.output());
    }
    @Test
    public void switchingCalculatorsStates() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();

        firstCalculator.insertMinus();
        firstCalculator.insertDigit(3);
        firstCalculator.insertMinus();
        Serializable firstCalcState = firstCalculator.saveState();

        secondCalculator.insertMinus();
        secondCalculator.insertDigit(8);
        secondCalculator.insertMinus();
        secondCalculator.insertDigit(8);
        Serializable secondCalcState = secondCalculator.saveState();

        firstCalculator.loadState(secondCalcState);
        secondCalculator.loadState(firstCalcState);

        assertEquals("0-8-8",firstCalculator.output());
        assertEquals("0-3-",secondCalculator.output());
    }
    @Test
    public void insertSequenceOfOrders_should_showOnlyFirstOrder(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertMinus();
        assertEquals("0-",calculatorUnderTest.output());
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertPlus();
        assertEquals("0-",calculatorUnderTest.output());
    }

    // TODO:
    //  the existing tests are not enough since they only test simple use-cases with small inputs.
    //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
    //  examples:
    //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
    //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
    //  - given input "8-7=+4=-1=", expected output is "4"
    //  - given input "999-888-222=-333", expected output is "-111-333"
    //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
    //  etc etc.
    //  feel free to be creative in your tests!
}