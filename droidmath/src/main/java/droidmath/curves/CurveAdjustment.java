package droidmath.curves;

public class CurveAdjustment {

    public String linearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            return "ERROR";
        }
        String response = "";
        int n = x.length;
        double num = (n * multiplicationXY(x, y, 1, 1) - addXOrY(x, 1) * addXOrY(y, 1));
        double den = (n * addXOrY(x, 2) - Math.pow(addXOrY(x, 1), 2));
        double a1 = num / den;
        double a0 = (addXOrY(y, 1) / n) - a1 * (addXOrY(x, 1) / n);
        if (a1 < 0) {
            response = "y= " + a0 + " - " + Math.abs(a1) + "*x";
        } else {
            response = "y= " + a0 + " + " + a1 + "*x";
        }
        return response;
    }

    public String polynomialRegressionSecondGrade(double[] x, double[] y) {
        if (x.length != y.length) {
            return "ERROR";
        }
        String response = "";
        int n = x.length;
        double addX = addXOrY(x, 1);
        double addX2 = addXOrY(x, 2);
        double addX3 = addXOrY(x, 3);
        double addX4 = addXOrY(x, 4);
        double addY = addXOrY(y, 1);
        double mXY = multiplicationXY(x, y, 1, 1);
        double mX2Y = multiplicationXY(x, y, 2, 1);

        double[][] matrixA1 = {{n, addX, addX2},
                {addX, addX2, addX3},
                {addX2, addX3, addX4}};
        double[] matrixB1 = {addY, mXY, mX2Y};

        double a0 = gauss3(matrixA1, matrixB1, 1);
        double[][] matrixA2 = {{n, addX, addX2},
                {addX, addX2, addX3},
                {addX2, addX3, addX4}};
        double[] matrixB2 = {addY, mXY, mX2Y};
        double a1 = gauss3(matrixA2, matrixB2, 2);
        double[][] matrixA3 = {{n, addX, addX2},
                {addX, addX2, addX3},
                {addX2, addX3, addX4}};
        double[] matrixB3 = {addY, mXY, mX2Y};
        double a2 = gauss3(matrixA3, matrixB3, 3);

        String second = "", third = "";
        if (a1 < 0) {
            second = "- " + Math.abs(a1);
        } else {
            second = "+ " + a1;
        }
        if (a2 < 0) {
            third = "- " + Math.abs(a2);
        } else {
            third = "+ " + a2;
        }
        response = "y= " + a0 + " " + second + "*x " + third + "*x^2";
        return response;
    }

    public String polynomialRegressionThirdGrade(double[] x, double[] y) {
        if (x.length != y.length) {
            return "ERROR";
        }
        String response = "";
        int n = x.length;
        double addX = addXOrY(x, 1);
        double addX2 = addXOrY(x, 2);
        double addX3 = addXOrY(x, 3);
        double addX4 = addXOrY(x, 4);
        double addX5 = addXOrY(x, 5);
        double addX6 = addXOrY(x, 6);
        double addY = addXOrY(x, 1);
        double mXY = multiplicationXY(x, y, 1, 1);
        double mX2Y = multiplicationXY(x, y, 2, 1);
        double mX3Y = multiplicationXY(x, y, 3, 1);

        double[][] matrixA = {{n, addX, addX2, addX3},
                {addX, addX2, addX3, addX4},
                {addX2, addX3, addX4, addX5},
                {addX3, addX4, addX5, addX6}};
        double[] matrixB = {addY, mXY, mX2Y, mX3Y};

        double a0 = gauss4(matrixA, matrixB, 1);
        double[][] matrixA2 = {{n, addX, addX2, addX3},
                {addX, addX2, addX3, addX4},
                {addX2, addX3, addX4, addX5},
                {addX3, addX4, addX5, addX6}};
        double[] matrixB2 = {addY, mXY, mX2Y, mX3Y};
        double a1 = gauss4(matrixA2, matrixB2, 2);
        double[][] matrixA3 = {{n, addX, addX2, addX3},
                {addX, addX2, addX3, addX4},
                {addX2, addX3, addX4, addX5},
                {addX3, addX4, addX5, addX6}};
        double[] matrixB3 = {addY, mXY, mX2Y, mX3Y};
        double a2 = gauss4(matrixA3, matrixB3, 3);
        double[][] matrixA4 = {{n, addX, addX2, addX3},
                {addX, addX2, addX3, addX4},
                {addX2, addX3, addX4, addX5},
                {addX3, addX4, addX5, addX6}};
        double[] matrixB4 = {addY, mXY, mX2Y, mX3Y};
        double a3 = gauss4(matrixA4, matrixB4, 4);

        String second = "", third = "", fourth = "";
        if (a1 < 0) {
            second = "- " + Math.abs(a1);
        } else {
            second = "+ " + a1;
        }
        if (a2 < 0) {
            third = "- " + Math.abs(a2);
        } else {
            third = "+ " + a2;
        }
        if (a3 < 0) {
            fourth = "- " + Math.abs(a3);
        } else {
            fourth = "+ " + a3;
        }
        response = "y= " + a0 + " " + second + "*x " + third + "*x^2 " + fourth + "*x^3";
        return response;
    }

    public String linearRegressionMultipleTwoVars(double[] x, double[] x2, double[] y) {
        if (x.length != x2.length || x.length != y.length || x2.length != y.length) {
            return "ERROR";
        }
        String response = "";
        int n = x.length;
        double addXU = addXOrY(x, 1);
        double addXT = addXOrY(x2, 1);
        double addXU2 = addXOrY(x, 2);
        double addXT2 = addXOrY(x2, 2);
        double addY = addXOrY(y, 1);
        double mXUXT = multiplicationXY(x, x2, 1, 1);
        double mXUY = multiplicationXY(x, y, 1, 1);
        double mXTY = multiplicationXY(x2, y, 1, 1);

        double[][] matrixA = {{n, addXU, addXT},
                {addXU, addXU2, mXUXT},
                {addXT, mXUXT, addXT2}};
        double[] matrixB = {addY, mXUY, mXTY};

        double a0 = gauss3(matrixA, matrixB, 1);
        double[][] matrixA2 = {{n, addXU, addXT},
                {addXU, addXU2, mXUXT},
                {addXT, mXUXT, addXT2}};
        double[] matrixB2 = {addY, mXUY, mXTY};
        double a1 = gauss3(matrixA2, matrixB2, 2);
        double[][] matrixA3 = {{n, addXU, addXT},
                {addXU, addXU2, mXUXT},
                {addXT, mXUXT, addXT2}};
        double[] matrixB3 = {addY, mXUY, mXTY};
        double a2 = gauss3(matrixA3, matrixB3, 3);

        String second = "", third = "";
        if (a1 < 0) {
            second = "- " + Math.abs(a1);
        } else {
            second = "+ " + a1;
        }
        if (a2 < 0) {
            third = "- " + Math.abs(a2);
        } else {
            third = "+ " + a2;
        }
        response = "y= " + a0 + " " + second + "*x1 " + third + "*x2";
        return response;
    }

    private double addXOrY(double[] XY, int potencia) {
        double add = 0;
        for (int i = 0; i < XY.length; i++) {
            add += Math.pow(XY[i], potencia);
        }
        return add;
    }

    private double multiplicationXY(double[] x, double[] y, int potX, int potY) {
        double multiplication = 0;
        double add = 0;
        for (int i = 0; i < x.length; i++) {
            multiplication = Math.pow(x[i], potX) * Math.pow(y[i], potY);
            add += multiplication;
        }
        return add;
    }

    //GAUSS FOR A MATRIX OF 3x3
    private double gauss3(double[][] matrixA, double[] matrixB, int variable) {
        double result = 0;
        double x1 = 0, x2 = 0, x3 = 0;
        double CONSTANT = 0;
        double newValue = 0;
        double[] matrix1 = matrixA[0];
        double[] matrix2 = matrixA[1];
        double[] matrix3 = matrixA[2];
        int a = 0, b = 1, c = 2;

        CONSTANT = matrix2[a] / matrix1[a];
        matrix2 = arraySubtraction(matrix2, arrayMultiplicationConstant(matrix1, CONSTANT));

        newValue = matrixB[b] - CONSTANT * matrixB[a];
        matrixB[b] = newValue;

        CONSTANT = matrix3[a] / matrix1[a];
        matrix3 = arraySubtraction(matrix3, arrayMultiplicationConstant(matrix1, CONSTANT));

        newValue = matrixB[c] - CONSTANT * matrixB[a];
        matrixB[c] = newValue;

        CONSTANT = matrix3[b] / matrix2[b];
        matrix3 = arraySubtraction(matrix3, arrayMultiplicationConstant(matrix2, CONSTANT));

        newValue = matrixB[c] - CONSTANT * matrixB[b];
        matrixB[c] = newValue;

        x3 = matrixB[c] / matrix3[c];
        x2 = (matrixB[b] - matrix2[c] * x3) / matrix2[b];
        x1 = (matrixB[a] - matrix1[b] * x2 - matrix1[c] * x3) / (matrix1[a]);

        switch (variable) {
            case 1:
                result = x1;
                break;
            case 2:
                result = x2;
                break;
            case 3:
                result = x3;
                break;
        }
        return result;
    }

    private double gauss4(double[][] matrixA, double[] matrixB, int variable) {
        double result = 0;
        double[] matrix1 = matrixA[0];
        double[] matrix2 = matrixA[1];
        double[] matrix3 = matrixA[2];
        double[] matrix4 = matrixA[3];
        double x1 = 0, x2 = 0, x3 = 0, x4 = 0;
        double CONSTANT = 0;
        double newValue = 0;
        int a = 0, b = 1, c = 2, d = 3;
        //2,3,4
        CONSTANT = matrix2[a] / matrix1[a];
        matrix2 = arraySubtraction(matrix2, arrayMultiplicationConstant(matrix1, CONSTANT));
        newValue = matrixB[b] - CONSTANT * matrixB[a];
        matrixB[b] = newValue;

        CONSTANT = matrix3[a] / matrix1[a];
        matrix3 = arraySubtraction(matrix3, arrayMultiplicationConstant(matrix1, CONSTANT));
        newValue = matrixB[c] - CONSTANT * matrixB[a];
        matrixB[c] = newValue;

        CONSTANT = matrix4[a] / matrix1[a];
        matrix4 = arraySubtraction(matrix4, arrayMultiplicationConstant(matrix1, CONSTANT));
        newValue = matrixB[d] - CONSTANT * matrixB[a];
        matrixB[d] = newValue;
        //3,4
        CONSTANT = matrix3[b] / matrix2[b];
        matrix3 = arraySubtraction(matrix3, arrayMultiplicationConstant(matrix2, CONSTANT));
        newValue = matrixB[c] - CONSTANT * matrixB[b];
        matrixB[c] = newValue;

        CONSTANT = matrix4[b] / matrix2[b];
        matrix4 = arraySubtraction(matrix4, arrayMultiplicationConstant(matrix2, CONSTANT));
        newValue = matrixB[d] - CONSTANT * matrixB[b];
        matrixB[d] = newValue;
        //4
        CONSTANT = matrix4[c] / matrix3[c];
        matrix4 = arraySubtraction(matrix4, arrayMultiplicationConstant(matrix3, CONSTANT));
        newValue = matrixB[d] - CONSTANT * matrixB[c];
        matrixB[d] = newValue;

        x4 = matrixB[d] / matrix4[d];
        x3 = (matrixB[c] - matrix3[d] * x4) / (matrix3[c]);
        x2 = (matrixB[b] - matrix2[c] * x3 - matrix2[d] * x4) / (matrix2[b]);
        x1 = (matrixB[a] - matrix1[b] * x2 - matrix1[c] * x3 - matrix1[d] * x4) / (matrix1[a]);

        switch (variable) {
            case 1:
                result = x1;
                break;
            case 2:
                result = x2;
                break;
            case 3:
                result = x3;
                break;
            case 4:
                result = x4;
                break;
        }
        return result;
    }

    private double[] arrayMultiplicationConstant(double[] vector, double constant) {
        double[] vec = new double[vector.length];
        double assis = 0;
        for (int i = 0; i < vector.length; i++) {
            assis = vector[i] * constant;
            vec[i] = assis;
        }
        return vec;
    }

    private double[] arraySubtraction(double[] vec1, double[] vec2) {
        double[] subtraction = new double[vec1.length];
        double result = 0;
        for (int i = 0; i < vec1.length; i++) {
            result = vec1[i] - vec2[i];
            subtraction[i] = result;
        }
        return subtraction;
    }

}
