package at.dumphey.itemeditorplus.itemeditor.modules.utils.colors;

import at.dumphey.itemeditorplus.ui.UiPosition;
import at.dumphey.itemeditorplus.ui.items.BackUiItem;
import at.dumphey.itemeditorplus.ui.items.FillerUiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class ChooseColorUiScreen extends UiScreen {

    private Color defaultColor;
    private Runnable onCancelled;
    private final Consumer<Color> onColorChosen;

    public ChooseColorUiScreen(Player player, Color defaultColor, Runnable onCancelled, Consumer<Color> onColorChosen) {
        super(player, 45, "Choose color");
        this.defaultColor = defaultColor;
        this.onCancelled = onCancelled;
        this.onColorChosen = onColorChosen;
    }

    private Color[] generateColors(int n) {
        java.awt.Color[] cols = new java.awt.Color[n];
        for (int i = 0; i < n; i++) {
            cols[i] = java.awt.Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        }

        Color[] results = new Color[n];
        for (int i = 0; i < n; i++) {
            results[i] = Color.fromRGB(cols[i].getRed(), cols[i].getGreen(), cols[i].getBlue());
        }
        return results;
    }

    @Override
    protected void onUpdate() {
        Color[] colors = generateColors(36);
        for (int i = 0; i < colors.length; i++) {
            setItem(i, new ChooseColorUiItem(this, player, colors[i], onColorChosen));
        }

        if (defaultColor != null) {
            setItem(UiPosition.of(4, 5), new ChooseColorUiItem(this, player, defaultColor, onColorChosen));
        }
        setItem(UiPosition.of(4, 6), new ChooseCustomColorCodeUiItem(this, player, onColorChosen));
        setItem(UiPosition.of(4, 8),
                new BackUiItem(this, player, onCancelled));
        fillEmpty(UiPosition.of(4, 0), UiPosition.of(4, 8), new FillerUiItem(this, player));
    }
}
