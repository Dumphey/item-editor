package at.dumphey.itemeditor.itemeditor.modules.lore.screens.set;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import at.dumphey.itemeditor.ui.template.UiItem;
import at.dumphey.itemeditor.ui.template.UiScreen;
import at.dumphey.itemeditor.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SetLoreLineUiItem extends UiItem {
    private final int index;
    private final String content;

    public SetLoreLineUiItem(UiScreen screen, Player player, int index, String content) {
        super(screen, player);
        this.index = index;
        this.content = content;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.of(Material.PAPER).withName("§eLine §l" + (index + 1)).withLore("", content, "", "§8§l* §8Click to §fchange §8this line.").build();
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getPromptHandler().setPrompt(player, new SetLoreLinePrompt(player, index));
        }
    }
}
