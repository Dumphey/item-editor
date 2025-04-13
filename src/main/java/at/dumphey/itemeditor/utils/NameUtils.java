package at.dumphey.itemeditor.utils;


import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NameUtils {

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Arrays.stream(str.split(" "))
                .map(part -> part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static String enumToFriendlyName(String $enum) {
        return capitalize($enum.replace("_", " ").toLowerCase());
    }

    public static String enumToFriendlyName(Object $enum) {
        String name;

        try {
            name = (String) $enum.getClass().getMethod("name").invoke($enum);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return enumToFriendlyName(name);
    }

}
