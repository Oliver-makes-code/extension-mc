package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.GameruleHelper;
import com.github.olivermakescode.extension.extension;
import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public class VillagerTradeMixin {
    @Shadow private int restocksToday;
    @Inject(method = "tick", at = @At("HEAD"))
    public void checkRule(CallbackInfo info) {
        if (GameruleHelper.server != null)
            if (!extension.villagerTradeLock.getValue()) {
                this.restocksToday = 0;
                ((VillagerEntity)(Object)this).restock();
            }
    }
}