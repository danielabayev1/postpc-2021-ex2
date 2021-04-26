package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleCalculatorImpl implements SimpleCalculator {

    // todo: add fields as needed
    public ArrayList<String> calcInput = new ArrayList() {{
        add("0");
    }};
    //tells if the digit we want to insert is the first input after clear or starting position
    public boolean isFirst = true;

    @Override
    public String output() {
        // todo: return output based on the current state
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
            if (isFirst) {
                this.calcInput.remove(0);
                isFirst = false;
            }
            this.calcInput.add(String.valueOf(digit));
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void insertPlus() {
        // todo: insert a plus
        // plus comes after there is already a number in the output history
        // we can reach this.calcInput.size() - 1 all the time since we keep 0, if we clear/delete last number
        String lastElement = this.calcInput.get(this.calcInput.size() - 1);
        // if the last element equals + or - we ignore this insertPlus since first order sign matters
        if (!lastElement.equals("+") && !lastElement.equals("-")) {
            this.calcInput.add("+");
            this.isFirst = false;
        }

    }

    @Override
    public void insertMinus() {
        // todo: insert a minus
        //same as logic as insertPlus
        String lastElement = this.calcInput.get(this.calcInput.size() - 1);
        if (!lastElement.equals("+") && !lastElement.equals("-")) {
            this.calcInput.add("-");
            this.isFirst = false;
        }
    }

    /**
     * gives the next number that generated from the array list from index i
     * should stop when reaches a sign or the end of the list
     *
     * @param i index to start from
     * @return integer array of [the first index which is not related to the number, the number we found]
     */
    private int[] giveNextNum(int i) {
        if (i >= this.calcInput.size()) {
            return new int[]{this.calcInput.size(), 0};
        }
        String num = "";
        while (i < this.calcInput.size() && !(this.calcInput.get(i).equals("-")) && !(this.calcInput.get(i).equals("+"))) {
            num += this.calcInput.get(i);
            i++;
        }
        return new int[]{i, Integer.parseInt(num)};

    }

    @Override
    public void insertEquals() {
        // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
        //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
        int i = 0;
        int[] ret;
        String operator = "+";
        int answer = 0;
        int len = this.calcInput.size();
        while (i < len) {
            // ret gets [first index after the number that fetched, the number]
            ret = giveNextNum(i);
            answer = operator.equals("+") ? answer + ret[1] : answer - ret[1];
            // 'if' checks that we didn't come to the end of the Arraylist, so we noticed an operator
            if (ret[0] < len) {
                operator = this.calcInput.get(ret[0]);
            }
            i = ret[0] + 1;
        }
        this.calcInput.clear();
        this.calcInput.add(String.valueOf(answer));
    }

    @Override
    public void deleteLast() {
        // todo: delete the last input (digit, plus or minus)
        //  e.g.
        //  if input was "12+3" and called `deleteLast()`, then delete the "3"
        //  if input was "12+" and called `deleteLast()`, then delete the "+"
        //  if no input was given, then there is nothing to do here
        if (this.calcInput.size() >= 1) {
            this.calcInput.remove(this.calcInput.size() - 1);
            if (this.calcInput.isEmpty() ||
                    (this.calcInput.size() == 1 &&
                            (this.calcInput.get(0).equals("+") || this.calcInput.get(0).equals("-")))) {
                this.calcInput.add("0");
                isFirst = true;
            }
        }
    }

    @Override
    public void clear() {
        // todo: clear everything (same as no-input was never given)
        this.calcInput.clear();
        this.calcInput.add("0");
        this.isFirst = true;
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        // todo: insert all data to the state, so in the future we can load from this state
        for (String str : this.calcInput) {
            state.calcInputState.add(str);
        }
        state.isFirstState = this.isFirst;
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        // todo: use the CalculatorState to load
        this.calcInput.clear();
        for (String str : casted.calcInputState) {
            calcInput.add(str);
        }
        this.isFirst = casted.isFirstState;
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
        ArrayList<String> calcInputState = new ArrayList<String>();
        boolean isFirstState;
    }
}
