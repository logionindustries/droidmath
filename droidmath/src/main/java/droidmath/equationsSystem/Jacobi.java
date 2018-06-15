package droidmath.equationsSystem;

import droidmath.assistant.Assistant;

public class Jacobi {

    private Assistant assistant = new Assistant();

    public String jacobiEcuation2(String system, double error) {
        String system2 = assistant.clean(system);
        double[][] A = assistant.vectorsA(2, system2);
        double[] B = assistant.vectorB(2, system2);
        double epsilon = error;
        int count = 0;
        String show = "", show2 = "";
        double x = 0, y = 0;
        double X = 0, Y = 0;
        double epsilonX = 0;
        double epsilonY = 0;

        do {
            x = X;
            y = Y;

            X = (B[0] - A[0][1] * y) / A[0][0];
            Y = (B[1] - A[1][0] * x) / A[1][1];

            epsilonX = Math.abs((X - x) / X) * 100;
            epsilonY = Math.abs((Y - y) / Y) * 100;

            count++;
            show += "Iteration= " + count + "\n"
                    + "x = " + X + "\ny = " + Y + "\n"
                    + "EX= " + epsilonX + "\nEY= " + epsilonY + "\n**********";

            show2 += show + "\n\n";
            show = "";
        } while ((epsilonX > epsilon) || (epsilonY > epsilon));

        return show2;
    }

    public String jacobiEcuation3(String system, double error) {
        String system2 = assistant.clean(system);
        double[][] A = assistant.vectorsA(3, system2);
        double[] B = assistant.vectorB(3, system2);
        double epsilon = error;
        int count = 0;
        String show = "", show2 = "";
        double x = 0, y = 0, z = 0;
        double X = 0, Y = 0, Z = 0;
        double epsilonX = 0;
        double epsilonY = 0;
        double epsilonZ = 0;

        do {
            x = X;
            y = Y;
            z = Z;

            X = (B[0] - A[0][1] * y - A[0][2] * z) / A[0][0];
            Y = (B[1] - A[1][0] * x - A[1][2] * z) / A[1][1];
            Z = (B[2] - A[2][0] * x - A[2][1] * y) / A[2][2];

            epsilonX = Math.abs((X - x) / X) * 100;
            epsilonY = Math.abs((Y - y) / Y) * 100;
            epsilonZ = Math.abs((Z - z) / Z) * 100;

            count++;
            show += "Iteration= " + count + "\n"
                    + "x = " + X + "\ny = " + Y + "\nz = " + Z + "\n"
                    + "EX= " + epsilonX + "\nEY= " + epsilonY + "\nEZ= " + epsilonZ + "\n**********";

            show2 += show + "\n\n";
            show = "";
        } while ((epsilonX > epsilon) || (epsilonY > epsilon) || (epsilonZ > epsilon));

        return show2;
    }

    public String jacobiEcuation4(String system, double error) {
        String system2 = assistant.clean(system);
        double[][] A = assistant.vectorsA(4, system2);
        double[] B = assistant.vectorB(4, system2);
        double epsilon = error;
        int count = 0;
        String show = "", show2 = "";
        double x = 0, y = 0, z = 0, u = 0;
        double X = 0, Y = 0, Z = 0, U = 0;
        double epsilonX = 0;
        double epsilonY = 0;
        double epsilonZ = 0;
        double epsilonU = 0;

        do {
            x = X;
            y = Y;
            z = Z;
            u = U;

            X = (B[0] - A[0][1] * y - A[0][2] * z - A[0][3] * u) / A[0][0];
            Y = (B[1] - A[1][0] * x - A[1][2] * z - A[1][3] * u) / A[1][1];
            Z = (B[2] - A[2][0] * x - A[2][1] * y - A[2][3] * u) / A[2][2];
            U = (B[3] - A[3][0] * x - A[3][1] * y - A[3][2] * z) / A[3][3];

            epsilonX = Math.abs((X - x) / X) * 100;
            epsilonY = Math.abs((Y - y) / Y) * 100;
            epsilonZ = Math.abs((Z - z) / Z) * 100;
            epsilonU = Math.abs((U - u) / U) * 100;

            count++;
            show += "Iteration= " + count + "\n"
                    + "x = " + X + "\ny = " + Y + "\nz = " + Z + "\nu = " + U + "\n"
                    + "EX= " + epsilonX + "\nEY= " + epsilonY + "\nEZ= " + epsilonZ
                    + "\nEU= " + epsilonU + "\n**********";
            show2 += show + "\n\n";
            show = "";
        } while ((epsilonX > epsilon) || (epsilonY > epsilon) || (epsilonZ > epsilon) || (epsilonU > epsilon));

        return show2;
    }

    public String jacobiEcuation5(String system, double error) {
        String system2 = assistant.clean(system);
        double[][] A = assistant.vectorsA(5, system2);
        double[] B = assistant.vectorB(5, system2);
        double epsilon = error;
        int count = 0;
        String show = "", show2 = "";
        double x = 0, y = 0, z = 0, u = 0, w = 0;
        double X = 0, Y = 0, Z = 0, U = 0, W = 0;
        double epsilonX = 0;
        double epsilonY = 0;
        double epsilonZ = 0;
        double epsilonU = 0;
        double epsilonW = 0;

        do {
            x = X;
            y = Y;
            z = Z;
            u = U;
            w = W;

            X = (B[0] - A[0][1] * y - A[0][2] * z - A[0][3] * u) - A[0][4] * w / A[0][0];
            Y = (B[1] - A[1][0] * x - A[1][2] * z - A[1][3] * u - A[1][4] * w) / A[1][1];
            Z = (B[2] - A[2][0] * x - A[2][1] * y - A[2][3] * u - A[2][4] * w) / A[2][2];
            U = (B[3] - A[3][0] * x - A[3][1] * y - A[3][2] * z - A[3][4] * w) / A[3][3];
            W = (B[4] - A[4][0] * x - A[4][1] * y - A[4][2] * z - A[4][3] * u) / A[4][4];

            epsilonX = Math.abs((X - x) / X) * 100;
            epsilonY = Math.abs((Y - y) / Y) * 100;
            epsilonZ = Math.abs((Z - z) / Z) * 100;
            epsilonU = Math.abs((U - u) / U) * 100;
            epsilonW = Math.abs((W - w) / W) * 100;

            count++;
            show += "Iteration= " + count + "\n"
                    + "x = " + X + "\ny = " + Y + "\nz = " + Z + "\nu = " + U + "\nw = " + W + "\n"
                    + "EX= " + epsilonX + "\nEY= " + epsilonY + "\nEZ= " + epsilonZ + "\nEU= " + epsilonU
                    + "\nEW= " + epsilonW + "\n**********";
            show2 += show + "\n\n";
            show = "";
        } while ((epsilonX > epsilon) || (epsilonY > epsilon) || (epsilonZ > epsilon) || (epsilonU > epsilon) || (epsilonW > epsilon));

        return show2;
    }

}
