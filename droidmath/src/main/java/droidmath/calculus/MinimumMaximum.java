package droidmath.calculus;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;

public class MinimumMaximum {

    private Derivatives der = new Derivatives();
    private Evaluate eva = new Evaluate();
    private Assistant assistant = new Assistant();

    //x1 = x0 - f(x)' / f(x)''
    public String newtonOptimizacion(double x1, int iterations, String function) {
        if(assistant.validateFunction(function) == false){
            return null;
        }
        if (iterations < 1) {
            return null;
        }
        String store = "";
        String nR = "";
        double X = x1;
        double xAssis = X;
        double firstDerivativeFOfX = 0;
        double secondDerivativeFOfX = 0;

        for (int i = 0; i < iterations; i++) {
            firstDerivativeFOfX = der.firstDifferenceCentered4(0.01, X, function);
            secondDerivativeFOfX = der.secondDifferenceCentered4(0.01, X, function);
            X = X - (firstDerivativeFOfX / secondDerivativeFOfX);
            double y = eva.solveOneVariable(function, xAssis, Evaluate.RADIANS);
            store = "x" + (i + 1) + "= " + xAssis + "  y= " + y;
            nR += store + "\n";
            store = "";
            xAssis = X;
        }
        return nR;
    }

}
