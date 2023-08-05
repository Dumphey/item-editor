package at.dumphey.itemeditor.itemeditor.modules.utils.colors;

import at.dumphey.itemeditor.ui.prompts.ChatPrompt;
import at.dumphey.itemeditor.utils.Messages;
import org.bukkit.entity.Player;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterColorCodePrompt extends ChatPrompt {

    private final Runnable onCancel;
    private final Consumer<String> onFulfill;

    public EnterColorCodePrompt(Player player, Runnable onCancel, Consumer<String> onFulfill) {
        super(player, Messages.CHAT_PREFIX + "Enter a color code, e.g. §o#7AE7C7§f:", true);
        this.onCancel = onCancel;
        this.onFulfill = onFulfill;
    }

    @Override
    protected void onCancelled() {
        onCancel.run();
    }

    @Override
    protected void onFulfilled(String input) {
        if (input.startsWith("#")) input = input.substring(1);
        onFulfill.accept(input.toUpperCase());
    }

    @Override
    protected boolean validateInput(String input) {
        input = input.toUpperCase();
        final Pattern pattern = Pattern.compile("^#?([A-Fa-f\\d]{6}|[A-Fa-f\\d]{3})$");
        Matcher matcher = pattern.matcher(input);
        boolean ok = matcher.find();
        if (!ok) {
            player.sendMessage("§c§oInvalid hex color code: " + input);
        }
        return ok;
    }
}
