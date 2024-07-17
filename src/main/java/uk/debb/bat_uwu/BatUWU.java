package uk.debb.bat_uwu;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class BatUWU implements ModInitializer {
    public static String[] messages;
    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("bat_uwu.txt");
        if (!path.toFile().exists()) {
            try {
                if (!path.toFile().createNewFile()) {
                    throw new RuntimeException("Failed to create file config/bat_uwu.txt");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (FileWriter writer = new FileWriter(path.toFile())) {
                writer.write("# Add your messages here, one per line. ");
                writer.write("Lines that start with '#' are ignored.\n");
                writer.write("Use '%' as a placeholder for the player's name.\n");
                writer.write("Bat was killed by % uwu\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (Stream<String> lines = Files.lines(path)){
            messages = lines.filter(line -> !line.trim().isEmpty() && !line.startsWith("#")).toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
