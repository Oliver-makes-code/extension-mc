package com.github.olivermakescode.extension.mixin;

import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ScaffoldingBlock.class)
public interface ScaffoldAccessor {
    @Invoker("shouldBeBottom")
    boolean isBottom(BlockView world, BlockPos pos, int distance);
}
