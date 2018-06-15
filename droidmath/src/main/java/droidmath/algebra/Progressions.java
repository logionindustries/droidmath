package droidmath.algebra;

import droidmath.assistant.Assistant;

public class Progressions {

    private Assistant assistant = new Assistant();

    //Series with form :  1,2,3,4,5,6, etc
    public boolean isArithmeticSerie(String serie) {
        boolean itsOk = true;
        double[] v = numbersVec(serie);
        double difference = v[1] - v[0];
        double d = 0;
        for (int i = 0; i < v.length - 1; i++) {
            d = v[i + 1] - v[i];
            if (d != difference) {
                itsOk = false;
                i = v.length;
            }
        }
        return itsOk;
    }

    public boolean isGeometricSerie(String serie) {
        boolean itsOk = true;
        double[] v = numbersVec(serie);
        double reason = v[1] / v[0];
        double r = 0;
        for (int i = 0; i < v.length - 1; i++) {
            r = v[i + 1] / v[i];
            if (r != reason) {
                itsOk = false;
                i = v.length;
            }
        }
        return itsOk;
    }

    public double lastArithmeticTerm(String serie, int n) {
        double[] vec = numbersVec(serie);
        double res = 0;
        double a = vec[0];
        double d = vec[1] - vec[0];
        if (isArithmeticSerie(serie)) {
            res = a + (n - 1) * d;
        } else {
            res = Double.NaN;
        }
        return res;
    }

    public double lastGeometricTerm(String serie, int n) {
        double[] vec = numbersVec(serie);
        double res = 0;
        double a = vec[0];
        double r = vec[1] / vec[0];
        if (isGeometricSerie(serie)) {
            res = a * Math.pow(r, n - 1);
        } else {
            res = Double.NaN;
        }
        return res;
    }

    public double arithmeticTermsAddition(String serie, int n) {
        double[] vec = numbersVec(serie);
        double res = 0;
        double a = vec[0];
        double d = vec[1] - vec[0];
        if (isArithmeticSerie(serie)) {
            res = (n * (2 * a + (n - 1) * d)) / 2;
        } else {
            res = Double.NaN;
        }
        return res;
    }

    public double geometricTermsAddition(String serie, int n) {
        double[] vec = numbersVec(serie);
        double res = 0;
        double a = vec[0];
        double r = vec[1] / vec[0];
        if (isGeometricSerie(serie)) {
            res = (a * (1 - Math.pow(r, n))) / (1 - r);
        } else {
            res = Double.NaN;
        }
        return res;
    }

    public String interpolateArithmetic(double a, double l, int nInterpolate) {
        String res = "";
        double n = nInterpolate + 2;
        double difference = (l - a) / (n - 1);
        res += "" + assistant.castValue(a) + ",";
        for (double i = 1; i <= nInterpolate; i++) {
            res += assistant.castValue((a + i * difference)) + ",";
        }
        res += "" + assistant.castValue(l);
        return res;
    }

    public String interpolateGeometric(double a, double l, int nInterpolate) {
        String res = "";
        double n = nInterpolate + 2;
        double root = 1 / (n - 1);
        double reason = Math.pow(l / a, root);
        res += "" + assistant.castValue(a) + ",";
        for (double i = 1; i <= nInterpolate; i++) {
            res += assistant.castValue((a * Math.pow(reason ,i))) + ",";
        }
        res += "" + assistant.castValue(l);
        return res;
    }

    //receive a string how 1,2,3,4,5,6,...etc
    private double[] numbersVec(String str) {
        double[] num = new double[sizeVec(str)];
        str += ",";
        String store = "";
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ',') {
                store += str.charAt(i);
            } else {
                num[count] = Double.parseDouble(store);
                store = "";
                count++;
            }
        }
        return num;
    }

    private int sizeVec(String str) {
        int count = 0;
        str += ",";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                count++;
            }
        }
        return count;
    }

}
