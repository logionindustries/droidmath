package droidmath.differentialEquation;

import droidexpression.Evaluate;
import droidmath.assistant.Assistant;

public class FourierSeries {

    private Evaluate evalua = new Evaluate();
    private Assistant assistant = new Assistant();

    public String fourierSerie(String funcion, int nTimes) {
        if (fourierSerieInConditions(assistant.clean(funcion)) == false) {
            return null;
        }
        if (nTimes < 1) {
            return null;
        }
        String serie = "";
        String serieF = "";
        try {
            double[] valuesA = A(assistant.clean(funcion));
            double[] valuesB = B(assistant.clean(funcion));
            double p = Math.abs(valuesA[0]);
            String[] setFunctions = functions(assistant.clean(funcion));
            serie = "" + (constantA0(valuesA, valuesB, setFunctions, p) / 2)
                    + summation(nTimes, valuesA, valuesB, setFunctions, p);
            serieF = newSerie(serie);
        } catch (Exception err) {
            serieF = null;
        }
        if (serie == null) {
            return null;
        }
        if (serie.equals("NaN")) {
            return null;
        }
        return serieF;
    }

    public boolean fourierSerieInConditions(String funcion) {
        boolean itsOk = false;
        try {
            if (continuosFunctionInI(assistant.clean(funcion))
                    && intervalPAcceptable(assistant.clean(funcion))
                    && acceptableFunctions(functions(assistant.clean(funcion)))) {
                return true;
            }
        } catch (Exception err) {
            itsOk = false;
        }
        return itsOk;
    }

    private String newSerie(String fourierSerie) {
        String newSerie = "";
        String assis = "";
        boolean itsOk = false;
        for (int a = 0; a < fourierSerie.length(); a++) {
            if (fourierSerie.charAt(a) == 'E') {
                for (int b = a + 1; b < fourierSerie.length(); b++) {
                    if (fourierSerie.charAt(b) == '-' && itsOk == false) {
                        a++;
                        b++;
                        itsOk = true;
                    }
                    if (fourierSerie.charAt(b) != '+' & fourierSerie.charAt(b) != '-'
                            & fourierSerie.charAt(b) != '*' & fourierSerie.charAt(b) != '/'
                            & fourierSerie.charAt(b) != '^' & fourierSerie.charAt(b) != '('
                            & fourierSerie.charAt(b) != ')') {
                        assis += fourierSerie.charAt(b);
                        a++;
                    } else {
                        b = fourierSerie.length();
                    }
                }
                if (itsOk == true) {
                    int valor = Math.abs(Integer.parseInt(assis));
                    newSerie += "/10^" + valor;
                } else {
                    double valor = Math.abs(Double.parseDouble(assis));
                    newSerie += "*10^" + valor;
                }
                itsOk = false;
                assis = "";
            } else {
                newSerie += fourierSerie.charAt(a);
            }
        }
        return newSerie;
    }

    private String summation(int nTimes, double[] valuesA,
                             double[] valuesB, String[] setFunctions, double p) {
        String summation = "";
        String addCos = "", addSin = "";

        for (int i = 1; i <= nTimes; i++) {
            double val = (Math.PI * i) / p;
            double an = constantA(i, valuesA, valuesB, setFunctions, p);
            double bn = constantBn(i, valuesA, valuesB, setFunctions, p);
            addCos = "" + an + "*"
                    + "cos(" + val + "*x" + ")";
            addSin = "" + bn + "*"
                    + "sin(" + val + "*x" + ")";
            //verify conditions to reduce evaluation time
            if (an < 0 && bn < 0) {
                summation += addCos + addSin;
            } else if (an < 0 && bn > 0) {
                summation += addCos + "+" + addSin;
            } else if (an > 0 && bn < 0) {
                summation += "+" + addCos + addSin;
            } else if (an > 0 && bn > 0) {
                summation += "+" + addCos + "+" + addSin;
            }
        }
        return summation;
    }

    private double constantA0(double[] valuesA,
                              double[] valuesB, String[] setFunctions, double p) {
        double integral = 0;
        double a0 = 0;
        for (int i = 0; i < setFunctions.length; i++) {
            double a = valuesA[i];
            double b = valuesB[i];
            String func = setFunctions[i];
            integral += addSimpsonThirdMultiple(a, b, func);
        }
        a0 = integral / p;
        return a0;
    }

    private double constantA(int n, double[] valuesA,
                             double[] valuesB, String[] setFunctions, double p) {
        double integral = 0;
        double an = 0;
        double coeficientAssis = (n * Math.PI) / p;
        for (int i = 0; i < setFunctions.length; i++) {
            double a = valuesA[i];
            double b = valuesB[i];
            String func = setFunctions[i] + "*cos(" + coeficientAssis + "*x" + ")";
            integral += addSimpsonThirdMultiple(a, b, func);
        }
        an = integral / p;
        return an;
    }

    private double constantBn(int n, double[] valuesA,
                              double[] valuesB, String[] setFunctions, double p) {
        double integral = 0;
        double bn = 0;
        double coeficientAssis = (n * Math.PI) / p;
        for (int i = 0; i < setFunctions.length; i++) {
            double a = valuesA[i];
            double b = valuesB[i];
            String func = setFunctions[i] + "*sin(" + coeficientAssis + "*x" + ")";
            integral += addSimpsonThirdMultiple(a, b, func);
        }
        bn = integral / p;
        return bn;
    }

