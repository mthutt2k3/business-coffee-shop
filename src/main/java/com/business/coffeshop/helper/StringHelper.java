package com.business.coffeshop.helper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StringHelper {
    /**
     * Converts a string of image filenames separated by ' | ' into a List<String>.
     * @param imageString The string containing image filenames separated by ' | '.
     * @return A List of image filenames.
     */
    public static List<String> convertStringToList(String imageString) {
        if (imageString == null || imageString.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(imageString.split("\\s*\\|\\s*"));
    }

    // Converts List<String> to a comma-separated String
    public static String convertListToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return String.join(" | ", list);
    }

    public static String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
    public static double parseCurrency(String currency) {
        try {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            return formatter.parse(currency).doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid currency format: " + currency);
        }
    }
}
