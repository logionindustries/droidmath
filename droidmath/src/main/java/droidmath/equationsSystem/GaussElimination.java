package droidmath.equationsSystem;

import droidmath.assistant.Assistant;

public class GaussElimination {

    private Assistant assistant = new Assistant();

    public String gauss2(String system) {
        String result = "";
        String system2 = assistant.clean(system);
        double[][] matrixA = assistant.vectorsA(2, system2);
        double[] matrixB = assistant.vectorB(2, system2);

        double[] matrix1 = matrixA[0];
        double[] matrix2 = matrixA[1];
        double x1 = 0, x2 = 0;
        int a = 0, b = 1;
        double CONST = 0;
        double valor = 0;
        //Delete first element from second row
        CONST = matrix2[a] / matrix1[a];
        matrix2 = subtractionVectors(matrix2, vectorMultiplicateConstant(matrix1, CONST));
        valor = matrixB[b] - CONST * matrixB[a];
        matrixB[b] = valor;

        x2 = matrixB[b] / matrix2[b];
        x1 = (matrixB[a] - matrix1[b] * x2) / (matrix1[a]);

        result += "x = " + x1 + "\n";
        result += "y = " + x2;
        return result;
    }

    public String gauss3(String system) {
        String result = "";
        String system2 = assistant.clean(system);
        double[][] matrixA = assistant.vectorsA(3, system2);
        double[] matrixB = assistant.vectorB(3, system2);

        double x1 = 0, x2 = 0, x3 = 0;
        double CONST = 0;
        double newValue = 0;
        double[] matrix1 = matrixA[0];
        double[] matrix2 = matrixA[1];
        double[] matrix3 = matrixA[2];
        int a = 0, b = 1, c = 2;
        //Delete first element from second row
        CONST = matrix2[a] / matrix1[a];
        matrix2 = subtractionVectors(matrix2, vectorMultiplicateConstant(matrix1, CONST));

        newValue = matrixB[b] - CONST * matrixB[a];
        matrixB[b] = newValue;
        //Delete first element from third row
        CONST = matrix3[a] / matrix1[a];
        matrix3 = subtractionVectors(matrix3, vectorMultiplicateConstant(matrix1, CONST));

        newValue = matrixB[c] - CONST * matrixB[a];
        matrixB[c] = newValue;
        //Delete second element from third row
        CONST = matrix3[b] / matrix2[b];
        matrix3 = subtractionVectors(matrix3, vectorMultiplicateConstant(matrix2, CONST));

        newValue = matrixB[c] - CONST * matrixB[b];
        matrixB[c] = newValue;

        x3 = matrixB[c] / matrix3[c];
        x2 = (matrixB[b] - matrix2[c] * x3) / matrix2[b];
        x1 = (matrixB[a] - matrix1[b] * x2 - matrix1[c] * x3) / (matrix1[a]);

        result += "x = " + x1 + "\n";
        result += "y = " + x2 + "\n";
        result += "z = " + x3;
        return result;
    }

