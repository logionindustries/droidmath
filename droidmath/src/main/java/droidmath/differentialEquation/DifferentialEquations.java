package droidmath.differentialEquation;

import java.util.LinkedList;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;

public class DifferentialEquations {

    private Evaluate evalua = new Evaluate();
    private Assistant assistant = new Assistant();

    public DifferentialEquations() {
        String[] var1 = {"x"};
        String[] var2 = {"y"};
        evalua.addAcceptableFirstDependency(var1);
        evalua.addAcceptableSecondDependency(var2);
    }

    //Euler method
    public String euler(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                        String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }
        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();
        String result = "";
        double y = 0;
        double derivative = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            derivative = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
            y = yIni - derivative * h;
            result = "x = " + i + " y = " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            xIni -= h;
            yIni = y;
            cond++;
        }
        listLeft = flip(listLeftAssis);
        result = "";
        y = 0;
        derivative = 0;
        xIni = xIniAssis;
        yIni = yIniAssis;
        //Right
        for (double i = xIni; i <= xF; i += h) {
            derivative = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
            y = yIni + derivative * h;
            result = "x = " + i + " y = " + yIni;
            listRight.add(result);
            xIni += h;
            yIni = y;
        }
        return join(listLeft, listRight);
    }

    //Heun method
    public String heun(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                       String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }
        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();
        double yiPlus1 = 0;
        double derivative = 0, derivative2 = 0;
        double yAssis = 0;
        String result = "";
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            derivative = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
            yAssis = yIni - derivative * h;
            xIni -= h;
            derivative2 = evalua.solveTwoVariables(function, xIni, yAssis, Evaluate.RADIANS);
            yiPlus1 = yIni - ((derivative + derivative2) / 2) * h;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yiPlus1;
        }
        listLeft = flip(listLeftAssis);
        yiPlus1 = 0;
        derivative = 0;
        derivative2 = 0;
        yAssis = 0;
        result = "";
        xIni = xIniAssis;
        yIni = yIniAssis;
        //Right
        for (double i = xIni; i <= xF; i += h) {
            derivative = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
            yAssis = yIni + derivative * h;
            xIni += h;
            derivative2 = evalua.solveTwoVariables(function, xIni, yAssis, Evaluate.RADIANS);
            yiPlus1 = yIni + ((derivative + derivative2) / 2) * h;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yiPlus1;
        }
        return join(listLeft, listRight);
    }

    //Heun method with iterations
    public String heunIteration(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                                int iteration, String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();
        double yiPlus1 = 0;
        String result = "";
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            yiPlus1 = yiPrevious(xIni, yIni, h, iteration, function);
            xIni -= h;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yiPlus1;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        yiPlus1 = 0;
        result = "";
        //Right
        for (double i = xIni; i <= xF; i += h) {
            yiPlus1 = yiNext(xIni, yIni, h, iteration, function);
            xIni += h;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yiPlus1;
        }
        return join(listLeft, listRight);
    }

    private double yiNext(double xIni, double yIni, double h, int iteraciones,
                          String function) {
        double y = 0;
        double x = xIni + h;
        double derivative = 0;
        double derivative2 = 0;
        double yAssis = 0;
        double yAssis2 = 0;

        double functionValue = 0;
        int i = 0;

        //Step for calculate the approximate y value
        derivative = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
        yAssis = yIni + derivative * h;
        derivative2 = evalua.solveTwoVariables(function, x, yAssis, Evaluate.RADIANS);
        yAssis2 = yIni + ((derivative + derivative2) / 2) * h;

        i++;
        //Step for calculate the approximate y value with iterations
        while (i < iteraciones) {
            functionValue = evalua.solveTwoVariables(function, x, yAssis2, Evaluate.RADIANS);
            y = yIni + ((derivative + functionValue) / 2) * h;
            yAssis2 = y;
            i++;
        }
        return y;
    }

    private double yiPrevious(double xIni, double yIni, double h, int iteraciones,
                              String function) {
        double y = 0;
        double x = xIni - h;
        double derivative = 0;
        double derivative2 = 0;
        double yAssis = 0;
        double yAssis2 = 0;

        double functionValue = 0;
        int i = 0;

        //Step for calculate the approximate y value
        derivative = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
        yAssis = yIni - derivative * h;
        derivative2 = evalua.solveTwoVariables(function, x, yAssis, Evaluate.RADIANS);
        yAssis2 = yIni - ((derivative + derivative2) / 2) * h;

        i++;
        //Step for calculate the approximate y value with iterations
        while (i < iteraciones) {
            functionValue = evalua.solveTwoVariables(function, x, yAssis2, Evaluate.RADIANS);
            y = yIni - ((derivative + functionValue) / 2) * h;
            yAssis2 = y;
            i++;
        }
        return y;
    }

    //Runge Kutta methods
    public String midPoint(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                           String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);
            xAssis = xIni - (h / 2);
            yAssis = yIni - ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - k2 * h;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        result = "";
        yPlusOne = 0;
        k1 = k2 = 0;
        xAssis = 0;
        yAssis = 0;

        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni + ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + k2 * h;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    public String ralston(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                          String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (3 * h / 4);
            yAssis = yIni - ((3 * k1 * h) / 4);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((k1 / 3) + (2 * k2 / 3)) * h;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        yPlusOne = 0;
        k1 = k2 = 0;
        xAssis = 0;
        yAssis = 0;
        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (3 * h / 4);
            yAssis = yIni + ((3 * k1 * h) / 4);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((k1 / 3) + (2 * k2 / 3)) * h;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    public String rungeKutta3(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                              String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0, k3 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (h / 2);
            yAssis = yIni - ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - h;
            yAssis = yIni + k1 * h + 2 * k2 * h;
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((k1 + 4 * k2 + k3) * h) / 6;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        result = "";
        yPlusOne = 0;
        k1 = 0;
        k2 = 0;
        k3 = 0;
        xAssis = 0;
        yAssis = 0;
        //right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni + ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + h;
            yAssis = yIni - k1 * h + 2 * k2 * h;
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((k1 + 4 * k2 + k3) * h) / 6;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    public String rungeKutta4(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                              String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0, k3 = 0, k4 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (h / 2);
            yAssis = yIni - ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (h / 2);
            yAssis = yIni - ((k2 * h) / 2);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - h;
            yAssis = yIni - k3 * h;
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((k1 + 2 * k2 + 2 * k3 + k4) * h) / 6;
            result = "x = " + i + " y = " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        yPlusOne = 0;
        xAssis = 0;
        yAssis = 0;
        k1 = k2 = k3 = k4 = 0;
        result = "";
        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni + ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni + ((k2 * h) / 2);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + h;
            yAssis = yIni + k3 * h;
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((k1 + 2 * k2 + 2 * k3 + k4) * h) / 6;
            result = "x = " + i + " y = " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    public double[][] rungeKutta4Vector(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                                        String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        double[] vLeft = null;
        double[] vRight = null;
        double[] vLeftX = null;
        double[] vRightX = null;

        String result = "";
        String resultX = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0, k3 = 0, k4 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (h / 2);
            yAssis = yIni - ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (h / 2);
            yAssis = yIni - ((k2 * h) / 2);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - h;
            yAssis = yIni - k3 * h;
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((k1 + 2 * k2 + 2 * k3 + k4) * h) / 6;

            if (cond > 0) {
                result += yIni + ",";
                resultX += i + ",";
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        vLeft = flip(vector(result));
        vLeftX = flip(vector(resultX));
        xIni = xIniAssis;
        yIni = yIniAssis;
        yPlusOne = 0;
        xAssis = 0;
        yAssis = 0;
        k1 = k2 = k3 = k4 = 0;
        result = "";
        resultX = "";
        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni + ((k1 * h) / 2);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni + ((k2 * h) / 2);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + h;
            yAssis = yIni + k3 * h;
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((k1 + 2 * k2 + 2 * k3 + k4) * h) / 6;
            result += yIni + ",";
            resultX += i + ",";
            yIni = yPlusOne;
            xIni += h;
        }
        vRight = vector(result);
        vRightX = vector(resultX);
        double[][] vector = new double[2][];
        vector[0] = join(vLeftX, vRightX);
        vector[1] = join(vLeft, vRight);
        return vector;
    }

    public String rungeKutta5Butcher(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                                     String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0, k6 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (h / 4);
            yAssis = yIni - ((k1 * h) / 4);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (h / 4);
            yAssis = yIni - ((k1 * h) / 8) - ((k2 * h) / 8);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (h / 2);
            yAssis = yIni + ((k2 * h) / 2) - k3 * h;
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (3 * h / 4);
            yAssis = yIni - ((3 * k1 * h) / 16) - ((9 * k4 * h) / 16);
            k5 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - h;
            yAssis = yIni + ((3 * k1 * h) / 7) - ((2 * k2 * h) / 7) - ((12 * k3 * h) / 7) + ((12 * k4 * h) / 7)
                    - ((8 * k5 * h) / 7);
            k6 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((7 * k1 + 32 * k3 + 12 * k4 + 32 * k5 + 7 * k6) * h) / 90;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        yPlusOne = 0;
        k1 = k2 = k3 = k4 = k5 = k6 = 0;
        xAssis = 0;
        yAssis = 0;
        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 4);
            yAssis = yIni + ((k1 * h) / 4);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (h / 4);
            yAssis = yIni + ((k1 * h) / 8) + ((k2 * h) / 8);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (h / 2);
            yAssis = yIni - ((k2 * h) / 2) + k3 * h;
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (3 * h / 4);
            yAssis = yIni + ((3 * k1 * h) / 16) + ((9 * k4 * h) / 16);
            k5 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + h;
            yAssis = yIni - ((3 * k1 * h) / 7) + ((2 * k2 * h) / 7) + ((12 * k3 * h) / 7) - ((12 * k4 * h) / 7)
                    + ((8 * k5 * h) / 7);
            k6 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((7 * k1 + 32 * k3 + 12 * k4 + 32 * k5 + 7 * k6) * h) / 90;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    public String rungeKuttaFehlberg4(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                                      String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0, k6 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (h / 5);
            yAssis = yIni - ((k1 * h) / 5);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (3 * h / 10);
            yAssis = yIni - ((3 * k1 * h) / 40) - ((9 * k2 * h) / 40);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (3 * h / 5);
            yAssis = yIni - ((3 * k1 * h) / 10) + ((9 * k2 * h) / 10) - ((6 * k3 * h) / 5);
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - h;
            yAssis = yIni + ((11 * k1 * h) / 54) - ((5 * k2 * h) / 2) + ((70 * k3 * h) / 27) - ((35 * k4 * h) / 27);
            k5 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (7 * h / 8);
            yAssis = yIni - ((1631 * k1 * h) / 55296) - ((175 * k2 * h) / 512) - ((575 * k3 * h) / 13824)
                    - ((44275 * k4 * h) / 110592) - ((253 * k5 * h) / 4096);
            k6 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((37 * k1 / 378) + (250 * k3 / 621) + (125 * k4 / 594)
                    + (512 * k6 / 1771)) * h;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        xIni = xIniAssis;
        yIni = yIniAssis;
        yPlusOne = 0;
        k1 = k2 = k3 = k4 = k5 = k6 = 0;
        result = "";
        xAssis = 0;
        yAssis = 0;

        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 5);
            yAssis = yIni + ((k1 * h) / 5);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (3 * h / 10);
            yAssis = yIni + ((3 * k1 * h) / 40) + ((9 * k2 * h) / 40);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (3 * h / 5);
            yAssis = yIni + ((3 * k1 * h) / 10) - ((9 * k2 * h) / 10) + ((6 * k3 * h) / 5);
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + h;
            yAssis = yIni - ((11 * k1 * h) / 54) + ((5 * k2 * h) / 2) - ((70 * k3 * h) / 27) + ((35 * k4 * h) / 27);
            k5 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (7 * h / 8);
            yAssis = yIni + ((1631 * k1 * h) / 55296) + ((175 * k2 * h) / 512) + ((575 * k3 * h) / 13824)
                    + ((44275 * k4 * h) / 110592) + ((253 * k5 * h) / 4096);
            k6 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((37 * k1 / 378) + (250 * k3 / 621) + (125 * k4 / 594)
                    + (512 * k6 / 1771)) * h;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    public String rungeKuttaFehlberg5(double xInitialCondition, double yInitialCondition, double h, double xI, double xF,
                                      String function) {
        if (xInitialCondition < xI) {
            return null;
        }
        if (xInitialCondition >= xF) {
            return null;
        }
        if (xF < xI) {
            return null;
        }
        if(assistant.validateFunction(function) == false){
            return null;
        }

        double xIni = xInitialCondition;
        double yIni = yInitialCondition;
        double xIniAssis = xIni;
        double yIniAssis = yIni;
        LinkedList listLeft = new LinkedList();
        LinkedList listLeftAssis = new LinkedList();
        LinkedList listRight = new LinkedList();

        String result = "";
        double yPlusOne = 0;
        double k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0, k6 = 0;
        double xAssis = 0, yAssis = 0;
        int cond = 0;
        //Left
        for (double i = xIni; i >= xI; i -= h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni - (h / 5);
            yAssis = yIni - ((k1 * h) / 5);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (3 * h / 10);
            yAssis = yIni - ((3 * k1 * h) / 40) - ((9 * k2 * h) / 40);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (3 * h / 5);
            yAssis = yIni - ((3 * k1 * h) / 10) + ((9 * k2 * h) / 10) - ((6 * k3 * h) / 5);
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - h;
            yAssis = yIni + ((11 * k1 * h) / 54) - ((5 * k2 * h) / 2) + ((70 * k3 * h) / 27) - ((35 * k4 * h) / 27);
            k5 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni - (7 * h / 8);
            yAssis = yIni - ((1631 * k1 * h) / 55296) - ((175 * k2 * h) / 512) - ((575 * k3 * h) / 13824)
                    - ((44275 * k4 * h) / 110592) - ((253 * k5 * h) / 4096);
            k6 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni - ((2825 * k1 / 27648) + (18575 * k3 / 43384) + (13525 * k4 / 55296)
                    + (277 * k5 / 14336) + (k6 / 4)) * h;
            result = "x= " + i + " y= " + yIni;
            if (cond > 0) {
                listLeftAssis.add(result);
            }
            cond++;
            yIni = yPlusOne;
            xIni -= h;
        }
        listLeft = flip(listLeftAssis);
        yPlusOne = 0;
        xIni = xIniAssis;
        yIni = yIniAssis;
        xAssis = 0;
        yAssis = 0;
        k1 = k2 = k3 = k4 = k5 = k6 = 0;
        result = "";
        //Right
        for (double i = xIni; i <= xF; i += h) {
            k1 = evalua.solveTwoVariables(function, xIni, yIni, Evaluate.RADIANS);

            xAssis = xIni + (h / 5);
            yAssis = yIni + ((k1 * h) / 5);
            k2 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (3 * h / 10);
            yAssis = yIni + ((3 * k1 * h) / 40) + ((9 * k2 * h) / 40);
            k3 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (3 * h / 5);
            yAssis = yIni + ((3 * k1 * h) / 10) - ((9 * k2 * h) / 10) + ((6 * k3 * h) / 5);
            k4 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + h;
            yAssis = yIni - ((11 * k1 * h) / 54) + ((5 * k2 * h) / 2) - ((70 * k3 * h) / 27) + ((35 * k4 * h) / 27);
            k5 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            xAssis = xIni + (7 * h / 8);
            yAssis = yIni + ((1631 * k1 * h) / 55296) + ((175 * k2 * h) / 512) + ((575 * k3 * h) / 13824)
                    + ((44275 * k4 * h) / 110592) + ((253 * k5 * h) / 4096);
            k6 = evalua.solveTwoVariables(function, xAssis, yAssis, Evaluate.RADIANS);

            yPlusOne = yIni + ((2825 * k1 / 27648) + (18575 * k3 / 43384) + (13525 * k4 / 55296)
                    + (277 * k5 / 14336) + (k6 / 4)) * h;
            result = "x= " + i + " y= " + yIni;
            listRight.add(result);
            yIni = yPlusOne;
            xIni += h;
        }
        return join(listLeft, listRight);
    }

    private double[] vector(String strNumbers) {
        double[] v = new double[count(strNumbers)];
        String store = "";
        int count = 0;
        for (int i = 0; i < strNumbers.length(); i++) {
            if (strNumbers.charAt(i) != ',') {
                store += strNumbers.charAt(i);
            } else {
                v[count] = Double.parseDouble(store);
                store = "";
                count++;
            }
        }
        return v;
    }

    public double[] flip(double[] vec) {
        double[] v = new double[vec.length];
        int c = 0;
        for (int i = vec.length - 1; i >= 0; i--) {
            v[c] = vec[i];
            c++;
        }
        return v;
    }

    private int count(String strNumbers) {
        int size = 0;
        for (int i = 0; i < strNumbers.length(); i++) {
            if (strNumbers.charAt(i) == ',') {
                size++;
            }
        }
        return size;
    }

    private double[] join(double[] v1, double[] v2) {
        double[] vec = new double[v1.length + v2.length];
        int c = 0;
        for (int i = 0; i < v1.length; i++) {
            vec[c] = v1[i];
            c++;
        }
        for (int i = 0; i < v2.length; i++) {
            vec[c] = v2[i];
            c++;
        }
        return vec;
    }

    private LinkedList flip(LinkedList<String> l) {
        LinkedList list = new LinkedList();
        for (int i = l.size() - 1; i >= 0; i--) {
            String v = l.get(i);
            list.add(v);
        }
        return list;
    }

    private String join(LinkedList<String> l1, LinkedList<String> l2) {
        String result = "";
        for (int i = 0; i < l1.size(); i++) {
            result += l1.get(i) + "\n";
        }
        for (int i = 0; i < l2.size() - 1; i++) {
            result += l2.get(i) + "\n";
        }
        result += l2.get(l2.size() - 1) + "\n";
        return result;
    }

}
