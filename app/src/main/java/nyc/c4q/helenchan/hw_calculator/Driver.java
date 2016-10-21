package nyc.c4q.helenchan.hw_calculator;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

/**
 * Created by akashaarcher on 10/15/16.
 */

public class Driver {

    public static void main(String[] args) {

      //  MainActivity main = new MainActivity();

        calculateString("20%100");

       // Expression e = new Expression("3*5");
        //mXparser.consolePrintln("Res: " + e.getExpressionString() + " = " + e.calculate());

    }


    public static void calculateString(String equation) {
        Expression e = new Expression(equation);
        mXparser.consolePrintln("Res: " + e.getExpressionString() + " = " + e.calculate());
    }

}
