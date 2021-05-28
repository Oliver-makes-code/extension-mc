package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.extension;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(FallingBlock.class)
public class FallingBlockMixin {
    @Inject(at=@At("HEAD"),method="scheduledTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", cancellable = true)
    public void fallingBlockGamerule(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!extension.fallingBlocks.getValue())
            ci.cancel();
    }
}