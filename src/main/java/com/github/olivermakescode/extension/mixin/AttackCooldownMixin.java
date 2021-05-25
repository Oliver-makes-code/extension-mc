package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.GameruleHelper;
import com.github.olivermakescode.extension.extension;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class AttackCooldownMixin {
    @Inject(method= "resetLastAttackedTicks()V",at=@At("RETURN"),cancellable = true)
    private void attackCooldownGamerule(CallbackInfo ci) {
        if (GameruleHelper.server != null)
            if (!extension.attackCool.getValue() || !extension.itemCooldown.getValue())
                ci.cancel();
    }
}
