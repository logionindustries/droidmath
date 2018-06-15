package droidmath.numericSystem;

import droidmath.assistant.Assistant;

public class NumericSystemDecimal {

    private Assistant assistant = new Assistant();

    public String decimalToBinary(String number, int precision) {
        return decimalToOtherSystem(number, precision, 2);
    }

    public String decimalToOctal(String number, int precision) {
        return decimalToOtherSystem(number, precision, 8);
    }

    public String decimalToHexadecimal(String number, int precision) {
        return decimalToOtherSystem(number, precision, 16);
    }

    private String decimalToOtherSystem(String number, int precision, int system) {
        if (!isDecimal(number)) {
            return "ERROR";
        }
        boolean isInteger = assistant.isInteger(number);
        String storeMantissa = "", storeCharacteristic = "";
        int characteristic = characteristic(number), module = 0;
        double mantissa, mantissaAssis;
        //Convert integer part to system specified
        while (characteristic > 0) {
            module = characteristic % system;
            if (system == 2 || system == 8) {//binary or octal
                storeCharacteristic += module;
            } else if (system == 16) {//hexadecimal
                if (module == 10) {
                    storeCharacteristic += "A";
                } else if (module == 11) {
                    storeCharacteristic += "B";
                } else if (module == 12) {
                    storeCharacteristic += "C";
                } else if (module == 13) {
                    storeCharacteristic += "D";
                } else if (module == 14) {
                    storeCharacteristic += "E";
                } else if (module == 15) {
                    storeCharacteristic += "F";
                } else {
                    storeCharacteristic += module;
                }
            }
            characteristic = characteristic / system;
        }
        //Invert store
        storeCharacteristic = invertStr(storeCharacteristic);
        //Convert decimal part to system specified
        if (isInteger == false) {
            int mantissaInteger = 0;
            mantissa = Double.parseDouble(assistant.mantissaStr(number))
                    / Math.pow(10, assistant.mantissaStr(number).length());
            for (int b = 0; b < precision; b++) {//n decimals precision
                mantissaAssis = mantissa * system;
                mantissaInteger = (int) mantissaAssis;
                if (system == 2 || system == 8) {//binary or octal
                    storeMantissa += mantissaInteger;
                } else if (system == 16) {//hexadecimal
                    if (mantissaInteger == 10) {
                        storeMantissa += "A";
                    } else if (mantissaInteger == 11) {
                        storeMantissa += "B";
                    } else if (mantissaInteger == 12) {
                        storeMantissa += "c";
                    } else if (mantissaInteger == 13) {
                        storeMantissa += "D";
                    } else if (mantissaInteger == 14) {
                        storeMantissa += "E";
                    } else if (mantissaInteger == 15) {
                        storeMantissa += "F";
                    } else {
                        storeMantissa += mantissaInteger;
                    }
                }
                mantissa = mantissa(String.valueOf(mantissaAssis));
            }
        }
        if (isInteger == true) {
            return storeCharacteristic;
        } else {
            return (storeCharacteristic + "." + storeMantissa);
        }
    }

    public boolean isDecimal(String number) {
        boolean itsOk = true;
        char c = ' ';
        char[] digits = {'.', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '0'};
        for (int i = 0; i < number.length(); i++) {
            c = number.charAt(i);
            for (int k = 0; k < digits.length; k++) {
                char j = digits[k];
                if (c == j) {
                    k = digits.length;
                    itsOk = true;
                } else {
                    itsOk = false;
                }
            }
            if (itsOk == false) {
                i = number.length();
            }
        }
        return itsOk;
    }

    //Is the characteristic part from a number
    //10.57 -> 10 is the characteristic
    private int characteristic(String number) {
        String[] parts = number.split("\\.");
        return Integer.parseInt(parts[0]);
    }

    //Is the fractional part from a number
    //10.57 -> 57 is the mantissa
    private double mantissa(String number) {
        String[] parts = number.split("\\.");
        return Double.parseDouble("." + parts[1]);
    }

    private String invertStr(String str) {
        String store = "";
        for (int x = str.length() - 1; x >= 0; x--) {
            store += str.charAt(x);
        }
        return store;
    }

}
