package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @VisibleForTesting
    public SimpleCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */

        //find main text-view
        TextView mainTextView= findViewById(R.id.textViewCalculatorOutput);

        //initial main text-view based on calc's output
        mainTextView.setText(calculator.output());

        //find digit buttons and set the onClickListeners
        int[] digitIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        TextView[] digitButtons = new TextView[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = findViewById(digitIds[i]);
            int finalI = i;
            digitButtons[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    calculator.insertDigit(finalI);
                    mainTextView.setText(calculator.output());
                }
            });
        }
        //rest buttons
        View backSpaceButton = (View) findViewById(R.id.buttonBackSpace);
        backSpaceButton.setOnClickListener(v -> {
            calculator.deleteLast();
            mainTextView.setText(calculator.output());
        });

        TextView clearButton = (TextView) findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(v -> {
            calculator.clear();
            mainTextView.setText(calculator.output());
        });

        TextView plusButton = (TextView) findViewById(R.id.buttonPlus);
        plusButton.setOnClickListener(v -> {
            calculator.insertPlus();
            mainTextView.setText(calculator.output());
        });

        TextView minusButton = (TextView) findViewById(R.id.buttonMinus);
        minusButton.setOnClickListener(v -> {
            calculator.insertMinus();
            mainTextView.setText(calculator.output());
        });
        TextView equalsButton = (TextView) findViewById(R.id.buttonEquals);
        equalsButton.setOnClickListener(v -> {
            calculator.insertEquals();
            mainTextView.setText(calculator.output());
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // todo: save calculator state into the bundle
        outState.putSerializable("state",calculator.saveState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
        this.calculator.loadState(savedInstanceState.getSerializable("state"));

        //find main text-view
        TextView mainTextView= (TextView)findViewById(R.id.textViewCalculatorOutput);

        //initial main text-view based on calc's output
        mainTextView.setText(calculator.output());
    }
}