package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.extension;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(FarmlandBlock.class)
public class FarmMixin {
    @Inject(at=@At("HEAD"),method= "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", cancellable = true)
    public void trample(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (!extension.entitiesTrampleCrops.getValue()) ci.cancel();
    }

    @Inject(at=@At("HEAD"),method= "isWaterNearby(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
    private static void distance(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        int dist = extension.cropWaterRadius.getValue();
        Iterator newPos = BlockPos.iterate(pos.add(-dist, 0, -dist), pos.add(dist, 1, dist)).iterator();

        BlockPos blockPos;
        do {
            if (!newPos.hasNext()) {
                cir.setReturnValue(false);
                return;
            }
            blockPos = (BlockPos) newPos.next();
        } while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));

        cir.setReturnValue(true);
        return;
    }
}
