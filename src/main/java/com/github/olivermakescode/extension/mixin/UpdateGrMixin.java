package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.GameRuleInterface;
import com.github.olivermakescode.extension.GameruleHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class UpdateGrMixin {
    @Inject(at=@At("HEAD"),method="tick")
    public void updateGR(CallbackInfo info) {
        World world = ((PlayerEntity)(Object)this).world;
        for (GameRuleInterface i : GameruleHelper.rules) {
            i.updateValue(world);
        }
    }
}
