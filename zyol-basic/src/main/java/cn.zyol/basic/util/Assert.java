package cn.zyol.basic.util;

import java.util.Collection;

public class Assert {
    private Assert() {
    }

    public static void isNull(Object object, String message) throws Exception {
        if (object == null) {
            throwException(message);
        }
    }

    public static void notNull(Object object, String message) throws Exception {
        if (object != null) {
            throwException(message);
        }
    }

    public static void isTrue(boolean expression, String message) throws Exception {
        if (expression) {
            throwException(message);
        }
    }

    public static void notTrue(boolean expression, String message) throws Exception {
        if (!expression) {
            throwException(message);
        }
    }


    @SuppressWarnings("rawtypes")
    public static void isEmpty(Collection collection, String message) throws Exception {
        if (collection != null && !collection.isEmpty()) {
            throwException(message);
        }
    }


    @SuppressWarnings("rawtypes")
    public static void notEmpty(Collection collection, String message) throws Exception {
        if (collection == null || collection.isEmpty()) {
            throwException(message);
        }
    }

    public static void notEmpty(Object[] collection, String message) throws Exception {
        if (collection == null || collection.length == 0) {
            throwException(message);
        }
    }


    private static void throwException(String message) throws Exception {
        throw new Exception(message);
    }
}
