package com.github.olivermakescode.extension;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static net.minecraft.server.command.CommandManager.*;

public class swapRow {
    public static void swap(int row, PlayerEntity player) {
        for (int i = row*9; i<row*9+9; i++) {
            int currSlot = i-row*9;
            ItemStack currItem = player.getInventory().getStack(i);
            ItemStack currHotbar = player.getInventory().getStack(currSlot);
            player.getInventory().setStack(currSlot,currItem);
            player.getInventory().setStack(i,currHotbar);
        }
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            var ctx = argument("slot", IntegerArgumentType.integer(1, 3)).executes(context -> {
                swap(IntegerArgumentType.getInteger(context, "slot"), context.getSource().getPlayer());
                return 1;
            });

            dispatcher.register(literal("hotbar").then(ctx));
            dispatcher.register(literal("hb").then(ctx));
        });
    }
}
