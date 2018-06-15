package droidmath.assistant;

import droidexpression.Evaluate;

public class Assistant {

    private Evaluate evalua = new Evaluate();

    public boolean validateFunction(String function) {
        if (evalua.balancedFunction(function) == false) {
            return false;
        }
        if (evalua.withoutUnacceptableValues(function) == false) {
            return false;
        }
        if (evalua.withoutUnacceptableVariables(function) == false) {
            return false;
        }
        return true;
    }

    public boolean isInteger(String number) {
        boolean isInteger = false;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                isInteger = false;
                i = number.length();
            } else {
                isInteger = true;
            }
        }
        return isInteger;
    }

    //Is the characteristic part from a number
    //10.57 -> 10 is the characteristic
    public String characteristicStr(String number) {
        String[] parts = number.split("\\.");
        return parts[0];
    }

    //Is the fractional part from a number
    //10.57 -> 57 is the mantissa
    public String mantissaStr(String number) {
        String[] parts = number.split("\\.");
        return parts[1];
    }

    //If double value dont have decimals, return integer number
    //else retur the number
    public String castValue(double value) {
        String[] number = String.valueOf(value).split("\\.");
        double decimals = Double.parseDouble(number[1]);
        if (decimals == 0.0) {
            return "" + ((int) value);
        }
        return "" + value;
    }

    public String clean(String str) {
        String newStr = str.trim();
        String strAssis = "";
        for (int i = 0; i < newStr.length(); i++) {
            if (newStr.charAt(i) != ' ') {
                strAssis += newStr.charAt(i);
            }
        }
        newStr = strAssis;
        return newStr;
    }

    //For system ecuations
    public double[][] vectorsA(int sizeSystem, String system) {
        double[][] vector = new double[sizeSystem][sizeSystem];
        String store = "", storeAssis = "";
        int i = 0;
        int j = 0;
        for (int Z = 0; Z < system.length(); Z++) {
            if (system.charAt(Z) != 'x' && system.charAt(Z) != 'y' && system.charAt(Z) != 'z'
                    && system.charAt(Z) != 'u' && system.charAt(Z) != 'w' && system.charAt(Z) != '='
                    && system.charAt(Z) != '\n') {
                store += system.charAt(Z);
            } else {
                if (system.charAt(Z) == '=') {
                    for (int aux = Z; aux < system.length(); aux++) {
                        if (system.charAt(aux) != '\n') {
                            Z++;
                        } else {
                            aux = system.length();
                            i++;
                            j = 0;
                        }
                    }
                } else {
                    if (store.equals("") || store.equals("+")) {
                        storeAssis = "1";
                    } else if (store.equals("-")) {
                        storeAssis = "-1";
                    } else {
                        storeAssis = store;
                    }
                    vector[i][j] = Double.parseDouble(storeAssis);
                    j++;
                    store = "";
                    storeAssis = "";
                }
            }
        }
        return vector;
    }

    public double[] vectorB(int sizeSystem, String systemaR) {
        String system = systemaR + "\n";
        double[] vector = new double[sizeSystem];
        int count = 0;
        String store = "";
        for (int i = 0; i < system.length(); i++) {
            if (system.charAt(i) == '=') {
                for (int j = i + 1; j < system.length(); j++) {
                    if (system.charAt(j) != '\n') {
                        store += system.charAt(j);
                    } else {
                        vector[count] = Double.parseDouble(store);
                        count++;
                        j = system.length();
                        store = "";
                    }
                }
            }
        }
        return vector;
    }

    public String showSystem(int option) {
        String systemShow = "";
        switch (option) {
            case 2:
                systemShow = "a1x+a1y=b1" + "\n"
                        + "a2x+a2y=b2";
                break;
            case 3:
                systemShow = "a1x+a1y+a1z=b1" + "\n"
                        + "a2x+a2y+a2z=b2" + "\n"
                        + "a3x+a3y+a3z=b3";
                break;
            case 4:
                systemShow = "a1x+a1y+a1z+a1u=b1" + "\n"
                        + "a2x+a2y+a2z+a2u=b2" + "\n"
                        + "a3x+a3y+a3z+a3u=b3" + "\n"
                        + "a4x+a4y+a4z+a4u=b4";
                break;
            case 5:
                systemShow = "a1x+a1y+a1z+a1u+a1w=b1" + "\n"
                        + "a2x+a2y+a2z+a2u+a2w=b2" + "\n"
                        + "a3x+a3y+a3z+a3u+a3w=b3" + "\n"
                        + "a4x+a4y+a4z+a4u+a4w=b4" + "\n"
                        + "a5x+a5y+a5z+a5u+a5w=b5";
                break;
            default:
                systemShow = "a1x+a1y=b1" + "\n"
                        + "a2x+a2y=b2";
                break;
        }
        return systemShow;
    }

}
