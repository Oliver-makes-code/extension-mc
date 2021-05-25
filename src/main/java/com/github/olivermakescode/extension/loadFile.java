package com.github.olivermakescode.extension;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class loadFile {
    public static String load(String name) throws IOException {
        MinecraftServer server = GameruleHelper.server;
        assert server != null;
        String path = server.getSavePath(WorldSavePath.ROOT).toString();
        path = path.substring(0,path.length()-1)+"nick.txt";
        Path newPath = Path.of(path);
        if (!Files.exists(newPath)) {
            Files.createFile(newPath);
            return "";
        }
        return Files.readString(newPath);
    }

    public static void save(String name, String text) throws IOException {
        MinecraftServer server = GameruleHelper.server;
        assert server != null;
        String path = server.getSavePath(WorldSavePath.ROOT).toString();
        path = path.substring(0,path.length()-1)+"nick.txt";
        Path newPath = Path.of(path);
        File file = new File(path);
        Files.deleteIfExists(newPath);
        Files.createFile(newPath);
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(text);
        output.close();
    }
}
