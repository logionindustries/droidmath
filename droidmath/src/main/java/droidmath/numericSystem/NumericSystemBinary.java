package droidmath.numericSystem;

import droidmath.assistant.Assistant;

public class NumericSystemBinary {

    private Assistant assistant = new Assistant();
    private NumericSystemDecimal nd = new NumericSystemDecimal();

    public String binaryToDecimal(String number) {
        if (!isBinary(number)) {
            return "ERROR";
        }
        double addInteger = 0, addDecimal = 0;
        String characteristic = assistant.characteristicStr(number);
        String mantissa = assistant.mantissaStr(number);
        double potency = 0;

        //Evaluate integer part
        potency = characteristic.length() - 1;
        for (int i = 0; i < characteristic.length(); i++) {
            double numberInt = Double.parseDouble(String.valueOf(characteristic.charAt(i)));
            addInteger += (numberInt * Math.pow(2, potency));
            potency--;
        }
        //Evaluate decimal part
        potency = -1;
        for (int j = 0; j < mantissa.length(); j++) {
            double numberDec = Double.parseDouble(String.valueOf(mantissa.charAt(j)));
            addDecimal += (numberDec * Math.pow(2, potency));
            potency--;
        }

        if (assistant.isInteger(number) == true) {
            return assistant.castValue(addInteger);
        } else {
            return assistant.castValue(addInteger + addDecimal);
        }
    }

    public String binaryToOctal(String number, int precision) {
        if (!isBinary(number)) {
            return "ERROR";
        }
        String decimal = "" + binaryToDecimal(number);
        return nd.decimalToOctal("" + decimal, precision);
    }

    public String binaryToHexadecimal(String number, int precision) {
        if (!isBinary(number)) {
            return "ERROR";
        }
        String decimal = binaryToDecimal(number);
        return nd.decimalToHexadecimal("" + decimal, precision);
    }

    public boolean isBinary(String number) {
        boolean itsOk = true;
        char c = ' ';
        char[] digits = {'.', '1', '0'};
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

}
