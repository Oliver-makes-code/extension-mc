package com.github.olivermakescode.extension;

import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.*;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.suggestion.SuggestionProviders;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import static net.minecraft.server.command.CommandManager.literal;

public class musicCommand {
    static RequiredArgumentBuilder<ServerCommandSource, Identifier> requiredArgumentBuilder = CommandManager.argument("sound", IdentifierArgumentType.identifier()).suggests(SuggestionProviders.AVAILABLE_SOUNDS);
    public static void playSound(ServerPlayerEntity entity, SoundEvent sound) {
        entity.playSound(sound,SoundCategory.RECORDS,0x1.fffffeP+127f,1); // Hacky solution, feel free to PR anything better.
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (context.getSource().getEntity() instanceof ServerPlayerEntity) {
            playSound((ServerPlayerEntity)context.getSource().getEntity(), new SoundEvent(IdentifierArgumentType.getIdentifier(context,"sound")));
            return 1;
        }
        return 0;
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("music").then(requiredArgumentBuilder.executes(musicCommand::run)));
        });
    }
}
