package droidmath.calculus;

import droidexpression.Evaluate;

public class CalculusDI {

    private Derivatives d = new Derivatives();
    private NewtonCotesIntegration nc = new NewtonCotesIntegration();
    private Evaluate e = new Evaluate();

    public CalculusDI() {
        String[] var = {"x", "theta", "θ"};
        e.addAcceptableFirstDependency(var);
        e.addAcceptableSecondDependency(var);
    }

    public double angleTwoStraightPendant(double m1, double m2) {
        double r = (m1 - m2) / (1 + m1 * m2);
        double angle = Math.atan(r);
        return angle;
    }

    public double subtangentLength(String function, double x) {
        double l = 0;
        double y1 = e.solveOneVariable(function, x, Evaluate.RADIANS);
        double m = d.firstDifferenceCentered4(0.01, x, function);
        l = y1 / m;
        return l;
    }

    public double subnormalLength(String function, double x) {
        double l = 0;
        double y1 = e.solveOneVariable(function, x, Evaluate.RADIANS);
        double m = d.firstDifferenceCentered4(0.01, x, function);
        l = y1 * m;
        return l;
    }

    public String tangent(String function, double x1) {
        String t = "";
        double y1 = e.solveOneVariable(function, x1, Evaluate.RADIANS);
        double m = d.firstDifferenceCentered4(0.01, x1, function);
        double s = (m * (-x1)) + y1;
        t = m + "*" + "x" + (s < 0 ? "-" : "+") + Math.abs(s);
        return t;
    }

    public String normal(String function, double x1) {
        String t = "";
        double y1 = e.solveOneVariable(function, x1, Evaluate.RADIANS);
        double m = d.firstDifferenceCentered4(0.01, x1, function);
        double s = ((-1 / m) * (-x1)) + y1;
        t = m + "*" + "x" + (s < 0 ? "-" : "+") + Math.abs(s);
        return t;
    }

    public double firstDerivativeParametric(String functionX, String functionY, double t1, double t2) {
        double der = 0;
        double dy = d.firstDifferenceCentered4(0.01, t1, functionY);
        double dx = d.firstDifferenceCentered4(0.01, t2, functionX);
        der = dy / dx;
        return der;
    }

    public double secondDerivativeParametric(String functionX, String functionY, double t1, double t2) {
        double der = 0;
        double dyPrima = d.secondDifferenceCentered4(0.01, t1, functionY);
        double dx = d.firstDifferenceCentered4(0.01, t2, functionX);
        der = dyPrima / dx;
        return der;
    }

    public double differentialRectangularArch(String function, double x, double deltaX) {
        double der = d.firstDifferenceCentered4(0.01, x, function);
        double ds = Math.sqrt(1 + Math.pow(der, 2)) * deltaX;
        return ds;
    }

    public double differentialPolarArch(String function, double theta, double deltaTheta) {
        double der = d.firstDifferenceCentered4(0.01, theta, function);
        double f = e.solveOneVariable(function, theta, Evaluate.RADIANS);
        double ds = Math.sqrt(Math.pow(f, 2) + Math.pow(der, 2)) * deltaTheta;
        return ds;
    }

    public double rectangularCurvature(String function, double x) {
        double d1 = d.firstDifferenceCentered4(0.01, x, function);
        double d2 = d.secondDifferenceCentered4(0.01, x, function);
        double k = d2 / Math.pow(1 + Math.pow(d1, 2), 3 / 2);
        return k;
    }

    public double parametricCurvature(String functionY, String functionX, double ty, double tx) {
        double d1y = d.firstDifferenceCentered4(0.01, ty, functionY);
        double d1x = d.firstDifferenceCentered4(0.01, tx, functionX);
        double d2y = d.secondDifferenceCentered4(0.01, ty, functionY);
        double d2x = d.secondDifferenceCentered4(0.01, tx, functionX);
        double num = (d1x * d2y) - (d1y * d2x);
        double den = Math.pow(Math.pow(d1x, 2) + Math.pow(d1y, 2), 3 / 2);
        double k = num / den;
        return k;
    }

    public double polarCurvature(String function, double theta) {
        double k = 0;
        double r = e.solveOneVariable(function, theta, Evaluate.RADIANS);
        double d1 = d.firstDifferenceCentered4(0.01, theta, function);
        double d2 = d.secondDifferenceCentered4(0.01, theta, function);
        double num = Math.pow(r, 2) + 2 * Math.pow(d1, 2) - r * d2;
        double den = Math.pow(Math.pow(r, 2) + Math.pow(d1, 2), 3 / 2);
        k = num / den;
        return k;
    }

    public double radiusCurvatureRectangular(String function, double x) {
        double radius = 1 / rectangularCurvature(function, x);
        return radius;
    }

    public double[] centerCurvatureRectangular(String function, double x) {
        double[] c = new double[2];
        double y = e.solveOneVariable(function, x, Evaluate.RADIANS);
        double d1 = d.firstDifferenceCentered4(0.01, x, function);
        double d2 = d.secondDifferenceCentered4(0.01, x, function);
        double a = x - ((d1 * (1 + Math.pow(d1, 2))) / d2);
        double b = y + ((1 + Math.pow(d1, 2)) / d2);
        c[0] = a;
        c[1] = b;
        return c;
    }

    public double polarCurveArea(String r, double a, double b) {
        String function = "(" + r + ")^2";
        double area = 0.5 * nc.simpsonThirdMultiple(a, b, function, 20);
        return area;
    }

    public double solidRevolutionVolumeRectangularX(String function, double a, double b) {
        double vol = 0;
        String nF = "(" + function + ")^2";
        vol = Math.PI * nc.simpsonThirdMultiple(a, b, nF, 20);
        return vol;
    }

    public double[] gravityCenterRectangular(String function, double a, double b) {
        double[] c = new double[2];
        double area = nc.simpsonThirdMultiple(a, b, function, 20);
        double mx = 0.5 * nc.simpsonThirdMultiple(a, b, "(" + function + ")^2", 20);
        double my = nc.simpsonThirdMultiple(a, b, "x*" + function, 20);
        double xg = my / area;
        double yg = mx / area;
        c[0] = xg;
        c[1] = yg;
        return c;
    }

    public double revolutionSolidGravityCenterRectangularX(String function, double a, double b) {
        double xC = 0;
        double my = Math.PI * nc.simpsonThirdMultiple(a, b, "x*(" + function + ")^2", 20);
        double v = solidRevolutionVolumeRectangularX(function, a, b);
        xC = my / v;
        return xC;
    }

    public double liquidPressure(String function, double a, double b) {
        double pL = 0;
        pL = 1000 * nc.simpsonThirdMultiple(a, b, "x*" + function, 20);
        return pL;
    }

    public double workPumping(String function, double a, double b) {
        double tB = 0;
        tB = 1000 * Math.PI * nc.simpsonThirdMultiple(a, b, "x*(" + function + ")^2", 20);
        return tB;
    }

    public double averageValueOfAfunction(String function, double a, double b) {
        double vM = 0;
        double area = nc.simpsonThirdMultiple(a, b, function, 20);
        vM = area / (b - a);
        return vM;
    }

}
