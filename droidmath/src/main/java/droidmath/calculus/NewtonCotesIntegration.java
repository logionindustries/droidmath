package droidmath.calculus;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;

public class NewtonCotesIntegration {

    private Evaluate evalua = new Evaluate();
    private Assistant assistant = new Assistant();

    //*****integrals
    public double trapezoidal(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double area = 0;
        double fOfA = evalua.solveOneVariable(function, a, Evaluate.RADIANS);
        double fOfB = evalua.solveOneVariable(function, b, Evaluate.RADIANS);

        area = (b - a) * ((fOfA + fOfB) / 2);
        return area;
    }

    public double trapezoidalMultiple(double a, double b, String function, int n) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        if (n < 1) {
            return Double.NaN;
        }
        double na = n;
        double h = (b - a) / na;
        double aAssis = a;
        double area = 0;
        double[] functionResult = new double[n + 1];
        double fOfX = 0;
        double summation = 0;

        for (int i = 0; i <= n; i++) {
            fOfX = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
            functionResult[i] = fOfX;
            aAssis += h;
        }

        for (int i = 1; i < n; i++) {
            summation += functionResult[i];
        }
        double num = functionResult[0] + 2 * summation + functionResult[n];
        area = (b - a) * (num / (2 * na));
        return area;
    }

    public double simpsonTrapezoidal(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double h = (b - a) / 2;
        double aAssis = a;
        double area = 0;
        double fx0 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx1 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx2 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);

        area = (h / 3) * (fx0 + 4 * fx1 + fx2);
        return area;
    }

    public double simpsonThirdMultiple(double a, double b, String function, int n) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        if (n < 1) {
            return Double.NaN;
        }
        double h = (b - a) / n;
        double area = 0;
        double aAssis = a;
        double[] functionResult = new double[n + 1];
        double fX = 0;
        double addPair = 0;
        double addodd = 0;

        for (int i = 0; i <= n; i++) {
            fX = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
            aAssis += h;
            functionResult[i] = fX;
        }

        for (int i = 1; i < n; i += 2) {
            addodd += functionResult[i];
        }

        for (int i = 2; i < n; i += 2) {
            addPair += functionResult[i];
        }

        double num = functionResult[0] + 4 * addodd + 2 * addPair + functionResult[n];
        area = (b - a) * (num / (3 * n));
        return area;
    }

    public double simpsonThreeEighths(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double h = (b - a) / 3;
        double aAssis = a;
        double area = 0;

        double fx0 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx1 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx2 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx3 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);

        double num = fx0 + 3 * fx1 + 3 * fx2 + fx3;
        area = (b - a) * (num / 8);
        return area;
    }

    public double boole5Points(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double h = (b - a) / 4;
        double aAssis = a;
        double area = 0;

        double fx0 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx1 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx2 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx3 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx4 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);

        double num = 7 * fx0 + 32 * fx1 + 12 * fx2 + 32 * fx3 + 7 * fx4;
        area = (b - a) * (num / 90);
        return area;
    }

    public double boole6Points(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double h = (b - a) / 5;
        double aAssis = a;
        double area = 0;

        double fx0 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx1 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx2 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx3 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx4 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
        aAssis += h;
        double fx5 = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);

        double num = 19 * fx0 + 75 * fx1 + 50 * fx2 + 50 * fx3 + 75 * fx4 + 19 * fx5;
        area = (b - a) * (num / 288);
        return area;
    }

}
