package com.example.classproject1;

import java.util.HashMap;
import java.util.Map;

public abstract class Converter {

    private static final String[] CONSTANTS = {"million ", "thousand ", " ", "hundred "};

    private static final Map<String, String> NAMES = new HashMap<String, String>() {
        {
            put("1", "one ");
            put("2", "two ");
            put("3", "three ");
            put("4", "four ");
            put("5", "five ");
            put("6", "six ");
            put("7", "seven ");
            put("8", "eight ");
            put("9", "nine ");
            put("10", "ten ");
            put("11", "eleven ");
            put("12", "twelve ");
            put("13", "thirteen ");
            put("14", "fourteen ");
            put("15", "fifteen ");
            put("16", "sixteen ");
            put("17", "seventeen ");
            put("18", "eighteen ");
            put("19", "nineteen ");
            put("20", "twenty ");
            put("30", "thirty ");
            put("40", "forty ");
            put("50", "fifty ");
            put("60", "sixty ");
            put("70", "seventy ");
            put("80", "eighty ");
            put("90", "ninety ");
        }
    };

    /**
     * The main function that handles all the important inner method calls, segments the inputted String
     * into an array of 3 Strings and calls inner conversion methods
     *
     * @param inputtedText String
     * @return A String conversion of the inputted number, with the fist word starting with capital letter.
     */
    public static String convert(String inputtedText) {
        StringBuilder finalText = new StringBuilder();

        if (inputtedText.equals("0")) {
            return "Zero";
        }

        if (inputtedText.startsWith("-")) {
            inputtedText = inputtedText.substring(1);
            finalText.append("Negative ");
        }

        inputtedText = fill0s(inputtedText);

        String[] threeParts = {inputtedText.substring(0, 3), inputtedText.substring(3, 6), inputtedText.substring(6, 9)};

        for (int i = 0; i < 3; i++) {
            finalText.append(innerCleanConvert(threeParts[i], i));
        }

        return finalText.substring(0, 1).toUpperCase() + finalText.substring(1);
    }


    /**
     * Fill the inputted number with 0s at the beginning, so that the number always has exactly 9 digits.
     * For example, for input number 66779, the result will be: 000066779
     *
     * @param inputtedText String
     * @return The entered String with filled 0s at the beginning
     */
    private static String fill0s(String inputtedText) {
        String txt = "000000000" + inputtedText;
        return txt.substring(txt.length() - 9);
    }

    /**
     * The main inner function that calls other inner functions to make calculations.
     * Checks the cases when the inputted number has 0, 1, 2 or 3 digits and calls the corresponding
     * methods to execute the conversion.
     *
     * @param dirty3Digits String (3-digit number with possible starting 0's)
     * @param i            int
     * @return A String conversion with the name of the inputted 3-digit number, cleaned and left without starting 0's.
     */
    private static String innerCleanConvert(String dirty3Digits, int i) {
        if (dirty3Digits.startsWith("0")) {
            if (dirty3Digits.startsWith("00")) {
                if (dirty3Digits.equals("000")) {
                    return "";
                }
                return convert1Digit(dirty3Digits.substring(2)) + CONSTANTS[i];
            }
            return convert2Digits(dirty3Digits.substring(1)) + CONSTANTS[i];
        }
        return convert3Digits(dirty3Digits) + CONSTANTS[i];
    }

    /**
     * Convert the inputted 3 digit number into text
     *
     * @param threeDigits String (3-digit number)
     * @return A String conversion with the name of the inputted 3-digit number.
     */
    private static String convert3Digits(String threeDigits) {
        return NAMES.get(threeDigits.charAt(0) + "") + CONSTANTS[3] + convert2Digits(threeDigits.substring(threeDigits.length() - 2));
    }

    /**
     * Convert the inputted 2 digit number into text
     *
     * @param twoDigits String (2-digit number)
     * @return A String conversion with the name of the inputted 2-digit number.
     */
    private static String convert2Digits(String twoDigits) {
        String tenth;

        if ((twoDigits.charAt(0) + "").equals("0")) {
            tenth = "";
            if (!(twoDigits.charAt(1) + "").equals("0")) {
                tenth = NAMES.get((twoDigits.charAt(1) + ""));
            }
        } else if ((twoDigits.charAt(0) + "").equals("1")) {
            tenth = NAMES.get((twoDigits.charAt(0) + "") + (twoDigits.charAt(1) + ""));
        } else if ((twoDigits.charAt(1) + "").equals("0")) {
            tenth = NAMES.get(twoDigits.charAt(0) + "0");
        } else {
            tenth = NAMES.get(twoDigits.charAt(0) + "0") + NAMES.get(twoDigits.charAt(1) + "");
        }

        return tenth;
    }

    /**
     * Convert the inputted 1 digit number into text
     *
     * @param oneDigit String (1-digit number)
     * @return A String conversion with the name of the inputted 1-digit number.
     */
    private static String convert1Digit(String oneDigit) {
        return NAMES.get(oneDigit + "");
    }


}
