package droidmath.calculus;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;

public class GaussLegendreIntegration {

    private Evaluate evalua = new Evaluate();
    private Assistant assistant = new Assistant();

    //Integral Gauss-Legendre
    public double gaussLegendre2(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double area = 0;
        double x = 0;
        double dx = 0;
        double fOfX0 = 0, fOfX1 = 0;
        ///weighting factors
        double c0 = 1, c1 = 1;
        ///function arguments
        double x0 = -0.577350269, x1 = 0.577350269;
        //make change in x variable
        x = ((b + a) / 2) + ((b - a) / 2) * x0;
        dx = ((b - a) / 2);
        fOfX0 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x1;
        dx = ((b - a) / 2);
        fOfX1 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        area = c0 * fOfX0 + c1 * fOfX1;
        return area;
    }

    public double gaussLegendre3(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double area = 0;
        double x = 0;
        double dx = 0;
        double fOfX0 = 0, fOfX1 = 0, fOfX2 = 0;
        ///weighting factors
        double c0 = 0.5555556, c1 = 0.8888889, c2 = c0;
        ///function arguments
        double x0 = -0.774596669, x1 = 0, x2 = -x0;
        //make change in x variable
        x = ((b + a) / 2) + ((b - a) / 2) * x0;
        dx = ((b - a) / 2);
        fOfX0 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x1;
        dx = ((b - a) / 2);
        fOfX1 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x2;
        dx = ((b - a) / 2);
        fOfX2 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        area = c0 * fOfX0 + c1 * fOfX1 + c2 * fOfX2;
        return area;
    }

    public double gaussLegendre4(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double area = 0;
        double x = 0;
        double dx = 0;
        double fOfX0 = 0, fOfX1 = 0, fOfX2 = 0, fOfX3 = 0;
        ///weighting factors
        double c0 = 0.3478548, c1 = 0.6521452, c2 = c1, c3 = c0;
        ///function arguments
        double x0 = -0.861136312, x1 = -0.339981044, x2 = -x1, x3 = -x0;
        //make change in x variable
        x = ((b + a) / 2) + ((b - a) / 2) * x0;
        dx = ((b - a) / 2);
        fOfX0 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x1;
        dx = ((b - a) / 2);
        fOfX1 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x2;
        dx = ((b - a) / 2);
        fOfX2 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x3;
        dx = ((b - a) / 2);
        fOfX3 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        area = c0 * fOfX0 + c1 * fOfX1 + c2 * fOfX2 + c3 * fOfX3;
        return area;
    }

    public double gaussLegendre5(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double area = 0;
        double x = 0;
        double dx = 0;
        double fOfX0 = 0, fOfX1 = 0, fOfX2 = 0, fOfX3 = 0, fOfX4 = 0;
        ///weighting factors
        double c0 = 0.2369269, c1 = 0.4786287, c2 = 0.5688889, c3 = c1, c4 = c0;
        ///function arguments
        double x0 = -0.906179846, x1 = -0.53846931, x2 = 0, x3 = -x1, x4 = -x0;
        //make change in x variable
        x = ((b + a) / 2) + ((b - a) / 2) * x0;
        dx = ((b - a) / 2);
        fOfX0 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x1;
        dx = ((b - a) / 2);
        fOfX1 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x2;
        dx = ((b - a) / 2);
        fOfX2 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x3;
        dx = ((b - a) / 2);
        fOfX3 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x4;
        dx = ((b - a) / 2);
        fOfX4 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        area = c0 * fOfX0 + c1 * fOfX1 + c2 * fOfX2 + c3 * fOfX3 + c4 * fOfX4;
        return area;
    }

    public double gaussLegendre6(double a, double b, String function) {
        if(assistant.validateFunction(function) == false){
            return Double.NaN;
        }
        if (b <= a) {
            return Double.NaN;
        }
        double area = 0;
        double x = 0;
        double dx = 0;
        double fOfX0 = 0, fOfX1 = 0, fOfX2 = 0, fOfX3 = 0, fOfX4 = 0, fOfX5 = 0;
        ///weighting factors
        double c0 = 0.1713245, c1 = 0.3607616, c2 = 0.4679139, c3 = c2, c4 = c1, c5 = c0;
        ///function arguments
        double x0 = -0.932469514, x1 = -0.661209386, x2 = -0.238619186, x3 = -x2, x4 = -x1, x5 = -x0;
        //make change in x variable
        x = ((b + a) / 2) + ((b - a) / 2) * x0;
        dx = ((b - a) / 2);
        fOfX0 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x1;
        dx = ((b - a) / 2);
        fOfX1 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x2;
        dx = ((b - a) / 2);
        fOfX2 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x3;
        dx = ((b - a) / 2);
        fOfX3 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x4;
        dx = ((b - a) / 2);
        fOfX4 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        x = ((b + a) / 2) + ((b - a) / 2) * x5;
        dx = ((b - a) / 2);
        fOfX5 = evalua.solveOneVariable(function, x, Evaluate.RADIANS) * dx;

        area = c0 * fOfX0 + c1 * fOfX1 + c2 * fOfX2 + c3 * fOfX3 + c4 * fOfX4 + c5 * fOfX5;
        return area;
    }

}
