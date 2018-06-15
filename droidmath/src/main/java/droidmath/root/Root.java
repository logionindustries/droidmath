package droidmath.root;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;
import droidmath.calculus.Derivatives;

public class Root {

    private Evaluate evalua = new Evaluate();
    private Derivatives der = new Derivatives();
    private Assistant assistant = new Assistant();

    public String bisection(double xi, double xu, double error, String function) {
        if(assistant.validateFunction(function) == false){
            return null;
        }
        if (xu <= xi) {
            return null;
        }
        String result = "";
        String store = "";
        int iteration = 1;
        double xLower = xi;
        double xHigher = xu;
        double xRoot = 0;
        double fOfXLower = 0;
        double fOfXHigher = 0;
        double fOfXRoot = 0;
        boolean existentRoot = false;
        double acceptableError = 100;//100 represent at 100%
        double xAssisPrevious = 0;
        fOfXLower = evalua.solveOneVariable(function, xLower, Evaluate.RADIANS);
        fOfXHigher = evalua.solveOneVariable(function, xHigher, Evaluate.RADIANS);

        if (fOfXLower * fOfXHigher < 0) {
            existentRoot = true;
        } else {
            existentRoot = false;
        }
        if (existentRoot == true) {
            while (acceptableError > error) {
                xRoot = (xLower + xHigher) / 2;
                fOfXLower = evalua.solveOneVariable(function, xLower, Evaluate.RADIANS);
                fOfXRoot = evalua.solveOneVariable(function, xRoot, Evaluate.RADIANS);
                if (fOfXLower * fOfXRoot < 0) {
                    xHigher = xRoot;
                } else if (fOfXLower * fOfXRoot > 0) {
                    xLower = xRoot;
                }
                //calculate error
                acceptableError = 100 * Math.abs((xRoot - xAssisPrevious) / xRoot);
                xAssisPrevious = xRoot;
                store += "Iteration: " + iteration + "---> x= " + xRoot + " error= " + acceptableError + "%";
                result += store + "\n";
                store = "";
                iteration++;
            }
        } else if (existentRoot == false) { //dont exist root
            result = "THERE IS NO ROOT AT THE INDICATED INTERVAL";
        }
        return result;
    }

    public String linearInterpolation(double xi, double xu, double error, String function) {
        if(assistant.validateFunction(function) == false){
            return null;
        }
        if (xu <= xi) {
            return null;
        }
        String result = "";
        String store = "";
        int iteration = 1;
        double xLower = xi;
        double xHigher = xu;
        double xRoot = 0;
        double fOfXLower = 0;
        double fOfXHigher = 0;
        double fOfXRoot = 0;
        boolean existentRoot = false;
        double acceptableError = 100;
        double xAssisPrevious = 0;
        fOfXLower = evalua.solveOneVariable(function, xLower, Evaluate.RADIANS);
        fOfXHigher = evalua.solveOneVariable(function, xHigher, Evaluate.RADIANS);

        if (fOfXLower * fOfXHigher < 0) {
            existentRoot = true;
        } else {
            existentRoot = false;
        }

        if (existentRoot == true) {
            while (acceptableError > error) {
                xRoot = xHigher - ((fOfXHigher * (xLower - xHigher)) / (fOfXLower - fOfXHigher));
                fOfXRoot = evalua.solveOneVariable(function, xRoot, Evaluate.RADIANS);
                if (fOfXLower * fOfXRoot < 0) {
                    xHigher = xRoot;
                } else if (fOfXLower * fOfXRoot > 0) {
                    xLower = xRoot;
                }
                fOfXLower = evalua.solveOneVariable(function, xLower, Evaluate.RADIANS);
                fOfXHigher = evalua.solveOneVariable(function, xHigher, Evaluate.RADIANS);
                //calculate error
                acceptableError = 100 * Math.abs((xRoot - xAssisPrevious) / xRoot);
                xAssisPrevious = xRoot;
                store += "Iteration: " + iteration + "---> x= " + xRoot
                        + " error= " + acceptableError + "%";
                result += store + "\n";
                store = "";
                iteration++;
            }
        } else if (existentRoot == false) {
            result = "THERE IS NO ROOT AT THE INDICATED INTERVAL";
        }
        return result;
    }

