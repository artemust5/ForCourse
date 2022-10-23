package com.example.demo;

public class CheckBrackets {
    public static boolean parsingB(String str) {
        int check = 0;
        for (int i = 0; i < str.length(); i++) {
            if (check < 0) {
                return false;
            }
            String symbol = str.substring(i, i + 1);
            if (symbol.equals("(")) {
                check++;
                continue;
            }
            if (symbol.equals(")"))
                check--;
        }
        if (check == 0) {
            return true;
        } else {
            return false;
        }
    }
}
