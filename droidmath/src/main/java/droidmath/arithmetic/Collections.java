package droidmath.arithmetic;

import droidmath.assistant.Assistant;

public class Collections {

    private Assistant assistant = new Assistant();

    public String union(String setA, String setB) {
        String[] A = fillVectors(assistant.clean(setA));
        String[] B = fillVectors(assistant.clean(setB));
        String[] C = fussion(A, B);

        String[] fussion = deleteRepeatElements(C);
        String[] assis = new String[size(fussion)];
        int count = 0;
        for (int i = 0; i < fussion.length; i++) {
            if (!(fussion[i].equals(""))) {
                assis[count] = fussion[i];
                count++;
            }
        }
        String store = "", union = "";
        for (int i = 0; i < assis.length; i++) {
            store += "" + assis[i] + ", ";
        }
        union = store.substring(0, store.length() - 2);//remove ,[space]
        return union;
    }

    public String intersection(String setA, String setB) {
        String[] A = fillVectors(assistant.clean(setA));
        String[] B = fillVectors(assistant.clean(setB));
        String store = "", intersection = "";
        for (int a = 0; a < A.length; a++) {
            for (int b = 0; b < B.length; b++) {
                if (A[a].equals(B[b])) {
                    store += "" + A[a] + ", ";
                }
            }
        }
        if (!(store.equals(""))) {
            intersection = store.substring(0, store.length() - 2);//remove ,[space]
        } else {
            intersection = "";
        }
        return intersection;
    }

    public String difference(String setA, String setB) {
        String[] A = fillVectors(assistant.clean(setA));
        String[] B = fillVectors(assistant.clean(setB));
        String store = "", difference = "";
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i].equals(B[j])) {
                    count++;
                }
            }
            if (count == 0) {
                store += "" + A[i] + ", ";
            }
            count = 0;
        }
        difference = store.substring(0, store.length() - 2);//remove ,[space]
        return difference;
    }

    public String symmetricDifference(String setA, String setB) {
        String[] A = fillVectors(assistant.clean(setA));
        String[] B = fillVectors(assistant.clean(setB));
        String store1 = "", store2 = "", symmetricDifference = "";
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i].equals(B[j])) {
                    count++;
                }
            }
            if (count == 0) {
                store1 += "" + A[i] + ", ";
            }
            count = 0;
        }
        store2 += store1;
        store1 = "";
        for (int k = 0; k < B.length; k++) {
            for (int l = 0; l < A.length; l++) {
                if (B[k].equals(A[l])) {
                    count++;
                }
            }
            if (count == 0) {
                store1 += "" + B[k] + ", ";
            }
            count = 0;
        }
        store2 += store1;
        symmetricDifference = store2.substring(0, store2.length() - 2);
        return symmetricDifference;
    }

    public String cartesianProduct(String setA, String setB) {
        String[] A = fillVectors(assistant.clean(setA));
        String[] B = fillVectors(assistant.clean(setB));
        String store1 = "", store2 = "", cartesianProduct = "";
        String a = "; ";
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (j < B.length - 1) {
                    store1 += "{" + A[i] + ", " + B[j] + "}" + a;
                } else {
                    store1 += "{" + A[i] + ", " + B[j] + "}";
                }
            }
            store2 += store1 + "\n";
            store1 = "";
        }
        cartesianProduct = store2.substring(0, store2.length() - 1);//remove last element
        return cartesianProduct;

    }

    private String[] fussion(String[] A, String[] B) {
        String[] C = new String[A.length + B.length];
        int count = 0;
        for (int a = 0; a < A.length; a++) {
            C[count] = A[a];
            count++;
        }
        for (int b = 0; b < B.length; b++) {
            C[count] = B[b];
            count++;
        }
        return C;
    }

    private String[] deleteRepeatElements(String[] vec) {
        String element = "";
        for (int i = 0; i < vec.length; i++) {
            element = vec[i];
            if (element.equals("")) {
                vec[i] = "";
            } else {
                for (int j = (i + 1); j < vec.length; j++) {
                    if (element.equals(vec[j])) {
                        vec[j] = "";
                    }
                }
            }
        }
        return vec;
    }

    private int size(String[] vec) {
        int size = 0;
        for (int i = 0; i < vec.length; i++) {
            if (!(vec[i].equals(""))) {
                size++;
            }
        }
        return size;
    }

    private int sizeVector(String set) {
        int count = 0;
        for (int j = 0; j < set.length(); j++) {
            if (set.charAt(j) == ',') {
                count++;
            }
        }
        return count;
    }

    private String[] fillVectors(String set) {
        set += ",";
        String[] sets = new String[sizeVector(set)];
        String store = "";
        int count = 0;
        for (int i = 0; i < set.length(); i++) {
            if (set.charAt(i) != ',') {
                store += set.charAt(i);
            } else {
                sets[count] = store;
                store = "";
                count++;
            }
        }
        return sets;
    }

}
