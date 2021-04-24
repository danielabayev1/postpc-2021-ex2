package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleCalculatorImpl implements SimpleCalculator {

    // todo: add fields as needed
    private List<String> calcInput = new ArrayList<>();

    @Override
    public String output() {
        // todo: return output based on the current state
        if (this.calcInput.isEmpty()) {
            return "0";
        } else if (this.calcInput.size() == 1 && (this.calcInput.get(0).equals("+") || (this.calcInput.get(0).equals("-")))) {
            return this.calcInput.get(0).equals("+") ? "0+" : "0-";
        }
        StringBuilder calcOutput = new StringBuilder();
        for (String str : this.calcInput) {
            calcOutput.append(str);
        }
        return calcOutput.toString();

    }

    @Override
    public void insertDigit(int digit) {
        // todo: insert a digit
        if (digit <= 9 && digit >= 0) {
            this.calcInput.add(String.valueOf(digit));
        } else {
            throw new NumberFormatException();
        }
    }

    @Override
    public void insertPlus() {
        // todo: insert a plus
        if (!this.calcInput.isEmpty()) {
            String lastElement = this.calcInput.get(this.calcInput.size() - 1);
            if (!lastElement.equals("+") && !lastElement.equals("-")) {
                this.calcInput.add("+");
            }
        } else {
            this.calcInput.add("+");
        }
    }

    @Override
    public void insertMinus() {
        // todo: insert a minus
        if (!this.calcInput.isEmpty()) {
            String lastElement = this.calcInput.get(this.calcInput.size() - 1);
            if (!lastElement.equals("+") && !lastElement.equals("-")) {
                this.calcInput.add("-");
            }
        } else {
            this.calcInput.add("-");
        }
    }

    @Override
    public void insertEquals() {
        // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
        //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
        int calcOutput = 0;
        boolean operate = false;
        String lastOperand = "";
        String firstOperand = "";
        int i = 0;
        while (i < calcInput.size()) {
            while (!this.calcInput.get(i).equals("+") && !this.calcInput.get(i).equals("-")) {
                firstOperand += this.calcInput.get(i);
                i++;
            }
            lastOperand = this.calcInput.get(i);
            i++;
            while (!this.calcInput.get(i).equals("+") && !this.calcInput.get(i).equals("-")) {
                lastOperand += this.calcInput.get(i);
                i++;
            }
            calcOutput = lastOperand.equals("+") ?
                    Integer.parseInt(firstOperand) + Integer.parseInt(lastOperand) :
                    Integer.parseInt(firstOperand) - Integer.parseInt(lastOperand);
            i++;
        }
        this.calcInput.clear();
        this.calcInput.add(String.valueOf(calcOutput));
    }

    @Override
    public void deleteLast() {
        // todo: delete the last input (digit, plus or minus)
        //  e.g.
        //  if input was "12+3" and called `deleteLast()`, then delete the "3"
        //  if input was "12+" and called `deleteLast()`, then delete the "+"
        //  if no input was given, then there is nothing to do here
        if (!this.calcInput.isEmpty()) {
            this.deleteLast();
        }
    }

    @Override
    public void clear() {
        // todo: clear everything (same as no-input was never given)
        this.calcInput.clear();
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        // todo: insert all data to the state, so in the future we can load from this state
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        // todo: use the CalculatorState to load
    }

    private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    }
}
