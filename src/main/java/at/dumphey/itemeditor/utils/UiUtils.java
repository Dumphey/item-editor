package at.dumphey.itemeditor.utils;

import at.dumphey.itemeditor.ui.template.UiItem;
import org.bukkit.Material;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UiUtils {


    public static List<UiItem> sortedByMaterialAndName(Collection<UiItem> items) {
        return items.stream()
                .sorted(Comparator.<UiItem, Material>comparing(a -> a.render().getType())
                        .thenComparing(it -> ItemBuilder.getName(it.render(), false)))
                .collect(Collectors.toList());
    }

    public static <T> List<T> sortedByEnumName(Collection<T> values) {
        return values.stream()
                .sorted(Comparator.comparing(value -> {
                    try {
                        return (String) value.getClass().getMethod("name").invoke(value);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());
    }

    public static List<UiItem> sortedByName(Collection<UiItem> items) {
        return items.stream().sorted(Comparator.comparing(it -> ItemBuilder.getName(it.render(), false))).collect(
                Collectors.toList());
    }

}