    public String gauss4(String system) {
        String result = "";
        String system2 = assistant.clean(system);
        double[][] matrixA = assistant.vectorsA(4, system2);
        double[] matrixB = assistant.vectorB(4, system2);

        double[] matrix1 = matrixA[0];
        double[] matrix2 = matrixA[1];
        double[] matrix3 = matrixA[2];
        double[] matrix4 = matrixA[3];
        double x1 = 0, x2 = 0, x3 = 0, x4 = 0;
        double CONST = 0;
        double newValue = 0;
        int a = 0, b = 1, c = 2, d = 3;
        //Delete the first element from row
        //2,3,4
        CONST = matrix2[a] / matrix1[a];
        matrix2 = subtractionVectors(matrix2, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[b] - CONST * matrixB[a];
        matrixB[b] = newValue;

        CONST = matrix3[a] / matrix1[a];
        matrix3 = subtractionVectors(matrix3, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[c] - CONST * matrixB[a];
        matrixB[c] = newValue;

        CONST = matrix4[a] / matrix1[a];
        matrix4 = subtractionVectors(matrix4, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[d] - CONST * matrixB[a];
        matrixB[d] = newValue;
        //Delete the second element from row
        //3,4
        CONST = matrix3[b] / matrix2[b];
        matrix3 = subtractionVectors(matrix3, vectorMultiplicateConstant(matrix2, CONST));
        newValue = matrixB[c] - CONST * matrixB[b];
        matrixB[c] = newValue;

        CONST = matrix4[b] / matrix2[b];
        matrix4 = subtractionVectors(matrix4, vectorMultiplicateConstant(matrix2, CONST));
        newValue = matrixB[d] - CONST * matrixB[b];
        matrixB[d] = newValue;
        //Delete the third element from row
        //4
        CONST = matrix4[c] / matrix3[c];
        matrix4 = subtractionVectors(matrix4, vectorMultiplicateConstant(matrix3, CONST));
        newValue = matrixB[d] - CONST * matrixB[c];
        matrixB[d] = newValue;

        x4 = matrixB[d] / matrix4[d];
        x3 = (matrixB[c] - matrix3[d] * x4) / (matrix3[c]);
        x2 = (matrixB[b] - matrix2[c] * x3 - matrix2[d] * x4) / (matrix2[b]);
        x1 = (matrixB[a] - matrix1[b] * x2 - matrix1[c] * x3 - matrix1[d] * x4) / (matrix1[a]);

        result += "x = " + x1 + "\n";
        result += "y = " + x2 + "\n";
        result += "z = " + x3 + "\n";
        result += "u = " + x4;
        return result;
    }

    public String gauss5(String system) {
        String result = "";
        String system2 = assistant.clean(system);
        double[][] matrixA = assistant.vectorsA(5, system2);
        double[] matrixB = assistant.vectorB(5, system2);

        double[] matrix1 = matrixA[0];
        double[] matrix2 = matrixA[1];
        double[] matrix3 = matrixA[2];
        double[] matrix4 = matrixA[3];
        double[] matrix5 = matrixA[4];
        double x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5;
        double CONST = 0;
        double newValue = 0;
        int a = 0, b = 1, c = 2, d = 3, e = 4;
        //Delete the first element from row
        //2,3,4,5
        CONST = matrix2[a] / matrix1[a];
        matrix2 = subtractionVectors(matrix2, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[b] - CONST * matrixB[a];
        matrixB[b] = newValue;

        CONST = matrix3[a] / matrix1[a];
        matrix3 = subtractionVectors(matrix3, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[c] - CONST * matrixB[a];
        matrixB[c] = newValue;

        CONST = matrix4[a] / matrix1[a];
        matrix4 = subtractionVectors(matrix4, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[d] - CONST * matrixB[a];
        matrixB[d] = newValue;

        CONST = matrix5[a] / matrix1[a];
        matrix5 = subtractionVectors(matrix5, vectorMultiplicateConstant(matrix1, CONST));
        newValue = matrixB[e] - CONST * matrixB[a];
        matrixB[e] = newValue;
        //Delete the second element from row
        //3,4,5
        CONST = matrix3[b] / matrix2[b];
        matrix3 = subtractionVectors(matrix3, vectorMultiplicateConstant(matrix2, CONST));
        newValue = matrixB[c] - CONST * matrixB[b];
        matrixB[c] = newValue;

        CONST = matrix4[b] / matrix2[b];
        matrix4 = subtractionVectors(matrix4, vectorMultiplicateConstant(matrix2, CONST));
        newValue = matrixB[d] - CONST * matrixB[b];
        matrixB[d] = newValue;

        CONST = matrix5[b] / matrix2[b];
        matrix5 = subtractionVectors(matrix5, vectorMultiplicateConstant(matrix2, CONST));
        newValue = matrixB[e] - CONST * matrixB[b];
        matrixB[e] = newValue;
        //Delete the third element from row
        //4,5
        CONST = matrix4[c] / matrix3[c];
        matrix4 = subtractionVectors(matrix4, vectorMultiplicateConstant(matrix3, CONST));
        newValue = matrixB[d] - CONST * matrixB[c];
        matrixB[d] = newValue;

        CONST = matrix5[c] / matrix3[c];
        matrix5 = subtractionVectors(matrix5, vectorMultiplicateConstant(matrix3, CONST));
        newValue = matrixB[e] - CONST * matrixB[c];
        matrixB[e] = newValue;
        //Delete the fourth element from row
        //5
        CONST = matrix5[d] / matrix4[d];
        matrix5 = subtractionVectors(matrix5, vectorMultiplicateConstant(matrix4, CONST));
        newValue = matrixB[e] - CONST * matrixB[d];
        matrixB[e] = newValue;

        x5 = matrixB[e] / matrix5[e];
        x4 = (matrixB[d] - matrix4[e] * x5) / (matrix4[d]);
        x3 = (matrixB[c] - matrix3[d] * x4 - matrix3[e] * x5) / (matrix3[c]);
        x2 = (matrixB[b] - matrix2[c] * x3 - matrix2[d] * x4 - matrix2[e] * x5) / (matrix2[b]);
        x1 = (matrixB[a] - matrix1[b] * x2 - matrix1[c] * x3 - matrix1[d] * x4 - matrix1[e] * x5) / (matrix1[a]);

        result += "x = " + x1 + "\n";
        result += "y = " + x2 + "\n";
        result += "z = " + x3 + "\n";
        result += "u = " + x4 + "\n";
        result += "w = " + x5;
        return result;
    }

    private double[] vectorMultiplicateConstant(double[] vector, double constant) {
        double[] vec = new double[vector.length];
        double assis = 0;
        for (int i = 0; i < vector.length; i++) {
            assis = vector[i] * constant;
            vec[i] = assis;
        }
        return vec;
    }

    private double[] subtractionVectors(double[] vec1, double[] vec2) {
        double[] subtraction = new double[vec1.length];
        double result = 0;
        for (int i = 0; i < vec1.length; i++) {
            result = vec1[i] - vec2[i];
            subtraction[i] = result;
        }
        return subtraction;
    }

}
