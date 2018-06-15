package droidmath.arithmetic;

import droidmath.assistant.Assistant;

public class Numbers {

    private Assistant assistant = new Assistant();

    //receive a number string 1,2,3,4,5,...,etc
    public String leastCommonMultiple(String strNumbers) {
        String lcm = "";
        String storeNumbers = "";
        strNumbers += ",";
        int[] num = numbers(assistant.clean(strNumbers));
        int[][] multiples = multiples(num);
        int[] aux;
        int[] initial = multiples[0];
        for (int i = 1; i < num.length; i++) {
            aux = multiples[i];
            for (int j = 0; j < initial.length; j++) {
                for (int k = 0; k < aux.length; k++) {
                    if (initial[j] == aux[k]) {
                        storeNumbers += initial[j] + ",";
                    }
                }
            }
            initial = numbers(storeNumbers);
            storeNumbers = "";
        }
        lcm = "" + initial[0];
        return lcm;
    }

    //receive a number string 1,2,3,4,5,...,etc
    public String greatestCommonDivisor(String strNumbers) {
        String gcd = "";
        String storeNumbers = "";
        strNumbers += ",";
        int[] num = numbers(assistant.clean(strNumbers));
        int[][] dividers = dividers(num);
        int[] aux;
        int[] initial = dividers[0];
        for (int i = 1; i < num.length; i++) {
            aux = dividers[i];
            for (int j = 0; j < initial.length; j++) {
                for (int k = 0; k < aux.length; k++) {
                    if (initial[j] == aux[k]) {
                        storeNumbers += initial[j] + ",";
                    }
                }
            }
            initial = numbers(storeNumbers);
            storeNumbers = "";
        }
        gcd = "" + initial[initial.length - 1];
        return gcd;
    }

    public String numbersFactorization(String strNumbers) {
        String factor = "", factor2 = "", factor3 = "";
        int[] vec = numerosVec(assistant.clean(strNumbers));
        for (int i = 0; i < vec.length; i++) {
            factor = vec[i] + " = " + factorization(vec[i]);
            factor2 += factor.substring(0, factor.length() - 2) + "\n";//remove last comma
        }
        factor3 = factor2.substring(0, factor2.length() - 1);
        return factor3;
    }

    //Screening of eratostenes
    public String screeningOfEratostenes(int init, int fin, int jump) {
        String screening = "";
        String store = "";
        boolean isPrime = false;
        int count = 1;
        if (init >= 0 && fin >= 0 && fin > init && jump >= 1) {
            for (int i = init; i <= fin; i++) {
                isPrime = isPrime(i);
                if (isPrime) {
                    store += "'" + i + "'\t";
                } else {
                    store += i + "\t";
                }

                if (count % jump == 0) {
                    screening += store + "\n";
                    store = "";
                }
                count++;
            }
        } else {
            screening = "ERROR";
        }
        return screening;
    }

    //for a numbers string, show that is prime or not
    public String primes(String strPrimes) {
        String res = "", res2 = "";
        boolean isPrime = false;
        int[] num = numerosVec(assistant.clean(strPrimes));
        int number = 0;
        for (int i = 0; i < num.length; i++) {
            number = num[i];
            isPrime = isPrime(number);
            if (isPrime) {
                res += "The number " + number + " is PRIME" + "\n";
            } else {
                res += "The number " + number + " is not PRIME" + "\n";
            }
        }
        res2 = res.substring(0, res.length() - 1);
        return res2;
    }

    public double average(String strNumbers) {
        double ave = 0;
        try {
            ave = numbersAddition(assistant.clean(strNumbers)) / sizeVec(assistant.clean(strNumbers));
        } catch (Exception err) {
            ave = Double.NaN;
        }
        return ave;
    }

    public int min(String strNumbers) {
        int min = 0;
        int[] vec = numerosVec(assistant.clean(strNumbers));
        min = vec[0];
        for (int i = 0; i < vec.length; i++) {
            if (vec[i] < min) {
                min = vec[i];
            }
        }
        return min;
    }

    public int max(String strNumbers) {
        int max = 0;
        int[] vec = numerosVec(assistant.clean(strNumbers));
        max = vec[0];
        for (int i = 0; i < vec.length; i++) {
            if (vec[i] > max) {
                max = vec[i];
            }
        }
        return max;
    }

    public boolean isPrime(int number) {
        boolean v = false;
        int div = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                div++;
            }
        }
        if (div == 2) {
            v = true;
        } else {
            v = false;
        }
        return v;
    }

    private double numbersAddition(String strNumbers) {
        double addition = 0;
        int[] vec = numerosVec(strNumbers);
        for (int i = 0; i < vec.length; i++) {
            addition += vec[i];
        }
        return addition;
    }

    private int[][] multiples(int[] numbers) {
        int[][] mult = new int[numbers.length][];
        int[] multNum = null;
        String store = "";
        int value = 0;
        for (int i = 0; i < numbers.length; i++) {
            value = numbers[i];
            for (int j = value; j < 999 * value; j += value) {
                store += j + ",";
            }
            multNum = numbers(store);
            store = "";
            mult[i] = multNum;
        }
        return mult;
    }

    private int[][] dividers(int[] numbers) {
        int[][] div = new int[numbers.length][];
        int[] divNum = null;
        String store = "";
        int value = 0;
        for (int i = 0; i < numbers.length; i++) {
            value = numbers[i];
            for (int j = 1; j <= value; j++) {
                if (value % j == 0) {
                    store += j + ",";
                }
            }
            divNum = numbers(store);
            store = "";
            div[i] = divNum;
        }
        return div;
    }

    //receive a string 1,2,3,4,5,6,...etc
    private int[] numbers(String str) {
        int[] num = new int[size(str)];
        String store = "";
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ',') {
                store += str.charAt(i);
            } else {
                num[count] = Integer.parseInt(store);
                store = "";
                count++;
            }
        }
        return num;
    }

    private int size(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                count++;
            }
        }
        return count;
    }

    private String factorization(int number) {
        String vector = "";
        if (number == 1) {
            vector += "1, ";
        } else if (number < 0) {
            vector += ", ";
        } else {
            for (int i = 2; i <= number; i++) {
                if (isPrime(number) == true) {
                    vector += number + ", ";
                    i = number;
                } else if (number % i == 0) {
                    vector += i + ", ";
                    number = number / i;
                    i = 1;
                }
            }
        }
        return vector;
    }

    //receive a string 1,2,3,4,5,6,...etc
    private int[] numerosVec(String str) {
        int[] num = new int[sizeVec(str)];
        str += ",";
        String store = "";
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ',') {
                store += str.charAt(i);
            } else {
                num[count] = Integer.parseInt(store);
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
