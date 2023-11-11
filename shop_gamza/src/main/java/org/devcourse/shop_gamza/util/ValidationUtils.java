package org.devcourse.shop_gamza.util;

public final class ValidationUtils {
    private ValidationUtils() {
    }

    public static void requireNonNull(Object value, String message) {
         if(value == null) throw new IllegalArgumentException(message);
    }

    public static void requireNonNegative(Integer value, String message) {
        if(value < 0) throw new IllegalArgumentException(message);
    }
}