    public String newtonRaphson(double x1, int iterations, String function) {
        if(assistant.validateFunction(function) == false){
            return null;
        }
        if (iterations <= 0) {
            return null;
        }
        String store = "";
        String nR = "";
        double x = x1;
        double xAssis = x;
        double fOfX = 0;
        double derivativeFOfX = 0;
        for (int i = 0; i < iterations; i++) {
            fOfX = evalua.solveOneVariable(function, x, Evaluate.RADIANS);
            derivativeFOfX = der.firstDifferenceCentered4(0.01, x, function);
            x = x - (fOfX / derivativeFOfX);
            store = "x" + (i + 1) + "= " + xAssis;
            nR += store + "\n";
            store = "";
            xAssis = x;
        }
        return nR;
    }

    public String secant(double xiPrevious, double xiCurrent, double error, String function) {
        if(assistant.validateFunction(function) == false){
            return null;
        }
        String result = "";
        String store = "";
        double xPrevious = xiPrevious;
        double xCurrent = xiCurrent;
        double xNext = 0;
        double fOfXPrevious = 0;
        double fOfXCurrent = 0;
        int iteration = 1;
        double acceptableError = 100;
        while (acceptableError > error) {
            fOfXPrevious = evalua.solveOneVariable(function, xPrevious, Evaluate.RADIANS);
            fOfXCurrent = evalua.solveOneVariable(function, xCurrent, Evaluate.RADIANS);
            xNext = xCurrent - ((fOfXCurrent * (xPrevious - xCurrent)) / (fOfXPrevious - fOfXCurrent));
            acceptableError = 100 * Math.abs((xNext - xCurrent) / xNext);
            xPrevious = xCurrent;
            xCurrent = xNext;
            store += "Iteration: " + iteration + "---> x= " + xNext + " error= " + acceptableError + "%";
            result += store + "\n";
            store = "";
            iteration++;
        }
        return result;
    }

    public String muller(double x0, double x1, double x2, double error, String function) {
        if(assistant.validateFunction(function) == false){
            return null;
        }

        String store = "";
        String result = "";
        double x3 = 0;
        double xZero = x0, xOne = x1, xTwo = x2;
        double fOfX0 = 0;
        double fOfX1 = 0;
        double fOfX2 = 0;
        double h0 = 0, h1 = 0;
        double delta0, delta1 = 0;
        double a = 0, b = 0, c = 0, d = 0;
        double determinant = 0;
        double acceptableError = 100;
        int iteration = 1;

        while (acceptableError > error) {
            fOfX0 = evalua.solveOneVariable(function, xZero, Evaluate.RADIANS);
            fOfX1 = evalua.solveOneVariable(function, xOne, Evaluate.RADIANS);
            fOfX2 = evalua.solveOneVariable(function, xTwo, Evaluate.RADIANS);

            h0 = xOne - xZero;
            h1 = xTwo - xOne;
            delta0 = (fOfX1 - fOfX0) / (h0);
            delta1 = (fOfX2 - fOfX1) / (h1);

            a = (delta1 - delta0) / (h1 + h0);
            b = a * h1 + delta1;
            c = fOfX2;

            determinant = Math.pow(b, 2) - 4 * a * c;
            if (determinant < 0) {
                result += "The roots are not real";
                acceptableError = error;
            } else {
                d = Math.sqrt(determinant);
                if (Math.abs(b + d) > Math.abs(b - d)) {
                    x3 = xTwo + ((-2 * c) / (b + d));
                } else {
                    x3 = xTwo + ((-2 * c) / (b - d));
                }
            }

            acceptableError = 100 * Math.abs((x3 - xTwo) / x3);
            store += "Iteration: " + iteration + "---> x= " + x3
                    + " error= " + acceptableError + "%";
            result += store + "\n";
            store = "";
            iteration++;
            xZero = xOne;
            xOne = xTwo;
            xTwo = x3;
        }
        return result;
    }

