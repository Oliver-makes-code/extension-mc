package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.extension;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldFallMixin {
    @Redirect(
            at=@At(
                    value="INVOKE",
                    target="Lnet/minecraft/server/world/ServerWorld;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            ),
            method="scheduledTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V"
    )
    public boolean spawnEntity(ServerWorld serverWorld, Entity entity) {
        if (extension.fallingBlocks.getValue()) return false;
        return serverWorld.spawnEntity(entity);
    }
}
