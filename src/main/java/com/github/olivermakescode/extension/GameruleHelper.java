package com.github.olivermakescode.extension;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public class GameruleHelper {
    @Nullable
    public static MinecraftServer server;

    public static void start() {
        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> server = minecraftServer);
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> server = null);
    }

    public static GameRuleInterface register(String name, boolean defaultValue) {
        return new BoolRuleHelper(name, defaultValue);
    }

    public static GameRuleInterface register(String name, int defaultValue) {
        return new IntRuleHelper(name, defaultValue);
    }
}
