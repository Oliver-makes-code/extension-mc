package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.extension;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class FarmMixin {
    @Inject(at=@At("HEAD"),method= "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", cancellable = true)
    public void trample(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (!extension.entitiesTrampleCrops.getValue())
            ci.cancel();
    }

    @Redirect(
            at=@At(
                    value="INVOKE",
                    target="Lnet/minecraft/util/math/BlockPos;iterate(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Ljava/lang/Iterable;"
            ),
            method="isWaterNearby(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z"
    )
    private static Iterable<BlockPos> iterate(BlockPos start, BlockPos end) {
        int dist = extension.cropWaterRadius.getValue();
        start = start.add(4-dist,0,4-dist);
        end = end.add(dist-4,0,dist-4);
        return BlockPos.iterate(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()), Math.min(start.getZ(), end.getZ()), Math.max(start.getX(), end.getX()), Math.max(start.getY(), end.getY()), Math.max(start.getZ(), end.getZ()));
    }
}
