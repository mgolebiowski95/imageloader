package com.examples.imageloaderlibrary.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Mariusz Gołębiowski
 */
public class PrettyPrint {
    public static String percentOf(double a, double b) {
        return formatNum((a / b) * 100, "0.##") + "%";
    }

    public static String bytes(long byteSize, BinaryUnit unit){
        return formatNum(byteSize / Math.pow(1024, unit.ordinal()), "0.##") + unit.toString();
    }

    public static String bytes(long byteSize) {
        double scaledByteSize = byteSize;
        BinaryUnit unit = BinaryUnit.B;
        while (scaledByteSize >= 1024d){
            scaledByteSize /= 1024d;
            unit = BinaryUnit.values()[unit.next().ordinal()];
        }
        return formatNum(scaledByteSize, "0.##") + unit.toString();
    }

    private static String formatNum(double number, String formatString) {
        final DecimalFormat format = new DecimalFormat(formatString);
        format.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(number);
    }

    public enum BinaryUnit {
        B,
        KiB,
        MiB,
        GiB,
        TiB,
        PiB,
        EiB,
        ZiB,
        YiB;

        public BinaryUnit next() {
            return values()[ordinal() + 1];
        }
    }
}
