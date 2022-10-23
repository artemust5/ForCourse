package com.example.demo;

public class CheckSign {
    public static boolean parsingS(String str) {
        int check1 = 0;
        int check2 = 0;
        for (int i = 0; i < str.length(); i++) {
            String symbol = str.substring(i, i + 1);
            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j-1) == str.charAt(j) && !Character.isDigit(str.charAt(j-1)) && !Character.isDigit(str.charAt(j))) {
                    if (str.charAt(j-1) != '(' && str.charAt(j-1) != ')') return false;
                }
            }
            if (Character.isDigit(str.charAt(i))) {
                check1++;
            }
            if (symbol.equals("*") || symbol.equals("/") || symbol.equals("+") || symbol.equals("-")) {
                check2++;
            }
        }

        if (check1 > check2) {
            return true;
        } else {
            return false;
        }
    }
}
