package com.github.olivermakescode.extension.mixin;

import com.github.olivermakescode.extension.GameruleHelper;
import com.github.olivermakescode.extension.extension;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemCooldownManager.class)
public class ItemCooldownMixin {
    @Inject(method="set", at=@At("HEAD"), cancellable = true)
    public void cooldownGamerule(Item item, int duration, CallbackInfo ci) {
        if (!extension.itemCooldown.getValue())
            ci.cancel();
        if (!(extension.ePearlEnable.getValue()) && item instanceof EnderPearlItem)
            ci.cancel();
        if (!(extension.chorusEnable.getValue()) && item instanceof ChorusFruitItem)
            ci.cancel();
        if (!(extension.shieldEnable.getValue()) && item instanceof ShieldItem)
            ci.cancel();
    }
}
