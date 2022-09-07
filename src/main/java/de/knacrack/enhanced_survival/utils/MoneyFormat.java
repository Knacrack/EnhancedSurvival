package de.knacrack.enhanced_survival.utils;

import java.text.DecimalFormat;

public class MoneyFormat {

    static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");

    public static DecimalFormat getDecimalFormat() {
        return DECIMAL_FORMAT;
    }

    public static String getFormated(long amount) { return DECIMAL_FORMAT.format(Utils.round(amount, 2)); }
    public static String getFormated(double amount) { return DECIMAL_FORMAT.format(Utils.round(amount, 2)); }
    public static String getFormated(int amount) { return DECIMAL_FORMAT.format(Utils.round(amount, 2)); }

}