package droidmath.algebra;

import droidmath.assistant.Assistant;

public class CoordinatoryTheory {

    private Assistant assistant = new Assistant();

    public double factorial(int n) {
        double fac = 0;
        if (assistant.isInteger("" + n) && n >= 0) {
            if (n == 0) {
                n = 1;
            }
            int x = n;
            fac = x;
            for (int i = x - 1; i > 0; i--) {
                fac = fac * i;
            }
        } else {
            fac = Double.NaN;
        }
        return fac;
    }

    public double permutation(int m) {
        return factorial(m);
    }

    public double coordination(int m, int n) {
        double c = 0;
        if (assistant.isInteger("" + m) && assistant.isInteger("" + n)
                && m >= 0 && n >= 0 && m > n) {
            int s = m - n + 1;
            c = m;
            for (int i = m - 1; i >= s; i--) {
                c *= i;
            }
        } else {
            c = Double.NaN;
        }
        return c;
    }

    public double combination(int m, int n) {
        double com = 0;
        com = coordination(m, n) / permutation(n);
        return com;
    }

}
