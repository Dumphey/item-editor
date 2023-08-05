package at.dumphey.itemeditorplus.itemeditor.modules.book.screens.generation;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.book.screens.manage.ManageBookUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.Messages;
import at.dumphey.itemeditorplus.utils.NameUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class SetBookGenerationUiItem extends UiItem {

    private final BookMeta.Generation generation;

    public SetBookGenerationUiItem(UiScreen screen, Player player, BookMeta.Generation generation) {
        super(screen, player);
        this.generation = generation;
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.WRITTEN_BOOK, NameUtils.enumToFriendlyName(generation), "Click to set");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.modifyItem(player, item -> ItemBuilder.of(item).editMeta(meta -> {
                BookMeta bookMeta = (BookMeta) meta;
                bookMeta.setGeneration(generation);
            }).build());
            Messages.send(player, "Book generation set to: §o" + NameUtils.enumToFriendlyName(generation));
            open(new ManageBookUiScreen(player));
        }
    }
}
