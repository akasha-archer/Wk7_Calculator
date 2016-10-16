package nyc.c4q.helenchan.hw_calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private Context context;
    private SharedPreferences prefs;

    //implements View.OnClickListener
    private Button num1Button;
    private Button num2Button;
    private Button num3Button;
    private Button num4Button;
    private Button num5Button;
    private Button num6Button;
    private Button num7Button;
    private Button num8Button;
    private Button num9Button;
    private Button num0Button;
    private Button decimalButton;
    private Button additionButton;
    private Button subtractionButton;
    private Button multiplyButton;
    private Button divisionButton;
    private Button equalsButton;
    private Button deleteButton;
    private Button negButton;
    private Button percentButton;
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Button degree;
    private Button radian;
    private Button inverse;
    private Button sin;
    private Button baseLog;
    private Button cos;
    private Button log;
    private Button tan;
    private Button sqRoot;
    private Button ans;
    private Button exponent;

    /* inverse buttons */
    private Button degreeCover;
    private Button radianCover;
    private Button inverseInv;
    private Button sinInv;
    private Button baseLogInv;
    private Button cosInv;
    private Button logInv;
    private Button tanInv;
    private Button sqRootInv;
    private Button ansInv;
    private Button exponentInv;


    /* variables to hold operands and type of calculations
       using the class instance Double so the value can be set to null to indicate that there
       isn't a value yet
     */
    private Double operand1 = null;
    private String pendingOperation = "=";
    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        num0Button = (Button) findViewById(R.id.num0);
        num1Button = (Button) findViewById(R.id.num1);
        num2Button = (Button) findViewById(R.id.num2);
        num3Button = (Button) findViewById(R.id.num3);
        num4Button = (Button) findViewById(R.id.num4);
        num5Button = (Button) findViewById(R.id.num5);
        num6Button = (Button) findViewById(R.id.num6);
        num7Button = (Button) findViewById(R.id.num7);
        num8Button = (Button) findViewById(R.id.num8);
        num9Button = (Button) findViewById(R.id.num9);
        decimalButton = (Button) findViewById(R.id.period);

        //operations
        additionButton = (Button) findViewById(R.id.addition);
        subtractionButton = (Button) findViewById(R.id.minus);
        multiplyButton = (Button) findViewById(R.id.multiply);
        divisionButton = (Button) findViewById(R.id.division_button);
        equalsButton = (Button) findViewById(R.id.equals);
        deleteButton = (Button) findViewById(R.id.delete_button);
        negButton = (Button) findViewById(R.id.neg_button);
        percentButton = (Button) findViewById(R.id.percent);

        degree = (Button) findViewById(R.id.deg_btn);
        radian = (Button) findViewById(R.id.rad_btn);
        inverse = (Button) findViewById(R.id.inverse_btn);
        sin = (Button) findViewById(R.id.sin_btn);
        baseLog = (Button) findViewById(R.id.ln_btn);
        cos = (Button) findViewById(R.id.cos_btn);
        log = (Button) findViewById(R.id.log_btn);
        tan = (Button) findViewById(R.id.tan_btn);
        sqRoot = (Button) findViewById(R.id.sq_root);
        ans = (Button) findViewById(R.id.ans_btn);
        exponent = (Button) findViewById(R.id.exp_btn);

        /* inverse buttons */
        degreeCover = (Button) findViewById(R.id.deg_cover);
        radianCover = (Button) findViewById(R.id.rad_cover);
        inverseInv = (Button) findViewById(R.id.inverse_reverse);
        sinInv = (Button) findViewById(R.id.sin_inverse);
        baseLogInv = (Button) findViewById(R.id.ln_inverse);
        cosInv = (Button) findViewById(R.id.cos_inverse);
        logInv = (Button) findViewById(R.id.log_inverse);
        tanInv = (Button) findViewById(R.id.tan_inverse);
        sqRootInv = (Button) findViewById(R.id.sq_root_inverse);
        ansInv = (Button) findViewById(R.id.ans_inverse);
        exponentInv = (Button) findViewById(R.id.exponent_inverse);


        // input and answer fields
        newNumber = (EditText) findViewById(R.id.new_number);
        result = (EditText) findViewById(R.id.result);
        displayOperation = (TextView) findViewById(R.id.operation);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            activateButtons();
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            activateScientific();
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        }

        startCalc();
    }


    public void radianClicked() {
        radianCover.setVisibility(View.VISIBLE);
        degree.setVisibility(View.VISIBLE);
        degreeCover.setVisibility(View.INVISIBLE);
    }

    public void degreeClicked() {
        degreeCover.setVisibility(View.VISIBLE);
        radianCover.setVisibility(View.INVISIBLE);
        radian.setVisibility(View.VISIBLE);
    }

    public void radianCoverClicked() {
        radian.setVisibility(View.VISIBLE);
        degreeCover.setVisibility(View.VISIBLE);
        radianCover.setVisibility(View.INVISIBLE);
    }

    public void degreeCoverClicked() {
        degree.setVisibility(View.VISIBLE);
        degreeCover.setVisibility(View.INVISIBLE);
        radianCover.setVisibility(View.VISIBLE);
    }

    public void inverseClicked() {
        inverseInv.setVisibility(View.VISIBLE);
        sinInv.setVisibility(View.VISIBLE);
        baseLogInv.setVisibility(View.VISIBLE);
        cosInv.setVisibility(View.VISIBLE);
        logInv.setVisibility(View.VISIBLE);
        tanInv.setVisibility(View.VISIBLE);
        sqRootInv.setVisibility(View.VISIBLE);
        ansInv.setVisibility(View.VISIBLE);
        exponentInv.setVisibility(View.VISIBLE);

    }

    public void inverseRevClicked() {
        inverseInv.setVisibility(View.INVISIBLE);
        sin.setVisibility(View.VISIBLE);
        baseLog.setVisibility(View.VISIBLE);
        cos.setVisibility(View.VISIBLE);
        log.setVisibility(View.VISIBLE);
        tan.setVisibility(View.VISIBLE);
        sqRoot.setVisibility(View.VISIBLE);
        ans.setVisibility(View.VISIBLE);
        exponent.setVisibility(View.VISIBLE);

        /* turning off inverse buttons */
        sinInv.setVisibility(View.INVISIBLE);
        baseLogInv.setVisibility(View.INVISIBLE);
        cosInv.setVisibility(View.INVISIBLE);
        logInv.setVisibility(View.INVISIBLE);
        tanInv.setVisibility(View.INVISIBLE);
        sqRootInv.setVisibility(View.INVISIBLE);
        ansInv.setVisibility(View.INVISIBLE);
        exponentInv.setVisibility(View.INVISIBLE);

    }


    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Button b = (Button) v;

            switch (v.getId()) {
                case R.id.rad_btn:
                    radianClicked();
                    break;

                case R.id.rad_cover:
                    radianCoverClicked();
                    break;

                case R.id.deg_btn:
                    degreeClicked();
                    break;

                case R.id.deg_cover:
                    degreeCoverClicked();
                    break;

                case R.id.inverse_btn:
                    inverseClicked();
                    break;

                case R.id.inverse_reverse:
                    inverseRevClicked();
                    break;

            /* clicking inverse buttons restores non-inverse state */
                case R.id.sin_inverse:
                case R.id.ln_inverse:
                case R.id.cos_inverse:
                case R.id.log_inverse:
                case R.id.tan_inverse:
                case R.id.sq_root_inverse:
                case R.id.ans_inverse:
                case R.id.exponent_inverse:
                    inverseRevClicked();
                    break;

            }

            newNumber.append(b.getText().toString());

        }

    };


    public void startCalc() {
        activateButtons();
        //On Click listener for the operation buttons
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();

                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        equalsButton.setOnClickListener(opListener);
        divisionButton.setOnClickListener(opListener);
        multiplyButton.setOnClickListener(opListener);
        subtractionButton.setOnClickListener(opListener);
        additionButton.setOnClickListener(opListener);
//        negButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String value = newNumber.getText().toString();
//                if (value.length() == 0) {
//                    newNumber.setText("-");
//                } else {
//                    try {
//                        Double doubleValue = Double.valueOf(value);
//                        doubleValue *= -1;
//                        newNumber.setText(doubleValue.toString());
//                    } catch (NumberFormatException e) {
//                        //newNumber was "-" or "." so clear it
//                        newNumber.setText("");
//                    }
//                }
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);
    }


    public void calculateString(String equation) {
        Expression e = new Expression(equation);
        mXparser.consolePrintln("Res: " + e.getExpressionString() + " = " + e.calculate());
    }


    private void performOperation(Double value, String operation) {
        if (null == operand1) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "x":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");  //clears the input widget
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            activateScientific();
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            activateButtons();
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


    public void activateButtons() {

        num0Button.setOnClickListener(listener);
        num1Button.setOnClickListener(listener);
        num2Button.setOnClickListener(listener);
        num3Button.setOnClickListener(listener);
        num4Button.setOnClickListener(listener);
        num5Button.setOnClickListener(listener);
        num6Button.setOnClickListener(listener);
        num7Button.setOnClickListener(listener);
        num8Button.setOnClickListener(listener);
        num9Button.setOnClickListener(listener);
        decimalButton.setOnClickListener(listener);


    }

    public void activateScientific() {
        num0Button.setOnClickListener(listener);
        num1Button.setOnClickListener(listener);
        num2Button.setOnClickListener(listener);
        num3Button.setOnClickListener(listener);
        num4Button.setOnClickListener(listener);
        num5Button.setOnClickListener(listener);
        num6Button.setOnClickListener(listener);
        num7Button.setOnClickListener(listener);
        num8Button.setOnClickListener(listener);
        num9Button.setOnClickListener(listener);
        decimalButton.setOnClickListener(listener);

        degree.setOnClickListener(listener);
        radian.setOnClickListener(listener);
        inverse.setOnClickListener(listener);
        sin.setOnClickListener(listener);
        baseLog.setOnClickListener(listener);
        cos.setOnClickListener(listener);
        log.setOnClickListener(listener);
        tan.setOnClickListener(listener);
        sqRoot.setOnClickListener(listener);
        ans.setOnClickListener(listener);
        exponent.setOnClickListener(listener);

        degreeCover.setOnClickListener(listener);
        radianCover.setOnClickListener(listener);
        inverseInv.setOnClickListener(listener);
        sinInv.setOnClickListener(listener);
        baseLogInv.setOnClickListener(listener);
        cosInv.setOnClickListener(listener);
        logInv.setOnClickListener(listener);
        tanInv.setOnClickListener(listener);
        sqRootInv.setOnClickListener(listener);
        ansInv.setOnClickListener(listener);
        exponentInv.setOnClickListener(listener);
    }


}