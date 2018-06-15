package droidmath.calculus;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;

public class Derivatives {

    private Evaluate evalua = new Evaluate();
    private Assistant assistant = new Assistant();

    //progresive differences formulas (forward) of order O(h^2)
    public double firstDifferenceForward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xPlusONE = x + h;
        double xPlusTWO = xPlusONE + h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        result = (-3 * fOfXZERO + 4 * fOfXPlusONE - fOfXPlusTWO) / (2 * h);
        return result;
    }

    public double secondDifferenceForward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xPlusONE = x + h;
        double xPlusTWO = xPlusONE + h;
        double xPlusTHREE = xPlusTWO + h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        double fOfXPlusTHREE = evalua.solveOneVariable(function, xPlusTHREE, Evaluate.RADIANS);
        result = (2 * fOfXZERO - 5 * fOfXPlusONE + 4 * fOfXPlusTWO - fOfXPlusTHREE) / (Math.pow(h, 2));
        return result;
    }

    public double thirdDifferenceForward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xPlusONE = x + h;
        double xPlusTWO = xPlusONE + h;
        double xPlusTHREE = xPlusTWO + h;
        double xPlusFOUR = xPlusTHREE + h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        double fOfXPlusTHREE = evalua.solveOneVariable(function, xPlusTHREE, Evaluate.RADIANS);
        double fOfXPlusFOUR = evalua.solveOneVariable(function, xPlusFOUR, Evaluate.RADIANS);
        result = (-5 * fOfXZERO + 18 * fOfXPlusONE - 24 * fOfXPlusTWO + 14 * fOfXPlusTHREE - 3 * fOfXPlusFOUR) / (2 * Math.pow(h, 3));
        return result;
    }

    public double fourthDifferenceForward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xPlusONE = x + h;
        double xPlusTWO = xPlusONE + h;
        double xPlusTHREE = xPlusTWO + h;
        double xPlusFOUR = xPlusTHREE + h;
        double xPlusFIVE = xPlusFOUR + h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        double fOfXPlusTHREE = evalua.solveOneVariable(function, xPlusTHREE, Evaluate.RADIANS);
        double fOfXPlusFOUR = evalua.solveOneVariable(function, xPlusFOUR, Evaluate.RADIANS);
        double fOfXPlusFIVE = evalua.solveOneVariable(function, xPlusFIVE, Evaluate.RADIANS);
        result = (3 * fOfXZERO - 14 * fOfXPlusONE + 26 * fOfXPlusTWO - 24 * fOfXPlusTHREE + 11 * fOfXPlusFOUR - 2 * fOfXPlusFIVE) / (Math.pow(h, 4));
        return result;
    }

    //regressive differences formulas (backward) of order O(h^2)
    public double firstDifferenceBackward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = x - h;
        double xMinusTWO = xMinusONE - h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        result = (3 * fOfXZERO - 4 * fOfXMinusONE + fOfXMinusTWO) / (2 * h);
        return result;
    }

    public double secondDifferenceBackward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = x - h;
        double xMinusTWO = xMinusONE - h;
        double xMinusTHREE = xMinusTWO - h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusTHREE = evalua.solveOneVariable(function, xMinusTHREE, Evaluate.RADIANS);
        result = (2 * fOfXZERO - 5 * fOfXMinusONE + 4 * fOfXMinusTWO - fOfXMinusTHREE) / (Math.pow(h, 2));
        return result;
    }

    public double thirdDifferenceBackward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = x - h;
        double xMinusTWO = xMinusONE - h;
        double xMinusTHREE = xMinusTWO - h;
        double xMinusFOUR = xMinusTHREE - h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusTHREE = evalua.solveOneVariable(function, xMinusTHREE, Evaluate.RADIANS);
        double fOfXMinusFOUR = evalua.solveOneVariable(function, xMinusFOUR, Evaluate.RADIANS);
        result = (5 * fOfXZERO - 18 * fOfXMinusONE + 24 * fOfXMinusTWO - 14 * fOfXMinusTHREE + 3 * fOfXMinusFOUR) / (2 * Math.pow(h, 3));
        return result;
    }

    public double fourthDifferenceBackward(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = x - h;
        double xMinusTWO = xMinusONE - h;
        double xMinusTHREE = xMinusTWO - h;
        double xMinusFOUR = xMinusTHREE - h;
        double xMinusFIVE = xMinusFOUR - h;
        //Evaluate functions
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusTHREE = evalua.solveOneVariable(function, xMinusTHREE, Evaluate.RADIANS);
        double fOfXMinusFOUR = evalua.solveOneVariable(function, xMinusFOUR, Evaluate.RADIANS);
        double fOfXMinusFIVE = evalua.solveOneVariable(function, xMinusFIVE, Evaluate.RADIANS);
        result = (3 * fOfXZERO - 14 * fOfXMinusONE + 26 * fOfXMinusTWO - 24 * fOfXMinusTHREE + 11 * fOfXMinusFOUR - 2 * fOfXMinusFIVE) / (Math.pow(h, 4));
        return result;
    }

    //centered differences formulas of order O(h^2)
    public double firstDifferenceCentered(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xPlusONE = xZERO + h;

        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        result = (fOfXPlusONE - fOfXMinusONE) / (2 * h);
        return result;
    }

    public double secondDifferenceCentered(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xPlusONE = xZERO + h;
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        result = (fOfXPlusONE - 2 * fOfXZERO + fOfXMinusONE) / (Math.pow(h, 2));
        return result;
    }

    public double thirdDifferenceCentered(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xMinusTWO = xMinusONE - h;
        double xPlusONE = xZERO + h;
        double xPlusTWO = xPlusONE + h;
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        result = (fOfXPlusTWO - fOfXPlusONE + 2 * fOfXMinusONE - fOfXMinusTWO) / (2 * Math.pow(h, 3));
        return result;
    }

    public double fourthDifferenceCentered(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xMinusTWO = xMinusONE - h;
        double xPlusONE = xZERO + h;
        double xPlusTWO = xPlusONE + h;
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        result = (fOfXPlusTWO - 4 * fOfXPlusONE + 6 * fOfXZERO - 4 * fOfXMinusONE + fOfXMinusTWO) / (Math.pow(h, 4));
        return result;
    }

    //centered differences formulas of order O(h^4)
    public double firstDifferenceCentered4(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xMinusTWO = xMinusONE - h;
        double xPlusONE = xZERO + h;
        double xPlusTWO = xPlusONE + h;
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        result = (-fOfXPlusTWO + 8 * fOfXPlusONE - 8 * fOfXMinusONE + fOfXMinusTWO) / (12 * h);
        return result;
    }

    public double secondDifferenceCentered4(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xMinusTWO = xMinusONE - h;
        double xPlusONE = xZERO + h;
        double xPlusTWO = xPlusONE + h;
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        result = (-fOfXPlusTWO + 16 * fOfXPlusONE - 30 * fOfXZERO + 16 * fOfXMinusONE - fOfXMinusTWO) / (12 * Math.pow(h, 2));
        return result;
    }

    public double thirdDifferenceCentered4(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xMinusTWO = xMinusONE - h;
        double xMinusTHREE = xMinusTWO - h;
        double xPlusONE = xZERO + h;
        double xPlusTWO = xPlusONE + h;
        double xPlusTHREE = xPlusTWO + h;
        double fOfXMinusTHREE = evalua.solveOneVariable(function, xMinusTHREE, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        double fOfXPlusTHREE = evalua.solveOneVariable(function, xPlusTHREE, Evaluate.RADIANS);
        result = (-fOfXPlusTHREE + 8 * fOfXPlusTWO - 13 * fOfXPlusONE + 13 * fOfXMinusONE - 8 * fOfXMinusTWO + fOfXMinusTHREE) / (8 * Math.pow(h, 3));
        return result;
    }

    public double fourthDifferenceCentered4(double h, double x, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        double result = 0;
        double xZERO = x;
        double xMinusONE = xZERO - h;
        double xMinusTWO = xMinusONE - h;
        double xMinusTHREE = xMinusTWO - h;
        double xPlusONE = xZERO + h;
        double xPlusTWO = xPlusONE + h;
        double xPlusTHREE = xPlusTWO + h;
        double fOfXZERO = evalua.solveOneVariable(function, xZERO, Evaluate.RADIANS);
        double fOfXMinusTHREE = evalua.solveOneVariable(function, xMinusTHREE, Evaluate.RADIANS);
        double fOfXMinusTWO = evalua.solveOneVariable(function, xMinusTWO, Evaluate.RADIANS);
        double fOfXMinusONE = evalua.solveOneVariable(function, xMinusONE, Evaluate.RADIANS);
        double fOfXPlusONE = evalua.solveOneVariable(function, xPlusONE, Evaluate.RADIANS);
        double fOfXPlusTWO = evalua.solveOneVariable(function, xPlusTWO, Evaluate.RADIANS);
        double fOfXPlusTHREE = evalua.solveOneVariable(function, xPlusTHREE, Evaluate.RADIANS);
        result = (-fOfXPlusTHREE + 12 * fOfXPlusTWO - 39 * fOfXPlusONE + 56 * fOfXZERO - 39 * fOfXMinusONE + 12 * fOfXMinusTWO - fOfXMinusTHREE) / (6 * Math.pow(h, 4));
        return result;
    }

}
