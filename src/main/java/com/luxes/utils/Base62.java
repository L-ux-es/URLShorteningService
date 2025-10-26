package com.luxes.utils;

import java.security.SecureRandom;

public class Base62 {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();
    private static final SecureRandom random = new SecureRandom();

    private Base62() {
        throw new IllegalStateException("Utility class");
    }

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt((int) (num % BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static String encodeWithPadding(long value) {
        StringBuilder code = new StringBuilder(encode(value));
        while (code.length() < 7) {
            code.append(ALPHABET.charAt(random.nextInt(BASE)));
        }
        return code.toString();
    }
}

