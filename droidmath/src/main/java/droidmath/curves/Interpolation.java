package droidmath.curves;

public class Interpolation {

    //Linear interpolation of Newton de differences divided
    public double linearInterpolation(double x, double x0, double x1, double fx0, double fx1) {
        double fx = 0;
        fx = fx0 + ((fx1 - fx0) / (x1 - x0)) * (x - x0);
        return fx;
    }

    public double quadraticInterpolation(double x, double x0, double x1, double x2,
                                         double fx0, double fx1, double fx2) {
        double fx = 0;
        double b0 = fx0;
        double b1 = (fx1 - fx0) / (x1 - x0);
        double b2A = (fx2 - fx1) / (x2 - x1);
        double b2 = (b2A - b1) / (x2 - x0);
        fx = b0 + b1 * (x - x0) + b2 * (x - x0) * (x - x1);
        return fx;
    }

    //Interpolation polynomials of Lagrange
    public double linearInterpolationLagrange(double x, double x0, double x1,
                                              double fx0, double fx1) {
        double fx = 0;
        double a = ((x - x1) / (x0 - x1)) * fx0;
        double b = ((x - x0) / (x1 - x0)) * fx1;
        fx = a + b;
        return fx;
    }

    public double quadraticInterpolationLagrange(double x, double x0, double x1,
                                                 double x2, double fx0, double fx1, double fx2) {
        double fx = 0;
        double a = (((x - x1) * (x - x2)) / ((x0 - x1) * (x0 - x2))) * fx0;
        double b = (((x - x0) * (x - x2)) / ((x1 - x0) * (x1 - x2))) * fx1;
        double c = (((x - x0) * (x - x1)) / ((x2 - x0) * (x2 - x1))) * fx2;
        fx = a + b + c;
        return fx;
    }

}
