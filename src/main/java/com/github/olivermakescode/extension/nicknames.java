package com.github.olivermakescode.extension;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class nicknames {
    public static String[] username = {};
    public static String[] nickname = {};

    public static void addName(PlayerEntity user, String nick) {
        int exists = nickExists(user);
        if (exists >= 0) {
            nickname[exists] = nick;
            return;
        }
        username = Arrays.copyOf(username,username.length+1);
        username[username.length-1] = user.getGameProfile().getName();

        nickname = Arrays.copyOf(nickname,nickname.length+1);
        nickname[nickname.length-1] = nick;
    }

    private static int nickExists(PlayerEntity user) {
        for (int i = 0; i < nickname.length; i++) {
            if (username[i] == user.getGameProfile().getName())
                return i;
        }
        return -1;
    }

    public static String getName(PlayerEntity user) {
        for (int i = 0; i < nickname.length; i++) {
            if (username[i].equals(user.getGameProfile().getName())) {
                if (nickname[i] != null)
                    return nickname[i];
                else return user.getGameProfile().getName();
            }
        }
        return user.getGameProfile().getName();
    }

    public static void registerCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("nick").then(argument("nickname", StringArgumentType.string()).executes(context -> {
                addName(context.getSource().getPlayer(),StringArgumentType.getString(context,"nickname"));
                return 1;
            })));
            dispatcher.register(literal("nickname").then(argument("nickname", StringArgumentType.string()).executes(context -> {
                addName(context.getSource().getPlayer(),StringArgumentType.getString(context,"nickname"));
                return 1;
            })));
        });
    }
}
