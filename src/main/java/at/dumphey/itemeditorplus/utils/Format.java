package at.dumphey.itemeditorplus.utils;

public class Format {

    public static String description(String description) {
        return String.format("§8§l* §8%s", description);
    }

    public static String name(String name) {
        return String.format("§e%s", name);
    }

    public static String descriptionContinued(String descriptionContinued) {
        return String.format("  §8%s", descriptionContinued);
    }
}
