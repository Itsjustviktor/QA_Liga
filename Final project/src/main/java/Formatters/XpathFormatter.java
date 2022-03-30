package Formatters;

public class XpathFormatter {
    public static String formatXpath(String dot, String rawPath, Object... args) {
        return String.format(dot + rawPath, args);
    }

}
