package nyc.c4q.helenchan.hw_calculator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences prefs;

    private String currentInput = "";
    private String inputToCalculate = "";

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
    private Button clearButton;
    private Button percentButton;
    private EditText calcResult;
    private EditText calcInput;

    private Button leftParen;
    private Button rightParen;
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
        clearButton = (Button) findViewById(R.id.clear_btn);

        //operations
        additionButton = (Button) findViewById(R.id.addition);
        subtractionButton = (Button) findViewById(R.id.minus);
        multiplyButton = (Button) findViewById(R.id.multiply);
        divisionButton = (Button) findViewById(R.id.division_button);
        equalsButton = (Button) findViewById(R.id.equals);
        percentButton = (Button) findViewById(R.id.percent);

        //scientific buttons
        leftParen = (Button) findViewById(R.id.left_paren);
        rightParen = (Button) findViewById(R.id.right_paren);
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
        calcInput = (EditText) findViewById(R.id.new_number);
        calcResult = (EditText) findViewById(R.id.result);

        checkOrientation();
        calcResult.setText("");
    }

    public void checkOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            activateButtons();
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            activateScientific();
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        }
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


    public double calculateString(String equation) {
        Expression e = new Expression(equation);
      //mXparser.consolePrintln("Res: " + e.getExpressionString() + " = " + e.calculate());
        double result = e.calculate();
        return result;
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
        num0Button.setOnClickListener(this);
        num1Button.setOnClickListener(this);
        num2Button.setOnClickListener(this);
        num3Button.setOnClickListener(this);
        num4Button.setOnClickListener(this);
        num5Button.setOnClickListener(this);
        num6Button.setOnClickListener(this);
        num7Button.setOnClickListener(this);
        num8Button.setOnClickListener(this);
        num9Button.setOnClickListener(this);
        decimalButton.setOnClickListener(this);

        equalsButton.setOnClickListener(this);
        divisionButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        subtractionButton.setOnClickListener(this);
        additionButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        percentButton.setOnClickListener(this);

    }

    public void activateScientific() {
        activateButtons();
        leftParen.setOnClickListener(this);
        rightParen.setOnClickListener(this);
        degree.setOnClickListener(this);
        radian.setOnClickListener(this);
        inverse.setOnClickListener(this);
        sin.setOnClickListener(this);
        baseLog.setOnClickListener(this);
        cos.setOnClickListener(this);
        log.setOnClickListener(this);
        tan.setOnClickListener(this);
        sqRoot.setOnClickListener(this);
        ans.setOnClickListener(this);
        exponent.setOnClickListener(this);

        /* scientific buttons */
        degreeCover.setOnClickListener(this);
        radianCover.setOnClickListener(this);
        inverseInv.setOnClickListener(this);
        sinInv.setOnClickListener(this);
        baseLogInv.setOnClickListener(this);
        cosInv.setOnClickListener(this);
        logInv.setOnClickListener(this);
        tanInv.setOnClickListener(this);
        sqRootInv.setOnClickListener(this);
        ansInv.setOnClickListener(this);
        exponentInv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.num9:
                inputToCalculate += "9";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num8:
                inputToCalculate += "8";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num7:
                inputToCalculate += "7";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num6:
                inputToCalculate += "6";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num5:
                inputToCalculate += "5";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num4:
                inputToCalculate += "4";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num3:
                inputToCalculate += "3";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num2:
                inputToCalculate += "2";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num1:
                inputToCalculate += "1";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.num0:
                inputToCalculate += "0";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.percent:
                inputToCalculate += "%";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.division_button:
                inputToCalculate += "/";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.multiply:
                inputToCalculate += "*";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.addition:
                inputToCalculate += "+";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.minus:
                inputToCalculate += "-";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.period:
                inputToCalculate += ".";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.left_paren:
                inputToCalculate += "(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.right_paren:
                inputToCalculate += ")";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.factorial:
                inputToCalculate += "!";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.sin_btn:
                inputToCalculate += "sin(";
                calcInput.setText(inputToCalculate);
                break;


            case R.id.cos_btn:
                inputToCalculate += "cos(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.tan_btn:
                inputToCalculate += "tan(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.log_btn:
                inputToCalculate += "log(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.ln_btn:
                inputToCalculate += "ln(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.sq_root:
                inputToCalculate += "√";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.pi_btn:
                inputToCalculate += "pi";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.exponent:
                inputToCalculate += "^";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.exp_btn:
                inputToCalculate += "E";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.e_btn:
                inputToCalculate += "e";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.clear_btn:
                calcInput.setText("");
                inputToCalculate = "";
                break;

            case R.id.equals:
                double answer = calculateString(inputToCalculate);
                calcResult.setText("Ans = " + answer);
                break;

            case R.id.sin_inverse:
                inputToCalculate += "asin(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.ln_inverse:
                inputToCalculate += "e^";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.cos_inverse:
                inputToCalculate += "acos(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.log_inverse:
                inputToCalculate += "10^";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.tan_inverse:
                inputToCalculate += "atan(";
                calcInput.setText(inputToCalculate);
                break;

            case R.id.sq_root_inverse:
                inputToCalculate += "^2";
                calcInput.setText(inputToCalculate);
                break;


            case R.id.ans_inverse:
                double rand = Math.random();
                inputToCalculate += String.valueOf(rand);
                calcInput.setText("Ans = " + String.valueOf(rand));
                break;

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

        }

    }
}
