package at.dumphey.itemeditorplus.commands;

import at.dumphey.itemeditorplus.ItemEditorPlus;
import at.dumphey.itemeditorplus.compatibility.CompatUtils;
import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.list.ItemListUiScreen;
import at.dumphey.itemeditorplus.itemeditor.storage.SerializedItem;
import at.dumphey.itemeditorplus.utils.Messages;
import at.dumphey.itemeditorplus.utils.NameUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemEditorCommand implements CommandExecutor, TabCompleter {

    private final ItemEditor itemEditor;

    public ItemEditorCommand() {
        this.itemEditor = ItemEditor.INSTANCE;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final boolean isPlayer = sender instanceof Player;
        if (args.length == 0) {
            printHelp(sender);
        } else if (isPlayer && args[0].equalsIgnoreCase("edit")) {
            handleEdit((Player) sender);
        } else if (isPlayer && args[0].equalsIgnoreCase("reopen")) {
            handleReopen((Player) sender);
        } else if (isPlayer && args[0].equalsIgnoreCase("save")) {
            handleSave((Player) sender, args);
        } else if (isPlayer && args[0].equalsIgnoreCase("get")) {
            handleGet((Player) sender, args);
        } else if (args[0].equalsIgnoreCase("list")) {
            handleList(sender, args);
        } else if (args[0].equalsIgnoreCase("delete")) {
            handleDelete(sender, args);
        } else if (args[0].equalsIgnoreCase("give")) {
            handleGive(sender, args);
        }
        return true;
    }

    private void handleGive(CommandSender sender, String[] args) {
        if (args.length != 4) {
            Messages.send(sender, "§cWrong usage. Please use §o/ie give <player> <item> <amount>");
            return;
        }

        String playerName = args[1];
        String itemName = args[2];
        String amountString = args[3];

        final Player player = Bukkit.getPlayer(playerName);
        if (player == null || !player.isOnline()) {
            Messages.send(sender, "§cThe player §o" + playerName + " §cis not online.");
            return;
        }

        final SerializedItem item = ItemEditor.INSTANCE.getItemStorage().getItem(itemName);
        if (item == null) {
            Messages.send(sender, "§cAn item called §o" + itemName + " §ccould not be found.");
            return;
        }

        int amount = 1;
        try {
            amount = Integer.parseInt(amountString);
            if (amount <= 0 || amount > 2304) {
                Messages.send(sender, "§cInvalid amount stated: §o" + amount);
            }
        } catch (NumberFormatException ex) {
            Messages.send(sender, "§cInvalid amount stated: §o" + amountString);
            return;
        }

        final ItemStack toGive = item.getItemStack().clone();
        toGive.setAmount(amount);

        player.getInventory().addItem(toGive);
        Messages.send(sender, "Gave §7" + amount + "x §fitem §7" + itemName + " §fto player §7" + playerName);
    }

    private void handleDelete(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Messages.send(sender, "§cPlease include the name of the item, e.g. §o/ie delete <name>");
            return;
        }

        if (ItemEditor.INSTANCE.getItemStorage().deleteItem(args[1])) {
            Messages.send(sender, "§aThe item was deleted.");
        } else {
            Messages.send(sender, "§cAn item called §o" + args[1] + "§c could not be found.");
        }
    }

    private void handleList(CommandSender sender, String[] args) {
        if (args.length > 1 || !(sender instanceof Player)) {
            if (!(sender instanceof Player) || args[1].equalsIgnoreCase("plain")) {
                List<String> itemNames = ItemEditor.INSTANCE.getItemStorage()
                        .getItems()
                        .stream()
                        .map(SerializedItem::getName)
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(
                                Collectors.toList());

                if (!(sender instanceof Player)) {
                    String itemsString = itemNames.stream().map(n -> "§e" + n).collect(Collectors.joining("§f, "));
                    Messages.send(sender, "Found §7" + itemNames.size() + "§f items: " + itemsString);
                } else {
                    Player player = (Player) sender;
                    ComponentBuilder builder = new ComponentBuilder(
                            Messages.CHAT_PREFIX + "Found §7" + itemNames.size() + " §fitems: ");

                    boolean first = true;
                    for (String name : itemNames) {
                        if (!first) {
                            builder = builder.append("§8, ");
                        }
                        first = false;
                        TextComponent comp = new TextComponent("§e" + name);
                        comp.setHoverEvent(
                                new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        new BaseComponent[]{new TextComponent("§aClick to get item")}));
                        comp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ie get " + name));
                        builder = builder.append(comp);
                    }

                    player.spigot().sendMessage(builder.create());
                    player.sendMessage("§7§oTip: Click on item names to get them.");
                }
            } else {
                Messages.send(sender, "Unknown argument for /ie list [§o" + args[1] + "§c].");
            }
            return;
        }

        ItemEditor.INSTANCE.getUiManager().open(((Player) sender), new ItemListUiScreen(((Player) sender)));
    }

    private void handleGet(Player player, String[] args) {
        if (args.length < 2) {
            Messages.send(player, "§cPlease include the name of the item, e.g. §o/ie get <name>");
            return;
        }

        String name = args[1];
        SerializedItem item = ItemEditor.INSTANCE.getItemStorage().getItem(name);

        if (item == null) {
            Messages.send(player, "§cNo item called §o" + name + " §cwas found.");
            return;
        }

        player.getInventory().setItemInMainHand(item.getItemStack());
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    private void handleSave(Player player, String[] args) {
        if (args.length < 2) {
            Messages.send(player, "§cPlease include a name for your item, e.g. §o/ie save <name>");
            return;
        }

        String name = args[1];

        if (CompatUtils.isAir(player.getInventory().getItemInMainHand())) {
            Messages.send(player, "§cYou need to hold an item in your main hand.");
            return;
        }

        try {
            ItemEditor.INSTANCE.getItemStorage().saveItem(name, player.getInventory().getItemInMainHand());
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            Messages.send(player, "§aYour §o" + NameUtils.enumToFriendlyName(player.getInventory()
                    .getItemInMainHand()
                    .getType()
                    .name()) + " §awas saved under the name §o" + name + "§a.");
        } catch (IOException e) {
            Messages.send(player, "§cSaving failed. Please check the console output for more information.");
            return;
        }

    }

    private void handleReopen(Player player) {
        if (!itemEditor.isEditing(player)) {
            player.sendMessage(Messages.CHAT_PREFIX + "§cNo open editor was found for you.");
            return;
        }

        itemEditor.getUiManager().reopen(player);
    }

    private void handleEdit(Player player) {
        final ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand.getType() == Material.AIR) {
            player.sendMessage(Messages.CHAT_PREFIX + "You need to hold an item in your main hand.");
            return;
        }

        itemEditor.edit(player, hand);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> possibilities = new ArrayList<>();
        if (args.length == 1) {
            possibilities.add("list");
            possibilities.add("give");
            possibilities.add("delete");
            if (sender instanceof Player) {
                possibilities.add("edit");
                possibilities.add("reopen");
                possibilities.add("save");
                possibilities.add("get");
            }
            possibilities = filterByString(possibilities, args[0]);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("get")) {
                possibilities.addAll(getItemNames());
                possibilities = filterByString(possibilities, args[1]);
            } else if (args[0].equalsIgnoreCase("list")) {
                possibilities.add("plain");
            }
        }

        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 2) {
                    possibilities.addAll(
                            Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList()));
                    possibilities = filterByString(possibilities, args[1]);
                } else if (args.length == 3) {
                    possibilities.addAll(getItemNames());
                    possibilities = filterByString(possibilities, args[2]);
                }
            }
        }

        return possibilities;
    }

    private List<String> filterByString(List<String> ls, String filter) {
        return ls.stream().filter(l -> l.toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
    }

    private List<String> getItemNames() {
        return ItemEditor.INSTANCE.getItemStorage()
                .getItems()
                .stream()
                .map(SerializedItem::getName)
                .collect(Collectors.toList());
    }

    private void printHelp(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(
                " §6§lI§6tem§e§lE§editor§f§lP§flus §7v" + ItemEditorPlus.getInstance().getDescription().getVersion());
        sender.sendMessage("");
        sendIfPlayer(sender, "  §e/ie edit §8- §fEdit the item you are holding.");
        sendIfPlayer(sender, "  §e/ie reopen §8- §fReopen the editor with the last item.");
        sendIfPlayer(sender, "  §e/ie save <item> §8- §fSave the item you are holding.");
        sender.sendMessage("  §e/ie delete <name> §8- §fDelete an item.");
        sendIfPlayer(sender, "  §e/ie get <item> §8- §fGive yourself an item.");
        sendIfPlayer(sender, "  §e/ie give <player> <item> <amount> §8- §fGive an item to a player.");
        if (sender instanceof Player) {
            sender.sendMessage("  §e/ie list [plain] §8- §fView a list of all saved items.");
        } else {
            sender.sendMessage("  §e/ie list §8- §fView a list of all saved items.");
        }
    }

    private void sendIfPlayer(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(message);
        }
    }
}
