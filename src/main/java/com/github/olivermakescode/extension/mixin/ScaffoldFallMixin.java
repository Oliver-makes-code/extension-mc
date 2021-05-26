package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.extension;
import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldFallMixin {
    @Shadow @Final public static IntProperty DISTANCE;
    @Shadow @Final public static BooleanProperty BOTTOM;
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    @Inject(at=@At("HEAD"), method= "scheduledTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V",cancellable = true)
    public void fallingBlockGamerule(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        ScaffoldingBlock self = (ScaffoldingBlock)(Object)this;

        int i = ScaffoldingBlock.calculateDistance(world, pos);
        BlockState blockState = state.with(DISTANCE, i).with(BOTTOM, ((ScaffoldAccessor)self).isBottom(world, pos, i));
        if (blockState.get(DISTANCE) == 7)
            if (state.get(DISTANCE) == 7 && extension.fallingBlocks.getValue())
                world.spawnEntity(
                        new FallingBlockEntity(
                            world,
                            pos.getX() + 0.5D,
                            pos.getY() + 0.0D,
                            pos.getZ() + 0.5D,
                            blockState.with(
                                WATERLOGGED,
                                false
                            )
                        )
                );
            else world.breakBlock(pos,true);
        else if (state != blockState)
            world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
        ci.cancel();
    }
}