    private double addSimpsonThirdMultiple(double a, double b, String function) {
        if (evalua.balancedFunction(function) == false) {
            return Double.NaN;
        }
        if (evalua.withoutUnacceptableValues(function) == false) {
            return Double.NaN;
        }
        if (evalua.withoutUnacceptableVariables(function) == false) {
            return Double.NaN;
        }
        int n = 50;
        double h = (b - a) / n;
        double area = 0;
        double aAssis = a;
        double[] functions = new double[n + 1];
        double fX = 0;
        double addEven = 0;
        double addOdd = 0;
        for (int i = 0; i <= n; i++) {
            fX = evalua.solveOneVariable(function, aAssis, Evaluate.RADIANS);
            aAssis += h;
            functions[i] = fX;
        }
        //add the evens
        for (int i = 1; i < n; i += 2) {
            addOdd += functions[i];
        }
        //add the odds
        for (int i = 2; i < n; i += 2) {
            addEven += functions[i];
        }
        double num = functions[0] + 4 * addOdd + 2 * addEven + functions[n];
        area = (b - a) * (num / (3 * n));
        return area;
    }

    //Assistants
    private int numberOfFunctions(String functions) {
        int total = 0;
        for (int i = 0; i < functions.length(); i++) {
            if (functions.charAt(i) == ';') {
                total++;
            }
        }
        return total;
    }

    private String stringWithoutJumps(String functions) {
        String strN = "";
        for (int i = 0; i < functions.length(); i++) {
            if (functions.charAt(i) == '\n') {
                strN += ":";
            } else {
                strN += functions.charAt(i);
            }
        }
        return strN;
    }

    private String[] functions(String functionsArray) {
        String[] func = new String[numberOfFunctions(functionsArray)];
        int count = 0;
        String functions = stringWithoutJumps(functionsArray);
        String store = "";
        for (int i = 0; i < functions.length(); i++) {
            if (functions.charAt(i) != ';') {
                store += functions.charAt(i);
            } else {
                func[count] = store;
                count++;
                store = "";
                for (int j = i; j < functions.length(); j++) {
                    if (functions.charAt(j) != ':') {
                        i++;
                    } else {
                        j = functions.length();
                    }
                }
            }
        }
        return func;
    }

    private double[] A(String functionsArray) {
        double[] dom = new double[numberOfFunctions(functionsArray)];
        int count = 0;
        String functions = stringWithoutJumps(functionsArray);
        String store = "";
        for (int i = 0; i < functions.length(); i++) {
            if (functions.charAt(i) == ';') {//j = i + 2 to jump to (
                for (int j = i + 2; j < functions.length(); j++) {
                    if (functions.charAt(j) != ',') {
                        store += functions.charAt(j);
                        i++;
                    } else {
                        dom[count] = Double.parseDouble(store);
                        j = functions.length();
                        count++;
                        store = "";
                    }
                }
            }
        }
        return dom;
    }

    private double[] B(String functionsArray) {
        double[] dom = new double[numberOfFunctions(functionsArray)];
        int count = 0;
        String functions = stringWithoutJumps(functionsArray);
        String store = "";
        for (int i = 0; i < functions.length(); i++) {
            if (functions.charAt(i) == ',') {//j = i + 1 to jump to ,
                for (int j = i + 1; j < functions.length(); j++) {
                    if (functions.charAt(j) != ')') {
                        store += functions.charAt(j);
                        i++;
                    } else {
                        dom[count] = Double.parseDouble(store);
                        j = functions.length();
                        count++;
                        store = "";
                    }
                }
            }
        }
        return dom;
    }

    private boolean acceptableFunctions(String[] setFunctions) {
        for (int i = 0; i < setFunctions.length; i++) {
            if (evalua.balancedFunction(setFunctions[i]) == false) {
                return false;
            }
            if (evalua.withoutUnacceptableValues(setFunctions[i]) == false) {
                return false;
            }
            if (evalua.withoutUnacceptableVariables(setFunctions[i]) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean continuosFunctionInI(String functionsArray) {
        double[] a = A(functionsArray);
        double[] b = B(functionsArray);
        boolean itsOk = false;
        /*Of the set of intervals, the functions must be continuous, that is to say
         the last value of the first function, must be equal to the first value of
         the next function
         x;(-12,5)
         sen[x];(5,9)
         */
        if (a.length > 1) {
            for (int i = 1; i < a.length; i++) {
                if (a[i] == b[i - 1]) {
                    itsOk = true;
                } else {
                    itsOk = false;
                    i = a.length;
                }
            }
        } else {
            itsOk = true;
        }
        return itsOk;
    }

    private boolean intervalPAcceptable(String functionsArray) {
        double[] a = A(functionsArray);
        double[] b = B(functionsArray);
        /*As long as the interval is acceptable that is to say
         * (-P, P), the xInicial must be P, like the xFinal
         */
        if (a[0] == b[b.length - 1]) {
            return false;
        }
        if (Math.abs(a[0]) == Math.abs(b[b.length - 1])) {
            return true;
        }
        return false;
    }

}
