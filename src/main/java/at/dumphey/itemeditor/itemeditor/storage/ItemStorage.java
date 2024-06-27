package at.dumphey.itemeditor.itemeditor.storage;

import at.dumphey.itemeditor.ItemEditor;
import com.google.common.hash.Hashing;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;

public class ItemStorage {

    private final Map<String, SerializedItem> storedItems = new HashMap<>();

    public void reload() {
        ItemEditor.getInstance().getLogger().log(Level.INFO, "Reloading items.");
        storedItems.clear();

        final Path storagePath = getStoragePath();
        final File storageFolder = new File(storagePath.toUri());

        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }

        for (File file : storageFolder.listFiles()) {
            try {
                loadItem(file);
            } catch (Exception e) { // make sure to catch any weird errors
                ItemEditor.getInstance()
                        .getLogger()
                        .log(Level.SEVERE, "Error while loading item from file " + file.toPath(), e);
            }
        }
        ItemEditor.getInstance()
                .getLogger()
                .log(Level.INFO, String.format("Loaded %d items from storage.", storedItems.size()));
    }

    private Path getStoragePath() {
        final File pluginsFolder = ItemEditor.getInstance().getDataFolder();
        return Paths.get(pluginsFolder.getPath(), "items");
    }

    private void loadItem(File file) throws IOException, ClassNotFoundException {
        final List<String> content = Files.readAllLines(file.toPath());
        final String data = String.join("", content);

        final SerializedItem item = deserializeItem(data);
        storedItems.put(item.getName(), item);
    }

    public Collection<SerializedItem> getItems() {
        return storedItems.values();
    }

    public SerializedItem getItem(String name) {
        return storedItems.get(cleanName(name));
    }


    public void saveItem(String name, ItemStack itemStack) throws IOException {
        String ogName = name;
        name = cleanName(name);
        final SerializedItem item = new SerializedItem(name, itemStack);
        String fileName = Hashing.sha256().hashString(name, StandardCharsets.UTF_8).toString() + "";
        final File file = new File(Paths.get(getStoragePath().toString(), fileName).toUri());
        storedItems.put(ogName, item);
        Files.write(file.toPath(), Collections.singleton(serializeItem(item)));
    }

    public boolean deleteItem(String name) {
        name = cleanName(name);
        String fileName = Hashing.sha256().hashString(name, StandardCharsets.UTF_8).toString() + "";
        final File file = new File(Paths.get(getStoragePath().toString(), fileName).toUri());
        if (!file.exists()) return false;
        storedItems.remove(name);
        file.delete();
        return true;
    }

    private String serializeItem(SerializedItem item) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput;
        dataOutput = new BukkitObjectOutputStream(outputStream);
        dataOutput.writeUTF(item.getName());
        dataOutput.writeObject(item.getItemStack());
        dataOutput.close();
        return Base64Coder.encodeLines(outputStream.toByteArray());

    }

    private static String padBase64String(String base64String) {
        int padding = (4 - base64String.length() % 4) % 4;
        StringBuilder paddedBase64 = new StringBuilder(base64String);
        for (int i = 0; i < padding; i++) {
            paddedBase64.append("=");
        }
        return paddedBase64.toString();
    }

    private SerializedItem deserializeItem(String data) throws IOException, ClassNotFoundException {
        String paddedData = padBase64String(data);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(paddedData));
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        String name = dataInput.readUTF();
        ItemStack item = (ItemStack) dataInput.readObject();
        dataInput.close();
        return new SerializedItem(name, item);
    }

    private String cleanName(String name) {
        return name.toLowerCase().trim();

    }


}
