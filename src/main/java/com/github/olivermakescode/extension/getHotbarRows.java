package com.github.olivermakescode.extension;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class getHotbarRows {
    public static void get(PlayerEntity playerEntity) {
        PlayerInventory inventory = playerEntity.getInventory();

        for (int i = 9; i < 36; i++) {
            ItemStack item = inventory.getStack(i);
            System.out.println(item.toString());
            
        }
    }
}
