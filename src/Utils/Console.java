package Utils;

import java.util.HashMap;

public class Console {
    private static final HashMap<String, String> colours = new HashMap<>() {{
        put("Red", "\u001B[31m");
        put("Blue", "\u001B[34m");
        put("Green", "\u001B[32m");
        put("Yellow", "\u001B[33m");
        put("Reset", "\u001B[0m");
    }};

    public static void log(String message, String name) {
        try {
            System.out.printf("%s%s%s%n", colours.get(name), message, colours.get("Reset"));
        } catch (NullPointerException e) {
            System.out.println(message);
        }
    }
}
