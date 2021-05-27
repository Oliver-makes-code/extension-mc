package com.github.olivermakescode.extension;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class keybinds implements ClientModInitializer {
    public static KeyBinding hotBarSwitchBind;
    public static int lastNumPressed = 0;
    public static int slot = 0;

    @Override
    public void onInitializeClient() {
        hotBarSwitchBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.extension.hotbar",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                "category.extension"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {

                if (hotBarSwitchBind.wasPressed()) {
                    lastNumPressed = 0;
                    slot = client.player.getInventory().selectedSlot;
                }
                if (hotBarSwitchBind.isPressed()) {
                    assert client.player != null;

                    boolean k1 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_1);
                    boolean k2 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_2);
                    boolean k3 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_3);

                    if (k1 && lastNumPressed != 1) {
                        client.player.sendChatMessage("/hb 1");
                        lastNumPressed = 1;
                    } else if (k2 && lastNumPressed != 2) {
                        client.player.sendChatMessage("/hb 2");
                        lastNumPressed = 2;
                    } else if (k3 && lastNumPressed != 3) {
                        client.player.sendChatMessage("/hb 3");
                        lastNumPressed = 3;
                    } else if (!k1 && !k2 && !k3) {
                        client.player.getInventory().selectedSlot = 0;
                        lastNumPressed = 0;
                    }
                    client.player.getInventory().selectedSlot = slot;
                }
            }
        });
    }
}
