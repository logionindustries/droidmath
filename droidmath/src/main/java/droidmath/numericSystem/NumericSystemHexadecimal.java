package droidmath.numericSystem;

import droidmath.assistant.Assistant;

public class NumericSystemHexadecimal {

    private Assistant assistant = new Assistant();
    private NumericSystemDecimal nd = new NumericSystemDecimal();

    public String hexadecimalToDecimal(String number) {
        if (!isHexadecimal(number)) {
            return "ERROR";
        }
        double addInteger = 0, addDecimal = 0;
        String characteristic = assistant.characteristicStr(number);
        String mantissa = assistant.mantissaStr(number);
        double potency = 0;
        //Evaluate integer part
        potency = characteristic.length() - 1;
        for (int i = 0; i < characteristic.length(); i++) {
            String hexToken = String.valueOf(characteristic.charAt(i));
            double numberInt = 0;
            if (hexToken.equals("A")) {
                numberInt = 10.0;
            } else if (hexToken.equals("B")) {
                numberInt = 11.0;
            } else if (hexToken.equals("C")) {
                numberInt = 12.0;
            } else if (hexToken.equals("D")) {
                numberInt = 13.0;
            } else if (hexToken.equals("E")) {
                numberInt = 14.0;
            } else if (hexToken.equals("F")) {
                numberInt = 15.0;
            } else {
                numberInt = Double.parseDouble(hexToken);
            }
            addInteger += (numberInt * Math.pow(16, potency));
            potency--;
        }
        //Evaluate decimal part
        potency = -1;
        for (int j = 0; j < mantissa.length(); j++) {
            String hexToken = String.valueOf(mantissa.charAt(j));
            double numberDec = 0;
            if (hexToken.equals("A")) {
                numberDec = 10.0;
            } else if (hexToken.equals("B")) {
                numberDec = 11.0;
            } else if (hexToken.equals("C")) {
                numberDec = 12.0;
            } else if (hexToken.equals("D")) {
                numberDec = 13.0;
            } else if (hexToken.equals("E")) {
                numberDec = 14.0;
            } else if (hexToken.equals("F")) {
                numberDec = 15.0;
            } else {
                numberDec = Double.parseDouble(hexToken);
            }
            addDecimal += (numberDec * Math.pow(16, potency));
            potency--;
        }

        if (assistant.isInteger(number) == true) {
            return assistant.castValue(addInteger);
        } else {
            return assistant.castValue(addInteger + addDecimal);
        }
    }

    public String hexadecimalToBinary(String number, int precision) {
        if (!isHexadecimal(number)) {
            return "ERROR";
        }
        String decimal = hexadecimalToDecimal(number);
        return nd.decimalToBinary("" + decimal, precision);
    }

    public String hexadecimalToOctal(String number, int precision) {
        if (!isHexadecimal(number)) {
            return "ERROR";
        }
        String decimal = hexadecimalToDecimal(number);
        return nd.decimalToOctal("" + decimal, precision);
    }

    public boolean isHexadecimal(String number) {
        boolean itsOk = true;
        char c = ' ';
        char[] digits = {'.', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '0', 'A', 'B', 'C',
                'D', 'E', 'F'};
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
