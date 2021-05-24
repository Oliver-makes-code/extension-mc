package com.github.olivermakescode.extension;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static java.lang.Integer.parseInt;
import static net.minecraft.command.argument.MessageArgumentType.getMessage;
import static net.minecraft.server.command.CommandManager.*;

public class swapRow {
    public static void swap(int row, PlayerEntity player) {
        int startIdx = row*9;

        for (int i = startIdx; i<startIdx+9; i++) {
            int currSlot = i-startIdx;
            ItemStack currItem = player.getInventory().getStack(i);
            ItemStack currHotbar = player.getInventory().getStack(currSlot);
            player.getInventory().setStack(currSlot,currItem);
            player.getInventory().setStack(i,currHotbar);
        }
        player.getInventory().selectedSlot = 0;
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("hb").then(argument("slot", IntegerArgumentType.integer(1,3)).executes(context -> {
                swap(IntegerArgumentType.getInteger(context,"slot"), context.getSource().getPlayer());
                return 1;
            })));
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("hotbar").then(argument("slot", IntegerArgumentType.integer(1,3)).executes(context -> {
                swap(IntegerArgumentType.getInteger(context,"slot"), context.getSource().getPlayer());
                return 1;
            })));
        });
    }
}
