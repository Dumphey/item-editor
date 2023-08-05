package at.dumphey.itemeditorplus.itemeditor.modules.utils.colors;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ChooseCustomColorCodeUiItem extends UiItem {

    private final Consumer<Color> onColorChosen;

    public ChooseCustomColorCodeUiItem(UiScreen screen, Player player, Consumer<Color> onColorChosen) {
        super(screen, player);
        this.onColorChosen = onColorChosen;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(Material.CAULDRON)
                          .withName("§eEnter custom color code")
                          .withLore("§8§l* §8Enter a custom hex color code", "  §8like §7#7AE7C7")
                          .build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().getScreen(player).hide();
            prompt(new EnterColorCodePrompt(player, () -> {
                ItemEditor.INSTANCE.getUiManager().getScreen(player).show();
            }, (hex) -> onColorChosen.accept(Color.fromRGB(Integer.valueOf(hex, 16)))));
        }
    }
}
