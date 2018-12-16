package org.khai.learning.engine.math;

import java.util.Locale;

public abstract class MathUtils {
    private MathUtils() { }

    public static double round4(double x) {
        final double K = 1e4;
        return (long) (x * K + 0.5) / K;
    }

    public static double roundExp4(double x) {
        String s = String.format(Locale.US, "%.4e", x);
        return Double.valueOf(s);
    }
}
