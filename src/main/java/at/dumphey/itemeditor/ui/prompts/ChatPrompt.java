package at.dumphey.itemeditor.ui.prompts;

import at.dumphey.itemeditor.ui.template.UiComponent;
import org.bukkit.entity.Player;

public abstract class ChatPrompt extends UiComponent {

    private final String message;
    private final boolean isCancellable;

    public ChatPrompt(Player player, String message, boolean isCancellable) {
        super(player);
        this.message = message;
        this.isCancellable = isCancellable;
    }

    public boolean fulfill(String input) {
        if (input.trim().equalsIgnoreCase("cancel") && isCancellable) {
            player.sendMessage("§7§oCancelled.");
            onCancelled();
            return true;
        }

        if (!validateInput(input)) {
            return false;
        }

        onFulfilled(input);
        return true;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    protected abstract void onCancelled();

    protected abstract void onFulfilled(String input);

    protected abstract boolean validateInput(String input);

    public void onComplete() {
    }
}