    public String bairstow(double r, double s, double error, String coeficients) {
        String store = "";
        String result = "";
        int iteration = 1;
        double root1 = 0;
        double root2 = 0;
        double det = 0;
        double R = r;
        double S = s;
        double deltaS = 0;
        double deltaR = 0;
        double c1 = 0, c2 = 0, c3 = 0, b0 = 0, b1 = 0;

        try {
            double[] coeficientsA = vectorCoeficients(assistant.clean(coeficients));
            double[] coeficientsB;
            double[] coeficientsC;
            double errorR = 100;//100%
            double errorS = 100;//100%

            while (errorR > error && errorS > error) {
                coeficientsB = coefBC(coeficientsA, R, S);
                coeficientsC = coefBC(coeficientsB, R, S);

                c1 = coeficientsC[coeficientsA.length - 2];
                c2 = coeficientsC[coeficientsA.length - 3];
                c3 = coeficientsC[coeficientsA.length - 4];
                b0 = coeficientsB[coeficientsA.length - 1];
                b1 = coeficientsB[coeficientsA.length - 2];

                deltaR = deltaRS(c1, c2, c3, b0, b1, 0);
                deltaS = deltaRS(c1, c2, c3, b0, b1, 1);

                R = R + deltaR;
                S = S + deltaS;
                errorR = 100 * Math.abs(deltaR / R);
                errorS = 100 * Math.abs(deltaS / S);
                store += "Iteration: " + iteration + "---> r= " + R + " s=" + S + " errorR= "
                        + errorR + " errorS= " + errorS + "%";
                result += store + "\n";
                store = "";
                iteration++;
            }

            det = Math.pow(R, 2) + 4 * S;
            if (det < 0) {
                result += "ROOTS:\n";
                result += "x= " + R / 2 + "+" + (Math.sqrt(Math.abs(det))) / 2 + "i" + "\n";
                result += "x= " + R / 2 + "-" + (Math.sqrt(Math.abs(det))) / 2 + "i";
            } else if (det >= 0) {
                root1 = (R + Math.sqrt(det)) / 2;
                root2 = (R - Math.sqrt(det)) / 2;
                result += "ROOTS:\n";
                result += "--->x1: " + root1 + "\n";
                result += "--->x2: " + root2;
            }
        } catch (Exception err) {
            result = null;
        }
        return result;
    }

    private double[] coefBC(double[] ab, double r, double s) {
        double[] bc = new double[ab.length];
        double bcResultado = 0;
        bc[0] = ab[0];
        bc[1] = ab[1] + r * bc[0];
        for (int i = 2; i < ab.length; i++) {
            bcResultado = ab[i] + (r) * (bc[i - 1]) + (s) * (bc[i - 2]);
            bc[i] = bcResultado;
        }
        return bc;
    }

    private double deltaRS(double c1, double c2, double c3, double b0, double b1, int type) {
        double result = 0;
        double delta = (c2 * c2) - (c1 * c3);
        double dRS = 0;

        if (type == 0) {
            dRS = (-b1 * c2) - (-b0 * c3);
        } else if (type == 1) {
            dRS = (-b0 * c2) - (-b1 * c1);
        }
        result = dRS / delta;
        return result;
    }

    private double[] vectorCoeficients(String coef) {
        double[] vector = new double[sizeCoeficients(coef)];
        coef += ",";
        String value = "";
        int count = 0;
        for (int i = 0; i < coef.length(); i++) {
            if (coef.charAt(i) != ',') {
                value += coef.charAt(i);
            } else {
                vector[count] = Double.parseDouble(value);
                value = "";
                count++;
            }
        }

        return vector;
    }

    private int sizeCoeficients(String coeficients) {
        int size = 0;
        for (int i = 0; i < coeficients.length(); i++) {
            if (coeficients.charAt(i) == ',') {
                size++;
            }
        }
        size++;
        return size;
    }

}
